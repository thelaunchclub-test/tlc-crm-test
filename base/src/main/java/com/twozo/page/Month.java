package com.twozo.page;

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

    private String getMonthName() {
        return monthName;
    }

    private int getMonthNumber(){
        return monthNumber;
    }

    public static String fromInt(int i) {
        for (Month month : values()) {
            if (month.getMonthNumber() == i) {
                return month.monthName;
            }
        }
        throw new IllegalArgumentException("Invalid Month: " + i);
    }
}
