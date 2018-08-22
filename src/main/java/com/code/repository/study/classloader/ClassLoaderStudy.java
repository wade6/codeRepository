package com.code.repository.study.classloader;

import java.net.URL;


/**
 * classLoader基本概念的实践
 * 
 * @author zhaoyuan.lizy
 * 2012-8-10下午1:57:27
 */
public class ClassLoaderStudy {

	 public static void main(String[] args) {
	     System.out.println(ClassLoader.getSystemClassLoader().getParent());
	     System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());

	 }
	 
	 /**
	  * 获得系统属性
	  * 
	  * @author zhaoyuan.lizy
	  * 2012-8-15下午4:20:16
	  */
	 private static void getProperty(){
       System.out.println("====查看bootstrapClassLoader加载的包");
     URL[] urls=sun.misc.Launcher.getBootstrapClassPath().getURLs();
     for (int i = 0; i < urls.length; i++) {
       System.out.println(urls[i].getFile());
     }
//     
//     System.out.println("\n====查看系统ClassPath包含的资源");
//     System.out.println(System.getProperty("java.class.path"));
//     
//     System.out.println("\n====查看Launcher的类加载器");
//     System.out.println("the Launcher's classloader is "+sun.misc.Launcher.getLauncher().getClass().getClassLoader());
//     
//     System.out.println("\n====加载该类的classLoader");
//     System.out.println(SimpleAction.class.getClassLoader().getClass().getName());
//     
//     System.out.println("\n====加载System的classLoader");
//     System.out.println(System.class.getClassLoader());
//     
//     System.out.println("\n====SystemClassLoader(AppClassLoader)的parent");
//     ClassLoader cl = ClassLoader.getSystemClassLoader();
//     System.out.println(cl);
//     System.out.println(cl.getParent());
       
//       System.out.println("\n====系统属性 java.ext.dirs");
//       System.out.println(System.getProperty("java.ext.dirs"));
//       System.out.println(System.getProperty("java.security.manager"));
//     System.out.println("\n====系统所有属性");
//     Properties p = System.getProperties();
//     for(Object s :p.keySet()){
//         System.out.println(s.toString()+"====>"+System.getProperty(s.toString()));
//     }
	     
	 }
	 
	 /**
	  * 自定义classLoader，加载自定义的classPath
	  * 
	  * @author zhaoyuan.lizy
	  * 2012-8-15下午4:43:05
	  */
	 private static void userClassLoader(){
	     
	 }
}
