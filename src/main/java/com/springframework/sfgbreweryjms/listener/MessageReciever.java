package com.springframework.sfgbreweryjms.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springframework.sfgbreweryjms.config.JmsConfig;
import com.springframework.sfgbreweryjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MessageReciever {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

/*    @JmsListener(destination = JmsConfig.MESSAGE_Q)
    public void receiveMessage(@Payload HelloWorldMessage incomingMsg,
                               @Header MessageHeaders headers,
                               Message message) throws JMSException {
        System.out.println("------------------------------------------");

        System.out.println("Listened and received...");
        System.out.println("Message ID : " + incomingMsg.getId());
        System.out.println("Message: " + incomingMsg.getMessage());

        HelloWorldMessage payLoad = (HelloWorldMessage) message.getPayload();
        System.out.println("Payload from messsage");
        System.out.println(payLoad);

        System.out.println("------------------------------------------");
    }*/


    @JmsListener(destination = JmsConfig.SEND_N_RECV_Q)
    public void receiveAndReply(HelloWorldMessage helloWorldMessage, MessageHeaders messageHeaders, Message msg) throws JMSException, JsonProcessingException {
        System.out.println("Received * Incoming * Message");
        HelloWorldMessage payLoad = objectMapper.readValue(msg.getBody(String.class), HelloWorldMessage.class);
        System.out.println("ID: " + payLoad.getId());
        System.out.println("MSG: " + payLoad.getMessage());

        
        HelloWorldMessage msgPayload = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("!!!<<< WORLD >>> !!! ")
                .build();

        System.out.println("REPLYING TO: " + msgPayload);
        jmsTemplate.convertAndSend(msg.getJMSReplyTo(), msgPayload);
    }
}
