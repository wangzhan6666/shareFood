package com.wu.demo.fileupload.demo.model;

import javax.persistence.*;

/**
 * @Classname MakeStep
 * @Description TODO
 * @Date 2020/4/17 23:50
 * @Created by wangzhan
 */
@Entity
@Table(name = "makestep")
public class MakeStep {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "sid")
    private int sid;

    @Column(name = "title")
    private String title;

    @Column(name = "step")
    private String step;


    //多篇文章属于一个用户
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @JoinColumn(name="personid")//设置在article表中的关联字段(外键)
    private Person makePerson;//所属作者

    public MakeStep() {
    }

    public MakeStep(String title, String step) {
        this.title = title;
        this.step = step;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public Person getMakePerson() {
        return makePerson;
    }

    public void setMakePerson(Person makePerson) {
        this.makePerson = makePerson;
    }
}
