package com.example.orchestratorservice.feignClient;

import com.example.orchestratorservice.dto.TransferMoneyRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-sender", url = "localhost:9091/sender", configuration = FeignClientsConfiguration.class)
public interface SenderUserClient {

    @PostMapping("/deduct")
    ResponseEntity<String> deductMoney(@RequestBody TransferMoneyRequestDTO transferMoneyRequestDTO);

    @PostMapping("/refund")
    ResponseEntity<String> refund(@RequestBody TransferMoneyRequestDTO transferMoneyRequestDTO);
}
