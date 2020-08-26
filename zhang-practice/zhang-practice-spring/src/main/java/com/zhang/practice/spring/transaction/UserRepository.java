//package com.zhang.practice.spring.transaction;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// * @ClassName UserRepository
// * @Description:
// * @Author: zhangzh
// * @Date 2018/9/22 18:02
// */
//public interface UserRepository extends JpaRepository<User, Long> {
//
//    User findByName(String name);
//
//    User findByNameAndAge(String name, Integer age);
//
//    @Query("from User u where u.name=:name")
//    User findUser(@Param("name") String name);
//
//
//}
