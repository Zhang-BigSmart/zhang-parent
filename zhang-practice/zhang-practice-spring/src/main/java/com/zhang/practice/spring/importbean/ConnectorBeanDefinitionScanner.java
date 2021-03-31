package com.zhang.practice.spring.importbean;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

public class ConnectorBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    public ConnectorBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    protected void registerFilters() {
        addIncludeFilter(new AnnotationTypeFilter(Connector.class));
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        return super.doScan(basePackages);
    }
}
