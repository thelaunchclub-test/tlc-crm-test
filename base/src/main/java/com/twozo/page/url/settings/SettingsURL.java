package com.twozo.page.url.settings;

import com.twozo.page.url.URL;

public class SettingsURL extends URL {

    public static final String CONTACT_DATA_FIELDS = buildSettingsURL("/datafields?type=Contact");
    public static final String COMPANY_DATA_FIELDS = buildSettingsURL("/datafields?type=Company");
    public static final String DEAL_DATA_FIELDS= buildSettingsURL("/datafields?type=Deal");
    public static final String PRODUCT_DATA_FIELDS = buildSettingsURL("/datafields?type=Product");

    public static String buildSettingsURL(final String path) {
        return String.format(TWO_STRING_FORMAT, SETTINGS, path);
    }
}
