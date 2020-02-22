package com.code.repository.study.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbit MQ 接收端
 */
public class ReciverMQ {

    private static String HOST = "localhost";
    private static int PORT = 5672;
    private static String NAME = "guest";
    private static String PASSWD = "guest";
    private static String QUEUE_NAME = "q1";

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT); //默认端口号
        factory.setUsername(NAME);//默认用户名
        factory.setPassword(PASSWD);//默认密码
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(4);  // 流控设置，每次只接受1个消息
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
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
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}
