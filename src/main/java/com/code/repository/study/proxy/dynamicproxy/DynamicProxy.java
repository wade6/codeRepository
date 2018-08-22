package com.code.repository.study.proxy.dynamicproxy;


import com.code.repository.study.proxy.Action;
import com.code.repository.study.proxy.Action1Impl;
import com.code.repository.study.proxy.Action2Impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理类完成全部的代理功能：只实现这一个类和方法invoke即可进行代理
 * <p>
 * 注：JDK的动态代理机制只能代理实现了接口的类
 * <p>
 * <code>InvocationHandler</code>接口： <code>
 *  <br>public interface InvocationHandler {
 *  <br>     public Object invoke(Object proxy,Method method,Object[] args) throws Throwable; 
 *  <br>} 
 * </code> <br>
 * 参数说明： <br>
 * Object proxy：指被代理的对象。 <br>
 * Method method：要调用的方法 <br>
 * Object[] args：方法调用时所需要的参数
 * <p>
 * Proxy类： <br>
 * Proxy类是专门完成代理的操作类，可以通过此类为一个或多个接口动态地生成实现类，此类提供了如下的操作方法： <br>
 * public static Object newProxyInstance(ClassLoader loader, Class<?>[]
 * interfaces, InvocationHandler h) throws IllegalArgumentException <br>
 * 参数说明： <br>
 * ClassLoader loader：类加载器 <br>
 * Class<?>[] interfaces：得到全部的接口 <br>
 * InvocationHandler h：得到InvocationHandler接口的子类实例
 * 
 * @author zhaoyuan.lizy 2012-9-21上午11:13:25
 */
public class DynamicProxy implements InvocationHandler {

    private Object target;

    /**
     * 绑定委托对象并返回一个代理类
     * 
     * @param target
     * @return
     */
    public Object bind(Object target) {

        this.target = target;

        //取得代理对象  
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass()
                .getInterfaces(), this); //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)

    }

    /**
     * 实现调用真实方法前后的逻辑
     * 
     * @author zhaoyuan.lizy 2012-9-21下午12:23:18
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("---调用之前！");
        if(method.getName().equals("doAction")){
            System.out.println("    ---只有调用doAction才出现");
        }
        //执行方法  
        result = method.invoke(target, args);
        System.out.println("+++调用之后！\n");
        return result;
    }

    public static void main(String[] args) {
        // 初始化动态代理类
        DynamicProxy dynamicProxy = new DynamicProxy();
        
        // 生成Action1的一个代理
        Action actionProxy = (Action) dynamicProxy.bind(new Action1Impl());
        // 执行代理
        actionProxy.doAction();
        
        // 生成Action2的一个代理
        actionProxy = (Action) dynamicProxy.bind(new Action2Impl());
        // 执行代理
        actionProxy.doOther();
    }

}
