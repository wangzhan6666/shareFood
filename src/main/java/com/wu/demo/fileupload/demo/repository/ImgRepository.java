/*
package com.wu.demo.fileupload.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

*/
/**
 * @Classname ImgRepository
 * @Description TODO
 * @Date 2020/4/14 22:12
 * @Created by wangzhan
 *//*

@Transactional
public interface ImgRepository extends JpaRepository<Img,Integer> {

    */
/*
     * @Description 添加图片名字
     * *//*

    @Modifying
    @Query(value = "insert into img(imgname) values(?1)",nativeQuery = true)
    void addImg(String imgname);

    */
/*
     * @Description 根据名字查找整个内容
     * *//*

    @Query(value = "select i from Img i where i.imgname=?1")
    Img selectImgByName(String imgname);

    */
/*
     * @Description 查找所有图片
     * *//*

    @Query(value = "select i from Img i,Mess m where i.mess.mid=m.mid group by i.mess.description")
    List<Img> selectAllImg();


}
*/
