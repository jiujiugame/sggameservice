package com.jiujiu.sggame;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * SpringBootApplication 启动类
 * 
 * @author 00fly
 * @version [版本号, 2018-09-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SpringBootApplication
public class SGServer
{
    public static void main(String[] args)
        throws IOException
    {
        SpringApplication.run(SGServer.class, args);
    }
}
