package com.mani.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Cities_Master")
public class CitiesEntity {
	@Id
	@Column(name="City_Id")
	private Integer cityId;
	@Column(name="State_Id")
	private Integer stateId;
	@Column(name="City_Name")
	private String cityName;
}
