package com.example.tob.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionType implements CodeEnum {
    TRANSFER(0, "Chuyển khoản"),
    CASH(1, "Tiền mặt"),
    TRANSFER_CASH(2, "Chuyển khoản và tiền mặt");

    @JsonValue
    private final int value;
    private final String displayName;

    @JsonCreator
    public static TransactionType of(int value) {
        for (TransactionType type : TransactionType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }

}
