package com.twozo.page.settings.sales.activities;

public enum ActivityIcon {

    SEARCH("//button[@class='MuiBox-root css-0'][1]"),
    CALL("//button[@class='MuiBox-root css-0'][2]"),
    ADD("//button[@class='MuiBox-root css-0'][3]"),
    COMPANY("//button[@class='MuiBox-root css-0'][4]"),
    CONTACT("//button[@class='MuiBox-root css-0'][5]"),
    COPY("//button[@class='MuiBox-root css-0'][6]"),
    CUSTOM("//button[@class='MuiBox-root css-0'][7]"),
    CALENDAR("//button[@class='MuiBox-root css-0'][8]"),
    DEAL("//button[@class='MuiBox-root css-0'][9]"),
    EDIT("//button[@class='MuiBox-root css-0'][10]"),
    EXPORT("//button[@class='MuiBox-root css-0'][11]"),
    FILE("//button[@class='MuiBox-root css-0'][12]"),
    INFO("//button[@class='MuiBox-root css-0'][13]"),
    KANBAN("//button[@class='MuiBox-root css-0'][14]"),
    LUNCH("//button[@class='MuiBox-root css-0'][15]"),
    MAIL("//button[@class='MuiBox-root css-0'][16]"),
    MEETING("//button[@class='MuiBox-root css-0'][17]"),
    NOTE("//button[@class='MuiBox-root css-0'][18]"),
    TIME("//button[@class='MuiBox-root css-0'][19]"),
    FAVORITE_OFF("//button[@class='MuiBox-root css-0'][20]"),
    NOTIFICATION("//button[@class='MuiBox-root css-0'][21]"),
    PRODUCT("//button[@class='MuiBox-root css-0'][22]"),
    QUOTE("//button[@class='MuiBox-root css-0'][23]"),
    SEND("//button[@class='MuiBox-root css-0'][24]"),
    SETTINGS("//button[@class='MuiBox-root css-0'][25]"),
    TASK("//button[@class='MuiBox-root css-0'][26]"),
    TICK("//button[@class='MuiBox-root css-0'][27]"),
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
