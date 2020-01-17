package com.fly.demo;

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
public class DemoApplication
{
    public static void main(String[] args)
        throws IOException
    {
        SpringApplication.run(DemoApplication.class, args);
       // Runtime.getRuntime().exec("cmd.exe /c start /min http://127.0.0.1:8080/user/all");
    }
}
