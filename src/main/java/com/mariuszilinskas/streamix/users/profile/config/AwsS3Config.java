package com.mariuszilinskas.streamix.users.profile.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.mariuszilinskas.streamix.users.profile.properties.AwsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AwsS3Config {

    private final AwsProperties awsProperties;

    @Bean
    public AmazonS3 s3Client() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(awsProperties.accessKey(), awsProperties.secretKey());
        return AmazonS3Client.builder()
                .withRegion(awsProperties.s3().region())
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

}
