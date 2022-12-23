package com.example.receivedservice.dto;

import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private String transactionId;
    private String accountId;
    private String transferId;
    private BigInteger creditAmount;
}
