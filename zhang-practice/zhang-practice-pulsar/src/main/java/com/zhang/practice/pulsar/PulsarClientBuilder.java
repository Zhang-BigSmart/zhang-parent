package com.zhang.practice.pulsar;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class PulsarClientBuilder {

    private static PulsarClient client;

    private static String SERVICE_URL = "pulsar://localhost:6650";

    public static PulsarClient getInstance() throws PulsarClientException {
        if (client == null) {
            synchronized (PulsarClient.class) {
                if (client == null) {
                    client = PulsarClient.builder()
                            .serviceUrl(SERVICE_URL)
                            .build();
                    return client;
                }
            }
        }
        return client;
    }

    public enum Topic {

        DEMO("my-topic");

        private String name;

        Topic(String name) {
            this.name = name;
        }
    }

}
