package cim.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.services.calendar.model.Calendar;

import cim.entity.CarOwner;
import cim.entity.InsuranceCompany;
import cim.entity.MaintenanceCompany;
import cim.entity.Message;
import cim.entity.UserInfo;
import cim.model.CalendarSample;
import cim.service.ICarOwnerService;
import cim.service.IInsuranceCompanyService;
import cim.service.IMaintenanceCompanyService;
import cim.service.IMessageService;
import cim.service.IUserService;



@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private  IUserService service;
	@Autowired
	private  ICarOwnerService coservice;
	@Autowired
	private  IInsuranceCompanyService icservice;
	@Autowired
	private  IMaintenanceCompanyService mcservice;
	@Autowired
	private  IMessageService messageService;
	
	private CalendarSample calendarController = CalendarSample.getInstance();
	@RequestMapping(value= {"/","/home",""})
	public String home(ModelMap model, Authentication authentication) throws IOException {
			UserInfo uinfo = service.getDataByUserName(authentication.getName());
			model.addAttribute("user", uinfo);
			return "/secure/admin/home";
		
 	}
	
	@RequestMapping(value="/info")
	public String showInfo(ModelMap model,Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		model.addAttribute("user",uinfo);
		return "/secure/admin/user-info";
	}
	
	@RequestMapping(value="/activate")
	public String showActivate(ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		model.addAttribute("user",uinfo);
		if(!req.getParameterMap().isEmpty() && req.getParameterMap().size()==1) {
		if(req.getParameter("carowner") != null) {
			List<CarOwner> carowners = getCarOwners();
			model.put("listType", "carowner");
			model.addAttribute("list", carowners);}
			else if(req.getParameter("insurance") != null){
			List<InsuranceCompany> insuranceCompanies = getInsuranceCompanies();
			model.put("listType", "insurance");
			model.addAttribute("list", insuranceCompanies);
			}else if(req.getParameter("maintenance") != null) {
			List<MaintenanceCompany> maintenanceCompanies = getMaintenanceCompanies();
				model.put("listType", "maintenance");
				model.addAttribute("list", maintenanceCompanies);
			}
		return "/secure/admin/activate";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/show")
	public String showUserInfo(ModelMap model,Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		model.addAttribute("user",uinfo);
		return "/secure/admin/show";
	}
	@RequestMapping(value="/messages")
	public String showMessages(ModelMap model,Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		model.addAttribute("user",uinfo);
		Map<UserInfo,String> messageUserMap = messageService.getMessageUser();
		messageUserMap = getMessageUserMap(messageUserMap,uinfo.getUserid());
		model.addAttribute("messageMap",messageUserMap);
		List<Message> userMessages=messageService.getMessagesForUser(uinfo);
		model.addAttribute("messageList",userMessages);
		return "/secure/admin/messages";
	}
	@RequestMapping(value="/activateCarOwner")
	public String activateCarOwner(ModelMap model,Authentication authentication,HttpServletRequest req) {
		Long carOwnerId = Long.valueOf(req.getParameter("id"));
		CarOwner co=coservice.getDataByOwnerId(carOwnerId);
		UserInfo uinfo = service.getDataByUserName(co.getUserinfo().getUsername());
		UserInfo admin = service.getDataByUserName(authentication.getName());
		if(co!=null && uinfo!=null) {
		
		uinfo.setActive(true);
		service.updateUser(uinfo);
		
		model.addAttribute("user",admin);
		List<CarOwner> carowners = getCarOwners();
		model.put("listType", "carowner");
		model.addAttribute("list", carowners);
		sendActivationMessage(admin, "carOwner", uinfo.getUserid());
		return "/secure/admin/activate";
		}else {
			uinfo=service.getDataByUserName(authentication.getName());
			List<CarOwner> carowners = getCarOwners();
			model.put("listType", "carowner");
			model.addAttribute("list", carowners);
			model.addAttribute("user",uinfo);
		return "/secure/admin/activate";
		}
	}
		@RequestMapping(value="/declineCarOwner")
		public String declineCarOwner(ModelMap model,Authentication authentication,HttpServletRequest req) {
			CarOwner co=coservice.getDataByOwnerId(Long.valueOf(req.getParameter("id")));
			UserInfo uinfo=null;
			if(co!=null) {
			co.setRequestActivate(false);
			coservice.updateCarOwner(co);
			uinfo=service.getDataByUserName(authentication.getName());
			model.addAttribute("user",uinfo);
			List<CarOwner> carowners = getCarOwners();
			model.put("listType", "carowner");
			model.addAttribute("list", carowners);
			return "/secure/admin/activate";
			}else {
				uinfo=service.getDataByUserName(authentication.getName());
				List<CarOwner> carowners = getCarOwners();
				model.put("listType", "carowner");
				model.addAttribute("list", carowners);
				model.addAttribute("user",uinfo);
			return "/secure/admin/activate";	
			}
		
	}
	
		
		
		@RequestMapping(value="/activateMaintenanceCompany")
		public String activateMaintenanceCompany(ModelMap model,Authentication authentication,HttpServletRequest req) {
			Long maintenanceCompanyId=Long.valueOf(req.getParameter("id"));
			MaintenanceCompany mc=mcservice.getDataByCompanyId(maintenanceCompanyId);
			UserInfo uinfo = service.getDataByUserName(mc.getUserinfo().getUsername());
			UserInfo admin = service.getDataByUserName(authentication.getName());
			if(mc!=null && uinfo!=null) {
			
			uinfo.setActive(true);
			StringBuilder calendarName = new StringBuilder();
			calendarName.append(mc.getMaintenanceCompanyName());
			calendarName.append(" ");
			calendarName.append("Calendar");
			Calendar calendar=null;
			try {
				calendar = calendarController.addCalendar(calendarName.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			mc.setCalendarId(calendar.getId());
			mcservice.updateMaintenanceCompany(mc);
			service.updateUser(uinfo);
		
			model.addAttribute("user",admin);
			List<MaintenanceCompany> maintenanceCompanies = getMaintenanceCompanies();
			model.put("listType", "maintenance");
			model.addAttribute("list", maintenanceCompanies);
			sendActivationMessage(admin,"maintenance",uinfo.getUserid());
			return "/secure/admin/activate";
			}else {
				uinfo=service.getDataByUserName(authentication.getName());
				List<MaintenanceCompany> maintenanceCompanies = getMaintenanceCompanies();
				model.put("listType", "maintenance");
				model.addAttribute("list", maintenanceCompanies);
				model.addAttribute("user",uinfo);
			return "/secure/admin/activate";
			}
		}
			@RequestMapping(value="/declineMaintenanceCompany")
			public String declineMaintenanceCompany(ModelMap model,Authentication authentication,HttpServletRequest req) {
				MaintenanceCompany mc=mcservice.getDataByCompanyId(Long.valueOf(req.getParameter("id")));
				UserInfo uinfo=null;
				if(mc!=null) {
				mc.setRequestActivate(false);
				mcservice.updateMaintenanceCompany(mc);
				uinfo=service.getDataByUserName(authentication.getName());
				model.addAttribute("user",uinfo);
				List<MaintenanceCompany> maintenanceCompanies = getMaintenanceCompanies();
				model.put("listType", "maintenance");
				model.addAttribute("list", maintenanceCompanies);
				return "/secure/admin/activate";
				}else {
					uinfo=service.getDataByUserName(authentication.getName());
					List<MaintenanceCompany> maintenanceCompanies = getMaintenanceCompanies();
					model.put("listType", "maintenance");
					model.addAttribute("list", maintenanceCompanies);
					model.addAttribute("user",uinfo);
				return "/secure/admin/activate";	
				}
			
		}	
			
			@RequestMapping(value="/activateInsuranceCompany")
			public String activateInsuranceCompany(ModelMap model,Authentication authentication,HttpServletRequest req) {
				Long insuranceCompanyId =Long.valueOf(req.getParameter("id"));
				InsuranceCompany ic=icservice.getDataByCompanyId(insuranceCompanyId);
				UserInfo uinfo = service.getDataByUserName(ic.getUserinfo().getUsername());
				UserInfo admin = service.getDataByUserName(authentication.getName());
				if(ic!=null && uinfo!=null) {
				
				uinfo.setActive(true);
				service.updateUser(uinfo);
				
				model.addAttribute("user",admin);
				List<InsuranceCompany> insuranceCompanies = getInsuranceCompanies();
				model.put("listType", "insurance");
				model.addAttribute("list", insuranceCompanies);
				sendActivationMessage(admin,"insurance",uinfo.getUserid());
				
				return "/secure/admin/activate";
				}else {
					uinfo=service.getDataByUserName(authentication.getName());
					List<InsuranceCompany> insuranceCompanies = getInsuranceCompanies();
					model.put("listType", "insurance");
					model.addAttribute("list", insuranceCompanies);
					model.addAttribute("user",uinfo);
				return "/secure/admin/activate";
				}
			}
				@RequestMapping(value="/declineInsuranceCompany")
				public String declineInsuranceCompany(ModelMap model,Authentication authentication,HttpServletRequest req) {
					InsuranceCompany ic=icservice.getDataByCompanyId(Long.valueOf(req.getParameter("id")));
					UserInfo uinfo=null;
					if(ic!=null) {
					ic.setRequestActivate(false);
					icservice.updateInsuranceCompany(ic);
					uinfo=service.getDataByUserName(authentication.getName());
					model.addAttribute("user",uinfo);
					List<InsuranceCompany> insuranceCompanies = getInsuranceCompanies();
					model.put("listType", "insurance");
					model.addAttribute("list", insuranceCompanies);
					return "/secure/admin/activate";
					}else {
						uinfo=service.getDataByUserName(authentication.getName());
						List<InsuranceCompany> insuranceCompanies = getInsuranceCompanies();
						model.put("listType", "insurance");
						model.addAttribute("list", insuranceCompanies);
						model.addAttribute("user",uinfo);
					return "/secure/admin/activate";	
					}
				
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
					
					return "/secure/admin/messages";
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
					return "/secure/admin/readMessage";
						}
					}
					Map<UserInfo,String> messageUserMap = messageService.getMessageUser();
					messageUserMap = getMessageUserMap(messageUserMap,uinfo.getUserid());
					model.addAttribute("messageMap",messageUserMap);
					List<Message> userMessages=messageService.getMessagesForUser(uinfo);
					model.addAttribute("messageList",userMessages);
					
					return "/secure/admin/messages";
					
					
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
					return "/secure/admin/messages";
				}
	List<CarOwner> getCarOwners(){
		List<CarOwner> tempList =coservice.getActivationList();
	
		Iterator<CarOwner> iter = tempList.iterator();

		while (iter.hasNext()) {
		    CarOwner co = iter.next();

		    if(service.getDataByUserName(co.getUserinfo().getUsername()).isActive()) {
		        iter.remove();
		        }
		}
		return tempList;
	}
	List<InsuranceCompany> getInsuranceCompanies(){
		List<InsuranceCompany> tempList =icservice.getActivationList();
	
		Iterator<InsuranceCompany> iter = tempList.iterator();

		while (iter.hasNext()) {
		    InsuranceCompany ic = iter.next();

		    if(service.getDataByUserName(ic.getUserinfo().getUsername()).isActive()) {
		        iter.remove();
		        }
		}
		return tempList;
	}
	
	List<MaintenanceCompany> getMaintenanceCompanies(){
		List<MaintenanceCompany> tempList =mcservice.getActivationList();
	
		Iterator<MaintenanceCompany> iter = tempList.iterator();

		while (iter.hasNext()) {
			MaintenanceCompany mc = iter.next();

		    if(service.getDataByUserName(mc.getUserinfo().getUsername()).isActive()) {
		        iter.remove();
		        }
		}
		return tempList;
	}
	
	void sendActivationMessage(UserInfo uinfo,String type,long id) {
		Long messageTo=id;
		switch(type) {
		case "insurance":{
			
		}break;
		case "maintenance":break;
		case "carOwner":break;
		}
		
		Long messageFrom=uinfo.getUserid();
		
			
		String message="<h2><strong>Welcome to CIM Platform.</strong></h2>\n" + 
				"\n" + 
				"<hr />\n" + 
				"<p>Your profile is now <span class=\"marker\">activated</span>.</p>\n" + 
				"\n" + 
				"<p>Admin.</p>\n"; 
		String messageTitle="Profile Activated";
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
}
