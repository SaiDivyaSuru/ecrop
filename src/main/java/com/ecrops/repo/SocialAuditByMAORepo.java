package com.ecrops.repo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.ecrops.dto.SocialAuditByMROMAOPojo;

@Repository
public class SocialAuditByMAORepo {

	@PersistenceContext
	EntityManager entityManager;

	public List<SocialAuditByMROMAOPojo> viewCropBookedDetailsByMAO(Integer dcode, Integer mcode, String crDetailsTab,
			String faremrGrievTab) {
		
		
		String var = "select c.griev_comp,cs.cropschdesc,c.cr_w_draw,crpn.description,wat.wsrcdesc,mismatch,c.cr_sow_type,c.tot_extent, c.cr_dist_code,c.cr_mand_code,c.cr_vcode,"
				+ " cn.cropname,v.varietyname,w.wbdname, w.wbmname, crn.naturedesc,w.wbvname,s.bookingid,c.cropseed_scheme,c.cr_sno,c.kh_no,c.cr_crop,c.cr_no,c.variety,"
				+ " occupname, supercheck_userid, occupfname, cr_farmeruid, c.cr_mix_unmix_ext, c.cr_sow_date, cropschtype_sug,"
				+ " cr_no_sug, variety_sug, coalesce(farmername_sug,'') as fname_sug, coalesce(fathername_sug,'') as ffname_sug, extent_sug, cr_w_draw_sug, cr_sow_type_sug, "
				+ " cr_crop_sug,cr_sow_date_sug ,mark_delet as social_status " + " from  " + crDetailsTab
				+ " c  inner join " + faremrGrievTab
				+ " s on s.cr_dist_code=c.cr_dist_code and s.cr_mand_code=c.cr_mand_code and s.cr_vcode=c.cr_vcode and s.kh_no=c.kh_no and s.bookingid=c.bookingid"
				+ " and  s.cr_crop=c.cr_crop and s.cr_no=c.cr_no and s.variety=c.variety and s.cr_sow_date=c.cr_sow_date"
				+ " inner join cropnames cn on c.cr_crop=cn.cropid inner join cr_variety_master v on c.cr_crop=v.cropcode and c.variety=v.varietycode"
				+ " inner join wbvillage_mst w on c.cr_vcode=w.wbvcode inner join waterresources wat on c.cr_w_draw=wat.wsrcid inner join cropnature crn on c.cr_sow_type=crn.id"
				+ " inner join cropseed_scheme cs on c.cropseed_scheme=cs.cropschtype  inner join cropnumber crpn on c.cr_no = cast(crpn.id as text)"
				+ " where s.cr_dist_code="+dcode+" and s.cr_mand_code="+mcode+" and (s.mao_remarks is null or (resubmitbyva='Y' and mao_remarks='D')) order by wbmname,wbvname,kh_no ";

		
		List<SocialAuditByMROMAOPojo> pojo = new ArrayList<>();

		Query query = entityManager.createNativeQuery(var);
		List<Object> objects = query.getResultList();

		if (objects != null && objects.size() > 0) {

			for (Object audit : objects) {

				Object[] row = (Object[]) audit;

				SocialAuditByMROMAOPojo pojos = new SocialAuditByMROMAOPojo();
				
				pojos.setGriev_comp((Character) row[0]);
				pojos.setCropschdesc(row[1].toString());
				pojos.setCr_w_draw((BigDecimal) row[2]);
				pojos.setDescription(row[3].toString());
				pojos.setWsrcdesc(row[4].toString());
				if (row[5] != null) {
					pojos.setMismatch(row[5].toString());
				}
				pojos.setCr_sow_type((Integer) row[6]);
				pojos.setTot_extent((BigDecimal) row[7]);
				pojos.setCr_dist_code((BigDecimal) row[8]);
				pojos.setCr_mand_code((BigDecimal) row[9]);
				pojos.setCr_vcode((Integer) row[10]);
				pojos.setCropname(row[11].toString());
				pojos.setVarietyname(row[12].toString());
				pojos.setWbdname(row[13].toString());
				pojos.setWbmname(row[14].toString());
				pojos.setNaturedesc(row[15].toString());
				pojos.setWbvname(row[16].toString());
				pojos.setBookingid((Integer) row[17]);
				pojos.setCropseed_scheme((Character) row[18]);
				pojos.setCr_sno(row[19].toString());
				pojos.setKh_no((BigDecimal) row[20]);
				pojos.setCr_crop((Integer) row[21]);
				pojos.setCr_no(row[22].toString());
				pojos.setVariety((Integer) row[23]);
				pojos.setOccupname(row[24].toString());
				if (row[25] != null) {
					pojos.setSupercheck_userid(row[25].toString());
				}
				pojos.setOccupfname(row[26].toString());
				pojos.setCr_farmeruid(row[27].toString());
				pojos.setCr_mix_unmix_ext((BigDecimal) row[28]);
				pojos.setCr_sow_date((Date) row[29]);
				
				
				
				if (row[30] != null) {
					pojos.setCropschtype_sug((Character) row[30]);
				}
				if (row[31] != null) {
					pojos.setCr_no_sug(row[31].toString());
				}
				
				if ((Integer) row[32] == 0 ) {
					pojos.setVariety_sug("");
				} else {
					int varietyCode = (Integer) row[32];
					String varietyCodeVar = "select varietyname from public.cr_variety_master where varietycode = '"+varietyCode+"'";
					Query varietyCodeQuery = entityManager.createNativeQuery(varietyCodeVar);
					String varietyName = (String) varietyCodeQuery.getSingleResult();
					pojos.setVariety_sug(varietyName);
				}
				
				pojos.setFname_sug(row[33].toString());
				pojos.setFfname_sug(row[34].toString());
				pojos.setExtent_sug((BigDecimal) row[35]);
				
//				pojos.setCr_w_draw_sug((Integer) row[36]);    // select wsrcdesc from waterresources where wsrcid =    
//				select naturedesc from cropnature  where id = 
				if ((Integer) row[36] == 0 ) {
					pojos.setCr_w_draw_sug("");
				} else {
					int sourceIrrCode = (Integer) row[36];
					String sourceIrrCodeVar = "select wsrcdesc from waterresources where wsrcid = "+sourceIrrCode;
					Query sourceIrrCodeQuery = entityManager.createNativeQuery(sourceIrrCodeVar);
					String sourceIrrName = (String) sourceIrrCodeQuery.getSingleResult();
					pojos.setCr_w_draw_sug(sourceIrrName);
				}
				
				if (row[37] != null) {
					pojos.setCr_sow_type_sug((Integer) row[37]);
				}
				
				if ((Integer) row[38] == 0 ) {
					pojos.setCr_crop_sug("");
				} else {
					int cropCode = (Integer) row[38];
					String cropCodeVar = "select cropname  from public.cropnames where cropid = "+cropCode;
					Query cropCodeQuery = entityManager.createNativeQuery(cropCodeVar);
					String CropName = (String) cropCodeQuery.getSingleResult();
					pojos.setCr_crop_sug(CropName);
				}
				
				if (row[39] != null) {
					pojos.setCr_sow_date_sug((Date) row[39]);
				}
				if (row[40] != null) {
					pojos.setSocial_status((Character) row[40]);
				}
				pojo.add(pojos);
				
			}
		}

		return pojo;
	}

}
