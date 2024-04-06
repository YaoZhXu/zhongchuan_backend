package com.shu.backend.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shu.backend.facade.aliyunoss.DirectoryFacade;
import com.shu.backend.facade.aliyunoss.FileManageFacade;
import com.shu.backend.facade.aliyunoss.UploadFileFacade;
import com.shu.backend.service.OssService;
import com.shu.backend.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OssServiceImpl implements OssService {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private UploadFileFacade uploadFileFacade;

    @Resource
    private FileManageFacade fileManageFacade;

    @Resource
    private DirectoryFacade directoryFacade;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        if (!FileUtils.isImageFile(file)) {
            return null;
        }

        log.info("【uploadFileFacade.simpleUploadFile】开始");
        String result = uploadFileFacade.simpleUploadFile(file);
        log.info("【uploadFileFacade.simpleUploadFile】结束: resp = {}", result);
        return result;
    }

    @Override
    public List<String> fileList() throws JsonProcessingException {
        // TODO 调算法
        // TODO 文件名映射成oss
        List<String> list = new ArrayList<>();

        log.info("【uploadFileFacade.isFilesExist】开始, req = {}", objectMapper.writeValueAsString(list));
        List<String> existFiles = fileManageFacade.isFilesExist(list);
        log.info("【uploadFileFacade.isFilesExist】结束, resp = {}", objectMapper.writeValueAsString(existFiles));
        return existFiles;
    }

    @Override
    public void createCatalogue(String catalogueName) {
        log.info("【catalogueFacade.createCatalogue】开始, req = {}", catalogueName);
        directoryFacade.createDirectory(catalogueName);
        log.info("【catalogueFacade.createCatalogue】开始, resp = {}", catalogueName);
    }

    @Override
    public void deleteCatalogue(String cataloguePrefix) {
        log.info("【catalogueFacade.deleteCatalogue】开始, req = {}", cataloguePrefix);
        directoryFacade.deleteDirectory(cataloguePrefix);
        log.info("【catalogueFacade.deleteCatalogue】开始, resp = {}", cataloguePrefix);
    }
}
