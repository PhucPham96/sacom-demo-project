package com.example.orchestratorservice.workflow.impl;

import com.example.orchestratorservice.activities.ReceiverServiceActivity;
import com.example.orchestratorservice.activities.SenderServiceActivity;
import com.example.orchestratorservice.activities.TransferMoneyCompletedActivity;
import com.example.orchestratorservice.dto.TransferMoneyRequestDTO;
import com.example.orchestratorservice.enums.TaskQueue;
import com.example.orchestratorservice.model.TransactionHistory;
import com.example.orchestratorservice.workflow.TransferMoneyWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.activity.LocalActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;

public class TransferMoneyWorkflowImpl implements TransferMoneyWorkflow {

    private Logger logger = Workflow.getLogger(this.getClass().getName());

    private final LocalActivityOptions transferMoneyActivityOptions =
            LocalActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofSeconds(10))
                    .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(10).build())
                    .build();

    private final ActivityOptions senderServiceActivityOptions =
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofSeconds(10))
                    .setTaskQueue(TaskQueue.SENDER_ACTIVITY_WORKFLOW_QUEUE.name())
                    .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(3).build())
                    .build();

    private final ActivityOptions receiverServiceActivityOptions =
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofSeconds(10))
                    .setTaskQueue(TaskQueue.RECEIVER_ACTIVITY_WORKFLOW_QUEUE.name())
                    .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(3).build())
                    .build();

    private final TransferMoneyCompletedActivity transferMoneyCompletedActivity =
            Workflow.newLocalActivityStub(TransferMoneyCompletedActivity.class, transferMoneyActivityOptions);

    private final SenderServiceActivity senderServiceActivity =
            Workflow.newActivityStub(SenderServiceActivity.class, senderServiceActivityOptions);

    private final ReceiverServiceActivity receiverServiceActivity =
            Workflow.newActivityStub(ReceiverServiceActivity.class, receiverServiceActivityOptions);

    @Override
    public void startTransferMoneyWorkflow(TransferMoneyRequestDTO transferMoneyRequestDTO, TransactionHistory transactionHistory) {
        logger.info("Transfer money start workflow....");
        logger.info("Workflow ID {}", Workflow.getInfo().getWorkflowId());

        logger.info("Sender Service Deduct Money...");
        try {
            // Debit money sender user
            senderServiceActivity.deductMoney(transferMoneyRequestDTO);
            transferMoneyCompletedActivity.completedDeductMoneyOfSenderUser(transactionHistory);
        } catch (Exception e) {
            logger.info("Sender Service Deduct Money Failed..." + e.getMessage());
        }


        logger.info("Receiver Service Update Money...");
        boolean isFailed = false;
        try {
            receiverServiceActivity.updateMoney(transferMoneyRequestDTO);
        } catch (Exception e) {
            logger.info("Receiver Service Update Money Failed..." + e.getMessage());
            // Save transaction history
            transferMoneyCompletedActivity.updateMoneyForReceiverUserFail(transactionHistory);
            // Rollback money of sender user
            senderServiceActivity.refundMoney(transferMoneyRequestDTO);
            isFailed = true;
        }

        if (!isFailed) {
            logger.info("Completed Workflow...");
            transferMoneyCompletedActivity.completedTransferMoney(transferMoneyRequestDTO, transactionHistory);
        }
    }
}
