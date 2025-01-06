package com.twozo.crm.automation.base.page.handler;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.commons.util.ConfigFileReader;
import com.twozo.crm.automation.base.page.BasePage;
import com.twozo.crm.automation.base.page.Month;
import com.twozo.crm.automation.base.page.url.URL;
import com.twozo.crm.automation.base.page.xpath.XPathBuilder;
import com.twozo.web.driver.service.*;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.ElementFinder;
import com.twozo.web.element.service.ElementInformationProvider;
import com.twozo.web.element.service.ElementInteraction;
import com.twozo.web.element.service.WebPageElement;
import com.twozo.web.mouse.service.actions.MouseActions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.DataFaker;

import java.time.Duration;
import java.util.*;

public class AbstractPageHandler implements PageHandler {

    public static final DataFaker DATA_FAKER = DataFaker.getInstance();

    protected static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    protected MouseActions mouseActions;
    private final WebAutomationDriver webAutomationDriver;
    private final ElementFinder elementFinder;
    private final WebNavigator webNavigator;
    private final PageInformationProvider pageInformationProvider;
    private final SessionCookie sessionCookie;
    private final ExplicitWaitHandler explicitWaitHandler;
    private final ImplicitWaitHandler implicitWaitHandler;
    private final WebWindow webWindowHandler;

    protected AbstractPageHandler(final WebAutomationDriver webAutomationDriver) {
        this.webAutomationDriver = webAutomationDriver;
        this.elementFinder = webAutomationDriver.getElementFinder();
        this.webNavigator = webAutomationDriver.getWebNavigator();
        this.pageInformationProvider = webAutomationDriver.getPageInformationProvider();
        this.mouseActions = webAutomationDriver.getMouseActionsHandler();
        this.implicitWaitHandler = webAutomationDriver.getImplicitWaitHandler();
        this.explicitWaitHandler = webAutomationDriver.getExplicitWaitHandler();
        this.webWindowHandler = webAutomationDriver.getWebWindowHandler();
        this.sessionCookie = webAutomationDriver.getSessionCookie();
    }

    @Override
    public void addCookie(final BrowserCookie cookie) {
        sessionCookie.addCookie(cookie);
    }

    @Override
    public void close() {
        webAutomationDriver.close();
    }

    @Override
    public void navigateTo(String link) {
        webNavigator.to(link);
    }

    @Override
    public void maximize() {
        webWindowHandler.maximize();
    }

    private ElementInformationProvider getElementInformationProvider(final WebPageElement webPageElement) {
        return webPageElement.getElementInformationProvider();
    }

    @Override
    public boolean isDisplayed(WebPageElement webPageElement) {
        return getElementInformationProvider(webPageElement).isDisplayed();
    }

    @Override
    public boolean isSelected(WebPageElement webPageElement) {
        return getElementInformationProvider(webPageElement).isSelected();
    }

    @Override
    public boolean isEnabled(WebPageElement webPageElement) {
        return getElementInformationProvider(webPageElement).isEnabled();
    }

    @Override
    public String getText(WebPageElement webPageElement) {
        return getElementInformationProvider(webPageElement).getText();
    }

    private ElementInteraction getElementInteraction(final WebPageElement webPageElement) {
        return webPageElement.interact();
    }

    @Override
    public void click(WebPageElement webPageElement) {
        getElementInteraction(webPageElement).click();
    }

    @Override
    public List<String> getTexts(Collection<WebPageElement> webPageElements) {
        final List<String> names = new ArrayList<>();

        for (final WebPageElement field : webPageElements) {
            names.add(getText(field));
        }
        return names;
    }

    @Override
    public String getURL() {
        return pageInformationProvider.getCurrentUrl();
    }

    @Override
    public String contains(String name) {
        return pageInformationProvider.getCurrentUrl();
    }

    @Override
    public String getAttribute(WebPageElement webPageElement, String attributeName) {
        return getElementInformationProvider(webPageElement).getAttribute(attributeName);
    }

    @Override
    public void dropdown(String option) {
        click(findByXpath(formatTwoStrings("//ul[@role]", XPathBuilder.getXPathByText(option))));
    }

    @Override
    public void hoverByXpath(String xpath) {
        mouseActions.moveToElement(new Element(LocatorType.XPATH, xpath, true)).build().perform();
    }

    @Override
    public void refresh() {
        webNavigator.refresh();
    }
}
