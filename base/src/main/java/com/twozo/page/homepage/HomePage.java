package com.twozo.page.homepage;

import com.twozo.page.BasePage;
import com.twozo.page.activity.Activity;
import com.twozo.page.analytics.Analytics;
import com.twozo.page.company.Company;
import com.twozo.page.contact.Contact;
import com.twozo.page.deal.Deal;
import com.twozo.page.inbox.Inbox;
import com.twozo.page.product.Product;
import com.twozo.page.settings.Settings;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.Objects;

public class HomePage extends BasePage {

    private static HomePage homePage;

    protected final WebAutomationDriver webAutomationDriver;
    private final Deal deal;
    private final Contact contact;
    private final Company company;
    private final Activity activity;
    private final Product product;
    private final Settings settings;
    private final Inbox inbox;
    private final Analytics analytics;

    private WebPageElement dealIcon;
    private WebPageElement contactIcon;
    private WebPageElement companyIcon;
    private WebPageElement activityIcon;
    private WebPageElement productIcon;
    private WebPageElement settingsIcon;
    private WebPageElement inboxIcon;
    private WebPageElement analyticsIcon;
    private final boolean isProductEnabled = isProductEnabled();

    protected HomePage(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
        this.webAutomationDriver = webAutomationDriver;
        this.deal = Deal.getInstance(webAutomationDriver);
        this.contact = Contact.getInstance(webAutomationDriver);
        this.company = Company.getInstance(webAutomationDriver);
        this.activity = Activity.getInstance(webAutomationDriver);
        this.product = Product.getInstance(webAutomationDriver);
        this.settings = Settings.getInstance(webAutomationDriver);
        this.inbox = Inbox.getInstance(webAutomationDriver);
        this.analytics = Analytics.getInstance(webAutomationDriver);
    }

    public static HomePage getInstance(final WebAutomationDriver webAutomationDriver) {

        if (Objects.isNull(homePage)) {
            homePage = new HomePage(webAutomationDriver);
        }

        return homePage;
    }

    public WebPageElement getDealIcon() {

        if (Objects.isNull(dealIcon)) {
            dealIcon = findByOrder(1);
        }

        return dealIcon;
    }

    public WebPageElement getContactIcon() {

        if (Objects.isNull(contactIcon)) {
            contactIcon = findByOrder(2);
        }

        return contactIcon;
    }

    public WebPageElement getCompanyIcon() {

        if (Objects.isNull(companyIcon)) {
            companyIcon = findByOrder(3);
        }

        return companyIcon;
    }

    public WebPageElement getActivityIcon() {

        if (Objects.isNull(activityIcon)) {
            activityIcon = findByOrder(4);
        }

        return activityIcon;
    }

    public WebPageElement getProductIcon() {

        if (Objects.isNull(productIcon)) {
            productIcon = findByOrder(5);
        }

        return productIcon;
    }

    public WebPageElement getSettingsIcon() {
        return getIcon(settingsIcon, 6, 5);
    }

    public WebPageElement getInboxIcon() {
        return getIcon(inboxIcon, 7, 6);
    }

    public WebPageElement getAnalyticsIcon() {
        return getIcon(analyticsIcon, 8, 7);
    }

    private WebPageElement getIcon(WebPageElement icon, final int productEnabledOrder, final int productDisabledOrder) {
        if (Objects.isNull(icon)) {
            icon = findByOrder(isProductEnabled() ? productEnabledOrder : productDisabledOrder);
        }

        return icon;
    }

    private boolean isProductEnabled() {
        try {
            return isDisplayed(findByOrder(8));
        } catch (Exception exception) {
            return false;
        }
    }

    private WebPageElement findByOrder(final int position) {
        return findByXpath(String.format("(//li[@class='MuiListItem-root MuiListItem-gutters css-12dzmul'])[%d]", position));
    }


    public Deal switchToDeal() {
        click(getDealIcon());

        return deal;
    }

    public Contact switchToContact() {
        click(getContactIcon());

        return contact;
    }

    public Company switchToCompany() {
        click(getCompanyIcon());

        return company;
    }

    public Activity switchToActivity() {
        click(getActivityIcon());

        return activity;
    }

    public Product switchToProduct() {
        click(getProductIcon());

        return product;
    }

    public Settings switchToSettings() {
        click(getSettingsIcon());

        return settings;
    }

    public Inbox switchToInbox() {
        click(getInboxIcon());

        return inbox;
    }

    public Analytics switchToAnalytics() {
        click(getAnalyticsIcon());

        return analytics;
    }
}
