package com.springframework.sfgbreweryjms.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springframework.sfgbreweryjms.config.JmsConfig;
import com.springframework.sfgbreweryjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MessageSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    private int i = 0;

    //@Scheduled(fixedRate = 2000)
    public void sendMessage() {
        HelloWorldMessage message = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello, I'am Jim : " + (i++))
                .build();

        //  System.out.println("Sending message");
        jmsTemplate.convertAndSend(JmsConfig.MESSAGE_Q, message);
        //System.out.println("message sent! " + message);
    }

    @Scheduled(fixedRate = 2000)
    public void sendAndReceiveReply() throws JMSException {
        HelloWorldMessage message = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello")
                .build();

        Message rcvMsg = jmsTemplate.sendAndReceive(JmsConfig.SEND_N_RECV_Q, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                try {
                    Message m = session.createTextMessage(objectMapper.writeValueAsString(message));
                    m.setStringProperty("_type", "com.springframework.sfgbreweryjms.model.HelloWorldMessage");
                    System.out.println("Sending hello >>>> ");
                    return m;
                } catch (JsonProcessingException e) {
                    throw new JMSException("Boom: " + e.getMessage());
                }
            }
        });

        System.out.println("<<< Received Reply: " + rcvMsg.getBody(String.class).toString());
    }


}
