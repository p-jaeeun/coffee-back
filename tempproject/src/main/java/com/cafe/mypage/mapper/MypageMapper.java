package com.cafe.mypage.mapper;

import com.cafe.admin.beans.CafeBean;
import com.cafe.mypage.beans.CafeAddBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;


public interface MypageMapper {



    //개인페이지 카페 신규 등록 요청

    @Insert("INSERT INTO Cafe(Cafe_Name, Cafe_Location, Cafe_Menu, Cafe_Information, Is_Enable_Bean_Choice, Cafe_Mood, Is_Enable_Buy_Bean, Is_Enable_Handdrip,Is_Checked, User_ID, Cafe_Image, Cafe_Sns)VALUES (#{cafe_name},  #{cafe_location}, #{cafe_menu}, #{cafe_information},#{is_enable_bean_choice},#{cafe_mood},#{is_enable_buy_bean},#{is_enable_handdrip},false,#{user_id},#{cafe_image},#{cafe_sns})")
    public void addCafe(CafeAddBean cafeAddBean);

//    @Update("UPDATE Like SET Push_User= Push_User + 1 WHERE Got_Like = User_ID")
//    public void updatelike();


}
