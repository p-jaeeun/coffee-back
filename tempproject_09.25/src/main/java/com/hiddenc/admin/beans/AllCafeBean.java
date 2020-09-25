package com.hiddenc.admin.beans;

import java.util.List;

public class AllCafeBean {

    private List<CafeBean> cafebean;
    private List<CafeImg> cafeimg;
    private List<Review> review;

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    public List<CafeBean> getCafebean() {
        return cafebean;
    }

    public void setCafebean(List<CafeBean> cafebean) {
        this.cafebean = cafebean;
    }

    public List<CafeImg> getCafeimg() {
        return cafeimg;
    }

    public void setCafeimg(List<CafeImg> cafeimg) {
        this.cafeimg = cafeimg;
    }
}
