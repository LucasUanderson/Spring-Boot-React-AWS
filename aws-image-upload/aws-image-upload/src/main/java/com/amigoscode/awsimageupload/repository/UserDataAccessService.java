package com.amigoscode.awsimageupload.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amigoscode.awsimageupload.model.UserModel;

@Repository
public class UserDataAccessService {

	private final FakeUserModelData fakeUserModelData;

	
	@Autowired
	public UserDataAccessService(FakeUserModelData fakeUserModelData) {
		this.fakeUserModelData = fakeUserModelData;
	}
	
	public List<UserModel> getUserModel(){
		return fakeUserModelData.getUserProfiles();
	}
	
}
