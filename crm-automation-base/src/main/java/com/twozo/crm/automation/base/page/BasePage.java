package com.twozo.crm.automation.base.page;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.commons.util.ConfigFileReader;
import com.twozo.crm.automation.base.page.url.URL;
import com.twozo.crm.automation.base.page.xpath.XPath;
import com.twozo.crm.automation.base.page.xpath.XPathBuilder;
import com.twozo.web.driver.service.*;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.ElementFinder;
import com.twozo.web.element.service.ElementInformationProvider;
import com.twozo.web.element.service.ElementInteraction;
import com.twozo.web.element.service.WebPageElement;
import com.twozo.web.mouse.service.actions.MouseActions;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.DataFaker;

import java.time.Duration;
import java.util.*;
import java.util.function.Supplier;

@Getter
public abstract class BasePage {

    public static final DataFaker DATA_FAKER = DataFaker.getInstance();
    protected static final Map<String, String> CRM_LOCATOR_REGISTRY = ConfigFileReader.get("locator/locator.properties");
    protected static final Map<String, String> CRM_TEXT_REGISTRY = ConfigFileReader.get("CrmText.properties");
    protected static final String TWO_STRING_FORMAT = "%s%s";
    protected static final String THREE_STRING_FORMAT = "%s%s%s";
    protected static final String OPEN_PARENTHESIS = "(";
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

    protected BasePage(final WebAutomationDriver webAutomationDriver) {
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

    public void addCookie(final BrowserCookie cookie) {
        sessionCookie.addCookie(cookie);
    }

    public WebPageElement getColumnSettingsButton() {
        return findByXpath("//*[contains(@class,'1jk9avb')]");
    }

    public void switchToColumnSettings() {
        waitTillVisible("//*[contains(@class,'6dh9wo')]");
        click(getColumnSettingsButton());
    }

//    protected WebAutomationDriver getWebAutomationDriver(){
//        return webAutomationDriver;
//    }

    public void close() {
        webAutomationDriver.close();
    }

    public void navigateTo(final String link) {
        webNavigator.to(link);
    }

    public void maximize() {
        webWindowHandler.maximize();
    }

    public void implicitWait(final Duration duration) {
        implicitWaitHandler.implicitWait(duration);
    }

    public String formatTwoStrings(final String string1, final String string2) {
        return String.format(TWO_STRING_FORMAT, string1, string2);
    }

    protected String formatThreeStrings(final String string1, final String string2, final String string3) {
        return String.format(THREE_STRING_FORMAT, string1, string2, string3);
    }

    public void switchToContact() {
        navigateTo(URL.CONTACTS);
    }

    public void switchToCompany() {
        navigateTo(URL.COMPANIES);
    }

    public void switchToDeal() {
        navigateTo(URL.DEALS);
    }

    public void switchToProduct() {
        navigateTo(URL.PRODUCTS);
    }

    public void switchToActivity() {
        navigateTo(URL.ACTIVITIES);
    }

    public void switchToContactAddForm() {
        if (!Objects.equals(getURL(), URL.CONTACTS)) {
            navigateTo(URL.CONTACTS);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        click(findByText("Contact"));
        waitTillVisible(CRM_LOCATOR_REGISTRY.get("crm.add.form"));
    }

    public void switchToCompanyAddForm() {
        navigateTo(URL.COMPANIES);
        click(findByText("Company"));
        waitTillVisible(CRM_LOCATOR_REGISTRY.get("crm.add.form"));
    }

    public void switchToDealAddForm() {
        navigateTo(URL.DEALS);
        click(findByText("Deals"));
        waitTillVisible(CRM_LOCATOR_REGISTRY.get("crm.add.form"));
    }

    public void switchToProductAddForm() {
        navigateTo(URL.PRODUCTS);
        click(findByText("Product"));
        waitTillVisible(CRM_LOCATOR_REGISTRY.get("crm.add.form"));
    }

    public void waitTillVisible(final Element element) {
        explicitWaitHandler.waitTillVisible(element);
    }

    public void waitTillInvisible(final Element element) {
        explicitWaitHandler.waitTillInvisible(element);
    }

    public void waitTillInvisible(final String xpath) {
        explicitWaitHandler.waitTillVisible(new Element(LocatorType.XPATH, xpath, true));
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

    private WebPageElement findElement(final Element element) {
        return elementFinder.getWebPageElement(element);
    }

    private Collection<WebPageElement> findElements(final Element element) {
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

    public WebPageElement findByClass(final String className) {
        return findElement(new Element(LocatorType.CLASS_NAME, className, true));
    }

    public WebPageElement findByXpath(final String xpath) {
        return findElement(new Element(LocatorType.XPATH, xpath, true));
    }

    protected Collection<WebPageElement> findElementsByXpath(final String xpath) {
        return findElements(new Element(LocatorType.XPATH, xpath, true));
    }

    public WebPageElement getBody(){
        return findByXpath("//body");
    }


    protected WebPageElement findByText(final String value) {
        return findElement(new Element(LocatorType.XPATH, XPathBuilder.getXPathByText(value), true));
    }

    protected WebPageElement findByNumber(final int value) {
        return findElement(new Element(LocatorType.XPATH, XPathBuilder.getXPathByNumber(value), true));
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

    protected final void chooseDate(final String fieldName, final String date) {
        final String[] part = date.split("-");

        click(findByXpath(String.format(CRM_LOCATOR_REGISTRY.get("crm.calendar.icon"), fieldName)));
        click(findByXpath(CRM_LOCATOR_REGISTRY.get("crm.calendar.switch.to.year.view")));
        click(findByNumber(Integer.parseInt(part[3])));

        while (!getText(findByXpath(CRM_LOCATOR_REGISTRY.get("crm.calendar.month.and.year"))).contains(Month.fromInt(Integer.parseInt(part[2])))) {
            click(findByXpath(CRM_LOCATOR_REGISTRY.get("crm.calendar.next.month.button")));
        }

        click(findByNumber(Integer.parseInt(part[1])));
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
        click(findByXpath(formatTwoStrings("//ul[@role]", XPathBuilder.getXPathByText(option))));
    }

    public final void hoverByXpath(final String xpath) {
        mouseActions.moveToElement(new Element(LocatorType.XPATH, xpath, true)).build().perform();
    }

//    private void select(final String option, final String dropdownType) {
//        for (final WebPageElement element : findElements(new Element(LocatorType.TAG_NAME, dropdownType, true))) {
//
//            if (getText(element).equalsIgnoreCase(option)) {
//                click(element);
//                break;
//            }
//        }
//    }

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

    public void enableFieldInColumnSettings(final String fieldName) {
        final String fieldCheckboxXpath = String.format(CRM_LOCATOR_REGISTRY.get("crm.column.settings.field.checkbox"), fieldName);

        if (!isSelected(findByXpath(fieldCheckboxXpath))) {
            click(findByXpath(fieldCheckboxXpath));
        }
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
