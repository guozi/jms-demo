package cn.cy.activemq.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * JMS方式发送消息发送者
 *
 * Created by guozi on 2015/7/27.
 */

public class MqSend {


    /**
     * 发送消息的基本步骤：

     (1)、创建连接使用的工厂类JMS ConnectionFactory

     (2)、使用管理对象JMS ConnectionFactory建立连接Connection，并启动

     (3)、使用连接Connection 建立会话Session

     (4)、使用会话Session和管理对象Destination创建消息生产者MessageSender

     (5)、使用消息生产者MessageSender发送消息

     */
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory;
        Connection connection = null;
        Session session = null;
        Destination destination;
        MessageProducer producer = null;
        String BROKER_URL = "tcp://127.0.0.1:61616";

        //1.创建factory
        //factory = new ActiveMQConnectionFactory("admin","admin",BROKER_URL);
        factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD,BROKER_URL);

        try {
            //2.创建connection
            connection = factory.createConnection();
            //3.启动connection
            connection.start();
            //4.创建session
            session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            //5.创建destination
            destination = session.createQueue("test-queue");
            //5.使用destination创建MessageProducer
            producer = session.createProducer(destination);
            //6.设置持久化模式
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            MqBean bean = new MqBean();
            bean.setAge(13);
            for (int i = 0;i < 100; i++) {
                bean.setName("小黄"+i);
                //7.利用producer发送消息
                producer.send(session.createObjectMessage(bean));
            }
            session.commit();

        }catch (JMSException e){
            e.printStackTrace();
        }finally {
            if (producer != null) {
                producer.close();
            }

            if (session != null) {
                session.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }
}
