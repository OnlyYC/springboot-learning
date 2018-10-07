package com.liaoyb.zookeeper.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceInstanceBuilder;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liaoyb
 * @date 2018-10-07 10:43
 */
@Slf4j
@Controller
@Configuration
public class ServerController {
    @Autowired
    private CuratorFramework client;

    /**
     * 服务注册(会在/service/myserver创建临时节点)
     */
    @PostConstruct
    public void registerService() throws Exception {
        //通过address方法设置服务地址，如果没有调用，会自动设置本机地址
        ServiceInstanceBuilder<Map> service = ServiceInstance.builder();
        service.address("127.0.0.2");
        service.port(8080);
        service.name("myserver");

        Map<String, String> config = new HashMap<>();
        config.put("url", "/api/v3/server");
        //额外信息
        service.payload(config);

        //basePath 指定服务注册的根节点
        ServiceInstance<Map> instance = service.build();
        ServiceDiscovery<Map> serviceDiscovery = ServiceDiscoveryBuilder.builder(Map.class)
                .client(client).serializer(new JsonInstanceSerializer<>(Map.class))
                .basePath("/service").build();

        //服务注册
        serviceDiscovery.registerService(instance);
        serviceDiscovery.start();
    }


    /**
     * 获取服务
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findService")
    @ResponseBody
    public Collection<ServiceInstance<Map>> findService() throws Exception {
        ServiceDiscovery<Map> serviceDiscovery = ServiceDiscoveryBuilder.builder(Map.class)
                .client(client).serializer(new JsonInstanceSerializer<>(Map.class))
                .basePath("/service").build();

        serviceDiscovery.start();

        //获取当前所有服务
        Collection<ServiceInstance<Map>> all = serviceDiscovery.queryForInstances("myserver");
        //todo 可以通过算法来公平调度服务
        return all;
    }


}
