package com.example.orchestratorservice.activities.impl;

import com.example.orchestratorservice.activities.SenderServiceActivity;
import com.example.orchestratorservice.dto.TransferMoneyRequestDTO;
import com.example.orchestratorservice.feignClient.SenderUserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SenderServiceActivityImpl implements SenderServiceActivity {

    @Autowired
    private SenderUserClient senderUserClient;

    @Override
    public void deductMoney(TransferMoneyRequestDTO transferMoneyRequestDTO) {
        log.info("Deduct Money Activity: ");
        var res = senderUserClient.deductMoney(transferMoneyRequestDTO);
        log.info("Deduct Sender User Service Response: ", senderUserClient.deductMoney(transferMoneyRequestDTO));
    }

    @Override
    public void refundMoney(TransferMoneyRequestDTO transferMoneyRequestDTO) {
        log.info("Refund Money Activity: ");
        var response = senderUserClient.refund(transferMoneyRequestDTO);
        log.info("Refund Sender User Service Response: ", senderUserClient.refund(transferMoneyRequestDTO));

    }
}
