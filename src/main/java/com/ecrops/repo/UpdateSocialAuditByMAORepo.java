package com.ecrops.repo;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
public class UpdateSocialAuditByMAORepo {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public int updateSocialAuditByMAODetails(Integer activeYear, String season, String bkId, String cropcode, String crno, 
			String sowdt, String khata, String surveyNo, String varietyCode, String apprStatus, 
			String userid, String ipaddress, String wbvillcode, String pwbdcode, String pwbmcode) {
		
		int executeUpdate = 0;
        
		activeYear = 2023;
        season = "R";
        boolean flag2 = true;
//        activeYear = activeYear;
        
        String farmerGrievTab="ecrop"+activeYear+"."+"farmer_grievances";
        
        String updateQuery = "update "+farmerGrievTab+" set upd_time=now(),mao_remarks=?, updatedby=? "
        		+ "where bookingid=? and cr_sow_date=? and cr_crop=? and  cr_no=? and variety=? and kh_no=?  and cr_vcode=?";
        Query updateSQL = entityManager.createNativeQuery(updateQuery);

        if (apprStatus.equals("A")) {
        	updateSQL.setParameter(1, apprStatus);
        } else if (apprStatus.equals("D")) {
        	updateSQL.setParameter(1, apprStatus);
        }
        
       	 updateSQL.setParameter(2, userid);
       	 updateSQL.setParameter(3, Integer.parseInt(bkId));
       	 updateSQL.setParameter(4, Date.valueOf(sowdt));
       	 updateSQL.setParameter(5, Integer.parseInt(cropcode));
       	 updateSQL.setParameter(6, crno);
       	 updateSQL.setParameter(7, Integer.parseInt(varietyCode));
       	 updateSQL.setParameter(8, Integer.parseInt(khata));
       	updateSQL.setParameter(9, Integer.parseInt(wbvillcode));
       	
       	 executeUpdate = updateSQL.executeUpdate();
		
		return executeUpdate;
		
	}
	
}
