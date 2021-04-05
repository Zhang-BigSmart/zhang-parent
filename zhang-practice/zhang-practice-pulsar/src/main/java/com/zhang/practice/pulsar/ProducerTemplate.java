package com.zhang.practice.pulsar;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

import java.nio.charset.StandardCharsets;

public class ProducerTemplate {

    public void sendMsg() throws PulsarClientException {
        PulsarClient client = PulsarClientBuilder.getInstance();

        Producer<String> producer = client
                .newProducer(Schema.STRING)
                .topic(PulsarClientBuilder.Topic.DEMO.name())
                .create();

        producer.send("message test");
    }

    public static void main(String[] args) throws PulsarClientException {
        new ProducerTemplate().sendMsg();
    }
}
