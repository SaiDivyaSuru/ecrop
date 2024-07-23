/**Step1: Hit the url
 * Step2:onchange of dropdown button hits another api
 * 
 * */
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ecrops.entity.CropgroupsFCNR;
import com.ecrops.entity.CropnamesRep;
import com.ecrops.repo.CropgroupsFCNRRepository;
import com.ecrops.repo.CropnamesRepRepository;

@Controller //To represent controller class
public class CropNamesRepController {

	    @Autowired 													
	    private CropgroupsFCNRRepository cropgroupsFCNRRepository;
	    
	    @Autowired 													
	    private CropnamesRepRepository cropnamesRepRepository;

	    //get the cropgroupdropdown
	    @GetMapping("/cropnamesrep")
		public String getCropgroupDropdown(Model model,HttpSession httpSession)
		{
	    	String role = httpSession.getAttribute("role").toString(); //get the role from the session
	    	System.out.println("role********------->"+role);
	    	List<CropgroupsFCNR> cropgroupdropdown=null;
	    	
	    	if (role.equals("36")) {
	    		 cropgroupdropdown=cropgroupsFCNRRepository.getCropGroup1();
            } else {
           	 cropgroupdropdown=cropgroupsFCNRRepository.getCropGroup2();
            }
	    	
	    	System.out.println("cropgroupdropdown---->"+cropgroupdropdown);

			model.addAttribute("cropnamesrep",cropgroupdropdown);
		    return "ddh/cropnamesrep";   
		} 
		
	  //get the cropnames report based on grpcode
	    //@PreAuthorize("hasAuthority('18')")
	    @GetMapping("/getCropNames/{grpcode}")
	    @ResponseBody
	    public Map<String, Object> getCropnamesRep(@PathVariable("grpcode") String grpcode,
	    										   Model model) {
	    	System.out.println("start...");
	    	System.out.println("cropgrpid..."+grpcode);//get the grpcode from the form
	    	
	    	///get the grpName from the form
	    	String grpName= cropgroupsFCNRRepository.getCropGroup2(Integer.parseInt(grpcode));
	    	
	    	System.out.println("grpName..."+grpName);
	        Map<String, Object> response = new HashMap<>();
	        List<CropnamesRep> cropnamesRep = null;
	        try {
	            if (grpName != null) {
	                cropnamesRep = cropnamesRepRepository.getCropNames1(Integer.parseInt(grpcode));
	            } else {
	              cropnamesRep = cropnamesRepRepository.getCropNames2();
	            }
	            
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        }


	        if (cropnamesRep == null || cropnamesRep.isEmpty()) {
	            response.put("message", "Table data not found");
	        } else {
	            response.put("message", null); // clear message attribute if data is found
	            response.put("data", cropnamesRep);
	        }
	        return response;
	    }
	    
//	    
//	    @GetMapping("/getCropNames")
//	    @ResponseBody
//	    public Map<String, Object> getCropnamesRep(@RequestParam("cropgroupid") String grpcode,
//	    										   Model model) {
//	    	System.out.println("start...");
//	    	System.out.println("cropgrpid..."+grpcode);//get the grpcode from the form
//	    	///get the grpName from the form
//	    	String grpName= cropgroupsFCNRRepository.getCropGroup2(Integer.parseInt(grpcode));
//	    	System.out.println("grpName..."+grpName);
//	        Map<String, Object> response = new HashMap<>();
//	        List<CropnamesRep> cropnamesRep = null;
//	        try {
//	            if (grpName != null) {
//	                cropnamesRep = cropnamesRepRepository.getCropNames1(Integer.parseInt(grpcode));
//	            } else {
//	              cropnamesRep = cropnamesRepRepository.getCropNames2();
//	            }
//	            
//	        } catch (NumberFormatException e) {
//	            e.printStackTrace();
//	        }
//
//
//	        if (cropnamesRep == null || cropnamesRep.isEmpty()) {
//	            response.put("message", "Table data not found");
//	        } else {
//	            response.put("message", null); // clear message attribute if data is found
//	            response.put("data", cropnamesRep);
//	        }
//	        return response;
//	    }
}
