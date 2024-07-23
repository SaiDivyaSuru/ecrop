package com.ecrops.service;

import java.util.List;

import com.ecrops.projection.InchargeRbkProjection;
import com.ecrops.projection.WbVillageRepository;

public interface DAO_Incharge_Service {

	public List<InchargeRbkProjection> getdaoDMcode(Integer distCode, Integer mandalcode);

	public int updateDAOIch(String maoRecList, String justifyIdList, String ada_appr, Integer district, Integer mandal,
			String rbkusersList, String empcodeList);

	public List<InchargeRbkProjection> getInc(Integer rbkcode, Integer empcode);

	public List<InchargeRbkProjection> getInclogic(Integer rbkcode, String rbkuserid);

	public int updateRBK(Integer empcodeList, Integer rbkusersList);

	public List<InchargeRbkProjection> getcheckrbklogs(Integer rbkcode, String rbkuserid);

	public int inslogs(Integer dcode, Integer mcode, String rbkusersList, String empcodeList, String rbkusersList2,
			Integer wbdcode, Integer wbmcode, String inchargests);

	public List<InchargeRbkProjection> getpincharge(Integer rbkcode);

	public int updateinch(Integer rbkusersList);

	public int updateinch2(Integer rbkusersList);

	public int updateinch3(Integer empcodeList, Integer rbkusersList);

	public int inslogs2(Integer rbkusersList, Integer empcodeList);

	public int inslogs3(Integer empcodeList, Integer rbkusersList);

	public int inslogs4(Integer rbkusersList, Integer empcodeList);

	public int updatelogsfinal(Integer rbkusersList);

}