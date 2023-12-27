package com.xmap.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.xmap.common.security.annotation.EnableRyFeignClients;

/**
 * 认证授权中心
 * 
 * @author xmap
 */
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class XmapAuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(XmapAuthApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Ahead认证授权中心启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
}
