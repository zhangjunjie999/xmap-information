package com.xmap.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 网关启动程序
 * 
 * @author xmap
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class XmapGatewayApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(XmapGatewayApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Ahead网关启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
}
