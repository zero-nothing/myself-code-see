package register.com.starter;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import register.com.mqtt.ClientPublish;
import register.com.mqtt.ClientReceive;
import register.com.utils.ApplicationContextUtil;

@Component
public class MyApplicationInitStarter implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 启动发布MQTT客户端
        ClientPublish clientPublish = ApplicationContextUtil.getBean(ClientPublish.class);
        clientPublish.init();
        // 启动接收MQTT客户端
        ClientReceive clientReceive = ApplicationContextUtil.getBean(ClientReceive.class);
        clientReceive.start();
    }
}
