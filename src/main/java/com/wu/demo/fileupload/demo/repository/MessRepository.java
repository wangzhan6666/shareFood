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
    @Query(value = "insert into mess(classify,description,imgname) values(?1,?2,?3)",nativeQuery = true)
    void addMess(String classify, String description,String imgname);

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
    @Query(value = "select m from Mess m order by m.mid desc")
    List<Mess> selectAllMess();

    /*
     * @Description 根据mid查询文章
     *
     * */
    @Query(value = "select m from Mess m where m.mid=?1")
    Mess selectMessByMid(int mid);

    /*
     * @Description 按照类别查询文章
     *
     * */
    @Query(value = "select m from Mess m where m.classify=?1")
    List<Mess> selectMessByClassify(String classify);

}
