package com.cafe.admin.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class temp {
    List<ResponseEntity<byte[]>> httpEntity;

    public List<ResponseEntity<byte[]>> getHttpEntity() {
        return httpEntity;
    }

    public void setHttpEntity(List<ResponseEntity<byte[]>> httpEntity) {
        this.httpEntity = httpEntity;
    }
}
