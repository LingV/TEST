package com.imooc.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author jiangaoxue
 * @date 2018/7/16 10:15
 */
public class AppProducer {
    /**
     * ava消息服务 编程接口之间的关系。 连接工厂 创造出 连接， 连接 创造出 会话， 会话 创造出 消息，消息生产者，消息消费者 这三个。
     * 消息生产者 发送到 目的地。 消息消费者 从 目的地 接收。
     */
    //服务器地址：activemq默认端口
    private static final String url="tcp://localhost:61616?jms.useAsyncSend=true";
    private static final String queueName="queue-test";
    public static void main(String[] args){
        try {
        //1.创建ConnectionFactory
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(url);

        //2.创建Connection
        Connection connection = connectionFactory.createConnection();

        //3.启动连接
        connection.start();

        //4.创建会话
            Session    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //5.创建一个目标
         Destination destination= session.createQueue(queueName);

         //6.创建一个生产者
            MessageProducer producer=session.createProducer(destination);

            for (int i=0;i<100;i++){
                //7.创建消息
               TextMessage textMessage=  session.createTextMessage("text"+i);
               //8.发布消息
                producer.send(textMessage);

                System.out.println("发布消息"+textMessage);
            }

            //9.关闭连接
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
