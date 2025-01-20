package com.twozo.page.activity;

public enum ReminderStatus {
    NO_REMINDER("No Reminder"),
    FIVE_MINUTES("5 Minutes"),
    TEN_MINUTES("10 Minutes"),
    FIFTEEN_MINUTES("15 Minutes"),
    THIRTY_MINUTES("30 Minutes"),
    ;

    private final String value;

    ReminderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ReminderStatus fromValue(final String value) {
        for (ReminderStatus status : ReminderStatus.values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + value);
    }
}
