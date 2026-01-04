package com.example.tob.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus implements CodeEnum {
    PAYMENTED(0, "Đã thanh toán"),
    UNPAID(1, "Chưa thanh toán");

    @JsonValue
    private final int value;
    private final String displayName;

    @JsonCreator
    public static PaymentStatus of(int value) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }

}
