package com.mani.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Country_Master")
public class CountryEntity {
	@Id
	@Column(name="Country_Id")
	private Integer countryId;
	@Column(name="Country_Code")
	private String countryCode;
	@Column(name="Country_Name")
	private String countryName;
}
