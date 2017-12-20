package cim.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
public class LoginController {
	
	@Autowired
	private SecurityContextLogoutHandler securityContextLogoutHandler;

	@RequestMapping(value = "/Login", method=RequestMethod.GET)
	public String goLogin(ModelMap model,HttpServletRequest req) {
		if(req.getParameter("error")!= null)
		model.put("error","Invalid Username or Password");
		return "Login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	       // new SecurityContextLogoutHandler().logout(request, response, auth);
	    		securityContextLogoutHandler.logout(request, response, auth);
	    }
	    return "Login";
	    }
	
	
	@RequestMapping("/timeout")
	public String gotimeout(ModelMap model) {
 		return "timeout";
 	}

}
