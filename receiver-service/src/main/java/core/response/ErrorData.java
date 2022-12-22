package core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ErrorData {
    public static final String ERROR_TYPE_STB = "stb";
    public static final String ERROR_TYPE_ABC  = "abcs";
    public static final String ERROR_TYPE_ABC_TRU  = "abcs-true";
    public static final String ERROR_TYPE_DSP  = "dsp";
    public static final String ERROR_TYPE_EKY_BRA  = "ekyc-branch";
    public static final String ERROR_TYPE_EKY_COU  = "ekyc-counter";
    public static final String ERROR_TYPE_MBA  = "mbase";
    public static final String ERROR_KEY1_SERV_REG  = "service-receiver";

//    public static final String ERROR_KEY1_SERV_REG = "service-register";
//    public static final String ERROR_KEY1_SERV_COU_SER = "service-counter-service";
    public static final String ERROR_KEY1_SERV_DEP_TRA = "service-deposit-transfer";

    private String code;
    private String msg;

    @JsonProperty("invalid_fields")
    private Object invalidFields;

    private Object error;
    private String cause;
    private String timestamp;

    @JsonProperty("context_path")
    private String contextPath;

    private String service;

    @JsonProperty("trace_id")
    private String traceId;

    @JsonProperty("error_type")
    private String errorType;

    @JsonProperty("error_key1")
    private String errorKey1;

    @JsonProperty("error_key2")
    private String errorKey2;

    @JsonProperty("error_key3")
    private String errorKey3;

    @JsonProperty("error_key4")
    private String errorKey4;

}
