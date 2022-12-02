package com.zhang.practice.mqtt.mqtt;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.mqtt.MqttEndpoint;
import io.vertx.mqtt.MqttServer;
import io.vertx.mqtt.MqttServerOptions;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author : zzh
 * create at:  2022/5/18
 * @description:
 */
@Slf4j
public class MyMqttServer {

    public static void main(String[] args) {
        MqttServer mqttServer = MqttServer.create(Vertx.vertx(), create());
        mqttServer.endpointHandler(endpoint -> {
                    // shows main connect info
                    log.info("MQTT client [{}] request to connect, clean session = {}", endpoint.clientIdentifier(), endpoint.isCleanSession());
                    if (endpoint.auth() != null) {
                        log.info("[username = {}, password = {}]", endpoint.auth().getUsername(), endpoint.auth().getPassword());
                    }

                    log.info("[properties = {}]", endpoint.connectProperties());
                    if (endpoint.will() != null) {
                        log.info("[will topic: {}, msg: {}, QoS: {}, isRetain: {}]", endpoint.will().getWillTopic(), endpoint.will().getWillMessageBytes(), endpoint.will().getWillQos(), endpoint.will().isWillRetain());
                    }

                    log.info("[keep alive timeout = {}]", endpoint.keepAliveTimeSeconds());
                    // accept connection from the remote client
                    endpoint.accept(true);
                    receiver(endpoint);
                    endpoint.disconnectMessageHandler(disconnectMessage -> log.info("Received disconnect from client, reason code = {}", disconnectMessage.code()));
                })
                .exceptionHandler(t -> log.error("MQTT exception fail: ", t))
                .listen(ar -> {
                    if (ar.succeeded()) {
                        log.warn("MQTT server is listening on port: {}", ar.result().actualPort());
                    } else {
                        log.error("Fail on starting the server: ", ar.cause());
                    }
                });
    }

    /**
     * 接收消息
     *
     * @param endpoint
     */
    private static void receiver(MqttEndpoint endpoint) {
        // 接收客户端的消息
        endpoint.publishHandler(p -> {
                    log.info("Server received message [{}] with QoS [{}]", p.payload().toString(Charset.defaultCharset()), p.qosLevel());
                    if (p.qosLevel() == MqttQoS.AT_LEAST_ONCE) {
                        endpoint.publishAcknowledge(p.messageId());
                    } else if (p.qosLevel() == MqttQoS.EXACTLY_ONCE) {
                        endpoint.publishReceived(p.messageId());
                    }
                    send(endpoint);
                })
                // TODO 是每次都要释放吗？
                .publishReleaseHandler(endpoint::publishComplete);
    }

    /**
     * 发送消息
     *
     * @param endpoint
     */
    private static void send(MqttEndpoint endpoint) {
        Buffer payload = Buffer.buffer("server: hello world.");
        endpoint.publish(MyMqttClient.MQTT_TOPIC, payload, MqttQoS.AT_MOST_ONCE, false, false, s -> {
            if (s.succeeded()) {
                log.info("===>Server publish success: {}", s.result());
            } else {
                log.error("===>Server publish fail: ", s.cause());
            }
        });
    }

    private static MqttServerOptions create() {
        MqttServerOptions options = new MqttServerOptions();
        options.setPort(1883);
        options.setHost("127.0.0.1");
        return options;
    }


}
