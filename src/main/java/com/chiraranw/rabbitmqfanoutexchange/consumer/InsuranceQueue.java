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
public class InsuranceQueue {

    @RabbitListener(queues = RabbitMQConfigs.INSURANCE_QUEUE)
    public void openInsuranceAccount(Message message) throws IOException {
        ObjectMapper objectMapper= new ObjectMapper();
        Employee epm=objectMapper.readValue(message.getBody(),Employee.class);
        log.info("Opened insurance account for {}: ", epm);
    }
}
