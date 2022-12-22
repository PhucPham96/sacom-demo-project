package com.example.orchestratorservice.config;

import com.example.orchestratorservice.activities.CompleteOrderActivity;
import com.example.orchestratorservice.activities.ReceiverServiceActivity;
import com.example.orchestratorservice.activities.SenderServiceActivity;
import com.example.orchestratorservice.activities.TransferMoneyCompletedActivity;
import com.example.orchestratorservice.activities.impl.CompleteOrderActivityImpl;
import com.example.orchestratorservice.activities.impl.ReceiverServiceActivityImpl;
import com.example.orchestratorservice.activities.impl.SenderServiceActivityImpl;
import com.example.orchestratorservice.activities.impl.TransferMoneyCompletedActivityImpl;
import com.example.orchestratorservice.worker.OrderWorker;
import com.example.orchestratorservice.worker.ReceiverServiceWorker;
import com.example.orchestratorservice.worker.SenderServiceWorker;
import com.example.orchestratorservice.worker.TransferMoneyWorker;
import com.example.orchestratorservice.workflow.WorkflowOrchestrator;
import com.example.orchestratorservice.workflow.WorkflowOrchestratorClient;
import com.example.orchestratorservice.workflow.impl.WorkflowOrchestratorImpl;
import lombok.Setter;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
@EnableFeignClients(basePackages = {"com.example.orchestratorservice.feignClient"})
public class OrderServiceAppConfig {

    @Bean
    public CompleteOrderActivity createPendingOrderActivity() {
        return new CompleteOrderActivityImpl();
    }

    @Bean
    public OrderWorker orderWorker() {
        return new OrderWorker(
                createPendingOrderActivity(), workflowOrchestratorClient());
    }

    @Bean
    public WorkflowOrchestratorClient workflowOrchestratorClient() {
        return new WorkflowOrchestratorClient();
    }

    @Bean
    public WorkflowOrchestrator workflowOrchestrator() {
        return new WorkflowOrchestratorImpl(workflowOrchestratorClient());
    }

    @Bean
    public TransferMoneyCompletedActivity transferMoneyCompletedActivity() {
        return new TransferMoneyCompletedActivityImpl();
    }

    @Bean
    public TransferMoneyWorker transferMoneyWorker() {
        return new TransferMoneyWorker(
                transferMoneyCompletedActivity(), workflowOrchestratorClient());
    }

    @Bean
    public SenderServiceActivity senderServiceActivity() {
        return new SenderServiceActivityImpl();
    }

    @Bean
    public SenderServiceWorker senderServiceWorker() {
        return new SenderServiceWorker(
                senderServiceActivity(), workflowOrchestratorClient());
    }

    @Bean
    public ReceiverServiceActivity receiverServiceActivity() {
        return new ReceiverServiceActivityImpl();
    }

    @Bean
    public ReceiverServiceWorker receiverServiceWorker() {
        return new ReceiverServiceWorker(
                receiverServiceActivity(), workflowOrchestratorClient());
    }

}
