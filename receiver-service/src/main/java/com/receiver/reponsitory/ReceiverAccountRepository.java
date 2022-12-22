package com.receiver.reponsitory;

import com.receiver.entity.ReceiverAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiverAccountRepository extends JpaRepository<ReceiverAccountModel, String> {
    String TABLE = "receiver_account";

    ReceiverAccountModel  findByUserReceiverId(String userReceiverId);
}
