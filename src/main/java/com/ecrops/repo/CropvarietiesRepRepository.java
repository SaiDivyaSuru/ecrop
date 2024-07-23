package com.ecrops.repo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.ecrops.entity.CropvarietiesRep;

@Repository
public class CropvarietiesRepRepository {

	@PersistenceContext
	private EntityManager entitymanger;

	public List<CropvarietiesRep> getCropVarieties1(int grpcode) {
		
		
		String qry = "select cropname,\r\n"
				+ "a.varietycode,\r\n"
				+ "a.varietyname,\r\n"
				+ "a.cropcode,\r\n"
				+ "a.releasedays,\r\n"
				+ "a.newreleasedays,\r\n"
				+ "grpname,cropgrpid,\r\n"
				+ "cropnameeng \r\n"
				+ "from cr_variety_master a, cropnames b, cropgroups c \r\n"
				+ "where a.cropcode=b.cropid and b.grpcode=c.cropgrpid and c.cropgrpid=?  and  b.cropnature in('A','H','S','R') and status='A' \r\n"
				+ "order by grpname,cropname,a.varietyname";
		
		Query query = entitymanger.createNativeQuery(qry);
		System.out.println("qry-------->"+qry);
		
		query.setParameter(1, grpcode);
		
		List<Object[]> result = query.getResultList();

		List<CropvarietiesRep> list = new ArrayList<CropvarietiesRep>();
		
		for (Object[] bean : result) {
		    CropvarietiesRep cropvarietiesRep = new CropvarietiesRep();

		    cropvarietiesRep.setCropname((String) bean[0]);
		    cropvarietiesRep.setVarietycode((int) bean[1]);
		    cropvarietiesRep.setVarietyname((String) bean[2]);
		    cropvarietiesRep.setCropcode((int) bean[3]);
		    cropvarietiesRep.setReleasedays((int) bean[4]);

		    // Check if bean[5] is null
		    if (bean[5] != null) {
		        cropvarietiesRep.setNewreleasedays((int) bean[5]);
		    } else {
		        cropvarietiesRep.setNewreleasedays(null); // Set to null if bean[5] is null
		    }

		    cropvarietiesRep.setGrpname((String) bean[6]);
		    cropvarietiesRep.setCropgrpid((int) bean[7]);
		    cropvarietiesRep.setCropnameeng((String) bean[8]);

		    list.add(cropvarietiesRep);
		}

		System.out.println("list---------------->" + list);

		return list;
	}
	
public List<CropvarietiesRep> getCropVarieties2() {
		
		
		String qry = "select cropname,\r\n"
				+ "a.varietycode,\r\n"
				+ "a.varietyname,\r\n"
				+ "a.cropcode,\r\n"
				+ "a.releasedays,\r\n"
				+ "a.newreleasedays,\r\n"
				+ "grpname,\r\n"
				+ "cropgrpid,\r\n"
				+ "cropnameeng \r\n"
				+ "from cr_variety_master a, cropnames b, cropgroups c \r\n"
				+ "where a.cropcode=b.cropid and b.grpcode=c.cropgrpid and b.cropnature in('A','H','S','R') and status='A'";
		
		Query query = entitymanger.createNativeQuery(qry);
		System.out.println("qry-------->"+qry);
		
		List<Object[]> result = query.getResultList();

		List<CropvarietiesRep> list = new ArrayList<CropvarietiesRep>();
		
		for (Object[] bean : result) {
		    CropvarietiesRep cropvarietiesRep = new CropvarietiesRep();
		   
		    cropvarietiesRep.setCropname((String) bean[0]);
		    cropvarietiesRep.setVarietycode((int) bean[1]);
		    cropvarietiesRep.setVarietyname((String) bean[2]);
		    cropvarietiesRep.setCropcode((int) bean[3]);
		    cropvarietiesRep.setReleasedays((int) bean[4]);
		    cropvarietiesRep.setNewreleasedays((int) bean[5]);
		    cropvarietiesRep.setGrpname((String) bean[6]);
		    cropvarietiesRep.setCropgrpid((int) bean[7]);
		    cropvarietiesRep.setCropnameeng((String) bean[8]);
		    
		    list.add(cropvarietiesRep);
		}

		System.out.println("list---------------->" + list);

		return list;
	}
}
