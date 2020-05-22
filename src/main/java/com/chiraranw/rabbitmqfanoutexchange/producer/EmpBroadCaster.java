package com.chiraranw.rabbitmqfanoutexchange.producer;

import com.chiraranw.rabbitmqfanoutexchange.configs.RabbitMQConfigs;
import com.chiraranw.rabbitmqfanoutexchange.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmpBroadCaster {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public EmpBroadCaster(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void banking(Employee employee) {
        rabbitTemplate.convertAndSend(RabbitMQConfigs.EXCHANGE,"", employee);
        log.info("Sending {}  to banking queue", employee);
    }

    public void insurance(Employee employee) {
        rabbitTemplate.convertAndSend(RabbitMQConfigs.EXCHANGE,"",employee);
        log.info("Sending {}  to insurance queue", employee);
    }

    public void residential(Employee employee) {
        rabbitTemplate.convertAndSend(RabbitMQConfigs.EXCHANGE,"",employee);
        log.info("Sending {}  to residential queue", employee);
    }
}
