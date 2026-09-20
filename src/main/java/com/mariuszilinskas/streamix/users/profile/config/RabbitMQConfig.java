package com.mariuszilinskas.streamix.users.profile.config;

import com.mariuszilinskas.streamix.users.profile.properties.RabbitMQProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final RabbitMQProperties rabbitMQProperties;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(rabbitMQProperties.exchange());
    }

    @Bean
    public Queue profileSetupQueue() {
        return new Queue(rabbitMQProperties.queues().profileSetup(), true);
    }

    @Bean
    public Binding profileSetupBinding() {
        return BindingBuilder.bind(profileSetupQueue())
                .to(exchange())
                .with(rabbitMQProperties.routingKeys().profileSetup());
    }

    @Bean
    public Queue deleteUserDataQueue() {
        return new Queue(rabbitMQProperties.queues().deleteUserData(), true);
    }

    @Bean
    public Binding deleteUserDataBinding() {
        return BindingBuilder.bind(deleteUserDataQueue())
                .to(exchange())
                .with(rabbitMQProperties.routingKeys().deleteUserData());
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter jacksonConverter() {
        return new JacksonJsonMessageConverter();
    }

}
