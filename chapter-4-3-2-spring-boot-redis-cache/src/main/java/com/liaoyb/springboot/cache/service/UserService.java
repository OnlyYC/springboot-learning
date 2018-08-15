package com.liaoyb.springboot.cache.service;

import com.liaoyb.springboot.cache.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class UserService {
    private static Map<String, User> userMap = new ConcurrentHashMap<String, User>();

    static {
        userMap.put("1", new User("id", "taobi", 24));
        userMap.put("2", new User("2", "taobi", 24));
        userMap.put("3", new User("3", "zhu", 24));
    }

    @Cacheable(value = "user", key = "'user'.concat(#id)")
    public User findUserById(String id) {
        log.info("==========findUserById from db,id:{}", id);
        return userMap.get(id);
    }

    @Cacheable(value = "user")
    public List<User> findAllUser() {
        log.info("==========findAllUser from db");
        return new ArrayList<>(userMap.values());
    }

    /**
     * 返回值放在缓存中
     *
     * @param user
     */
    @CachePut(value = "user", key = "'user'.concat(#user.id)")
    public User put(User user) {
        log.info("put user to db,id:{},user:{}", user.getId(), user);
        userMap.put(user.getId(), user);
        return user;
    }

    @CacheEvict(value = "user", key = "'user'.concat(#id)")
    public void remove(String id) {
        log.info("remove user from db,id:{}", id);
        userMap.remove(id);
    }

    @CacheEvict(value = "user", key = "'user'.concat(#id)")
    public User upuser(String id) {
        log.info("update user, id:{}", id);
        User d = userMap.get(id);
        d.setName("000000000000000000000000000000000000000000000000");
        return d;
    }

    @CacheEvict(value = "user", allEntries = true)
    public void deleteAll() {
        userMap.clear();
    }

}
