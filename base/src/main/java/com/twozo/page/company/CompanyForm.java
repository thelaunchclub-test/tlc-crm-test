package com.twozo.page.company;

import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

public class CompanyForm extends Company {

    private static CompanyForm addCompany;

    protected CompanyForm(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static CompanyForm getInstance(final WebAutomationDriver webAutomationDriver) {

       // if (Objects.isNull(addCompany)) {
            addCompany = new CompanyForm(webAutomationDriver);
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
