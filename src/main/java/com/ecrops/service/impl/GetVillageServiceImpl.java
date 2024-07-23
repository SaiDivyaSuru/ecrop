package com.ecrops.service.impl;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.ecrops.entity.AllocationSurveyNoModel;
import com.ecrops.entity.VillageSec;
import com.ecrops.service.GetVillageService;
@Service
public class GetVillageServiceImpl implements  GetVillageService {

	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public List<VillageSec> getvillagedrpdwn(Integer vscode) {
		// TODO Auto-generated method stub
	String sql = "select vcode, wbvname from ecrop2023.villsec_rev_v where vscode=?";	
		
			Query insertQuery = (Query)entityManager.createNativeQuery(sql);
			insertQuery.setParameter(1,  vscode);
			System.out.println("insertQuery=>"+insertQuery);
			List<Object[]> detailsEntities1 = insertQuery.getResultList();
			System.out.println("detailsEntities=>"+detailsEntities1.size());
			
			List<VillageSec> list = new ArrayList<VillageSec>();
			for(Object[] row: detailsEntities1) {
				VillageSec villSec = new VillageSec();
				villSec.setVcode((Integer) row[0]);
				villSec.setWbvname((String)row[1].toString());
				list.add(villSec);
			}
			System.out.println("list=>"+list.size());
			return list;
	
	}


	@Override
	public String getvillnameForvcode(Integer vcode) {
		// TODO Auto-generated method stub
		 String sql = " SELECT wbvname FROM wbvillage_mst WHERE wbvcode =?";
			
			
		 Query query = (Query) entityManager.createNativeQuery(sql); 
		 
		 
		 
//		    Query query = entityManager.createNativeQuery(sql);
		    
		 query.setParameter(1, vcode);
		    System.out.println("insertQuery=>" + query);
		   
		    try {
		         
				String villageName = (String) query.getSingleResult();
		        return villageName;
		    } catch (NoResultException e) {
		        // Handle case where no village name is found for the given vcode
		        return null; // or throw an exception, depending on your requirements
		    }

	}

	
	
}
