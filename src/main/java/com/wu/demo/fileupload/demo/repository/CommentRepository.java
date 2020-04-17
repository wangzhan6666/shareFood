package com.wu.demo.fileupload.demo.repository;

import com.wu.demo.fileupload.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Classname CommentRepository
 * @Description TODO
 * @Date 2020/4/17 9:26
 * @Created by wangzhan
 */
@Transactional
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    /*
     * @Description 添加评论
     *
     * */
    @Modifying
    @Query(value = "insert into Comment(comment) values(?1)",nativeQuery = true)
    void addComment(String comment);

    /*
     * @Description 根据评论查找整个信息
     *
     * */
    @Query(value = "select c from Comment c where c.comment=?1")
    Comment selectCommentByCom(String comment);
    @Query(value = "select c from Comment c where c.comment=?1")
    List<Comment> selectListCommentByCom(String comment);
    @Query(value = "select c from Comment c where c.comment like ?1%")
    List<Comment> selectListCommentByLikeCom(String comment);

    /*
     * @Description 根据mid查找整个信息
     *
     * */
    @Query(value = "select c from Comment c where c.mess.mid=?1")
    List<Comment> selectCommentByMid(int mid);

    /*
     * @Description 根据cid删除评论
     *
     * */
    @Modifying
    @Query(value = "delete c from Comment c where c.cid=?1", nativeQuery = true)
    void deleteByCid(int cid);

}
