package com.twozo.page.contact;

import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

public class AddContact extends Contact {

    private static AddContact addContact;

    protected AddContact(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static AddContact getInstance(final WebAutomationDriver webAutomationDriver) {

        // if (Objects.isNull(addContact)) {
        addContact = new AddContact(webAutomationDriver);
        //}

        return addContact;
    }

    public WebPageElement getFirstName() {
        return findByXpath("//*[@placeholder='Eg: John']");
    }

    public WebPageElement getEmailId(){
        return findByXpath("//*[@placeholder='example@abc.com']");
    }

    public WebPageElement getPhone(){
        return findByXpath("//*[@placeholder='9876543210']");
    }

    public WebPageElement getSaveButton(){
        return findByText("Save");
    }

    public void createContact(final String firstName, final String emailId, final String phone) {
        send(getFirstName(),firstName);
        send(getEmailId(),emailId);
        send(getPhone(),phone);
        click(getSaveButton());
    }

}
