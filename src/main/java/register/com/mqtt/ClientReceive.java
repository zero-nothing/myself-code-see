package register.com.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:client.properties")
public class ClientReceive {
    @Value("${clientRec.host}")
    private String HOST;

    @Value("${clientRec.username}")
    private String USERNAME;

    @Value("${clientRec.password}")
    private String PASSWORD;

    @Value("${clientRec.id}")
    private String ID;

    @Value("${clientRec.topicRec}")
    private String TOPICREC;

    @Value("${clientPub.topicPub}")
    private String TOPICPUB;

    @Autowired
    private PushCallback callback;

    private MqttClient client;

    public void start(){
        try {
            client = new MqttClient(HOST, ID, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setPassword(PASSWORD.toCharArray());
            options.setUserName(USERNAME);
            options.setConnectionTimeout(10);
            options.setKeepAliveInterval(20);
            client.setCallback(callback);
            client.connect(options);

            String[] topic = {TOPICREC};
            client.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void reconnect(){
        try {
            client.reconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
