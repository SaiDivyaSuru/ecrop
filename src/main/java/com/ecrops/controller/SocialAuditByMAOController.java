package com.ecrops.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecrops.dto.SocialAuditByMROMAOPojo;
import com.ecrops.entity.Employeename;
import com.ecrops.repo.SocialAuditByMAORepo;
import com.ecrops.repo.UpdateSocialAuditByMAORepo;

@Controller
public class SocialAuditByMAOController {
	
	@Autowired
	private SocialAuditByMAORepo socialAuditByMAORepo;
	
	@Autowired
	private UpdateSocialAuditByMAORepo updateSocialAuditByMAORepo;


	@GetMapping("/socialAuditByMAO")
	public String socialAuditByMAO(@ModelAttribute("allocSurvey") Employeename employeename, Model model,
			HttpSession httpSession, HttpServletRequest httpServletRequest) {
		
		System.out.println("********** Entered in socialAuditByMAO *********");
		
//		 String cropyear = "", season = "", dcode = "", activeyear = "2023";
//		 String activeYear = httpSession.getAttribute("ACTIVEYEAR").toString();
//		 System.out.println("activeYear -----> "+activeYear);
//         cropyear = "2023";
//         season = "R";
//         String wbdcode = httpSession.getAttribute("wbdcode").toString();
//         String wbmcode = httpSession.getAttribute("wbmcode").toString();
//         
//         System.out.println("dcode ---> "+wbdcode+"   mcode ---> "+wbmcode);
//
//         if (wbdcode.length() == 1) {
//        	 dcode = "0" + wbdcode;
//         }
//       String crDetailsTab = "cr_details_" + season + dcode + cropyear;   
//       String faremrGrievTab="farmer_grievances";
//       
//       if(activeyear.equals(cropyear)){                       
//    	   crDetailsTab="ecrop"+cropyear+"."+crDetailsTab; 
//    	   faremrGrievTab="ecrop"+cropyear+"."+faremrGrievTab;
//       }
//       int i = 0;
//       boolean flag = false;
		
		
		Integer activeYear = (Integer) httpSession.getAttribute("ACTIVEYEAR");
		String season = (String) httpSession.getAttribute("seasonActive");
		
         Integer wbdcode = (Integer) httpSession.getAttribute("wbdcode");
         String wbmcode = httpSession.getAttribute("wbmcode").toString();
         
         activeYear = 2023;
         season = "R";
         
         String crDetailsTab = "cr_details_";
         String faremrGrievTab="ecrop"+activeYear+"."+"farmer_grievances";
         
         if (wbdcode < 10) {
        	 crDetailsTab = "ecrop"+activeYear+"."+crDetailsTab + season + "0" + wbdcode + activeYear;
 		} else {
 			crDetailsTab = "ecrop"+activeYear+"."+crDetailsTab + season + wbdcode + activeYear;
 		}
         
         List<SocialAuditByMROMAOPojo> viewDetails = socialAuditByMAORepo.viewCropBookedDetailsByMAO(wbdcode, 
        		 Integer.parseInt(wbmcode), crDetailsTab, faremrGrievTab);
         
         System.out.println("View Details -----------> "+viewDetails.toString());
         
         model.addAttribute("viewDetails", viewDetails);

		return "maoroles/socialAuditByMAO";
	}
	

	@ResponseBody
	@PostMapping(path = "/saveSocialAuditByMAO")
	public String saveSocialAuditSelectionByMAO(@RequestBody Map<String, String> request,HttpSession httpSession, Model model, 
			HttpServletRequest httpRequest) {
		
		System.out.println("*********** Entered In SaveSelection *************");
		
	    String userid = httpSession.getAttribute("userid").toString();
	    
	    Integer activeYear = (Integer) httpSession.getAttribute("ACTIVEYEAR");
		String season = (String) httpSession.getAttribute("seasonActive");
	    
	    String rbkId = request.get("bookingIdOrg");
	    String rcrpcode = request.get("cropCodeOrg"); 
	    String rcrno = request.get("cropNumberOrg");
	    String rsowdt = request.get("sownDateOrg");  
	    String aprstatus = request.get("approvalStsOrg");
	    String khNo = request.get("khathaNoOrg");   
	    String surveyNo=request.get("surveyNoOrg");
	    String varietyCode = request.get("varietyCodeOrg");  
	    String webvill = request.get("villCodeOrg");   
	    String wbdcode = request.get("disCodeOrg");   
	    String wbmcode = request.get("mandCodeOrg");
	    
	    String clientip = httpRequest.getRemoteAddr();	
	    
	    int updateDetails = updateSocialAuditByMAORepo.updateSocialAuditByMAODetails( activeYear, season, rbkId, rcrpcode, rcrno, 
	    		rsowdt, khNo,  surveyNo,  varietyCode,  aprstatus, 
	    		userid,  clientip,  webvill,  wbdcode,  wbmcode);
	    String status = "";
	    if (updateDetails > 0) {
	    	status = "Data Updated Successfully";
	    } else {
	    	status = "Data Failed to Update";
	    }
	    
	    System.out.println("updateDetails ----------> "+updateDetails);
		
		
		return status;

	}

}
