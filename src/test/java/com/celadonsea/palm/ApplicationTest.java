package com.celadonsea.palm;

import com.celadonsea.palm.client.MessageClient;
import com.celadonsea.palm.controller.TestMessagingController;
import com.celadonsea.palm.test.client.LetterBox;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("default")
public class ApplicationTest {

    @Autowired
    private TestMessagingController testMessagingController;

    @Autowired
    private MessageClient testClient;

    @Autowired
    private LetterBox letterBox;

    @Test
    public void shouldRecognizeControllerAndHandleMessage() throws InterruptedException {

        letterBox.drop(
            "mytopic/subtopic/helloWorld",
            ("{'timestamp':42, 'value': 'AnyMessage'}".replaceAll("'", "\"")).getBytes());

        await()
            .atMost(1, TimeUnit.SECONDS)
            .until(() -> "AnyMessage#helloWorld".equals(testMessagingController.getIncomingMessage()));

    }

    @Test
    public void shouldPublishMessage() {

        String testingTopic = "nice/topic/for/test";

        testClient.publisher()
            .topic(testingTopic)
            .message("anyMessage")
            .publish();

        await()
            .atMost(1, TimeUnit.SECONDS)
            .until(() -> 1 == letterBox.messageCount(testingTopic));

        List<byte[]> messages = letterBox.getMessages(testingTopic);
        Assert.assertEquals("anyMessage", new String(messages.get(messages.size() - 1)));
        Assert.assertEquals(0, letterBox.messageCount("any/unknown/topic"));
    }
}
