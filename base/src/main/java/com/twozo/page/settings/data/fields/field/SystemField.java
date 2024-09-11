package com.twozo.page.settings.data.fields.field;

import com.twozo.web.element.service.WebPageElement;

public record SystemField(WebPageElement dragAndDropIcon, WebPageElement fieldName, WebPageElement fieldType,
                          boolean addViewCheckbox, boolean requiredCheckbox, WebPageElement hideIcon) {
        }