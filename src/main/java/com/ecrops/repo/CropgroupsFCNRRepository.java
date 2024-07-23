package com.ecrops.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecrops.entity.CropgroupsFCNR;

public interface CropgroupsFCNRRepository extends JpaRepository<CropgroupsFCNR,Integer>{
	
	@Query(value="SELECT cropgrpid,grpname from cropgroups  where cropnature='F' and  active ='A'",nativeQuery=true)
	List<CropgroupsFCNR> getCropGroup1();
	
	@Query(value="SELECT cropgrpid,grpname from cropgroups  where active ='A' and cropnature in('A','H','S','R' )",nativeQuery=true)
	List<CropgroupsFCNR> getCropGroup2();
	
	@Query(value="select grpname from cropgroups where cropgrpid=:code",nativeQuery=true)
	String getCropGroup2(@Param("code") Integer code);
	


}
