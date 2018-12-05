package com.code.repository.util;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * Spring容器工具类
 * 注入bean，获取bean操作
 */
public class SpringContextUtils implements ApplicationContextAware {

	// spring容器
	private static ApplicationContext applicationContext;

	public static void init(ApplicationContext context) {
		applicationContext = context;
	}

	public void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}

	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	/**
	 * 注册bean到spring
	 */
	public static void registerBean(String name, Object bean) {

		ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
		// 根据bean上对应的注解，通过spring容器注入依赖
		context.getBeanFactory().autowireBean(bean);
		// 将当前对象注入到spirng容器中
		context.getBeanFactory().registerSingleton(name, bean);
	}

	/**
	 * 避免重复注册bean到spring容器
	 */
	public static void registerIfNotExist(Class<?> clazz) {

		// boolean includeNonSingletons, boolean allowEagerInit
		Map<String, ?> beanMap = applicationContext.getBeansOfType(clazz, false, false);

		String beanId = springDefaultBeanName(clazz.getSimpleName());

		// 判断是否已存在相同ID
		if (beanMap.size() == 1 || beanMap.containsKey(beanId)) {
			return;
		}

		registerBean(beanId, clazz);
	}

	/**
	 * 从spring容器中获取bean，如果存在一个直接取出，如果存在多个，根据spring默认的bean名获取
	 */
	public static <T> T getMatchedBean(Class<T> clazz) {
		T beanObj = null;

		// boolean includeNonSingletons, boolean allowEagerInit
		Map<String, T> beanMap = applicationContext.getBeansOfType(clazz, false, false);

		String beanId = springDefaultBeanName(clazz.getSimpleName());

		if (beanMap.size() == 1) {
			beanObj = applicationContext.getBean(clazz);
		} else {
			beanObj = beanMap.get(beanId);
		}

		return beanObj;
	}

	/**
	 * spring默认beanName
	 */
	public static String springDefaultBeanName(String classSimpleName) {
		char[] ch = classSimpleName.toCharArray();

		//判断首字母大写且第二个字母是小写时,将首字母转换成小写
		if (ch[0] >= 'A' && ch[0] <= 'Z' && ch[1] >= 'a' && ch[1] <= 'z') {
			ch[0] = (char) (ch[0] + 32);
		}		
		return new String(ch);
	}

	/**
	 * 注册class到spring
	 */
	public static void registerBean(String id, Class<?> clazz) {

		ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();

		// 注册对象到spring容器中
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
		// dataSourceBuider.addPropertyValue("msg", "hello ");
		beanFactory.registerBeanDefinition(id, beanDefinitionBuilder.getBeanDefinition());

	}

	/**
	 * 注册className到spring
	 */
	public static void registerBean(String id, String className) {
		ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();

		// 注册对象到spring容器中
		BeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClassName(className);
		// beanDefinition.getPropertyValues().add("msg", "hello ");
		beanFactory.registerBeanDefinition(id, beanDefinition);
	}

}
