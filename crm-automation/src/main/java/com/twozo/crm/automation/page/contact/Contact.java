package com.twozo.crm.automation.page.contact;

import com.twozo.crm.automation.page.BasePage;
import com.twozo.crm.automation.page.Filter;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

public class Contact extends BasePage {

    private Contact(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static Contact getInstance(final WebAutomationDriver webAutomationDriver) {
        return new Contact(webAutomationDriver);
    }

    private ContactAddForm getAddContact() {
        return ContactAddForm.getInstance(webAutomationDriver);
    }

    public WebPageElement getFilterIcon() {
        return findByXpath("//div[@class='css-j7qwjs'][@style='cursor: pointer;']");
    }

    public WebPageElement getOtherUnsubscribeReason() {
        return findByText("Other unsubscribe reason");
    }

    public WebPageElement getTags() {
        return findByText("Tags");
    }

    public WebPageElement getAddContactButton() {
        return findByText("Contact");
    }

    public ContactAddForm addContact() {
        click(getAddContactButton());

        return getAddContact();
    }

    public Filter switchToFilter() {
        click(getFilterIcon());

        return Filter.getInstance(webAutomationDriver);
    }

}
