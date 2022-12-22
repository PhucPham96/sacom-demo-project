package com.receiver.sender;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrchestratorRequest {

    @JsonProperty("userReceiverId")
    private String userReceiverId;

    @JsonProperty("amount")
    private String amount;
}
