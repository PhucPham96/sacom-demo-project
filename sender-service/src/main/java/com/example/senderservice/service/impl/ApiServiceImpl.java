package com.example.senderservice.service.impl;

import com.example.senderservice.configuration.exception.CommonException;
import com.example.senderservice.dto.TransactionDto;
import com.example.senderservice.entity.TransactionEntity;
import com.example.senderservice.entity.UserEntity;
import com.example.senderservice.enumerator.Status;
import com.example.senderservice.repository.TransactionRepository;
import com.example.senderservice.repository.UserRepository;
import com.example.senderservice.service.ApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class ApiServiceImpl implements ApiService {
    @Autowired
    @Qualifier("customObjectMapper")
    private ObjectMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<TransactionEntity> getAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public UserEntity findUserByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId);
    }

    @Override
    @Transactional
    public void deduct(TransactionDto dto) throws CommonException {
        String accountId = dto.getAccountId();
        UserEntity user = userRepository.findByAccountId(accountId);
        if(user == null) {
            throw new CommonException("User not found");
        }
        BigInteger ballance = user.getBallanceAmount();
        BigInteger debit = dto.getDebitAmount();
        if(ballance.compareTo(debit) < 0) {
            throw new CommonException("Not enough money");
        }
        user.setBallanceAmount(ballance.subtract(debit));
        userRepository.save(user);

        TransactionEntity transaction = new TransactionEntity(accountId, dto.getRecipientId(), debit);
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void refund(TransactionDto dto) throws CommonException {
        TransactionEntity transaction = transactionRepository
                .findById(dto.getTransactionId())
                .orElseThrow(() -> new CommonException("Transaction not found"));
        String accountId = dto.getAccountId();
        String recipientId = dto.getRecipientId();
        BigInteger debitAmount = dto.getDebitAmount();
        if(accountId.equals(transaction.getAccountId()) == false) {
            throw new CommonException("AccountId not correct");
        }
        if(recipientId.equals(transaction.getRecipientId()) == false) {
            throw new CommonException("RecipientId not correct");
        }
        if(debitAmount.compareTo(transaction.getDebitAmount()) != 0) {
            throw new CommonException("Debit Amount not correct");
        }
        UserEntity user = userRepository.findByAccountId(accountId);
        user.setBallanceAmount(user.getBallanceAmount().add(debitAmount));
        userRepository.save(user);

        transaction.setStatus(Status.REFUND);
        transactionRepository.save(transaction);
    }

//    public static void main(String[] args) {
//        BigInteger a = BigInteger.valueOf(10);
//        BigInteger b = BigInteger.valueOf(15);
//        System.out.println(a.compareTo(b));
//    }
}
