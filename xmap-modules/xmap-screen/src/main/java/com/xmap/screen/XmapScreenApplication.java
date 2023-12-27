package com.xmap.screen;

import com.xmap.common.security.annotation.EnableCustomConfig;
import com.xmap.common.security.annotation.EnableRyFeignClients;
import com.xmap.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: shaohuaibin
 * @date:2023-06-25 19:24
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class XmapScreenApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(XmapScreenApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Ahead屏幕终端模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
}
