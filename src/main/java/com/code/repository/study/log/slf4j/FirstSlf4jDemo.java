package com.code.repository.study.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 直接使用包slf4j-api-xxx.jar的接口实现日志的输出
 * 如果classpath中没有引入具体的日志实现，代码报错<br>
 * --------------------------<br>
 * SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
 * SLF4J: Defaulting to no-operation (NOP) logger implementation
 * SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
 * --------------------------<br>
 * 日志系统动态绑定原理：<br>
 * 通过LoggerFactory类的静态getLogger()获取logger。
 * 通过查看该类的代码可以看出，最终是通过StaticLoggerBinder.SINGLETON.getLoggerFactory()
 * 方法获取LoggerFactory然后，在通过该具体的LoggerFactory来获取logger的。
 * 类org.slf4j.impl.StaticLoggerBinder并不在slf4j-api-1.5.2.jar包中，
 * 仔细查看每个与具体日志系统对应的jar包，就会发现，
 * 相应的jar包都有一个org.slf4j.impl.StaticLoggerBinder的实现，
 * 不同的实现返回与该日志系统对应的LoggerFactory，因此就实现了所谓的动态绑定
 * @author zhaoyuan.lizy
 *
 */
public class FirstSlf4jDemo {
	
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(FirstSlf4jDemo.class);
	    logger.info("Hello World");
	}

}
