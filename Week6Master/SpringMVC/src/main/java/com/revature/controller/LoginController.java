package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.beans.User;
import com.revature.service.UserService;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@Autowired
	private UserService us;

	@RequestMapping(method=RequestMethod.GET)
	public String goLogin(HttpSession session) {
		User u = (User) session.getAttribute("user");
		if(u != null)
			return "redirect: home";
		return "static/login.html";
	}
	
	@PostMapping
	public String login(String username, String password, HttpSession session) {
		System.out.println(username+"/"+password);
		User u = us.login(username, password);
		if(u == null)
			return "redirect:login";
		session.setAttribute("user", u);
		return "redirect:home";
	}
}
