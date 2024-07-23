package com.ecrops.repo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import com.ecrops.entity.ActiveseasonFDWSRFAC;
import com.ecrops.entity.DistwiseStatusRepForAllCrops;

@Repository
public class DistwiseStatusRepForAllCropsRepo {

	@PersistenceContext
	private EntityManager entitymanger;

	public List<DistwiseStatusRepForAllCrops> getCropwise(String sescrpyear, int activeYear) {

		String qry = "", cr_season = ",", seasonname = " ";
		int cr_year;
		if ((sescrpyear).equals(null) || (sescrpyear).equals("")) {
			qry = "select * from activeseason where current_season='C'  limit 1";

			Query query1 = entitymanger.createNativeQuery(qry);

			List<Object[]> result1 = query1.getResultList();

			System.out.println("result1-------->" + result1);
			cr_season = result1.get(0)[0].toString();
			cr_year = Integer.parseInt(result1.get(0)[1].toString());
		} else {
			cr_season = sescrpyear.split("@")[0];
			cr_year = Integer.parseInt(sescrpyear.split("@")[1]);

		}
		String table = "distwisestat_" + cr_season + cr_year;
		if (cr_year == activeYear) {
			table = "ecrop2023." + "distwisestat_" + cr_season + cr_year;
		}
		System.out.println(table);

		if (cr_season.equals("R")) {
			seasonname = "Rabi";
		} else if (cr_season.equals("K")) {
			seasonname = "Kharif";
		} else if (cr_season.equals("S")) {
			seasonname = "Summer";
		}

		System.out.println("season---------" + cr_season);
		System.out.println("year---------" + cr_year);
		System.out.println("table---------" + table);

		if (cr_year >= 2023
				&& !(cr_year == 2023 && cr_season.equalsIgnoreCase("S"))) {
			table = "ecrop" + cr_year + "." + "distwisestat_" + cr_season + cr_year;
		}
		qry = "SELECT \r\n"
				+ "    distcode,\r\n"
				+ "    wbdname,\r\n"
				+ "    tmandals,\r\n"
				+ "    tvillages,\r\n"
				+ "    dagri_mandals,\r\n"
				+ "    dagri_villages,\r\n"
				+ "    dagrinormalarea,\r\n"
				+ "    dagri_farmers,\r\n"
				+ "    dagri_ext,\r\n"
				+ "    dhorti_mandals,\r\n"
				+ "    dhorti_villages,\r\n"
				+ "    dhortinormalarea,\r\n"
				+ "    dhorti_farmers,\r\n"
				+ "    dhorti_ext,\r\n"
				+ "    dfish_mandals,\r\n"
				+ "    dfish_villages,\r\n"
				+ "    dfish_farmers,\r\n"
				+ "    dfish_ext,\r\n"
				+ "    dseri_mandals,\r\n"
				+ "    dseri_villages,\r\n"
				+ "    dseri_farmers,\r\n"
				+ "    dseri_ext,\r\n"
				+ "    0 AS dsnormalarea,\r\n"
				+ "    0 AS dfisheriesnormalarea,\r\n"
				+ "    0 AS dfnormalarea,\r\n"
				+ "    dsoc_farmers,\r\n"
				+ "    dsoc_ext,\r\n"
				+ "    dsoc_villages,\r\n"
				+ "    dsoc_mandals,\r\n"
				+ "    0 AS dsocnormalarea\r\n"
				 + "FROM " + table;  // Concatenate table variable properly

		Query query2 = entitymanger.createNativeQuery(qry);
		List<Object[]> result2 = query2.getResultList();
		System.out.println("result2------->"+result2.get(0)[1]);

		List<DistwiseStatusRepForAllCrops> list = new ArrayList<DistwiseStatusRepForAllCrops>();
		for (Object[] bean : result2) {
			DistwiseStatusRepForAllCrops cropwiseExtModel = new DistwiseStatusRepForAllCrops();
			System.out.println(bean[1] + "bean---------" + bean[0]);
			cropwiseExtModel.setDistcode((BigDecimal) bean[0]);
			cropwiseExtModel.setWbdname((String) bean[1]);
			cropwiseExtModel.setTmandals(((BigInteger) bean[2]).longValue()); // Convert BigInteger to long
			cropwiseExtModel.setTvillages(((BigInteger) bean[3]).longValue());
			cropwiseExtModel.setDagri_mandals(((BigInteger) bean[4]).longValue());
			cropwiseExtModel.setDagri_villages(((BigInteger) bean[5]).longValue());
			cropwiseExtModel.setDagri_ext((BigDecimal) bean[8]);
			cropwiseExtModel.setDagri_farmers(((BigInteger) bean[7]).longValue());
			cropwiseExtModel.setDhorti_mandals(((BigInteger) bean[9]).longValue());
			cropwiseExtModel.setDhorti_villages(((BigInteger) bean[10]).longValue());
			cropwiseExtModel.setDhorti_ext(((BigDecimal) bean[13]).longValue());
			cropwiseExtModel.setDhorti_farmers(((BigInteger) bean[12]).longValue());
			cropwiseExtModel.setDseri_mandals(((BigInteger) bean[18]).longValue());
			cropwiseExtModel.setDseri_villages(((BigInteger) bean[19]).longValue());
			cropwiseExtModel.setDseri_ext((BigDecimal) bean[21]);
			cropwiseExtModel.setDseri_farmers(((BigInteger) bean[20]).longValue());
			cropwiseExtModel.setDsoc_mandals(((BigInteger) bean[28]).longValue());
			cropwiseExtModel.setDsoc_villages(((BigInteger) bean[27]).longValue());
			cropwiseExtModel.setDsoc_ext((BigDecimal) bean[26]);
			cropwiseExtModel.setDsoc_farmers(((BigInteger) bean[27]).longValue());

			list.add(cropwiseExtModel);

		}
		System.out.println("list---------------->" + list);

		return list;
	}

}