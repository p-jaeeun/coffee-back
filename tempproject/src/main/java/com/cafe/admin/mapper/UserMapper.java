package com.cafe.admin.mapper;

import com.cafe.admin.beans.AdminUserBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    //어드민 회원 관리

    @Select("SELECT User_ID, User_Name, User_Caffeine, Is_Blocked_User FROM User ")
    public List<AdminUserBean> selectUsers();




}


