package com.cafe.admin.beans;

import org.springframework.stereotype.Repository;

@Repository
public class UserBean {
    private int user_pk;
    private String user_id;
    private String user_pw;
    private String user_email;
    private Boolean user_gender;
    private String user_birth;
    private int user_like;
    private int user_caffeine;
    private int count_expose_main;
    private Boolean is_specialuser;
    private Boolean is_blocked_user;
    private String user_image;
    private String user_status;
    private String user_name;

    public int getUser_pk() {
        return user_pk;
    }

    public void setUser_pk(int user_pk) {
        this.user_pk = user_pk;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public Boolean getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(Boolean user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_birth() {
        return user_birth;
    }

    public void setUser_birth(String user_birth) {
        this.user_birth = user_birth;
    }

    public int getUser_like() {
        return user_like;
    }

    public void setUser_like(int user_like) {
        this.user_like = user_like;
    }

    public int getUser_caffeine() {
        return user_caffeine;
    }

    public void setUser_caffeine(int user_caffeine) {
        this.user_caffeine = user_caffeine;
    }

    public int getCount_expose_main() {
        return count_expose_main;
    }

    public void setCount_expose_main(int count_expose_main) {
        this.count_expose_main = count_expose_main;
    }

    public Boolean getIs_specialuser() {
        return is_specialuser;
    }

    public void setIs_specialuser(Boolean is_specialuser) {
        this.is_specialuser = is_specialuser;
    }

    public Boolean getIs_blocked_user() {
        return is_blocked_user;
    }

    public void setIs_blocked_user(Boolean is_blocked_user) {
        this.is_blocked_user = is_blocked_user;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}