package com.example.demo.Queue;

import com.example.demo.Redis.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueueService {
    @Autowired
    private JmsTemplate jmsTemplate;
    ObjectMapper objectMapper = new ObjectMapper();
    public void sendMessage(User request) throws JsonProcessingException {
        String requestString = objectMapper.writeValueAsString(request);

        jmsTemplate.convertAndSend("myQueue-request", requestString);


    }


    @JmsListener(destination = "myQueue-response")
    public void receiveMessage(String response) throws JsonProcessingException {
        User user = objectMapper.readValue(response, User.class);
        System.out.println("Received message: " + user.getMessage());

    }
}