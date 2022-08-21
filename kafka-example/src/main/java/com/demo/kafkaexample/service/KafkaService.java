package com.demo.kafkaexample.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.demo.kafkaexample.bean.KafkaMessageBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaService {
	
	public static final ObjectMapper MAPPER=new ObjectMapper();
	
	@Autowired
	public KafkaTemplate<String, String> kafkaTemplate;
	
	
	public void sendMessage(String message) throws JsonMappingException, JsonProcessingException {
		JsonNode requestData=MAPPER.readTree(message);
		KafkaMessageBean kafkaMessageBean=MAPPER.convertValue(requestData, KafkaMessageBean.class);
		kafkaTemplate.send(kafkaMessageBean.getTopicName(), kafkaMessageBean.getTopicMessage());
	}
	
	public NewTopic myTopic(String topicName) throws JsonMappingException, JsonProcessingException {
		JsonNode requestData=MAPPER.readTree(topicName);
		KafkaMessageBean kafkaMessageBean=MAPPER.convertValue(requestData, KafkaMessageBean.class);
		return TopicBuilder.name(kafkaMessageBean.getTopicName()).build();
		
		
		/*Map<String, String> newTopicConfig = new HashMap<>();
		newTopicConfig.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_COMPACT);
		newTopicConfig.put(TopicConfig.COMPRESSION_TYPE_CONFIG, "lz4");

		return new NewTopic(kafkaMessageBean.getTopicName(), 1, (short) 1).configs(newTopicConfig);*/
		
	}
	
}
