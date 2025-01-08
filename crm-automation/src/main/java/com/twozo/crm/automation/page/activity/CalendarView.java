package com.twozo.crm.automation.page.activity;

import com.twozo.crm.automation.page.BasePage;
import com.twozo.web.driver.service.WebAutomationDriver;

public class CalendarView extends BasePage {

    private CalendarView(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static CalendarView getInstance(final WebAutomationDriver webAutomationDriver) {
        return new CalendarView(webAutomationDriver);
    }
}
