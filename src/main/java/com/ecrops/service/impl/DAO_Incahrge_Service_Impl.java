package com.ecrops.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecrops.projection.InchargeRbkProjection;
import com.ecrops.repo.DAO_Incharge_Repo;
import com.ecrops.repo.DAO_Incharge_Save_Repo;
import com.ecrops.service.DAO_Incharge_Service;

@Service
public class DAO_Incahrge_Service_Impl implements DAO_Incharge_Service {

	@Autowired
	private DAO_Incharge_Repo dao_Incharge_Repo;

	@Autowired
	private DAO_Incharge_Save_Repo dao_Incharge_Save_Repo;

	@Override
	public List<InchargeRbkProjection> getdaoDMcode(Integer distCode, Integer mandalcode) {
		return dao_Incharge_Repo.getdaoDMcode(distCode, mandalcode);
	}

	@Override
	public int updateDAOIch(String maoRecList, String justifyIdList, String ada_appr, Integer district, Integer mandal,
			String rbkusersList, String empcodeList) {
		return dao_Incharge_Save_Repo.updateDAOIch(maoRecList, justifyIdList, ada_appr, district, mandal, rbkusersList,
				empcodeList);
	}

	@Override
	public List<InchargeRbkProjection> getInc(Integer rbkcode, Integer empcode) {
		return dao_Incharge_Repo.getInc(rbkcode, empcode);
	}

	@Override
	public List<InchargeRbkProjection> getInclogic(Integer rbkcode, String rbkuserid) {
		return dao_Incharge_Repo.getInclogic(rbkcode, rbkuserid);
	}

	@Override
	public int updateRBK(Integer empcodeList, Integer rbkusersList) {
		return dao_Incharge_Save_Repo.updateRBK(empcodeList, rbkusersList);
	}

	@Override
	public List<InchargeRbkProjection> getcheckrbklogs(Integer rbkcode, String rbkuserid) {
		return dao_Incharge_Repo.getcheckrbklogs(rbkcode, rbkuserid);
	}

	@Override
	public int inslogs(Integer dcode, Integer mcode, String rbkusersList, String empcodeList, String rbkusersList2,
			Integer wbdcode, Integer wbmcode, String inchargests) {
		return dao_Incharge_Save_Repo.inslogs(dcode, mcode, rbkusersList, empcodeList, rbkusersList2, wbdcode, wbmcode,
				inchargests);
	}

	@Override
	public List<InchargeRbkProjection> getpincharge(Integer rbkcode) {
		return dao_Incharge_Repo.getpincharge(rbkcode);
	}

	@Override
	public int updateinch(Integer rbkusersList) {
		return dao_Incharge_Save_Repo.updateinch(rbkusersList);
	}

	@Override
	public int updateinch2(Integer rbkusersList) {
		return dao_Incharge_Save_Repo.updateinch2(rbkusersList);
	}

	@Override
	public int updateinch3(Integer empcodeList, Integer rbkusersList) {
		return dao_Incharge_Save_Repo.updateinch3(empcodeList, rbkusersList);
	}

	@Override
	public int inslogs2(Integer rbkusersList, Integer empcodeList) {
		return dao_Incharge_Save_Repo.inslogs2(rbkusersList, empcodeList);
	}

	@Override
	public int inslogs3(Integer empcodeList, Integer rbkusersList) {
		return dao_Incharge_Save_Repo.inslogs3(empcodeList, rbkusersList);
	}

	@Override
	public int inslogs4(Integer rbkusersList, Integer empcodeList) {
		return dao_Incharge_Save_Repo.inslogs4(rbkusersList, empcodeList);
	}

	@Override
	public int updatelogsfinal(Integer rbkusersList) {
		return dao_Incharge_Save_Repo.updatelogsfinal(rbkusersList);
	}
}
