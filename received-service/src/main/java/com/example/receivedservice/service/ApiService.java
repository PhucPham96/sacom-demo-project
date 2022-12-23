package com.example.receivedservice.service;

import com.example.receivedservice.configuration.exception.CommonException;
import com.example.receivedservice.dto.TransactionDto;
import com.example.receivedservice.entity.TransactionEntity;
import com.example.receivedservice.entity.UserEntity;

import java.util.List;

public interface ApiService {
    List<UserEntity> getAllUser();
    void credit(TransactionDto dto) throws CommonException;
    List<TransactionEntity> getAllTransaction();
}
