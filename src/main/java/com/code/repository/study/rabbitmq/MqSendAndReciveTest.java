package com.code.repository.study.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 利用mq 的 qos流控和ack，控制消费端的消息队列和速度
 * 关于mq的几个问题
 * 1、消息丢失问题
 *   -- 生产者端怎么防止消息丢失，confirm机制+服务端异常处理
 *   -- mq中间件怎么防止消息丢失，消息持久化
 *   -- 消费端，手动ack, 防止Unacked过多，异常发nack，并通知MQ把消息塞回的队列头部（不是尾部），会造成死循环，新消息无法消费
 * 2、消费端怎么控制速度
 *   -- Qos + 手动ack，全部消息都去做ack响应呗（异常里也用Ack，否则造成队列堵塞）
 */
public class MqSendAndReciveTest {

    private static String HOST = "localhost";
    private static int PORT = 5672;
    private static String NAME = "guest";
    private static String PASSWD = "guest";
    private static String QUEUE_NAME = "q1";
    private static String EXCHANGE = "hm.direct";

    public static void main(String[] args) throws Exception {
        MqSendAndReciveTest.senderMessage();
        MqSendAndReciveTest.consumer();
    }

    private static void consumer() throws Exception {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.basicQos(3);  // Qos流控设置，每次只接受1个消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("recive :" +message);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                // 手动ack,如果不自动ack，消息就会阻塞
                this.getChannel().basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //参数：队列名称、是否自动ACK、Consumer
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

    private static void senderMessage() throws Exception {
        Connection connection = getConnection();
        Channel channel =connection.createChannel();
        // 发送之前，我们必须声明消息要发往哪个队列，然后我们可以向队列发一条消息：
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        for(int i=0;i<20;i++){
            String message = "this is the "+i+" message!";
            channel.basicPublish(EXCHANGE, QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println("send :" +message);
        }
        channel.close();
        connection.close();
    }

    private static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT); //默认端口号
        factory.setUsername(NAME);//默认用户名
        factory.setPassword(PASSWD);//默认密码
        return factory.newConnection();
    }
}
