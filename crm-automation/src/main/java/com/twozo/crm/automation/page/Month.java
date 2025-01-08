package com.twozo.crm.automation.page;

import lombok.Getter;

@Getter
public enum Month {
    JANUARY("January",1),
    FEBRUARY("February",2),
    MARCH("March",3),
    APRIL("April",4),
    MAY("May",5),
    JUNE("June",6),
    JULY("July",7),
    AUGUST("August",8),
    SEPTEMBER("September",9),
    OCTOBER("October",10),
    NOVEMBER("November",11),
    DECEMBER("December",12);

    private final String monthName;
    private final int monthNumber;

    Month(final String monthName,final int monthNumber) {
        this.monthName = monthName;
        this.monthNumber = monthNumber;
    }

    public static String getMonthName(final int monthNumber) {
        for (final Month month : values()) {

            if (month.getMonthNumber() == monthNumber) {
                return month.getMonthName();
            }
        }
        throw new IllegalArgumentException("Invalid Month: " + monthNumber);
    }
}
