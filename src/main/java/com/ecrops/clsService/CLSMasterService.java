package com.ecrops.clsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecrops.dto.CLSDiseaseImagesCountDto;
import com.ecrops.dto.CLSLifespanImagesCountDto;
import com.ecrops.dto.CLSNutrientDeficiencyImagesCountDto;
import com.ecrops.dto.CLSPestImagesCountDto;

import com.ecrops.repo.DiseaseImageRepo;
import com.ecrops.repo.LifespanImageRepo;
import com.ecrops.repo.NutrientDeficiencyImageRepo;
import com.ecrops.repo.PestImageRepo;

@Service
public class CLSMasterService {
	
	@Autowired
	DiseaseImageRepo diseaseImageRepo;
	
	@Autowired
	LifespanImageRepo lifespanImageRepo;
	
	@Autowired
	NutrientDeficiencyImageRepo nutrientDeficiencyImageRepo;
	
	@Autowired
	PestImageRepo pestImagesRepo;
	
	
	
	public CLSDiseaseImagesCountDto totalDiseaseImages(Character cropNature, int distCode) {

	    int totalDrcPendingDiseaseImagesNo = 0;
	    int totalDrcApprovedDiseaseImagesNo = 0;
	    int totalDrcRejectedDiseaseImagesNo = 0;

	    CLSDiseaseImagesCountDto total = new CLSDiseaseImagesCountDto();
	    try {
	    	
	        Integer pending = diseaseImageRepo.getTotalDrcpendingDiseaseIamges(cropNature, distCode);
	        totalDrcPendingDiseaseImagesNo = (pending != null) ? Math.max(pending, 0) : 0;
	        System.out.println("totalDrcPendingDiseaseImagesNo---------------" + totalDrcPendingDiseaseImagesNo);
	
	        totalDrcApprovedDiseaseImagesNo = diseaseImageRepo.getTotalDrcApprovedDiseaseImages(cropNature, distCode);
	        totalDrcApprovedDiseaseImagesNo = Math.max(totalDrcApprovedDiseaseImagesNo, 0);
	        System.out.println("totalDrcApprovedDiseaseImagesNo----------------" + totalDrcApprovedDiseaseImagesNo);

	        totalDrcRejectedDiseaseImagesNo = diseaseImageRepo.getTotalDrcRejectedDiseaseImages(cropNature, distCode);
	        totalDrcRejectedDiseaseImagesNo = Math.max(totalDrcRejectedDiseaseImagesNo, 0);
	        System.out.println("totalDrcRejectedDiseaseImagesNo------------------" + totalDrcRejectedDiseaseImagesNo);
	        
	        total.setGettotalDrcaprrovedDiseaseImagesNo(totalDrcApprovedDiseaseImagesNo);
	        total.setGettotalDrcRejectedDiseaseImagesNo(totalDrcRejectedDiseaseImagesNo);
	        total.setGettotalDrcpendingDiseaseImagesNo(totalDrcPendingDiseaseImagesNo);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return total;
	    
	}
	
	public CLSLifespanImagesCountDto totalLifespanImages(Character cropnature ,int distcode) {
		
		CLSLifespanImagesCountDto total = new CLSLifespanImagesCountDto();
		int totalDrcPendingLifespanImagesNo = 0 ;
		int totalDrcApprovedLifespanImagesNo = 0 ;
		int totalDrcRejectedLifespanImagesNo = 0 ;
		
		try {
			totalDrcPendingLifespanImagesNo = lifespanImageRepo.getTotalDrcpendingLifespanImages(cropnature, distcode);
			totalDrcPendingLifespanImagesNo = Math.max(totalDrcPendingLifespanImagesNo, 0);
			System.out.println("totalDrcPendingLifespanImagesNo-------------" + totalDrcPendingLifespanImagesNo);
			
			totalDrcApprovedLifespanImagesNo = lifespanImageRepo.getTotalDrcApprovedLifespanImages(cropnature, distcode);
			totalDrcApprovedLifespanImagesNo = Math.max(totalDrcApprovedLifespanImagesNo, 0);
			System.out.println("totalDrcApprovedLifespanImagesNo-----------" + totalDrcApprovedLifespanImagesNo);
			
			totalDrcRejectedLifespanImagesNo = lifespanImageRepo.getTotalDrcRejectedLifespanImages(cropnature, distcode);
			totalDrcRejectedLifespanImagesNo = Math.max(totalDrcRejectedLifespanImagesNo, 0);
			System.out.println("totalDrcRejectedLifespanImagesNo-------------" + totalDrcRejectedLifespanImagesNo);
			
			total.setTotalDrcApprovedLifespanImagesNo(totalDrcApprovedLifespanImagesNo);
			total.setTotalDrcPendingLifespanImagesNo(totalDrcPendingLifespanImagesNo);
			total.setTotalDrcRejectedLifespanImagesNo(totalDrcRejectedLifespanImagesNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public CLSNutrientDeficiencyImagesCountDto totalNutrientDeficiencyImages(Character cropnature ,int distcode) {
		
		CLSNutrientDeficiencyImagesCountDto total = new CLSNutrientDeficiencyImagesCountDto();
		int totalDrcPendingNutrientDeficiencyImagesNo = 0;
		int totalDrcApprovedNutrientDeficiencyImagesNo = 0;
		int totalDrcRejectedNutrientDeficiencyImagesNo = 0;
		try {
			totalDrcPendingNutrientDeficiencyImagesNo = nutrientDeficiencyImageRepo.getTotalDrcPendingNutrientDeficiencyImages(cropnature, distcode);
			totalDrcPendingNutrientDeficiencyImagesNo = Math.max(totalDrcPendingNutrientDeficiencyImagesNo, 0);
			System.out.println("totalDrcPendingNutrientDeficiencyImagesNo---------------" + totalDrcPendingNutrientDeficiencyImagesNo);
			
			totalDrcApprovedNutrientDeficiencyImagesNo = nutrientDeficiencyImageRepo.getTotalDrcApprovedNutrientDeficiencyImages(cropnature, distcode);
			totalDrcApprovedNutrientDeficiencyImagesNo = Math.max(totalDrcApprovedNutrientDeficiencyImagesNo, 0);
			System.out.println("totalDrcApprovedNutrientDeficiencyImagesNo---------------" + totalDrcApprovedNutrientDeficiencyImagesNo);
			
			totalDrcRejectedNutrientDeficiencyImagesNo = nutrientDeficiencyImageRepo.getTotalDrcRejectedNutrientDeficiencyImages(cropnature, distcode);
			totalDrcRejectedNutrientDeficiencyImagesNo = Math.max(totalDrcRejectedNutrientDeficiencyImagesNo, 0);
			System.out.println("totalDrcRejectedNutrientDeficiencyImagesNo---------------" + totalDrcRejectedNutrientDeficiencyImagesNo);
			
			total.setTotalDrcApprovedNutrientDeficiencyImagesNo(totalDrcApprovedNutrientDeficiencyImagesNo);
			total.setTotalDrcPendingNutrientDeficiencyImagesNo(totalDrcPendingNutrientDeficiencyImagesNo);
			total.setTotalDrcRejectedNutrientDeficiencyImagesNo(totalDrcRejectedNutrientDeficiencyImagesNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return total;
	}
	
	public CLSPestImagesCountDto totalPestImages(Character cropnature , int distcode) {
		
		CLSPestImagesCountDto total = new CLSPestImagesCountDto();
		int totalDrcPendingPestImagesNo = 0;
		int totalDrcApprovedPestImagesNo = 0;
		int totalDrcRejectedPestImagesNo = 0;
		try {
			totalDrcPendingPestImagesNo = pestImagesRepo.getTotalDrcPendingPestImages(cropnature, distcode);
			totalDrcPendingPestImagesNo = Math.max(totalDrcPendingPestImagesNo, 0);
			System.out.println("totalDrcPendingPestImagesNo-------------" + totalDrcPendingPestImagesNo);
			
			Integer approved = pestImagesRepo.getTotalDrcApprovedPestImages(cropnature, distcode);
			//totalDrcApprovedPestImagesNo = Math.max(totalDrcApprovedPestImagesNo, 0);
			//System.out.println("totalDrcApprovedPestImagesNo" + totalDrcApprovedPestImagesNo);
			totalDrcApprovedPestImagesNo = (approved != null) ? Math.max(approved, 0) : 0;
	        System.out.println("totalDrcApprovedPestImagesNo--------------" + totalDrcApprovedPestImagesNo);

			totalDrcRejectedPestImagesNo = pestImagesRepo.getTotalDrcRejectedPestImages(cropnature, distcode);
			totalDrcRejectedPestImagesNo = Math.max(totalDrcRejectedPestImagesNo, 0);
			System.out.println("totalDrcRejectedPestImagesNo-----------------" + totalDrcRejectedPestImagesNo);
			
			total.setTotalDrcApprovedPestImagesNo(totalDrcApprovedPestImagesNo);
			total.setTotalDrcPendingPestImagesNo(totalDrcPendingPestImagesNo);
			total.setTotalDrcRejectedPestImagesNo(totalDrcRejectedPestImagesNo);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	
	

}
