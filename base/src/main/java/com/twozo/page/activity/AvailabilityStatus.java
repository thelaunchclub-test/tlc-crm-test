package com.twozo.page.activity;

import lombok.Getter;

@Getter
public enum AvailabilityStatus {
    FREE("Free"),
    BUSY("Busy");

    private final String value;

    AvailabilityStatus(final String value) {
        this.value = value;
    }

    public static AvailabilityStatus fromValue(final String value) {
        for (AvailabilityStatus status : AvailabilityStatus.values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + value);
    }
}

