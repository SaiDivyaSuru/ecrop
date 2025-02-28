package com.ecrops.repo.crop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecrops.dto.crop.response.CropYearCCRC;
import com.ecrops.dto.crop.response.VillageData;
import com.ecrops.dto.crop.response.CropYearCCRC;
import com.ecrops.dto.crop.response.VillageDataCcrc;
import com.ecrops.entity.crop.CCRC_DetailsEntity;

public interface CcrcCropRepository extends JpaRepository<CCRC_DetailsEntity, Integer> {

    @Query(value = "select distinct on (a.cropyear, a.season) concat(a.season, '@', cropyear) as seasonvalue, " +
            "concat(b.seasonname, ' ', cropyear) as cropyear \n" +
            "from activeseason a, season b  where a.season = b.season and a.active = 'A' and current_season = 'C' \n" +
            "order by a.cropyear, a.season", nativeQuery = true)
    List<CropYearCCRC> getCropYear();
    
    
    //and resurveyed='N' and resurveyed<>'Y' 
    @Query(value = "select wbvcode,wbevname from wbvillage_mst \n" +
            "where mcode=:mcode  and ecrop_dwn='Y' and wbvcode \n" +
            "in(select cr_vcode from ecrop2024.verify_datadownload where cr_year=:cropyear and cr_season=:season and ccrc_cnt is null and wbland='Y' )",
            nativeQuery = true)
    List<VillageDataCcrc> getVillages(@Param("mcode") int mCode, @Param("cropyear") int cropYear,
                                  @Param("season") String season);
    
    @Query(value = "select wbevname from wbvillage_mst where wbvcode= :wbvcode", nativeQuery = true)
	String getwbvname(@Param("wbvcode") int wbvcode);
}
    


   
