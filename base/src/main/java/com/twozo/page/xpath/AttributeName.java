package com.twozo.page.xpath;

public enum AttributeName {

    ARIA_INVALID("aria-invalid"),
    CLASS("class"),
    OPACITY("opacity"),
    STYLE("style"),
    ALT("alt");


    private final String attribute;

    AttributeName(final String tag) {
        this.attribute = tag;
    }

    public String getAttribute() {
        return this.attribute;
    }

}
