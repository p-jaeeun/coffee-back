package com.cafe.admin.controller;


import com.cafe.admin.beans.*;


import com.cafe.admin.mapper.CafeMapper;
import com.cafe.admin.mapper.UserMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.swing.*;
import javax.validation.Valid;
import java.io.*;
import java.util.*;


@RestController
@PropertySource("/WEB-INF/properties/Admin.properties")
public class RestExampleController {



    @Resource(name = "adminlogin")
    AdminConnection adminConnection;

    @Resource(name = "adminLoginBean")
    AdminConnection adminLogin;

    @Autowired
    SimpleAdminBean simpleAdminBean;

    @Autowired
    SimpleCafeBean simpleCafeBean;

    @Autowired
    SimpleUserCafeBean simpleUserCafeBean;
    @Autowired
    CafeMapper cafeMapper;
    @Autowired
    UserMapper userMapper;


// 어드민 로그인 전

    @PostMapping("/login")
    public String adminlogin(@RequestBody SimpleAdminBean simpleAdminBean) {
        String admin_id = simpleAdminBean.getAdmin_id();
        String admin_pw = simpleAdminBean.getAdmin_pw();
        String id = adminConnection.getAdmin_id();
        String pw = adminConnection.getAdmin_pw();


        if (id.equals(admin_id) && pw.equals(admin_pw)) {
            adminLogin.setLoggedIn(true);

            return "admin_true"; //다 맞으면

        } else if (!(id.equals(admin_id))) {
            return "id_false";  //아이디가 틀리면
        } else if (!(pw.equals(admin_pw))) {
            return "pw_false";   //패스워드가 틀리면
        } else {
            return "all_false";  //다 틀리면
        }
    }


//    // 어드민 로그인 후
//    @GetMapping("/admin/cafe")
//    public AllCafeBean adminmain(AllCafeBean allCafeBean) throws IOException {
//        CafeBean cafeBean = new CafeBean();
//        Cafeimg cafeimg = new Cafeimg();
//        OnlyCafeimg onlyCafeimg = new OnlyCafeimg();
//


    //어드민 카페 전체사진(미완료)
//@GetMapping(value = "/admin/cafe")
//public ResponseEntity<byte[]> displayFile() throws Exception{
//        InputStream imageStream = null;
//        ResponseEntity<byte[]> entity = null;
//
//    HttpHeaders header = new HttpHeaders();
//    header.setContentType(MediaType.IMAGE_JPEG);
//        OnlyCafeimg onlyCafeimg = new OnlyCafeimg();
//    String[] na = cafeMapper.selectCafeImg(onlyCafeimg);
//    List<String> list = new ArrayList<>();
//        for(int i=0; i < na.length; i++) {
//        String formatName = na[i];
//        String Path = "/Users/limjaewoo/IdeaProjects/tempproject/src/main/resources/cafeimage/"+formatName;
//
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//         imageStream = new FileInputStream(Path);
//        byte[] imgbyte = IOUtils.toByteArray(imageStream);
//
//           entity = new ResponseEntity<byte[]>(imgbyte,header,HttpStatus.CREATED);
//        }
//    imageStream.close();
//        return entity;
//}


    //어드민 회원 관리

    @GetMapping("/cafe1")
    public ResponseEntity adminAllcafes(AllCafeBean allCafeBean) throws Exception {
//        AllCafeBean allCafeBean = new AllCafeBean();
        OnlyCafeimg onlyCafeimg = new OnlyCafeimg();
        Add add = new Add();
//        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        CafeBean cafeBean = new CafeBean();
        String[] na = cafeMapper.selectCafeImg(onlyCafeimg);
        String name = na[0];
        String Path = "/Users/limjaewoo/IdeaProjects/tempproject/src/main/resources/cafeimage/" + name;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream imageStream = new FileInputStream(Path);
        byte[] imgbyte = IOUtils.toByteArray(imageStream);
        allCafeBean.setCafeBeans(cafeMapper.selectAllCafes(cafeBean));
        allCafeBean.setCafe_id(1);
        allCafeBean.setImg("http://192.168.1.131:8080/cafe/cafe");



        return new ResponseEntity(allCafeBean, HttpStatus.OK);
    }

    //어드민 카페 리스트(미완료)
    @GetMapping(value = "/cafe")
    public List<java.awt.Image> displayFile(temp temp) throws Exception {
        InputStream imageStream = null;
        ResponseEntity<byte[]> entity = null;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.IMAGE_JPEG);
        OnlyCafeimg onlyCafeimg = new OnlyCafeimg();
        String[] na = cafeMapper.selectCafeImg(onlyCafeimg);
        List<java.awt.Image> list = new ArrayList<>();
        for (int i = 0; i < na.length; i++) {
            String formatName = na[i];
            String Path = "/Users/limjaewoo/IdeaProjects/tempproject/src/main/resources/cafeimage/" + formatName;

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            imageStream = new FileInputStream(Path);

            byte[] imgbyte = IOUtils.toByteArray(imageStream);
            buffer.write(imgbyte);
            imageStream.close();
        byte[] img = buffer.toByteArray();
            ImageIcon imageIcon = new ImageIcon(img);
            imageIcon.getImage();
            System.out.println("로그 : " + entity);
//        entity = ResponseEntity.ok().headers(header).body(img);
        list.add(imageIcon.getImage());
        }

//        temp.setHttpEntity(list);
//            enetity2 = entity;

        return list;
    }



    @GetMapping(value = "/cafe5")
    public void displayFile1() throws Exception {
        InputStream imageStream = null;
        ResponseEntity<byte[]> entity = null;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.IMAGE_JPEG);
        OnlyCafeimg onlyCafeimg = new OnlyCafeimg();
        String[] na = cafeMapper.selectCafeImg(onlyCafeimg);
        List<java.awt.Image> list = new ArrayList<>();
        for (int i = 0; i < na.length; i++) {
            String formatName = na[i];
            String Path = "/Users/limjaewoo/IdeaProjects/tempproject/src/main/resources/cafeimage/" + formatName;

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            imageStream = new FileInputStream(Path);

            byte[] imgbyte = IOUtils.toByteArray(imageStream);
            buffer.write(imgbyte);
            imageStream.close();
            byte[] img = buffer.toByteArray();
            ImageIcon imageIcon = new ImageIcon(img);
            imageIcon.getImage();
            System.out.println("로그 : " + entity);
//        entity = ResponseEntity.ok().headers(header).body(img);
            list.add(imageIcon.getImage());
        }

    }



    @GetMapping(value = "/cafe15")
    public byte[] displayFile2() throws Exception {
        InputStream imageStream = null;
        ResponseEntity<byte[]> entity = null;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.IMAGE_JPEG);
        OnlyCafeimg onlyCafeimg = new OnlyCafeimg();
        String[] na = cafeMapper.selectCafeImg(onlyCafeimg);
        List<String> list = new ArrayList<>();

        String name = na[5];
        System.out.println(na[5]);
        String Path = "/Users/limjaewoo/IdeaProjects/tempproject/src/main/resources/cafeimage/" + name;
        System.out.println(Path);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        imageStream = new FileInputStream(Path);

        byte[] imgbyte = IOUtils.toByteArray(imageStream);



        return imgbyte;
    }
    @GetMapping(value = "/cafe115")
    public byte[] displayFile3() throws Exception {
        InputStream imageStream = null;
        ResponseEntity<byte[]> entity = null;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.IMAGE_JPEG);
        OnlyCafeimg onlyCafeimg = new OnlyCafeimg();
        String[] na = cafeMapper.selectCafeImg(onlyCafeimg);
        List<String> list = new ArrayList<>();

        String name = na[6];
        System.out.println(na[6]);
        String Path = "/Users/limjaewoo/IdeaProjects/tempproject/src/main/resources/cafeimage/" + name;
        System.out.println(Path);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        imageStream = new FileInputStream(Path);

        byte[] imgbyte = IOUtils.toByteArray(imageStream);



        return imgbyte;
    }


    @GetMapping("/cafe8")
    public ResponseEntity adminAllcafes1(AllCafeBean allCafeBean) throws Exception {
        CafeBean cafeBean = new CafeBean();
        OnlyCafeimg onlyCafeimg = new OnlyCafeimg();
        allCafeBean.setCafeBeans(cafeMapper.selectAllCafes(cafeBean));
        String[] na = cafeMapper.selectCafeImg(onlyCafeimg);
        String name =na[1];
        Add add = new Add();
//        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.IMAGE_JPEG);
        String Path = "/Users/limjaewoo/IdeaProjects/tempproject/src/main/resources/cafeimage/" + name;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream imageStream = new FileInputStream(Path);
        byte[] imgbyte = IOUtils.toByteArray(imageStream);
        String id = "jam";

        allCafeBean.setCafe_id(cafeBean.getCafe_id());
        allCafeBean.setImg("http://192.168.1.131:8080/cafe/cafe/");

        return new ResponseEntity(allCafeBean, HttpStatus.OK);
    }
    //어드민 카페 리스트(미완료)
    @GetMapping(value = "/cafe112")
    public ResponseEntity displayFile12() throws Exception {
        InputStream imageStream = null;
        ResponseEntity<byte[]> entity = null;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        OnlyCafeimg onlyCafeimg = new OnlyCafeimg();
        String[] na = cafeMapper.selectCafeImg(onlyCafeimg);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < na.length; i++) {
            String formatName = na[i];
            String Path = "/Users/limjaewoo/IdeaProjects/tempproject/src/main/resources/cafeimage/" + formatName;
            System.out.println(Path);
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            imageStream = new FileInputStream(Path);

            byte[] imgbyte = IOUtils.toByteArray(imageStream);
            buffer.write(imgbyte);
            imageStream.close();
        }
        byte[] img = buffer.toByteArray();
        entity = ResponseEntity.ok().headers(header).body(img);

        return entity;
    }




//
//        String[] na = cafeMapper.selectCafeImg(onlyCafeimg);
//        for(int i=0; i < na.length; i++) {
//        String imgname = na[i];
//            String uploadFolder = "/Users/limjaewoo/IdeaProjects/tempproject/src/main/resources/cafeimage/"+imgname;
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        InputStream imageStream = new FileInputStream(uploadFolder);
//        byte[] imgbyte = IOUtils.toByteArray(imageStream);
//
//        allCafeBean.setCafe_image(imgbyte);
//            imageStream.close();
//        }
//
//        List<CafeBean> list = cafeMapper.selectAllCafes(cafeBean);
//
//
//
//
//
//        allCafeBean.setCafeBeans(list);
//
//
//
//        return allCafeBean;
//    }


    //어드민 회원 관리

    @GetMapping("/admin/user")
    public List<AdminUserBean> adminuser() {

        return userMapper.selectUsers();
    }


//
////    어드민 카페 등록
//
//    @PostMapping("admin/addcafe")
//    public ResponseEntity addCafe(@Valid @RequestBody SimpleCafeBean simpleCafeBean, BindingResult bindingResult) {
//        ResponseEntity responseEntity;
//        if(bindingResult.hasErrors()){
//            List<ObjectError> allErrors = bindingResult.getAllErrors();
//            List<ErrorMessages> errorMessagesList = new ArrayList<>();
//            for(ObjectError objectError : allErrors){
//                String defaultMessage = objectError.getDefaultMessage();
//                String code = objectError.getCode();
//                com.cafe.admin.beans.ErrorMessages errorMessages = new com.cafe.admin.beans.ErrorMessages(defaultMessage,code);
//                errorMessagesList.add(errorMessages);
//            }
//            return new ResponseEntity(errorMessagesList, HttpStatus.BAD_REQUEST);
//        }
//
//        else
//
//        {
//            CafeBean cafeBean = new CafeBean();
//            cafeBean.setCafe_Name(simpleCafeBean.getCafe_Name());
//            cafeBean.setCafe_Location(simpleCafeBean.getCafe_Location());
//            cafeBean.setCafe_Menu(simpleCafeBean.getCafe_Menu());
//            cafeBean.setCafe_Information(simpleCafeBean.getCafe_Information());
//            cafeBean.setIs_Enable_Bean_Choice(simpleCafeBean.getIs_Enable_Bean_Choice());
//            cafeBean.setCafe_Mood(simpleCafeBean.getCafe_Mood());
//            cafeBean.setIs_Enable_Buy_Bean(simpleCafeBean.getIs_Enable_Buy_Bean());
//            cafeBean.setIs_Enable_Handdrip(simpleCafeBean.getIs_Enable_Handdrip());
//            cafeBean.setCafe_Image(simpleCafeBean.getCafe_Image());
//            cafeBean.setCafe_Sns(simpleCafeBean.getCafe_Sns());
//            cafeMapper.addCafe(cafeBean);
//
//            responseEntity= new ResponseEntity(cafeBean, HttpStatus.OK);
//        }
//        return responseEntity;
//
//    }


//  어드민 카페 등록dd

    @PostMapping(value = "admin/addcafe")

    public ResponseEntity addCafe(@Valid SimpleCafeBean simpleCafeBean, BindingResult bindingResult) {
        ResponseEntity responseEntity;
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            List<ErrorMessages> errorMessagesList = new ArrayList<>();
            for (ObjectError objectError : allErrors) {
                String defaultMessage = objectError.getDefaultMessage();
                String code = objectError.getCode();
                com.cafe.admin.beans.ErrorMessages errorMessages = new com.cafe.admin.beans.ErrorMessages(defaultMessage, code);
                errorMessagesList.add(errorMessages);
            }
            return new ResponseEntity(errorMessagesList, HttpStatus.BAD_REQUEST);
        } else {

            System.out.println("update Ajax post....");
            String uploadFolder = "/Users/limjaewoo/IdeaProjects/tempproject/src/main/resources/cafeimage";
            System.out.println(simpleCafeBean.getCafe_image());

            String uploadFileName = simpleCafeBean.getCafe_image().getOriginalFilename();


            //iE has file path
            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
            UUID uuid = UUID.randomUUID();
            String filen = uuid.toString() + "_" + "jam"+".png";
            File saveFile = new File(uploadFolder, filen);


            try {
                simpleCafeBean.getCafe_image().transferTo(saveFile);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // 데이터 베이스 처리를 현재 위치에서 처리

            CafeBean cafeBean = new CafeBean();
            cafeBean.setCafe_name(simpleCafeBean.getCafe_name());
            cafeBean.setCafe_location(simpleCafeBean.getCafe_location());
            cafeBean.setCafe_menu(simpleCafeBean.getCafe_menu());
            cafeBean.setCafe_information(simpleCafeBean.getCafe_information());
            cafeBean.setIs_enable_bean_choice(simpleCafeBean.getIs_enable_bean_choice());
            cafeBean.setCafe_mood(simpleCafeBean.getCafe_mood());
            cafeBean.setIs_enable_buy_bean(simpleCafeBean.getIs_enable_buy_bean());
            cafeBean.setIs_enable_handdrip(simpleCafeBean.getIs_enable_handdrip());
            cafeBean.setCafe_image(filen);
            cafeBean.setCafe_sns(simpleCafeBean.getCafe_sns());
            cafeMapper.addCafe(cafeBean);

            return new ResponseEntity(cafeBean, HttpStatus.OK);

        }
    }

//홈에있는거 테스트용

    @PostMapping(value = "adminaddcafe")

    public void addCafe11(@Valid SimpleCafeBean simpleCafeBean, BindingResult bindingResult) {
        ResponseEntity responseEntity;

        System.out.println("update Ajax post....");
        String uploadFolder = "/Users/limjaewoo/IdeaProjects/tempproject/src/main/resources/cafeimage";
        System.out.println(simpleCafeBean.getCafe_image());

        String uploadFileName = simpleCafeBean.getCafe_image().getOriginalFilename();


        //iE has file path
        uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
        UUID uuid = UUID.randomUUID();
        String filen = uuid.toString() + "_" + uploadFileName;
        File saveFile = new File(uploadFolder, filen);


        try {
            simpleCafeBean.getCafe_image().transferTo(saveFile);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }


    //어드민 유저카페등록 요청

    @GetMapping("admin/addcafe/byuser")
    public List<ByUserBean> byuser() {

        return cafeMapper.selectbyusers();
    }


    //어드민 유저 카페등록 수락

    @PostMapping("admin/addcafe/byuser")
    public ResponseEntity updateCafe(@Valid SimpleUserCafeBean simpleUserCafeBean, BindingResult bindingResult) {
        ResponseEntity responseEntity;
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            List<ErrorMessages> errorMessagesList = new ArrayList<>();
            for (ObjectError objectError : allErrors) {
                String defaultMessage = objectError.getDefaultMessage();
                String code = objectError.getCode();
                com.cafe.admin.beans.ErrorMessages errorMessages = new com.cafe.admin.beans.ErrorMessages(defaultMessage, code);
                errorMessagesList.add(errorMessages);
            }
            return new ResponseEntity(errorMessagesList, HttpStatus.BAD_REQUEST);
        } else {

            System.out.println("update Ajax post....");
            String uploadFolder = "/Users/limjaewoo/IdeaProjects/tempproject/src/main/resources/cafeimage";
            System.out.println(simpleUserCafeBean.getCafe_image());

            String uploadFileName = simpleUserCafeBean.getCafe_image().getOriginalFilename();


            //iE has file path
            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
            UUID uuid = UUID.randomUUID();
            String filen = uuid.toString() + "_" + uploadFileName;
            File saveFile = new File(uploadFolder, filen);


            try {
                simpleUserCafeBean.getCafe_image().transferTo(saveFile);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // 데이터 베이스 처리를 현재 위치에서 처리

            CafeBean cafeBean = new CafeBean();
            cafeBean.setCafe_id(simpleUserCafeBean.getCafe_id());
            cafeBean.setUser_id(simpleUserCafeBean.getUser_id());
            cafeBean.setCafe_name(simpleUserCafeBean.getCafe_name());
            cafeBean.setCafe_location(simpleUserCafeBean.getCafe_location());
            cafeBean.setCafe_menu(simpleUserCafeBean.getCafe_menu());
            cafeBean.setCafe_information(simpleUserCafeBean.getCafe_information());
            cafeBean.setIs_enable_bean_choice(simpleUserCafeBean.getIs_enable_bean_choice());
            cafeBean.setCafe_mood(simpleUserCafeBean.getCafe_mood());
            cafeBean.setIs_enable_buy_bean(simpleUserCafeBean.getIs_enable_buy_bean());
            cafeBean.setIs_enable_handdrip(simpleUserCafeBean.getIs_enable_handdrip());
            cafeBean.setIs_checked(simpleUserCafeBean.getIs_checked());
            cafeBean.setCafe_image(filen);
            cafeBean.setCafe_rate(simpleUserCafeBean.getCafe_rate());
            cafeBean.setCafe_sns(simpleUserCafeBean.getCafe_sns());
            cafeMapper.updatebyuser(cafeBean);

            responseEntity = new ResponseEntity(cafeBean, HttpStatus.OK);
        }
        return responseEntity;

    }


//    @PostMapping("/views/upload1")
//    public String upload(@RequestParam("file") MultipartFile file) {
//
//        System.out.println("파일 이름 : " + file.getOriginalFilename());
//        System.out.println("파일 크기 : " + file.getSize());
//
//        Image image = new Image();
//        image.setImage(file.getOriginalFilename());
//        image.setImage_size(file.getSize());
//
//        cafeMapper.imageUp(image);
//        // 데이터 베이스에 정보를 저장하는 건
//        // 이렇게 file의 get 메소드를 활용해 필요한 정보들을 가져오고
//        // 그걸 DTO에 담아 insert하면 된다.
//        // 간단한거니 후의 과정은 생략하고 파일로 서버에 저장하는 걸 보자면
//
//        try (
//                // 맥일 경우
//                FileOutputStream fos = new FileOutputStream("/tmp/" + file.getOriginalFilename());
//                // 윈도우일 경우
////                FileOutputStream fos = new FileOutputStream("c:/tmp/" + file.getOriginalFilename());
//                // 파일 저장할 경로 + 파일명을 파라미터로 넣고 fileOutputStream 객체 생성하고
//                InputStream is = file.getInputStream();) {
//            // file로 부터 inputStream을 가져온다.
//
//            int readCount = 0;
//            byte[] buffer = new byte[1024];
//            // 파일을 읽을 크기 만큼의 buffer를 생성하고
//            // ( 보통 1024, 2048, 4096, 8192 와 같이 배수 형식으로 버퍼의 크기를 잡는 것이 일반적이다.)
//
//            while ((readCount = is.read(buffer)) != -1) {
//                //  파일에서 가져온 fileInputStream을 설정한 크기 (1024byte) 만큼 읽고
//
//                fos.write(buffer, 0, readCount);
//                // 위에서 생성한 fileOutputStream 객체에 출력하기를 반복한다
//            }
//        } catch (Exception ex) {
//            throw new RuntimeException("file Save Error");
//        }
//
//        return "uploadok";
//    }





}








