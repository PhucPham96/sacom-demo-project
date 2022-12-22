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
        UserEntity user1 = new UserEntity("1001", "Nguyễn Văn A", BigInteger.valueOf(10000000));
        UserEntity user2 = new UserEntity("1002", "Nguyễn Văn B", BigInteger.valueOf(10000000));
        UserEntity user3 = new UserEntity("1003", "Nguyễn Văn C", BigInteger.valueOf(10000000));
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
