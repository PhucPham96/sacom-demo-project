package com.example.orchestratorservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferMoneyRequestDTO {

    private Long userSenderId;
    private Long userReceiverId;
    private Double amount;
}
