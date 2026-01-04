package com.example.tob.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageType implements CodeEnum {
    LIST(0, "Dạng danh sách"),
    SINGLE(1, "Dạng đơn");

    @JsonValue
    private final int value;
    private final String displayName;

    @JsonCreator
    public static ImageType of(int value) {
        for (ImageType type : ImageType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }

}
