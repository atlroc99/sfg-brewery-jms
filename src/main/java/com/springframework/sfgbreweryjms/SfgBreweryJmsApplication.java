package com.springframework.sfgbreweryjms;

import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SfgBreweryJmsApplication {


    public static void main(String[] args) throws Exception {
        //Setting up the embedded activemq server: 

        ActiveMQServer mqServer = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
                .setPersistenceEnabled(false)
                .setJournalDirectory("target/data/journal")
                .setSecurityEnabled(false)
                .addAcceptorConfiguration("invm", "vm://0"));


        mqServer.start();

        SpringApplication.run(SfgBreweryJmsApplication.class, args);
    }

}
