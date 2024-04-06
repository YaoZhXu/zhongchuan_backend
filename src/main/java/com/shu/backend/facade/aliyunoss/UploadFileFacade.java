package com.shu.backend.facade.aliyunoss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.shu.backend.utils.AliyunOssProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class UploadFileFacade {

    @Resource
    private AliyunOssProperties aliyunOssProperties;

    public String simpleUploadFile(MultipartFile file) throws IOException {
        String accessKeyId = aliyunOssProperties.getAccessKeyId();
        String accessKeySecret = aliyunOssProperties.getAccessKeySecret();
        String endpoint = aliyunOssProperties.getEndpoint();
        String bucketName = aliyunOssProperties.getBucketName();
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName = java.util.UUID.randomUUID() + fileExtension;

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file.getInputStream());
        ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
        return "https://" + bucketName + "." + endpoint + "/" + fileName;
    }
}
