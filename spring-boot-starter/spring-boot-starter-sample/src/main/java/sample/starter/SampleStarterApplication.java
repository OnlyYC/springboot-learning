package sample.starter;

import com.liaoyb.starter.config.EnableMyStarter;
import com.liaoyb.starter.config.MyStarterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author liaoyb
 * @date 2018-10-01 11:31
 */
@SpringBootApplication
//@EnableMyStarter
@Import(MyStarterConfig.class)
public class SampleStarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleStarterApplication.class, args);
    }
}
