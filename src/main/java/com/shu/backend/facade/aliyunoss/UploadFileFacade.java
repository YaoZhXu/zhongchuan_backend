package com.shu.backend.facade.aliyunoss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class UploadFileFacade {

    @Resource
    private OSS ossClient;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    public String simpleUploadFile(String dirName, MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
//        String fileName = dirName + java.util.UUID.randomUUID() + fileExtension;
        String fileName = dirName + originalFileName;

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file.getInputStream());
        ossClient.putObject(putObjectRequest);
        return "https://" + bucketName + "." + endpoint + "/" + fileName;
    }
}
