package com.zhang.practice.netty.mqtt;

import io.vertx.core.Vertx;
import io.vertx.mqtt.MqttServer;
import io.vertx.mqtt.MqttServerOptions;
import lombok.extern.slf4j.Slf4j;

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
                    System.out.println("MQTT client [" + endpoint.clientIdentifier() + "] request to connect, clean session = " + endpoint.isCleanSession());

                    if (endpoint.auth() != null) {
                        System.out.println("[username = " + endpoint.auth().getUsername() + ", password = " + endpoint.auth().getPassword() + "]");
                    }
                    //System.out.println("[properties = " + endpoint.connectProperties() + "]");
                    if (endpoint.will() != null) {
                        System.out.println("[will topic = " + endpoint.will().getWillTopic() + " msg = " + new String(endpoint.will().getWillMessageBytes()) + " QoS = " + endpoint.will().getWillQos() + " isRetain = " + endpoint.will().isWillRetain() + "]");
                    }

                    System.out.println("[keep alive timeout = " + endpoint.keepAliveTimeSeconds() + "]");

                    // accept connection from the remote client
                    endpoint.accept(false);

                })
                .exceptionHandler(t -> log.error("MQTT exception fail: ", t))
                .listen(ar -> {
                    if (ar.succeeded()) {
                        System.out.println("MQTT server is listening on port " + ar.result().actualPort());
                    } else {
                        System.out.println("Error on starting the server");
                        ar.cause().printStackTrace();
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
