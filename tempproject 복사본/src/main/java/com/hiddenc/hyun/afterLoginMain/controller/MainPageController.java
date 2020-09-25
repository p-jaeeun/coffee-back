package com.hiddenc.hyun.afterLoginMain.controller;

import com.hiddenc.hyun.afterLoginMain.dto.SimpleCafeDto;
import com.hiddenc.model.mapper.CafeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
public class MainPageController {

    @Autowired
    CafeMapper cafeMapper;

    // 로그인 후 메인페이지 카페목록 가져오기
    @GetMapping("/login/main")
    @ResponseBody
    public List<SimpleCafeDto> loginMain() {
        List<SimpleCafeDto> list = cafeMapper.selectCafeList();
        return list;
    }

    @GetMapping("/example/hihi")
    public String loginMain2(){
        return "formExample";
    }


}
