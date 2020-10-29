package com.mani.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.constants.AppConstants;
import com.mani.model.User;
import com.mani.service.IUserService;
import com.mani.util.EmailUtil;

@Controller
public class RegistrationController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private EmailUtil utils;
	
	@ModelAttribute
	public void loadUserForm(Model model)
	{

		User user=new User();
		model.addAttribute("user", user);

		Map<Integer, String> countriesMap = userService.loadCountries();
		model.addAttribute("countries", countriesMap);
	}


	@GetMapping("/register")
	public String loadRegistration()
	{
		return "registration";
	}

	@GetMapping("/checkEmail")
	public @ResponseBody String isEmailUnique(@RequestParam("emailId") String emailId)
	{
		//boolean emailUnique = userService.isEmailUnique(emailId);
		//return emailUnique?"Unique":"Duplicate";
		return userService.isEmailUnique(emailId)?"Unique":"Duplicate";
	}
	
	@PostMapping("/saveUser")
	public String saveUserAccount(@ModelAttribute User user,RedirectAttributes attribute)
	{
		boolean savedUser = userService.saveUserAccount(user);

		if(savedUser)
		{
			String mailBody = userService.getRegSuccMailBody(user);
			utils.sendEmail(user.getEmailId(), "Unlock IES Account", mailBody);
			attribute.addFlashAttribute(AppConstants.SUCCESSMSG, "Registration is almost Done.Please check your email to unlock your Account");
		}
		else
		attribute.addFlashAttribute(AppConstants.ERRORMSG, "Registration is not success");
		return "redirect:register";
	}
	
	@GetMapping("/loadStates")
	public @ResponseBody Map<Integer,String> loadStatesByCountryId(@RequestParam("countryId")Integer countryId)
	{
		Map<Integer, String> statesByCountryId = userService.loadStatesByCountryId(countryId);
		return statesByCountryId;
	}
	
	@GetMapping("/loadCities")
	public @ResponseBody Map<Integer,String> loadCitiesByStateId(@RequestParam("stateId")Integer stateId)
	{
		Map<Integer, String> citiesByStateId = userService.loadCitiesByStateId(stateId);
		return citiesByStateId;
	}
	
	

}
