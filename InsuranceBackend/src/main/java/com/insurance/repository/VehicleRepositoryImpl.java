package com.insurance.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import com.insurance.dto.VehicleDto;
import com.insurance.entities.Vehicle;
import com.insurance.model.VehicleModel;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {
	@PersistenceContext
	EntityManager em;

	@Transactional
	public long addOrUpdateVehicle(Vehicle vehicle) {
		Vehicle vehicleData = em.merge(vehicle);
		return vehicleData.getVehicleId();
	}

	@Transactional
	public List<Vehicle> viewAllvehicles() {

		return null;
	}

	@Transactional
	public Vehicle findVehicleById(String registrationNumber) {

		return null;
	}

	@Override
	public Map<String, String> scrapeData(VehicleDto vd) throws IOException {
		
		String dataPath = "https://parivahan.gov.in/rcdlstatus/vahan/rcDlHome.xhtml";
		String homePath = "https://parivahan.gov.in/rcdlstatus/";
		
		Map<String, String> result =new HashMap<String, String>();
		
		String scrapedData;
		scrapedData = Jsoup.connect(dataPath).cookies(vd.getCookies()).method(Method.POST).referrer(homePath).userAgent(
				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36")
				.data("javax.faces.partial.ajax", "true").data("javax.faces.source", vd.getCheckStatusButton())
				.data("javax.faces.partial.execute", "@all")
				.data("javax.faces.partial.render", "form_rcdl:pnl_show form_rcdl:pg_show form_rcdl:rcdl_pnl")
				.data(vd.getCheckStatusButton(), vd.getCheckStatusButton()).data("form_rcdl", "form_rcdl")
				.data("form_rcdl:tf_reg_no1", vd.getFirstPart()).data("form_rcdl:tf_reg_no2", vd.getSecondPart())
				.data("javax.faces.ViewState", vd.getViewState()).execute().body();

		if (scrapedData.contains("Registration No. does not exist!!! Please check the number.")) {
			result.put("Warning", "Registration No. does not exist!!! Please check the number.");
		} else {
			
			String htmlString = scrapedData.substring(scrapedData.indexOf("<table"),
					scrapedData.lastIndexOf("</table>"));
			Document parse2 = Jsoup.parse(htmlString);

			Element first2 = parse2.select("table").first();
			if (first2 != null) {
				Elements select = first2.select("tr");
				for (Element element : select) {
					Elements select2 = element.select("td");
					if (select2.size() == 2) {
						String key = select2.get(0).text().replace(":", "");
						result.put(key, select2.get(1).text());
					} else if (select2.size() == 4) {
						String key = select2.get(0).text().replace(":", "");
						result.put(key, select2.get(1).text());

						key = select2.get(2).text().replace(":", "");
						result.put(key, select2.get(3).text());
					}
				}
			} else {
				result.put("Warning", "No Record(s) Found");
			}

		}
		return result;

	}

}
