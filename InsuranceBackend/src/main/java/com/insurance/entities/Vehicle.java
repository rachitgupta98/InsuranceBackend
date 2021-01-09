package com.insurance.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_vehicleData")
public class Vehicle {

	@Id
	@SequenceGenerator(name = "seq_insr_vehicle", initialValue = 1000000, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_insr_vehicle")
	long vehicleId;

	String ownerName;
	String registrationNumber;
	Date registrationDate;
	String vehicleNameModel;
	String chasisNumber;
	String EngineNumber;
	String Fuel_type;
	String vehicleType;

	@OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
	Policy policy;

	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getVehicleNameModel() {
		return vehicleNameModel;
	}

	public void setVehicleNameModel(String vehicleNameModel) {
		this.vehicleNameModel = vehicleNameModel;
	}

	public String getChasisNumber() {
		return chasisNumber;
	}

	public void setChasisNumber(String chasisNumber) {
		this.chasisNumber = chasisNumber;
	}

	public String getEngineNumber() {
		return EngineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		EngineNumber = engineNumber;
	}

	public String getFuel_type() {
		return Fuel_type;
	}

	public void setFuel_type(String fuel_type) {
		Fuel_type = fuel_type;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

}
