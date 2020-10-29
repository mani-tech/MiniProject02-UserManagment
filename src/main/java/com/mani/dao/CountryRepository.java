package com.mani.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mani.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Serializable> {

}
