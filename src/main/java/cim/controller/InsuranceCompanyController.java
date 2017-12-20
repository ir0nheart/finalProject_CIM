package cim.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cim.entity.InsuranceCompany;
import cim.entity.Message;
import cim.entity.Policy;
import cim.entity.QuoteRequest;
import cim.entity.UserInfo;
import cim.model.InsuranceCompanyM;
import cim.service.IInsuranceCompanyService;
import cim.service.IMessageService;
import cim.service.IUserService;
@Controller
@RequestMapping("/insurance")
public class InsuranceCompanyController {
	@Autowired
	private  IUserService service;
	@Autowired
	private IInsuranceCompanyService icservice;
	@Autowired
	private IMessageService messageService;
	@RequestMapping(value= {"/home","/",""})
	public String home(ModelMap model, Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
			model.addAttribute("user", uinfo);
			return "/secure/insurance/home";
	}
	
	@RequestMapping(value="/info")
	public String showInfo(ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		
		InsuranceCompany ic= icservice.getDataByUserId(uinfo.getUserid());
		InsuranceCompanyM icM= new InsuranceCompanyM();
		if(req.getParameter("errMessage")!=null)
			model.addAttribute("errMessage","Not Yet Activated");
		
		model.addAttribute("user",uinfo);
		model.addAttribute("insurance",ic);
		model.addAttribute("insuranceM",icM);
		
		return "/secure/insurance/user-info";
	}
	
	@RequestMapping(value="/messages")
	public String showMessages(ModelMap model,Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		model.addAttribute("user",uinfo);
		if(!uinfo.isActive()) {
			return "redirect:/insurance/info?errMessage";
			}
		Map<UserInfo,String> messageUserMap = messageService.getMessageUser();
		messageUserMap = getMessageUserMap(messageUserMap,uinfo.getUserid());
		model.addAttribute("messageMap",messageUserMap);
		List<Message> userMessages=messageService.getMessagesForUser(uinfo);
		model.addAttribute("messageList",userMessages);
		return "/secure/insurance/messages";
	}
	
	@RequestMapping(value="/updateInsuranceCompany",method = RequestMethod.POST)
	public String doUpdate(@ModelAttribute("insuranceM") @Valid InsuranceCompanyM icM,BindingResult result,ModelMap model,Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		
		InsuranceCompany ic = icservice.getDataByUserId(uinfo.getUserid());
		if(result.hasErrors()) {
			model.addAttribute("user",uinfo);
			model.addAttribute("insurance",ic);
			return "/secure/insurance/user-info";
		}
		
		ic.setInsuranceCompanyContact(icM.getInsuranceCompanyContact());
		ic.setInsuranceCompanyName(icM.getInsuranceCompanyName());
		ic.setRequestActivate(true);
		
		
			icservice.updateInsuranceCompany(ic);
			model.addAttribute("user",uinfo);
			model.addAttribute("insurance",ic);
			
			return "/secure/insurance/user-info";
	}
	@RequestMapping(value="/show")
	public String show(ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		model.addAttribute("user",uinfo);
		if(!uinfo.isActive()) {
			return "redirect:/insurance/info?errMessage";
			}
		if(!req.getParameterMap().isEmpty() && req.getParameterMap().size()==1) {
			if(req.getParameter("requests") != null) {
				List<QuoteRequest> quoteRequests = icservice.getQuoteRequestByInsuranceCompanyId(icservice.getDataByUserId(uinfo.getUserid()));
				model.put("showType", "requests");
				model.addAttribute("requestList", quoteRequests);
				}
				else if(req.getParameter("pending") != null){
				List<QuoteRequest> pendingRequests = icservice.getPendingQuoteRequestByInsuranceCompanyId(icservice.getDataByUserId(uinfo.getUserid()));
				model.put("showType", "pending");	
				model.addAttribute("pendingList", pendingRequests);
				}else if(req.getParameter("accepted") != null) {
					List<QuoteRequest> acceptedRequests = icservice.getAcceptedQuoteRequestByInsuranceCompanyId(icservice.getDataByUserId(uinfo.getUserid()));
					model.put("showType", "accepted");	
					model.addAttribute("acceptedList", acceptedRequests);
				
				}
			return "/secure/insurance/show";
		}
		return "redirect:/";
	}
	@RequestMapping(value="prepareQuote")
	public String prepareQuote(ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		model.addAttribute("user",uinfo);
		QuoteRequest qr=icservice.getQuoteRequestById(Long.valueOf(req.getParameter("id")));
		model.addAttribute("qr", qr);
		return "/secure/insurance/prepareQuote";
	}
	
	
	@RequestMapping(value="giveQuote")
		public String sendQuote(ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		model.addAttribute("user",uinfo);
		QuoteRequest qr=icservice.getQuoteRequestById(Long.valueOf(req.getParameter("id")));
		qr.setPendingStatus(true);
		qr.setQuoteCost(Double.valueOf(req.getParameter("quoteVal")));
		icservice.updateQuote(qr);
		return "redirect:/insurance/show?requests";
	}
	
	@RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
    public ModelAndView downloadPDF(Authentication authentication,HttpServletRequest req) {
        ModelAndView insuranceQuoteView = new ModelAndView("pdfView");
        UserInfo uinfo = service.getDataByUserName(authentication.getName());
        QuoteRequest qr=icservice.getQuoteRequestById(Long.valueOf(req.getParameter("id")));
        insuranceQuoteView.addObject("userinfo",uinfo);
        InsuranceCompany insuranceCompany=icservice.getDataByUserId(uinfo.getUserid());
        insuranceQuoteView.addObject("insuranceCompany",insuranceCompany);
        insuranceQuoteView.addObject("quoteRequest",qr);
        insuranceQuoteView.addObject("addr",getHostAndPort(req));
        return insuranceQuoteView;
    }
		
		@RequestMapping(value = "/insureCar")
		public String insureCar(ModelMap model,Authentication authentication,HttpServletRequest req) {
			QuoteRequest qr = icservice.getQuoteRequestById(Long.valueOf(req.getParameter("id")));
			UserInfo uinfo = service.getDataByUserName(authentication.getName());
			qr.setInsured(true);
			icservice.insureVehicle(qr);
			List<QuoteRequest> acceptedRequests = icservice.getAcceptedQuoteRequestByInsuranceCompanyId(icservice.getDataByUserId(uinfo.getUserid()));
			
			model.addAttribute("user",uinfo);
			model.put("showType", "accepted");	
			model.addAttribute("acceptedList", acceptedRequests);
		return "secure/insurance/show";
		}
		
		@RequestMapping(value = "/showInsured")
		public String insureCar(ModelMap model,Authentication authentication) {
			UserInfo uinfo = service.getDataByUserName(authentication.getName());
			if(!uinfo.isActive()) {
				return "redirect:/insurance/info?errMessage";
				}
			InsuranceCompany ic=icservice.getDataByUserId(uinfo.getUserid());
			List<Policy> insuredCars = icservice.getInsuredVehicles(ic);
			model.addAttribute("user",uinfo);
			model.addAttribute("list",insuredCars);
			return "secure/insurance/showInsured";
		}
		@RequestMapping(value = "/cancelPolicy")
		public String cancelPolicy(ModelMap model,Authentication authentication,HttpServletRequest req) {
			UserInfo uinfo = service.getDataByUserName(authentication.getName());
			InsuranceCompany ic=icservice.getDataByUserId(uinfo.getUserid());
			Long policyId = Long.valueOf(req.getParameter("id"));
			Policy pol = icservice.getPolicyById(policyId);
			icservice.cancelPolicy(pol);
			List<Policy> insuredCars = icservice.getInsuredVehicles(ic);
			model.addAttribute("user",uinfo);
			model.addAttribute("list",insuredCars);
			return "secure/insurance/showInsured";
		}
		@RequestMapping(value = "/updatePolicy")
		public void updatePolicy(ModelMap model,Authentication authentication) {
			UserInfo uinfo = service.getDataByUserName(authentication.getName());
			InsuranceCompany ic=icservice.getDataByUserId(uinfo.getUserid());
			icservice.updatePolicy(ic);
		}
		@RequestMapping(value="/sendMessage")
		public String sendMessage(ModelMap model,Authentication authentication,HttpServletRequest req) {	
			UserInfo uinfo = service.getDataByUserName(authentication.getName());
			model.addAttribute("user",uinfo);
			Map<UserInfo,String> messageUserMap = messageService.getMessageUser();
			messageUserMap = getMessageUserMap(messageUserMap,uinfo.getUserid());
			model.addAttribute("messageMap",messageUserMap);
			Long messageFrom=uinfo.getUserid();
			Long messageTo=Long.valueOf(req.getParameter("messageTo"));
			String message= req.getParameter("messageBody");
			String messageTitle=req.getParameter("messageTitle");
			Message msg= new Message();
			msg.setMessageBody(message);
			msg.setMessageFromId(messageFrom);
			msg.setMessageToId(messageTo);
			msg.setMessageTitle(messageTitle);
			String pattern = "MM/dd/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			msg.setMessageDate(date);
			messageService.addMessage(msg);
			List<Message> userMessages=messageService.getMessagesForUser(uinfo);
			model.addAttribute("messageList",userMessages);
			
			return "/secure/insurance/messages";
		}
		
		@RequestMapping(value="/readMessage")
		public String readMessage(ModelMap model,Authentication authentication,HttpServletRequest req) {
			UserInfo uinfo = service.getDataByUserName(authentication.getName());
			model.addAttribute("user",uinfo);
			Long msgId= Long.valueOf(req.getParameter("id"));
			Message msg=null;
			if(msgId!=null) {
			msg=messageService.getMessageByMessageId(Long.valueOf(req.getParameter("id")));
				if (msg.getMessageToId() == uinfo.getUserid()) {
					msg.setMessageIsRead(true);
					messageService.updateMessageStatus(msg);
					model.addAttribute("userMessage",msg);
					return "/secure/insurance/readMessage";
				}
			}
			Map<UserInfo,String> messageUserMap = messageService.getMessageUser();
			messageUserMap = getMessageUserMap(messageUserMap,uinfo.getUserid());
			model.addAttribute("messageMap",messageUserMap);
			List<Message> userMessages=messageService.getMessagesForUser(uinfo);
			model.addAttribute("messageList",userMessages);
			
			return "/secure/insurance/messages";
			
			
	}
		
		@RequestMapping(value="/deleteMessage")
		public String deleteMessage(ModelMap model,Authentication authentication,HttpServletRequest req) {
			UserInfo uinfo = service.getDataByUserName(authentication.getName());
			model.addAttribute("user",uinfo);
			Map<UserInfo,String> messageUserMap = messageService.getMessageUser();
			messageUserMap = getMessageUserMap(messageUserMap,uinfo.getUserid());
			model.addAttribute("messageMap",messageUserMap);
			
			Long msgId= Long.valueOf(req.getParameter("id"));
			Message msg=null;
			if(msgId!=null) {
				
				msg=messageService.getMessageByMessageId(Long.valueOf(req.getParameter("id")));
				if (msg.getMessageToId() == uinfo.getUserid()) {
				messageService.deleteMessage(msg);
				}
			}
			List<Message> userMessages=messageService.getMessagesForUser(uinfo);
			model.addAttribute("messageList",userMessages);
			return "/secure/insurance/messages";
		}
		
		Map<UserInfo,String> getMessageUserMap(Map<UserInfo,String> allMap, Long userId){
			Map<UserInfo,String> tempMap = new HashMap<UserInfo,String>();
		
		

			for (Map.Entry<UserInfo, String> entry : allMap.entrySet())
			{
			    if(entry.getKey().getUserid() != userId)
			    	tempMap.put(entry.getKey(), entry.getValue());
			}
			 
			return tempMap;
		}
		
		String getHostAndPort(HttpServletRequest req) {
			URL myUrl = null;
			String domain =req.getRequestURL().toString(); 
			try {
				myUrl = new URL(domain);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String host= myUrl.getHost();
			int port=myUrl.getPort();
			
			String addr = "http://"+host+":"+port;
			return addr;
		}
		@RequestMapping(value="/updateLogoPicture",method=RequestMethod.POST)
		public String updateLogoPicture(@RequestParam("pic")MultipartFile multifile, ModelMap model,Authentication authentication,HttpServletRequest req) {
			UserInfo uinfo = service.getDataByUserName(authentication.getName());
			InsuranceCompany ic = icservice.getDataByUserId(uinfo.getUserid());
			model.addAttribute("user",uinfo);
			model.addAttribute("insurance",ic);
			if(!multifile.isEmpty()) {
			ic.setPhoto(multifile);
			icservice.updateInsuranceCompany(ic);
			}
			return "secure/cowner/user-info";
		}
		@RequestMapping(value="/showLogo",method=RequestMethod.GET)
		public @ResponseBody String viewPhoto(Authentication authentication,HttpServletResponse res) throws IOException {
			UserInfo uinfo = service.getDataByUserName(authentication.getName());
			InsuranceCompany ic = icservice.getDataByUserId(uinfo.getUserid());
			byte[] photoBytes = ic.getPhotoBytes();
			if(photoBytes!=null) {
				int photoLength=photoBytes.length;
				try(ServletOutputStream sos = res.getOutputStream()){
				
				res.setContentType(ic.getPhotoContentType());
				res.setContentLength(photoLength);
				res.setHeader("Content-Disposition", "inline; filename=\""+ic.getPhotoFileName()+"\"");
				sos.write(photoBytes);
				sos.flush();
				}
			
			}
			return "";
		}
		
}
