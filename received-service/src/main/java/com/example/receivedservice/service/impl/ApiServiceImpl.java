package com.example.receivedservice.service.impl;

import com.example.receivedservice.configuration.exception.CommonException;
import com.example.receivedservice.dto.TransactionDto;
import com.example.receivedservice.entity.TransactionEntity;
import com.example.receivedservice.entity.UserEntity;
import com.example.receivedservice.repository.TransactionRepository;
import com.example.receivedservice.repository.UserRepository;
import com.example.receivedservice.service.ApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiServiceImpl implements ApiService {
    final ObjectMapper customObjectMapper;

    final UserRepository userRepository;

    final TransactionRepository transactionRepository;

    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<TransactionEntity> getAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    @Transactional
    public void credit(TransactionDto dto) throws CommonException {
        String accountId = dto.getAccountId();
        UserEntity user = userRepository.findByAccountId(accountId);
        if(user == null) {
            throw new CommonException("User not found");
        }
        BigInteger ballance = user.getBallanceAmount();
        BigInteger credit = dto.getCreditAmount();
        user.setBallanceAmount(ballance.add(credit));
        userRepository.save(user);

        TransactionEntity transaction = new TransactionEntity(accountId, dto.getTransferId(), credit, dto.getTransactionId());
        transactionRepository.save(transaction);
    }
}
