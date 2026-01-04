package com.example.tob.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DrinkServiceType implements CodeEnum {
    BIN(0, "Lốc"),
    BOTTLE(1, "Chai"),
    CARTON(2, "Thùng");

    @JsonValue
    private final int value;
    private final String displayName;

    @JsonCreator
    public static DrinkServiceType of(int value) {
        for (DrinkServiceType serviceType : DrinkServiceType.values()) {
            if (serviceType.value == value) {
                return serviceType;
            }
        }
        return null;
    }

}
