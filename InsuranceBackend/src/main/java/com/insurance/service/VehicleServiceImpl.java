package com.insurance.service;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.VehicleDto;
import com.insurance.entities.Vehicle;
import com.insurance.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	VehicleRepository vehicleRepository;

	@Override
	public ApiResponse addOrUpdateVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse viewAllvehicles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse findVehicleById(String registrationNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse scrapeVehicleInfo(String registrationNumber) throws IOException {

		VehicleDto vehicleDto = new VehicleDto();
		String firstPart = registrationNumber.substring(0, registrationNumber.length() - 4);
		String secondPart = registrationNumber.substring(registrationNumber.length() - 4);

		String homePath = "https://parivahan.gov.in/rcdlstatus/";
		// String dataPath = "https://parivahan.gov.in/rcdlstatus/vahan/rcDlHome.xhtml";

		Connection.Response response = Jsoup.connect(homePath).ignoreHttpErrors(true).method(Method.GET).execute();

		Map<String, String> cookies = null;
		int statusCode = response.statusCode();

		if (statusCode == 200) {
			cookies = response.cookies();
		} else {
			return new ApiResponse(500, "Server error", null);
		}
		Document document = Jsoup.parse(response.body());

		Element viewElement = document.getElementById("j_id1:javax.faces.ViewState:0");
		String viewState = viewElement.attr("value");

		Element checkButtonElement = document.getElementById("form_rcdl:j_idt44");
		String checkStatusButton = checkButtonElement.attr("id");

		vehicleDto.setFirstPart(firstPart);
		vehicleDto.setSecondPart(secondPart);
		vehicleDto.setRegNo(registrationNumber);
		vehicleDto.setCookies(cookies);
		vehicleDto.setViewState(viewState);
		vehicleDto.setCheckStatusButton(checkStatusButton);

		Map<String, String> finalData = vehicleRepository.scrapeData(vehicleDto);
		if (!finalData.containsKey("Warning")) {
			return new ApiResponse(200, "Successfully scraped the data", finalData);
		} else {
			return new ApiResponse(500, "Server error", null);
		}
	}

}
