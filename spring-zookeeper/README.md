# SpringBoot集成Zookeeper


## 集成Curator
依赖
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



### 服务注册
通过ServiceDiscovery注册服务


