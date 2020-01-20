package com.springframework.sfgbreweryjms.sender;

import com.springframework.sfgbreweryjms.config.JmsConfig;
import com.springframework.sfgbreweryjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MessageSender {

    private final JmsTemplate jmsTemplate;

    private int i = 0;
    
    @Scheduled(fixedRate = 2000)
    public void sendMessage(){
        HelloWorldMessage message = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello, I'am Jim : " + (i++))
                .build();

        System.out.println("Sending message");

        jmsTemplate.convertAndSend(JmsConfig.MESSAGE_Q, message);

        System.out.println("message sent! " + message);

    }

}
