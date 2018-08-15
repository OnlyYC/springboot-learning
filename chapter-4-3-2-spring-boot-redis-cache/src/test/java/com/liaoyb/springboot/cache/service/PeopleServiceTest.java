package com.liaoyb.springboot.cache.service;

import com.liaoyb.springboot.cache.RedisCacheApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Slf4j
public class PeopleServiceTest extends RedisCacheApplicationTests {
    @Autowired
    private PeopleService peopleService;

    @Test
    public void test() {
        String id = UUID.randomUUID().toString();
        String people = peopleService.findPeopleById(id);
        log.info("查询people:{}", people);
        people = peopleService.findPeopleById(id);
        log.info("查询people:{}", people);
    }
}
