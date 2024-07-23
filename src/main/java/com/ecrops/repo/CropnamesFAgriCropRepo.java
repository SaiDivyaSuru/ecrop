package com.ecrops.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ecrops.entity.CropnamesFAgriCrop;

public interface CropnamesFAgriCropRepo extends JpaRepository<CropnamesFAgriCrop, String>{
	
	@Query(value="select \r\n"
			+ "	cropname,\r\n"
			+ "	cropnameeng,\r\n"
			+ "	cropnature \r\n"
			+ "from cropnames \r\n"
			+ "where cropnature='A' or croprepunder = 'C' \r\n"
			+ "order by cropnameeng",nativeQuery=true)
	List<CropnamesFAgriCrop> getAgri();
	
	@Query(value="select \r\n"
			+ "	cropname,\r\n"
			+ "	cropnameeng,\r\n"
			+ "	cropnature \r\n"
			+ "from cropnames \r\n"
			+ "where cropnature='H' \r\n"
			+ "order by cropnameeng",nativeQuery=true)
	List<CropnamesFAgriCrop> getHorti();



	@Query(value="select \r\n"
			+ "	cropname,\r\n"
			+ "	cropnameeng,\r\n"
			+ "	cropnature \r\n"
			+ "from cropnames \r\n"
			+ "where cropnature='S' \r\n"
			+ "order by cropnameeng",nativeQuery=true)
	List<CropnamesFAgriCrop> getSeri();


	
}
