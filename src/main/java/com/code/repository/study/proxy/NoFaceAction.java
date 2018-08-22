package com.code.repository.study.proxy;

/**
 * 没有实现接口的动作类，若想实现动态代理，必须使用cglib
 * 
 * @author zhaoyuan.lizy
 * 2012-9-21下午12:27:42
 */
public class NoFaceAction {
    
    public void doAction(){
        System.out.println("     NoFaceAction is run!");
    }
    
    public Integer getNumber(){
        Integer i = new Integer(12);
        System.out.println("     number:"+i);
        return i;
    }

}
