package cn.cy.activemq.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * JMS方式发送消息接收者
 *
 * Created by guozi on 2015/7/27.
 */
public class MqReceive {
    /*
    消息接收者从JMS接受消息的步骤

    (1)、创建连接使用的工厂类JMS ConnectionFactory

    (2)、使用管理对象JMS ConnectionFactory建立连接Connection，并启动

    (3)、使用连接Connection 建立会话Session

    (4)、使用会话Session和管理对象Destination创建消息接收者MessageReceiver

    (5)、使用消息接收者MessageReceiver接受消息，需要用setMessageListener将MessageListener接口绑定到MessageReceiver消息接收者必须实现了MessageListener接口，需要定义onMessage事件方法。
     */
    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;
        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        // 消费者，消息接收者
        MessageConsumer consumer;
        connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://127.0.0.1:61616");
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            //这个最好还是有事务
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createQueue("test-queue");
            consumer = session.createConsumer(destination);
            while (Boolean.TRUE) {
                Message message = consumer.receive(1000*1000);
                MqBean bean = (MqBean) ((ObjectMessage)message).getObject();
                if (message != null) {
                    System.out.println("收到消息" + bean.getName());
                }else{
                    break;
                }
            }
            //提交会话
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
