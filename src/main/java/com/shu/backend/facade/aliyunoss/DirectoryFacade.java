package com.shu.backend.facade.aliyunoss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import com.shu.backend.utils.AliyunOssProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DirectoryFacade {

    @Resource
    private AliyunOssProperties aliyunOssProperties;

    public void createDirectory(String catalogueName) {
        String accessKeyId = aliyunOssProperties.getAccessKeyId();
        String accessKeySecret = aliyunOssProperties.getAccessKeySecret();
        String endpoint = aliyunOssProperties.getEndpoint();
        String bucketName = aliyunOssProperties.getBucketName();
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // Supports recursive creation of file directories
        if (!catalogueName.endsWith("/")) {
            catalogueName += "/";
        }
        String content = "";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, catalogueName, new ByteArrayInputStream(content.getBytes()));
        ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
    }

    public void deleteDirectory(String cataloguePrefix) {
        String accessKeyId = aliyunOssProperties.getAccessKeyId();
        String accessKeySecret = aliyunOssProperties.getAccessKeySecret();
        String endpoint = aliyunOssProperties.getEndpoint();
        String bucketName = aliyunOssProperties.getBucketName();
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // Supports recursive deletion of file directories
        if (!cataloguePrefix.endsWith("/")) {
            cataloguePrefix += "/";
        }
        String nextMarker = null;
        ObjectListing objectListing;
        do {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName)
                    .withPrefix(cataloguePrefix)
                    .withMarker(nextMarker);

            objectListing = ossClient.listObjects(listObjectsRequest);
            if (!objectListing.getObjectSummaries().isEmpty()) {
                List<String> keys = new ArrayList<>();
                for (OSSObjectSummary s : objectListing.getObjectSummaries()) {
                    keys.add(s.getKey());
                }
                DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName).withKeys(keys).withEncodingType("url");
                ossClient.deleteObjects(deleteObjectsRequest);
            }
            nextMarker = objectListing.getNextMarker();
        } while (objectListing.isTruncated());
        ossClient.shutdown();
    }

    public List<String> listDirectory(String cataloguePrefix) {
        String accessKeyId = aliyunOssProperties.getAccessKeyId();
        String accessKeySecret = aliyunOssProperties.getAccessKeySecret();
        String endpoint = aliyunOssProperties.getEndpoint();
        String bucketName = aliyunOssProperties.getBucketName();
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ObjectListing objectListing = ossClient.listObjects(bucketName, cataloguePrefix);
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        List<String> directoryList = sums.stream()
                .map(OSSObjectSummary::getKey)
                .filter(key -> key.endsWith("/"))
                .collect(Collectors.toList());
        ossClient.shutdown();
        return directoryList;
    }
}
