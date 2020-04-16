package com.wu.demo.fileupload.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Classname Mess
 * @Description TODO    发布的信息
 * @Date 2020/4/14 21:43
 * @Created by wangzhan
 */
@Entity
@Table(name="mess")
public class Mess {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "mid")
    private int mid;

    //存放文章内容
    @Column(name = "description")
    private String description;

    //存放图片名称
    @Column(name = "imgname")
    private String imgname;

    //存放一条文章有多少个图片
    @Transient
    private int imglength;

    //存放一条文章的图片名称
    @Transient
    private List<String> nameList;

    //多篇文章属于一个用户
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @JoinColumn(name="personid")//设置在article表中的关联字段(外键)
    private Person person;//所属作者

    /*//一篇文章有多张照片
    @OneToMany(mappedBy = "mess",cascade={CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE}
            ,fetch=FetchType.LAZY)
    //级联保存、更新、删除、刷新;延迟加载。当删除用户，会级联删除该用户的所有文章
    //拥有mappedBy注解的实体类为关系被维护端
    //mappedBy="author"中的author是Article中的author属性
    private Set<Img> imges = new HashSet<>();//文章列表*/



    public Mess() {
    }

    public Mess(String description, String imgname, int imglength) {
        this.description = description;
        this.imgname = imgname;
        this.imglength = imglength;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgname() {
        return imgname;
    }

    public void setImgname(String imgname) {
        this.imgname = imgname;
    }

    public int getImglength() {
        return imglength;
    }

    public void setImglength(int imglength) {
        this.imglength = imglength;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


}
