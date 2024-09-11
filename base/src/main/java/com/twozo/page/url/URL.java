package com.twozo.page.url;

import com.twozo.commons.util.ConfigFileReader;

import java.util.Map;

public class URL {

    private static final Map<String, String> CONFIG = ConfigFileReader.get("Config.Properties");

    public static final String TWO_STRING_FORMAT = "%s%s";

    public static final String BASE_URL = CONFIG.get("Domain");
    public static final String LOG_IN = buildURL("/login");
    public static final String SIGN_UP = buildURL("/signup");
    public static final String DEALS = buildURL("/deals");
    public static final String CONTACTS = buildURL("/contacts");
    public static final String COMPANIES = buildURL("/companies");
    public static final String ACTIVITIES = buildURL("/activities");
    public static final String PRODUCTS = buildURL("/products");
    public static final String SETTINGS = buildURL("/settings");

    public static String buildURL(final String path) {
        return String.format(TWO_STRING_FORMAT, BASE_URL, path);
    }
}
