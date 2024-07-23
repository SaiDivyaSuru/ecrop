package com.ecrops.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecrops.entity.Villcoords;
import com.ecrops.entity.WbMaster;

public interface JioreferenceRepository extends JpaRepository<Villcoords, com.ecrops.entity.EmbeddedVillcoords>{

	void save(WbMaster jioref);

}
