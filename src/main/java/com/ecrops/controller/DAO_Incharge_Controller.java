package com.ecrops.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecrops.entity.MandalEntity;
import com.ecrops.projection.InchargeRbkProjection;
import com.ecrops.projection.VillageName;
import com.ecrops.projection.WbVillageRepository;
import com.ecrops.repo.MandalRepo;
import com.ecrops.repo.WbvillageRepository;
import com.ecrops.service.impl.DAO_Incahrge_Service_Impl;

@Controller
public class DAO_Incharge_Controller {

	@Autowired
	private MandalRepo mandalRepo;

	@Autowired
	private DAO_Incahrge_Service_Impl dao_Incahrge_Service_Impl;

	@Autowired
	private WbvillageRepository wbVillageRepository;

	@PreAuthorize("hasAuthority('9')")
	@GetMapping("/daoinchargesearch")
	public String getadaapproval(@Valid @ModelAttribute("mandalEntity") MandalEntity mandalEntity,
			BindingResult bindingResult, HttpSession httpSession, HttpServletRequest httpServiceRequest, Model model) {

		if (bindingResult.hasErrors()) {
			return "redirect:/daoinchargesearch";
		}

		String district = (String) httpSession.getAttribute("dcode");
		List<VillageName> mandalName = mandalRepo.getmname(Integer.parseInt(district));
		model.addAttribute("mandalName", mandalName);

		return "dao/daoinchargesearch";
	}

	@PreAuthorize("hasAuthority('9')")
	@PostMapping("/daoincharge")
	public String postdaoapproval(RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest,
			HttpSession httpSession, Model model) {

		String mandalcodes = httpServletRequest.getParameter("mandal");

		if (mandalcodes == null || mandalcodes.isEmpty()) {

			redirectAttributes.addFlashAttribute("msg", "Please select a Mandal");
			return "redirect:/daoinchargesearch";
		}

		String district = (String) httpSession.getAttribute("dcode");

		List<InchargeRbkProjection> getdaoDMcode = dao_Incahrge_Service_Impl.getdaoDMcode(Integer.parseInt(district),
				Integer.parseInt(mandalcodes));

		List<VillageName> mandalName = mandalRepo.getmname(Integer.parseInt(district));
		String mandalNameName = "";
		for (VillageName bean : mandalName) {
			if (bean.getMcode().equalsIgnoreCase(mandalcodes)) {
				mandalNameName = bean.getMname();
			}
		}
		model.addAttribute("mandalNameName", mandalNameName);

		model.addAttribute("mandalcodes", mandalcodes);

		// Check if the result list is empty
		if (getdaoDMcode.isEmpty()) {
			model.addAttribute("noResults", true);
		} else {
			model.addAttribute("getdaoDMcode", getdaoDMcode);
		}

		return "dao/daoinchargeapproval";
	}

	@PreAuthorize("hasAuthority('9')")
	@PostMapping("/daoinchargeapproval")
	public String daoAppRej(HttpSession httpSession, HttpServletRequest httpServletRequest, Model model) {

		String district = (String) httpSession.getAttribute("dcode");
		String mandalcodes = (String) httpServletRequest.getParameter("mandalcode");
		List<WbVillageRepository> weblanddetails = wbVillageRepository.getWebLandDetails(Integer.parseInt(district),
				Integer.parseInt(mandalcodes));
		String wbdcode = weblanddetails.get(0).getWbdcode();
		String wbmcode = weblanddetails.get(0).getWbmcode();
		String dao_appr = httpServletRequest.getParameter("recommendation");
		String dao_remarks = httpServletRequest.getParameter("justification");
		String ada_appr = "A";
		String Rbkuserid = httpServletRequest.getParameter("Rbkuserid");
		String Rbkcode = httpServletRequest.getParameter("Rbkcode");
		String Empcode = httpServletRequest.getParameter("Empcode");
		String inchargeSts = httpServletRequest.getParameter("Inchargests");
		model.addAttribute("inchargeSts", inchargeSts);
		
		String maoRecLst[] = httpServletRequest.getParameter("maoRecLst").split(",");
		String justifyIdList[] = httpServletRequest.getParameter("justifyIdLst").split(",");
		String pempcodeLst[] = httpServletRequest.getParameter("pempcodeLst").split(",");
		String prbkusrLst[] = httpServletRequest.getParameter("prbkusrLst").split(",");
		String incStsLst[] = httpServletRequest.getParameter("incStsLst").split(",");
		String prbkcodesLst[] = httpServletRequest.getParameter("prbkcodesLst").split(",");

		if (maoRecLst.length == 0) {
			List<VillageName> mandalName = mandalRepo.getmname(Integer.parseInt(district));
			String mandalNameName = "";
			for (VillageName bean : mandalName) {
				if (bean.getMcode().equalsIgnoreCase(mandalcodes)) {
					mandalNameName = bean.getMname();
				}
			}
			model.addAttribute("mandalNameName", mandalNameName);
			model.addAttribute("mandalcodes", mandalcodes);

			List<InchargeRbkProjection> getdaoDMcode = dao_Incahrge_Service_Impl
					.getdaoDMcode(Integer.parseInt(district), Integer.parseInt(mandalcodes));
			if (getdaoDMcode.isEmpty()) {
				model.addAttribute("noResults", true);
			} else {
				model.addAttribute("getdaoDMcode", getdaoDMcode);
			}
			model.addAttribute("msg", "Please select atleast One Record ");
			return "dao/daoinchargeapproval";
		}
		for (int i = 0; i < pempcodeLst.length; i++) {
			int updateDAOIch = dao_Incahrge_Service_Impl.updateDAOIch(maoRecLst[i], justifyIdList[i], ada_appr,
					Integer.parseInt(district), Integer.parseInt(mandalcodes), pempcodeLst[i], prbkusrLst[i]);
			if (updateDAOIch > 0) {
				List<VillageName> mandalName = mandalRepo.getmname(Integer.parseInt(district));
				String mandalNameName = "";
				for (VillageName bean : mandalName) {
					if (bean.getMcode().equalsIgnoreCase(mandalcodes)) {
						mandalNameName = bean.getMname();
					}
				}
				model.addAttribute("mandalNameName", mandalNameName);
				model.addAttribute("mandalcodes", mandalcodes);

				List<InchargeRbkProjection> getdaoDMcode = dao_Incahrge_Service_Impl
						.getdaoDMcode(Integer.parseInt(district), Integer.parseInt(mandalcodes));
				if (getdaoDMcode.isEmpty()) {
					model.addAttribute("noResults", true);
				} else {
					model.addAttribute("getdaoDMcode", getdaoDMcode);
				}
				if (maoRecLst[i].equals("A")) {
					List<InchargeRbkProjection> Daoinch = dao_Incahrge_Service_Impl
							.getInc(Integer.parseInt(prbkcodesLst[i]), Integer.parseInt(pempcodeLst[i]));
					if (Daoinch.size() > 0) {
						String puid = Daoinch.get(0).getUid();
						String pEmail = Daoinch.get(0).getEmail();
						String pMobile = Daoinch.get(0).getMobile();
						String pEmpcode = Daoinch.get(0).getEmpcode();
						String pEmpName = Daoinch.get(0).getEmpname();
						String pDcode = Daoinch.get(0).getDcode();
						String pMcode = Daoinch.get(0).getMcode();
						String pRbkcode = Daoinch.get(0).getRbkcode();
						if (incStsLst[i].equals("I")) {
							boolean EmpExist = false;
							List<InchargeRbkProjection> Inchsts = dao_Incahrge_Service_Impl
									.getInclogic(Integer.parseInt(prbkcodesLst[i]), "RBK_" + prbkcodesLst[i]);
							if (Inchsts.size() > 0) {
								EmpExist = true;
							}
							if (EmpExist) {
								int incdaoapp = dao_Incahrge_Service_Impl.updateRBK(Integer.parseInt(pempcodeLst[i]),
										Integer.parseInt(prbkcodesLst[i]));
								List<InchargeRbkProjection> checkrbklogs = dao_Incahrge_Service_Impl
										.getcheckrbklogs(Integer.parseInt(prbkcodesLst[i]), "RBK_" + prbkcodesLst[i]);
								if (checkrbklogs.size() > 0) {
									if (inchargeSts.equals("R")) {
										String status = "I";
										int inslogs = dao_Incahrge_Service_Impl.inslogs(Integer.parseInt(district),
												Integer.parseInt(mandalcodes), prbkcodesLst[i], pempcodeLst[i],
												"RBK_" + prbkcodesLst[i], Integer.parseInt(wbdcode),
												Integer.parseInt(wbmcode), status);
										String pInchargests = "";
										List<InchargeRbkProjection> getpincharge = dao_Incahrge_Service_Impl
												.getpincharge(Integer.parseInt(prbkcodesLst[i]));
										if (getpincharge.size() > 0) {
											pInchargests = getpincharge.get(0).getInchargests();
										}
										if (pInchargests.equals("R")) {
											int updateinch = dao_Incahrge_Service_Impl
													.updateinch(Integer.parseInt(prbkcodesLst[i]));

										} else if (pInchargests.equals("I")) {
											int updateinch2 = dao_Incahrge_Service_Impl
													.updateinch2(Integer.parseInt(prbkcodesLst[i]));
										}
									} else {
										int updateinch3 = dao_Incahrge_Service_Impl.updateinch3(
												Integer.parseInt(pempcodeLst[i]), Integer.parseInt(prbkcodesLst[i]));
									}
								} else {
									int inslogs2 = dao_Incahrge_Service_Impl.inslogs2(Integer.parseInt(prbkcodesLst[i]),
											Integer.parseInt(pempcodeLst[i]));
								}
							} else {
								int inslogs3 = dao_Incahrge_Service_Impl.inslogs3(Integer.parseInt(pempcodeLst[i]),
										Integer.parseInt(prbkcodesLst[i]));
								int inslogs4 = dao_Incahrge_Service_Impl.inslogs4(Integer.parseInt(prbkcodesLst[i]),
										Integer.parseInt(pempcodeLst[i]));
								String Inchargests = "";
								List<InchargeRbkProjection> getpincharge = dao_Incahrge_Service_Impl
										.getpincharge(Integer.parseInt(prbkcodesLst[i]));
								if (getpincharge.size() > 0) {
									Inchargests = getpincharge.get(0).getInchargests();
								}
								if (Inchargests.equals("R")) {
									int updatelogsfinal = dao_Incahrge_Service_Impl
											.updatelogsfinal(Integer.parseInt(prbkcodesLst[i]));
								}
							}
						}
					}
				}
				model.addAttribute("msg", "Successfully Updated Details");
			} else {
				List<VillageName> mandalName = mandalRepo.getmname(Integer.parseInt(district));
				String mandalNameName = "";
				for (VillageName bean : mandalName) {
					if (bean.getMcode().equalsIgnoreCase(mandalcodes)) {
						mandalNameName = bean.getMname();
					}
				}
				model.addAttribute("mandalNameName", mandalNameName);
				model.addAttribute("mandalcodes", mandalcodes);
				List<InchargeRbkProjection> getdaoDMcode = dao_Incahrge_Service_Impl
						.getdaoDMcode(Integer.parseInt(district), Integer.parseInt(mandalcodes));
				if (getdaoDMcode.isEmpty()) {
					model.addAttribute("noResults", true);
				} else {
					model.addAttribute("getdaoDMcode", getdaoDMcode);
				}
				model.addAttribute("msg", "Updation Failed");
			}
		}
		return "dao/daoinchargeapproval";
	}

}
