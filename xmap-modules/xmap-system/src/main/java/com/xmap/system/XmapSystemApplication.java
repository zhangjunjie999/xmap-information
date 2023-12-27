package com.xmap.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.xmap.common.security.annotation.EnableCustomConfig;
import com.xmap.common.security.annotation.EnableRyFeignClients;
import com.xmap.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 系统模块
 * 
 * @author xmap
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class XmapSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(XmapSystemApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Ahead系统模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
}
