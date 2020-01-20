package com.springframework.sfgbreweryjms.listener;

import com.springframework.sfgbreweryjms.config.JmsConfig;
import com.springframework.sfgbreweryjms.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class MessageReciever {

    @JmsListener(destination = JmsConfig.MESSAGE_Q)
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
    }

}
