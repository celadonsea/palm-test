# Messaging framework

## Introduction

Messaging Framework is designed to handle asynchronous message transfer on producer and consumer side too.
Handles simple publishing, automatic subscribing and easy topic parsing.

## What does it do

#### 1) Automatic topic subscription

#### 2) Controller-way incoming message handling

#### 3) Automatic topic subscription

## How to use

Auto configuration has to be enabled in the Spring boot application.

```java
@EnableAutoConfiguration
@SpringBootApplication
public class MessageingtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageingtestApplication.class, args);
	}
}
```

The messaging client configuration should have the mandatory settings and the client bean
should be created with this configuration.

```java
@Getter
@Configuration
public class MessagingConfigurationA implements MessageClientConfig {

    private String clientType = "mqtt";

    private String clientId = "SampleClient";

    private String brokerUrl = "tcp://localhost:1883";

    private int maxInFlight = 100;

    private int connectionTimeout = 1000;

    private int keepAliveInterval = 1000;

    private boolean connectionSecured = false;

    private int maxThread = 10;

    private int qos = 2;

    @Bean
    public MessageClient messageClient() {
        MessageClient client = MessageClientFactory.getFactory().getClient(this);
        client.connect();
        return client;
    }
}
```

The message controller subscribes automatically to the requested topic and it's bound
to the corresponding client. 

```java
@MessagingController(topic = "my/topic/{topicVariable}", client = "messageClient")
public class MyController {

    @Listener("/{otherVariable}")
    public void sampleSubscription(@TopicParameter("topicVariable") String topicVariable, 
                                   @TopicParameter("otherVariable") int otherVariable,
                                   @MessageBody String body,
                                   MessageContext messageContext) {
        // some message processing stuff
    }
}
```

The publisher resolves templates and topic variables and publishes messages on a builder way.

```java
@Component
public class MyComponent {
    
    @Autowired
    private MessageClient messageClient;
    
    public void someMethod() {
        messageClientA.publisher()
            .topic("messaging/sample/{stringVariable}/topic/{numberVariable}")
            .variable("stringVariable", "forFunny")
            .variable("numberVariable", 42)
            .message("Hello world!".getBytes())
            .qos(2)
            .publish();
    }
}
```

## Binaries

TBD

## Bugs and feedback

For bugs, questions and discussions please use the [GitHub Issues](https://github.com/Netflix/Hystrix/issues).

## LICENCE

Copyright 2019 Rafael Revesz

TBD