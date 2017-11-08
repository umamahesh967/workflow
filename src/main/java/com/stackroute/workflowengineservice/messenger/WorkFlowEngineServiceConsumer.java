package com.stackroute.workflowengineservice.messenger;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.stackroute.workflowengineservice.model.JenkinsJob;

@Service
public class WorkFlowEngineServiceConsumer {
    
    @KafkaListener(topics="${spring.kafka.consumer.group-id}")
   public void processMessage(JenkinsJob model) {
        System.out.println("received content = " + model);
   }
}
