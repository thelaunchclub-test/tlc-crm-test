package com.twozo.page;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.commons.util.ConfigFileReader;
import com.twozo.page.xpath.XPath;
import com.twozo.page.xpath.XPathBuilder;
import com.twozo.web.driver.service.*;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.ElementFinder;
import com.twozo.web.element.service.ElementInformationProvider;
import com.twozo.web.element.service.ElementInteraction;
import com.twozo.web.element.service.WebPageElement;
import com.twozo.web.mouse.service.actions.MouseActions;

import java.util.*;
import java.util.function.Supplier;

public class BasePage {

    protected static final Map<String, String> MAP = ConfigFileReader.get("locator/locator.Properties");
    protected static final String TWO_STRING_FORMAT = "%s%s";

    public WebAutomationDriver webAutomationDriver;
    public ElementFinder elementFinder;
    public WebNavigator webNavigator;
    public PageInformationProvider pageInformationProvider;
    public SessionCookie sessionCookie;
    public MouseActions mouseActions;
    public ExplicitWaitHandler explicitWaitHandler;
    public ImplicitWaitHandler implicitWaitHandler;
    private static BasePage basePage;

    protected BasePage(final WebAutomationDriver webAutomationDriver) {
        this.webAutomationDriver = webAutomationDriver;
        this.elementFinder = webAutomationDriver.getElementFinder();
        this.webNavigator = webAutomationDriver.getWebNavigator();
        this.pageInformationProvider = webAutomationDriver.getPageInformationProvider();
        this.mouseActions = webAutomationDriver.getMouseActionsHandler();
        this.implicitWaitHandler = webAutomationDriver.getImplicitWaitHandler();
        this.explicitWaitHandler = webAutomationDriver.getExplicitWaitHandler();
        this.sessionCookie = webAutomationDriver.getSessionCookie();
    }

    public static BasePage getInstance(final WebAutomationDriver webAutomationDriver) {
        basePage = new BasePage(webAutomationDriver);

        return basePage;
    }

    public Set<BrowserCookie> getCookies() {
        //System.out.println(sessionCookie.getCookies());
//        for (Cookie cookie : sessionCookie.getCookies()) {
//            System.out.println(cookie);
//        }
        return sessionCookie.getCookies();
    }

    public void addCookies(final Set<BrowserCookie> cookies) {
        //System.out.println(sessionCookie.getCookies());
        for (BrowserCookie cookie : cookies) {
            sessionCookie.addCookie(cookie);
        }
    }

    public WebPageElement getColumnSettingsButton() {
        return findByXpath("//*[@class='css-181x7hd']");
    }

    public void switchToColumnSettings() {
        //waitTillVisible("//*[@class='css-181x7hd']");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        click(getColumnSettingsButton());
    }

    public void close() {
        webAutomationDriver.close();
    }
    
    protected String format(final String div, final String element) {
        return String.format(TWO_STRING_FORMAT, div, element);
    }

    public void waitTillVisible(final Element element) {
        explicitWaitHandler.waitTillVisible(element);
    }

    public void waitTillVisible(final String xpath) {
        explicitWaitHandler.waitTillVisible(new Element(LocatorType.XPATH, xpath, true));
    }

    public void waitTillClickable(final String xpath) {
        explicitWaitHandler.WaitTillClickable(new Element(LocatorType.XPATH, xpath, true));
    }

    public void shortWaitTillVisible(final String xpath) {
        explicitWaitHandler.shortWaitTillVisible(new Element(LocatorType.XPATH, xpath, true));
    }

    public void shortWaitTillClickable(final String xpath) {
        explicitWaitHandler.shortWaitTillClickable(new Element(LocatorType.XPATH, xpath, true));
    }

    protected final WebPageElement findElement(final Element element) {
        return elementFinder.getWebPageElement(element);
    }

    protected final Collection<WebPageElement> findElements(final Element element) {
        return elementFinder.getWebPageElements(element);
    }

    protected final WebPageElement findAboveElement(final Collection<Element> elements) {
        return elementFinder.findAboveElement(elements);
    }

    protected final WebPageElement findBelowElement(final Collection<Element> elements) {
        return elementFinder.findBelowElement(elements);
    }

    protected final WebPageElement findNearElement(final Collection<Element> elements) {
        return elementFinder.findNearElement(elements);
    }

    protected final WebPageElement findLeftElement(final Collection<Element> elements) {
        return elementFinder.findLeftElement(elements);
    }

    protected final WebPageElement findRightElement(final Collection<Element> elements) {
        return elementFinder.findRightElement(elements);
    }

    public WebPageElement findByXpath(final XPath xpath) {
        return findElement(new Element(LocatorType.XPATH, XPathBuilder.getXPath(xpath), true));
    }

    public WebPageElement findByClass(final String className) {
        return findElement(new Element(LocatorType.CLASS_NAME, className, true));
    }

    public WebPageElement findByXpath(final String xpath) {
        return findElement(new Element(LocatorType.XPATH, xpath, true));
    }

    protected Collection<WebPageElement> findElementsByXpath(final String xpath) {
        return findElements(new Element(LocatorType.XPATH, xpath, true));
    }

    protected Collection<WebPageElement> findElementsByClass(final String className) {
        return findElements(new Element(LocatorType.CLASS_NAME, className, true));
    }

    protected WebPageElement findByText(final String value) {
        return findElement(new Element(LocatorType.XPATH, XPathBuilder.getXPathByText(value), true));
    }

    protected final void send(final WebPageElement webPageElement, final String value) {
        getElementInteraction(webPageElement).sendKeys(value);
    }

    protected final void click(final WebPageElement webPageElement) {
        getElementInteraction(webPageElement).click();
    }

    protected final void clear(final WebPageElement webPageElement) {
        getElementInteraction(webPageElement).clear();
    }

    protected <T> T initializeElement(final T element, final Supplier<T> initializer) {
        return Objects.isNull(element) ? initializer.get() : element;
    }

    protected String buildXpathByText(final String text) {
        return XPathBuilder.getXPathByText(text);
    }

    protected final void selectDate(final String fieldXPath, final String month, final int date, final int year) {
        final String xpath = "//button[text()='%d']";

        click(findBelowElement(List.of(
                new Element(LocatorType.XPATH, "//button[@aria-label='Choose date']", false),
                new Element(LocatorType.XPATH, fieldXPath, true))));
        click(findByXpath("//button[@aria-label='calendar view is open, switch to year view']"));
        click(findByText(String.format(xpath, year)));
        final WebPageElement div = findLeftElement(List.of(
                new Element(LocatorType.TAG_NAME, "div", false),
                new Element(LocatorType.XPATH,
                        "//button[@aria-label='calendar view is open, switch to year view']", true)));

        while (!getText(div).equals(String.format("%s %d", month, year))) {
            click(findByXpath("//button[@aria-label='Next month']"));
        }

        click(findByXpath(String.format(xpath, date)));
    }

    public final boolean isDisplayed(final WebPageElement webPageElement) {
        return getElementInformationProvider(webPageElement).isDisplayed();
    }

    protected final boolean isSelected(final WebPageElement webPageElement) {
        return getElementInformationProvider(webPageElement).isSelected();
    }

    protected final boolean isEnabled(final WebPageElement webPageElement) {
        return getElementInformationProvider(webPageElement).isEnabled();
    }

    protected final String getText(final WebPageElement webPageElement) {
        return getElementInformationProvider(webPageElement).getText();
    }

    protected final List<String> getTexts(final Collection<WebPageElement> webPageElement) {
        final List<String> names = new ArrayList<>();

        for (WebPageElement field : webPageElement) {
            names.add(getText(field));
        }
        return names;
    }

    public String getURL() {
        return pageInformationProvider.getCurrentUrl();
    }

    protected String contains(final String name) {
        return XPathBuilder.getXpathByContains(name);
    }

    protected String getAttribute(final WebPageElement webPageElement, String attributeName) {
        return getElementInformationProvider(webPageElement).getAttribute(attributeName);
    }

    protected int getLocationOfX(final WebPageElement webPageElement) {
        return getElementInformationProvider(webPageElement).getXLocation();
    }

    protected int getLocationOfY(final WebPageElement webPageElement) {
        return getElementInformationProvider(webPageElement).getYLocation();
    }

    protected final void dropdown(final String option) {
        select(option, "li");
    }

    protected final void dropdownMenu(final String option) {
        select(option, "div");
    }

    protected final void hover(final Element element) {
        mouseActions.moveToElement(element).build().perform();
    }

    public final void hoverByXpath(final String xpath) {
        mouseActions.moveToElement(new Element(LocatorType.XPATH, xpath, true)).build().perform();
    }

    private void select(final String option, final String dropdownType) {
        for (final WebPageElement element : findElements(new Element(LocatorType.TAG_NAME, dropdownType, true))) {

            if (getText(element).equalsIgnoreCase(option)) {
                click(element);
                break;
            }
        }
    }

    protected Element getElementByXpath(final String xpath) {
        return new Element(LocatorType.XPATH, xpath, true);
    }


    private ElementInformationProvider getElementInformationProvider(final WebPageElement webPageElement) {
        return webPageElement.getElementInformationProvider();
    }


    private ElementInteraction getElementInteraction(final WebPageElement webPageElement) {
        return webPageElement.interact();
    }

    public void refresh() {
        webNavigator.refresh();
    }

//    public WebPageElement getStatus() {
//
//        if (Objects.isNull(status)) {
//            status = findBy("(//div[text()='Status'])[2]");
//            status = TagFinder.get(Record)
//        }
//
//        return status;
//    }

}
