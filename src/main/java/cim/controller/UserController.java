package cim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cim.entity.UserInfo;
import cim.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private  IUserService service;
	@RequestMapping(value="/home")
	public String home(ModelMap model, Authentication authentication) {
		UserInfo uinfo = service.getDataByUserName(authentication.getName());
		model.addAttribute("user", uinfo);
		switch( uinfo.getRole()){
		case "ROLE_CAROWNER":return "redirect:/cowner/home";
		case "ROLE_ADMIN":return "redirect:/admin/home";
		case "ROLE_INSURANCE":return "redirect:/insurance/home";
		case "ROLE_MAINTENANCE":return "redirect:/maintenance/home";
		}
		return "access-denied";
		
 	}
	@RequestMapping(value="/error")
	public String error() {
 		return "access-denied";
 	}

}
