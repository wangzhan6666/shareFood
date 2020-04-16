package com.wu.demo.fileupload.demo.controller;

import com.wu.demo.fileupload.demo.model.Mess;
import com.wu.demo.fileupload.demo.repository.MessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname IndexController
 * @Description TODO
 * @Date 2020/4/15 8:07
 * @Created by wangzhan
 */
//@RestController
@Controller
public class IndexController {

    /*@Autowired
    ImgRepository imgRepository;*/
    @Autowired
    MessRepository messRepository;

    @RequestMapping(value = "/")
    public String toIndex(Model model, HttpSession session){

        /*Object pname = session.getAttribute("pname");
        if (pname == null){
            return "login";
        }*/

        List<Mess> messes = messRepository.selectAllMess();

        List<Mess> newMesses = null;

        for (Mess m : messes){
            String[] n = m.getImgname().split(",");
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n.length; i++) {
                list.add(n[i]);
            }
            m.setNameList(list);
            m.setImglength(n.length);
            System.out.println("n.length       "+n.length);
            System.out.println("m.getNameList().toString()   "+m.getNameList().toString());
            //System.out.println(""+newMesses.toString());
        }

        model.addAttribute("newMesses",messes);
        return "index";
    }


}
