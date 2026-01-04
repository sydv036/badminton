package com.example.tob.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GenderEnum implements CodeEnum {
    MALE(0, "Nam"),
    FEMALE(1, "Nữ"),
    OTHER(2, "Giới tính thứ 3");

    @JsonValue
    private final int value;
    private final String displayName;

    @JsonCreator
    public static GenderEnum of(int value) {
        for (GenderEnum data : GenderEnum.values()) {
            if (data.value == value) {
                return data;
            }
        }
        return null;
    }

}
