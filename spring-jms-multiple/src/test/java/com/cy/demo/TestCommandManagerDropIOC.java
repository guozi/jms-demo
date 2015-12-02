package com.cy.demo;

import com.cy.demo.scopeWithDropioc.CommandManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yun.chen
 * @create 2015-12-02 9:24
 */
public class TestCommandManagerDropIOC {
    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("/spring/beans-dropioc.xml");
    }

    @Test
    public void testProcess() {
        CommandManager manager = (CommandManager) context.getBean("commandManager",
                CommandManager.class);
        System.out.println("第一执行process,Command的地址是:" + manager.process());
        System.out.println("第二执行process,Command的地址是:" + manager.process());
    }
}
