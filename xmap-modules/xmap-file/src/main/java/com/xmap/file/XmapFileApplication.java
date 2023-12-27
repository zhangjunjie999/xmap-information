package com.xmap.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.xmap.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 文件服务
 * 
 * @author xmap
 */
@EnableCustomSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class XmapFileApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(XmapFileApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Ahead文件服务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
