package com.liaoyb.springboot.cache.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * cacheNames指定@Cacheable的value值都是people
 */
@Slf4j
@CacheConfig(cacheNames = "people")
@Service
public class PeopleService {
    @Cacheable(key = "'people'.concat(#id)")
    public String findPeopleById(String id) {
        log.info("findPeopleById from db, id:" + id);
        return "people===>" + id;
    }
}
