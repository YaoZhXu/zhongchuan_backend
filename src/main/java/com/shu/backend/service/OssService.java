package com.shu.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface OssService {

    String uploadImage(MultipartFile file) throws IOException;

    List<String> fileList() throws JsonProcessingException;

    void createCatalogue(String catalogueName);

    void deleteCatalogue(String cataloguePrefix);
}
