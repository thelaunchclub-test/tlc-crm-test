package com.twozo.page.activity;

public enum PriorityStatus {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low"),
    ;

    private final String value;

    PriorityStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static PriorityStatus fromValue(final String value) {
        for (PriorityStatus status : PriorityStatus.values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + value);
    }
}

