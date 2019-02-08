package com.celadonsea.palm.test.listener;

import com.celadonsea.palm.client.MessageClient;
import com.celadonsea.palm.config.MessageClientConfig;
import com.celadonsea.palm.listener.CallBack;

public class TestCallBack extends CallBack {
    /**
     * Constructor sets the message client and the executor service for
     * the multi thread processing.
     *
     * @param messageClient       message client
     * @param messageClientConfig message client configuration
     */
    public TestCallBack(MessageClient messageClient, MessageClientConfig messageClientConfig) {
        super(messageClient, messageClientConfig);
    }
}
