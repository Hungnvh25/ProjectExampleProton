package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    public static final String HASH_KEY = "User";
    public static final String HASH_KEY_TIME = "TIME";
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RedisTemplate<String, String> template;

    @Value("${scheduler.auto-update.enabled:false}")
    private boolean autoUpdateEnabled;

    public String getUserAndSet() throws JsonProcessingException {
        String userString = template.opsForValue().get(HASH_KEY_TIME);
        if (userString != null) {
            User user1 = objectMapper.readValue(userString, User.class);

            if (user1.getUserName().equals("Quan") && user1.getMessage().contains("SvA")) {
                user1.setMessage("SvB:Update");
                String userStringNew = objectMapper.writeValueAsString(user1);
                template.opsForValue().set(HASH_KEY_TIME, userStringNew, 30, TimeUnit.SECONDS);
                System.out.println("Thanh cong");
                return "Thanh cong";
            } else {
                System.out.println("Da cap nhat");
                return "Da cap nhat";
            }
        }
        System.out.println("That bai");
        return "That bai";
    }

    @Scheduled(fixedRate = 10000)
    public void getUserAndSetAuto() throws JsonProcessingException {
        if (autoUpdateEnabled) {
            String userString = template.opsForValue().get(HASH_KEY_TIME);
            if (userString != null) {
                User user1 = objectMapper.readValue(userString, User.class);

                if (user1.getUserName().equals("Quan") && user1.getMessage().contains("SvA")) {
                    user1.setMessage("SvB:Update");
                    String userStringNew = objectMapper.writeValueAsString(user1);
                    template.opsForValue().set(HASH_KEY_TIME, userStringNew, 30, TimeUnit.SECONDS);
                    System.out.println("Thanh cong");
                } else {
                    System.out.println("Da cap nhat");
                }
            }
            System.out.println("That bai");
        } else
            System.out.println("Da tat auto");
    }
}
