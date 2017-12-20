package cim.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cim.entity.UserInfo;
import cim.service.IUserService;

@Controller
public class HomeController {
	@Autowired
	private  IUserService service;
	
	@RequestMapping("/")
	public String goHome(ModelMap model,HttpServletRequest req) {
		if (req.isUserInRole("ROLE_ADMIN")) {
				UserInfo uinf = service.getDataByUserName(req.getUserPrincipal().getName());
				model.addAttribute("user",uinf);
			 return "redirect:/admin/home";
		}else
		if (req.isUserInRole("ROLE_CAROWNER")) {
			UserInfo uinf = service.getDataByUserName(req.getUserPrincipal().getName());
			model.addAttribute("user",uinf);
			return "redirect:/cowner/home";
		}else
		if (req.isUserInRole("ROLE_INSURANCE")) {
			UserInfo uinf = service.getDataByUserName(req.getUserPrincipal().getName());
			model.addAttribute("user",uinf);return "redirect:/insurance/home";
		}else
		if (req.isUserInRole("ROLE_MAINTENANCE")) {
			UserInfo uinf = service.getDataByUserName(req.getUserPrincipal().getName());
		model.addAttribute("user",uinf);
		return "redirect:/maintenance/home";}else {
 		return "home";}
 	}
}
