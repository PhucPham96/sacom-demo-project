package com.example.orchestratorservice.activities.impl;

import com.example.orchestratorservice.activities.ReceiverServiceActivity;
import com.example.orchestratorservice.dto.TransferMoneyRequestDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceiverServiceActivityImpl implements ReceiverServiceActivity {

    @Override
    public void updateMoney(TransferMoneyRequestDTO transferMoneyRequestDTO) {
//        Double failFlag = 1 / transferMoneyRequestDTO.getAmount() - 2000;
        log.info("Update money for user receiver", true);
    }
}
