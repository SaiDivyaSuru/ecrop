package com.ecrops.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ecrops.entity.CropgroupsFCVR;
import com.ecrops.entity.CropvarietiesRep;
import com.ecrops.repo.CropgroupsFCVRRepository;
import com.ecrops.repo.CropvarietiesRepRepository;

@Controller
public class CropVarietiesRepController {
	
	@Autowired 
    private CropgroupsFCVRRepository  cropgroupsFCVRRepository;
	
	@Autowired 
    private CropvarietiesRepRepository  cropvarietiesRepRepository;
	
	//To get the cropgroup dropdown
	@PreAuthorize("hasAuthority('18')")
	@GetMapping("/cropvarietiesrep")
	public String findCropVarieties(Model model,HttpSession httpsession)
	{
		String role=httpsession.getAttribute("role").toString();
		List<CropgroupsFCVR> cropgroupdropdown=null;
		 if (role.equals("36")) {
			  cropgroupdropdown=cropgroupsFCVRRepository.getCropGroup1();
             
         } else {
        	 cropgroupdropdown=cropgroupsFCVRRepository.getCropGroup2();
         }
		System.out.println("cropgroupdropdown------->"+cropgroupdropdown);
		model.addAttribute("cropgroups",cropgroupdropdown);
	    return "ddh/cropvarietiesrep";   
	}
	
	//To get the cropvarieties report
	@PreAuthorize("hasAuthority('18')")
	@GetMapping("/getCropvarietiesRep/{grpcode}")
    @ResponseBody											 // Indicates that the return value should be written directly to the HTTP response body
    public Map<String, Object>  findAllCrop(@PathVariable("grpcode") String grpcode,Model model) {
		   Map<String, Object> response = new HashMap<>();
		   
		System.out.println("grpcode------->"+grpcode);
		
		///get the grpName from the form
    	String grpName= cropgroupsFCVRRepository.getCropGroup2(Integer.parseInt(grpcode));

		List<CropvarietiesRep> cropvarietiesRepLst;
		try {
            if (grpName != null) {
                cropvarietiesRepLst = cropvarietiesRepRepository.getCropVarieties1(Integer.parseInt(grpcode));
            } else {
                cropvarietiesRepLst = cropvarietiesRepRepository.getCropVarieties2();
            }
        } catch (Exception e) {
            // Handle NumberFormatException if necessary
            e.printStackTrace();
            cropvarietiesRepLst = null; // Or handle this case accordingly
        }
		
		 if (cropvarietiesRepLst == null || cropvarietiesRepLst.isEmpty()) {
	           model.addAttribute("message", "no data");
	     }else {
	            response.put("message", null); // clear message attribute if data is found
	            response.put("data", cropvarietiesRepLst);
	     }
 
        return response;
    }
	
	
	
}





















