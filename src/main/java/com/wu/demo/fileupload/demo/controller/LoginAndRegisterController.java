package com.wu.demo.fileupload.demo.controller;

import com.wu.demo.fileupload.demo.model.Person;
import com.wu.demo.fileupload.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @Classname LoginAndRegisterController
 * @Description TODO
 * @Date 2020/4/16 9:17
 * @Created by wangzhan
 */
@Controller
public class LoginAndRegisterController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "/tologin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam("pname") String pname, @RequestParam("password") String password
            , @RequestParam("fileName") MultipartFile[] files,  HttpSession session, Model model){

        //根据用户名查找密码
        Person person = personRepository.selectPasswordByPname(pname);
        //判断输入的密码和查找出来的密码是否相等
        if (password.equals(person.getPassword())){
            session.setAttribute("pname",pname);
            return "forward:/";
        }
        String message = "请再次确认用户名或密码！！";

        model.addAttribute("message",message);
        return "login";
    }

    @RequestMapping(value = "/toregister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping(value = "/register")
    public String register(@RequestParam("pname") String pname, @RequestParam("password") String password,
                        HttpSession session, Model model){

        //根据用户名查找用户
        Person person = personRepository.selectPasswordByPname(pname);

        System.out.println("person.getPname():::    "+person);

        String message;
        //判断输入的密码和查找出来的密码是否相等
        if (person == null){
            personRepository.addPerson(pname,password);
            message = "注册成功，请登录！！！";

            model.addAttribute("message",message);
            return "login";
        }
        message = "该用户已经存在，请登录！！！";

        model.addAttribute("message",message);
        return "login";
    }





}
