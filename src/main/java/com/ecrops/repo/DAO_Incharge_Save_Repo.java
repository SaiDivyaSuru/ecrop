package com.ecrops.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
public class DAO_Incharge_Save_Repo {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public int updateDAOIch(String maoRecList, String justifyIdList, String ada_appr, Integer district, Integer mandal,
			String empcodeList, String rbkusersList) {

		System.out.println("maoRecList=>" + maoRecList);
		System.out.println("justifyIdList=>" + justifyIdList);
		System.out.println("ada_appr=>" + ada_appr);
		System.out.println("district=>" + district);
		System.out.println("mandal=>" + mandal);
		System.out.println("rbkusersList=>" + rbkusersList);
		System.out.println("empcodeList=>" + empcodeList);

		String updateQry = "update ecrop2024.emp_rbk_map_incharges set ts_dao_appr=now(),dao_appr=?,dao_remarks=? where  ada_appr=? and dcode=? and mcode=? and rbkuserid=? and  cast( empcode as character varying )=?";
		Query sql = entityManager.createNativeQuery(updateQry);
		sql.setParameter(1, maoRecList);
		sql.setParameter(2, justifyIdList);
		sql.setParameter(3, ada_appr);
		sql.setParameter(4, district);
		sql.setParameter(5, mandal);
		sql.setParameter(6, rbkusersList);
		sql.setParameter(7, empcodeList);
		int executeUpdate = sql.executeUpdate();
		return executeUpdate;
	}

	@Transactional
	public int updateRBK(Integer empcodeList, Integer rbkusersList) {
		String updateQry = " update ecrop2024.emp_rbk_map set incharge_sts='I',empcode=?  where rbkcode=?";
		Query sql = entityManager.createNativeQuery(updateQry);
		sql.setParameter(1, empcodeList);
		sql.setParameter(2, rbkusersList);

		int executeUpdate = sql.executeUpdate();
		return executeUpdate;

	}

	@Transactional
	public int inslogs(Integer dcode, Integer mcode, String rbkusersList, String empcodeList, String rbkusersList2,
			Integer wbdcode, Integer wbmcode, String inchargests) {
		String insertQry = "INSERT INTO ecrop2024.emp_rbk_map_logs dcode, mcode,  rbkcode, empcode, ts, rbkuserid, ipaddress, wbdcode, wbmcode, incharge_sts) values( ?, ?,  ?, ?, now(), ?, ?, ?, ?, 'I')";
		Query sql = entityManager.createNativeQuery(insertQry);
		sql.setParameter(1, dcode);
		sql.setParameter(2, mcode);
		sql.setParameter(3, rbkusersList);
		sql.setParameter(4, empcodeList);
		sql.setParameter(5, rbkusersList2);
		sql.setParameter(6, wbdcode);
		sql.setParameter(7, wbmcode);
		sql.setParameter(8, inchargests);
		int executeUpdate = sql.executeUpdate();
		return executeUpdate;
	}

	@Transactional
	public int updateinch(Integer rbkusersList) {
		String updateQry = "update ecrop2024.emp_rbk_map_logs set to_date=now()  where rbkcode=? and incharge_sts='R' ";
		Query sql = entityManager.createNativeQuery(updateQry);
		sql.setParameter(1, rbkusersList);

		int executeUpdate = sql.executeUpdate();
		return executeUpdate;

	}

	@Transactional
	public int updateinch2(Integer rbkusersList) {
		String updateQry = "update ecrop2024.emp_rbk_map_logs set to_date=now()  where rbkcode=? and incharge_sts='I' ";
		Query sql = entityManager.createNativeQuery(updateQry);
		sql.setParameter(1, rbkusersList);

		int executeUpdate = sql.executeUpdate();
		return executeUpdate;

	}

	@Transactional
	public int updateinch3(Integer empcodeList, Integer rbkusersList) {
		String updateQry = "update ecrop2024.emp_rbk_map_logs set incharge_sts='I',empcode=?  where rbkcode=?  ";
		Query sql = entityManager.createNativeQuery(updateQry);
		sql.setParameter(1, empcodeList);
		sql.setParameter(2, rbkusersList);

		int executeUpdate = sql.executeUpdate();
		return executeUpdate;

	}

	@Transactional
	public int inslogs2(Integer rbkusersList, Integer empcodeList) {
		String insertQry = "INSERT INTO ecrop2024.emp_rbk_map_logs( dcode, mcode, vcode, rbkcode, empcode, ts, rbkuserid, ipaddress, wbdcode, wbmcode, incharge_sts, to_date)SELECT dcode, mcode, vcode, rbkcode, empcode, ts, rbkuserid, ipaddress, wbdcode, wbmcode, incharge_sts,now() FROM ecrop2024.emp_rbk_map where rbkcode=? and empcode=? ";
		Query sql = entityManager.createNativeQuery(insertQry);
		sql.setParameter(1, rbkusersList);
		sql.setParameter(2, empcodeList);
		int executeUpdate = sql.executeUpdate();
		return executeUpdate;
	}

	@Transactional
	public int inslogs3(Integer empcodeList, Integer rbkusersList) {
		String insertQry = "INSERT INTO ecrop2024.emp_rbk_map(dcode, mcode,  rbkcode, empcode, ts, rbkuserid, ipaddress, wbdcode, wbmcode, incharge_sts) select dcode,mcode,rbkcode,empcode,now(),rbkuserid,ipaddress,wbdcode,wbmcode,inchargests from  ecrop2024.emp_rbk_map_incharges where inchargests='I' and dao_appr ='A' and rbkcode not in (select rbkcode from ecrop2024.emp_rbk_map) and empcode=? and rbkcode=? ";
		Query sql = entityManager.createNativeQuery(insertQry);
		sql.setParameter(1, empcodeList);
		sql.setParameter(2, rbkusersList);
		int executeUpdate = sql.executeUpdate();
		return executeUpdate;
	}

	@Transactional
	public int inslogs4(Integer rbkusersList, Integer empcodeList) {
		String insertQry = "INSERT INTO ecrop2024.emp_rbk_map_logs( dcode, mcode, vcode, rbkcode, empcode, ts, rbkuserid, ipaddress, wbdcode, wbmcode, incharge_sts) SELECT dcode, mcode, vcode, rbkcode, empcode, ts, rbkuserid, ipaddress, wbdcode, wbmcode, incharge_sts  FROM ecrop2024.emp_rbk_map where rbkcode=? and empcode=?   ";
		Query sql = entityManager.createNativeQuery(insertQry);
		sql.setParameter(1, rbkusersList);
		sql.setParameter(2, empcodeList);
		int executeUpdate = sql.executeUpdate();
		return executeUpdate;
	}

	@Transactional
	public int updatelogsfinal(Integer rbkcode) {
		String updateQry = " update ecrop2024.emp_rbk_map_logs set to_date=now()  where rbkcode=? ";
		Query sql = entityManager.createNativeQuery(updateQry);
		sql.setParameter(1, rbkcode);

		int executeUpdate = sql.executeUpdate();
		return executeUpdate;

	}

//	MAO LOGIN FORWADING TO ADA AS BUILD STATUS OF EMPLOYEE
	@Transactional
	public int updEmpAadh(Integer empCodesLst, String empUidLst, Integer dcode, Integer mcode, Integer VcodeLst) {
		String updateQry = "UPDATE rbk_status SET  emp_code=?, emp_uid=?  WHERE dcode=? and mcode=? and vscode=?";
		Query sql = entityManager.createNativeQuery(updateQry);
		sql.setParameter(1, empCodesLst);
		sql.setParameter(2, empUidLst);
		sql.setParameter(3, dcode);
		sql.setParameter(4, mcode);
		sql.setParameter(5, VcodeLst);

		int executeUpdate = sql.executeUpdate();
		return executeUpdate;

	}

	@Transactional
	public int updFrdtoAda(String adauserid, Integer dcode, Integer mcode, Integer vcodes) {
		String updateQry = "UPDATE rbk_status SET  mao_frwd='Y',ada_userid=?  WHERE dcode=? and mcode=? and vscode=?";
		Query sql = entityManager.createNativeQuery(updateQry);
		sql.setParameter(1, adauserid);
		sql.setParameter(2, dcode);
		sql.setParameter(3, mcode);
		sql.setParameter(4, vcodes);

		int executeUpdate = sql.executeUpdate();
		return executeUpdate;

	}

	@Transactional
	public int updVroAadh(String AadhaarList, String district, String mandal, String UseridList, String AadhaarList1,
			String MobileList) {
		String updateQry = "update  user_registration set tempuid=null,aadhaar_id=?  where district=? and blockortehsil=? and userid=? and  tempuid=? and mobile_phone=?  and type_user='30' and status='A'";
		Query sql = entityManager.createNativeQuery(updateQry);
		sql.setParameter(1, AadhaarList);
		sql.setParameter(2, district);
		sql.setParameter(3, mandal);
		sql.setParameter(4, UseridList);
		sql.setParameter(5, AadhaarList1);
		sql.setParameter(6, MobileList);

		int executeUpdate = sql.executeUpdate();
		return executeUpdate;

	}

	@Transactional
	public int updRbkAadh(String AadhaarList, String district, String mandal, String UseridList, String AadhaarList1,
			String MobileList) {
		String updateQry = "update  user_registration set tempuid=null,aadhaar_id=?  where district=? and blockortehsil=? and userid=? and  tempuid=? and mobile_phone=?  and type_user='25' and status='A'";
		Query sql = entityManager.createNativeQuery(updateQry);
		sql.setParameter(1, AadhaarList);
		sql.setParameter(2, district);
		sql.setParameter(3, mandal);
		sql.setParameter(4, UseridList);
		sql.setParameter(5, AadhaarList1);
		sql.setParameter(6, MobileList);

		int executeUpdate = sql.executeUpdate();
		return executeUpdate;

	}
}
