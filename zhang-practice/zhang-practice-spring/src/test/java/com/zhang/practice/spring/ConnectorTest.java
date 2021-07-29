package com.zhang.practice.spring;

import com.zhang.practice.spring.den.A;
import com.zhang.practice.spring.den.ErrorProcessor;
import com.zhang.practice.spring.importbean.ConnectorService;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class ConnectorTest extends BaseTest {

    @Autowired
    public ApplicationContext context;

    @Test
    public void test() {
        /*A bean = context.getBean(A.class);
        System.out.println(bean.getClass().getName());

        if (context.containsBean("connectorService")) {
            ConnectorService c = context.getBean(ConnectorService.class);
            System.out.println(c.getClass().getName());
        }*/

        Tea tea = new Tea();
        tea.setName("Teacher");
        Stu stu = new Stu();
        stu.setAge(1);
        tea.setStu(stu);

        Stu stu1 = tea.getStu();
        stu1.setAge(2);
        System.out.println(stu1);

        System.out.println(tea.getStu());


    }

    @Data
    class Tea {
        private String name;
        private Stu stu;
    }

    @Data
    class Stu {
        private int age;
    }

}
