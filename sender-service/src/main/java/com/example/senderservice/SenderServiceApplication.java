package com.example.senderservice;

import com.example.senderservice.entity.UserEntity;
import com.example.senderservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;

@SpringBootApplication
public class SenderServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SenderServiceApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
//        RandomUtils.nextInt()
        UserEntity user1 = new UserEntity("SEN001", "SE1", BigInteger.valueOf(10000000));
        UserEntity user2 = new UserEntity("SEN002", "SE2", BigInteger.valueOf(10000000));
        userRepository.save(user1);
        userRepository.save(user2);
    }
}
