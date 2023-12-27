package com.xmap.modules.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * 监控中心
 * 
 * @author xmap
 */
@EnableAdminServer
@SpringBootApplication
public class XmapMonitorApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(XmapMonitorApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Ahead监控中心启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
}
