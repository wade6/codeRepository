package com.code.repository.study.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * rabbit MQ 发送端
 *
 * 二方库依赖
 *      <dependency>
 * 			<groupId>com.rabbitmq</groupId>
 * 			<artifactId>amqp-client</artifactId>
 * 			<version>5.1.2</version>
 * 		</dependency>
 */
public class SenderMQ {

    private static String HOST = "localhost";
    private static int PORT = 5672;
    private static String NAME = "guest";
    private static String PASSWD = "guest";
    private static String QUEUE_NAME = "q1";
    private static String EXCHANGE = "hm.direct";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // connection是socket连接的抽象，并且为我们管理协议版本协商（protocol version negotiation），
        // 认证（authentication ）等等事情。这里我们要连接的消息代理在本地，因此我们将host设为“localhost”。
        // 如果我们想连接其他机器上的代理，只需要将这里改为特定的主机名或IP地址。
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT); //默认端口号
        factory.setUsername(NAME);//默认用户名
        factory.setPassword(PASSWD);//默认密码
        factory.setRequestedHeartbeat(20000); // 心跳时间
        Connection connection = factory.newConnection();

        // 接下来，我们创建一个channel，绝大部分API方法需要通过调用它来完成。
        Channel channel = connection.createChannel();
        // 开启confirm模式
        channel.confirmSelect();
        // 发送之前，我们必须声明消息要发往哪个队列，然后我们可以向队列发一条消息：
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        for(int i=0;i<30;i++){
            String message = "this is the "+i+" message!";
            channel.basicPublish(EXCHANGE, QUEUE_NAME, null, message.getBytes("UTF-8"));
            if (channel.waitForConfirms()) {
                System.out.println("发送成功");
                System.out.println("send :" +message);
            } else {
                //发送失败这里可进行消息重新投递的逻辑
                System.out.println("发送失败");
                System.out.println("send :" +message);
            }
        }

        channel.close();
        connection.close();
    }

}
