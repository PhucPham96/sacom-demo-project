package com.receiver.entity;

import com.receiver.reponsitory.ReceiverAccountRepository;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = ReceiverAccountRepository.TABLE)
public class ReceiverAccountModel {

    @Id
    @Column(nullable = false)
    private String userReceiverId;

    @Column(columnDefinition = "Double default 0.0")
    private Double moneyTotal;
    private LocalDateTime createDate;
}
