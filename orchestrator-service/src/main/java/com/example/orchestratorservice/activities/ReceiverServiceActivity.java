package com.example.orchestratorservice.activities;

import com.example.orchestratorservice.dto.TransferMoneyRequestDTO;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface ReceiverServiceActivity {

    void updateMoney(TransferMoneyRequestDTO transferMoneyRequestDTO);
}
