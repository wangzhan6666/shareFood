package com.wu.demo.fileupload.demo.model;

import javax.persistence.*;

/**
 * @Classname Shoucang
 * @Description TODO
 * @Date 2020/4/25 16:59
 * @Created by wangzhan
 */
@Entity
@Table(name = "shoucang")
public class Shoucang {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "scid")
    private int scid;



    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @JoinColumn(name="personid")//设置在article表中的关联字段(外键)
    private Person scPerson;//所属作者

    @ManyToOne(cascade={CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @JoinColumn(name="messid")//设置在article表中的关联字段(外键)
    private Mess scmess;


    public Shoucang() {
    }

    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    public Person getScPerson() {
        return scPerson;
    }

    public void setScPerson(Person scPerson) {
        this.scPerson = scPerson;
    }

    public Mess getScmess() {
        return scmess;
    }

    public void setScmess(Mess scmess) {
        this.scmess = scmess;
    }
}
