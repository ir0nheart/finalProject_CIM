package cim.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cim.entity.UserInfo;
import cim.service.RegisterService;


@Controller
@RequestMapping("/Register")
public class RegisterController {
	@Autowired
	private  RegisterService registerService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String goRegisterForm(ModelMap model) {
		
		cim.model.UserInfo userinfo = new cim.model.UserInfo();
		model.addAttribute("userinfo", userinfo);
		return "Register";
 	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doRegister(@ModelAttribute("userinfo") @Valid cim.model.UserInfo user,BindingResult result,ModelMap model) {
		if(user.getRole()== null || user.getRole().equals("NONE")) {
			model.put("message","Select a Role");
			return "Register";
		
			
		}else if(result.hasErrors()) {
			
			return "Register";
			
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		UserInfo uinf = new UserInfo(user.getUsername(), encoder.encode(user.getPassword()), user.getEmail(), user.getRole());
				
		if(registerService.register(uinf))	
		return ("redirect:/Login");
		model.put("messageUserName","Username in use. Please select another Username");
		return "Register";
 	}
	
	@ModelAttribute("userTypeList")
	public List<String> initializeUserType(){
		List<String> userType = new ArrayList<String>();
		userType.add("Car Owner");
		userType.add("Insurance Company");
		userType.add("Maintenance Company");
		return userType;
	}
}

