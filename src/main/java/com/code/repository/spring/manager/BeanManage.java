package com.code.repository.spring.manager;

import com.code.repository.spring.Handler;
import com.code.repository.spring.IHandler;
import com.code.repository.util.PackageScaner;
import com.code.repository.util.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author zhaoyuan.lizy on 2019/1/14
 **/

public class BeanManage implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(BeanManage.class);

    private Map<String,IHandler> hanlderRegister = new HashMap<String, IHandler>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        logger.info("===BeanManage starting ....");

        // 获取spring容器
        SpringContextUtils.init(applicationContext);

        // 注册带有注解的bean
        List<Class<?>> classList = PackageScaner.getClassesByPackage("com.code.repository.spring.hanlder");

        for(Class<?> clazz : classList){
            Handler annotation = clazz.getAnnotation(Handler.class);
            if (annotation != null && IHandler.class.isAssignableFrom(clazz)) { // 实现IHandler接口的类，且有Handler注解
                // 注册handler到spring容器中
                SpringContextUtils.registerIfNotExist(clazz);
                // 取出通过spring依赖注入后的handler对象
                IHandler handler = (IHandler) SpringContextUtils.getMatchedBean(clazz);
                // 根据注解中handler name注册handler
                hanlderRegister.put(annotation.value(), handler);
                // 如果一个handler有多个别名，遍历别名注册handler
                String[] aliases = annotation.aliases();
                for (String alias : aliases) {
                    hanlderRegister.put(alias, handler);
                }
            }
        }

        // 取出hanlder调用
        Set<String> hanlderNameSet =  hanlderRegister.keySet();
        for(String name: hanlderNameSet){
            IHandler hanlder = hanlderRegister.get(name);
            logger.info("=== get handler name: {},invoke result: {}",name,hanlder.invoke());
        }

    }
}
