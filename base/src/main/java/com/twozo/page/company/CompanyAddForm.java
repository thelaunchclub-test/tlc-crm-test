package com.twozo.page.company;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.twozo.page.add.form.AddForm;
import com.twozo.page.settings.data.fields.company.field.CompanyField;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.Objects;

public class CompanyAddForm extends AddForm {

    private static CompanyAddForm addCompany;

    protected CompanyAddForm(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
        switchToAddForm("Company");
    }

    public static CompanyAddForm getInstance(final WebAutomationDriver webAutomationDriver) {
        addCompany = new CompanyAddForm(webAutomationDriver);

        return addCompany;
    }

    private WebPageElement getWebsite() {
        return getTextFieldWebPageElement(CompanyField.WEBSITE.getName());
    }

    private WebPageElement getAnnualRevenue() {
        return getNumberFieldWebPageElement(CompanyField.ANNUAL_REVENUE.getName());
    }

    private WebPageElement getAnnualRevenueCurrency() {
        return getDropdownFieldWebPageElement(CompanyField.ANNUAL_REVENUE.getName());
    }

    private WebPageElement getSICCode() {
        return getNumberFieldWebPageElement(CompanyField.SIC_CODE.getName());
    }

    private WebPageElement getEmployees() {
        return getNumberFieldWebPageElement(CompanyField.EMPLOYEES.getName());
    }

    private WebPageElement getParentCompany() {
        return getTextFieldWebPageElement(CompanyField.PARENT_COMPANY.getName());
    }

    private WebPageElement getOrganizationStatus() {
        return getDropdownFieldWebPageElement(CompanyField.ORGANIZATION_STATUS.getName());
    }

    private WebPageElement getIndustryType() {
        return getDropdownFieldWebPageElement(CompanyField.INDUSTRY_TYPE.getName());
    }

    private WebPageElement getBusinessType() {
        return getDropdownFieldWebPageElement(CompanyField.BUSINESS_TYPE.getName());
    }

    private void setName(final CompanyForm companyForm) {
        send(getName(), companyForm.getName());
    }

    private void setWebsite(final CompanyForm companyForm) {
        send(getWebsite(), companyForm.getWebsite());
    }

    private void setSalesOwner(final CompanyForm companyForm) {
        final String salesOwner = companyForm.getSalesOwner();

        if (!Objects.equals(getText(getSalesOwner()), salesOwner)) {
            click(getSalesOwner());
            dropdown(salesOwner);
        }
    }

    private void setAddress(final CompanyForm companyForm) {
        final CompanyForm.Address address = companyForm.getAddress();

        send(getAddressLine1(), address.getAddressLine1());
        send(getAddressLine2(), address.getAddressLine2());
        send(getCity(), address.getCity());
        send(getState(), address.getState());
        click(getCountry());
        dropdown(address.getCountry());
        //mouseActions.click(new Element(LocatorType.XPATH,"//ul[@role='listbox']//*[text()='Angola']))
        send(getPincode(), address.getPincode());
    }

    private void setFacebook(final CompanyForm companyForm) {
        send(getFacebook(), companyForm.getFacebook());
    }

    private void setTwitter(final CompanyForm companyForm) {
        send(getTwitter(), companyForm.getTwitter());
    }

    private void setLinkedIn(final CompanyForm companyForm) {
        send(getLinkedIn(), companyForm.getLinkedin());
    }

    private void setAnnualRevenue(final CompanyForm companyForm) {
        final CompanyForm.AnnualRevenue annualRevenue = companyForm.getAnnualRevenue();

        send(getAnnualRevenue(), annualRevenue.getValue());
        final String currency = annualRevenue.getCurrency();

        if (!Objects.equals(getText(getAnnualRevenueCurrency()), currency)) {
            click(getAnnualRevenueCurrency());
            dropdown(currency);
        }
    }

    private void setSICCode(final CompanyForm companyForm) {
        send(getSICCode(), companyForm.getSICCode());
    }

    private void setTerritory(final CompanyForm companyForm) {
        click(getTerritory());
        dropdown(companyForm.getTerritory());
    }

    private void setTags(final CompanyForm companyForm) {
        final List<String> tags = companyForm.getTags();

        for (final String tag : tags) {
            send(getTags(), tag);
            mouseActions.keyDown(Keys.ENTER).keyUp(Keys.CONTROL).perform();
        }
    }

    private void setEmployees(final CompanyForm companyForm) {
        send(getEmployees(), companyForm.getEmployees());
    }

    private void setParentCompany(final CompanyForm companyForm) {
        send(getParentCompany(), companyForm.getParentCompany());
    }

    private void setOrganizationStatus(final CompanyForm companyForm) {
        click(getOrganizationStatus());
        dropdown(companyForm.getOrganizationStatus());
    }

    private void setIndustryType(final CompanyForm companyForm) {
        click(getIndustryType());
        dropdown(companyForm.getIndustryType());
    }

    private void setBusinessType(final CompanyForm companyForm) {
        click(getBusinessType());
        dropdown(companyForm.getBusinessType());
    }

    private void setDescription(final CompanyForm companyForm) {
        send(getDescription(), companyForm.getDescription());
    }

    public void fill(final CompanyForm companyForm) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        setName(companyForm);
        setWebsite(companyForm);
        setSalesOwner(companyForm);
        setAddress(companyForm);
        setFacebook(companyForm);
        setTwitter(companyForm);
        setLinkedIn(companyForm);
        setAnnualRevenue(companyForm);
        setSICCode(companyForm);
        //setTerritory(companyForm);
        setTags(companyForm);
        setEmployees(companyForm);
       // setParentCompany(companyForm);
        setOrganizationStatus(companyForm);
        setIndustryType(companyForm);
        setBusinessType(companyForm);
        setDescription(companyForm);

    }

}
