package com.wu.demo.fileupload.demo.controller;

import com.wu.demo.fileupload.demo.model.Comment;
import com.wu.demo.fileupload.demo.model.Mess;
import com.wu.demo.fileupload.demo.model.Person;
import com.wu.demo.fileupload.demo.repository.CommentRepository;
import com.wu.demo.fileupload.demo.repository.MessRepository;
import com.wu.demo.fileupload.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Classname CommentController
 * @Description TODO
 * @Date 2020/4/17 9:50
 * @Created by wangzhan
 */
@Controller
public class CommentController {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    MessRepository messRepository;
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/comment")
    public String comment(@RequestParam("comment") String comment, @RequestParam("mid") int mid, @RequestParam("pid") int pid
            , Model model, HttpSession session){
        String newComment = comment;

        Object pname = session.getAttribute("pname");
        if (pname == null){
            return "login";
        }
        //获取该用户
        Person person = personRepository.selectPasswordByPname(String.valueOf(pname));
        Person fatherPerson = null;
        if (pid != 0){
            fatherPerson = personRepository.selecPersonByPid(pid);
        }


        //获取文章
        Mess mess = messRepository.selectMessByMid(mid);
        //添加评论
        String likeComment = comment + "~";
        List<Comment> comments = commentRepository.selectListCommentByLikeCom(likeComment);
        System.out.println("comments.size()     "+comments.size());
        String test = "~";
        if (comments.size() != 0){
            for (int i = 0; i < comments.size(); i++) {
                test += "~" ;
            }
            newComment += test;
        }

        List<Comment> commentList = commentRepository.selectListCommentByCom(comment);
        if (commentList.size() == 1){
            newComment += "~";
        }

        commentRepository.addComment(newComment);
        Comment c2 = commentRepository.selectCommentByCom(newComment);

        //关联表
        c2.setMess(mess);
        mess.getComments().add(c2);
        c2.setChildPerson(person);
        person.getChildComments().add(c2);

        if (pid != 0){
            c2.setFatherPerson(fatherPerson);
            fatherPerson.getFatherComments().add(c2);
            personRepository.save(fatherPerson);
        }

        messRepository.save(mess);
        personRepository.save(person);


        return "forward:/";
    }

    //删除评论
    @RequestMapping(value = "/deleteComment")
    public String deleteComment(@RequestParam("cid") int cid, HttpSession session){
        Object pname = session.getAttribute("pname");
        if (pname == null){
            return "login";
        }
        commentRepository.deleteByCid(cid);
        return "forward:/";
    }



}
