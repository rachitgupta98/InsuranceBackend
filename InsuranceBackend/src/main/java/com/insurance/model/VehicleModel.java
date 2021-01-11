package com.insurance.model;

public class VehicleModel {
	String stateOrUtCode;
	String districtCode;
	String rtoSeries;
	int uniqueNumber;

	public String getStateOrUtCode() {
		return stateOrUtCode;
	}

	public void setStateOrUtCode(String stateOrUtCode) {
		this.stateOrUtCode = stateOrUtCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getRtoSeries() {
		return rtoSeries;
	}

	public void setRtoSeries(String rtoSeries) {
		this.rtoSeries = rtoSeries;
	}

	public int getUniqueNumber() {
		return uniqueNumber;
	}

	public void setUniqueNumber(int uniqueNumber) {
		this.uniqueNumber = uniqueNumber;
	}

}
