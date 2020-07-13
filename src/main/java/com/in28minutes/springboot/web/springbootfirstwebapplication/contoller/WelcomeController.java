package com.in28minutes.springboot.web.springbootfirstwebapplication.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;



@Controller  
public class WelcomeController {
	
	//Injected Automatically  
//	@Autowired
	//LoginService service; 
	
	
	@RequestMapping(value = "/" , method = RequestMethod.GET)	
	public String showWelcomePage(ModelMap model)
	{
		model.put("Username", getLoggedInUsername());
		return "welcome";
	}
	
	private String getLoggedInUsername()
	{
		
		Object principal = 
				SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails)
			return ((UserDetails)principal).getUsername();
		return principal.toString();
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
