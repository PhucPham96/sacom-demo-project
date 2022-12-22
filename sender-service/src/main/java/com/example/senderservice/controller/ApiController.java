package com.example.senderservice.controller;


import com.example.senderservice.dao.SenderDao;
import com.example.senderservice.dto.TransactionDto;
import com.example.senderservice.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @Autowired
    private SenderDao senderDao;

    @GetMapping(value = "/getAllUser")
    public <T> T getAllUser() {
        return (T) apiService.getAllUser();
    }

    @GetMapping(value = "/getAllTransaction")
    public <T> T getAllTransaction() {
        return (T) apiService.getAllTransaction();
    }

    @PostMapping(value = "/deduct")
    public void deduct(@RequestBody TransactionDto dto) throws Exception {
        apiService.deduct(dto);
    }

    @PostMapping(value = "/refund")
    public void refund(@RequestBody TransactionDto dto) throws Exception {
        apiService.refund(dto);
    }

    @PostMapping(value = "/createNativeQuery")
    public void createNativeQuery(@RequestBody TransactionDto dto) {
        senderDao.createNativeQuery(dto.getAccountId());
    }

}
