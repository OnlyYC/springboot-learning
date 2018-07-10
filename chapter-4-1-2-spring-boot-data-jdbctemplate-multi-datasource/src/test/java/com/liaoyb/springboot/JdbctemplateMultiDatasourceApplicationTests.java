package com.liaoyb.springboot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbctemplateMultiDatasourceApplicationTests {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate2;


    private boolean validateTableNameExist(JdbcTemplate jdbcTemplate, String schema, String tableName) {
//		select count(1)  from information_schema.columns where table_schema='csdb' and table_name='users'
        int tableNum = jdbcTemplate.queryForObject("select count(1)  from information_schema.columns where table_schema='" + schema + "' and table_name='" + tableName + "'", Integer.class);
        if (tableNum > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Before
    public void setUp() {
        //建表
        if (!validateTableNameExist(jdbcTemplate1, "test1", "user")) {
//			jdbcTemplate1.getDataSource().getConnection().getSchema()
            jdbcTemplate1.update("create table user(id VARCHAR(64) PRIMARY KEY ,name VARCHAR(50),age INT )");
        }
        if (!validateTableNameExist(jdbcTemplate2, "test2", "user")) {
            jdbcTemplate2.update("create table user(id VARCHAR(64) PRIMARY KEY ,name VARCHAR(50),age INT )");
        }

        //清理数据
        jdbcTemplate1.update("delete from user");
        jdbcTemplate2.update("delete from user");
    }

    @Test
    public void test() {
        // 插入数据
        jdbcTemplate1.update("insert into user(id,name,age) values(?, ?, ?)", 1, "aaa", 20);
        jdbcTemplate1.update("insert into user(id,name,age) values(?, ?, ?)", 2, "bbb", 30);

        // 往第二个数据源中插入一条数据，若插入的是第一个数据源，则会主键冲突报错
        jdbcTemplate2.update("insert into user(id,name,age) values(?, ?, ?)", 1, "aaa", 20);

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("2", jdbcTemplate1.queryForObject("select count(1) from user", String.class));

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("1", jdbcTemplate2.queryForObject("select count(1) from user", String.class));
    }


}
