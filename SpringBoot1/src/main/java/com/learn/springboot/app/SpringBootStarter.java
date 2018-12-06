package com.learn.springboot.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * SpringBoot启动类;
 * @author zxk
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.learn.springboot.controller")
public class SpringBootStarter
{
	public SpringBootStarter()
	{
	}
}
