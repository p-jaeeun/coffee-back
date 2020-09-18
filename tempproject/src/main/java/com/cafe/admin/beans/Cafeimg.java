package com.cafe.admin.beans;

import org.springframework.web.multipart.MultipartFile;

public class Cafeimg {

    private int cafe_id;
    private byte[] cafe_image;

    public int getCafe_id() {
        return cafe_id;
    }

    public void setCafe_id(int cafe_id) {
        this.cafe_id = cafe_id;
    }

    public byte[] getCafe_image() {
        return cafe_image;
    }

    public void setCafe_image(byte[] cafe_image) {
        this.cafe_image = cafe_image;
    }
}
