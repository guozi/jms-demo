package cn.cy.activemq.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

/**
 * Topic主题发布和订阅消息 消息接收者； 依赖hawtbuf-1.9.jar
 * <p/>
 * Created by yun.chen on 2015/7/27.
 */
public class TopicReceiver {
    //tcp地址
    public static final String BROKER_URL = "tcp://localhost:61616";
    //目标，在ActiveMQ管理员控制台创建 http://localhost:8161/admin/queues.jsp
    public static final String TARGET = "cn.cy.topic";

    public static void run() throws Exception {
        TopicConnection connection = null;
        TopicSession session = null;

        try {
            //创建连接工厂
            TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
            //通过工厂创建一个连接
            connection = factory.createTopicConnection();
            //启动连接
            connection.start();
            //创建一个session会话
            session = connection.createTopicSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建一个消息队列
            Topic topic = session.createTopic(TARGET);
            //创建一个消息制作者
            TopicSubscriber subscriber = session.createSubscriber(topic);

            subscriber.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    if (message != null) {
                        MapMessage map = (MapMessage) message;
                        try {
                            System.out.println(map.getLong("time") + "接收#" + map.getString("text"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            //休眠100ms再关闭
            Thread.sleep(100);
            //提交会话
            session.commit();
        } catch (Exception e) {
            throw e;
        } finally {
            //关闭资源
            if (session != null) {
                session.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

    }

    public static void main(String[] args) throws Exception {
        TopicReceiver.run();
    }
}
