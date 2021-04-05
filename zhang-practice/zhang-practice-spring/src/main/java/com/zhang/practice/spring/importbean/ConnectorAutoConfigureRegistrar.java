package com.zhang.practice.spring.importbean;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

public class ConnectorAutoConfigureRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        ConnectorBeanDefinitionScanner scanner = new ConnectorBeanDefinitionScanner(beanDefinitionRegistry);
        scanner.setResourceLoader(resourceLoader);
        scanner.registerFilters();
        scanner.doScan("com.zhang.practice.spring");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
