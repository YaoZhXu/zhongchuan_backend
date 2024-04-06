package com.shu.backend.facade.aliyunoss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.shu.backend.utils.AliyunOssProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileManageFacade {

    @Resource
    private AliyunOssProperties aliyunOssProperties;

    public boolean isFileExist(String ossFilePath) {
        String accessKeyId = aliyunOssProperties.getAccessKeyId();
        String accessKeySecret = aliyunOssProperties.getAccessKeySecret();
        String endpoint = aliyunOssProperties.getEndpoint();
        String bucketName = aliyunOssProperties.getBucketName();
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        boolean result = ossClient.doesObjectExist(bucketName, ossFilePath);
        ossClient.shutdown();
        return result;
    }

    public List<String> isFilesExist(List<String> ossFilePaths) {
        String accessKeyId = aliyunOssProperties.getAccessKeyId();
        String accessKeySecret = aliyunOssProperties.getAccessKeySecret();
        String endpoint = aliyunOssProperties.getEndpoint();
        String bucketName = aliyunOssProperties.getBucketName();
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        List<String> existFilePaths = new ArrayList<>();
        ossFilePaths.forEach((path) -> {
            if (ossClient.doesObjectExist(bucketName, path)) {
                existFilePaths.add(path);
            }
        });
        ossClient.shutdown();
        return existFilePaths;
    }

    public List<String> listFile(String keyPrefix) {
        String accessKeyId = aliyunOssProperties.getAccessKeyId();
        String accessKeySecret = aliyunOssProperties.getAccessKeySecret();
        String endpoint = aliyunOssProperties.getEndpoint();
        String bucketName = aliyunOssProperties.getBucketName();
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        List<String> files = new ArrayList<>();
        ObjectListing objectListing = ossClient.listObjects(bucketName, keyPrefix);
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        sums.forEach((s) -> files.add(s.getKey()));
        ossClient.shutdown();
        return files;
    }

    public void renameFile(String sourceKey, String destinationKey) {
        String accessKeyId = aliyunOssProperties.getAccessKeyId();
        String accessKeySecret = aliyunOssProperties.getAccessKeySecret();
        String endpoint = aliyunOssProperties.getEndpoint();
        String bucketName = aliyunOssProperties.getBucketName();
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ossClient.copyObject(bucketName, sourceKey, bucketName, destinationKey);
        ossClient.deleteObject(bucketName, sourceKey);
        ossClient.shutdown();
    }

    public void deleteFile(String ossFilePath) {
        String accessKeyId = aliyunOssProperties.getAccessKeyId();
        String accessKeySecret = aliyunOssProperties.getAccessKeySecret();
        String endpoint = aliyunOssProperties.getEndpoint();
        String bucketName = aliyunOssProperties.getBucketName();
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ossClient.deleteObject(bucketName, ossFilePath);
        ossClient.shutdown();
    }
}
