package com.code.repository;

import com.alibaba.fastjson.JSONObject;
import com.code.repository.controller.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@ImportResource({ "classpath*:/bean.xml" })
//@ComponentScan(basePackages = { "com.code.repository.spring.manager" })
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

	// http://127.0.0.1:8081/
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class,args);

//		try{
//			String[] beanNames = ctx.getBeanDefinitionNames();//所有的bean
////			String[] beanNames = ctx.getBeanNamesForAnnotation(RestController.class);//所有添加该注解的bean
//
//			logger.error("total bean count :"+ ctx.getBeanDefinitionCount());
//
//			int i = 0;
//			for (String str : beanNames) {
//				logger.info("{},beanName:{}", ++i, str);// 打印所有加载的类
//			}
//		} catch(Exception e){
//		}
	}
	
//	@Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//
//        };
//    }

	/**
	 *
	 *  关于 applicaiton.properties文件的位置
	 *  如果在不同的目录中存在多个配置文件，它的读取顺序是：
	 *
	 *         1、config/application.properties（项目根目录中config目录下）
	 *
	 *         2、config/application.yml
	 *
	 *         3、application.properties（项目根目录下）
	 *
	 *         4、application.yml
	 *
	 *         5、resources/config/application.properties（项目resources目录中config目录下）
	 *
	 *         6、resources/config/application.yml
	 *
	 *         7、resources/application.properties（项目的resources目录下）
	 *
	 *         8、resources/application.yml
	 *
	 *     注：
	 *
	 *         1、如果同一个目录下，有application.yml也有application.properties，默认先读取application.properties。
	 *
	 *         2、如果同一个配置属性，在多个配置文件都配置了，默认使用第1个读取到的，后面读取的不覆盖前面读取到的。
	 *
	 *         3、创建SpringBoot项目时，一般的配置文件放置在“项目的resources目录下”
	 */

}
