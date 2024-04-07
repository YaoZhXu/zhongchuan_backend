package com.shu.backend.controller;

import com.shu.backend.service.OssService;
import com.shu.backend.vo.request.oss.DeleteOssDirReq;
import com.shu.backend.vo.request.oss.DeleteOssFileReq;
import com.shu.backend.vo.request.oss.listDirFileReq;
import com.shu.backend.vo.response.CommonResponse;
import com.shu.backend.vo.response.oss.DirListResp;
import com.shu.backend.vo.response.oss.FileListResp;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Resource
    private OssService ossService;

    @PostMapping("/oss/uploadImage")
    public CommonResponse uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String result = ossService.uploadImage(file);
        if (result == null) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "图片上传失败");
        }

        return CommonResponse.success(result);
    }

    @PostMapping("/oss/uploadFile")
    public CommonResponse uploadFile(@RequestParam("file") MultipartFile file,
                                     @RequestParam("dirName") String dirName) throws IOException {
        String result = ossService.uploadFile(dirName, file);
        if (result == null) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "文件上传失败");
        }

        return CommonResponse.success(result);
    }

    @PostMapping("/oss/listDirFile")
    public CommonResponse listDirFile(@Validated @RequestBody listDirFileReq req) {
        List<String> files = ossService.listDirFile(req.getDirName());

        FileListResp resp = new FileListResp();
        resp.setFileList(files);
        return CommonResponse.success(resp);
    }

    @GetMapping("/oss/listAllDir")
    public CommonResponse listAllDir() {
        List<String> dirs = ossService.listAllDir();

        DirListResp resp = new DirListResp();
        resp.setDirList(dirs);
        return CommonResponse.success(resp);
    }

    @PostMapping("/oss/deleteDir")
    public CommonResponse deleteDir (@Validated @RequestBody DeleteOssDirReq req) {
        ossService.deleteDir(req.getDirName());
        return CommonResponse.success();
    }

    @PostMapping("/oss/deleteFile")
    public CommonResponse deleteFile (@Validated @RequestBody DeleteOssFileReq req) {
        ossService.deleteFile(req.getDirName(), req.getFileName());
        return CommonResponse.success();
    }
}
