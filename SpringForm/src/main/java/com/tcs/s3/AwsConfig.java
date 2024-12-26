package com.tcs.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsConfig {
	
	
	 @Value("${aws.accessKeyId}")
	    private String accessKey;

	    @Value("${aws.secretAccessKey}")
	    private String secretKey;

	    @Value("${aws.region}")
	    private String region;
	
	@Bean
    public S3Client s3Client() {
        return S3Client.builder()
            .region(Region.of("ap-south-1")) 
            .credentialsProvider(StaticCredentialsProvider.create(
             AwsBasicCredentials.create("", "")))
            .build();
    }
	
	@Bean
    public AmazonS3 amazonS3() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3Client.builder()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.AP_SOUTH_1)  // Use the region from properties
                .build();
    }
	  
		 
	    

}
