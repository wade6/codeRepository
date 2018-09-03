package com.code.repository.study.proxy;

/**
 * 动作具体实现1
 *  
 * @author zhaoyuan.lizy
 * 2012-9-21上午10:58:26
 */
public class Action1Impl implements Action{

    @Override
    public void doAction() {
       System.out.println("    ===>Action1Impl is run!");
    }

    @Override
    public void doOther() {
        System.out.println("    ===>Action1Impl is run other!");
    }

}
