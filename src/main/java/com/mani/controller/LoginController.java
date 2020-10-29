package com.mani.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.constants.AppConstants;
import com.mani.service.IUserService;

@Controller
public class LoginController {
	@Autowired
	private IUserService service;
	
	@GetMapping("/index")
	public String loadForm()
	{
		return AppConstants.VIEW_INDEX_PAGE;
	}
	@PostMapping("/login")
	public String handleSignInBtn(HttpServletRequest request,Model model)
	{
		String viewName="";
		String email = request.getParameter("emailId");
		String password = request.getParameter("password");
		String status = service.validateEmailIdAndPwd(email, password);
		if(status.equals(AppConstants.LOCKED))
		{
		model.addAttribute(AppConstants.ERRORMSG,"Your Account is locked" );	
		viewName=AppConstants.VIEW_INDEX_PAGE;
		}
		else if(status.equals(AppConstants.INVALID))
		{
			model.addAttribute(AppConstants.ERRORMSG1,"Invalid Credentials");
			viewName=AppConstants.VIEW_INDEX_PAGE;
		}
		else
		{
			model.addAttribute(AppConstants.SUCCESSMSG,"Your Account is successfully logged in");	
			viewName=AppConstants.VIEW_WELCOME_PAGE;
		}
		return viewName;
	}
	
	
	
}
