package com.twozo.page.homepage;

import com.twozo.page.BasePage;
import com.twozo.page.activity.ActivityForm;
import com.twozo.page.analytics.Analytics;
import com.twozo.page.company.Company;
import com.twozo.page.contact.Contact;
import com.twozo.page.deal.DealPage;
import com.twozo.page.inbox.Inbox;
import com.twozo.page.product.Product;
import com.twozo.page.settings.Settings;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.Objects;

public class HomePage extends BasePage {

    private static HomePage homePage;

    protected final WebAutomationDriver webAutomationDriver;
    private final DealPage deal;
    private final Contact contact;
    private final Company company;
    private final ActivityForm activity;
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

    protected HomePage(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
        this.webAutomationDriver = webAutomationDriver;
        this.deal = DealPage.getInstance(webAutomationDriver);
        this.contact = Contact.getInstance(webAutomationDriver);
        this.company = Company.getInstance(webAutomationDriver);
        this.activity = ActivityForm.getInstance(webAutomationDriver);
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
}
