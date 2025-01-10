package com.twozo.page.settings.sales.activities;

public enum ActivityIcon {

    SEARCH("//button[contains(@class, 'MuiBox-root')][1]"),
    CALL("//button[contains(@class, 'MuiBox-root')][2]"),
    ADD("//button[contains(@class, 'MuiBox-root')][3]"),
    COMPANY("//button[contains(@class, 'MuiBox-root')][4]"),
    CONTACT("//button[contains(@class, 'MuiBox-root')][5]"),
    COPY("//button[contains(@class, 'MuiBox-root')][6]"),
    CUSTOM("//button[contains(@class, 'MuiBox-root')][7]"),
    CALENDAR("//button[contains(@class, 'MuiBox-root')][8]"),
    DEAL("//button[contains(@class, 'MuiBox-root')][9]"),
    EDIT("//button[contains(@class, 'MuiBox-root')][10]"),
    EXPORT("//button[contains(@class, 'MuiBox-root')][11]"),
    FILE("//button[contains(@class, 'MuiBox-root')][12]"),
    INFO("//button[contains(@class, 'MuiBox-root')][13]"),
    KANBAN("//button[contains(@class, 'MuiBox-root')][14]"),
    LUNCH("//button[contains(@class, 'MuiBox-root')][15]"),
    MAIL("//button[contains(@class, 'MuiBox-root')][16]"),
    MEETING("//button[contains(@class, 'MuiBox-root')][17]"),
    NOTE("//button[contains(@class, 'MuiBox-root')][18]"),
    TIME("//button[contains(@class, 'MuiBox-root')][19]"),
    FAVORITE_OFF("//button[contains(@class, 'MuiBox-root')][20]"),
    NOTIFICATION("//button[contains(@class, 'MuiBox-root')][21]"),
    PRODUCT("//button[contains(@class, 'MuiBox-root')][22]"),
    QUOTE("//button[contains(@class, 'MuiBox-root')][23]"),
    SEND("//button[contains(@class, 'MuiBox-root')][24]"),
    SETTINGS("//button[contains(@class, 'MuiBox-root')][25]"),
    TASK("//button[contains(@class, 'MuiBox-root')][26]"),
    TICK("//button[contains(@class, 'MuiBox-root')][27]"),
    ;


    private final String iconXPath;

    ActivityIcon(final String iconXPath) {
        this.iconXPath = iconXPath;
    }

    public String getIconXPath() {
        return iconXPath;
    }

    public static ActivityIcon fromName(final String name) {
        return ActivityIcon.valueOf(name.toUpperCase().replace(" ", "_"));
    }
}
