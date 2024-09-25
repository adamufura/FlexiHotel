package com.flexisaf.FlexiHotel.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.flexisaf.FlexiHotel.exception.MainException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class AwsS3Service {
    private final String bucketName = "flexi-hotel-images";

    @Value("${aws.s3.access.key}")
    private String awsAccessKey;


    @Value("${aws.s3.secret.key}")
    private String awsSecretKey;

    public String saveImagetToS3(MultipartFile photo){
        String s3LocationImage = null;

        try {
            String s3FileName = photo.getOriginalFilename();

            BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
            AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                    .withRegion(Regions.EU_NORTH_1)
                    .build();

            InputStream inputStream = photo.getInputStream();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/jpeg");

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3FileName, inputStream, metadata);
            amazonS3Client.putObject(putObjectRequest);

            s3LocationImage = "https://" + bucketName + ".s3.amazonaws.com/" + s3FileName;

        }catch (Exception e){
            e.printStackTrace();
            throw new MainException("Unable to upload image to s3 bucket " + e.getMessage());
        }
        return s3LocationImage;
    }
}
