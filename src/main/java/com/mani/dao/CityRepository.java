package com.mani.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mani.entity.CitiesEntity;

public interface CityRepository extends JpaRepository<CitiesEntity, Serializable> {

	public List<CitiesEntity> findByStateId(Integer stateId);
}
