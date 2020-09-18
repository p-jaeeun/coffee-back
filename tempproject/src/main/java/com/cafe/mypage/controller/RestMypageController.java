package com.cafe.mypage.controller;


import com.cafe.admin.beans.*;
import com.cafe.mypage.beans.CafeAddBean;
import com.cafe.mypage.beans.SimpleUserAdd;
import com.cafe.mypage.mapper.MypageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class RestMypageController {


    @Autowired
    MypageMapper mypageMapper;

    @Autowired
    CafeAddBean cafeAddBean;


    //개인 페이지 카페 신규 등록 요청

    @PostMapping("/user/addcafe")
    public ResponseEntity userAddCafe(@Valid SimpleUserAdd simpleUserAdd, BindingResult bindingResult) {
        ResponseEntity responseEntity;
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            List<ErrorMessages> errorMessagesList = new ArrayList<>();
            for (ObjectError objectError : allErrors) {
                String defaultMessage = objectError.getDefaultMessage();
                String code = objectError.getCode();
                ErrorMessages errorMessages = new ErrorMessages(defaultMessage, code);
                errorMessagesList.add(errorMessages);
            }
            return new ResponseEntity(errorMessagesList, HttpStatus.BAD_REQUEST);
        } else {


            System.out.println("update Ajax post....");
            String uploadFolder = "/Users/limjaewoo/IdeaProjects/tempproject/src/main/resources/cafeimage";
            System.out.println(simpleUserAdd.getCafe_image());

            String uploadFileName = simpleUserAdd.getCafe_image().getOriginalFilename();


            //iE has file path
            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
            UUID uuid = UUID.randomUUID();
            String filen = uuid.toString() + "_" + uploadFileName;
            File saveFile = new File(uploadFolder, filen);


            try {
                simpleUserAdd.getCafe_image().transferTo(saveFile);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // 데이터 베이스 처리를 현재 위치에서 처리

            CafeAddBean cafeAddBean = new CafeAddBean();
            cafeAddBean.setCafe_name(simpleUserAdd.getCafe_name());
            cafeAddBean.setCafe_location(simpleUserAdd.getCafe_location());
            cafeAddBean.setCafe_menu(simpleUserAdd.getCafe_menu());
            cafeAddBean.setCafe_information(simpleUserAdd.getCafe_information());
            cafeAddBean.setIs_enable_bean_choice(simpleUserAdd.getIs_enable_bean_choice());
            cafeAddBean.setCafe_mood(simpleUserAdd.getCafe_mood());
            cafeAddBean.setIs_enable_buy_bean(simpleUserAdd.getIs_enable_buy_bean());
            cafeAddBean.setIs_enable_handdrip(simpleUserAdd.getIs_enable_handdrip());
            cafeAddBean.setUser_id(simpleUserAdd.getUser_id());
            cafeAddBean.setCafe_image(filen);
            cafeAddBean.setCafe_sns(simpleUserAdd.getCafe_sns());
            mypageMapper.addCafe(cafeAddBean);

            responseEntity = new ResponseEntity(cafeAddBean, HttpStatus.OK);
        }
        return responseEntity;

    }
}