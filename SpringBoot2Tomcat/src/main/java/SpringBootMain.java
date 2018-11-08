import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import com.wrapper.Print;

import com.learn.springboot.SpringBootDemo;

public class SpringBootMain
{
	public static void test1(String[] args)
	{
		/** 启动方式1: **/
		Print.info("********** 启动方式1 **********");
		SpringApplication.run(SpringBootDemo.class, args);
	}
	
	public static void test2(String[] args)
	{
		/** 启动方式2: **/
		Print.info("********** 启动方式2 **********");
		SpringApplication app = new SpringApplication(SpringBootDemo.class);
		app.run(args);
	}
	
	public static void test3(String[] args)
	{
		/** 启动方式3: **/
		Print.info("********** 启动方式3 **********");
		SpringApplicationBuilder sab = new SpringApplicationBuilder();
		sab.sources(SpringBootDemo.class);
		sab.run(args);
	}
	
	public static void main(String[] args)
	{
		//SpringBootMain.test1(args);
		//SpringBootMain.test2(args);
		SpringBootMain.test3(args);
	}
}
