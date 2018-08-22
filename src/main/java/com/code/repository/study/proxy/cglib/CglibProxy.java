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
        CglibProxy cglibProxy = new CglibProxy();
        NoFaceAction NoFaceActionProxy = (NoFaceAction) cglibProxy.getInstance(new NoFaceAction());
        
        NoFaceActionProxy.doAction();
        
        NoFaceActionProxy.getNumber();
    }

}
