package com.shu.backend.facade.aliyunoss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DirectoryFacade {

    @Resource
    private OSS ossClient;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    public void createDirectory(String catalogueName) {
        // Supports recursive creation of file directories
        if (!catalogueName.endsWith("/")) {
            catalogueName += "/";
        }
        String content = "";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, catalogueName, new ByteArrayInputStream(content.getBytes()));
        ossClient.putObject(putObjectRequest);
    }

    public void deleteDirectory(String cataloguePrefix) {
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
    }

    public List<String> listDirectory(String cataloguePrefix) {
        ObjectListing objectListing = ossClient.listObjects(bucketName, cataloguePrefix);
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        List<String> directoryList = sums.stream()
                .map(OSSObjectSummary::getKey)
                .filter(key -> key.endsWith("/"))
                .collect(Collectors.toList());
        return directoryList;
    }

    public List<String> listDirFile(String cataloguePrefix) {
        ObjectListing objectListing = ossClient.listObjects(bucketName, cataloguePrefix);
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        List<String> directoryList = sums.stream()
                .map(OSSObjectSummary::getKey)
                .filter(key -> !key.endsWith("/"))
                .collect(Collectors.toList());
        return directoryList;
    }
}
