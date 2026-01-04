package com.example.tob.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContributionStatus implements CodeEnum {
    CLOSED(0, "Closed"),
    OPEN(1, "Open"),
    PENDING_APROVAL(2, "Pending approval"),
    UNDER_LIQUIDATION(3, "Under liquidation");

    @JsonValue
    private final int value;
    private final String displayName;

    @JsonCreator
    public static ContributionStatus of(int value) {
        for (ContributionStatus status : ContributionStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }

}
