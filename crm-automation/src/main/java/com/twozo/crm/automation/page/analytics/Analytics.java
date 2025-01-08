package com.twozo.crm.automation.page.analytics;

import com.twozo.crm.automation.page.BasePage;
import com.twozo.web.driver.service.WebAutomationDriver;

public class Analytics extends BasePage {

    private static Analytics analytics;

    protected Analytics(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static Analytics getInstance(final WebAutomationDriver webAutomationDriver) {

        //if (Objects.isNull(analytics)) {
            analytics = new Analytics(webAutomationDriver);
       // }

        return analytics;
    }
}
