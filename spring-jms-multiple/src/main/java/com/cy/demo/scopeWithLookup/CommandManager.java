package com.cy.demo.scopeWithLookup;


/**
 * @author yun.chen
 * @create 2015-12-01 17:38
 */
public abstract class CommandManager {
    //模拟业务处理的方法
    public Object process () {
        Command command = createCommand();
        return command.execute();
    }

    //获取一个命令
    protected abstract Command createCommand();
}
