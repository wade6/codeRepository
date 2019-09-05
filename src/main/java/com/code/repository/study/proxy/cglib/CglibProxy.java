package com.code.repository.study.proxy.cglib;

import com.code.repository.study.proxy.NoFaceAction;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    
    private Object target;  
    
    /** 
     * 创建代理对象 
     *  
     * @param target 
     * @return 
     */  
    public Object getInstance(Object target) {  
        
        this.target = target;  
        
        Enhancer enhancer = new Enhancer();
        
        // 设置代理类的父类
        enhancer.setSuperclass(this.target.getClass());  
        // 回调方法  
        enhancer.setCallback(this);  
        // 创建代理对象  
        return enhancer.create();  
    }  

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
            throws Throwable {
        System.out.println("---cglib代理动作开始"); 
        
        if(method.getName().equals("doAction")){
            System.out.println("     ---只有调用doAction才出现");
        }
        
        Object result = proxy.invokeSuper(obj, args); 
        
//        proxy.invoke(target, args);
//        
//        method.invoke(target, args);
        
        
        System.out.println("+++cglib代理动作结束\n"); 
        
        return result;  
    }
    
    public static void main(String[] args) {
//        CglibProxy cglibProxy = new CglibProxy();
//        NoFaceAction NoFaceActionProxy = (NoFaceAction) cglibProxy.getInstance(new NoFaceAction());
//
//        NoFaceActionProxy.doAction();
//
//        NoFaceActionProxy.getNumber();

        CglibProxy cglibProxy = new CglibProxy(); // 创建代理实现类

        NoFaceAction action = new NoFaceAction(); // 创建被代理业务类

        Enhancer enhancer = new Enhancer(); // 创建加强器，用来生成代理类

        enhancer.setSuperclass(action.getClass());// 设置代理类的父类：CGlib动态代理是通过继承业务类，生成的动态代理类是业务类的子类，通过重写业务方法进行代理；

        enhancer.setCallback(cglibProxy);// 回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦

        NoFaceAction actionProxy = (NoFaceAction)enhancer.create();// 创建代理类对象

        // 使用代理类调用
        actionProxy.doAction();

    }

}
