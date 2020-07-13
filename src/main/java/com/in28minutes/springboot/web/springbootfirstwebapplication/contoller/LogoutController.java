package com.in28minutes.springboot.web.springbootfirstwebapplication.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;



@Controller  
public class LogoutController {
	
	//Injected Automatically  
	@Autowired
	//LoginService service; 
	
	
	@RequestMapping(value = "/logout" , method = RequestMethod.GET)	
	public String logout(HttpServletRequest request, HttpServletResponse response)
	{
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null)
		{
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/";
	}
	
	
	
	
	/*
	 * @RequestMapping(value = "/login" , method = RequestMethod.POST) public String
	 * showWelcomePage(ModelMap model , @RequestParam String Username
	 * , @RequestParam String Password) { boolean isValidUser =
	 * service.validateUser(Username, Password);
	 * 
	 * if(!isValidUser) { model.put("errormessage","Invalid Credentials"); return
	 * "login"; }
	 * 
	 * model.put("Username" , Username); model.put("Password" , Password); return
	 * "welcome"; }
	 */
//	@RequestMapping("/res")
//	@ResponseBody
//	public int val()
//	{
//		return 2;
//	}
}
