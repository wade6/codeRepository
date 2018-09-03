package com.code.repository.study.classpath;

public class Test {
    
    private static String str;
    
    private static int length;
    
    public int getLength(){
        return length;
    }
    
    static{

        // 对null的操作，此处抛 
        // java.lang.NullPointerException
        length = str.length();
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.getLength();
    }

}