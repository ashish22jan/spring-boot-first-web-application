package com.in28minutes.springboot.web.springbootfirstwebapplication.contoller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.in28minutes.springboot.web.model.Todo;
//import com.in28minutes.springboot.web.service.LoginService;
import com.in28minutes.springboot.web.service.TodoService;

@Controller
public class TodoController {

	// Injected Automatically
	@Autowired
	TodoService service;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date format is: dd/MM?YYYY
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showToDosList(ModelMap model) {
		String Username = getLoggedInUsername(model);
		model.put("todos", service.retrieveTodos(Username));
		// model.put("Password" , password);
		return "list-todos";
	}

	private String getLoggedInUsername(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddToDoPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, getLoggedInUsername(model), "Default desc", new Date(), false));
		return "todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String updateToDoPage(@RequestParam int id, ModelMap model) {
		Todo todo = service.retrieveTodos(id);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateToDo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		todo.setUser(getLoggedInUsername(model));
		service.updateTodo(todo);

		return "redirect:/list-todos";
	}
	
	

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteToDo(@RequestParam int id) {
		
		if(id==1)
			throw new RuntimeException("Something went wrong");
		
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addToDo(ModelMap model, @Valid Todo todo, BindingResult result) // @RequestParam String desc
	{
		if (result.hasErrors()) {
			return "todo";
		}
		service.addTodo(getLoggedInUsername(model), todo.getDesc(), todo.getTargetDate(), false);
		// model.put("todos" , service.retrieveTodos(Username));
		return "redirect:/list-todos";
	}

//	@RequestMapping("/res")
//	@ResponseBody
//	public int val()
//	{
//		return 2;
//	}
}
