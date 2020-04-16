package com.wu.demo.fileupload.demo.repository;

import com.wu.demo.fileupload.demo.model.Mess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Classname Mess
 * @Description TODO
 * @Date 2020/4/14 22:11
 * @Created by wangzhan
 */
@Transactional
public interface MessRepository extends JpaRepository<Mess, Integer> {

    /*
    * @Description 添加一篇文章的描述和图片名字
    * */
    @Modifying
    @Query(value = "insert into mess(description,imgname) values(?1,?2)",nativeQuery = true)
    void addMess(String description,String imgname);

    /*
     * @Description 根据文章描述查找文章
     *
     * */
    @Query(value = "select m from Mess m where m.description=?1")
    Mess selectMidByDescription(String description);

    /*
     * @Description 查询所有的文章
     *
     * */
    @Query(value = "select m from Mess m")
    List<Mess> selectAllMess();


}
