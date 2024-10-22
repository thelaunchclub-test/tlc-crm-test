package com.twozo.page.settings.data.fields.field;

import com.twozo.page.xpath.AttributeName;
import com.twozo.page.xpath.TagName;
import com.twozo.page.xpath.XPath;
import com.twozo.page.xpath.XPathBuilder;

public class FieldElement {

    public static final String NON_DRAGGABLE = XPathBuilder.getXPath(new XPath(TagName.ALL, AttributeName.OPACITY, "0.2", 0));
    public static final String DRAGGABLE = XPathBuilder.getXPath(new XPath(TagName.ALL, AttributeName.OPACITY, "1", 0));
    public static final String DRAG_ICON = "//*[@class='MuiBox-root css-44bmtb']";
    public static final String ADD_VIEW_CHECKBOX = "//*[@type='checkbox'])[1]";
    public static final String REQUIRED_CHECKBOX = "//*[@type='checkbox'])[2]";
    public static final String CANCEL_BUTTON = XPathBuilder.getXPathByText("Cancel");
    public static final String ADD_BUTTON = XPathBuilder.getXPathByText("Add");
    public static final String UPDATE_BUTTON = XPathBuilder.getXPathByText("Update");
    public static final String FIELD_BLOCK = "//*[@class='css-11x1d9z']//child::div[@class='MuiBox-root css-0']" +
            "//*[text()='%s']/ancestor::div[@class='MuiBox-root css-19idom']";

    public static final String DEPENDABLE_BLOCK = "//*[@class='css-11x1d9z']//child::div[@class='MuiBox-root css-0']" +
            "//*[text()='%s']/ancestor::div[@class='css-11x1d9z']";
    public static final String BLOCK = "(//*[@class='MuiBox-root css-19idom'])[%d]";

    public static final String MENU_BLOCK = "//*[@class='css-u4p24i']//*[text()='%s']/ancestor::div[@class='css-1531yod']";
    public static final String CUSTOM_FIELD_NAME = "(//*[@type='text'])[2]";
    public static final String SYSTEM_FIELD_SEARCH_RESULT = "//*[@class='MuiBox-root css-2pd7af']/div";
    public static final String CHECKBOX = "//*[@type='checkbox'])";
    public static final String SPECIFIC_FIELD_COLUMN_SETTINGS_CHECKBOX = "//*[@class='css-eawmf1']//*[text()='%s']/ancestor::div[@class='css-vb6e92']//child::input[@type='checkbox']";
    public static final String EDIT_ICON = "//button[1]";
    public static final String DELETE_ICON = "//button[2]";
    public static final String EYE_ICON = "//button[1]";
}
