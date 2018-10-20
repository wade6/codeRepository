package com.code.repository.study.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class mybatisDemo {
    
    public static void main(String[] args) throws IOException {
        // 加载mybatise配置文件
        String resource = "mybatis/mybatis-config.xml";// 配置信息
        InputStream inputStream = Resources.getResourceAsStream(resource);// 获取配置文件输入流
        // 初始化sessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取SqlSession
        SqlSession session = sqlSessionFactory.openSession();
        // 执行sql调用
        try {
            UserDO userDO = (UserDO) session.selectOne("org.mybatis.userMapper.selectByName", "tom");
            System.out.println("===> userId:"+userDO.getId());
        } finally {
            // 关闭session
            session.close();
        }
    }  
}
