package com.xmap.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.xmap.common.security.annotation.EnableCustomConfig;
import com.xmap.common.security.annotation.EnableRyFeignClients;
import com.xmap.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 代码生成
 * 
 * @author xmap
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableRyFeignClients
@SpringBootApplication
public class XmapGenApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(XmapGenApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Ahead代码生成模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
