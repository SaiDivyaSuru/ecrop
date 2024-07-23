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
public class SocialAuditByMRORepo {

	@PersistenceContext
	EntityManager entityManager;

	public List<SocialAuditByMROMAOPojo> viewCropBookedDetailsByMRO(Integer dcode, Integer mcode, Integer vcode, String crDetailsTab,
			String faremrGrievTab) {
		
		String var = " select x.grievance_id,x.mao_remarks,w.wsrcdesc as sugwsrc, cr.cropname as sugcropname,v.varietyname as sugvariety,crps.cropschdesc as sugcropsch,cnat.naturedesc as sugcropnature,  "
                + " crn.description as sugcroppatt,  y.naturedesc,y.cropschdesc,y.cr_w_draw,y.wsrcdesc,y.cr_sow_type,y.tot_extent, y.cr_dist_code,y.cr_mand_code,  y.cr_vcode,y.cropname,   "
                + " y.varietyname,y.wbdname, y.wbmname,  y.wbvname ,  y.bookingid,y.description,y.cropseed_scheme, y.cr_sno,y.kh_no,   y.cr_crop,y.cr_no, y.variety, y.occupname,  "
                + " y.occupfname, y.cr_farmeruid,   y.cr_mix_unmix_ext,y.cr_sow_date ,y.griev_comp ,y.mark_delet,  cropschtype_sug,    cr_crop_sug,cr_no_sug, variety_sug, farmername_sug, fathername_sug,  "
                + " extent_sug, cr_w_draw_sug, cr_sow_type_sug, cr_sow_date_sug   from      (select  grievance_id, cr_dist_code,cr_mand_code,cr_vcode ,bookingid, cr_crop,cr_no,variety,cr_sow_date,cropschtype_sug,   "
                + " cr_crop_sug,cr_no_sug, variety_sug, farmername_sug,       fathername_sug, extent_sug, cr_w_draw_sug, cr_sow_type_sug, cr_sow_date_sug,mao_remarks  "
                + " from " + faremrGrievTab + "  where    cr_dist_code="+dcode+" and cr_mand_code="+mcode+"  and cr_vcode="+vcode+" and cropyear=2023 and season='K' and (mro_remarks is null or ( mro_remarks='D' and mro_flag is null)) and mao_remarks ='A')   "
                + "  x     left join   "
                + " (select c.mark_delet,cs.cropschdesc,c.cr_w_draw,wat.wsrcdesc,c.cr_sow_type,c.tot_extent,    c.cr_dist_code,c.cr_mand_code,c.cr_vcode,cn.cropname,  "
                + "  crpnum.description,ocnat.naturedesc, v.varietyname,w.wbdname, w.wbmname,  w.wbvname ,    c.bookingid,c.cropseed_scheme, c.cr_sno,c.kh_no,c.cr_crop,c.cr_no,   "
                + "  c.variety, occupname, occupfname, cr_farmeruid,   c.cr_mix_unmix_ext,  c.cr_sow_date ,  griev_comp    "
                + "  from " + crDetailsTab + "  c  "
                + "  inner join cropnames cn on c.cr_crop=cn.cropid    "
                + "  inner join cr_variety_master v on c.cr_crop=v.cropcode and c.variety=v.varietycode    "
                + " inner join  wbvillage_mst w on c.cr_vcode=w.wbvcode    "
                + "  inner join cropnature ocnat on ocnat.id=c.cr_sow_type   "
                + "  inner join waterresources wat on  c.cr_w_draw=wat.wsrcid      "
                + "  inner join cropseed_scheme cs on c.cropseed_scheme=cs.cropschtype   " 
                + "  inner join cropnumber crpnum on c.cr_no=cast(crpnum.id as text) where c.cr_dist_code="+dcode+" and c.cr_mand_code="+mcode+"  and cr_vcode="+vcode+"  and c.griev_comp is not null) y    "
                + "  on x.bookingid=y.bookingid and x.cr_crop=y.cr_crop and   x.variety=y.variety and  x.cr_sow_date=y.cr_sow_date    "
                + "  left join waterresources w on w.wsrcid=x.cr_w_draw_sug  left join cropnames cr on cr.cropid=x.cr_crop_sug    "
                + "  left join cropseed_scheme crps on   crps.cropschtype=x.cropschtype_sug    "
                + "  left join cropnature cnat on cnat.id=x.cr_sow_type_sug  " 
                + "  left join cropnumber crn on crn.id=cast(x.cr_no_sug as int)  "
                + "  left join cr_variety_master v on v.varietycode=x.variety_sug  order by wbmname,wbvname    ";

		Query query = entityManager.createNativeQuery(var);
		
        List<SocialAuditByMROMAOPojo> pojo = new ArrayList<>();

		List<Object> objects = query.getResultList();

		if (objects != null && objects.size() > 0) {

			for (Object audit : objects) {

				Object[] row = (Object[]) audit;

				SocialAuditByMROMAOPojo pojos = new SocialAuditByMROMAOPojo();
				
				pojos.setGrievance_id((Integer) row[0]);
				pojos.setMao_remarks(row[1].toString());
				if (row[2] != null) {
					pojos.setSugwsrc(row[2].toString());
				}
				if (row[3] != null) {
					pojos.setSugcropname(row[3].toString());
				}
				if (row[4] != null) {
					pojos.setSugvariety(row[4].toString());
				}
				if (row[5] != null) {
					pojos.setSugcropsch(row[5].toString());
				}
				if (row[6] != null) {
					pojos.setSugcropnature(row[6].toString());
				}
				if (row[7] != null) {
					pojos.setSugcroppatt(row[7].toString());
				}
				if (row[8] != null) {
					pojos.setNaturedesc(row[8].toString());
				}
				if (row[9] != null) {
					pojos.setCropschdesc(row[9].toString());
				}
				if (row[10] != null) {
					pojos.setCr_w_draw((BigDecimal) row[10]);
				}
				if (row[11] != null) {
					pojos.setWsrcdesc(row[11].toString());
				}
				if (row[12] != null) {
					pojos.setCr_sow_type((Integer) row[12]);
				}

				pojos.setTot_extent((BigDecimal) row[13]);
				pojos.setCr_dist_code((BigDecimal) row[14]);
				pojos.setCr_mand_code((BigDecimal) row[15]);
				pojos.setCr_vcode((Integer) row[16]);
				if (row[17] != null) {
					pojos.setCropname(row[17].toString());
				}
				if (row[18] != null) {
					pojos.setVarietyname(row[18].toString());
				}
				if (row[19] != null) {
					pojos.setWbdname(row[19].toString());
				}
				if (row[20] != null) {
					pojos.setWbmname(row[20].toString());
				}
				if (row[21] != null) {
					pojos.setWbvname(row[21].toString());
				}
				if (row[22] != null) {
					pojos.setBookingid((Integer) row[22]);
				}
				if (row[23] != null) {
					pojos.setDescription(row[23].toString());
				}
				if (row[24] != null) {
					pojos.setCropseed_scheme((Character) row[24]);
				}
				if (row[25] != null) {
					pojos.setCr_sno(row[25].toString());
				}
				
				pojos.setKh_no((BigDecimal) row[26]);
				pojos.setCr_crop((Integer) row[27]);
				if (row[28] != null) {
					pojos.setCr_no(row[28].toString());
				}
				
				pojos.setVariety((Integer) row[29]);
				if (row[30] != null) {
					pojos.setOccupname(row[30].toString());
				}
				if (row[31] != null) {
					pojos.setOccupfname(row[31].toString());
				}
				if (row[32] != null) {
					pojos.setCr_farmeruid(row[32].toString());
				}
				if (row[33] != null) {
					pojos.setCr_mix_unmix_ext((BigDecimal) row[33]);
				}
				if (row[34] != null) {
					pojos.setCr_sow_date((Date) row[34]);
				}
				if (row[35] != null) {
					pojos.setGriev_comp((Character) row[35]);
				}
				if (row[36] != null) {
					pojos.setMark_delet((Character) row[36]);
				}
				
				if (row[37] != null) {
					pojos.setCropschtype_sug((Character) row[37]);
				}
//				pojos.setCr_crop_sug((Integer) row[38]);
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
					pojos.setCr_no_sug(row[39].toString());
				}
//				pojos.setVariety_sug((Integer) row[40]);
				if ((Integer) row[40] == 0 ) {
					pojos.setVariety_sug("");
				} else {
					int varietyCode = (Integer) row[40];
					String varietyCodeVar = "select varietyname from public.cr_variety_master where varietycode = '"+varietyCode+"'";
					Query varietyCodeQuery = entityManager.createNativeQuery(varietyCodeVar);
					String varietyName = (String) varietyCodeQuery.getSingleResult();
					pojos.setVariety_sug(varietyName);
				}
				if (row[41] != null) {
					pojos.setFname_sug(row[41].toString());
				}
				if (row[42] != null) {
					pojos.setFfname_sug(row[42].toString());
				}
				if (row[43] != null) {
					pojos.setExtent_sug((BigDecimal) row[43]);
				}
//				if (row[44] != null) {
//					pojos.setCr_w_draw_sug((Integer) row[44]);
//				}
				if ((Integer) row[44] == 0 ) {
					pojos.setCr_w_draw_sug("");
				} else {
					int sourceIrrCode = (Integer) row[44];
					String sourceIrrCodeVar = "select wsrcdesc from waterresources where wsrcid = "+sourceIrrCode;
					Query sourceIrrCodeQuery = entityManager.createNativeQuery(sourceIrrCodeVar);
					String sourceIrrName = (String) sourceIrrCodeQuery.getSingleResult();
					pojos.setCr_w_draw_sug(sourceIrrName);
				}
				
				if (row[45] != null) {
					pojos.setCr_sow_type_sug((Integer) row[45]);
				}
				if (row[46] != null) {
					pojos.setCr_sow_date_sug((Date) row[46]);
				}
				
				pojo.add(pojos);
				
			}
		}
		
		return pojo;
	}

}
