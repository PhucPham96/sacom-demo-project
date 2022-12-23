package com.example.receivedservice;

import com.example.receivedservice.entity.UserEntity;
import com.example.receivedservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;

@SpringBootApplication
@RequiredArgsConstructor
public class ReceivedServiceApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(ReceivedServiceApplication.class, args);
    }

    final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
//        RandomUtils.nextInt()
        UserEntity user1 = new UserEntity("REC001", "Receive 1", BigInteger.valueOf(800000));
        UserEntity user2 = new UserEntity("REC002", "Receive 2", BigInteger.valueOf(800000));
        userRepository.save(user1);
        userRepository.save(user2);
    }
}
