package com.modestack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.modestack.model.Pepper;

@Repository
public interface PepperRepository extends CrudRepository<Pepper, Integer>{
	
	public Pepper findByPepperId(int pepperId);

}
