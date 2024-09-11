package com.twozo.page.company;

import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

public class AddCompany extends Company {

    private static AddCompany addCompany;

    protected AddCompany(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static AddCompany getInstance(final WebAutomationDriver webAutomationDriver) {

       // if (Objects.isNull(addCompany)) {
            addCompany = new AddCompany(webAutomationDriver);
        //}

        return addCompany;
    }

    public WebPageElement getCompanyName(){
        return findByXpath("//*[@placeholder='Eg: Tephey Restaurant']");
    }

    public WebPageElement getSaveButton(){
        return findByText("Save");
    }

    public void createCompany(final String companyName){
        send(getCompanyName(),companyName);
        click(getSaveButton());
    }
}
