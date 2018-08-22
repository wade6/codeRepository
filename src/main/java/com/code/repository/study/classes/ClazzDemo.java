package com.code.repository.study.classes;

/**
 * 
 * Class.forName(xxx.xx.xx) 返回的是一个类, .newInstance() 后才创建一个对象.
 * 
 * Class.forName(xxx.xx.xx);的作用是要求JVM查找并加载指定的类，也就是说JVM会执行该类的静态代码段。
 * 
 * @author zhaoyuan.lizy
 *
 */
public class ClazzDemo {
	
	// 父类A
	public class A{
		
		public void aa(){
			System.out.println("A");
		}
	}
	
	// 子类B
	public class B extends A{
		
		public void aa(){
			System.out.println("B");
		}
		
	}
	
	// 子类C
	public class C extends A{
		
		public void aa(){
			System.out.println("C");
		}
		
	}
	
	// 调用aa方法
	void showInnerClass(String name){
        try{
             A show=(A)Class.forName("com.alibaba.webx.study.my.classes."+name).newInstance();
             show.aa();
        }catch(Exception e){
        	System.out.println ("showInnerClass Exception："+e);
        }
    }
	
	// 调用外部
	void show(String pathName){
		try {
			DemoA demo = (DemoA)Class.forName(pathName).newInstance();
			demo.aa();
		} catch (Exception e) {
			System.out.println ("show Exception："+e);
		} 
	}

	public static void main(String[] args){
		ClazzDemo t=new ClazzDemo();
        t.showInnerClass("A");
        t.showInnerClass("B");
        t.showInnerClass("C");
        
        t.show("com.alibaba.webx.study.my.classes.DemoA");
    }
	
	
    
}
