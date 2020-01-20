package com.springframework.sfgbreweryjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.Queue;

@Configuration
public class JmsConfig {
    /**
     * sending message to jms as java object will be conveted as json text for payload
     * and receiving json text payload from the JMS gets converted to Java object
     * */

    public final static String MESSAGE_Q = "MY_QUEUE";
    public final static String SEND_N_RECV_Q= "SEND_N_RECEIVE_QUEUE";

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
    
}
