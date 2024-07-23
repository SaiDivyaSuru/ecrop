/**
 * If you hit the url we will get the table
 * 
 * 
 * */

package com.ecrops.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ecrops.entity.DistwiseSupChk;
import com.ecrops.repo.DistwiseSupChkRepo;

@Controller
public class DistwiseSupChkRepController {
	
	@Autowired
	DistwiseSupChkRepo distwiseSupChkRepo;
	
	@GetMapping("/distwisesupchkrep")
	public String Distwisesupchkrep(Model model,HttpSession httpsession,RedirectAttributes redirectAttributes) {
		
		String role = (String) httpsession.getAttribute("role");
		String wbdcode = (String) httpsession.getAttribute("wbdcode");
		String cr_season = httpsession.getAttribute("seasonActive").toString();
		String cr_year = httpsession.getAttribute("ACTIVEYEAR").toString();
		
		   System.out.println("start....");
		   
	 	   List<DistwiseSupChk> distwiseSupChk=null;
	 	   
	 	  try {
	 		  
			distwiseSupChk=distwiseSupChkRepo.getCropwise(role,wbdcode,cr_season,Integer.parseInt(cr_year));
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			   e.printStackTrace();
		      }
	 	   
	 	 if (distwiseSupChk.isEmpty()){
			 redirectAttributes.addFlashAttribute("message", "table data not found!");
	 
		 }
			for(DistwiseSupChk distwiseSupCh:distwiseSupChk) {
				System.out.println("DistwiseSupChk------------>"+distwiseSupCh.toString());
		    }
			model.addAttribute("distwisesupchk",distwiseSupChk);
		    return "ddh/distwisesupchkrep"; 
			
		}

}
