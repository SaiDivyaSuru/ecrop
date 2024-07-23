package com.ecrops.service;

import java.util.List;

import com.ecrops.entity.CLSCropWisePojo;


public interface CLSCropWiseService {

	 List<CLSCropWisePojo> getCropImagesData();
	 
	List<CLSCropWisePojo> getCropMandalImagesDatas(String param);
}
