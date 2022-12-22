package com.example.orchestratorservice.model;


import com.example.orchestratorservice.enums.TransferMoneyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction_history")
@Entity
public class TransactionHistory {

    @Id
    private String id;
    private Long userSenderId;
    private Long userReceiverId;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransferMoneyStatus transferMoneyStatus;
}
