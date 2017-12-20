package cim.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
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

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import cim.entity.Appointment;
import cim.entity.CarOwner;
import cim.entity.InsuranceCompany;
import cim.entity.MaintenanceCompany;
import cim.entity.Message;
import cim.entity.QuoteRequest;
import cim.entity.Schedule;
import cim.entity.UserInfo;
import cim.entity.Vehicle;
import cim.entity.Policy;
import cim.model.CalendarSample;
import cim.model.CarOwnerM;
import cim.model.FullCalendarEventObject;
import cim.model.VehicleM;
import cim.service.ICarOwnerService;
import cim.service.IInsuranceCompanyService;
import cim.service.IMaintenanceCompanyService;
import cim.service.IMessageService;
import cim.service.IUserService;

@Controller
@MultipartConfig
@RequestMapping("/cowner")
public class CarOwnerController {
	@Autowired
	private  IUserService service;
	@Autowired
	private  ICarOwnerService coservice;
	@Autowired
	private IInsuranceCompanyService icservice;
	@Autowired
	private IMaintenanceCompanyService mcservice;
	@Autowired
	private IMessageService messageService;
	
	private CalendarSample calendarController = CalendarSample.getInstance();
	@RequestMapping(value= {"/","/home",""})
	public String home(ModelMap model, Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
			model.addAttribute("user", uinfo);
		return "/secure/cowner/home";		
 	}
	
	@RequestMapping(value="/info")
	public String showInfo(ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		CarOwner co = coservice.getDataByUserId(uinfo.getUserid());
		CarOwnerM com = new CarOwnerM();
		if(req.getParameter("errMessage")!=null)
			model.addAttribute("errMessage","Not Yet Activated");
		model.addAttribute("user",uinfo);
		model.addAttribute("owner",co);
		model.addAttribute("cownerM",com);
		
		return "/secure/cowner/user-info";
	}
	@RequestMapping(value="/updateProfilePicture",method=RequestMethod.POST)
	public String updateProfilePicture(@RequestParam("pic")MultipartFile multifile, ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		CarOwner co = coservice.getDataByUserId(uinfo.getUserid());
		model.addAttribute("user",uinfo);
		model.addAttribute("owner",co);
		if(!multifile.isEmpty()) {
		co.setPhoto(multifile);
		coservice.updateCarOwner(co);
		}
		return "secure/cowner/user-info";
	}
	
	@RequestMapping(value="/updateCarOwner",method = RequestMethod.POST)
	public String doUpdate(@ModelAttribute("cownerM") @Valid CarOwnerM cowner,BindingResult result,ModelMap model,Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		CarOwner co = coservice.getDataByUserId(uinfo.getUserid());
		if(result.hasErrors()) {
			model.addAttribute("user",uinfo);
			model.addAttribute("owner",co);
			return "/secure/cowner/user-info";
		}
		co.setCarOwnerCity(cowner.getCarOwnerCity());
		co.setCarOwnerCountry(cowner.getCarOwnerCountry());
		co.setCarOwnerRoute(cowner.getCarOwnerRoute());
		co.setCarOwnerState(cowner.getCarOwnerState());
		co.setCarOwnerStreetNumber(cowner.getCarOwnerStreetNumber());
		co.setCarOwnerZipCode(cowner.getCarOwnerZipCode());
		co.setAddress(cowner.getAddress());
		co.setFirstName(cowner.getFirstName());
		co.setLastName(cowner.getLastName());
		co.setDateOfBirth(cowner.getDateOfBirth());
		co.setDriverLicenseExpiration(cowner.getDriverLicenseExpiration());
		co.setDriverLicenseNo(cowner.getDriverLicenseNo());
		co.setDriverLicenseSince(cowner.getDriverLicenseSince());
		if(cowner.getMiddleName() !=null && !(cowner.getMiddleName().equals("")))
			co.setMiddleName(cowner.getMiddleName());
		co.setRequestActivate(true);
		
			coservice.updateCarOwner(co);
			model.addAttribute("user",uinfo);
			model.addAttribute("owner",co);
			
			return "/secure/cowner/user-info";
	}
	
	@RequestMapping(value="/vehicles")
	public String showVehicles(ModelMap model,Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		CarOwner co = coservice.getDataByUserId(uinfo.getUserid());
		model.addAttribute("user",uinfo);
		if(!uinfo.isActive()) {
		return "redirect:/cowner/info?errMessage";
		}
		List<Vehicle> vehicleList = coservice.getVehicleList(co);
		model.addAttribute("list",vehicleList);
		return "/secure/cowner/vehicles";
	}
	
	@RequestMapping(value="/insuranceservices")
	public String showInsuranceServices(ModelMap model,Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		model.addAttribute("user",uinfo);
		List<InsuranceCompany> insuranceCompanyList =icservice.getInsuranceCompanyList();
		model.addAttribute("insuranceCompanyList",insuranceCompanyList);
		if(!uinfo.isActive()) {
			return "redirect:/cowner/info?errMessage";
			}
		return "/secure/cowner/showInsurance";
	}
	@RequestMapping(value="/maintenanceservices")
	public String showMaintenanceServices(ModelMap model,Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		model.addAttribute("user",uinfo);
		List<MaintenanceCompany> maintenanceCompanyList =mcservice.getMaintenanceCompanyList();
		model.addAttribute("maintenanceCompanyList",maintenanceCompanyList);
		if(!uinfo.isActive()) {
			return "redirect:/cowner/info?errMessage";
			}
		return "/secure/cowner/showMaintenance";
	}
	@RequestMapping(value="/messages")
	public String showMessages(ModelMap model,Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		model.addAttribute("user",uinfo);
		if(!uinfo.isActive()) {
			return "redirect:/cowner/info?errMessage";
			}
		Map<UserInfo,String> messageUserMap = messageService.getMessageUser();
		messageUserMap = getMessageUserMap(messageUserMap,uinfo.getUserid());
		model.addAttribute("messageMap",messageUserMap);
		List<Message> userMessages=messageService.getMessagesForUser(uinfo);
		model.addAttribute("messageList",userMessages);
		return "/secure/cowner/messages";
	}
	
	@RequestMapping(value="/addVehicle")
	public String addVehicle(ModelMap model,Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		model.addAttribute("user",uinfo);
		VehicleM vm= new VehicleM();
		model.addAttribute("vehicleM",vm);
		return "/secure/cowner/addVehicle";
	}
	@RequestMapping(value="/registerVehicle")
	public String doRegister(@ModelAttribute("vehicleM") @Valid VehicleM vehicleM,BindingResult result,ModelMap model,Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		CarOwner co = coservice.getDataByUserId(uinfo.getUserid());
		if(result.hasErrors()) {
			model.addAttribute("vehicleM",vehicleM);
			model.addAttribute("user",uinfo);
			return "/secure/cowner/addVehicle";
		}
		Vehicle m = new Vehicle();
		m.setVehicleKM(vehicleM.getVehicleKM());
		m.setVehiclePlate(vehicleM.getVehiclePlate());
		m.setVehicleOwner(co);
		m.setVehicleMake(vehicleM.getVehicleMake());
		m.setVehicleModel(vehicleM.getVehicleModel());
		m.setVehicleYear(vehicleM.getVehicleYear());
		m.setVehicleTransmission(vehicleM.getVehicleTransmission());
		
		coservice.addVehicle(m);
		model.addAttribute("user",uinfo);
		List<Vehicle> vehicleList = coservice.getVehicleList(co);
		model.addAttribute("list",vehicleList);
	
		return "/secure/cowner/vehicles";
	}
	
	@RequestMapping(value="/quoteRequest")
	public String requestQuoteFrom(ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		CarOwner co = coservice.getDataByUserId(uinfo.getUserid());
		
		List<Vehicle> vehicleList = coservice.getNotInsuredVehicleList(co);
		InsuranceCompany insurance = icservice.getDataByCompanyId(Long.valueOf(req.getParameter("id")));
		List<Vehicle> vehicleList2=QuoteNotRequested(vehicleList,insurance);
		List<QuoteRequest> qrList=coservice.getPendingQuoteRequestByCarOwnerId(co);
		model.addAttribute("user",uinfo);
		model.addAttribute("vehicleList",vehicleList2);
		model.addAttribute("insurance",insurance);
		model.addAttribute("pendingQuotes",qrList);
		
		return "/secure/cowner/quoteRequest";
	}
	
	@RequestMapping(value="/appointmentFrom")
	public String appointmentFrom(ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		CarOwner co = coservice.getDataByUserId(uinfo.getUserid());
		Long maintenanceCompanyId = Long.valueOf(req.getParameter("id"));
		MaintenanceCompany mc = mcservice.getDataByCompanyId(maintenanceCompanyId);
		List<Vehicle> vehicleList = coservice.getVehicleList(co);
		model.addAttribute("user",uinfo);
		model.addAttribute("maintenance",mc);
		model.addAttribute("vehicleList",vehicleList);
		
		return "/secure/cowner/appointment";
		
	}
	
	@RequestMapping(value="/askQuote")
	public String requestQuote(ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		CarOwner co = coservice.getDataByUserId(uinfo.getUserid());
		InsuranceCompany ic=icservice.getDataByCompanyId(Long.valueOf(req.getParameter("insurance")));
		if(req.getParameter("vehicle")!=null) {
		Vehicle veh=coservice.getVehicleById(Long.valueOf(req.getParameter("vehicle")));
		
		QuoteRequest qr = new QuoteRequest();
		qr.setInsuranceCompany(ic);
		qr.setQuoteTerm(Integer.valueOf(req.getParameter("term")));
		qr.setVehicle(veh);
		coservice.askQuote(qr);
		}
		model.addAttribute("user",uinfo);
		List<Vehicle> vehicleList = coservice.getVehicleList(co);
		List<Vehicle> vehicleList2=QuoteNotRequested(vehicleList,ic);
		List<QuoteRequest> qrList=coservice.getPendingQuoteRequestByCarOwnerId(co);
		model.addAttribute("pendingQuotes",qrList);
		model.addAttribute("vehicleList",vehicleList2);
		model.addAttribute("insurance",ic);
		
		return "redirect:/cowner/quoteRequest?id="+ic.getInsuranceCompanyId();
	}
	
	
	List<Vehicle> QuoteNotRequested(List<Vehicle> vehicles, InsuranceCompany ic){
		List<Vehicle> tempList = vehicles;
		Iterator<Vehicle> iter = tempList.iterator();
		while(iter.hasNext()) {
			Vehicle veh = iter.next();
			List<QuoteRequest>qrs=coservice.getQuoteRequestByVehicleId(veh);
			Iterator<QuoteRequest> qrIter=qrs.iterator();
			while(qrIter.hasNext()) {
				QuoteRequest qr=qrIter.next();
				if(qr.getInsuranceCompany().getInsuranceCompanyId() == ic.getInsuranceCompanyId()) {
					iter.remove();
				}
				
			}
			}		
		return tempList;
		
		}
	@RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
    public ModelAndView downloadPDF(Authentication authentication,HttpServletRequest req) {
        ModelAndView insuranceQuoteView = new ModelAndView("pdfView");
        UserInfo uinfo = service.getDataByUserName(authentication.getName());
        QuoteRequest qr=coservice.getQuoteRequestById(Long.valueOf(req.getParameter("id")));
        insuranceQuoteView.addObject("userinfo",uinfo);
        InsuranceCompany insuranceCompany=qr.getInsuranceCompany();
        insuranceQuoteView.addObject("insuranceCompany",insuranceCompany);
        insuranceQuoteView.addObject("quoteRequest",qr);
        insuranceQuoteView.addObject("addr",getHostAndPort(req));
        return insuranceQuoteView;
    }
	@RequestMapping(value = "/acceptQuote")
	public String acceptQuote(ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		CarOwner co = coservice.getDataByUserId(uinfo.getUserid());
		QuoteRequest qr = coservice.getQuoteRequestById(Long.valueOf(req.getParameter("id")));
		qr.setAcceptedStatus(true);
		coservice.acceptQuote(qr);
		List<Vehicle> vehicleList = coservice.getVehicleList(co);
		InsuranceCompany insurance = qr.getInsuranceCompany();
		List<Vehicle> vehicleList2=QuoteNotRequested(vehicleList,insurance);
		List<QuoteRequest> qrList=coservice.getPendingQuoteRequestByCarOwnerId(co);
		model.addAttribute("user",uinfo);
		model.addAttribute("vehicleList",vehicleList2);
		model.addAttribute("insurance",insurance);
		model.addAttribute("pendingQuotes",qrList);
		
		
	 return "redirect:/cowner/quoteRequest?id="+insurance.getInsuranceCompanyId();
		
	}
	
	@RequestMapping(value = "/declineQuote")
	public String declineQuote(ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		CarOwner co = coservice.getDataByUserId(uinfo.getUserid());
		QuoteRequest qr = coservice.getQuoteRequestById(Long.valueOf(req.getParameter("id")));
		coservice.declineQuote(qr);
		List<Vehicle> vehicleList = coservice.getVehicleList(co);
		InsuranceCompany insurance =qr.getInsuranceCompany();
		List<Vehicle> vehicleList2=QuoteNotRequested(vehicleList,insurance);
		List<QuoteRequest> qrList=coservice.getPendingQuoteRequestByCarOwnerId(co);
		model.addAttribute("user",uinfo);
		model.addAttribute("vehicleList",vehicleList2);
		model.addAttribute("insurance",insurance);
		model.addAttribute("pendingQuotes",qrList);
		
		return "redirect:/cowner/quoteRequest?id="+insurance.getInsuranceCompanyId();
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
		
		return "/secure/cowner/messages";
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
		return "/secure/cowner/readMessage";
			}
		}
		Map<UserInfo,String> messageUserMap = messageService.getMessageUser();
		messageUserMap = getMessageUserMap(messageUserMap,uinfo.getUserid());
		model.addAttribute("messageMap",messageUserMap);
		List<Message> userMessages=messageService.getMessagesForUser(uinfo);
		model.addAttribute("messageList",userMessages);
		
		return "/secure/cowner/messages";
		
		
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
		return "/secure/cowner/messages";
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
	
	@RequestMapping(value = "/showVehicle")
	public String showVehicle(ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		Vehicle veh=coservice.getVehicleById(Long.valueOf(req.getParameter("id")));
		model.addAttribute("vehicle",veh);
		Policy pol = coservice.getInsurance(veh);
		if(veh.isInsured()) {model.addAttribute("insuranceStat","insured");
		model.addAttribute("policy",pol);
		}else {
			model.addAttribute("insuranceStat","Not insured");
		}
		model.addAttribute("user",uinfo);
		return "secure/cowner/showVehicle";
	}
	
	@RequestMapping(value="/showPhoto",method=RequestMethod.GET)
	public @ResponseBody String viewPhoto(Authentication authentication,HttpServletResponse res) throws IOException {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		CarOwner co = coservice.getDataByUserId(uinfo.getUserid());
		byte[] photoBytes = co.getPhotoBytes();
		if(photoBytes!=null) {
			int photoLength=photoBytes.length;
			try(ServletOutputStream sos = res.getOutputStream()){
			
			res.setContentType(co.getPhotoContentType());
			res.setContentLength(photoLength);
			res.setHeader("Content-Disposition", "inline; filename=\""+co.getPhotoFileName()+"\"");
			sos.write(photoBytes);
			sos.flush();
			}
		
		}
		return "";
	}
	@RequestMapping(value="/showLogo",method=RequestMethod.GET)
	public @ResponseBody String viewLogo(Authentication authentication,HttpServletRequest req,HttpServletResponse res) throws IOException {
		String type=req.getParameter("type");
		Long id = Long.valueOf(req.getParameter("id"));
		byte[] photoBytes = null;
		String photoFileName = null;
		String contentType = null;
		if(type!=null && type!="") {
			if(type.equals("maintenance")) {
				MaintenanceCompany mc = mcservice.getDataByCompanyId(id);
				photoBytes=mc.getPhotoBytes();
				photoFileName=mc.getPhotoFileName();
				contentType=mc.getPhotoContentType();
			}else if(type.equals("insurance")){
				InsuranceCompany ic = icservice.getDataByCompanyId(id);
				photoBytes=ic.getPhotoBytes();
				photoFileName=ic.getPhotoFileName();
				contentType=ic.getPhotoContentType();
			}
				
		}
		
		if(photoBytes!=null) {
			int photoLength=photoBytes.length;
			try(ServletOutputStream sos = res.getOutputStream()){
			
			res.setContentType(contentType);
			res.setContentLength(photoLength);
			res.setHeader("Content-Disposition", "inline; filename=\""+ photoFileName+"\"");
			sos.write(photoBytes);
			sos.flush();
			}
		
		}
		return "";
	}
	
	
	@RequestMapping(value="/makeAppoint", method=RequestMethod.POST)
	public String makeAppointment(ModelMap model,Authentication authentication,HttpServletRequest req) throws IOException {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		Long carOwnerId=Long.valueOf(uinfo.getUserid());
		CarOwner co = coservice.getDataByUserId(carOwnerId);
		Long vehicleId = Long.valueOf(req.getParameter("car"));
		Long maintenanceCompanyId = Long.valueOf(req.getParameter("maintenanceCompany"));
		MaintenanceCompany mc = mcservice.getDataByCompanyId(maintenanceCompanyId);
		String shr= req.getParameter("radio");
		String appointmentDate = req.getParameter("appointmentDate");
		Integer ihr=Integer.valueOf(shr.substring(5))+7;
		Vehicle veh = coservice.getVehicleById(vehicleId);
		Schedule sch = null;
		sch = coservice.getSchedule(appointmentDate,maintenanceCompanyId);
		if(sch==null)
		sch = new Schedule();
		sch.setMaintenanceCompany(mc);
		sch.setScheduleDate(appointmentDate);
		coservice.createOrUpdateSchedule(sch);
		Appointment app = new Appointment();
		app.setAppointmentForId(vehicleId);
		app.setAppointmentFromId(co.getCarownerid());
		app.setAppointmentToId(maintenanceCompanyId);
		app.setScheduleDate(sch.getScheduleDate());
		app.setAppointmentTime(ihr);
		String timeStart =ihr.toString();
		String timeEnd=String.valueOf(ihr+1);
		Integer itimeEnd = Integer.valueOf(timeEnd);
		Integer itimeStart = Integer.valueOf(timeStart);
		if(itimeStart<10) {
			timeStart = "0"+timeStart;
		}
		if (itimeEnd < 10) {
			timeEnd="0"+timeEnd;
		}
		String summary= co.getFirstName() +" "+ co.getLastName() + "," + veh.getVehiclePlate();
		String startDate = sch.getScheduleDate()+"T"+timeStart+":00:00-05:00";
		String endDate = sch.getScheduleDate()+"T"+timeEnd+":00:00-05:00";
		
		coservice.createOrUpdateAppointment(app);
	
Event event = new Event();
		
		event.setSummary(summary);
		event.setHtmlLink("http://localhost:8080/finalProjectCIM/maintenance/checkAppointment?id="+app.getAppointmentId());
		event.setStart(new EventDateTime().setDateTime(new DateTime(startDate)));
		event.setEnd(new EventDateTime().setDateTime(new DateTime(endDate)));
		event.setLocation(mc.getMaintenanceCompanyAddress());
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			// Convert object to JSON string
			 String jsonInString = mapper.writeValueAsString(event);
			// System.out.println(jsonInString);

			// Convert object to JSON string and pretty print
		//String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(event);

			// System.out.println(jsonInString);
		app.setJsonAppointment(jsonInString);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FullCalendarEventObject fcEvent = new FullCalendarEventObject();
		fcEvent.setId(app.getAppointmentId().toString());
		fcEvent.setTitle(summary);
		fcEvent.setStart(sch.getScheduleDate()+"T"+timeStart+":00:00");
		fcEvent.setEnd(sch.getScheduleDate()+"T"+timeEnd+":00:00");
		fcEvent.setUrl("http://localhost:8080/finalProjectCIM/maintenance/checkAppointment?id="+app.getAppointmentId());
		fcEvent.setColor("blue");
		fcEvent.setTextColor("white");
		ObjectMapper eventMapper = new ObjectMapper();
		try {
			// Convert object to JSON string
			 String jsonInString = eventMapper.writeValueAsString(fcEvent);
			// System.out.println(jsonInString);

			// Convert object to JSON string and pretty print
		//String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(event);

			// System.out.println(jsonInString);
		app.setJsonAppointmentEvent(jsonInString);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		coservice.createOrUpdateAppointment(app);
		
		sch.addToAppointmentList(app.getAppointmentId());
		coservice.createOrUpdateSchedule(sch);
		
		calendarController.addAppointmentAsEvent(mc.getCalendarId(),event);
//		calendarController.addAppointmentEvent(mc.getCalendarId(), startDate, endDate, summary,
//				link,mc.getMaintenanceCompanyAddress());
		model.addAttribute("user",uinfo);
		return "/secure/cowner/home" ;
	}
	

}
