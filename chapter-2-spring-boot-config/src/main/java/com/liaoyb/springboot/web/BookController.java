package com.liaoyb.springboot.web;

import com.liaoyb.springboot.config.BookProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liaoyb
 * @date 2018-07-01 11:04
 */
@RestController
public class BookController {

    @Autowired
    private BookProperties bookProperties;

    /**
     * 这里会读取不到配置的属性
     * 因为：会加载 “systemEnvironment" 作为首个PropertySource. 即为System.getProperties()
     */
    @Value("${user.name}")
    private String userName;

    /**
     * 通过@PropertySource引入的配置文件
     */
    @Value("${jdbc.url}")
    private String jdbcUrl;

    @GetMapping("/book")
    public String book(){
        return bookProperties.getWriter() + " write " + bookProperties.getName() +",uuid:" + bookProperties.getUuid();
    }

    @GetMapping("/userName")
    public String userName(){
        return userName;
    }

    @GetMapping("/jdbcUrl")
    public String jdbcUrl(){
        return jdbcUrl;
    }

}
