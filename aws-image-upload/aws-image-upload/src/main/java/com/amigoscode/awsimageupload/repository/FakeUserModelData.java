package com.amigoscode.awsimageupload.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.amigoscode.awsimageupload.model.UserModel;

@Repository
public class FakeUserModelData {
	
	private static final List<UserModel> USER_PROFILES = new ArrayList<>();
	
	static {
		USER_PROFILES.add(new UserModel(UUID.randomUUID(), "Username", null ));
		USER_PROFILES.add(new UserModel(UUID.randomUUID(), "Username", null ));
	}
	
	public List<UserModel> getUserProfiles(){
		return USER_PROFILES;
	}

}
