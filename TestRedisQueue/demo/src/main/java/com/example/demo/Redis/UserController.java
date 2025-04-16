package com.example.demo.Redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/save")
    public User save(@RequestBody User user) throws JsonProcessingException {

        User saveUser = userService.save(user);

        return saveUser;
    }

    @PostMapping("/save/{key}")
    public User save(@RequestBody User user, @PathVariable String key) throws JsonProcessingException {

        User saveUser = userService.saveNewKey(user, key);

        return saveUser;
    }

    @PostMapping("/saveTime")
    public User saveTime(@RequestBody User user) throws JsonProcessingException {

        return userService.saveTime(user);
    }


    @PostMapping("/user/{id}")
    public String save(@PathVariable String id) {

        String jsonUser = userService.findUserById(id);

        return jsonUser;
    }


    @PostMapping("/userTime/{id}")
    public String saveTime(@PathVariable String id) {

        String jsonUser = userService.findUserByKey(id);

        return jsonUser;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {

        return userService.delete(id);
    }

}
