package com.example.demo.Redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User save(User user) throws JsonProcessingException {
        return userDao.save(user);

    }

    public User saveTime(User user) throws JsonProcessingException {
        return userDao.saveTime(user);

    }

    public User saveNewKey(User user, String key) throws JsonProcessingException {
        return userDao.saveNewKey(user, key);

    }

    public String findUserById(String id) {
        return userDao.findUserById(id);

    }

    public String findUserByKey(String id) {
        return userDao.findUserByKey(id);

    }

    public String delete(String id) {
        return userDao.deleteUser(id);
    }

}
