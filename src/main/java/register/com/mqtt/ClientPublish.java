package register.com.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:client.properties")
public class ClientPublish {
    @Value("${clientPub.host}")
    private String HOST;

    @Value("${clientPub.username}")
    private String USERNAME;

    @Value("${clientPub.password}")
    private String PASSWORD;

    @Value("${clientPub.id}")
    private String ID;

    @Value("${clientPub.topicPub}")
    private String TOPICPUB;

    private MqttClient client;
    public void init() {
        try {
            client = new MqttClient(HOST, ID, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setPassword(PASSWORD.toCharArray());
            options.setUserName(USERNAME);
            options.setConnectionTimeout(10);
            options.setKeepAliveInterval(20);
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    System.out.println("throwable = " + throwable);
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    System.out.println("s = " + s + ", mqttMessage = " + mqttMessage);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                }
            });
            client.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String message, String cpuId){
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(message.getBytes());
        mqttMessage.setQos(1);
        try {
            client.publish(TOPICPUB + cpuId, mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
