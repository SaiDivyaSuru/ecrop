package com.ecrops.partition;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.ecrops.entity.AuthCropWise;
import com.ecrops.entity.NormalArea;

@Repository
@Transactional
public class NormalAreapartition {

	@PersistenceContext
	private EntityManager entityManager;

	public List<NormalArea> normalarea(String cropyear, String dcode, String cropid) {

		String[] season = cropyear.split("@");
		String seasonType = season[0];
		Integer seasonYear = Integer.parseInt(season[1]);
		String part_key = "", sql = "", tableName = "";

		part_key = seasonType + seasonYear;

		if (seasonYear >= 2023) {
			tableName = "ecrop" + seasonYear + "." + "mand_crop_normalareas_v";
		} else {
			tableName = "mand_crop_normalareas_v";
		}

		System.out.println("tableName---------------->" + tableName);

		sql = " select mname,normalarea,a.mcode,cropcode from " + tableName + " a where \r\n"
				+ "a.dcode=? and cropcode=? and a.cropyear=? and a.season=? order by mname ";

		System.out.println(sql);

		Query sesnyr = (Query) entityManager.createNativeQuery(sql);
		sesnyr.setParameter(1, Integer.parseInt(dcode));
		sesnyr.setParameter(2, Integer.parseInt(cropid));
		sesnyr.setParameter(3, seasonYear);
		sesnyr.setParameter(4, seasonType);

		System.out.println("sesnyr=>" + sesnyr);
		List<Object[]> normal = sesnyr.getResultList();

		List<NormalArea> entityDetails = new ArrayList<NormalArea>();

		for (Object[] row : normal) {

			NormalArea entity = new NormalArea();

			entity.setMname((String) row[0]);
			entity.setNormalarea((Number) row[1]);

			entityDetails.add(entity);

		}

		return entityDetails;

	}

}
