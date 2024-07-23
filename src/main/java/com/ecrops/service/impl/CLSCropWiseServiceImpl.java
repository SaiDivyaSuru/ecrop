package com.ecrops.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecrops.entity.CLSCropWisePojo;
import com.ecrops.repo.CLSCropWiseRepo;
import com.ecrops.repo.DatabaseRepo;
import com.ecrops.service.CLSCropWiseService;

@Service
public class CLSCropWiseServiceImpl implements CLSCropWiseService {

	@Autowired
	CLSCropWiseRepo clsCropWiseRepo;	

	@Override
	public List<CLSCropWisePojo> getCropImagesData() {
		// TODO Auto-generated method stub
		return clsCropWiseRepo.getCropImagesDatas();
	}
	
	@Override
	public List<CLSCropWisePojo> getCropMandalImagesDatas(String param) {
		// TODO Auto-generated method stub
		return clsCropWiseRepo.getCropMandalImagesDatas(param);
	}

}
