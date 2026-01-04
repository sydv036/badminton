package com.example.tob.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GrandRole implements CodeEnum {
    CAPTION(0, "Trưởng nhóm"),
    DEPUTY(1, "Phó nhóm"),
    MEMBER(2, "Thành viên"),
    BLOCK(3, "Chặn");

    @JsonValue
    private final int value;
    private final String displayName;

    @JsonCreator
    public static GrandRole of(int value) {
        for (GrandRole role : GrandRole.values()) {
            if (role.value == value) {
                return role;
            }
        }
        return null;
    }

}
