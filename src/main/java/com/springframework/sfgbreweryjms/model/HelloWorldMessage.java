package com.springframework.sfgbreweryjms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldMessage implements Serializable {

    /***
     * we are sending message as jms text message. So the Serializable is not that importat
     * but if we were to send messages as JMS object then the Serializable is important
     * we are going to send the text message as json, both has a sender and receiver
     * */
    static final long serialVersionUID = -4012023952012489202L;
    
    private UUID id;
    private String message;
}
