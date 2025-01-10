package com.twozo.test.settings;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.page.homepage.HomePage;
import com.twozo.page.settings.sales.activities.SalesActivities;
import com.twozo.page.url.URL;
import com.twozo.page.url.settings.SettingsURL;
import com.twozo.test.BaseTest;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.driver.service.WebNavigator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HoverExample extends BaseTest {

    private WebAutomationDriver automationDriver;
    WebNavigator webNavigator;
    HomePage homePage;

    @BeforeMethod
    public void beforeMethod() {
        automationDriver = WebAutomationDriver.get();
        webNavigator = automationDriver.getWebNavigator();
        webNavigator.to(link);

        for (final BrowserCookie cookie : cookies) {
            automationDriver.getSessionCookie().addCookie(cookie);
        }

        automationDriver.getWebWindowHandler().maximize();
        automationDriver.getImplicitWaitHandler().implicitWait(Duration.ofSeconds(10));
        webNavigator.to(SettingsURL.CONTACT_DATA_FIELDS);
        automationDriver.getWebWindowHandler().maximize();
        HomePage.getInstance(automationDriver);
        homePage = new HomePage(automationDriver);

    }

    @Test
    public void hover() {
        homePage.hover();
    }
}
