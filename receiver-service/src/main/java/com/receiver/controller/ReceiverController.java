package com.receiver.controller;


import com.receiver.sender.OrchestratorRequest;
import com.receiver.service.ReceiverMoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReceiverController {

    final ReceiverMoneyService receiverMoneyService;

    @PostMapping(value = "/confirm")
    public ResponseEntity<Object> registerConfirm(@RequestBody OrchestratorRequest request) {
        return ResponseEntity.ok(receiverMoneyService.receiverMoney(request));
    }

}
