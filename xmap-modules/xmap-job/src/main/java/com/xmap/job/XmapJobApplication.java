package com.xmap.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.xmap.common.security.annotation.EnableCustomConfig;
import com.xmap.common.security.annotation.EnableRyFeignClients;
import com.xmap.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 定时任务
 * 
 * @author xmap
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableRyFeignClients
@SpringBootApplication
public class XmapJobApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(XmapJobApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Ahead定时任务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
}
