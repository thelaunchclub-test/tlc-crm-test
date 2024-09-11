package com.twozo.page.activity;

import com.twozo.page.BasePage;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.Objects;

public class Activity extends BasePage {

    private static Activity activity;

    private WebPageElement status;
    private WebPageElement title;
    private WebPageElement deal;
    private WebPageElement contacts;
    private WebPageElement company;
    private WebPageElement type;
    private WebPageElement availability;
    private WebPageElement description;
    private WebPageElement startTime;
    private WebPageElement endTime;
    private WebPageElement createdAt;
    private WebPageElement updatedAt;
    private WebPageElement duration;
    private WebPageElement assignedToUser;
    private WebPageElement creator;
    private WebPageElement markedAsDoneTime;
    private WebPageElement lastNotificationTime;
    private WebPageElement reminder;
    private WebPageElement priority;
    private WebPageElement collaborators;
    private WebPageElement location;
    private WebPageElement privateNote;
    private WebPageElement allActivities;
    private WebPageElement taskType;
    private WebPageElement dueDate;
    private WebPageElement statusFilter;
    private WebPageElement listView;
    private WebPageElement calendarView;
    private WebPageElement importActivities;
    private WebPageElement addActivity;

    protected Activity(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static Activity getInstance(final WebAutomationDriver webAutomationDriver) {

        //if (Objects.isNull(activity)) {
            activity = new Activity(webAutomationDriver);
        //}

        return activity;
    }

    public WebPageElement getStatus() {

        if (Objects.isNull(status)) {
            status = findByXpath("(//*[text()='Status'])[2]");
        }

        return status;
    }

    public WebPageElement getTitle() {

        if (Objects.isNull(title)) {
            title = findByText("Title");
        }

        return title;
    }

    public WebPageElement getDeal() {

        if (Objects.isNull(deal)) {
            deal = findByText("Deal");
        }

        return deal;
    }

    public WebPageElement getContacts() {

        if (Objects.isNull(contacts)) {
            contacts = findByText("Contacts");
        }

        return contacts;
    }

    public WebPageElement getCompany() {

        if (Objects.isNull(company)) {
            company = findByText("Company");
        }

        return company;
    }

    public WebPageElement getType() {

        if (Objects.isNull(type)) {
            type = findByText("Type");
        }

        return type;
    }

    public WebPageElement getAvailability() {

        if (Objects.isNull(availability)) {
            availability = findByText("Availability");
        }

        return availability;
    }

    public WebPageElement getDescription() {

        if (Objects.isNull(description)) {
            description = findByText("Description");
        }

        return description;
    }

    public WebPageElement getStartTime() {

        if (Objects.isNull(startTime)) {
            startTime = findByText("Start Time");
        }

        return startTime;
    }

    public WebPageElement getEndTime() {

        if (Objects.isNull(endTime)) {
            endTime = findByText("End Time");
        }

        return endTime;
    }

    public WebPageElement getCreatedAt() {

        if (Objects.isNull(createdAt)) {
            createdAt = findByText("Created At");
        }

        return createdAt;
    }

    public WebPageElement getUpdatedAt() {

        if (Objects.isNull(updatedAt)) {
            updatedAt = findByText("Updated At");
        }

        return updatedAt;
    }

    public WebPageElement getDuration() {

        if (Objects.isNull(duration)) {
            duration = findByText("Duration");
        }

        return duration;
    }

    public WebPageElement getAssignedToUser() {

        if (Objects.isNull(assignedToUser)) {
            assignedToUser = findByText("Assigned To User");
        }

        return assignedToUser;
    }

    public WebPageElement getCreator() {

        if (Objects.isNull(creator)) {
            creator = findByText("Creator");
        }

        return creator;
    }

    public WebPageElement getMarkedAsDoneTime() {

        if (Objects.isNull(markedAsDoneTime)) {
            markedAsDoneTime = findByText("Marked as done time");
        }

        return markedAsDoneTime;
    }

    public WebPageElement getLastNotificationTime() {

        if (Objects.isNull(lastNotificationTime)) {
            lastNotificationTime = findByText("Last notification time");
        }

        return lastNotificationTime;
    }

    public WebPageElement getReminder() {

        if (Objects.isNull(reminder)) {
            reminder = findByText("Reminder");
        }

        return reminder;
    }

    public WebPageElement getPriority() {

        if (Objects.isNull(priority)) {
            priority = findByText("Priority");
        }

        return priority;
    }

    public WebPageElement getCollaborators() {

        if (Objects.isNull(collaborators)) {
            collaborators = findByText("Collaborators");
        }

        return collaborators;
    }

    public WebPageElement getLocation() {

        if (Objects.isNull(location)) {
            location = findByText("Location");
        }

        return location;
    }

    public WebPageElement getPrivateNote() {

        if (Objects.isNull(privateNote)) {
            privateNote = findByText("Private note");
        }

        return privateNote;
    }

    public WebPageElement getAllActivities() {

        if (Objects.isNull(allActivities)) {
            allActivities = findByText("All Activities");
        }

        return allActivities;
    }

    public WebPageElement getTaskType() {

        if (Objects.isNull(taskType)) {
            taskType = findByText("Task Type");
        }

        return taskType;
    }

    public WebPageElement getDueDate() {

        if (Objects.isNull(dueDate)) {
            dueDate = findByText("Due Date");
        }

        return dueDate;
    }

    public WebPageElement getStatusFilter() {

        if (Objects.isNull(statusFilter)) {
            statusFilter = findByText("Status");
        }

        return statusFilter;
    }


    public WebPageElement getListView() {

        if (Objects.isNull(statusFilter)) {
            statusFilter = findByXpath("//button[@value='list']");
        }

        return statusFilter;
    }

    public WebPageElement getCalendarView() {

        if (Objects.isNull(calendarView)) {
            calendarView = findByXpath("//button[@value='calendar']");
        }

        return calendarView;
    }

    public WebPageElement getImportActivities() {

        if (Objects.isNull(importActivities)) {
            importActivities = findByText("Import Activities");
        }

        return importActivities;
    }

    public WebPageElement getAddActivity() {

        if (Objects.isNull(addActivity)) {
            addActivity = findByText("Activity");
        }

        return addActivity;
    }

    public AddActivity addActivity() {
        click(getCalendarView());
        click(getAddActivity());

        return AddActivity.getInstance(webAutomationDriver);
    }
}

