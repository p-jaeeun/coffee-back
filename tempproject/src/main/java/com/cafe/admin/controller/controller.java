package com.cafe.admin.controller;

import com.cafe.admin.beans.AdminUserBean;
import com.cafe.admin.beans.Image;
import com.cafe.admin.mapper.CafeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class controller {



@Autowired
    CafeMapper cafeMapper;


    @GetMapping("/")
    public String home() {

        return "home";
    }

    @PostMapping("/views/userphoto")
    public void imageUp(MultipartFile[] uploadFile, Image vo)
    {
        System.out.println("update Ajax post....");
        String uploadFolder = "/Users/limjaewoo/imagesave/userphoto";

        for(MultipartFile multipartFile : uploadFile) {
            System.out.println("----------------------------");
            System.out.println("Upload File Name: "+multipartFile.getOriginalFilename());
            System.out.println("Upload File Size: "+multipartFile.getSize());
            String uploadFileName = multipartFile.getOriginalFilename();

            //iE has file path
            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);

            System.out.println("only file name(for IE): "+uploadFileName);

            File saveFile = new File(uploadFolder,uploadFileName);


            try {
                Map<String, Object> hmap = new HashMap<String, Object>();
                hmap.put("img", vo.getUploadFile().getBytes());
                cafeMapper.imageUp(hmap);
                multipartFile.transferTo(saveFile);

            }catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
//
//    @PostMapping("/views/cafephoto")
//    public void imageUp1(MultipartFile[] Cafe_Image, Image vo)
//    {
//        System.out.println("update Ajax post....");
//        String uploadFolder = "/Users/limjaewoo/imagesave/cafephoto";
//
//        for(MultipartFile multipartFile : Cafe_Image) {
//            System.out.println("----------------------------");
//            System.out.println("Upload File Name: "+multipartFile.getOriginalFilename());
//            System.out.println("Upload File Size: "+multipartFile.getSize());
//            String uploadFileName = multipartFile.getOriginalFilename();
//
//            //iE has file path
//            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
//
//            System.out.println("only file name(for IE): "+uploadFileName);
//
//            File saveFile = new File(uploadFolder,uploadFileName);
//
//
//            try {
//                Map<String, Object> hmap = new HashMap<String, Object>();
//                hmap.put("img", vo.getUploadFile().getBytes());
//                cafeMapper.imageUp(hmap);
//                multipartFile.transferTo(saveFile);
//
//            }catch(Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//
//    @PostMapping("/views/uploadAjax")
//    public void imageUp2(MultipartFile[] uploadFile, Image vo)
//    {
//        System.out.println("update Ajax post....");
//        String uploadFolder = "/Users/limjaewoo/imagesave/reviewphoto";
//
//        for(MultipartFile multipartFile : uploadFile) {
//            System.out.println("----------------------------");
//            System.out.println("Upload File Name: "+multipartFile.getOriginalFilename());
//            System.out.println("Upload File Size: "+multipartFile.getSize());
//            String uploadFileName = multipartFile.getOriginalFilename();
//
//            //iE has file path
//            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
//
//            System.out.println("only file name(for IE): "+uploadFileName);
//
//            File saveFile = new File(uploadFolder,uploadFileName);
//
//
//            try {
//                Map<String, Object> hmap = new HashMap<String, Object>();
//                hmap.put("img", vo.getUploadFile().getBytes());
//                cafeMapper.imageUp(hmap);
//                multipartFile.transferTo(saveFile);
//
//            }catch(Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }


//    @PostMapping("/views/upload")
//    public String imageUp(Image vo) {
//        try {
//            Map<String, Object> hmap = new HashMap<String, Object>();
//            hmap.put("img", vo.getUploadFile().getBytes());
//            cafeMapper.imageUp(hmap);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "home";
//    }

//    @GetMapping("/home")
//    public String getFile(Model model) {
//        return "/views/home";
//    }
//    @PostMapping("/home")
//    public String postFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) throws IOException{
//        String fileName = file.getOriginalFilename();
//
//        File destinationFile = new File("/Users/limjaewoo");
//        destinationFile.getParentFile().mkdir();
//        file.transferTo(destinationFile);
//
//        String message = fileName + "is Upload.";
//        redirectAttributes.addFlashAttribute("message",message);
//
//        return "redirect:/home";
//    }



//
//
//    @RequestMapping(value="/views/view")
//    public String view(){
//        return "view";
//    }
//    @RequestMapping(value="/getByteImage")
//    public ResponseEntity<byte[]> getByteImage() {
//        Map<String, Object> map = cafeMapper.getByteImage();
//        byte[] imageContent = (byte[]) map.get("img");
//        final HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        headers.setContentType(MediaType.IMAGE_JPEG);
//        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
//    }

@Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/ccc")
    public void loadResource() {
        try{
            Resource[] resources = applicationContext.getResources("file:/Users/limjaewoo/IdeaProjects/tempproject/src/main/resources/cafeimage");
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
