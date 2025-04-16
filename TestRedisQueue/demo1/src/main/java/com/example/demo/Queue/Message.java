package com.example.demo.Queue;

import com.example.demo.Redis.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Message {
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private JmsTemplate jmsTemplate;
    User userCheck = null;
    @JmsListener(destination = "myQueue-request")
    public void receiveMessage(String response) throws JsonProcessingException {
        User user = objectMapper.readValue(response, User.class);

        System.out.println("Received message: " + user.getMessage());

        user.setMessage("SvB:Updated");
        String userString = objectMapper.writeValueAsString(user);
        jmsTemplate.convertAndSend("myQueue-response", userString);


    }



}