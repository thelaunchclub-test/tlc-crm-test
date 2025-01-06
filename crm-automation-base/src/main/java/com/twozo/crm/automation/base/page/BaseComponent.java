package com.twozo.crm.automation.base.page;

import com.twozo.web.element.service.WebPageElement;

public class BaseComponent {
    protected WebPageElement webPageElement;

    public BaseComponent(WebPageElement webPageElement) {
        this.webPageElement = webPageElement;
    }
}
