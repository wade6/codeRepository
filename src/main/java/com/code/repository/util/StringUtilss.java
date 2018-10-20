package com.code.repository.util;

/**
 * @Author zhaoyuan.lizy on 2018/9/17
 **/

public class StringUtilss {

    public static void main(String[] args){
        StringBuilder queryStr = new StringBuilder("version:'0'");
        queryStr.append(" AND (");
        queryStr.append(" OR");
        queryStr.append(" OR");
        queryStr.append(" OR");
        System.out.println(queryStr.toString());
        String s = queryStr.substring(0,queryStr.lastIndexOf("OR"));
        System.out.println(s);
    }
}
