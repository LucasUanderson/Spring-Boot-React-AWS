package com.amigoscode.awsimageupload.model;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserModel {
	
	private UUID userId;
	private String username;
	private String userModelImageLink; // S3 key
	
	public UserModel(UUID userId, String username, String userModelImageLink) {
		super();
		this.userId = userId;
		this.username = username;
		this.userModelImageLink = userModelImageLink;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Optional <String> getUserModelImageLink() {
		return Optional.ofNullable(userModelImageLink);
	}

	public void setUserModelImageLink(String userModelImageLink) {
		this.userModelImageLink = userModelImageLink;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, userModelImageLink, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel that = (UserModel) obj;
		return Objects.equals(userId, that.userId) && 
			   Objects.equals(userModelImageLink, that.userModelImageLink)
				&& Objects.equals(username, that.username);
	}

	
	
}
