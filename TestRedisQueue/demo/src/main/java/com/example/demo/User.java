package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("User")
public class User  {
    private String id;
    private String userName;
    public String message;

}
