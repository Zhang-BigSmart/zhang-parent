package com.zhang.practice.spring;

import com.zhang.practice.spring.den.A;
import com.zhang.practice.spring.importbean.ConnectorService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class ConnectorTest extends BaseTest {

    @Autowired
    public ApplicationContext context;

    @Test
    public void test() {
        A bean = context.getBean(A.class);
        System.out.println(bean.getClass().getName());

        if (context.containsBean("connectorService")) {
            ConnectorService c = context.getBean(ConnectorService.class);
            System.out.println(c.getClass().getName());
        }
    }

}
