# 单元测试
---


## 测试工具:
---
JUnit、Spring Boot Test、Mockito、JsonPath






### mockito模拟未完成类（或第三方接口）
@MockBean 可以注入Spring管理的bean，在Spring上下文中，mock的实现被模拟的实现代替了
given方法，用来模拟一个实例方法的调用返回


### 测试MVC
1、单独测试Controller
带有@Service、@Component的类不会自动被扫描注册为Spring的Bean
使用@WebMvcTest传入待测试的Controller(需模拟Controller调用的所有service)   
```java
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest{
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private UserService userService;
    
    @Test
    public void testGetUser() throws Exception {
        String userId = "10";
        mvc.perform(get("/user/{id}", userId))
                .andExpect(status().isOk())
                //期望返回内容是application/json
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                //检查返回内容
                .andExpect(jsonPath("$.name").value("taobi"));
    }
}
```


2、整个功能验证测试
在@Before中初始化MockMvc
```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest{
    private MockMvc mvc;
    @MockBean
    private UserService userService;
    
    @Before
    public void setUp(){
        mvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
    }
}
```


### 面向数据库的单元测试
`@Sql`,用来初始化数据库

