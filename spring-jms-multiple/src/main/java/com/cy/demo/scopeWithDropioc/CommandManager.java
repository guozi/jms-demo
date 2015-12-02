package com.cy.demo.scopeWithDropioc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author yun.chen
 * @create 2015-12-01 17:38
 */
public class CommandManager implements ApplicationContextAware {
    //用于保存ApplicationContext的引用，set方式注入
    private ApplicationContext applicationContext;

    //模拟业务处理的方法
    public Object process() {
        Command command = createCommand();
        return command.execute();
    }

    //获取一个命令
    private Command createCommand() {
        return (Command) this.applicationContext.getBean("asyncCommand"); //
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;//获得该ApplicationContext引用
    }
}
