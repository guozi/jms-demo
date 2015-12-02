import com.cy.spring.jms.ProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

/**
 * @author yun.chen
 * @create 2015-12-01 11:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ProducerConsumerTest2 {
    @Autowired
    private ProducerService producerService;
    @Autowired
    @Qualifier("sessionAwareQueue")
    private Destination sessionAwareQueue;

    @Test
    public void testSessionAwareMessageListener() {
        producerService.sendMessage(sessionAwareQueue, "测试SessionAwareMessageListener");
    }
}
