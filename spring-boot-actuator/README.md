# SpringBoot监控

## 配置Actuator

**添加依赖**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

暴露端点
```
management.endpoints.enabled-by-default=true
management.endpoints.web.exposure.include=*
management.server.port=8081
```


**监控端点**
/actuator/health
/actuator/heapdump
/actuator/httptrace
/actuator/loggers


**其他监控**
- health 查看所有应有的健康状态，如磁盘、数据源、Redis、Elasticsearch等
- metrics 性能指标，如cpu核数、已有内存，未占用内存、类信息、jvm加载的类、tomcat线程数等
- env 显示SpringBoot环境变量
- configprops 所有@ConfigurationProperties注解的配置信息
- loggers



## 自定义监控信息
**自定义/actuator/health端点**
只需实现HealthIndicator
```java
@Component
public class MyHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        int errorCode = check();
        if (errorCode != 0) {
            return Health.down().withDetail("Message", "error:" + errorCode).build();
        }

        return Health.up().build();
    }

    /**
     * 检查服务状态
     *
     * @return
     */
    private int check() {
        //模拟返回错误状态
        return 1;
    }
}
```



**新增监控端点**
使用@Endpoint 来自定义一个监控端点

@ReadOperation用于返回监控信息

@WriteOperation可以已POST方式来动态更新监控指标

