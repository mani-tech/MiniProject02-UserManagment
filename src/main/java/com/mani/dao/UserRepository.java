package com.mani.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mani.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Serializable> {

	public UserEntity findByemailId(String emailId);
	
	public UserEntity findByemailIdAndPassword(String emailId,String password);
	
}
