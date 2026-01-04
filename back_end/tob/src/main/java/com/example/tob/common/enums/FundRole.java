package com.example.tob.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FundRole implements CodeEnum {
    CAPTION(0, "Trưởng quỹ"),
    DEPUTY(1, "Phó quỹ"),
    MEMBER(2, "Thành viên"),
    BLOCK(3, "Chặn");

    @JsonValue
    private final int value;
    private final String displayName;

    @JsonCreator
    public static FundRole of(int value) {
        for (FundRole role : FundRole.values()) {
            if (role.value == value) {
                return role;
            }
        }
        return null;
    }
    
}
