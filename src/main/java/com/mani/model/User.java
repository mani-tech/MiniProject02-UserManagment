package com.mani.model;

import lombok.Data;
@Data
public class User {
	private Integer userId;
	private String firstName;
	private String lastName;
	private String emailId;
	private long phoneNumber;
	private String dob;
	private String gender;
	private String country;
	private String state;
	private String city;
	private String accountStatus;
	private String password;
}
