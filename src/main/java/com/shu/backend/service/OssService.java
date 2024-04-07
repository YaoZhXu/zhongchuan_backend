package com.shu.backend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface OssService {

    String uploadImage(MultipartFile file) throws IOException;

    String uploadFile(String dirName, MultipartFile file) throws IOException;

    List<String> listDirFile(String dirName);

    List<String> listAllDir();

    void deleteDir(String dirName);

    void deleteFile(String dirName, String fileName);

    List<String> fileList();

    void createCatalogue(String catalogueName);

    void deleteCatalogue(String cataloguePrefix);
}
