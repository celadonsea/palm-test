package com.celadonsea.palm.controller;

import com.celadonsea.palm.annotation.Listener;
import com.celadonsea.palm.annotation.MessageBody;
import com.celadonsea.palm.annotation.MessagingController;
import com.celadonsea.palm.annotation.TopicParameter;
import com.celadonsea.palm.message.Message;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@MessagingController(topic = "mytopic", client = "messageClient")
public class TestMessagingController {

    @Getter
    private String incomingMessage;

    @Listener("subtopic/{variable}")
    public void listenerMethod(@TopicParameter("variable") String var1,
                               @MessageBody Message<String> message) {
        incomingMessage = message.getValue() + "#" + var1;
    }
}