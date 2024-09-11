package com.twozo.page.deal;

import com.twozo.page.Month;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;

import java.util.List;
import java.util.Objects;

public class AddDeal extends Deal {

    private static AddDeal addDeal;

    private WebPageElement titleField;
    private WebPageElement pipelineDropdown;
    private WebPageElement stageDropdown;
    private WebPageElement wonReasonDropdown;
    private WebPageElement lostReasonDropdown;
    private WebPageElement dealClosedOn;
    private WebPageElement primaryContact;
    private WebPageElement relatedContacts;
    private WebPageElement company;
    private WebPageElement dealValue;
    private WebPageElement addProducts;
    private WebPageElement salesOwner;
    private WebPageElement saveButton;

    protected AddDeal(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static AddDeal getInstance(final WebAutomationDriver webAutomationDriver) {

      //  if (Objects.isNull(addDeal)) {
            addDeal = new AddDeal(webAutomationDriver);
        //}

        return addDeal;
    }

    public WebPageElement getTitleField() {

        if (Objects.isNull(titleField)) {
//            titleField = findBelowElement(List.of(
//                    new Finder(LocatorType.XPATH, "//*[text()='Title']", true),
//                    new Finder(LocatorType.TAG_NAME, "input", false)));
            titleField = findByXpath("//*[@placeholder='Eg: Finance Software, Website Optimization']");
        }

        return titleField;
    }

    public WebPageElement getPipelineDropdown() {

        if (Objects.isNull(pipelineDropdown)) {
            pipelineDropdown = findBelowElement(List.of(
                    new Element(LocatorType.TAG_NAME, "p", false),
                    new Element(LocatorType.XPATH, "//*[text()='Pipeline']", true)));
        }

        return pipelineDropdown;
    }

    public WebPageElement getStageDropdown() {

        if (Objects.isNull(stageDropdown)) {
            stageDropdown = findBelowElement(List.of(
                    new Element(LocatorType.TAG_NAME, "p", false),
                    new Element(LocatorType.XPATH, "//*[text()='Stage']", true)));
        }

        return stageDropdown;
    }

    public WebPageElement getWonReasonDropdown() {

        if (Objects.isNull(wonReasonDropdown)) {
            wonReasonDropdown = findBelowElement(List.of(
                    new Element(LocatorType.TAG_NAME, "p", false),
                    new Element(LocatorType.XPATH, "//*[text()='Won Reason']", true)));
        }

        return wonReasonDropdown;
    }

    public WebPageElement getLostReasonDropdown() {

        if (Objects.isNull(lostReasonDropdown)) {
            lostReasonDropdown = findBelowElement(List.of(
                    new Element(LocatorType.TAG_NAME, "p", false),
                    new Element(LocatorType.XPATH, "//*[text()='Lost Reason']", true)));

        }

        return lostReasonDropdown;
    }

    public WebPageElement getPrimaryContact() {

        if (Objects.isNull(primaryContact)) {
            primaryContact = findBelowElement(List.of(
                    new Element(LocatorType.TAG_NAME, "input", false),
                    new Element(LocatorType.XPATH, "//*[text()='Primary Contact']", true)));
        }

        return primaryContact;
    }

    public WebPageElement getRelatedContacts() {

        if (Objects.isNull(relatedContacts)) {
            relatedContacts = findBelowElement(List.of(
                    new Element(LocatorType.TAG_NAME, "input", false),
                    new Element(LocatorType.XPATH, "//*[text()='Related Contacts']", true)));
        }

        return relatedContacts;
    }

    public WebPageElement getCompany() {

        if (Objects.isNull(company)) {
            company = findBelowElement(List.of(
                    new Element(LocatorType.TAG_NAME, "input", false),
                    new Element(LocatorType.XPATH, "//*[text()='Company']", true)));
        }

        return company;
    }

    public WebPageElement getDealValue() {

        if (Objects.isNull(dealValue)) {
            dealValue = findBelowElement(List.of(
                    new Element(LocatorType.TAG_NAME, "input", false),
                    new Element(LocatorType.XPATH, "//*[text()='Deal Value']", true)));
        }

        return dealValue;
    }

    public WebPageElement getAddProducts() {

        if (Objects.isNull(addProducts)) {
            addProducts = findByText("Add Products");
        }

        return addProducts;
    }

    public WebPageElement getSalesOwner() {

        if (Objects.isNull(salesOwner)) {
            salesOwner = findBelowElement(List.of(
                    new Element(LocatorType.TAG_NAME, "p", false),
                    new Element(LocatorType.XPATH, "//*[text()='Sales Owner']", true)));
        }

        return salesOwner;
    }

    public WebPageElement getSaveButton() {

        if (Objects.isNull(saveButton)) {
            saveButton = findByText("Save");
        }

        return saveButton;
    }

    public void createDeal() {
        send(getTitleField(), "title");
        click(getStageDropdown());
        dropdown("won");
        click(getWonReasonDropdown());
        dropdown("timing");
        click(getLostReasonDropdown());
        dropdown("junk lead");
        selectDate(new Element(LocatorType.XPATH, "//*[text()='Deal Closed On']", true), Month.AUGUST, 23, 1980);
        send(getPrimaryContact(), "erer");
        send(getRelatedContacts(), "dfse");
        send(getDealValue(), "324");
        click(getSalesOwner());
        dropdown("new user");
        click(getSaveButton());
    }
}
