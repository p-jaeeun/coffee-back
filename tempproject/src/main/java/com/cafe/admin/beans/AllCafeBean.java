package com.cafe.admin.beans;




import java.util.List;

public class AllCafeBean {

    private int cafe_id;
    private String img;
    private List<CafeBean> cafeBeans;

    public int getCafe_id() {
        return cafe_id;
    }

    public void setCafe_id(int cafe_id) {
        this.cafe_id = cafe_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<CafeBean> getCafeBeans() {
        return cafeBeans;
    }

    public void setCafeBeans(List<CafeBean> cafeBeans) {
        this.cafeBeans = cafeBeans;
    }
}