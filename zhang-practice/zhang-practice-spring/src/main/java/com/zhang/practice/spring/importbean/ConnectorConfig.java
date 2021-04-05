package com.zhang.practice.spring.importbean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ConnectorAutoConfigureRegistrar.class)
public class ConnectorConfig {
}
