package com.hiddenc.hyun.search.dto;

public class SearchResultDto {

    private int cafe_id;
    private String cafe_name;
    private String cafe_image;
    private String cafe_location;

    public int getCafe_id() {
        return cafe_id;
    }

    public void setCafe_id(int cafe_id) {
        this.cafe_id = cafe_id;
    }

    public String getCafe_name() {
        return cafe_name;
    }

    public void setCafe_name(String cafe_name) {
        this.cafe_name = cafe_name;
    }

    public String getCafe_image() {
        return cafe_image;
    }

    public void setCafe_image(String cafe_image) {
        this.cafe_image = cafe_image;
    }

    public String getCafe_location() {
        return cafe_location;
    }

    public void setCafe_location(String cafe_location) {
        this.cafe_location = cafe_location;
    }
}
