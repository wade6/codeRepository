package com.code.repository.study.goodcode;


import java.util.ArrayList;
import java.util.List;

/**
 * 判断元素是否唯一
 */
public class UniqueElement {

    public static void main(String[] args){
        List<Object> list = new ArrayList<Object>();
        list.add("12");
        list.add("12");
        UniqueElement.uniqueElement(list);// 唯一元素是字符串12
        list.add("10");
        UniqueElement.uniqueElement(list);// 集合元素不唯一
    }

    public static void uniqueElement(List<Object> list) {
        int size = list.size();
        if (size==0) {
            System.out.println("===> list is null");
            return;
        }
        Object first = list.get(0);
        for ( int i=1; i<size; i++ ) {
            if ( list.get(i)!=first ) {
                System.out.println("===> list element is not unique");
                return;
            }
        }
        System.out.println("===> unique element is :"+first);
    }
}
