package com.example.senderservice.service;

import com.example.senderservice.configuration.exception.CommonException;
import com.example.senderservice.dto.TransactionDto;
import com.example.senderservice.entity.TransactionEntity;
import com.example.senderservice.entity.UserEntity;

import java.util.List;

public interface ApiService {
    List<UserEntity> getAllUser();
    UserEntity findUserByAccountId(String accountId);
    void deduct(TransactionDto dto) throws CommonException;
    List<TransactionEntity> getAllTransaction();
    void refund(TransactionDto dto) throws CommonException;
}
