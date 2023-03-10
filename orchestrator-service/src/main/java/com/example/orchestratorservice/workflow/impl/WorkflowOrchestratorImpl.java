package com.example.orchestratorservice.workflow.impl;

import com.example.orchestratorservice.dto.OrderDTO;
import com.example.orchestratorservice.dto.TransferMoneyRequestDTO;
import com.example.orchestratorservice.enums.TaskQueue;
import com.example.orchestratorservice.model.Order;
import com.example.orchestratorservice.model.TransactionHistory;
import com.example.orchestratorservice.workflow.OrderFulfillmentWorkflow;
import com.example.orchestratorservice.workflow.TransferMoneyWorkflow;
import com.example.orchestratorservice.workflow.WorkflowOrchestrator;
import com.example.orchestratorservice.workflow.WorkflowOrchestratorClient;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class WorkflowOrchestratorImpl implements WorkflowOrchestrator {

    private final WorkflowOrchestratorClient workflowOrchestratorClient;

    @Override
    public void createOrder(Order order) {
        var orderDTO = map(order);
        var workflowClient = workflowOrchestratorClient.getWorkflowClient();
        String transactionId = UUID.randomUUID().toString();
        var orderFulfillmentWorkflow =
                workflowClient.newWorkflowStub(
                        OrderFulfillmentWorkflow.class,
                        WorkflowOptions.newBuilder()
                                //TODO
//                                .setWorkflowId("OrderFulfillmentWorkflow" + "-" + orderDTO.getOrderId())
                                .setWorkflowId("OrderFulfillmentWorkflow-test" + "-" + transactionId)
                                .setTaskQueue(TaskQueue.ORDER_FULFILLMENT_WORKFLOW_TASK_QUEUE.name())
                                .build());
        WorkflowClient.start(orderFulfillmentWorkflow::createOrder, orderDTO);
    }

    @Override
    public void startTransferMoneyWorkflow(TransferMoneyRequestDTO transferMoneyRequestDTO, TransactionHistory transactionHistory) {
        var workflowClient = workflowOrchestratorClient.getWorkflowClient();

        var transferMoneyWorkflow =
                workflowClient.newWorkflowStub(
                        TransferMoneyWorkflow.class,
                        WorkflowOptions.newBuilder()
                                //TODO
//                                .setWorkflowId("OrderFulfillmentWorkflow" + "-" + orderDTO.getOrderId())
                                .setWorkflowId("TransferMoneyWorkflow-" + "-" + transactionHistory.getId())
                                .setTaskQueue(TaskQueue.TRANSFER_MONEY_WORKFLOW_TASK_QUEUE.name())
                                .build());
        WorkflowClient.start(transferMoneyWorkflow::startTransferMoneyWorkflow, transferMoneyRequestDTO, transactionHistory);
    }

    private OrderDTO map(Order order) {
        var orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setProductId(order.getProductId());
        orderDTO.setPrice(order.getPrice());
        orderDTO.setQuantity(order.getQuantity());
        return orderDTO;
    }
}
