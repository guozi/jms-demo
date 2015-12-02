package com.cy.demo;

import com.cy.demo.scopeWithLookup.CommandManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yun.chen
 * @create 2015-12-02 9:52
 */
public class TestCommandManagerLookup {
    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("/spring/beans-lookup.xml");
    }

    @Test
    public void testProcess() {
        CommandManager manager = (CommandManager) context.getBean("commandManager",
                CommandManager.class);
        System.out.println("第一执行process,Command的地址是:" + manager.process());
        System.out.println("第二执行process,Command的地址是:" + manager.process());
    }
}
