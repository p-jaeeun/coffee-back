package com.cafe.admin.mapper;

import com.cafe.admin.beans.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CafeMapper {

    //어드민 로그인 후

    @Select("SELECT * FROM Cafe")
    public List<CafeBean> selectAllCafes(CafeBean cafeBean);

    @Select("SELECT * FROM Cafe WHERE Cafe_ID=1")
    public CafeBean selectAllCafess(CafeBean cafeBean);

    @Select("SELECT Cafe_Image FROM Cafe")
    public String[] selectCafeImg(OnlyCafeimg onlyCafeimg);

    //어드민 카페등록

    @Insert("INSERT INTO Cafe(Cafe_Name, Cafe_Location, Cafe_Menu, Cafe_Information, Is_Enable_Bean_Choice, Cafe_Mood, Is_Enable_Buy_Bean, Is_Enable_Handdrip,Is_Checked, User_ID, Cafe_Image, Cafe_Sns)VALUES (#{cafe_name},  #{cafe_location}, #{cafe_menu}, #{cafe_information},#{is_enable_bean_choice},#{cafe_mood},#{is_enable_buy_bean},#{is_enable_handdrip},true,'1',#{cafe_image},#{cafe_sns})")
    public void addCafe(CafeBean cafeBean);

    //어드림 유저 카페 등록 요청
    @Select("SELECT A.User_ID, A.User_Image,B.Cafe_ID, B.Cafe_Name, B.Cafe_Location, B.Cafe_Menu, B.Cafe_Information, B.Is_Enable_Bean_Choice, B.Cafe_Mood, B.Is_Enable_Buy_Bean, B.Is_Enable_Handdrip,B.Is_Checked, B.User_ID, B.Cafe_Image,B.Cafe_Rate,B.Cafe_Sns FROM Cafe B JOIN coffee.User A ON  A.User_PK = B.User_ID AND B.Is_Checked = 0")
    public List<ByUserBean> selectbyusers();

    //어드민 유저 카페 등록 수락
    @Update("UPDATE Cafe SET Cafe_Name = #{cafe_name}, Cafe_Location = #{cafe_location}, Cafe_Menu = #{cafe_menu}, Cafe_Information = #{cafe_information}, Is_Enable_Bean_Choice = #{is_enable_bean_choice}, Cafe_Mood = #{cafe_mood}, Is_Enable_Buy_Bean = #{is_enable_buy_bean}, Is_Enable_Handdrip = #{is_enable_handdrip}, Is_Checked = #{is_checked}, User_ID = #{user_id}, Cafe_Image =#{cafe_image}, Cafe_Rate = #{cafe_rate}, Cafe_Sns = #{cafe_sns} WHERE Cafe_ID = #{cafe_id}")
    public void updatebyuser(CafeBean cafeBean);


    @Insert("INSERT INTO image(img) VALUES (#{img})")
    public void imageUp(Map<String, Object> hmap);

    @Select("SELECT * FROM image ORDER BY img_ID DESC LIMIT 1")
    public Map<String, Object> getByteImage();

}
