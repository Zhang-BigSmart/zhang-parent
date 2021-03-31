package com.zhang.practice.pulsar;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

public class ConsumerTemplate {

    public void receiveMsg() throws PulsarClientException {
        Consumer<String> consumer = PulsarClientBuilder.getInstance().newConsumer(Schema.STRING)
                .topic(PulsarClientBuilder.Topic.DEMO.name())
                .subscriptionName("my-subscription")
                .subscribe();

        while (true) {
            Message<String> receive = consumer.receive();
            System.out.println(receive.getValue());
            consumer.acknowledge(receive);
        }
    }

    public static void main(String[] args) throws PulsarClientException {
        new ConsumerTemplate().receiveMsg();
    }
}
