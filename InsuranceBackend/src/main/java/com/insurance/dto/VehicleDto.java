package com.insurance.dto;

import java.util.Map;

public class VehicleDto {

	String regNo;
	String firstPart;
	String secondPart;
	String viewState;
	String checkStatusButton;
	Map<String, String> cookies;
	
	
	
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getFirstPart() {
		return firstPart;
	}
	public void setFirstPart(String firstPart) {
		this.firstPart = firstPart;
	}
	public String getSecondPart() {
		return secondPart;
	}
	public void setSecondPart(String secondPart) {
		this.secondPart = secondPart;
	}
	public String getViewState() {
		return viewState;
	}
	public void setViewState(String viewState) {
		this.viewState = viewState;
	}
	public String getCheckStatusButton() {
		return checkStatusButton;
	}
	public void setCheckStatusButton(String checkStatusButton) {
		this.checkStatusButton = checkStatusButton;
	}
	public Map<String, String> getCookies() {
		return cookies;
	}
	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}
	
	
}
