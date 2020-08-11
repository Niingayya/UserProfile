package com.modestack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.modestack.model.UserProfile;

@Repository
public interface ModestackRepository extends CrudRepository<UserProfile, Integer> {

	public UserProfile findByUserName(String userName);
	public UserProfile findByUserNameAndPassword(String userName, String password);
	public UserProfile findByUserNameAndAccessToken(String userName, String accessToken);
	
}
