package com.mani.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mani.service.IUserService;

@Controller
public class ForgotPwdController {
	@Autowired
	private IUserService service;
	
	@GetMapping("/forgotPwd")
	public String loadForgotPage()
	{
		return "forgotPwd";
	}
	
	@PostMapping("/pwd")
	public String handleSubmitButton(HttpServletRequest request,Model model)
	{
		String email = request.getParameter("emailId");
		String status = service.sendForgotPwd(email);
		if(status.equals("Valid"))
		{
			model.addAttribute("successMsg", "Password sent to email");
		}
		else
			model.addAttribute("errorMsg", "Entered mailId is not registered");

		return "forgotPwd";
	}
}
