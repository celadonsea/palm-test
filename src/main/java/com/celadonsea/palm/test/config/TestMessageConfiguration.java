package com.celadonsea.palm.test.config;

import com.celadonsea.palm.config.MessageClientConfig;
import lombok.Getter;

@Getter
public class TestMessageConfiguration implements MessageClientConfig {

    private String clientDialect= "com.celadonsea.palm.test.config.TestMessageClient";

    private String clientId = "testMessageClient";

    private String brokerUrl = "memory";

    private int maxInFlight = 100;

    private int connectionTimeout = 30;

    private int keepAliveInterval = 30;

    private int qos = 0;

    private boolean connectionSecured = false;

    private int maxThread = 10;

    private int threadKeepAliveTime = 1;
}
