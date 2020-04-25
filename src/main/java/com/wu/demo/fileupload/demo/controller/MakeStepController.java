package com.wu.demo.fileupload.demo.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wu.demo.fileupload.demo.model.MakeStep;
import com.wu.demo.fileupload.demo.model.Person;
import com.wu.demo.fileupload.demo.repository.MakeStepRepository;
import com.wu.demo.fileupload.demo.repository.PersonRepository;
import com.wu.demo.fileupload.demo.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname MakeStepController
 * @Description TODO
 * @Date 2020/4/18 15:28
 * @Created by wangzhan
 */
@Controller
public class MakeStepController {

    @Autowired
    MakeStepRepository makeStepRepository;
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("toMakeStep")
    public String toMakeStep(HttpSession session){
        Object pname = session.getAttribute("pname");
        if (pname == null){
            return "login";
        }
        return "addmakestep";
    }

    @RequestMapping("addMakeStep")
    public String add(@RequestParam("fileName") MultipartFile[] files, @RequestParam("title") String title, HttpSession session
            , HttpServletRequest request, @RequestParam("len") String len, Model model){

        int length = Integer.parseInt(len);
/*        String value = "";
        for (int i = 0; i <= length; i++) {
            value = request.getParameter(String.valueOf(i));
            //String file = request.getParameter("fileName"+i);
            System.out.println("value      "+value);
            //System.out.println("file        "+file);
        }
        // 要上传的目标文件存放路径
        String localPath = "D:/IDEA/save_date/FileUploadDemo-master/src/main/resources/static/img";

        for (MultipartFile f : files){
            System.out.println("in file value  "+value);
            FileUtils.upload(f, localPath, f.getOriginalFilename());
            System.out.println("f.getOriginalFilename()    "+f.getOriginalFilename());
        }*/

        String localPath = "D:/IDEA/save_date/FileUploadDemo-master/src/main/resources/static/img";
        List list = new ArrayList();
        String step = "";
        for (int i = 0; i <= length; i++) {
            list.add(request.getParameter(String.valueOf(i)));
            //list.add(files[i].getOriginalFilename());

            //修改文件名
            String newName = updateFileName(files[i]);
            FileUtils.upload(files[i], localPath, newName);

            list.add(newName);


            step += request.getParameter(String.valueOf(i))+"@@";
            step += newName+"@@";
        }

        Object pname = session.getAttribute("pname");
        Person person = personRepository.selectPasswordByPname(String.valueOf(pname));

        //添加美食制作步骤
        makeStepRepository.addMakeStep(title,step);
        //根据title查找美食制作步骤
        MakeStep makeStep = makeStepRepository.selectMakeStepByTitle(title);
        makeStep.setMakePerson(person);
        person.getMakeSteps().add(makeStep);

        personRepository.save(person);


        model.addAttribute("list",list);
        return "showmakestep";
    }

    //修改文件名
    private String updateFileName(MultipartFile file) {
        String uName = file.getOriginalFilename();
        int index = uName.lastIndexOf(".");  //获取 . 前面有多少位数
        String lastName = uName.substring(index,uName.length());  //文件后缀名
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String suffix = fmt.format(new Date());
        return suffix+lastName;
    }

    @RequestMapping("toSeeMakeStep")
    public String toSeeMakeStep(Model model){

        List<MakeStep> makeSteps = makeStepRepository.selectAllMakeStep();

        model.addAttribute("makeSteps",makeSteps);
        return "makesteptitle";
    }

    @RequestMapping("showthisstep")
    public String showthisstep(@RequestParam("sid") int sid, Model model){

        MakeStep makeStep = makeStepRepository.selectMakeStepBySid(sid);

        String[] n = makeStep.getStep().split("@@");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n.length; i++) {
            list.add(n[i]);
        }

        model.addAttribute("list",list);
        return "showmakestep";
    }


    //美食步骤查询
    @RequestMapping(value = "findmakestep")
    public String findmakestep(@RequestParam("name") String name, Model model){

        //模糊查询
        List<MakeStep> makeSteps = makeStepRepository.selectMakeStepByLikeTitle(name);
        model.addAttribute("makeSteps",makeSteps);
        return "makesteptitle";
    }



}
