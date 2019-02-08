package com.celadonsea.palm.test.client;

import com.celadonsea.palm.client.MessageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import java.util.List;

@RequiredArgsConstructor
public class LetterBox {

    private final MessageClient messageClient;

    public void drop(String topic, byte[] message) {
        checkClient();
        ((TestMessageClient)messageClient).getCallBack().messageArrived(topic, message);
    }

    public int messageCount(String topic) {
        checkClient();
        return ((TestMessageClient)messageClient).getPublishedMessageCount(topic);
    }

    public List<byte[]> getMessages(String topic) {
        checkClient();
        return ((TestMessageClient)messageClient).getPublishedMessages(topic);
    }

    private void checkClient() {
        Assert.notNull(messageClient, "No message client found");
        Assert.isTrue(messageClient instanceof TestMessageClient, "Message client is not for testing purpose.");
    }
}
