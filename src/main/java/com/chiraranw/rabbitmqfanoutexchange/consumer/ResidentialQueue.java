package com.chiraranw.rabbitmqfanoutexchange.consumer;

import com.chiraranw.rabbitmqfanoutexchange.configs.RabbitMQConfigs;
import com.chiraranw.rabbitmqfanoutexchange.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ResidentialQueue {

    @RabbitListener(queues = RabbitMQConfigs.RESIDENTIAL_QUEUE)
    public void openResidentialAccount(Message message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee epm = objectMapper.readValue(message.getBody(), Employee.class);
        log.info("Opened residential account for {}: ", epm);
    }
}
