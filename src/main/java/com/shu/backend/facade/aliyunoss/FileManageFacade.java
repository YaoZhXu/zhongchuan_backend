package com.shu.backend.facade.aliyunoss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileManageFacade {

    @Resource
    private OSS ossClient;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    public boolean isFileExist(String ossFilePath) {
        boolean result = ossClient.doesObjectExist(bucketName, ossFilePath);
        return result;
    }

    public List<String> isFilesExist(List<String> ossFilePaths) {
        List<String> existFilePaths = new ArrayList<>();
        ossFilePaths.forEach((path) -> {
            if (ossClient.doesObjectExist(bucketName, path)) {
                existFilePaths.add(path);
            }
        });
        return existFilePaths;
    }

    public List<String> listFile(String keyPrefix) {
        List<String> files = new ArrayList<>();
        ObjectListing objectListing = ossClient.listObjects(bucketName, keyPrefix);
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        sums.forEach((s) -> files.add(s.getKey()));
        return files;
    }

    public void renameFile(String sourceKey, String destinationKey) {
        ossClient.copyObject(bucketName, sourceKey, bucketName, destinationKey);
        ossClient.deleteObject(bucketName, sourceKey);
    }

    public void deleteFile(String ossFilePath) {
        ossClient.deleteObject(bucketName, ossFilePath);
    }
}
