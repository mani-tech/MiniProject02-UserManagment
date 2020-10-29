package com.mani.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="States_Master")
public class StateEntity {
	@Id
	@Column(name="State_Id")
	private Integer stateId;
	@Column(name="Country_Id")
	private Integer countryId;
	@Column(name="State_Name")
	private String stateName;
}
