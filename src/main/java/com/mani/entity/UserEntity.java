package com.mani.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="User_Entity")
public class UserEntity {
	@Id
	@GeneratedValue
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
