package com.ecrops.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecrops.entity.CLSCrNutrideficiencyImagesEntity;
@Repository
public interface NutrientDeficiencyImageRepo extends JpaRepository<CLSCrNutrideficiencyImagesEntity, Integer> {

	@Query(value = "select count(*) as totalPending from cropimages.cr_nutrideficiency_images a,public.cropnames b where a.cropcode = b.cropid and b.cropnature=?1 and drc_appr_status='P' and   cr_dist_code = ?2 and cr_nutrientcode!=0", nativeQuery = true)
	public int getTotalDrcPendingNutrientDeficiencyImages(Character cropnature, int distcode);

	@Query(value = "select count(*) as totalAccept from cropimages.cr_nutrideficiency_images a,public.cropnames b where a.cropcode = b.cropid and b.cropnature=?1 and drc_appr_status='A' and   cr_dist_code = ?2 and cr_nutrientcode!=0", nativeQuery = true)
	public int getTotalDrcApprovedNutrientDeficiencyImages(Character cropnature, int distcode);

	@Query(value = "select count(*) as totalReject from cropimages.cr_nutrideficiency_images a,public.cropnames b where a.cropcode = b.cropid and b.cropnature=?1 and drc_appr_status='R' and   cr_dist_code = ?2 and cr_nutrientcode!=0", nativeQuery = true)
	public int getTotalDrcRejectedNutrientDeficiencyImages(Character cropnature, int distcode);

}
