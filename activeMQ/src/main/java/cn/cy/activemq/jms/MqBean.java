package cn.cy.activemq.jms;

import java.io.Serializable;

/**
 * Created by yun.chen on 2015/7/27.
 */
public class MqBean implements Serializable{
    private Integer age;
    private String name;


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
