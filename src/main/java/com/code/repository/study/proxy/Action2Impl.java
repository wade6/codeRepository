package com.code.repository.study.proxy;

/**
 * 动作具体实现2
 * 
 * @author zhaoyuan.lizy
 * 2012-9-21上午10:58:47
 */
public class Action2Impl implements Action{

    @Override
    public void doAction() {
        System.out.println("    ===>Action2Impl is run!");
    }

    @Override
    public void doOther() {
        System.out.println("    ===>Action2Impl is run other!");
    }
}
