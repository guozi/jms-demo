package cn.cy.activemq.queue;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.DeliveryMode;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;

/**
 * Queue 方式消息发送者
 *
 * Created by yun.chen on 2015/7/27.
 */
public class QueueSender {
    //发送次数
    public static final int SEND_NUM = 5;
    //tcp地址
    public static final String BROKER_URL = "tcp://localhost:61616";
    //目标，在ActiveMQ管理员控制台创建 http://localhost:8161/admin/queues.jsp
    public static final String DESTINATION ="cn.cy.queue";

    /**
     * 发送消息
     *
     * @param session
     * @param sender
     * @throws Exception
     */
    public static void sendMessage(QueueSession session, javax.jms.QueueSender sender) throws Exception{
        for (int i = 0;i < SEND_NUM;i++) {
            String message = "发送消息第"+(i+1)+"条";
            MapMessage map = session.createMapMessage();
            map.setString("text",message);
            map.setLong("time",System.currentTimeMillis());
            System.out.println(map);
            sender.send(map);
        }
    }


    public static void run() throws Exception{
        QueueConnection connection = null;
        QueueSession session = null;

        try {
            //创建链接工厂
            QueueConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD,BROKER_URL);
            //通过工厂创建一个链接
            connection = factory.createQueueConnection();
            //启动链接
            connection.start();
            //创建一个会话
            session = connection.createQueueSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建一个消息队列
            Queue queue = session.createQueue(DESTINATION);
            //创建消息发送者
            javax.jms.QueueSender sender = session.createSender(queue);
            //设置持久化模式
            sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            sendMessage(session,sender);
            //提交会话
            session.commit();
        }catch (Exception e) {
            throw e;
        }finally {
            //关闭资源
            if(session != null) {
                session.close();
            }

            if(connection != null) {
                connection.close();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        QueueSender.run();
    }
}
