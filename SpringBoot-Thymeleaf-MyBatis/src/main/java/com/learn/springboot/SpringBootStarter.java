package com.learn.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootStarter SpringBoot应用启动类
 * @author tsange
 *
 */
@SpringBootApplication
@MapperScan("com.learn.springboot.mapper") //或者使用注解@Mapper修饰Mapper接口IxxxMapper,并自动注册Mapper;注解@Mapper与@MapperScan之间必须使用一个;
public class SpringBootStarter
{
	public SpringBootStarter()
	{
	}
}
