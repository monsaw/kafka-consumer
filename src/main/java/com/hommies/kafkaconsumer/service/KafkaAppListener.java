package com.hommies.kafkaconsumer.service;


import com.hommies.kafkaconsumer.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



@Service
public class KafkaAppListener {

    Logger logger = LoggerFactory.getLogger(KafkaAppListener.class);


   @KafkaListener(topics = "hommies-demo-7", groupId = "hm" ,containerFactory = "kafkaListenerContainerFactory")
    public void consumer1(Customer message){
        logger.info("Consumer1 received message {}", message);

    }


}
