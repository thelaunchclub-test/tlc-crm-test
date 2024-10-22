package com.twozo.page.company;

import com.twozo.page.BasePage;
import com.twozo.page.xpath.XPathBuilder;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.Objects;

public class Company extends BasePage {

    private static Company company;
    private CompanyForm addCompany;

    protected Company(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static Company getInstance(final WebAutomationDriver webAutomationDriver) {

        // if (Objects.isNull(company)) {
        company = new Company(webAutomationDriver);
        //}

        return company;
    }

    private CompanyForm getAddCompany() {

        if (Objects.isNull(addCompany)) {
            addCompany = CompanyForm.getInstance(webAutomationDriver);
        }

        return addCompany;
    }

    public WebPageElement getAddCompanyButton() {
        return findByText("Company");
    }

    public CompanyForm addCompany() {
        waitTillVisible(XPathBuilder.getXPathByText("Company"));
        click(getAddCompanyButton());

        return getAddCompany();
    }

}
