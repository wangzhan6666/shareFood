package com.wu.demo.fileupload.demo.controller;

import com.wu.demo.fileupload.demo.model.Comment;
import com.wu.demo.fileupload.demo.model.Mess;
import com.wu.demo.fileupload.demo.repository.CommentRepository;
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

    @Autowired
    CommentRepository commentRepository;
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

            List<Comment> comments = commentRepository.selectCommentByMid(m.getMid());
            /*List<Comment> comments1 = comments;
            for (Comment c : comments1){
                String[] n2 = c.getComment().split("~");

                System.out.println("n2.toString()   "+n2.toString());

                for (int i = 0; i < n2.length; i++) {
                    System.out.println("c.getComment()     "+c.getComment());
                    c.setComment(n2[i]);

                    System.out.println("n2[i]         "+n2[i]);

                    System.out.println("c.getComment()第一次哦：     "+c.getComment());
                }
                System.out.println("c.getComment()第二次哦：     "+c.getComment());
            }*/

            m.setCommentList(comments);
        }

        model.addAttribute("newMesses",messes);
        model.addAttribute("nowName",String.valueOf(session.getAttribute("pname")));
        return "index";
    }


}
