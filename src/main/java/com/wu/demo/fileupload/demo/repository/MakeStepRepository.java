package com.wu.demo.fileupload.demo.repository;

import com.wu.demo.fileupload.demo.model.MakeStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Classname MakeStepRepository
 * @Description TODO
 * @Date 2020/4/18 15:21
 * @Created by wangzhan
 */
@Transactional
public interface MakeStepRepository extends JpaRepository<MakeStep,Integer> {

    /*
     * @Description 添加美食制作的步骤
     * */
    @Modifying
    @Query(value = "insert into MakeStep(title,step) values (?1,?2)", nativeQuery = true)
    void addMakeStep(String title, String step);

    /*
     * @Description 根据title查找美食制作的步骤
     * */
    @Query(value = "select m from MakeStep m where m.title=?1")
    MakeStep selectMakeStepByTitle(String title);
    @Query(value = "select m from MakeStep m where m.sid=?1")
    MakeStep selectMakeStepBySid(int sid);

    /*
     * @Description 查找所有美食制作的步骤
     * */
    @Query(value = "select m from MakeStep m")
    List<MakeStep> selectAllMakeStep();


}
