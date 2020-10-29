package com.mani.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mani.entity.StateEntity;

public interface StateRepository extends JpaRepository<StateEntity, Serializable> {

	//select * from StateEntity where countryId=:countryId;
	public List<StateEntity> findByCountryId(Integer countryId);
}
