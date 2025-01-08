package com.twozo.crm.automation.page.company;

import com.twozo.crm.automation.page.BasePage;
import com.twozo.crm.automation.page.xpath.XPathBuilder;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.Objects;

public class Company extends BasePage {

    private static Company company;
    private CompanyAddForm addCompany;

    protected Company(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static Company getInstance(final WebAutomationDriver webAutomationDriver) {

        // if (Objects.isNull(company)) {
        company = new Company(webAutomationDriver);
        //}

        return company;
    }

    private CompanyAddForm getAddCompany() {

        if (Objects.isNull(addCompany)) {
            addCompany = CompanyAddForm.getInstance(webAutomationDriver);
        }

        return addCompany;
    }

    public WebPageElement getAddCompanyButton() {
        return findByText("Company");
    }

    public CompanyAddForm addCompany() {
        waitTillVisible(XPathBuilder.getXPathByText("Company"));
        click(getAddCompanyButton());

        return getAddCompany();
    }

}
