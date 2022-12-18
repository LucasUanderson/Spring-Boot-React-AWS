package com.amigoscode.awsimageupload.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amigoscode.awsimageupload.model.UserModel;
import com.amigoscode.awsimageupload.service.UserService;

@RestController
@RequestMapping("api/v1/user-profile")

/*
 * Não é correto liberar o acesso de qualquer lugar em produção com @CrossOrigin("*")
 * ou quando estiver deploying, O correto é você saber exatamente oque permitir em seus
 * endpoints.
 */
@CrossOrigin("*")
public class UserProfileController {
	
	private UserService userService;
	
	
	@Autowired
	public UserProfileController(UserService userService) {
		this.userService = userService;
	}



	@GetMapping
	public List<UserModel> getUserModel(){
		return userService.getUserModel();
	}
 
	@PostMapping (
			path = "{userId}/image/upload",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
		
			
			)
	public void uploadUserModelImage(@PathVariable("userId") UUID userId,
			                         @RequestParam("file") MultipartFile file) {
		
		userService.uploadUserModelImage(userId, file);
		
	}
	
	
	@GetMapping("{userId}/image/download")
	public byte[] downloadUserModelImage(@PathVariable("userId") UUID userId) {
		return userService.downloadUserModelImage(userId);
	}
}
