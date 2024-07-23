package com.ecrops.repo;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UpdateSocialAuditByMRORepo {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Transactional
	public String updateSocialAuditByMRODetails(Integer activeYear, Integer dcode, String mcode, String season, String bkId, String cropcode, String crno, 
			String sowdt, String khata, String surveyNo, String varietyCode, String apprStatus, 
			String userid, String ipaddress, String wbvillcode, String pwbdcode, String pwbmcode, String grievId) {
		
		
		int executeUpdate = 0, executeUpdate2 = 0;
		String Status = "";
        
//        String tab = "ecrop"+activeYear+"."+"cr_details_" + season + dcode + activeYear;
        String tab = "cr_details_" + season + dcode + activeYear;
        
        if (apprStatus.equals("A")) {
        	
            if (!isDataPresentInSurveyTable(Integer.parseInt(bkId), Date.valueOf(sowdt), Integer.parseInt(cropcode))) {
            	String updateQuery = "  update " + tab + " a set occupname=case when b.farmername_sug is not null then b.farmername_sug else occupname end,\n"
                        + "  occupfname=case when b.fathername_sug is not null then b.fathername_sug else occupfname end,\n"
                        + " cr_crop=case when (a.cr_crop<> b.cr_crop_sug AND b.cr_crop_sug<>0)  then b.cr_crop_sug else a.cr_crop end,\n"
                        + " variety=case when (a.variety <> b.variety_sug AND b.variety_sug<>0) then b.variety_sug else a.variety end,\n"
                        + " cr_mix_unmix_ext=case when (a.cr_mix_unmix_ext <>b.extent_sug AND extent_sug<>0) then b.extent_sug else a.cr_mix_unmix_ext end,\n"
                        + " cr_w_draw=case when (a.cr_w_draw<>b.cr_w_draw_sug AND b.cr_w_draw_sug!=0 ) then b.cr_w_draw_sug else a.cr_w_draw end,\n"
                        + " cr_sow_date=case when (a.cr_sow_date<>b.cr_sow_date_sug AND b.cr_sow_date_sug is not null) then b.cr_sow_date_sug else a.cr_sow_date end,\n"
                        + " cropseed_scheme=case when (a.cropseed_scheme<>b.cropschtype_sug AND b.cropschtype_sug  is not null ) then b.cropschtype_sug else a.cropseed_scheme end , \n"
                        + " cr_sow_type=case when b.cr_sow_type_sug <> 0 then b.cr_sow_type_sug else a.cr_sow_type end,\n"
                        + " cr_no=case when (b.cr_no_sug <> '0' AND b.cr_no_sug is not null) then b.cr_no_sug else a.cr_no end\n"
                        + " from farmer_grievances b where a.cr_dist_code=b.cr_dist_code and a.bookingid=b.bookingid and \n"
                        + " a.cr_crop=b.cr_crop and a.cr_sow_date=b.cr_sow_date  and a.bookingid=? and a.cr_sow_date=? and a.cr_crop=? and a.kh_no=? and a.cr_vcode=? ";
            	
            	Query updateSQL = entityManager.createNativeQuery(updateQuery);
            	
            	updateSQL.setParameter(1, Integer.parseInt(bkId));
            	updateSQL.setParameter(2, Date.valueOf(sowdt));
            	updateSQL.setParameter(3, Integer.parseInt(cropcode));
            	updateSQL.setParameter(4, Integer.parseInt(khata));
            	updateSQL.setParameter(5, Integer.parseInt(wbvillcode));
            	
            	executeUpdate = updateSQL.executeUpdate();
            	
            }
     
        }
        
        String updateQuery2 = " UPDATE public.farmer_grievances\n"
                + "	SET  mro_upd_time=now(), mro_userid=?,  mro_remarks=?	\n"
                + "	WHERE  bookingid=? and cr_dist_code=? and cr_mand_code=? and cr_vcode=? and cr_sow_date=?  and kh_no=? and cr_crop=? and grievance_id=? ";
        
        Query updateSQL2 = entityManager.createNativeQuery(updateQuery2);
    	
        updateSQL2.setParameter(1, userid);
        updateSQL2.setParameter(2, apprStatus);
        updateSQL2.setParameter(3, Integer.parseInt(bkId));
        updateSQL2.setParameter(4, dcode);
        updateSQL2.setParameter(5, Integer.parseInt(mcode));
        updateSQL2.setParameter(6, Integer.parseInt(wbvillcode));
        updateSQL2.setParameter(7, Date.valueOf(sowdt));
        updateSQL2.setParameter(8, Integer.parseInt(khata));
        updateSQL2.setParameter(9, Integer.parseInt(cropcode));
        updateSQL2.setParameter(10, Integer.parseInt(grievId));
    	
    	executeUpdate2 = updateSQL2.executeUpdate();
        
    	if(apprStatus.equals("A")) {
    		if (executeUpdate > 0 && executeUpdate2 > 0) {
    			Status = "Data Updated Successfully";
    		}
    	}else if (apprStatus.equals("D")) {
    		if (executeUpdate2 > 0) {
        		Status = "Data Updated Successfully";
    		}
    	} else {
    		Status = "Data Failed to Update";
    	}
 
		
		return Status;
		
	}
	
	public boolean isDataPresentInSurveyTable(Integer bookingId, Date sow_Date, Integer cropCode) {
	    String sql = "select COUNT(*) from vcbicrdt_multi where bookingid= "+bookingId+" and cr_sow_date= "+sow_Date+" and cr_crop= "+cropCode;   
	    Integer count = jdbcTemplate.queryForObject(sql, new Object[] {}, Integer.class);
	    return count != null && count > 0;
	}
}
