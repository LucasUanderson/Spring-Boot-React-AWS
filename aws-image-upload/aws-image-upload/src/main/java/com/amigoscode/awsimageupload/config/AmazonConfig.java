package com.amigoscode.awsimageupload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonConfig {
	
	/*
	* Após o BasicAWSCredentials preencha onde esta nulo com (acessKey, secretKey)
	* que se encontra na https://aws.amazon.com/ entrar em my security credentials 
	* e gerar a access key e baixar.
	* 
	* No prompt digite acessKeys.csv e voce ira receber as chaves
	*/
	@Bean
	public AmazonS3 s3() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(
				"AKIAT54YEKARRF6UKPOY",
				"KjSFt1fgiGjWCjrAzRkw7wMoUAvYre+mI9uPvjex");
	
		return AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.build();
	
	}

}
