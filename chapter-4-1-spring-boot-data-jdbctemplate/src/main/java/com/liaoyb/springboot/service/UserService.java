package com.liaoyb.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author liaoyb
 * @date 2018-07-02 21:27
 */
@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(){
        jdbcTemplate.update("create table user(id VARCHAR(64) PRIMARY KEY ,name VARCHAR(50),age INT )");
    }

    public void add(String id,String name, Integer age){
        jdbcTemplate.update("insert into user(id,name,age) values(?,?,?)",id, name, age);
    }

    public void deleteByName(String name){
        jdbcTemplate.update("delete from user where name=?", name);
    }

    public Integer getAllUserCount(){
        return jdbcTemplate.queryForObject("select count(1) from user", Integer.class);
    }

    public void deleteAllUsers() {
        jdbcTemplate.update("delete from user");
    }
}
