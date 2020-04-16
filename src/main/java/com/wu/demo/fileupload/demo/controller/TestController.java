package com.wu.demo.fileupload.demo.controller;

//import com.wu.demo.fileupload.demo.model.FileModel;
import com.wu.demo.fileupload.demo.model.Mess;
import com.wu.demo.fileupload.demo.model.Person;
import com.wu.demo.fileupload.demo.repository.MessRepository;
import com.wu.demo.fileupload.demo.repository.PersonRepository;
import com.wu.demo.fileupload.demo.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
//@RestController
public class TestController {

    private final ResourceLoader resourceLoader;

    /*@Autowired
    ImgRepository imgRepository;*/
    @Autowired
    MessRepository messRepository;
    @Autowired
    PersonRepository personRepository;


    @Autowired
    public TestController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${web.upload-path}")
    private String path;

    /**
     * 跳转到文件上传页面
     * @return
     */

    @RequestMapping("test")
    public String toUpload(){
        return "test";
    }
    @RequestMapping("test2")
    public String toUpload2(HttpSession session, Model model){
        Object pname = session.getAttribute("pname");
        if (pname == null){
            String message = "你没登录，还想发表文章，做梦呢吧！！";
            model.addAttribute("message",message);
            return "login";
        }

        return "test2";
    }

    /**
     *
     * @param files 要上传的文件
     * @return
     */

/*    @RequestMapping("fileUpload")
    public String upload(@RequestParam("fileName") MultipartFile[] files, Map<String, Object> map, Model model){

        // 要上传的目标文件存放路径
//        String localPath = "E:/Develop/Files/Photos";
        String localPath = "D:/IDEA/save_date/FileUploadDemo-master/src/main/resources/static/img";
        // 上传成功或者失败的提示
        String msg = "";


    *//*if (FileUtils.upload(file, localPath, file.getOriginalFilename())){
            // 上传成功，给出页面提示
            msg = "上传成功！";
        }else {
            msg = "上传失败！";

        }*//*
    //List<FileModel> list = new ArrayList<FileModel>();
        String name ;
        for (MultipartFile file : files) {
            FileUtils.upload(file, localPath, file.getOriginalFilename());

            name = path + file.getOriginalFilename();
            System.out.println("输出：    "+name);



           // FileModel fileModel = new FileModel();
          //  fileModel.setName(file.getOriginalFilename());
            *//*try {
                fileModel.setPic(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }*//*

            //list.add(fileModel);
            //System.out.println("fileModel.getName()     "+fileModel.getName());
        }


        // 显示图片
        map.put("msg", msg);
        //map.put("fileName", list);

        //model.addAttribute("filesModel",list);

        return "forward:/test2";
    }*/

    @RequestMapping("fileUpload")
    public String upload(@RequestParam("fileName") MultipartFile[] files, @RequestParam("description") String description,
                         Model model, HttpSession session){

        Object pname = session.getAttribute("pname");
        if (pname == null){
            return "login";
        }

        // 要上传的目标文件存放路径
        String localPath = "D:/IDEA/save_date/FileUploadDemo-master/src/main/resources/static/img";

        Person person = personRepository.selectPasswordByPname(String.valueOf(pname));

        //List<Img> list = new ArrayList<Img>();
        String name = "";   //将所有图片名字用，号隔开，放在同一个字符串中
        for (MultipartFile file : files) {
            FileUtils.upload(file, localPath, file.getOriginalFilename());

            name += file.getOriginalFilename()+",";
        }
        System.out.println("name::::::::::::::::"+name);

        //保存文章描述
        messRepository.addMess(description,name);
        //根据文章描述查找该文章的ID
        Mess mess = messRepository.selectMidByDescription(description);
        System.out.println("测试："+mess.getMid()+"   "+mess.getDescription());

        //添加外键
        mess.setPerson(person);
        person.getMesses().add(mess);
        personRepository.save(person);

        List list = new ArrayList();
        String[] n = name.split(",");
        for (int i = 0; i < n.length; i++) {
            list.add(n[i]);
            System.out.println("输出结果：  "+n[i]);
            System.out.println("list输出： "+list.toString());
        }

        model.addAttribute("description",description);
        model.addAttribute("filesName",list);

        return "showmess";
    }




    /**
     * 显示单张图片
     * @return
     */
    @RequestMapping("show")
    public ResponseEntity showPhotos(String fileName){

        System.out.println("path + fileName    "+path + fileName);
        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
