package core.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {
    INVALID_REQUEST_PARAMETER("STB-0003", "Invalid request parameter: "),
    MONEY_NOT_EMPTY("STB-0019", "money not empty");

    private final String code;
    private final String message;

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";

    public static ResponseStatus findResponseStatus(String code) {
        for (ResponseStatus v : values()) {
            if (v.getCode().equals(code)) {
                return v;
            }
        }

        return null;
    }
}
