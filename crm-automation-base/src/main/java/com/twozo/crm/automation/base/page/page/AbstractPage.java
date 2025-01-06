package com.twozo.crm.automation.base.page.page;

import com.twozo.crm.automation.base.page.xpath.XPathBuilder;
import com.twozo.web.driver.service.ExplicitWaitHandler;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.ElementFinder;
import com.twozo.web.element.service.WebPageElement;

import java.util.Collection;

public class AbstractPage implements Page {

    protected static final String TWO_STRING_FORMAT = "%s%s";
    protected static final String THREE_STRING_FORMAT = "%s%s%s";
    protected static final String OPEN_PARENTHESIS = "(";

    private final ExplicitWaitHandler explicitWaitHandler;
    private final ElementFinder elementFinder;


    public AbstractPage(final WebAutomationDriver webAutomationDriver) {
        this.explicitWaitHandler = webAutomationDriver.getExplicitWaitHandler();
        this.elementFinder = webAutomationDriver.getElementFinder();
    }

    protected String formatTwoStrings(final String string1, final String string2) {
        return String.format(TWO_STRING_FORMAT, string1, string2);
    }

    protected String formatThreeStrings(final String string1, final String string2, final String string3) {
        return String.format(THREE_STRING_FORMAT, string1, string2, string3);
    }
    @Override
    public void waitTillVisible(Element element) {
        explicitWaitHandler.waitTillVisible(element);

    }

    @Override
    public void waitTillInvisible(Element element) {
        explicitWaitHandler.waitTillInvisible(element);

    }

    @Override
    public void waitTillInvisible(String xpath) {
        explicitWaitHandler.waitTillVisible(new Element(LocatorType.XPATH, xpath, true));
    }

    @Override
    public void waitTillVisible(String xpath) {
        explicitWaitHandler.waitTillVisible(new Element(LocatorType.XPATH, xpath, true));
    }

    @Override
    public void waitTillClickable(String xpath) {
        explicitWaitHandler.WaitTillClickable(new Element(LocatorType.XPATH, xpath, true));
    }

    @Override
    public void shortWaitTillVisible(String xpath) {
        explicitWaitHandler.shortWaitTillVisible(new Element(LocatorType.XPATH, xpath, true));
    }

    @Override
    public void shortWaitTillClickable(String xpath) {
        explicitWaitHandler.shortWaitTillClickable(new Element(LocatorType.XPATH, xpath, true));
    }

    @Override
    public boolean isSelected(WebPageElement webPageElement) {
        return webPageElement.getElementInformationProvider().isSelected();
    }

    private WebPageElement findElement(final Element element) {
        return elementFinder.getWebPageElement(element);
    }

    private Collection<WebPageElement> findElements(final Element element) {
        return elementFinder.getWebPageElements(element);
    }

    @Override
    public WebPageElement findByXpath(String xpath) {
        return findElement(new Element(LocatorType.XPATH, xpath, true));
    }

    @Override
    public Collection<WebPageElement> findElementsByXpath(String xpath) {
        return findElements(new Element(LocatorType.XPATH, xpath, true));
    }

    @Override
    public WebPageElement findByText(String value) {
        return findElement(new Element(LocatorType.XPATH, XPathBuilder.getXPathByText(value), true));
    }

    @Override
    public WebPageElement findByNumber(int value) {
        return findElement(new Element(LocatorType.XPATH, XPathBuilder.getXPathByNumber(value), true));
    }
}
