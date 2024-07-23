package com.ecrops.partition;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ecrops.entity.ResurveyStatusDMV;
@Repository
@Transactional
public class ResurveyStatusDMVPartition {

	@PersistenceContext
	private EntityManager entityManager;
	public List<ResurveyStatusDMV> getResurveyStatus(String role,String wbdcode,String wbmcode) {
		System.out.println("wbmcode==================>"+wbmcode);

			String tableName = "wbvillage_mst" ;
			System.out.println("tableName---------------->" + tableName);
		if(role.equals("17") || role.equals("48")) {
		String Sql = "select wbdname,wbmname,wbvname,wbedname,wbemname,wbevname,resurveyed from "+tableName+"  where resurveyed='Y'";
		Query insertQuery = (Query) entityManager.createNativeQuery(Sql);
//		insertQuery.setParameter(1, Integer.parseInt(crop));
//		insertQuery.setParameter(2, Integer.parseInt(wbdcode));
//		insertQuery.setParameter(3, Integer.parseInt(wbmcode));
//		insertQuery.setParameter(4, vscode);
		
		List<Object[]> detailsEntities1 = insertQuery.getResultList();
		List<ResurveyStatusDMV> detailsEntities = new ArrayList<ResurveyStatusDMV>();

		for (Object[] row : detailsEntities1) {

			ResurveyStatusDMV entity = new ResurveyStatusDMV();

			entity.setWbdname((String) row[0]);
			entity.setWbmname((String) row[1]); 
			entity.setWbvname((String) row[2]);
			entity.setWbedname((String) row[3]);
			entity.setWbemname((String) row[4]);//System.out.println(""+row[1]);
			entity.setWbevname((String) row[5]);//System.out.println(""+row[1]);
			entity.setResurveyed((Character) row[6]);//System.out.println(""+row[1]);
			detailsEntities.add(entity);

		}

		return detailsEntities;

	}else {
		
		
			String Sql = "select wbdname,wbmname,wbvname,wbedname,wbemname,wbevname,resurveyed from "+tableName+"  where "
					+ "resurveyed='Y' and wbdcode=? and wbmcode=?";
			Query insertQuery = (Query) entityManager.createNativeQuery(Sql);
			insertQuery.setParameter(1, Integer.parseInt(wbdcode));System.out.println("wbdcode================>"+Integer.parseInt(wbdcode));
			insertQuery.setParameter(2, Integer.parseInt(wbmcode));System.out.println("wbmcode================>"+Integer.parseInt(wbmcode));
			
			List<Object[]> detailsEntities1 = insertQuery.getResultList();
			List<ResurveyStatusDMV> detailsEntities = new ArrayList<ResurveyStatusDMV>();

			for (Object[] row : detailsEntities1) {

				ResurveyStatusDMV entity = new ResurveyStatusDMV();

				entity.setWbdname((String) row[0]);
				entity.setWbmname((String) row[1]); 
				entity.setWbvname((String) row[2]);
				entity.setWbedname((String) row[3]);
				entity.setWbemname((String) row[4]);//System.out.println(""+row[1]);
				entity.setWbevname((String) row[5]);//System.out.println(""+row[1]);
				entity.setResurveyed((Character) row[6]);//System.out.println(""+row[1]);
				detailsEntities.add(entity);

			}

			return detailsEntities;	
		
	}
		
	}	

}
