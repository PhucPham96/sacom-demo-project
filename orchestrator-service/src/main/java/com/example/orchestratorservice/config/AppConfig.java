package com.example.orchestratorservice.config;//package com.example.orchestratorservice.config;
//
//import com.example.orchestratorservice.activities.CompleteOrderActivity;
//import com.example.orchestratorservice.activities.TransferMoneyCompletedActivity;
//import com.example.orchestratorservice.activities.impl.CompleteOrderActivityImpl;
//import com.example.orchestratorservice.activities.impl.TransferMoneyCompletedActivityImpl;
//import com.example.orchestratorservice.worker.OrderWorker;
//import com.example.orchestratorservice.worker.TransferMoneyWorker;
//import com.example.orchestratorservice.workflow.WorkflowOrchestrator;
//import com.example.orchestratorservice.workflow.WorkflowOrchestratorClient;
//import com.example.orchestratorservice.workflow.impl.WorkflowOrchestratorImpl;
//import lombok.Setter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@Setter
//public class AppConfig {
//
//    @Bean
//    public TransferMoneyCompletedActivity transferMoneyCompletedActivity() {
//        return new TransferMoneyCompletedActivityImpl();
//    }
//
//    @Bean
//    public TransferMoneyWorker transferMoneyWorker() {
//        return new TransferMoneyWorker(
//                transferMoneyCompletedActivity(), workflowOrchestratorClient());
//    }
//
//    @Bean
//    public WorkflowOrchestratorClient workflowOrchestratorClient() {
//        return new WorkflowOrchestratorClient();
//    }
//
//    @Bean
//    public WorkflowOrchestrator workflowOrchestrator() {
//        return new WorkflowOrchestratorImpl(workflowOrchestratorClient());
//    }
//
//}
