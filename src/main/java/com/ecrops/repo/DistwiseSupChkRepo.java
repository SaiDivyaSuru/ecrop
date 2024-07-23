package com.ecrops.repo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.ecrops.entity.DistwiseSupChk;

@Repository
public class DistwiseSupChkRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<DistwiseSupChk> getCropwise(String role, String wbdcode, String cr_season, int cr_year) {
        String t1 = "ecrop" + cr_year + ".supercheck_status_" + cr_season + cr_year;
        System.out.println("Table----------------" + t1);
        
        String qry = null;
        if (role.equals("17") || role.equals("44") || role.equals("45") || role.equals("18")) {
            qry = "SELECT cr_dist_code, dname, dao_allotted, dao_approved, dao_rejected, dho_allotted, dho_approved, dho_rejected, " +
                  "rdo_allotted, rdo_approved, rdo_rejected, ada_allotted, ada_approved, ada_rejected, tah_allotted, tah_approved, tah_rejected, " +
                  "mao_allotted, mao_approved, mao_rejected, ho_allotted, ho_approved, ho_rejected, dc_allotted, dc_approved, dc_rejected, " +
                  "jc_allotted, jc_approved, jc_rejected FROM " + t1 + " ORDER BY dname";
        } else if (role.equals("9") || role.equals("19") || role.equals("20") || role.equals("21") || role.equals("31")) {
            qry = "SELECT cr_dist_code, dname, dao_allotted, dao_approved, dao_rejected, dho_allotted, dho_approved, dho_rejected, " +
                  "rdo_allotted, rdo_approved, rdo_rejected, ada_allotted, ada_approved, ada_rejected, tah_allotted, tah_approved, tah_rejected, " +
                  "mao_allotted, mao_approved, mao_rejected, ho_allotted, ho_approved, ho_rejected, dc_allotted, dc_approved, dc_rejected, " +
                  "jc_allotted, jc_approved, jc_rejected FROM " + t1 + " WHERE cr_dist_code = :wbdcode ORDER BY dname";
        }

        if (qry == null) {
            return new ArrayList<>();
        }

        Query query = entityManager.createNativeQuery(qry);
        
        if (role.equals("9") || role.equals("19") || role.equals("20") || role.equals("21") || role.equals("31")) {
            query.setParameter("wbdcode", wbdcode);
        }
        
        List<Object[]> result = query.getResultList();
        List<DistwiseSupChk> distwiseSupChkList = new ArrayList<>();
        
        for (Object[] obj : result) {
            DistwiseSupChk distwiseSupChk = new DistwiseSupChk();
            distwiseSupChk.setCr_dist_code((BigDecimal) obj[0]); 
            distwiseSupChk.setDname((String) obj[1]); 
            distwiseSupChk.setDho_allotted(((BigInteger) obj[5]).longValue()); 
            distwiseSupChk.setDh_found_correct(((BigInteger) obj[6]).longValue());
            distwiseSupChk.setDh_found_incorrect(((BigInteger) obj[7]).longValue());
            distwiseSupChk.setHo_allotted(((BigInteger) obj[20]).longValue()); 
            distwiseSupChk.setHo_found_incorrect(((BigInteger) obj[22]).longValue());

            distwiseSupChkList.add(distwiseSupChk);
        }

        System.out.println("list---------------->" + distwiseSupChkList);
        return distwiseSupChkList;
    }
}
