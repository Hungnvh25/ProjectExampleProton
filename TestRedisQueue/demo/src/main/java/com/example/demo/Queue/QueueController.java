package com.example.demo.Queue;

import com.example.demo.Redis.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueueController {

    @Autowired
    QueueService queueService;

    @PostMapping("/send")
    public void SendMessage(@RequestBody User request) throws JsonProcessingException {
        queueService.sendMessage(request);
    }
}
