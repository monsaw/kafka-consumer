package com.hommies.kafkaconsumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration



public class KafkaConfig {

    private static final int NUMBER_OF_CONSUMERS = 4;

    @Bean
    public ConsumerFactory<String, Object> consumerFactory(){
         return new DefaultKafkaConsumerFactory<>(consumerConfig());
//        return new DefaultKafkaConsumerFactory<>(consumerConfig(),  new StringDeserializer(),
//                new JsonDeserializer<>(Object.class,false));

    }

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory(
//            ConsumerFactory<String, String> consumerFactory) {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//        factory.setConcurrency(NUMBER_OF_CONSUMERS); // Set the number of concurrent consumers
//        factory.getContainerProperties().setPollTimeout(3000); // Set the poll timeout
//        return factory;
//    }



        public Map<String, Object> consumerConfig(){
            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"MONSURU:9092");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
            props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.hommies.kafkaconsumer.dto");
            props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.hommies.kafkaconsumer.dto.Customer");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "hm");


            return props;




        }


    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory(
             ) {
        ConcurrentKafkaListenerContainerFactory<String, Object> listenerContainerFactory =
                new ConcurrentKafkaListenerContainerFactory<>();
        listenerContainerFactory.setConsumerFactory(consumerFactory());
        listenerContainerFactory.setConcurrency(NUMBER_OF_CONSUMERS);
        return listenerContainerFactory;
    }




    }


