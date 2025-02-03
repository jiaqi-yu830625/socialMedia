package ncl.yujiaqi;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author yujiaqi
 * @Since 03/02/2025
 */
@SpringBootApplication
@ServletComponentScan
@EnableScheduling
@EnableAsync
@MapperScan("ncl.yujiaqi.**.mapper")
@Slf4j
public class LauncherApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(LauncherApplication.class, args);
        log.info("Start OK");
    }
}
