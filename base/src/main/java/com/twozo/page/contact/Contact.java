package com.twozo.page.contact;

import com.twozo.page.BasePage;
import com.twozo.page.Filter;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.Objects;

public class Contact extends BasePage {

    private static Contact contact;

    private AddContact addContact;
    private WebPageElement filterIcon;
    private WebPageElement firstName;
    private WebPageElement lastName;
    private WebPageElement emails;
    private WebPageElement phones;
    private WebPageElement source;
    private WebPageElement stage;
    private WebPageElement company;
    private WebPageElement department;
    private WebPageElement facebook;
    private WebPageElement twitter;
    private WebPageElement linkedIn;
    private WebPageElement dateOfBirth;
    private WebPageElement designation;
    private WebPageElement createdTime;
    private WebPageElement modifiedTime;
    private WebPageElement territory;
    private WebPageElement salesOwner;
    private WebPageElement createdBy;
    private WebPageElement updatedBy;
    private WebPageElement recentNote;
    private WebPageElement unsubscribeReason;
    private WebPageElement otherUnsubscribeReason;
    private WebPageElement tags;
    private WebPageElement addContactButton;
    private WebPageElement importContacts;

    protected Contact(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static Contact getInstance(final WebAutomationDriver webAutomationDriver) {
        return new Contact(webAutomationDriver);
    }

    private AddContact getAddContact() {
        return AddContact.getInstance(webAutomationDriver);
    }

    public WebPageElement getFilterIcon() {
        return findByXpath("//div[@class='css-j7qwjs'][@style='cursor: pointer;']");
    }

    public WebPageElement getFirstName() {
        return findByText("contact.fields.first.name");
    }

    public WebPageElement getLastName() {
        return findByText("Last Name");
    }

    public WebPageElement getEmails() {
        return findByText("Emails");
    }

    public WebPageElement getPhones() {
        return findByText("Phones");
    }

    public WebPageElement getSource() {
        return findByText("Source");
    }

    public WebPageElement getStage() {
        return findByText("Stage");
    }

    public WebPageElement getCompany() {
        return findByText("Company");
    }

    public WebPageElement getDepartment() {
        return findByText("Department");
    }

    public WebPageElement getFacebook() {
        return findByText("Facebook");
    }

    public WebPageElement getTwitter() {
        return findByText("Twitter");
    }

    public WebPageElement getLinkedIn() {
        return findByText("LinkedIn");
    }

    public WebPageElement getDateOfBirth() {
        return findByText("Date of Birth");
    }

    public WebPageElement getDesignation() {

        return findByText("Designation");
    }

    public WebPageElement getCreatedTime() {
        return findByText("Created Time");
    }

    public WebPageElement getModifiedTime() {

        if (Objects.isNull(modifiedTime)) {
            modifiedTime = findByText("Modified Time");
        }

        return modifiedTime;
    }

    public WebPageElement getTerritory() {

        if (Objects.isNull(territory)) {
            territory = findByText("Territory");
        }

        return territory;
    }

    public WebPageElement getSalesOwner() {

        if (Objects.isNull(salesOwner)) {
            salesOwner = findByText("Sales Owner");
        }

        return salesOwner;
    }

    public WebPageElement getCreatedBy() {

        if (Objects.isNull(createdBy)) {
            createdBy = findByText("Created By");
        }

        return createdBy;
    }

    public WebPageElement getUpdatedBy() {

        if (Objects.isNull(updatedBy)) {
            updatedBy = findByText("Updated By");
        }

        return updatedBy;
    }

    public WebPageElement getRecentNote() {

        if (Objects.isNull(recentNote)) {
            recentNote = findByText("Recent Note");
        }

        return recentNote;
    }

    public WebPageElement getUnsubscribeReason() {

        if (Objects.isNull(unsubscribeReason)) {
            unsubscribeReason = findByText("Unsubscribe reason");
        }

        return unsubscribeReason;
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

    public AddContact addContact() {
        click(getAddContactButton());

        return getAddContact();
    }

    public Filter switchToFilter() {
        click(getFilterIcon());

        return Filter.getInstance(webAutomationDriver);
    }


}
