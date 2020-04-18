package com.wu.demo.fileupload.demo.model;

import javax.persistence.*;

/**
 * @Classname Comment
 * @Description TODO    评论内容
 * @Date 2020/4/17 8:54
 * @Created by wangzhan
 */
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "cid")
    private int cid;

    @Column(name = "comment")
    private String comment;

    //多篇文章属于一个用户
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @JoinColumn(name="fpid")//设置在article表中的关联字段(外键)
    private Person fatherPerson;//所属作者

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @JoinColumn(name="cpid")//设置在article表中的关联字段(外键)
    private Person childPerson;//所属作者

    //多篇文章属于一个用户
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @JoinColumn(name="messid")//设置在article表中的关联字段(外键)
    private Mess mess;//所属作者

    public Comment() {
    }

    public Comment(String comment) {
        this.comment = comment;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Person getFatherPerson() {
        return fatherPerson;
    }

    public void setFatherPerson(Person fatherPerson) {
        this.fatherPerson = fatherPerson;
    }

    public Person getChildPerson() {
        return childPerson;
    }

    public void setChildPerson(Person childPerson) {
        this.childPerson = childPerson;
    }

    public Mess getMess() {
        return mess;
    }

    public void setMess(Mess mess) {
        this.mess = mess;
    }
}
