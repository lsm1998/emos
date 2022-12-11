package com.lsm1998.api.oss;

import io.minio.*;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;

@Component
@Slf4j
public class MinioFacade
{
    @Value("${minio.url}")
    private String url;

    @Value("${minio.secretId}")
    private String secretId;

    @Value("${minio.secretKey}")
    private String secretKey;

    private MinioClient minioClient;

    public MinioFacade()
    {
    }

    public MinioFacade(String url, String secretId, String secretKey)
    {
        this.url = url;
        this.secretId = secretId;
        this.secretKey = secretKey;
    }

    @PostConstruct
    public void initMinioClient()
    {
        this.minioClient = MinioClient.builder().
                endpoint(url).
                credentials(secretId, secretKey).
                build();
    }

    public List<Bucket> bucketList() throws Exception
    {
        return minioClient.listBuckets();
    }

    public String updateFile(String bucketName, String fileName, String filePath) throws Exception
    {
        return this.updateFile(bucketName, fileName, new File(filePath));
    }

    public String updateFile(String bucketName, String fileName, MultipartFile file) throws Exception
    {
        minioClient.putObject(
                PutObjectArgs.builder().
                        bucket(bucketName).
                        object(fileName).
                        contentType(file.getContentType()).
                        stream(file.getInputStream(), file.getSize(), -1).
                        build());
        return String.format("%s/%s/%s", url, bucketName, fileName);
    }

    public boolean removeObject(String bucketName, String objectName) throws Exception
    {
        if (this.existBucket(bucketName))
        {
            return false;
        }
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
        return true;
    }

    public String updateFile(String bucketName, String fileName, File file) throws Exception
    {
        // String type = Files.probeContentType(file.toPath());
        try (InputStream inputStream = new FileInputStream(file))
        {
            minioClient.putObject(
                    PutObjectArgs.builder().
                            bucket(bucketName).
                            object(fileName).
                            stream(inputStream, file.length(), -1).
                            build());
        } catch (IOException exception)
        {
            log.error("updateFile fail,err={}", exception.getMessage());
        }
        return String.format("%s/%s/%s", url, bucketName, fileName);
    }

    public boolean existBucket(String bucketName) throws Exception
    {
        return !minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    public void createBucket(String bucketName) throws Exception
    {
        this.minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
    }

    public boolean deleteBucket(String bucketName) throws Exception
    {
        if (this.existBucket(bucketName))
        {
            return false;
        }
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        return true;
    }

    public String previewUrl(String bucketName, String objectName)
    {
        return String.format("%s/%s/%s", url, bucketName, objectName);
    }

    public void download(String bucketName, String objectName, String savePath) throws Exception
    {
        GetObjectResponse response = minioClient.getObject(GetObjectArgs.builder().
                bucket(bucketName).
                object(objectName).
                build());
        OutputStream outputStream = new FileOutputStream(savePath);
        response.transferTo(outputStream);
    }
}
