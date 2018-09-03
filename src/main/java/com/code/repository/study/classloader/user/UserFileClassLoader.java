package com.code.repository.study.classloader.user;

import java.io.File;
import java.io.FileInputStream;


/**
 * 自定义ClassLoader,通过文件系统加载class
 * 
 * @author zhaoyuan.lizy 2012-8-10下午3:19:54
 */
public class UserFileClassLoader extends ClassLoader {

    private  String    classPath   = "";

    UserFileClassLoader(String classPath) {
        if (!classPath.endsWith("\\")) {
            classPath = classPath + "\\";
        }
        this.classPath = classPath;
    }

    /**
     *  使用类名获取该类的Class对象
     *  
     * @author zhaoyuan.lizy
     * 2012-8-10下午4:01:20
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Class findClass(String name) {
        byte[] data = loadClassData(name);
        Class c = defineClass(null, data, 0, data.length);
        System.out.println("加载" + name + "类成功!");
        return c;
    }

    /**
     *  通过类的名称返回class文件的字节数组
     *  
     * @author zhaoyuan.lizy
     * 2012-8-10下午3:26:05
     * @param name
     * @return
     */
    public byte[] loadClassData(String name) {
        byte[] data = null;
        try {
            // 检测文件的有效性
            String filePath = searchFile(classPath,name+".class");
            if(filePath==null){
                System.out.println("没有找到文件：" + name+".class");
            }else{
                System.out.println("该类的路径："+filePath);
            }
            
            
            if (!(filePath == null || filePath == "")) {
                // 生成文件流
                FileInputStream inFile = new FileInputStream(filePath);
                // 将文件流赋值到字节数组
                byte[] classData = new byte[inFile.available()];
                inFile.read(classData);
                inFile.close();
                return classData;
            }
            System.out.println("读取字节码失败！");
        } catch (Exception e){
            return data;
        }
        return data;
    }

    /**
     * 查找该文件，判断文件的有效性
     * <p>
     * 如果目录中没有改文件，则搜索其子目录
     * 
     * @author zhaoyuan.lizy 2012-8-10下午3:23:27
     * @param classpath
     * @param fileName
     * @return
     */
    @SuppressWarnings({ "static-access" })
    public String searchFile(String classpath, String fileName) {
        File f = new File(classpath + fileName);
        // 测试此路径名表示的文件是否是一个标准文件
        if (f.isFile()) {
            return f.getPath();
        } else {
            // 返回由此路径名所表示的目录中的文件和目录，将其名称组成字符串数组
            String objects[] = new File(classpath).list();
            for (int i = 0; i < objects.length; i++) {
                // 测试此路径名表示的文件是否是一个目录
                if (new File(classpath + f.separator + objects[i]).isDirectory()) {
                    // 迭代遍历。separator是与系统有关的默认名称分隔符
                    String path = searchFile(classpath + f.separator + objects[i] + f.separator, fileName);
                    if(path!=null){
                        return path;
                    }
                }
            }
        }
        return null;
    }

    
    /**
     * 测试加载本工程的类SimpleObject
     * 
     * @author zhaoyuan.lizy
     * 2012-8-10下午4:10:08
     * @param args
     */
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
        
        // 初始化自定义的classLoader
        String classForld ="E:\\test\\mywebx3\\target\\classes"; 
        UserFileClassLoader cl = new UserFileClassLoader(classForld);
        
        //使用自定义的classLoader加载类
        System.out.println("【使用自定义的classLoader加载类】：");
        String name = "SimpleObject";
        Class s1 = cl.findClass(name);
        System.out.println("类的名称："+s1);
        System.out.println("类的类加载器:"+s1.getClassLoader());
        
        // 使用AppClassLoader加载的类造型自定义ClassLoader加载的同样类的实例,应该报异常java.lang.ClassCastException
        System.out.println("========================================");
        System.out.println("【使用AppClassLoader加载的类造型自定义ClassLoader加载的同样类的实例,应该报异常java.lang.ClassCastException】：");
        System.out.println("SimpleObject类的类加载器："+SimpleObject.class.getClassLoader());
        try{
            SimpleObject so = (SimpleObject) s1.newInstance();
        }catch (Exception e){
            System.out.println(e);
        }
        
        // 使用AppClassLoader加载的类造型自定义ClassLoader加载的同样类的实例，该类在appcl中有一个父类
        System.out.println("========================================");
        System.out.println("【使用AppClassLoader加载的类造型自定义ClassLoader加载的同样类的实例，该类在appcl中有一个父类】：");
        String childerName = "ChilderClassInUserClassLoader";
        Class s2 = cl.findClass(childerName);
        System.out.println("childern的类加载器"+s2.getClassLoader());
        System.out.println("parent的类加载器"+ParentClassInAppClassLoader.class.getClassLoader());
        try{
            ParentClassInAppClassLoader pc = (ParentClassInAppClassLoader) s2.newInstance();
            System.out.println("pc的类加载器"+pc.getClass().getClassLoader());
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
