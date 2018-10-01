# 自定义starter
使用@Configuration和@Bean来自动装配集成
可以通过以下几种方式启用自动化配置：

- 在resources/META-INF目录下的spring.factories告诉springboot自动装配类在哪里。比如：
  > org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.liaoyb.starter.config.MyStarterConfig

- 通过@Import注解引入配置类
  > @Import(MyStarterConfig.class)


- 自定义注解，通过@Improt引入配置类
  > @Target(ElementType.TYPE)  
    @Retention(RetentionPolicy.RUNTIME)  
    @Documented  
    @Import(MyStarterConfig.class)  
    public @interface EnableMyStarter {  
    }