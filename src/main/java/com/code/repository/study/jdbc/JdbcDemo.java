package com.code.repository.study.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用jdbc链接数据库<p>
 * JDBC从物理结构上说就是Java语言访问数据库的一套接口集合。<p>
 * 缺点：
 *  1、数据库服务器名称 、用户名和口令都可能需要改变，由此引发JDBC URL需要修改；
 *  2、数据库可能改用别的产品，如改用DB2或者Oracle，引发JDBC驱动程序包和类名需要修改；
 *  。。。
 *  <p>
 *  使用jndi进行改进:可以将名称与资源绑定，通过名称来查找资源，资源的名字在整个j2ee应用中(j2ee容器中)是唯一的<p>
 *  允许程序将一个对象和一个命名绑定到目录树上。<p>
 *  JNDI只用lookup和bind读写
 *
 * @author zhaoyuanli
 * 2013-5-22下午10:27:30
 */
public class JdbcDemo {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            // 加载MySQL的数据驱动程序
//            Class.forName("com.mysql.jdbc.Driver"); // jdk1.6 之后可以不用再调用这句
//            DriverManager.registerDriver(new Driver());
            // 创建数据连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            // 创建Statement对象
            Statement statement = conn.createStatement();
            // 执行sql
            // 通过execuUpdate()方法用来数据的更新，包括插入和删除等操；
            String userName = "tst"+ System.currentTimeMillis();
            statement.executeUpdate( "INSERT INTO user(gmt_create, gmt_modified,user_name)" + " VALUES (now(),now(),'"+userName+"') ") ;
            // 通过executeQuery()方法进行数据的查询，而查询结果会得到 ResulSet对象
            ResultSet resultSel = statement.executeQuery( "select * from user" );
            while(resultSel.next()){
                String username = resultSel.getString("user_name");
                System.out.println("userName:"+username);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }

    }
//    事务处理
//    try{
//        con.setAutoCommit(false);   //step① 把自动提交关闭
//        Statement stm = con.createStatement();    //step② 正常的DB操作
//        stm.executeUpdate("insert into person(id, name, age) values(520, 'X-Man', 18)");
//        stm.executeUpdate("insert into Person(id, name, age) values(521, 'Super', 19)");
//        con.commit();               //step③ 成功主动提交
//    } catch(SQLException e){
//        try{con.rollback();        //如果中途发生异常，则roolback；这语句也会抛异常
//        }catch(Exception e){e.printStackTrace();}    //step③ 失败则主动回滚
//    }

}