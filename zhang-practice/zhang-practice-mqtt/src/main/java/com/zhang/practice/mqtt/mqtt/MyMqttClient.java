package com.zhang.practice.mqtt.mqtt;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.mqtt.MqttClient;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author : zzh
 * create at:  2022/5/18
 * @description:
 */
@Slf4j
public class MyMqttClient {

    public static final String MQTT_TOPIC = "hello_topic";
    public static Vertx vertx = Vertx.vertx();

    public static void main(String[] args) {
        MqttClient client = MqttClient.create(vertx);

        client.connect(1883, "127.0.0.1", s -> {
            if (s.succeeded()) {
                log.info("Client connect success.");
                // 如果连接成功，就发起订阅
                subscribe(client);
            } else {
                log.error("Client connect fail: ", s.cause());
            }
        }).publishHandler(p -> {
            // 接收服务端的消息
            log.info("Server received message [{}] with QoS [{}]", p.payload().toString(Charset.defaultCharset()), p.qosLevel());
        }).exceptionHandler(event -> {
            log.error("client fail: ", event.getCause());
        });
    }

    private static void subscribe(MqttClient client) {
        client.subscribe(MQTT_TOPIC, 0, e -> {
            if (e.succeeded()) {
                log.info("===>subscribe success: {}", e.result());
                // 发送消息
                vertx.setPeriodic(10_000, l -> publish(client));
            } else {
                log.error("===>subscribe fail: ", e.cause());
            }
        });
    }


    private static void publish(MqttClient client) {
        Buffer payload = Buffer.buffer("client: hello world.");
        client.publish(MQTT_TOPIC, payload, MqttQoS.AT_MOST_ONCE, false, false, s -> {
            if (s.succeeded()) {
                // TODO 为什么result会递增？
                log.info("===>Client publish success: {}", s.result());
            } else {
                log.error("===>Client publish fail: ", s.cause());
            }
        });
    }

}
