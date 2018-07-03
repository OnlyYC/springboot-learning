package com.liaoyb.springboot;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * spring boot 配置
 */
@EnableSwagger2Doc // 开启 Swagger
@SpringBootApplication
@PropertySource(value= {"classpath:config/jdbc-dev.properties"},ignoreResourceNotFound=false,encoding="UTF-8",name="jdbc-dev.properties")
public class ConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}
}
