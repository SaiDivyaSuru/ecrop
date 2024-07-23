package com.ecrops.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecrops.clsService.CLSMasterService;
import com.ecrops.dto.CLSDiseaseImagesCountDto;
import com.ecrops.dto.CLSLifespanImagesCountDto;
import com.ecrops.dto.CLSNutrientDeficiencyImagesCountDto;
import com.ecrops.dto.CLSPestImagesCountDto;
import com.ecrops.entity.CLSCropWisePojo;
import com.ecrops.repo.CLSCropWiseRepo;
import com.ecrops.service.CLSCropWiseService;

@Valid
@Controller
public class CLSController {

	@Autowired
	CLSMasterService clsMasterService;
	
	@Autowired
	CLSCropWiseService clsCropWiseService;
	
	
	
	@PreAuthorize("hasAuthority('53') || hasAuthotiry('19')")
	@GetMapping("/clsstats")
	public String getClsStats(HttpSession session, Model model) {
		String role1 = session.getAttribute("role").toString();
		String dist1 = session.getAttribute("wbdcode").toString();
		Character cropNature = (role1.equals("53")) ? 'A' : 'H';
		int distCode = Integer.parseInt(dist1);

		CLSDiseaseImagesCountDto diseaseImages = clsMasterService.totalDiseaseImages(cropNature, distCode);
		CLSLifespanImagesCountDto lifespanImages = clsMasterService.totalLifespanImages(cropNature, distCode);
		CLSNutrientDeficiencyImagesCountDto nutrientImages = clsMasterService.totalNutrientDeficiencyImages(cropNature,	distCode);
		CLSPestImagesCountDto pestImages = clsMasterService.totalPestImages(cropNature, distCode);

		model.addAttribute("diseaseImages", diseaseImages);
		model.addAttribute("lifespanImages", lifespanImages);
		model.addAttribute("nutrientImages", nutrientImages);
		model.addAttribute("pestImages", pestImages);

		return "cls/clsreport";
	}
	
	@GetMapping("/clsaccepttrans")
	public String cropwise(Model model) {		
		model.addAttribute("getcropwisedata",clsCropWiseService.getCropImagesData());
		return "cls/clsaccepttrans";
	}

	@GetMapping("/cropwisedata/{param}")
	public String cropwisedata(@PathVariable("param") String param) {
	    System.out.println("param---------------------" + param);
	    if(param.equalsIgnoreCase("Pest")) {
	    	System.out.println("Pest------------>");
	    	List<CLSCropWisePojo> cropImagesData = clsCropWiseService.getCropMandalImagesDatas(param);

	        
//	        if (cropImagesData != null) {
//	            for (CLSCropWisePojo pojo : cropImagesData) {
//	                System.out.println(pojo);
//	            }
//	        }
	    }else if(param.equalsIgnoreCase("Disease")) {
	    	System.out.println("Disease------------>");
	    }else if(param.equalsIgnoreCase("NutriDeficiency")) {
	    	System.out.println("NutriDeficiency------------>");
	    }else if(param.equalsIgnoreCase("LifeSpan")) {
	    	System.out.println("LifeSpan------------>");
	    }
	    return "cls/cropwisedata";
	}
	

}
