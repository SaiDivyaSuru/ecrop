package com.ecrops.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Valid
@Controller
public class CLSAcceptRejectController {

//	@GetMapping("/clsaccepttrans")
//	public String acceptreject(HttpSession session) {
//		String role1 = session.getAttribute("role").toString();
//		String dist1 = session.getAttribute("wbdcode").toString();
//		System.out.println("role----------"+role1);
//		System.out.println("dist------------"+dist1);
//		
//		return "cls/clsaccepttrans";
//	}
}
