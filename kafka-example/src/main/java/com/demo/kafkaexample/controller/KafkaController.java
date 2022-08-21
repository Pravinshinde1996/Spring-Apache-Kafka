package com.demo.kafkaexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.kafkaexample.service.KafkaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("api/v1/messages")
public class KafkaController {

	@Autowired
	public KafkaService kafkaService;
	

	@PostMapping(path="/sendMessage")
	public void publish(@RequestBody String message) throws JsonMappingException, JsonProcessingException {
		kafkaService.sendMessage(message);
	}
	
	@PostMapping(path="/createTopic")
	public void createTopic(@RequestBody String topicName) throws JsonMappingException, JsonProcessingException {
		kafkaService.myTopic(topicName);
	}
	
}
