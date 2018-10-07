# SpringBoot集成Zookeeper


## 集成Curator
添加依赖
```xml
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-recipes</artifactId>
    <version>2.12.0</version>
</dependency>
```

创建Configuration类，创建一个CuratorFramework，用来完成所有的zk功能
```java
@Configuration
public class ZookeeperConfig {
    @Value("${zk.url}")
    private String zkUrl;

    @Bean
    public CuratorFramework getCuratorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkUrl, retryPolicy);
        client.start();
        return client;
    }
}
```



### Curator API
创建、检查节点是否存在、获取节点数据、设置节点数据

监听节点变化


## 场景

### 实现分布式锁
`InterProcessMutex`用acquire方法获取锁，以及用release释放锁，同其它锁一样，release方法需要放在finally代码块中，确保锁能正常释放。
会自动创建path节点，每个获取锁的操作都会在下面创建临时的序列节点，并选择序列最小的作为锁的拥有者（release会删除该临时节点）
```java
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
```


### 服务注册
通过ServiceDiscovery注册服务

添加依赖
```xml
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-x-discovery</artifactId>
    <version>2.12.0</version>
</dependency>
```

**注册服务**
```java
public void registerService() throws Exception {
        ServiceInstanceBuilder<Map> service = ServiceInstance.builder();
        service.address("127.0.0.1");
        service.port(8080);
        service.name("myserver");
        
        Map<String, String> config = new HashMap<>();
        config.put("url", "/api/v3/server");
        //额外信息
        service.payload(config);

        ServiceInstance<Map> instance = service.build();
        ServiceDiscovery<Map> serviceDiscovery = ServiceDiscoveryBuilder.builder(Map.class)
                .client(client).serializer(new JsonInstanceSerializer<>(Map.class))
                .basePath("/service").build();

        //服务注册
        serviceDiscovery.registerService(instance);
        serviceDiscovery.start();
    }
```

**获取服务**
```java
public Collection<ServiceInstance<Map>> findService() throws Exception {
        ServiceDiscovery<Map> serviceDiscovery = ServiceDiscoveryBuilder.builder(Map.class)
                .client(client).serializer(new JsonInstanceSerializer<>(Map.class))
                .basePath("/service").build();

        serviceDiscovery.start();

        //获取当前所有服务
        Collection<ServiceInstance<Map>> all = serviceDiscovery.queryForInstances("myserver");
        return all;
    }
```