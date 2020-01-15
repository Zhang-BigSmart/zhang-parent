package com.zhang.practice.spring.autoconf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : zzh
 * create at:  2020/1/11
 * @description:
 */
@Component
@ConfigurationProperties(prefix = "job")
@Data
public class JobProperties {

    private String token;

    private Executor executor;

    @Data
    class Executor {
        private String appName;
    }

    public static void main(String[] args) {
        JobProperties jobProperties = new JobProperties();
        System.out.println(jobProperties.getExecutor().getAppName());
    }
}
