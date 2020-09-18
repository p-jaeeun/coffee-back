package com.cafe.admin.beans;


import org.springframework.stereotype.Repository;


@Repository
public class SimpleAdminBean {


    private String admin_id;

    private String admin_pw;

    @Override
    public String toString() {
        return "SimpleAdminBean{" +
                "admin_id='" + admin_id + '\'' +
                ", admin_pw='" + admin_pw + '\'' +
                '}';
    }

    public String getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(String admin_id) {
            this.admin_id = admin_id;
        }

        public String getAdmin_pw() {
            return admin_pw;
        }

        public void setAdmin_pw(String admin_pw) {
            this.admin_pw = admin_pw;
        }

}
