package com.twozo.page.xpath;

public enum TagName {

    PATH("path"),
    IMG("img"),
    ID("id"),
    NAME("name"),
    CLASS("class"),
    TAG_NAME("tagName"),
    BUTTON("button"),
    DIV("div"),
    TEXT("text"),
    ALL("*"),
    INPUT("input");

    private final String tag;

    TagName(final String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }
}

