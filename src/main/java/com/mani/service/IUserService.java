package com.mani.service;

import java.util.Map;

import com.mani.model.UnlockAccount;
import com.mani.model.User;

public interface IUserService {
public boolean isEmailUnique(String emailId);	
public Map<Integer,String> loadCountries();
public Map<Integer,String> loadStatesByCountryId(Integer countryId);
public Map<Integer,String> loadCitiesByStateId(Integer stateId);
public String generateTempPwd();
public boolean saveUserAccount(User user);
public String getRegSuccMailBody(User user);
public boolean checkTempPwd(UnlockAccount unlockAcc);
public String validateEmailIdAndPwd(String emailId,String password);
public String sendForgotPwd(String emailId);

}
