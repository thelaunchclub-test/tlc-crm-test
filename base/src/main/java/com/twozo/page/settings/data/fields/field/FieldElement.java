package com.twozo.page.settings.data.fields.field;

import com.twozo.page.xpath.AttributeName;
import com.twozo.page.xpath.TagName;
import com.twozo.page.xpath.XPath;
import com.twozo.page.xpath.XPathBuilder;

public class FieldElement {

    public static final String NON_DRAGGABLE = "//*[@opacity='0.2']";
    public static final String DRAGGABLE = "//*[@opacity='1']";
    public static final String ADD_VIEW_CHECKBOX = "//*[@type='checkbox'])[1]";
    public static final String REQUIRED_CHECKBOX = "//*[@type='checkbox'])[2]";
    public static final String CANCEL_BUTTON = XPathBuilder.getXPathByText("Cancel");
    public static final String ADD_BUTTON = XPathBuilder.getXPathByText("Add");
    public static final String UPDATE_BUTTON = XPathBuilder.getXPathByText("Update");
    public static final String FIELD_BLOCK = "//*[text()='%s']//ancestor::div[@data-rbd-draggable-id]";
    public static final String DEPENDABLE_BLOCK = "//*[text()='%s']//ancestor::div[4]";
    public static final String BLOCK = "(//*[@data-rbd-draggable-id])[%d]";
    public static final String MENU_BLOCK = "//*[@class='css-u4p24i']//*[text()='%s']/ancestor::div[@class='css-1531yod']";
    public static final String CUSTOM_FIELD_NAME = "(//*[@type='text'])[2]";
    public static final String SYSTEM_FIELD_SEARCH_RESULT = "//*[@role='menu']/child::div[2]/div";
    public static final String CHECKBOX = "//*[@type='checkbox'])";
    public static final String SPECIFIC_FIELD_COLUMN_SETTINGS_CHECKBOX = "//*[@role='menu']//*[text()='%s']/ancestor::div[2]//input";
    public static final String EDIT_ICON = "//button[1]";
    public static final String DELETE_ICON = "//button[2]";
    public static final String EYE_ICON = "//button[1]";
    public static final String ADD_CURRENCY_BLOCK = "//*[contains(@class, 'j7qwjs')]/div/div[2]/p";
    public static final String CURRENCY_BLOCK_SWITCH = "(//*[contains(@class, 'j7qwjs')])[%d]/div/div/span";
    public static final String CURRENCY_BLOCK ="(//*[contains(@class, 'j7qwjs')])[%d]";
}
