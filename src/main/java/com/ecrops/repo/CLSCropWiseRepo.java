package com.ecrops.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ecrops.entity.CLSCropWisePojo;

@Repository
public class CLSCropWiseRepo {

	@PersistenceContext
	EntityManager entityManager;

	public List<CLSCropWisePojo> getCropImagesDatas() {

		String var = "SELECT count(DISTINCT cr_vcode) AS rbk_count, 'Disease' AS cropname, "
				+ "count(*) FILTER (WHERE drc_appr_status IN ('A', 'P', 'R')) AS photosuploaded, "
				+ "count(*) FILTER (WHERE drc_appr_status = 'A') AS approvedbydrc, "
				+ "count(*) FILTER (WHERE ps_appr_status = 'A') AS approvedbyps "
				+ "FROM cropimages.cr_disease_images a, public.cropnames b "
				+ "WHERE a.cropcode = b.cropid AND b.cropnature='A' " + "UNION ALL "
				+ "SELECT count(DISTINCT cr_vcode) AS rbk_count, 'LifeSpan' AS cropname, "
				+ "count(*) FILTER (WHERE drc_appr_status IN ('A', 'P', 'R')) AS photosuploaded, "
				+ "count(*) FILTER (WHERE drc_appr_status = 'A') AS approvedbydrc, "
				+ "count(*) FILTER (WHERE ps_appr_status = 'A') AS approvedbyps "
				+ "FROM cropimages.cr_lifespan_images a, public.cropnames b "
				+ "WHERE a.cropcode = b.cropid AND b.cropnature='A' " + "UNION ALL "
				+ "SELECT count(DISTINCT cr_vcode) AS rbk_count, 'NutriDeficiency' AS cropname, "
				+ "count(*) FILTER (WHERE drc_appr_status IN ('A', 'P', 'R')) AS photosuploaded, "
				+ "count(*) FILTER (WHERE drc_appr_status = 'A') AS approvedbydrc, "
				+ "count(*) FILTER (WHERE ps_appr_status = 'A') AS approvedbyps "
				+ "FROM cropimages.cr_nutrideficiency_images a, public.cropnames b "
				+ "WHERE a.cropcode = b.cropid AND b.cropnature='A' " + "UNION ALL "
				+ "SELECT count(DISTINCT cr_vcode) AS rbk_count, 'Pest' AS cropname, "
				+ "count(*) FILTER (WHERE drc_appr_status IN ('A', 'P', 'R')) AS photosuploaded, "
				+ "count(*) FILTER (WHERE drc_appr_status = 'A') AS approvedbydrc, "
				+ "count(*) FILTER (WHERE ps_appr_status = 'A') AS approvedbyps "
				+ "FROM cropimages.cr_pest_images a, public.cropnames b "
				+ "WHERE a.cropcode = b.cropid AND b.cropnature='A'";

		List<CLSCropWisePojo> pojo = new ArrayList<>();

		Query query = entityManager.createNativeQuery(var);
		List<Object[]> objects = query.getResultList();

		int totalrbks = 0;
		int totalPhotosUploaded = 0;
		int totalDrcApprStatus = 0;
		int totalPsApprStatus = 0;

		if (objects != null && !objects.isEmpty()) {
			for (Object eol : objects) {
				Object[] row = (Object[]) eol;
				CLSCropWisePojo pojos = new CLSCropWisePojo();

				int rbks = Integer.parseInt(row[0].toString());
				pojos.setCr_vcode(row[0].toString());

				pojos.setCropnature(row[1].toString());

				int photosUploaded = Integer.parseInt(row[2].toString());
				int drcApprStatus = Integer.parseInt(row[3].toString());
				int psApprStatus = Integer.parseInt(row[4].toString());

				pojos.setPhotosuploaded(row[2].toString());
				pojos.setDrc_appr_status(row[3].toString());
				pojos.setPs_appr_status(row[4].toString());

				System.out.println(row[4].toString());

				pojo.add(pojos);

				totalrbks += rbks;
				totalPhotosUploaded += photosUploaded;
				totalDrcApprStatus += drcApprStatus;
				totalPsApprStatus += psApprStatus;
			}

			CLSCropWisePojo totalsPojo = new CLSCropWisePojo();
			totalsPojo.setTotalrbks(totalrbks);
			totalsPojo.setTotalPhotosUploaded(totalPhotosUploaded);
			totalsPojo.setTotalDrcApprStatus(totalDrcApprStatus);
			totalsPojo.setTotalPsApprStatus(totalPsApprStatus);
			pojo.add(totalsPojo);

			System.out.println("Total Photos Uploaded: " + totalPhotosUploaded);
			System.out.println("Total DRC Approval Status: " + totalDrcApprStatus);
			System.out.println("Total PS Approval Status: " + totalPsApprStatus);
		}

		return pojo;
	}

	public List<CLSCropWisePojo> getCropMandalImagesDatas(String param) {
		System.out.println("param------------------------------------------->"+param);
	    String var = "SELECT a.wbdcodenew AS dcode, c.dname AS dname, mname, b.mcode AS mcode, vcode, vname " +
	                 "FROM public.mandal_2011_cs a " +
	                 "JOIN public.vill_sec_det b ON a.dcode = b.dcode AND a.mcode = b.mcode " +
	                 "JOIN public.district_2011_cs c ON a.dcode = c.dcode order by dcode";

	    List<CLSCropWisePojo> pojoList = new ArrayList<>();	   
	    Query query = entityManager.createNativeQuery(var);
	    List<Object[]> objects = query.getResultList();
	    
	    if (objects != null && !objects.isEmpty()) {
	        for (Object obj : objects) {
	            Object[] row = (Object[]) obj;
	            CLSCropWisePojo pojo = new CLSCropWisePojo();
	            Integer dcode = (Integer) row[0];
	            Integer mcode = (Integer) row[3];
	            Integer vcode = (Integer) row[4];
	            System.out.println("Initial values - dcode: " + dcode + ", mcode: " + mcode + ", vcode: " + vcode);
	            if (dcode == null) {
	                System.out.println("Skipping entry due to null dcode.");
	                continue; 
	            }
	            pojo.setDcode(dcode);
	            pojo.setDname((String) row[1]);
	            pojo.setMname((String) row[2]);
	            pojo.setMcode(mcode);
	            pojo.setVcode(vcode);
	            pojo.setVname((String) row[5]);
	            String secondQuery = "SELECT COUNT(*) AS photosuploaded, " +
	                                 "COUNT(*) FILTER (WHERE drc_appr_status = 'A') AS drcapproved, " +
	                                 "COUNT(*) FILTER (WHERE ps_appr_status = 'A') AS psapproved " +
	                                 "FROM cropimages.cr_pest_images a " +
	                                 "JOIN public.cropnames b ON a.cropcode = b.cropid " +
	                                 "WHERE b.cropnature = 'A' AND cr_dist_code = :dcode " +
	                                 "AND cr_mand_code = :mcode AND cr_vcode = :vcode";

	            Query secondQueryObject = entityManager.createNativeQuery(secondQuery);
	            secondQueryObject.setParameter("dcode", dcode);
	            secondQueryObject.setParameter("mcode", mcode);
	            secondQueryObject.setParameter("vcode", vcode);
	            System.out.println("secondQuery----->"+secondQuery);
	            List<Object[]> secondQueryResults = secondQueryObject.getResultList();
	            if (secondQueryResults != null && !secondQueryResults.isEmpty()) {
	                Object[] resultRow = secondQueryResults.get(0);

	                if (resultRow != null && resultRow.length >= 3) {
	                    int photosUploaded = (int) (resultRow[0] != null ? ((Number) resultRow[0]).longValue() : 0L);
	                    int drcApproved = (int) (resultRow[1] != null ? ((Number) resultRow[1]).longValue() : 0L);
	                    int psApproved = (int) (resultRow[2] != null ? ((Number) resultRow[2]).longValue() : 0L);

	                    System.out.println("photosUploaded: " + photosUploaded);
	                    System.out.println("drcApproved: " + drcApproved);
	                    System.out.println("psApproved: " + psApproved);

	                   
	                    pojo.setPhotosuploaded(String.valueOf(photosUploaded));
	                    pojo.setDrc_appr_status(String.valueOf(drcApproved));
	                    pojo.setPs_appr_status(String.valueOf(psApproved));
	                }
	            }
	            pojoList.add(pojo);
	        }
	    }

	    return pojoList;
	}
}
