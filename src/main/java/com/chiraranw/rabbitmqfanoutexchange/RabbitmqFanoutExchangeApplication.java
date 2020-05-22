package com.chiraranw.rabbitmqfanoutexchange;

import com.chiraranw.rabbitmqfanoutexchange.model.Employee;
import com.chiraranw.rabbitmqfanoutexchange.producer.EmpBroadCaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
@Slf4j
public class RabbitmqFanoutExchangeApplication implements CommandLineRunner {

    private final EmpBroadCaster empBroadCaster;

    @Autowired
    public RabbitmqFanoutExchangeApplication(EmpBroadCaster empBroadCaster) {
        this.empBroadCaster = empBroadCaster;
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqFanoutExchangeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       while(true){
           empBroadCaster.banking(new Employee(UUID.randomUUID().toString(),"Nation","Developer"));
           empBroadCaster.insurance(new Employee(UUID.randomUUID().toString(),"Nation","Engineer"));
           empBroadCaster.residential(new Employee(UUID.randomUUID().toString(),"Nation","Manager"));
       }

    }
}
