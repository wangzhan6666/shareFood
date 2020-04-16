package com.wu.demo.fileupload.demo.repository;

import com.wu.demo.fileupload.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * @Classname PersonRepository
 * @Description TODO
 * @Date 2020/4/14 22:11
 * @Created by wangzhan
 */
@Transactional
public interface PersonRepository extends JpaRepository<Person,Integer> {
}
