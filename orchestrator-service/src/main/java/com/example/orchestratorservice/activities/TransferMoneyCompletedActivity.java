package com.example.orchestratorservice.activities;

import com.example.orchestratorservice.dto.TransferMoneyRequestDTO;
import com.example.orchestratorservice.model.TransactionHistory;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface TransferMoneyCompletedActivity {

    void completedTransferMoney(TransferMoneyRequestDTO transferMoneyRequestDTO, TransactionHistory transactionHistory);

    void completedDeductMoneyOfSenderUser(TransactionHistory transactionHistory);

    void updateMoneyForReceiverUserFail(TransactionHistory transactionHistory);
}
