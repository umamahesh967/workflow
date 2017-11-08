package com.stackroute.workflowengineservice.messenger;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.stackroute.workflowengineservice.model.JenkinsJob;


@EnableKafka
@Configuration
public class WorkFlowEngineServiceConsumerConfig {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;
    
    @Value("kafka-group")
    private String groupId;
    
    @Bean
    public ConsumerFactory<String, JenkinsJob> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "something");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<String, JenkinsJob>(props,new StringDeserializer(), 
                  new JsonDeserializer<>(JenkinsJob.class));
//        return new DefaultKafkaConsumerFactory<>(props);
    }
    
     
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, JenkinsJob> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, JenkinsJob> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}