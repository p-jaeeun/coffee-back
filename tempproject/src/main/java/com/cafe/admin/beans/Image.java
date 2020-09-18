package com.cafe.admin.beans;

import org.springframework.web.multipart.MultipartFile;

public class Image {

private MultipartFile uploadFile;

    public MultipartFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(MultipartFile uploadFile) {
        this.uploadFile = uploadFile;
    }
}
