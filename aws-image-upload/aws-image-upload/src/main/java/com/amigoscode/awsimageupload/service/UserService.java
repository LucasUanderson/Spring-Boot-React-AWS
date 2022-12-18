package com.amigoscode.awsimageupload.service;

import static org.apache.http.entity.ContentType.IMAGE_PNG;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amigoscode.awsimageupload.bucket.BucketName;
import com.amigoscode.awsimageupload.model.UserModel;
import com.amigoscode.awsimageupload.repository.UserDataAccessService;

@Service
public class UserService {

	private final UserDataAccessService userDataAccessService;
    private final FileStore fileStore;
	
	@Autowired
 	public UserService(UserDataAccessService userDataAccessService, FileStore fileStore ) {
		this.userDataAccessService = userDataAccessService;
		this.fileStore = fileStore;
		
	     
	}
	
	public List<UserModel> getUserModel(){
		return userDataAccessService.getUserModel();
	}

	public void uploadUserModelImage(UUID userId, MultipartFile file) {
		
		//1. Check if image is not empty
		isFileEmpty(file);
		
		//2. if file is an image
		isImage(file);
		
		// 3.The user exists in our database
		UserModel user = getUserProfileOrThrow(userId);
		
		//4. Grab some metadata from file if any
		Map<String, String> metadata = extractedMetadata(file);
		
		//5. Store the image in s3 and update database (userModelImageLink) with s3 image link    
		String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserId() );
		String filename = String.format("%s-S%",file.getOriginalFilename(), UUID.randomUUID());
		
		try {
			fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
			user.setUserModelImageLink(filename);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public byte[] downloadUserModelImage(UUID userId) {
		UserModel user = getUserProfileOrThrow(userId);
		String path  = String.format("%s/%s", 
				BucketName.PROFILE_IMAGE.getBucketName(), user.getUserId());
		
		return user.getUserModelImageLink()
		.map(key -> fileStore.download(path, key) )
		.orElse(new byte[0]);
		
		
	} 
	
	

	private Map<String, String> extractedMetadata(MultipartFile file) {
		Map<String,String> metadata = new HashMap<>();
		metadata.put("Content-Type", file.getContentType());
		metadata.put("Content-Length", String.valueOf(file.getSize()));
		return metadata;
	}

	private UserModel getUserProfileOrThrow(UUID userId) {
		return userDataAccessService
		.getUserModel()
		.stream()
		.filter(UserModel -> UserModel.getUserId().equals(userId))
		.findFirst()
		.orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userId)));
	}

	private void isImage(MultipartFile file) {
		if (!Arrays.asList(IMAGE_PNG.getMimeType(),IMAGE_PNG.getMimeType(), org.apache.http.entity.ContentType.IMAGE_GIF.getMimeType()).contains(file.getContentType())){
			throw new IllegalStateException("File must be an image [" + file.getContentType() +"]" );
		}
	}

	private void isFileEmpty(MultipartFile file) {
		if (file.isEmpty()) {
			throw new IllegalStateException("Cannot uoload empty file [ " + file.getSize() + "]");
		}
	}

	
	
}
