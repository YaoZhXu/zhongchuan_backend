package com.shu.backend.controller;

import com.shu.backend.service.OssService;
import com.shu.backend.vo.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Resource
    private OssService ossService;

    @PostMapping("/uploadImage")
    public CommonResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String result = ossService.uploadImage(file);
        if (result == null) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "图片上传失败");
        }

        return CommonResponse.success(result);
    }
}
