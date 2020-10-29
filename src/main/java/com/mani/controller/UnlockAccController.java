package com.mani.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mani.model.UnlockAccount;
import com.mani.service.IUserService;
@Controller
public class UnlockAccController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/unlockAcc")
	public String loadUnlockAccPage(@RequestParam("email") String emailId,Model model)
	{
		UnlockAccount unlockAcc=new UnlockAccount();
		unlockAcc.setEmailId(emailId);
		
		model.addAttribute("unlockAcc", unlockAcc);
		
		return "unlockAcc";
	}
	
	@PostMapping("/checkPwd")
	public String checkTempPwd(@ModelAttribute UnlockAccount unlock,Model model)
	{
	 boolean isStatusUpdated = userService.checkTempPwd(unlock);
	 if(isStatusUpdated)
	 {
		 model.addAttribute("successMsg", "Account unlocked ,please proceed with login.<a href=\"index\">Login Here</a>");
	 }
	 else
		 model.addAttribute("errorMsg", "Entered Temporary password is incorrect");
		model.addAttribute("unlockAcc", unlock);
		return "unlockAcc";
	
	}

}
