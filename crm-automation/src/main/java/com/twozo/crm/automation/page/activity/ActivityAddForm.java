package com.twozo.crm.automation.page.activity;

import com.twozo.crm.automation.page.form.add.form.AddForm;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

public class ActivityAddForm extends AddForm {

    private static ActivityAddForm addActivity;

    private WebPageElement titleField;
    private WebPageElement type;
    private WebPageElement activityStartDate;
    private WebPageElement activityStartTime;
    private WebPageElement activityEndDate;
    private WebPageElement getActivityEndTime;
    private WebPageElement availability;
    private WebPageElement reminder;
    private WebPageElement priority;
    private WebPageElement salesOwner;
    private WebPageElement collaborators;
    private WebPageElement addGuests;
    private WebPageElement addLocation;
    private WebPageElement addDescription;
    private WebPageElement privateNoteField;
    private WebPageElement dealField;
    private WebPageElement contactField;
    private WebPageElement companyField;
    private WebPageElement saveButton;
    private WebPageElement saveAndNewButton;
    private WebPageElement markAsCompletedCheckBox;
    private WebPageElement closeAddActivityDrawer;

    private ActivityAddForm(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static ActivityAddForm getInstance(final WebAutomationDriver webAutomationDriver) {
        return new ActivityAddForm(webAutomationDriver);
    }

    public WebPageElement getActivityType(final String activityType) {
        return findByXpath(String.format("//*[@aria-label='%s']", activityType));
    }

}
