package cim.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cim.entity.Appointment;
import cim.entity.CarOwner;
import cim.entity.Maintenance;
import cim.entity.MaintenanceCompany;
import cim.entity.Message;
import cim.entity.Schedule;
import cim.entity.UserInfo;
import cim.entity.Vehicle;
import cim.model.MaintenanceCompanyM;
import cim.service.ICarOwnerService;
import cim.service.IMaintenanceCompanyService;
import cim.service.IMessageService;
import cim.service.IUserService;
@Controller
@RequestMapping("/maintenance")
public class MaintenanceCompanyController {
	@Autowired
	private  IUserService service;
	@Autowired
	private IMaintenanceCompanyService mcservice;
	@Autowired
	private ICarOwnerService coservice;
	
	@Autowired
	private IMessageService messageService;
	
	@RequestMapping(value= {"/","/home",""})
	
	public String home(ModelMap model, Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
			model.addAttribute("user", uinfo);
		return "/secure/maintenance/home";
		
 	}
	@RequestMapping(value="/info")
	public String showInfo(ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		MaintenanceCompany mc= mcservice.getDataByUserId(uinfo.getUserid());
		MaintenanceCompanyM mcM= new MaintenanceCompanyM();
		if(req.getParameter("errMessage")!=null)
			model.addAttribute("errMessage","Not Yet Activated");
		
		model.addAttribute("user",uinfo);
		model.addAttribute("maintenance",mc);
		model.addAttribute("maintenanceM",mcM);
		
		return "/secure/maintenance/user-info";
	}
	
	
	@RequestMapping(value="/updateMaintenanceCompany",method = RequestMethod.POST)
	public String doUpdate(@ModelAttribute("maintenanceM") @Valid MaintenanceCompanyM mcM,BindingResult result,ModelMap model,Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		
		MaintenanceCompany mc = mcservice.getDataByUserId(uinfo.getUserid());
		if(result.hasErrors()) {
			model.addAttribute("user",uinfo);
			model.addAttribute("maintenance",mc);
			return "/secure/maintenance/user-info";
		}
		mc.setMaintenanceCompanyStreetNumber(mcM.getMaintenanceCompanyStreetNumber());
		mc.setMaintenanceCompanyRoute(mcM.getMaintenanceCompanyRoute());
		mc.setMaintenanceCompanyZipCode(mcM.getMaintenanceCompanyZipCode());
		mc.setMaintenanceCompanyCountry(mcM.getMaintenanceCompanyCountry());
		mc.setMaintenanceCompanyCity(mcM.getMaintenanceCompanyCity());
		mc.setMaintenanceCompanyContact(mcM.getMaintenanceCompanyContact());
		mc.setMaintenanceCompanyName(mcM.getMaintenanceCompanyName());
		mc.setMaintenanceCompanyState(mcM.getMaintenanceCompanyState());
		mc.setRequestActivate(true);
		mc.setMaintenanceCompanyAddress();
		
			mcservice.updateMaintenanceCompany(mc);
			model.addAttribute("user",uinfo);
			model.addAttribute("maintenance",mc);
			
			return "/secure/maintenance/user-info";
	}
	
	@RequestMapping(value="/updateLogoPicture",method=RequestMethod.POST)
	public String updateLogoPicture(@RequestParam("pic")MultipartFile multifile, ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		MaintenanceCompany mc = mcservice.getDataByUserId(uinfo.getUserid());
		model.addAttribute("user",uinfo);
		model.addAttribute("maintenance",mc);
		if(!multifile.isEmpty()) {
		mc.setPhoto(multifile);
		mcservice.updateMaintenanceCompany(mc);
		}
		return "secure/cowner/user-info";
	}
	@RequestMapping(value="/showLogo",method=RequestMethod.GET)
	public @ResponseBody String viewPhoto(Authentication authentication,HttpServletResponse res) throws IOException {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		MaintenanceCompany mc = mcservice.getDataByUserId(uinfo.getUserid());
		byte[] photoBytes = mc.getPhotoBytes();
		if(photoBytes!=null) {
			int photoLength=photoBytes.length;
			try(ServletOutputStream sos = res.getOutputStream()){
			
			res.setContentType(mc.getPhotoContentType());
			res.setContentLength(photoLength);
			res.setHeader("Content-Disposition", "inline; filename=\""+mc.getPhotoFileName()+"\"");
			sos.write(photoBytes);
			sos.flush();
			}
		
		}
		return "";
	}
	
	@RequestMapping(value="/messages")
	public String showMessages(ModelMap model,Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		model.addAttribute("user",uinfo);
		if(!uinfo.isActive()) {
			return "redirect:/maintenance/info?errMessage";
			}
		Map<UserInfo,String> messageUserMap = messageService.getMessageUser();
		messageUserMap = getMessageUserMap(messageUserMap,uinfo.getUserid());
		model.addAttribute("messageMap",messageUserMap);
		List<Message> userMessages=messageService.getMessagesForUser(uinfo);
		model.addAttribute("messageList",userMessages);
		return "/secure/maintenance/messages";
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
		
		return "/secure/maintenance/messages";
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
		return "/secure/maintenance/readMessage";
			}
		}
		Map<UserInfo,String> messageUserMap = messageService.getMessageUser();
		messageUserMap = getMessageUserMap(messageUserMap,uinfo.getUserid());
		model.addAttribute("messageMap",messageUserMap);
		List<Message> userMessages=messageService.getMessagesForUser(uinfo);
		model.addAttribute("messageList",userMessages);
		
		return "/secure/maintenance/messages";
		
		
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
		return "/secure/maintenance/messages";
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
	
	
	@RequestMapping(value="/show")
	public String show(ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		if(!uinfo.isActive()) {
			return "redirect:/maintenance/info?errMessage";
			}
		MaintenanceCompany mc=mcservice.getDataByUserId(uinfo.getUserid());
		model.addAttribute("user",uinfo);
		model.addAttribute("mc", mc);
		if(!req.getParameterMap().isEmpty() && req.getParameterMap().size()==1) {
			if(req.getParameter("appointment") != null) {
				// get Appointments for this company
				model.put("showType", "appointment");
				String type = req.getParameter("appointment");
				if(type!=null && type.equals("google")) {
					model.put("viewType", "google");}else {
					model.put("viewType", "db");
				}
				model.addAttribute("appointmentList", "appointmentList");
				}
				else if(req.getParameter("cars") != null){
				// Get List Of Cars Under Maintenance
				model.put("showType", "cars");	
				List<Maintenance> maintenance = mcservice.findMaintenanceCars(mc.getMaintenanceCompanyId());
				
				model.addAttribute("maintenanceList", maintenance);
				}
			return "/secure/maintenance/show";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/updateStatus")
	public String updateStatus(ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		MaintenanceCompany mc=mcservice.getDataByUserId(uinfo.getUserid());
		model.addAttribute("user",uinfo);
		model.addAttribute("mc", mc);
		String status=req.getParameter("status");
		Long maintenanceId=Long.valueOf(req.getParameter("maintenanceId"));
		Maintenance maintenanceI = mcservice.getMaintenanceById(maintenanceId);
		maintenanceI.setMaintenanceStatus(status);
		mcservice.updateMaintenanceStatus(maintenanceI);
		model.put("showType", "cars");	
		List<Maintenance> maintenance = mcservice.findMaintenanceCars(mc.getMaintenanceCompanyId());
		model.addAttribute("maintenanceList", maintenance);
		
	return "secure/maintenance/show";
	}
	@RequestMapping(value="/checkAppointment")
	public String checkAppointment(ModelMap model,Authentication authentication,HttpServletRequest req) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		model.addAttribute("user",uinfo);
		MaintenanceCompany mc=mcservice.getDataByUserId(uinfo.getUserid());
		Long appointmentId = Long.valueOf(req.getParameter("id"));
		Appointment appointment = null;
		if(appointmentId!=null && appointmentId!=0) {
			appointment=mcservice.getAppointmentById(appointmentId);
			CarOwner co = coservice.getDataByOwnerId(appointment.getAppointmentFromId());
			Vehicle veh = coservice.getVehicleById(appointment.getAppointmentForId());
			model.addAttribute("carOwner",co);
			model.addAttribute("vehicle",veh);
			model.addAttribute("maintenance",mc);
			model.addAttribute("appointment",appointment);
			return "/secure/maintenance/checkAppointment";
		}				
		return "redirect:/";
	}
	
	@RequestMapping(value="/addMaintenance")
	public String addMaintenance(ModelMap model,Authentication authentication,HttpServletRequest req) {
		Long appId = Long.valueOf(req.getParameter("appId"));
	
		Long maintenanceId = Long.valueOf(req.getParameter("maintenanceId"));
		MaintenanceCompany mc=mcservice.getDataByCompanyId(maintenanceId);
		Long vehicleId = Long.valueOf(req.getParameter("vehicleId"));
		Long carOwnerId = Long.valueOf(req.getParameter("carOwnerId"));
		if( appId!=null && maintenanceId !=null && carOwnerId !=null && vehicleId!=null) {
			Maintenance maintenance = new Maintenance();
			Appointment appointment = mcservice.getAppointmentById(appId);
			CarOwner co = coservice.getDataByOwnerId(carOwnerId);
			Vehicle veh = coservice.getVehicleById(vehicleId);	
			maintenance.setAppointment(appointment);
			maintenance.setCarOwner(co);
			maintenance.setMaintenanceCompany(mc);
			maintenance.setVehicle(veh);
			maintenance.setMaintenanceStatus("Under Maintenance");
			mcservice.createMaintenance(maintenance);
		}
		
		return "redirect:/";
		
	}
	
	@RequestMapping(value="/getCalendar",method = RequestMethod.GET)
	public @ResponseBody String getCalendar(Authentication auth,HttpServletResponse res) throws IOException {
			UserInfo uinf = service.getDataByUserName(auth.getName());
			MaintenanceCompany mc = mcservice.getDataByUserId(uinf.getUserid());
			List<Schedule> companySchedule = mcservice.getCompanySchedule(mc.getMaintenanceCompanyId());
			List<Appointment> globalList = new ArrayList<Appointment>();
			Iterator<Schedule> iter = companySchedule.iterator();
			while(iter.hasNext()) {
				Schedule sch = iter.next();
				ArrayList<Long> tempApps = sch.getAppointmentList();
				for(Long lo:tempApps) {
					Appointment tempApp = mcservice.getAppointmentById(lo);
					globalList.add(tempApp);
				}
				
			}
			//List<String> EventData = new ArrayList<String>();
			//StringBuffer EventDatas =new StringBuffer("[");
			List<String> EventData = new ArrayList<String>();
			for(Appointment app:globalList) {
				EventData.add(app.getJsonAppointmentEvent());
				
			}
			 Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			 String jsonString = gson.toJson(EventData);
			
			
				
					
//				res.setContentType("application/json");
//				res.setContentLength(jsonString.length());
//				ServletOutputStream sos = res.getOutputStream();
//				sos.write(jsonString.getBytes());
//				sos.flush();
//				sos.close();
//				
			
				
			
			
	return jsonString;
	}
}
