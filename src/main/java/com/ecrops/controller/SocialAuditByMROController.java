package com.ecrops.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecrops.dto.SocialAuditByMROMAOPojo;
import com.ecrops.projection.ActiveSeasonProjection;
import com.ecrops.repo.GetCropYearRepo;
import com.ecrops.repo.SocialAuditByMRORepo;
import com.ecrops.repo.UpdateSocialAuditByMRORepo;
import com.ecrops.repo.WbvillagesRepository;
import com.ecrops.projection.WbVillageMastProjection;

@Controller
public class SocialAuditByMROController {
	
	@Autowired
	private SocialAuditByMRORepo socialAuditByMRORepo;     
	
	@Autowired
	private GetCropYearRepo cropYearRepo;

	@Autowired
	private WbvillagesRepository wbvillagesRepository;
	
	@Autowired
	private UpdateSocialAuditByMRORepo updateSocialAuditByMRORepo;
	
	@GetMapping("/socialAuditByMRO")
	public String allocOfSurveyNo(Model model,
			HttpSession httpSession, HttpServletRequest httpServletRequest) {

		Integer mcode = (Integer) httpSession.getAttribute("wbmcode");
		String wbdcode = httpSession.getAttribute("wbdcode").toString();
		System.out.println("Mandal code ---------> " + mcode);
		System.out.println("wbdcode ---------> " + wbdcode);
		List<ActiveSeasonProjection> activeSeason = cropYearRepo.getActiveSeason();
		List<WbVillageMastProjection> rbk = wbvillagesRepository.getVillNameByMand(mcode);
		
		model.addAttribute("activeseason", activeSeason);
		model.addAttribute("villages", rbk);

		return "mro/socialAuditByMRO";
	}
	
	@GetMapping("/viewSocialAuditByMRO")
	public String socialAuditByMRO(Model model,
			HttpSession httpSession, HttpServletRequest httpServletRequest) {
		
		System.out.println("********** Entered in socialAuditByMRO *********");
		
		String wbvcode = httpServletRequest.getParameter("rbkVillage");
		System.out.println("wbvcode -----> "+wbvcode);
		
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
        
        List<SocialAuditByMROMAOPojo> viewDetails = socialAuditByMRORepo.viewCropBookedDetailsByMRO(wbdcode, 
       		 Integer.parseInt(wbmcode), Integer.parseInt(wbvcode), crDetailsTab, faremrGrievTab);
        
         System.out.println("View Details -----------> "+viewDetails.toString());
         
         model.addAttribute("viewDetails", viewDetails);
		

		return "mro/viewDetailsOfSocialAuditByMRO";
	}
//	
//
	@ResponseBody
	@PostMapping(path = "/saveSocialAuditByMRO")
	public String saveSocialAuditSelectionByMRO(@RequestBody Map<String, String> request,HttpSession httpSession, Model model, 
			HttpServletRequest httpRequest) {
		
		System.out.println("*********** Entered In SaveSelection *************");
		
	    String mcode = httpSession.getAttribute("wbmcode").toString();
	    String userid = httpSession.getAttribute("userid").toString();
	    
	    Integer activeYear = (Integer) httpSession.getAttribute("ACTIVEYEAR");
		String season = (String) httpSession.getAttribute("seasonActive");
		Integer dcode = (Integer) httpSession.getAttribute("wbdcode");
	    
	    String rbkId = request.get("bookingIdOrg");
	    String rcrpcode = request.get("cropCodeOrg"); 
	    String rcrno = request.get("cropNumberOrg");
	    char crno = rcrno.charAt(0);
	    String rsowdt = request.get("sownDateOrg");  
	    String aprstatus = request.get("approvalStsOrg");
	    String khNo = request.get("khathaNoOrg");   
	    String surveyNo=request.get("surveyNoOrg");
	    String varietyCode = request.get("varietyCodeOrg");  
	    String webvill = request.get("villCodeOrg");   
	    String wbdcode = request.get("disCodeOrg");   
	    String wbmcode = request.get("mandCodeOrg");
	    String grievId = request.get("grievanceIdOrg");
	    
	    String clientip = httpRequest.getRemoteAddr();	
	    
	    String updateDetails = updateSocialAuditByMRORepo.updateSocialAuditByMRODetails( activeYear, dcode, mcode, season, rbkId, rcrpcode, rcrno, 
	    		rsowdt, khNo,  surveyNo,  varietyCode,  aprstatus, 
	    		userid,  clientip,  webvill,  wbdcode,  wbmcode, grievId);
	    
	    System.out.println("updateDetails ----------> "+updateDetails);
		
//		
		return updateDetails;

	}

}
