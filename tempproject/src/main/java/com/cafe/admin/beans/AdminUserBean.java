package com.cafe.admin.beans;

public class AdminUserBean {

    private String user_id;
    private String user_name;
    private int user_caffeine;
    private Boolean is_blocked_user;

    @Override
    public String toString() {
        return "AdminUserBean{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_caffeine=" + user_caffeine +
                ", is_blocked_user=" + is_blocked_user +
                '}';
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_caffeine() {
        return user_caffeine;
    }

    public void setUser_caffeine(int user_caffeine) {
        this.user_caffeine = user_caffeine;
    }

    public Boolean getIs_blocked_user() {
        return is_blocked_user;
    }

    public void setIs_blocked_user(Boolean is_blocked_user) {
        this.is_blocked_user = is_blocked_user;
    }
}
