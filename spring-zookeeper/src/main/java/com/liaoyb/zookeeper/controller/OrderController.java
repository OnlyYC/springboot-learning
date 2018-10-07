package com.liaoyb.zookeeper.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

/**
 * 实现分布式锁
 *
 * @author liaoyb
 * @date 2018-10-07 10:06
 */
@Slf4j
@Controller
public class OrderController {
    @Autowired
    private CuratorFramework client;


    /**
     * 会自动创建path节点，每个获取锁的操作都会在下面创建临时的序列节点，并选择序列最小的作为锁的拥有者（release会删除该临时节点）
     *
     * @param type
     */
    @RequestMapping("/makeOrderType")
    @ResponseBody
    public void makeOrderType(String type) {
        String path = "/lock/order/" + type;
        log.info("尝试执行订单操作 for:" + type);

        try {
            InterProcessMutex lock = new InterProcessMutex(client, path);
            if (lock.acquire(10, TimeUnit.MINUTES)) {
                try {
                    //todo 即使获得分布式锁，也应该检查数据是否已经被处理

                    //模拟耗时操作，5秒
                    TimeUnit.SECONDS.sleep(5);
                    log.info("订单执行完成 for:" + type);
                } finally {
                    lock.release();
                }
            }
        } catch (Exception e) {
            //zk 异常
            e.printStackTrace();
        }
    }
}
