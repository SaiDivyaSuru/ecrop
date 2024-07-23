package com.ecrops.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ecrops.entity.CropgroupsFCVR;

@Repository
public interface CropgroupsFCVRRepository extends JpaRepository<CropgroupsFCVR, Integer> {
	
//	@Query(value="select cropgrpid,grpname from cropgroups  where active='A'",nativeQuery=true)
//	List<CropgroupsFCVR> getCropGroup();
	@Query(value="SELECT cropgrpid,grpname from cropgroups  where active ='A' and cropnature='F'",nativeQuery=true)
	List<CropgroupsFCVR> getCropGroup1();

	@Query(value="SELECT cropgrpid,grpname from cropgroups  where active ='A'",nativeQuery=true)
	List<CropgroupsFCVR> getCropGroup2();

	@Query(value="select grpname from cropgroups where cropgrpid=:code",nativeQuery=true)
	String getCropGroup2(@Param("code") Integer code);
} 



