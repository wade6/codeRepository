package com.code.repository.study.classloader;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderDemo {

    public static void main(String[] args) throws IOException {
        ClassLoader ClassLoader_thread = Thread.currentThread().getContextClassLoader(); // 应用类加载器
        System.out.println("===> ClassLoader_thread:"+ClassLoader_thread.toString());

        ClassLoader ClassLoader_class = ClassLoaderDemo.class.getClassLoader();// 应用类加载器
        System.out.println("===> ClassLoader_class:"+ClassLoader_class.toString());

        ClassLoader ClassLoader_classLoader = ClassLoader.getSystemClassLoader();// 应用类加载器
        System.out.println("===> ClassLoader_classLoader:"+ClassLoader_classLoader.toString());

        System.out.println("===> ClassLoader_classLoader.parent:"+ClassLoader_classLoader.getParent().toString());// 扩展类加载器

//        System.out.println("===> ClassLoader_classLoader.parent.parent:"+ClassLoader_classLoader.getParent().getParent().toString());// 扩展类加载器

        InputStream returnValue1 = ClassLoader_thread.getResourceAsStream("banner.txt");
        returnValue1.close();
        InputStream returnValue3 = ClassLoader_classLoader.getResourceAsStream("banner.txt");
        returnValue3.close();
        InputStream returnValue2 = ClassLoader_class.getResourceAsStream("banner.txt");
        returnValue2.close();

    }
}
