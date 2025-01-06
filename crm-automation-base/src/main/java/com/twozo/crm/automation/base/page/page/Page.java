package com.twozo.crm.automation.base.page.page;

import com.twozo.web.element.model.Element;
import com.twozo.web.element.service.WebPageElement;

import java.util.Collection;

public interface Page {
    WebPageElement findByXpath(final String xpath);

    Collection<WebPageElement> findElementsByXpath(final String xpath);

    WebPageElement findByText(final String value);

    WebPageElement findByNumber(final int value);
    void waitTillVisible(final Element element);

    void waitTillInvisible(final Element element);

    void waitTillInvisible(final String xpath);

    void waitTillVisible(final String xpath);

    void waitTillClickable(final String xpath);

    void shortWaitTillVisible(final String xpath);

    void shortWaitTillClickable(final String xpath);
    boolean isSelected(final WebPageElement webPageElement);
}
