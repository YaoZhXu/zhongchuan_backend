package com.shu.backend.service.impl;

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
import java.util.Objects;

@Slf4j
@Service
public class OssServiceImpl implements OssService {

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
        String result = uploadFileFacade.simpleUploadFile("", file);
        log.info("【uploadFileFacade.simpleUploadFile】结束: resp = {}", result);
        return result;
    }

    @Override
    public String uploadFile(String dirName, MultipartFile file) throws IOException {
        if (!dirName.endsWith("/")) {
            dirName += "/";
        }
        if (dirName.charAt(0) == '/') {
            dirName = dirName.substring(1);
        }

        log.info("【directoryFacade.createDirectory】开始: dirName = {}", dirName);
        directoryFacade.createDirectory(dirName);
        log.info("【directoryFacade.createDirectory】结束");

        log.info("【uploadFileFacade.simpleUploadFile】开始: dirName = {}", dirName);
        String imageUrl = uploadFileFacade.simpleUploadFile(dirName, file);
        log.info("【uploadFileFacade.simpleUploadFile】结束: resp = {}", imageUrl);
        return imageUrl;
    }

    @Override
    public List<String> listDirFile(String dirName) {
        if (!dirName.endsWith("/")) {
            dirName += "/";
        }
        if (dirName.charAt(0) == '/') {
            dirName = dirName.substring(1);
        }

        if (dirName.equals("/")) {
            dirName = null;
        }

        log.info("【directoryFacade.listDirFile】开始: req = {}", dirName);
        List<String> files = directoryFacade.listDirFile(dirName);
        log.info("【directoryFacade.listDirFile】结束: resp = {}", files);
        return files;
    }

    @Override
    public List<String> listAllDir() {
        log.info("【directoryFacade.listDirectory】开始");
        List<String> dirs = directoryFacade.listDirectory(null);
        log.info("【directoryFacade.listDirectory】结束: resp = {}", dirs);
        return dirs;
    }

    @Override
    public void deleteDir(String dirName) {
        if (Objects.equals(dirName, "/")) {
            return;
        }
        if (!dirName.endsWith("/")) {
            dirName += "/";
        }
        if (dirName.charAt(0) == '/') {
            dirName = dirName.substring(1);
        }

        log.info("【fileManageFacade.isFileExist】开始, req = {}", dirName);
        boolean exist = fileManageFacade.isFileExist(dirName);
        log.info("【fileManageFacade.isFileExist】结束, resp = {}", exist);
        if (!exist) {
            return;
        }

        log.info("【directoryFacade.deleteDirectory】开始, req = {}", dirName);
        directoryFacade.deleteDirectory(dirName);
        log.info("【directoryFacade.deleteDirectory】结束");
    }

    @Override
    public void deleteFile(String dirName, String fileName) {
        if (!dirName.endsWith("/")) {
            dirName += "/";
        }
        if (dirName.charAt(0) == '/') {
            dirName = dirName.substring(1);
        }

        String filePath = dirName + fileName;
        if (dirName.equals("/")) {
            filePath = fileName;
        }

        log.info("【fileManageFacade.isFileExist】开始, req = {}", filePath);
        boolean exist = fileManageFacade.isFileExist(filePath);
        log.info("【fileManageFacade.isFileExist】结束, resp = {}", exist);
        if (!exist) {
            return;
        }

        log.info("【fileManageFacade.deleteFile】开始, req = {}", filePath);
        fileManageFacade.deleteFile(filePath);
        log.info("【fileManageFacade.deleteFile】结束");
    }

    @Override
    public List<String> fileList() {
        // TODO 调算法
        // TODO 文件名映射成oss
        List<String> list = new ArrayList<>();

        log.info("【uploadFileFacade.isFilesExist】开始, req = {}", list);
        List<String> existFiles = fileManageFacade.isFilesExist(list);
        log.info("【uploadFileFacade.isFilesExist】结束, resp = {}", existFiles);
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
