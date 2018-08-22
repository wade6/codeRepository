package com.code.repository.study.proxy.staticproxy;


import com.code.repository.study.proxy.Action;
import com.code.repository.study.proxy.Action1Impl;
import com.code.repository.study.proxy.Action2Impl;

/**
 * action的静态代理，代理类只能为接口Action服务，而且要实现接口的所有方法，对每个方法都要写相同的逻辑;动态代理可以解决这些
 * <p>
 * 注：JDK的动态代理机制只能代理实现了接口的类
 *
 * @author zhaoyuan.lizy
 * 2012-9-21上午11:09:25
 */
public class ActionStaticProxy implements Action {

    private Action action;

    ActionStaticProxy(Action action) {
        this.action = action;
    }

    @Override
    public void doAction() {

        System.out.println("do before excute real action!");
        this.action.doAction();
        System.out.println("do after excute real action!");

    }

    @Override
    public void doOther() {
        System.out.println("do before excute real action!other");
        this.action.doOther();
        System.out.println("do after excute real action!other");

    }


    public static void main(String[] args) {

        System.out.println("proxy action1-------------\n");
        ActionStaticProxy actionProxy = new ActionStaticProxy(new Action1Impl());
        actionProxy.doAction();

        System.out.println("\nproxy action2-------------\n");
        actionProxy = new ActionStaticProxy(new Action2Impl());
        actionProxy.doAction();

        System.out.println("\nproxy action1 other-------------\n");
        actionProxy = new ActionStaticProxy(new Action2Impl());
        actionProxy.doOther();
    }

}
