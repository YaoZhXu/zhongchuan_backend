package com.shu.backend.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

    public static boolean isImageFile(MultipartFile file) {
        if (file == null) {
            return false;
        }

        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }
}
