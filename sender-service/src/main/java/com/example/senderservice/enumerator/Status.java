package com.example.senderservice.enumerator;

import java.util.Arrays;

public enum Status {
    SUCCESS("SU", ""),
    REFUND("RE", "");

    private final String code;
    private final String detail;

    Status(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    public static Status getEnum(String code) {
        return Arrays.stream(values())
                .filter(s -> s.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No matching Status for [" + code + "]"));
    }

    public String getCode() {
        return code;
    }

    public String getDetail() {
        return detail;
    }
}
