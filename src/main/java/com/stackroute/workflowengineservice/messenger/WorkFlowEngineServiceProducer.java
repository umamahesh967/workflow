package com.stackroute.workflowengineservice.messenger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.stackroute.workflowengineservice.model.JenkinsJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * For producer
 *
 */
@Service
public class WorkFlowEngineServiceProducer {
    
	 private static final Logger LOGGER = LoggerFactory.getLogger(WorkFlowEngineServiceProducer.class);

	/*
	 * for now testing with string. It will be replaced to ModelForJenkins.
	 * 
	 * */
	@Autowired
    private KafkaTemplate<String, JenkinsJob> kafkaTemplate;
    
//    @Value("${spring.kafka.producer.group-id}")
    String kafkaTopic = "logdata";// = "trigger11234"; 
    // "${kafka.topic.bootnew}";
    
    // refer : https://kafka.apache.org/quickstart
    public void send(JenkinsJob model) {
    	LOGGER.info("sending payload='{}'", model);
        LOGGER.info("sending data=" + model);
        LOGGER.info(kafkaTopic);
        kafkaTemplate.send(kafkaTopic, model);
        LOGGER.info("sending " +  " done ..........." );
    }
}

