package com.mariuszilinskas.streamix.users.profile.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws")
public record AwsProperties(
        String accessKey,
        String secretKey,
        S3 s3
) {

    public record S3(
            String region,
            String avatarBucketName
    ) {}
}
