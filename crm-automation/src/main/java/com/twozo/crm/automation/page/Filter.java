package com.twozo.crm.automation.page;

import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

public class Filter extends BasePage {

    private Filter(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static Filter getInstance(final WebAutomationDriver webAutomationDriver) {
        return new Filter(webAutomationDriver);
    }

    public WebPageElement getSelectModuleField() {
        return findByXpath("//input[@placeholder='Select Column...']");
    }

    public WebPageElement getSelectField() {
        return findByXpath("//p[text()='Select Field']");
    }

    public void filter() {
        click(getSelectModuleField());
        dropdown("contact");
        click(getSelectField());
        dropdown("Phones");
    }
}
