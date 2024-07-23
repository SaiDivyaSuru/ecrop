package com.ecrops.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ecrops.entity.ActiveseasonFDWSRFAC;
import com.ecrops.entity.CropnamesFAgriCrop;
import com.ecrops.entity.DistwiseStatusRepForAllCrops;
import com.ecrops.repo.ActiveseasonFDWSRFACRepository;
import com.ecrops.repo.CropnamesFAgriCropRepo;
import com.ecrops.repo.DistwiseStatusRepForAllCropsRepo;

@Controller
public class DistwiseStatusRepForAllCropsController {
	@Autowired
	ActiveseasonFDWSRFACRepository activeseasonFDWSRFACRepository;
	
	@Autowired
	DistwiseStatusRepForAllCropsRepo distwiseStatusRepForAllCropsRepo;
	
	@Autowired
	CropnamesFAgriCropRepo cropnamesFAgriCropRepo;
	
	@PreAuthorize("hasAuthority('18')")
	@GetMapping("/distwisestatusrepforallcrops")
	public String getCropYear(Model model) {
		
		List<ActiveseasonFDWSRFAC> cropyr=activeseasonFDWSRFACRepository.getCropyear(); //To get the cropyear dropdown
		System.out.println("cropyear-------->"+cropyr);
		
		model.addAttribute("cropyears", cropyr);   //To bind the result to thymeleaf
		
		model.addAttribute("theDate", LocalDate.now());//To print the date
		
		return "ddh/distwisestatusrepforallcrops";   //To return the thymeleaf page
	}
	
	
	//To get districtwisestatusreportforallcrops Report
	@PreAuthorize("hasAuthority('18')")
	@GetMapping("/districtwisestatusreportforallcrp/{cropyrid}")
	@ResponseBody
	public List<DistwiseStatusRepForAllCrops> getDistwiseStatusRepForAllCropsRepLst(@PathVariable("cropyrid") String sescropyear,
																					RedirectAttributes redirectAttributes,
																					HttpSession httpsession,
																					Model model){
		System.out.println("report starts.........");
		
		String activeYear=httpsession.getAttribute("ACTIVEYEAR").toString();
		
		List<DistwiseStatusRepForAllCrops> cropreport=null;
		try {
			cropreport = distwiseStatusRepForAllCropsRepo.getCropwise(sescropyear,Integer.parseInt(activeYear));
		} catch (Exception e) {
			
			model.addAttribute("message", "table relation does not exist!");
			e.printStackTrace();
		}
		 if (cropreport.isEmpty()){
			 redirectAttributes.addFlashAttribute("message", "table data not found!");
	 
		 }
		
		 for (DistwiseStatusRepForAllCrops distwiseStatusRepForAllCrops : cropreport) {
	           System.out.println("distwiseStatusRepForAllCrops: " + distwiseStatusRepForAllCrops.getWbdname());
	     }
		
		return cropreport;   
		
	}
	
	@GetMapping("/Rep_Agri_Crops")
	public String AgriCropsRep(Model model) {
		List<CropnamesFAgriCrop> agricrop=cropnamesFAgriCropRepo.getAgri(); //To get the cropyear dropdown
		System.out.println("agricrop-------->"+agricrop);
		
		model.addAttribute("agricrops", agricrop);   //To bind the result to thymeleaf
		
		return "ddh/Rep_Agri_Crops";   //To return the thymeleaf page
	}
	
	@GetMapping("/Rep_Horti_Crops")
	public String HortiCropsRep(Model model) {
		
		List<CropnamesFAgriCrop> horticrop=cropnamesFAgriCropRepo.getHorti(); //To get the cropyear dropdown
		System.out.println("horticrop-------->"+horticrop);
		
		model.addAttribute("horticrops", horticrop);   //To bind the result to thymeleaf
		
		return "ddh/Rep_Horti_Crops";   //To return the thymeleaf page
	}
	
	@GetMapping("/Rep_Seri_Crops")
	public String SeriCropsRep(Model model) {
		
		List<CropnamesFAgriCrop> sericrop=cropnamesFAgriCropRepo.getSeri(); //To get the cropyear dropdown
		System.out.println("sericrop-------->"+sericrop);
		
		model.addAttribute("sericrops", sericrop);   //To bind the result to thymeleaf
		
		return "ddh/Rep_Seri_Crops";   //To return the thymeleaf page
	}
	
	
}
