package com.demo.kafkaexample.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
	
	@KafkaListener(topics="java-topic",groupId="groupId")
	void listeners(String data) {
		System.err.println("Listneres received::"+data);
	}
	
	
}
