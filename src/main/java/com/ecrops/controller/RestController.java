
package com.ecrops.controller;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecrops.dto.AdvisoriesSent;
import com.ecrops.dto.CropPojo;
import com.ecrops.dto.DistrictsPojo;
import com.ecrops.dto.FarmerAlertsTop;
import com.ecrops.dto.MarketPicePojo;
import com.ecrops.projection.EmployeeName;
import com.ecrops.projection.WbVillageMastProjection;
import com.ecrops.repo.CrEmployeeProfileRepository;
import com.ecrops.repo.DatabaseRepo;
import com.ecrops.repo.WbvillagesRepository;
import com.ecrops.service.AllocationService;
import com.ecrops.service.SelectionOfSurveyNoSurvice;
import com.ecrops.validations.AllocationValidations;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	

	@Autowired
	private AllocationService employeeService;
	

	@Autowired
	private WbvillagesRepository wbVillageRepository;
	
	@Autowired
	private CrEmployeeProfileRepository empRepo;
	
	@Autowired
	private SelectionOfSurveyNoSurvice   selectionService;
	
	@Autowired
	private DatabaseRepo repo;

	@GetMapping("/getRevenueVillage")
	public List<WbVillageMastProjection> getRevenueVillage(@RequestParam("rbkCode") Integer rbkCode) {
		System.out.println("rbkcode--->"+rbkCode);
		List<WbVillageMastProjection> entities = new ArrayList<>();
		try {
			entities = wbVillageRepository.getWebLandDet(rbkCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entities;

	
	}
	
	
	@GetMapping("/getEmployeeByRevenue")
	public List<EmployeeName> getEmployeeByRevenue(HttpSession httpSession, @RequestParam("rbkCode") Integer rbkCode) {
        String district = (String) httpSession.getAttribute("dcode");
          System.out.println("district"+district);
		String mandal = (String) httpSession.getAttribute("mcode");
		  System.out.println("mandal"+mandal);
		List<WbVillageMastProjection> webLandDetails = employeeService.getWebLandDetails(Integer.parseInt(district),
				Integer.parseInt(mandal));
		String wbdcode2 = webLandDetails.get(0).getWbdcode();
		String wbmcode2 = webLandDetails.get(0).getWbmcode();
		System.out.println("wbdcode2"+wbdcode2);
		System.out.println("wbmcode2"+wbmcode2);
		System.out.println("rbkcode"+rbkCode);
		List<EmployeeName> entities = new ArrayList<>();
		try {
			

			entities = empRepo.getEmpByRevenue(rbkCode, Integer.parseInt(wbdcode2), Integer.parseInt(wbmcode2));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entities;
 }


	

	

	@CrossOrigin(origins = "*")
	@GetMapping("/getMarketPrice")
	public List<MarketPicePojo> getList(@RequestParam String amc,@RequestParam String commodity,@RequestParam String fromDate,@RequestParam String toDate) {
	
		//List<MarketPicePojo> l= repo.listMarketPrice(amc, commodity, transDate, dates);
		
		return repo.listMarketPrice(amc, commodity, fromDate, toDate);
		
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/geFarmerAlerts")
	public List<FarmerAlertsTop> getFarmerAlerts() {
     
		return repo.getFarmerAlerts();
 }
	
	
	

	@CrossOrigin(origins = "*")
	@GetMapping("/getDistricts")
	public List<DistrictsPojo> getDistricts() {
     
		return repo.getDistrictsList();
 }

	@CrossOrigin(origins = "*")
	@GetMapping("/getCropList")
	public List<CropPojo> getCropList() {
     
		return repo.getCropList();
 }
	

	@CrossOrigin(origins = "*")
	@GetMapping("/getSmsSent")
	public List<AdvisoriesSent> getSmsSent(@RequestParam int district,@RequestParam int crop,@RequestParam String fromDate,@RequestParam String toDate) {
		List<AdvisoriesSent> adviroriesSent=null;
	    AllocationValidations allocValidation =new AllocationValidations();
	    if(!allocValidation.isDigitPattern(String.valueOf(district)) || !allocValidation.isDigitPattern(String.valueOf(crop)) || !allocValidation.isValidDate(fromDate) 
	    	||  !allocValidation.isValidDate(toDate)	) {
	   return adviroriesSent;
	    }
		return repo.getAdvisoriesDateWise(district, crop, fromDate, toDate);
 }

	
	
	

	@CrossOrigin(origins = "*")
	@GetMapping("/geNewsAlert")
	public   List<FarmerAlertsTop>  getFarmerAlert(){
		 return repo.getnewsAlertt();
	
 }

}
