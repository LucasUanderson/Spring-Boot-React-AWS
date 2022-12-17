package com.amigoscode.awsimageupload.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amigoscode.awsimageupload.model.UserModel;
import com.amigoscode.awsimageupload.repository.UserDataAccessService;

@Service
public class UserService {

	private final UserDataAccessService userDataAccessService;

	@Autowired
	public UserService(UserDataAccessService userDataAccessService) {
		this.userDataAccessService = userDataAccessService;
	}
	
	public List<UserModel> getUserModel(){
		return userDataAccessService.getUserModel();
	}

	public void uploadUserModelImage(UUID userId, MultipartFile file) {
		
		
	}
	
	
}
