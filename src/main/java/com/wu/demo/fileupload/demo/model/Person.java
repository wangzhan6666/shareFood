package com.wu.demo.fileupload.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Classname Person
 * @Description TODO    用户
 * @Date 2020/4/14 15:20
 * @Created by wangzhan
 */
@Entity
@Table(name="person")
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "pid")
    private int pid;

    @Column(name = "pname")
    private String name;

    @Column(name = "password")
    private String password;

    //一个用户有多篇文章
    @OneToMany(mappedBy = "person",cascade={CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE}
            ,fetch=FetchType.LAZY)
    //级联保存、更新、删除、刷新;延迟加载。当删除用户，会级联删除该用户的所有文章
    //拥有mappedBy注解的实体类为关系被维护端
    //mappedBy="author"中的author是Article中的author属性
    private Set<Mess> messes = new HashSet<>();//文章列表


    public Person() {
    }

    public Person(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Mess> getMesses() {
        return messes;
    }

    public void setMesses(Set<Mess> messes) {
        this.messes = messes;
    }
}
