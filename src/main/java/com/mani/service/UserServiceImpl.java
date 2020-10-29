package com.mani.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mani.dao.CityRepository;
import com.mani.dao.CountryRepository;
import com.mani.dao.StateRepository;
import com.mani.dao.UserRepository;
import com.mani.entity.CitiesEntity;
import com.mani.entity.CountryEntity;
import com.mani.entity.StateEntity;
import com.mani.entity.UserEntity;
import com.mani.model.UnlockAccount;
import com.mani.model.User;
import com.mani.util.EmailUtil;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CityRepository citiesRepo;
	@Autowired
	private EmailUtil utils;
	@Override
	public boolean isEmailUnique(String emailId) {
		UserEntity userEntity = userRepo.findByemailId(emailId);
		return userEntity.getEmailId()!=null?false:true;
	}

	@Override
	public Map<Integer, String> loadCountries() {
		Map<Integer,String> countryMap=new HashMap<>();
		List<CountryEntity> list = countryRepo.findAll();
		list.forEach(action->
		{
			countryMap.put(action.getCountryId(), action.getCountryName());
		});
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStatesByCountryId(Integer countryId) {
		Map<Integer,String> stateMap=new HashMap<>();
		List<StateEntity> stateList = stateRepo.findByCountryId(countryId);
		stateList.forEach(action->
		{
			stateMap.put(action.getStateId(), action.getStateName());
		});
		return stateMap;
	}

	@Override
	public Map<Integer, String> loadCitiesByStateId(Integer stateId) {
		Map<Integer,String> citiesMap=new HashMap<>();
		List<CitiesEntity> citiesList = citiesRepo.findByStateId(stateId);
		citiesList.forEach(action->
		{
			citiesMap.put(action.getCityId(), action.getCityName());
		});
		return citiesMap;
	}

	@Override
	public String generateTempPwd() {
		
	        // chose a Character random from this String 
	        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                    + "0123456789"
	                                    + "abcdefghijklmnopqrstuvxyz"; 
	  
	        // create StringBuffer size of AlphaNumericString 
	        StringBuilder sb = new StringBuilder(6); 
	  
	        for (int i = 0; i < 6; i++) { 
	  
	            // generate a random number between 
	            // 0 to AlphaNumericString variable length 
	            int index 
	                = (int)(AlphaNumericString.length() 
	                        * Math.random()); 
	  
	            // add Character one by one in end of sb 
	            sb.append(AlphaNumericString 
	                          .charAt(index)); 
	        } 
	  
	        return sb.toString(); 	
	}

	@Override
	public boolean saveUserAccount(User user) {
		user.setAccountStatus("Locked");
		user.setPassword(generateTempPwd());
		UserEntity entity=new UserEntity();
		BeanUtils.copyProperties(user, entity);
		UserEntity savedEntity = userRepo.save(entity);
		return savedEntity.getUserId()!=null;
	}
	
	@Override
	public String getRegSuccMailBody(User user) {
		String fileName = "UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt";
		List<String> replacedLines = null;
		String mailBody = null;
		try {
			Path path = Paths.get(fileName, "");
			@SuppressWarnings("resource")
			Stream<String> lines = Files.lines(path);

			replacedLines = lines.map(line -> line.replace("{FNAME}", user.getFirstName())
								 .replace("{LNAME}", user.getLastName())
								 .replace("{TEMP-PWD}", user.getPassword())
								 .replace("{EMAIL}", user.getEmailId()))
								 .collect(Collectors.toList());

			mailBody = String.join("", replacedLines);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}
	
@Override
public boolean checkTempPwd(UnlockAccount unlockAcc) {
	 UserEntity userEntity = userRepo.findByemailIdAndPassword(unlockAcc.getEmailId(),unlockAcc.getTempPwd());
	 if(userEntity!=null)
	 {
		 userEntity.setAccountStatus("UNLOCKED");
		 userEntity.setPassword(unlockAcc.getCnfrmPwd());
		 return userRepo.save(userEntity)!=null;
	 }
	 else
		 return false;
}

@Override
public String validateEmailIdAndPwd(String emailId, String password) {
	UserEntity entity = userRepo.findByemailIdAndPassword(emailId,password);
	String message="";
	if(entity==null)
		message="Invalid Credentails";
	else if(entity.getAccountStatus().equals("Locked"))
		message="Locked";
	else
		message="Valid Credentails";
return message;
}	
@Override
public String sendForgotPwd(String emailId) {
	UserEntity user = userRepo.findByemailId(emailId);
	String message="";
	if(user!=null)
	{
		utils.sendEmail(user.getEmailId(), "Forgot Password", forgotPwdmailBody(user));
		message="Valid";	
	}
	else
		message="Invalid";
	return message;
}	

public String forgotPwdmailBody(UserEntity user)
{
	String fileName = "FORGOT-PASSWORD-EMAIL-BODY-TEMPLATE.txt";
	List<String> replacedLines = null;
	String mailBody = null;
	try {
		Path path = Paths.get(fileName, "");
		@SuppressWarnings("resource")
		Stream<String> lines = Files.lines(path);

		replacedLines = lines.map(line -> line.replace("{FNAME}", user.getFirstName())
							 .replace("{LNAME}", user.getLastName())
							 .replace("{PWD}", user.getPassword()))
							 .collect(Collectors.toList());
		mailBody = String.join("", replacedLines);

	} catch (Exception e) {
		e.printStackTrace();
	}
	return mailBody;
}
}
