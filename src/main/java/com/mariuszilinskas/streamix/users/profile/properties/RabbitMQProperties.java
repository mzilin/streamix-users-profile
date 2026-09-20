package com.mariuszilinskas.streamix.users.profile.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rabbitmq")
public record RabbitMQProperties(
        String exchange,
        Queues queues,
        RoutingKeys routingKeys
) {

    public record Queues(
            String profileSetup,
            String deleteUserData
    ) {}

    public record RoutingKeys(
            String profileSetup,
            String deleteUserData
    ) {}
}
