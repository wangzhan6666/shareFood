package com.wu.demo.fileupload.demo.controller;

import com.wu.demo.fileupload.demo.model.Comment;
import com.wu.demo.fileupload.demo.model.Mess;
import com.wu.demo.fileupload.demo.model.Person;
import com.wu.demo.fileupload.demo.model.Shoucang;
import com.wu.demo.fileupload.demo.repository.CommentRepository;
import com.wu.demo.fileupload.demo.repository.MessRepository;
import com.wu.demo.fileupload.demo.repository.PersonRepository;
import com.wu.demo.fileupload.demo.repository.ShoucangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ShoucangController
 * @Description TODO
 * @Date 2020/4/25 17:55
 * @Created by wangzhan
 */
@Controller
public class ShoucangController {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    MessRepository messRepository;
    @Autowired
    ShoucangRepository shoucangRepository;

    @RequestMapping(value = "/shoucang")
    /*@ResponseBody*/
    public String shoucang(@RequestParam("messid") String messid, Model model, HttpSession session){

        Object pname = session.getAttribute("pname");

        //根据用户名查找用户
        Person person = personRepository.selectPasswordByPname((String) pname);
        //根据文章id查找文章
        Mess mess = messRepository.selectMessByMid(Integer.parseInt(messid));

        Shoucang shoucang = shoucangRepository.findTopByOrderByScidDesc();

        System.out.println(" =========="+shoucang);
        int i;
        if (shoucang == null){
            i = 1;
        }else {
            i = shoucang.getScid() + 1;
        }


        //System.out.println(shoucang.getScid()+"============="+i);

        Shoucang sc = new Shoucang();
        sc.setScid(i);

        Shoucang shoucang1 = shoucangRepository.selectShoucangByPidAndMid(person.getPid(), mess.getMid());
        if (shoucang1 != null){
            model.addAttribute("message","已经收藏过了哦");
            return "forward:/";
        }


        sc.setScPerson(person);
        sc.setScmess(mess);

        person.getShoucangs().add(sc);
        personRepository.save(person);
        mess.getShoucangs().add(sc);
        messRepository.save(mess);

        return "forward:/myshoucang";
    }

    @RequestMapping(value = "/myshoucang")
    public String myshoucang(HttpSession session, Model model){

        Object pname = session.getAttribute("pname");

        //根据用户名查找用户
        Person person = personRepository.selectPasswordByPname((String) pname);

        //查找收藏记录
        List<Shoucang> shoucangs = shoucangRepository.selectShoucangByPid(person.getPid());

        for(Shoucang sc : shoucangs){
            String[] n = sc.getScmess().getImgname().split(",");

            List<String> list = new ArrayList<>();
            for (int i = 0; i < n.length; i++) {
                list.add(n[i]);
            }
            sc.getScmess().setNameList(list);
            sc.getScmess().setImglength(n.length);

            List<Comment> comments = commentRepository.selectCommentByMid(sc.getScmess().getMid());

            sc.getScmess().setCommentList(comments);
        }
        model.addAttribute("shoucangs",shoucangs);

        return "shoucang";
    }


}
