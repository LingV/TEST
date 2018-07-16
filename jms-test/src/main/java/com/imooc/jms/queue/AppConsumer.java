package com.imooc.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author jiangaoxue
 * @date 2018/7/16 13:23
 */
public class AppConsumer {
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
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //5.创建一个目标
            Destination destination= session.createQueue(queueName);

            //6.创一个消费者
            MessageConsumer consumer=session.createConsumer(destination);

            //7.创建一个监听器
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    TextMessage textMessage= (TextMessage) message;
                    System.out.println("接收消息"+textMessage);
                }
            });

            //8.关闭连接
           // connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
