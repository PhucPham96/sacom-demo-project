package com.example.receivedservice.controller;


import com.example.receivedservice.dao.SenderDao;
import com.example.receivedservice.dto.TransactionDto;
import com.example.receivedservice.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class ApiController {
    final ApiService apiService;

    final SenderDao senderDao;

    @GetMapping(value = "/getAllUser")
    public <T> T getAllUser() {
        return (T) apiService.getAllUser();
    }

    @GetMapping(value = "/getAllTransaction")
    public <T> T getAllTransaction() {
        return (T) apiService.getAllTransaction();
    }

    @PostMapping(value = "/credit")
    public void credit(@RequestBody TransactionDto dto) throws Exception {
        apiService.credit(dto);
    }

    @PostMapping(value = "/createNativeQuery")
    public void createNativeQuery(@RequestBody TransactionDto dto) {
        senderDao.createNativeQuery(dto.getAccountId());
    }

}
