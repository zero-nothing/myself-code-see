package register.com.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import register.com.service.UserService;
import register.com.utils.StringUtil;

import java.util.regex.Pattern;

@Component
@Slf4j
public class PushCallback implements MqttCallback {

    private static final Pattern pattern = Pattern.compile("\\$WCMD:REG\\$#");

    @Autowired
    private UserService userService;

    @Autowired
    private ClientPublish clientPublish;

    @Override
    public void connectionLost(Throwable throwable) {
        log.warn("connectionLost throwable = " + throwable);
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        log.info("Topic: " + topic + " Payload: " + mqttMessage);

        //过滤错误的请求信息
        String message = new String(mqttMessage.getPayload());
        if (!pattern.matcher(message).matches()) {
            return;
        }

        String cpuid = topic.split("/")[4];
        String name = userService.getPadByCPUID(cpuid);
        if (name == null) {
            String nameLast = userService.getNameLast();
            name = StringUtil.increaseString(nameLast);
            userService.insertPad(name, cpuid);
        }

        String messagePub = "$WID:" + name + "W$";
        log.info("messagePub: " + messagePub);
        clientPublish.publish(messagePub, cpuid);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        try {
            System.out.println("Has deliveried " + token.getMessage().toString());
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
