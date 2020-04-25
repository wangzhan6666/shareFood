package com.wu.demo.fileupload.demo.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wu.demo.fileupload.demo.model.Comment;
import com.wu.demo.fileupload.demo.model.Mess;
import com.wu.demo.fileupload.demo.model.Person;
import com.wu.demo.fileupload.demo.model.Shoucang;
import com.wu.demo.fileupload.demo.repository.CommentRepository;
import com.wu.demo.fileupload.demo.repository.MessRepository;
import com.wu.demo.fileupload.demo.repository.PersonRepository;
import com.wu.demo.fileupload.demo.repository.ShoucangRepository;
import com.wu.demo.fileupload.demo.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    PersonRepository personRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    MessRepository messRepository;
    @Autowired
    ShoucangRepository shoucangRepository;

    @RequestMapping(value = "/first")
    public String first() {
        return "first";
    }
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

            List<Comment> comments = commentRepository.selectCommentByMid(m.getMid());

            m.setCommentList(comments);
        }

        model.addAttribute("newMesses",messes);
        model.addAttribute("nowName",String.valueOf(session.getAttribute("pname")));
        return "index";
    }


    @RequestMapping(value = "/toclassify")
    public String toclassify(@RequestParam("classify") String classify, Model model, HttpSession session){

        //模糊查询
        List<Mess> messes = messRepository.selectMessByClassify(classify);

        List<Mess> newMesses = null;

        for (Mess m : messes){
            String[] n = m.getImgname().split(",");
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n.length; i++) {
                list.add(n[i]);
            }
            m.setNameList(list);
            m.setImglength(n.length);

            List<Comment> comments = commentRepository.selectCommentByMid(m.getMid());

            m.setCommentList(comments);
        }

        model.addAttribute("newMesses",messes);
        model.addAttribute("nowName",String.valueOf(session.getAttribute("pname")));
        return "index";
    }




}
