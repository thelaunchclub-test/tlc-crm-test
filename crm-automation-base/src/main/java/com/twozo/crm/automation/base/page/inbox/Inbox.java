package com.twozo.crm.automation.base.page.inbox;

import com.twozo.crm.automation.base.page.BasePage;
import com.twozo.web.driver.service.WebAutomationDriver;

public class Inbox extends BasePage {

    private static Inbox inbox;

    protected Inbox(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static Inbox getInstance(final WebAutomationDriver webAutomationDriver) {

       // if (Objects.isNull(inbox)) {
            inbox = new Inbox(webAutomationDriver);
        //}

        return inbox;
    }
}
