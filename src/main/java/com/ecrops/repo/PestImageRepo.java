package com.ecrops.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecrops.entity.CLSCrPestImagesEntity;
@Repository
public interface PestImageRepo extends JpaRepository<CLSCrPestImagesEntity, Integer> {

	@Query(value = "select count(*) as totalPending from cropimages.cr_pest_images a,public.cropnames b where a.cropcode = b.cropid and b.cropnature=?1 and drc_appr_status='P' and   cr_dist_code = ?2", nativeQuery = true)
	public int getTotalDrcPendingPestImages(Character cropnature, int distcode);

	@Query(value = "select count(*) as totalAccept from cropimages.cr_pest_images a,public.cropnames b where a.cropcode = b.cropid and b.cropnature=?1 and drc_appr_status='A' and   cr_dist_code = ?2", nativeQuery = true)
	public int getTotalDrcApprovedPestImages(Character cropnature, int distcode);

	@Query(value = "select count(*) as totalReject from cropimages.cr_pest_images a,public.cropnames b where a.cropcode = b.cropid and b.cropnature=?1 and drc_appr_status='R' and   cr_dist_code = ?2", nativeQuery = true)
	public int getTotalDrcRejectedPestImages(Character cropnature, int distcode);
	
	

}
