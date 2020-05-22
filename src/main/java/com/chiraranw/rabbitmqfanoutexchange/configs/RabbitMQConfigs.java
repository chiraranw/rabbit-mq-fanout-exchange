package com.chiraranw.rabbitmqfanoutexchange.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfigs {

    //Queues names
    public static final String RESIDENTIAL_QUEUE = "residential_queue";
    public static final String BANKING_QUEUE = "banking_queue";
    public static final String INSURANCE_QUEUE = "insurance_queue";

    //Exchange
    public static final String EXCHANGE = "fanout_exchange";
    private static final FanoutExchange fanoutExchange = new FanoutExchange(EXCHANGE, false, true);

    @Bean
    public Declarables declarables() {
        Queue rq = new Queue(RESIDENTIAL_QUEUE);
        Queue bq = new Queue(BANKING_QUEUE);
        Queue iq = new Queue(INSURANCE_QUEUE);
        return new Declarables(
                rq, bq, iq,
                fanoutExchange,
                BindingBuilder.bind(rq).to(fanoutExchange),
                BindingBuilder.bind(bq).to(fanoutExchange),
                BindingBuilder.bind(iq).to(fanoutExchange)
        );
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate= new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }


}
