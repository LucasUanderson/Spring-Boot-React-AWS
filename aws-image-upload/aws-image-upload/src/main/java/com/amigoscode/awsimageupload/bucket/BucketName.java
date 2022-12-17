package com.amigoscode.awsimageupload.bucket;

public enum BucketName {
	
	/*
	 * Na http://aws.amazon procure por S3 
	 * e em seguida create bucket após criar o bucket 
	 * insira o nome em profile_image
	 */
	
	PROFILE_IMAGE ("aqui_vai_o_nome_do_bucket");
	
	private final String bucketName;

	
	private BucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	
	public String getBucketName() {
		return bucketName;
	}
	
	
	

}
