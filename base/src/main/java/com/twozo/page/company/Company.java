package com.twozo.page.company;

import com.twozo.page.BasePage;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.Objects;

public class Company extends BasePage {

    private static Company company;
    private AddCompany addCompany;

    protected Company(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static Company getInstance(final WebAutomationDriver webAutomationDriver) {

        // if (Objects.isNull(company)) {
        company = new Company(webAutomationDriver);
        //}

        return company;
    }

    private AddCompany getAddCompany() {

        if (Objects.isNull(addCompany)) {
            addCompany = AddCompany.getInstance(webAutomationDriver);
        }

        return addCompany;
    }

    public WebPageElement getAddCompanyButton() {
        return findByText("Company");
    }

    public AddCompany addCompany() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(getAddCompanyButton());

        return getAddCompany();
    }

}
