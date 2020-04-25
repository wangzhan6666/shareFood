package com.wu.demo.fileupload.demo.repository;

import com.wu.demo.fileupload.demo.model.Shoucang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Classname ShoucangRepository
 * @Description TODO
 * @Date 2020/4/25 17:06
 * @Created by wangzhan
 */
@Transactional
public interface ShoucangRepository extends JpaRepository<Shoucang, Integer> {

    /*
     * @Description 查找最后一个数据
     * */
    Shoucang findTopByOrderByScidDesc();

    /*
     * @Description 查找收藏
     * */
    @Query(value = "select sc from Shoucang sc where sc.scPerson.pid=?1 and sc.scmess.mid=?2")
    Shoucang selectShoucangByPidAndMid(int personid, int messid);


    /*
     * @Description 查找收藏通过用户id
     * */
    @Query(value = "select sc from Shoucang sc where sc.scPerson.pid = ?1")
    List<Shoucang> selectShoucangByPid(int personid);

}
