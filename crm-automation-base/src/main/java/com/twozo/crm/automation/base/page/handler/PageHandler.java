package com.twozo.crm.automation.base.page.handler;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.service.WebPageElement;

import java.time.Duration;
import java.util.Collection;
import java.util.List;

public interface PageHandler {

    void addCookie(final BrowserCookie cookie);

    void close();

    void navigateTo(final String link);

    void maximize();

    boolean isDisplayed(final WebPageElement webPageElement);

    boolean isSelected(final WebPageElement webPageElement);

    boolean isEnabled(final WebPageElement webPageElement);

    String getText(final WebPageElement webPageElement);

    void click(final WebPageElement webPageElement);

    List<String> getTexts(final Collection<WebPageElement> webPageElements);

    String getURL();

    String contains(final String name);

    String getAttribute(final WebPageElement webPageElement, final String attributeName);

    void dropdown(final String option);

    void hoverByXpath(final String xpath);

    void refresh();

}
