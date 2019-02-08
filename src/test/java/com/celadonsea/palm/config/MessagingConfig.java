package com.celadonsea.palm.config;

import com.celadonsea.palm.client.MessageClient;
import com.celadonsea.palm.test.client.LetterBox;
import com.celadonsea.palm.test.client.TestMessageClient;
import com.celadonsea.palm.test.config.TestMessageConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    @Bean
    public MessageClient messageClient() {
        TestMessageClient testMessageClient = new TestMessageClient(new TestMessageConfiguration());
        testMessageClient.connect();
        return testMessageClient;
    }

    @Bean
    public LetterBox letterBox(MessageClient messageClient) {
        return new LetterBox(messageClient);
    }
}
