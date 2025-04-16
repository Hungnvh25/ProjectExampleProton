package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class UserDao {
    public static final String HASH_KEY = "User";
    public static final String HASH_KEY_TIME = "TIME";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private RedisTemplate<String, String> template;

    public User save(User user) throws JsonProcessingException {
        String jsonUser = objectMapper.writeValueAsString(user);
        template.opsForHash().put(HASH_KEY, user.getId(), jsonUser);
        return user;
    }

    public User saveTime(User user) throws JsonProcessingException {
        String jsonUser = objectMapper.writeValueAsString(user);

        template.opsForValue().set(HASH_KEY_TIME, jsonUser, 30, TimeUnit.SECONDS);

        return user;
    }

    public User saveNewKey(User user, String key) throws JsonProcessingException {
        String jsonUser = objectMapper.writeValueAsString(user);
        template.opsForHash().put(key, user.getId(), jsonUser);
        return user;
    }

    public String findUserById(String id) {
        String jsonUser = (String) template.opsForHash().get(HASH_KEY, String.valueOf(id));

        return jsonUser;
    }

    public String findUserByKey(String id) {
        String stringUser = template.opsForValue().get(HASH_KEY_TIME);
        return stringUser;
    }

    public String deleteUser(String id) {
        template.opsForHash().delete(HASH_KEY, String.valueOf(id));
        return "Xóa thành công";
    }
}
