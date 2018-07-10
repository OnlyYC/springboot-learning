package com.liaoyb.springboot.dao;

import com.liaoyb.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author liaoyb
 * @date 2018-07-07 10:51
 */
public interface UserRepository extends JpaRepository<User, Long>{
    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    List<User> findByNameLike(String name);

    /**
     * 自定义查询
     *
     * @param name
     * @return
     */
    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

}
