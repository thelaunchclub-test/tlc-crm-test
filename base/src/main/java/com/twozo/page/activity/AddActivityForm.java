package com.twozo.page.activity;

import com.twozo.commons.json.JsonArray;
import com.twozo.extent.report.reporter.logger.ExtentLogger;
import com.twozo.page.BasePage;
import com.twozo.page.Month;
import com.twozo.page.TestCase;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AddActivityForm extends BasePage {

    private static final String SUGGESTIONS = map.get("crm.activity.list.summary.text");
    private static final String START_TIME = map.get("crm.activity.form.list.start.time.checkbox");
    private static final String DATE_TIME = map.get("crm.activity.list.date.time.text");
    private static final String YEAR_BUTTON = map.get("crm.activity.list.date.select.year.button");
    private static final String ACTIVITIES_LIST = map.get("crm.activity.form.activities.list");
    private static final String TRY = "Try resetting";
    private static final String LAST_ACTIVITY = map.get("crm.activity.form.activities.last.activity");
    private static final String LIST_TITLE = map.get("crm.activity.form.list.title.row");
    private static final String OPTIONS = map.get("crm.activity.form.options");
    private static final String PLACEHOLDER = "placeholder";
    private static final String SPECIFIC_ACTIVITY = map.get("crm.activity.form.list.specific.activity");
    private static final String TIMELINE = map.get("crm.activity.deal.timeline");
    private static final String TIMELINE_TEXT = map.get("crm.activity.deal.activity.name.timeline");

    public AddActivityForm(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    private WebPageElement getActivityButton() {
        return this.findByText(map.get("crm.activity.form.activity.button"));
    }

    private WebPageElement getTitleField() {
        return this.findByXpath(map.get("crm.activity.form.title"));
    }

    private WebPageElement getActivityForm() {
        return this.findByXpath(map.get("crm.activity.form.activityForm"));
    }

    private WebPageElement getSave() {
        return this.findByXpath(map.get("crm.activity.form.save.button"));
    }

    private WebPageElement getSaveAndNewButton() {
        return this.findByXpath(map.get("crm.activity.form.save.new.button"));
    }

    private WebPageElement getCancel() {
        return this.findByXpath(map.get("crm.activity.form.cancel.button"));
    }

    private WebPageElement getCompletedBox() {
        return this.findByXpath(map.get("crm.activity.form.completed.checkbox"));
    }

    private WebPageElement getTodayActivities() {
        return this.findByText(map.get("crm.activity.list.today.activities"));
    }

    private WebPageElement getAllActivities() {
        return this.findByText(map.get("crm.activity.list.all.activities"));
    }

    private WebPageElement getAvailabilityField() {
        return findByXpath(map.get("crm.activity.form.availability"));
    }

    private WebPageElement getReminderField() {
        return findByXpath(map.get("crm.activity.form.reminder"));
    }

    private WebPageElement getPriorityField() {
        return findByXpath(map.get("crm.activity.form.priority"));
    }

    private WebPageElement getSalesOwnerField() {
        return findByXpath(map.get("crm.activity.form.salesOwner"));
    }

    private WebPageElement getCollaboratorsField() {
        return findByXpath(map.get("crm.activity.form.collaborators"));
    }

    private WebPageElement getGuestButton() {
        return findByXpath(map.get("crm.activity.form.guest.button"));
    }

    private WebPageElement getLocationButton() {
        return findByXpath(map.get("crm.activity.form.location.button"));
    }

    private WebPageElement getDescriptionButton() {
        return findByXpath(map.get("crm.activity.form.description.button"));
    }

    private WebPageElement getGuestField() {
        return findByXpath(map.get("crm.activity.form.guest"));
    }

    private WebPageElement getLocationField() {
        return findByXpath(map.get("crm.activity.form.location"));
    }

    private WebPageElement getDescriptionField() {
        return findByXpath(map.get("crm.activity.form.description"));
    }

    private WebPageElement getPrivateNoteField() {
        return findByXpath(map.get("crm.activity.form.private.note"));
    }

    private WebPageElement getInvalidGuest() {
        return findByXpath(map.get("crm.activity.form.invalid.guest"));
    }

    private WebPageElement getDealField() {
        return findByXpath(map.get("crm.activity.form.deal"));
    }

    private WebPageElement getContactField() {
        return findByXpath(map.get("crm.activity.form.contact"));
    }

    private WebPageElement getCompanyField() {
        return findByXpath(map.get("crm.activity.form.company"));
    }


    public void sleep(final int millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {

        }
    }

    /**
     * Iterates over all the checkboxes in the form and collects the names of the fields that are currently checked.
     * After gathering the names of the checked fields, it searches for the position of the specified field
     * and returns its position in the list.
     *
     * @param fieldName The name of the field to find in the list of checked fields.
     * @return int The position of the specified field in the list of checked fields. If not found, returns 0.
     */
    public int getFieldPositionInCheckedList(final String fieldName) {

        sleep(2000);
        final List<String> fieldsThatChecked = new ArrayList<>();
        final Collection<WebPageElement> fieldNames = findElementsByXpath(map.get("crm.activity.form.list.all.checkboxes"));

        int position = 1;

        for (final WebPageElement fieldNameAsElement : fieldNames) {

            if (isSelected(fieldNameAsElement)) {
                fieldsThatChecked.add(getText(findByXpath(String.format(map.get("crm.activity.form.list.checkbox.position"), position))));
            }
            position++;

        }

        for (String s : fieldsThatChecked) {
            System.out.println(s);
        }

        return fieldsThatChecked.indexOf(fieldName) + 1;
    }

    /**
     * Verifies if the Activity add form is visible
     *
     * @return true if the Activity add form is visible, false otherwise
     */
    public boolean verifyAddFormVisible() {

        waitTillVisible("//*[text()='Activity']");

        click(getActivityButton());
        ExtentLogger.pass("Clicked on Activity button to open the form.");

        boolean isFormVisible = isDisplayed(getActivityForm());
        ExtentLogger.pass("Activity add form visibility: " + isFormVisible);

        return isFormVisible;
    }

    /**
     * Verifies if the Activity form can be closed by clicking the cancel button (X symbol).
     *
     * @return true if the Activity form is successfully closed, false if it remains visible
     */
    public boolean isActivityFormClosable() {

        sleep(3000);
        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");

        sleep(2000);
        click(getCancel());
        ExtentLogger.pass("X symbol is clicked");

        sleep(2000);

        try {
            isDisplayed(getActivityForm());
            ExtentLogger.pass("Activity form visible");
            return false;
        } catch (Exception exception) {
            ExtentLogger.pass("Activity form closed");
            return true;
        }
    }

    /**
     * Checks if all default fields are present in the Activity form.
     *
     * @return true if all default fields are displayed, false if any field is missing
     */
    public boolean checkDefaultField() {

        click(getActivityButton());
        ExtentLogger.pass("Activity button clicked");

        final List<String> defaultNames = List.of(
                "Title", "Type", "Date and Time", "Availability",
                "Reminder", "Priority", "Sales Owner", "Collaborators",
                "Private Note", "Deal", "Contact", "Company",
                "Location", "Guests", "Description"
        );

        final Collection<WebPageElement> availableNames = findElementsByXpath("//*[@class='MuiTypography-root MuiTypography-body1 twozo-css-prefix-1uwf8r9']");
        availableNames.add(findByXpath("(//button[text()='Location'])[1]"));
        availableNames.add(findByXpath("(//button[text()='Guests'])[1]"));
        availableNames.add(findByXpath("(//button[text()='Description'])[1]"));

        for (String defaultName : defaultNames) {
            boolean isPresent = availableNames.stream().anyMatch(element -> getText(element).equals(defaultName));

            if (!isPresent) {
                ExtentLogger.fail("Some fields are not showing");
                return false;
            }
        }

        ExtentLogger.pass("All default fields shown");

        return true;
    }

    /**
     * Checks if the "Add Activity" form is visible on the screen.
     *
     * @return boolean true if the activity form is visible, false otherwise.
     */
    public boolean isActivityAddFormVisible() {
        final boolean isVisible = isDisplayed(getActivityForm());
        ExtentLogger.pass("Activity form is visible");

        return isVisible;
    }

    /**
     * Verifies that the default activity type is set correctly in the "Activity" form.
     *
     * @return boolean true if the default activity type (Call) is set correctly, false otherwise.
     */
    public boolean checkDefaultActivityType() {
        final String activityIcons = "Call";

        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");
        return Objects.equals(activityIcons, getAttribute(getTitleField(), "placeholder"));
    }

    /**
     * Verifies that the correct activity type is selected from the activity options.
     *
     * @param testCase The test case containing the input data (activity type).
     * @return boolean true if the selected activity type matches the placeholder text, false otherwise.
     */
    public boolean checkActivityType(final TestCase testCase) {
        final String activityIcons = testCase.input.getString("activityType");

        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");
        click(findByXpath("//*[@aria-label='" + activityIcons + "']"));
        ExtentLogger.pass("The given" + activityIcons + "is Clicked");
        return Objects.equals(activityIcons, getAttribute(getTitleField(), "placeholder"));
    }

    /**
     * Changes the activity type and verifies the change by comparing the activity title
     * with the placeholder text in the "Activity" form.
     *
     * @param testCase The test case containing the input data (new activity type).
     * @return boolean true if the activity type change is successful, false otherwise.
     */
    public boolean changeActivityType(final TestCase testCase) {
        final String activityIcons = testCase.input.getString("activityType");
        int countBeforeAddAnActivity = getCurrentActivityCount();

        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");
        click(findByXpath("//button[@aria-label='" + activityIcons + "']"));
        ExtentLogger.pass("The given" + activityIcons + "is Clicked");
        final String getTitle = getAttribute(getTitleField(), "placeholder");
        click(getSave());
        ExtentLogger.pass("Save button is clicked");

        navigateToActivityList();

        int currentActivityCount = getCurrentActivityCountAfterActivity(countBeforeAddAnActivity);
        final WebPageElement getActivityTitle = findByXpath(String.format(LIST_TITLE, currentActivityCount));

        System.out.println(getText(getActivityTitle));
        System.out.println(getTitle);
        return Objects.equals(getText(getActivityTitle), getTitle);
    }

    /**
     * Clicks the Activity button, verifies if an added activity appears in the list.
     *
     * @return true if the new activity is visible, false otherwise
     */
    public boolean getListOfCollection() {
        return verifyActivityPresenceAfterAddition(false);
    }

    /**
     * Clicks the "Save and New" button and checks if the new activity appears in the list.
     *
     * @return true if the new activity is visible, false otherwise
     */
    public boolean saveWithSaveAndNew() {
        return verifyActivityPresenceAfterAddition(true);
    }

    /**
     * Marks an activity as completed and checks if the activity status updates to "Completed".
     *
     * @return true if the activity is marked as completed, false otherwise
     */
    public boolean getMarkedAsCompleted() {
        return updateActivityStatusAndVerifyCompletion();
    }

    /**
     * Adds an activity with a title specified in a TestCase, and verifies if the title appears in the activity list.
     *
     * @param testCase the test case containing activity title data
     * @return true if the activity title appears in the list, false otherwise
     */
    public boolean addTitleToActivity(final TestCase testCase) {
        final String title = testCase.input.getString("Title");
        int initialActivityCount = getCurrentActivityCount();
        click(getActivityButton());
        ExtentLogger.pass("Activity button clicked");
        send(getTitleField(), title);
        ExtentLogger.pass(title + " entered in TitleField");
        click(getSave());
        ExtentLogger.pass("Save button clicked");

        navigateToActivityList();

        int updatedActivityCount = getCurrentActivityCountAfterActivity(initialActivityCount);

        sleep(2000);
        WebPageElement activityTitleElement = findByXpath(String.format(LIST_TITLE, updatedActivityCount));
        System.out.println(getText(activityTitleElement));
        System.out.println(title);

        return Objects.equals(getText(activityTitleElement), title);
    }

    /**
     * Helper method to verify the presence of a new activity in the activity list after addition.
     *
     * @param useSaveAndNew specifies whether to use the "Save and New" button
     * @return true if the new activity is visible, false otherwise
     */
    private boolean verifyActivityPresenceAfterAddition(boolean useSaveAndNew) {

        sleep(5000);
        int initialActivityCount = getCurrentActivityCount();
        click(getActivityButton());
        ExtentLogger.pass("Activity button clicked");

        String defaultActivityTitle = getAttribute(getTitleField(), PLACEHOLDER);
        if (useSaveAndNew) {
            click(getSaveAndNewButton());
            ExtentLogger.pass("Save and new button clicked");
        } else {
            click(getSave());
            ExtentLogger.pass("Save button clicked");
        }

        navigateToActivityList();
        int updatedActivityCount = getCurrentActivityCountAfterActivity(initialActivityCount);

        WebPageElement activityTitleElement = findByXpath(String.format(LIST_TITLE, updatedActivityCount));
        return Objects.equals(getText(activityTitleElement), defaultActivityTitle);
    }

    /**
     * Helper method to mark an activity as completed and verify if its status is updated.
     *
     * @return true if the activity is marked as completed, false otherwise
     */
    private boolean updateActivityStatusAndVerifyCompletion() {
        int initialActivityCount = getCurrentActivityCount();
        click(getActivityButton());
        ExtentLogger.pass("Activity button clicked");
        sleep(2000);
        click(getCompletedBox());
        ExtentLogger.pass("Marked as completed box clicked");
        click(getSave());
        ExtentLogger.pass("Save button clicked");

        navigateToActivityList();
        int updatedActivityCount = getCurrentActivityCountAfterActivity(initialActivityCount);

        WebPageElement statusElement = findByXpath(String.format(map.get("crm.activity.form.list.status.row.text"), updatedActivityCount));
        final String activityStatus = getText(statusElement);

        System.out.println(activityStatus);
        return "Completed".equals(activityStatus);
    }

    /**
     * <p>
     * Validates the start date of an activity.
     * </p>
     *
     * @return `true` if the activity's start date matches the expected start date; otherwise, `false`.
     */
    public boolean validateActivityStartDate() {
        return validateActivityDate(START_TIME, "Start Time",
                null, null, null);
    }

    /**
     * <p>
     * Validates the end date of an activity.
     * </p>
     *
     * @return `true` if the activity's end date matches the expected end date; otherwise, `false`.
     */
    public boolean validateActivityEndDate() {
        return validateActivityDate(
                map.get("crm.activity.form.list.end.time.checkbox"),
                "End Time",
                String.valueOf(Month.DECEMBER), 12, 2004
        );
    }

    /**
     * <p>
     * Validates the activity start or end date based on the given parameters.
     * </p>
     *
     * @param specificFieldXPath The XPath of the checkbox to toggle the date field in the column settings.
     * @param fieldName          The field name for logging and validation.
     * @param endMonth           The month of the end date (optional for start date validation).
     * @param endDay             The day of the end date (optional for start date validation).
     * @param endYear            The year of the end date (optional for start date validation).
     * @return `true` if the formatted activity date matches the expected date; otherwise, `false`.
     */
    private boolean validateActivityDate(final String specificFieldXPath, final String fieldName, final String endMonth, final Integer endDay, final Integer endYear) {
        int countBeforeAddAnActivity = getCurrentActivityCount();

        click(getColumnSettingsButton());

        if (!isSelected(findByXpath(specificFieldXPath))) {
            click(findByXpath(specificFieldXPath));
        }

        int columnIndex = getFieldPositionInCheckedList(fieldName);
        refresh();
        sleep(3000);

        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");

        String selectedStartDate = pickStartDate(DATE_TIME, String.valueOf(Month.DECEMBER), 11, 2004);
        String[] startDateParts = selectedStartDate.split(" ");
        String formattedStartDate = formatDate(startDateParts[1], startDateParts[0], startDateParts[2]);

        String formattedEndDate = null;
        if (endMonth != null && endDay != null && endYear != null) {
            String selectedEndDate = pickEndDate(DATE_TIME, endMonth, endDay, endYear);
            String[] endDateParts = selectedEndDate.split(" ");
            formattedEndDate = formatDate(endDateParts[1], endDateParts[0], endDateParts[2]);
        }

        click(getSave());
        ExtentLogger.pass("Save button is clicked");

        navigateToActivityList();
        sleep(2000);

        int currentActivityCount = (countBeforeAddAnActivity > 20)
                ? scrollToNewActivityCount()
                : getCurrentActivityCount();
        
        final WebPageElement activityTitleElement = findByXpath(
                String.format(SPECIFIC_ACTIVITY, currentActivityCount, columnIndex)
        );

        String activityTitle = getText(activityTitleElement).trim();

        System.out.println("Activity Title: " + activityTitle);
        System.out.println("Formatted Start Date: " + formattedStartDate);
        if (formattedEndDate != null) {
            System.out.println("Formatted End Date: " + formattedEndDate);
        }

        return formattedEndDate == null
                ? activityTitle.equalsIgnoreCase(formattedStartDate)
                : activityTitle.equalsIgnoreCase(formattedEndDate);
    }

    /**
     * <p>
     * Formats the date into a readable string for comparison.
     * </p>
     *
     * @param day   The day of the date as a string.
     * @param month The month of the date as a string.
     * @param year  The year of the date as a string.
     * @return A formatted date string (e.g., "11 Dec 2004").
     */
    private String formatDate(String day, String month, String year) {
        return day + " " + month.substring(0, 3) + " " + year;
    }

    /**
     * <p>
     * Gets the current count of activities in the list.
     * </p>
     *
     * @return the number of activities currently displayed
     */
    private int getCurrentActivityCount() {
        sleep(5000);
        Collection<WebPageElement> activities = findElementsByXpath(ACTIVITIES_LIST);

        return activities.isEmpty() && getText(findByXpath(ACTIVITIES_LIST)).contains(TRY) ? 0 : activities.size();
    }

    /**
     * <p>
     * Navigates to the list of activities, including Today and All Activities sections.
     * </p>
     */
    private void navigateToActivityList() {
        try {
            isDisplayed(getTodayActivities());
            click(getTodayActivities());
            click(getAllActivities());
        } catch (Exception ignored) {
        }
    }

    /**
     * Scrolls through the activity list until the new activity count is reached.
     *
     * @return the actual count of activities after scrolling
     */

    private int scrollToNewActivityCount() {
        int defaultActivityCount = findElementsByXpath(ACTIVITIES_LIST).size();
        int targetActivityCount = Integer.parseInt(getText(findByXpath("//*[@class='twozo-css-prefix-182t9k5']/p")).replaceAll("\\D+", ""));
        int scrollThreshold = 20;

        while (defaultActivityCount < targetActivityCount) {
            System.out.println("Current Activity Count: " + defaultActivityCount + ", Target: " + targetActivityCount);
            String xpathForRow = String.format(LAST_ACTIVITY, Math.min(scrollThreshold, defaultActivityCount));
            mouseActions.scrollToElement(new Element(LocatorType.XPATH, xpathForRow, true)).perform();

            sleep(2000);

            defaultActivityCount = findElementsByXpath(ACTIVITIES_LIST).size();

            System.out.println("Scrolled. New Activity Count: " + defaultActivityCount);

            if (defaultActivityCount >= scrollThreshold) {
                scrollThreshold += 20;
            }

            if (targetActivityCount < scrollThreshold) {
                break;
            }
        }

        System.out.println("Final Activity Count after scrolling: " + defaultActivityCount);
        return defaultActivityCount;
    }

    /**
     * <p>
     * Selects a start date from a calendar dropdown.
     * </p>
     *
     * @param fieldXPath The XPath locator for the field that triggers the date picker.
     * @param month      The target month to select (e.g., "January").
     * @param date       The specific date to select within the month (e.g., 15).
     * @param year       The target year to navigate to.
     * @return A formatted string representing the selected date.
     */
    protected String pickStartDate(final String fieldXPath, final String month, final int date, final int year) {
        return selectDateFromCalendar(fieldXPath, month, date, year, "crm.activity.list.date.dropdown");
    }

    /**
     * <p>
     * Selects an end date from a calendar dropdown.
     * </p>
     *
     * @param fieldXPath The XPath locator for the field that triggers the date picker.
     * @param month      The target month to select (e.g., "January").
     * @param date       The specific date to select within the month (e.g., 20).
     * @param year       The target year to navigate to.
     * @return A formatted string representing the selected end date.
     */
    protected String pickEndDate(final String fieldXPath, final String month, final int date, final int year) {
        return selectDateFromCalendar(fieldXPath, month, date, year, "crm.activity.list.date.end.dropdown");
    }

    /**
     * <p>
     * Opens a calendar dropdown and selects a specific date.
     * </p>
     *
     * @param fieldXPath  The XPath locator for the field that triggers the calendar dropdown.
     * @param month       The target month to select.
     * @param date        The specific date to select within the month.
     * @param year        The target year to navigate to.
     * @param dropdownKey The key in the `map` object for the calendar dropdown button's XPath.
     * @return A formatted string representing the selected date.
     */
    private String selectDateFromCalendar(final String fieldXPath, final String month, final int date, final int year, final String dropdownKey) {
        final String dateButtonXpath = String.format(YEAR_BUTTON, date);

        sleep(2000);

        click(findBelowElement(List.of(
                new Element(LocatorType.XPATH, map.get(dropdownKey), false),
                new Element(LocatorType.XPATH, fieldXPath, true)
        )));
        ExtentLogger.pass("Calendar icon is clicked");
        click(findByXpath(map.get("crm.activity.list.date.month.dropdown")));
        ExtentLogger.pass("Select year dropdown button is clicked");
        click(findByXpath(String.format(YEAR_BUTTON, year)));
        ExtentLogger.pass(year + " is selected");

        navigateToDate(month, year);

        final WebPageElement dateElement = findByXpath(dateButtonXpath);

        if (isDisplayed(dateElement)) {
            click(dateElement);
        } else {
            throw new RuntimeException("Date element not found or not clickable.");
        }

        sleep(5000);

        return String.format("%s %d %d", month, date, year);
    }

    /**
     * <p>
     * Navigates the calendar to the target month and year.
     * </p>
     *
     * @param month The target month to navigate to (e.g., "March").
     * @param year  The target year to navigate to.
     * @throws RuntimeException If the target month and year cannot be reached within the defined attempt limit.
     */
    private void navigateToDate(final String month, final int year) {
        String targetDate = String.format("%s %d", month, year);
        int maxAttempts = 12;

        while (maxAttempts > 0) {
            WebPageElement div = findByXpath(map.get("crm.activity.list.date.month.text"));
            String currentDisplayedDate = getText(div);

            if (currentDisplayedDate.equals(targetDate)) {
                return;
            }

            boolean isEarlier = isTargetMonthEarlier(currentDisplayedDate, targetDate);

            if (isEarlier) {
                click(findByXpath(map.get("crm.activity.list.date.previous.month.button")));
                ExtentLogger.pass("Previous month dropdown is selected");
            } else {
                click(findByXpath(map.get("crm.activity.list.date.next.month.button")));
                ExtentLogger.pass("Next month dropdown is selected");
            }

            maxAttempts--;
            sleep(500);
        }

        throw new RuntimeException("Failed to reach the specified month/year within the attempt limit.");
    }

    /**
     * <p>
     * Determines if the target month and year are earlier than the current displayed month and year.
     * </p>
     *
     * @param currentDisplayedDate The current date displayed on the calendar, formatted as "Month Year" (e.g., "March 2025").
     * @param targetDate           The target date to compare, formatted as "Month Year" (e.g., "January 2025").
     * @return `true` if the target date is earlier than the current date, `false` otherwise.
     */
    private boolean isTargetMonthEarlier(final String currentDisplayedDate, final String targetDate) {

        final String[] currentParts = currentDisplayedDate.split(" ");
        final String[] targetParts = targetDate.split(" ");

        final String currentMonth = currentParts[0];
        int currentYear = Integer.parseInt(currentParts[1]);
        final String targetMonth = targetParts[0];
        int targetYear = Integer.parseInt(targetParts[1]);

        if (targetYear < currentYear) {
            return true;
        } else if (targetYear > currentYear) {
            return false;
        }

        int currentMonthIndex = getMonthIndex(currentMonth);
        int targetMonthIndex = getMonthIndex(targetMonth);

        return targetMonthIndex < currentMonthIndex;
    }

    /**
     * <p>
     * Retrieves the zero-based index of a given month name.
     * </p>
     *
     * @param monthName The name of the month to retrieve the index for (e.g., "March").
     * @return The zero-based index of the month (e.g., 0 for "January", 11 for "December"), or -1 if the name is invalid.
     */
    private int getMonthIndex(final String monthName) {
        return switch (monthName) {
            case "January" -> 0;
            case "February" -> 1;
            case "March" -> 2;
            case "April" -> 3;
            case "May" -> 4;
            case "June" -> 5;
            case "July" -> 6;
            case "August" -> 7;
            case "September" -> 8;
            case "October" -> 9;
            case "November" -> 10;
            case "December" -> 11;
            default -> -1;
        };
    }

    /**
     * <p>
     * Verifies the status of an activity based on a randomly generated date.
     * </p>
     * Helps validate that activities correctly update their status according to the given date,
     * ensuring the system's handling of date-based status transitions is correct.
     *
     * @return boolean Returns true if the activity status matches the expected status ("Overdue" or "Pending")
     *         based on whether the given date is in the past or future.
     */
    public boolean validateActivityStatusByDate() {
        int countBeforeAddAnActivity = getCurrentActivityCount();

        click(getColumnSettingsButton());

        WebPageElement checkbox = findByXpath(START_TIME);
        if (!isSelected(checkbox)) {
            click(checkbox);
        }

        refresh();

        sleep(3000);
        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");

        pickStartDate(DATE_TIME, String.valueOf(Month.DECEMBER), 11, 2024);

        String selectedDate = "December 11 2024 9:45 AM";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy h:mm a", Locale.ENGLISH);

        LocalDateTime selectedDateTime;
        try {
            selectedDateTime = LocalDateTime.parse(selectedDate, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return false;
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        String activityStatus = selectedDateTime.isAfter(currentDateTime) ? "Pending" : "Overdue";
        System.out.println("Status: " + activityStatus);

        click(getSave());
        ExtentLogger.pass("Save button is clicked");
        navigateToActivityList();

        sleep(2000);

        int currentActivityCount = getCurrentActivityCountAfterActivity(countBeforeAddAnActivity);

        WebPageElement getActivityTitle = findByXpath(String.format(map.get("crm.activity.form.list.status.row.text"), currentActivityCount));
        final String activityTitle = getText(getActivityTitle).trim();

        return switch (activityStatus) {
            case "Pending" -> activityTitle.contains("Pending");
            case "Overdue" -> activityTitle.contains("Overdue");
            default -> false;
        };
    }

    /**
     * <p>
     * Validates whether the default status of a specified field in the Add Activity form
     * matches the expected value displayed in the activity list.
     * </p>
     *
     * @param fieldName     The name of the field being validated (e.g., "Availability").
     * @param fieldXpath    The XPath of the field in the column settings.
     * @param getFieldMethod The method name to fetch the field's value.
     * @return boolean      Returns true if the field's default status matches the displayed value,
     *                      false otherwise.
     */
    public boolean verifyFieldDefaultStatus(final String fieldName, final String fieldXpath, final String getFieldMethod) {

        int countBeforeAddAnActivity = getCurrentActivityCount();
        sleep(2000);
        click(getColumnSettingsButton());

        if (!isSelected(findByXpath(fieldXpath))) {
            sleep(2000);
            click(findByXpath(fieldXpath));
        }

        int getNumber = getFieldPositionInCheckedList(fieldName);
        refresh();

        sleep(4000);
        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");
        String fieldText = getFieldText(getFieldMethod);

        click(getSave());
        ExtentLogger.pass("Save button is clicked");
        navigateToActivityList();

        sleep(2000);

        int currentActivityCount = (countBeforeAddAnActivity > 20) ? scrollToNewActivityCount() : getCurrentActivityCount();

        final WebPageElement getActivityTitle = findByXpath(String.format(SPECIFIC_ACTIVITY, currentActivityCount, getNumber));

        System.out.println(getText(getActivityTitle));
        System.out.println(fieldText);
        return Objects.equals(getText(getActivityTitle), fieldText);
    }

    /**
     * <p>
     * Fetches the text value of a specified field from the Add Activity form based on the provided method name.
     * </p>
     *
     * @param fieldMethod The name of the field for which the text value is to be fetched.
     *
     * @return String     The text value of the specified field.
     * @throws IllegalArgumentException if an invalid field method is provided.
     */
    private String getFieldText(String fieldMethod) {
        return switch (fieldMethod) {
            case "Availability" -> getText(getAvailabilityField());
            case "Reminder" -> getText(getReminderField());
            case "Priority" -> getText(getPriorityField());
            case "Assigned To User" -> getText(getSalesOwnerField());
            default -> throw new IllegalArgumentException("Invalid field method: " + fieldMethod);
        };
    }

    /**
     * Validates whether the default status of the "Availability" field in the Add Activity form
     * matches the expected value.
     *
     * @return boolean - Returns true if the default status matches, false otherwise.
     */
    public boolean verifyDefaultAvailabilityStatus() {
        return verifyFieldDefaultStatus("Availability", map.get("crm.activity.form.list.availability.checkbox"), "Availability");
    }

    /**
     * Validates whether the default status of the "Reminder" field in the Add Activity form
     * matches the expected value.
     *
     * @return boolean - Returns true if the default status matches, false otherwise.
     */
    public boolean verifyDefaultReminderStatus() {
        return verifyFieldDefaultStatus("Reminder", map.get("crm.activity.form.list.reminder.checkbox"), "Reminder");
    }

    /**
     * Validates whether the default status of the "Priority" field in the Add Activity form
     * matches the expected value.
     *
     * @return boolean - Returns true if the default status matches, false otherwise.
     */
    public boolean verifyDefaultPriorityStatus() {
        return verifyFieldDefaultStatus("Priority", map.get("crm.activity.form.list.priority.checkbox"), "Priority");
    }

    /**
     * Validates whether the default "Assigned To User" (Sales Owner) field in the Add Activity form
     * matches the expected value.
     *
     * @return boolean - Returns true if the default status matches, false otherwise.
     */
    public boolean verifyDefaultAssignToUserStatus() {
        return verifyFieldDefaultStatus("Assigned To User", map.get("crm.activity.form.list.assign.user.checkbox"), "Assigned To User");
    }

    /**
     * <p>
     * Validates whether the status of a specified field (e.g., Availability, Reminder, or Priority)
     * in the Add Activity form matches the expected value displayed in the activity list.
     * </p>
     *
     * @param testCase  The test case containing input data for validation.
     * @param statusType The field status type to validate (e.g., "Availability", "Reminder", "Priority").
     * @return boolean   Returns true if the field's status matches the expected value, false otherwise.
     */
    public boolean validateFieldStatus(final TestCase testCase, final String statusType) {

        final String statusValue = testCase.input.getString(statusType.toLowerCase());
        final String xPathForSpecificField = String.format(map.get("crm.activity.form.list.generic.checkbox"), statusType);

        int countBeforeAddAnActivity = getCurrentActivityCount();

        click(getColumnSettingsButton());
        sleep(2000);

        if (!isSelected(findByXpath(xPathForSpecificField))) {
            click(findByXpath(xPathForSpecificField));
        }

        int getNumber = getFieldPositionInCheckedList(statusType);
        refresh();

        sleep(4000);
        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");
        click(validateFieldStatus(statusType));
        dropdown(String.valueOf(getEnumValueForFieldStatus(statusType, statusValue)));

        final String statusFieldText = getText(validateFieldStatus(statusType));
        click(getSave());
        ExtentLogger.pass("Save button is clicked");

        navigateToActivityList();

        sleep(2000);

        int currentActivityCount = (countBeforeAddAnActivity > 20) ? scrollToNewActivityCount() : getCurrentActivityCount();

        final WebPageElement getActivityTitle = findByXpath(String.format(SPECIFIC_ACTIVITY, currentActivityCount, getNumber));
        System.out.println(getText(getActivityTitle));
        System.out.println(statusFieldText);

        return Objects.equals(getText(getActivityTitle), statusFieldText);
    }

    /**
     * <p>
     * Returns the appropriate field element based on the specified status type.
     * </p>
     *
     * @param statusType The type of status field (e.g., "Availability", "Reminder", "Priority").
     * @return WebPageElement The WebPageElement representing the requested field.
     * @throws IllegalArgumentException if the statusType is invalid.
     */
    private WebPageElement validateFieldStatus(final String statusType) {
        return switch (statusType) {
            case "Availability" -> getAvailabilityField();
            case "Reminder" -> getReminderField();
            case "Priority" -> getPriorityField();
            default -> throw new IllegalArgumentException("Invalid status type: " + statusType);
        };
    }

    /**
     * <p>
     * Converts the string value of a status to its corresponding enum value.
     * </p>
     *
     * @param statusType The type of status field (e.g., "Availability", "Reminder", "Priority").
     * @param statusValue The status value to be converted into an enum value.
     * @return String The enum value corresponding to the provided status value.
     * @throws IllegalArgumentException if the statusType is invalid.
     */
    private String getEnumValueForFieldStatus(final String statusType, final String statusValue) {
        return switch (statusType) {
            case "Availability" -> AvailabilityStatus.fromValue(statusValue).getValue();
            case "Reminder" -> ReminderStatus.fromValue(statusValue).getValue();
            case "Priority" -> PriorityStatus.fromValue(statusValue).getValue();
            default -> throw new IllegalArgumentException("Invalid status type: " + statusType);
        };
    }

    /**
     * <p>
     * Validates the default or selected availability status of an activity by using the getStatus method.
     * </p>
     *
     * @param testCase The test case containing input data for validation.
     * @return boolean Returns true if the availability status is correct, false otherwise.
     */
    public boolean validateAvailabilityStatus(final TestCase testCase) {
        return validateFieldStatus(testCase, "Availability");
    }

    /**
     * <p>
     * Validates the default or selected reminder status of an activity by using the getStatus method.
     * </p>
     *
     * @param testCase The test case containing input data for validation.
     * @return boolean Returns true if the reminder status is correct, false otherwise.
     */
    public boolean validateReminderStatus(final TestCase testCase) {
        return validateFieldStatus(testCase, "Reminder");
    }

    /**
     * <p>
     * Validates the default or selected priority status of an activity by using the getStatus method.
     * </p>
     *
     * @param testCase The test case containing input data for validation.
     * @return boolean Returns true if the priority status is correct, false otherwise.
     */
    public boolean validatePriorityStatus(final TestCase testCase) {
        return validateFieldStatus(testCase, "Priority");
    }

    /**
     * <p>
     * Validates whether the correct collaborator (sales owner) is selected in the Add Activity form
     * and saved in the activity list.
     * </p>
     *
     * @param testCase The test case containing the input data for the collaborator selection.
     * @return boolean Returns true if the selected collaborator in the activity matches the expected
     *                 collaborator text, otherwise false.
     */
    public boolean validateCollaboratorField(final TestCase testCase) {
        final String salesOwnerText = testCase.input.getString("Collaborators");
        final String xPathForSpecificField = map.get("crm.activity.form.list.collaborator.checkbox");

        click(getColumnSettingsButton());
        sleep(2000);

        if (!isSelected(findByXpath(xPathForSpecificField))) {
            click(findByXpath(xPathForSpecificField));
        }

        int getNumber = getFieldPositionInCheckedList("Collaborators");
        refresh();

        int initialActivityCount = getCurrentActivityCount();

        sleep(4000);
        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");
        click(getCollaboratorsField());
        ExtentLogger.pass("Collaborators field is clicked");
        Collection<WebPageElement> salesOwnerOptions = findElementsByXpath(map.get("crm.activity.form.sales.owner.options"));

        for (WebPageElement option : salesOwnerOptions) {
            if (getText(option).equals(salesOwnerText)) {
                click(option);
                ExtentLogger.pass(option + "is clicked");
                break;
            }
        }
        click(findByXpath("//body"));
        sleep(2000);

        final String collaboratorsFieldText = getText(getCollaboratorsField());
        click(getSave());
        ExtentLogger.pass("Save button is clicked");
        navigateToActivityList();

        int currentActivityCount = (initialActivityCount > 20) ? scrollToNewActivityCount() : getCurrentActivityCount();

        final WebPageElement getActivityTitle = findByXpath(String.format(SPECIFIC_ACTIVITY, currentActivityCount, getNumber));

        return Objects.equals(getText(getActivityTitle).trim(), collaboratorsFieldText);
    }

    /**
     * <p>
     * Validates whether the correct guest is selected and saved in the activity form
     * and compares the guest name on the activity list page with the selected guest name.
     * </p>
     *
     * @param testCase The test case containing the input data for selecting the guest.
     * @return boolean Returns true if the selected guest's name in the activity list matches
     *                 the expected guest name, otherwise false.
     */
    public boolean verifyGuestSelection(final TestCase testCase) {

        final String guest = testCase.input.getString("guest");
        int countBeforeAddAnActivity = getCurrentActivityCount();

        navigateToActivityList();

        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");

        click(getGuestButton());
        ExtentLogger.pass("Guest button is clicked");
        sleep(2000);

        click(getGuestField());
        ExtentLogger.pass("Guest field is clicked");
        sleep(2000);
        send(getGuestField(), "J");
        ExtentLogger.pass("Value is entered in Guest field");


        Collection<WebPageElement> guestOptions = findElementsByXpath(map.get("crm.activity.form.guest.options"));
        for (WebPageElement option : guestOptions) {
            if (getText(option).equals(guest)) {
                click(option);
                ExtentLogger.pass(option + " is clicked");
                break;
            }
        }
        sleep(2000);

        final String getGuestText = getText(findByXpath(SUGGESTIONS));
        click(getSave());
        ExtentLogger.pass("Save button is clicked");
        sleep(5000);

        int currentActivityCount = (countBeforeAddAnActivity >= 20) ? scrollToNewActivityCount() : getCurrentActivityCount();

        click(findByXpath(String.format(LAST_ACTIVITY, currentActivityCount)));
        final String getGuestField = getText(findByXpath(map.get("crm.activity.list.guest.detail.page")));

        System.out.println(getGuestText);
        System.out.println(getGuestField);

        return getGuestText.contains(getGuestField);
    }

    /**
     * Validates the selection of a guest by email and ensures the selected guest is displayed in the activity.
     *
     * @param testCase The test case containing the input guest name and email.
     * @return boolean Returns true if the guest is correctly selected and saved in the activity.
     */
    public boolean getGuestWithEmail(final TestCase testCase) {
        final String guestEmail = testCase.input.getString("guestWithEmail");
        int countBeforeAddAnActivity = getCurrentActivityCount();

        navigateToActivityList();
        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");

        selectGuest(guestEmail);

        final String getGuestText = getText(findByXpath(SUGGESTIONS));

        click(getSave());
        ExtentLogger.pass("Save button is clicked");
        sleep(2000);

        int currentActivityCount = getCurrentActivityCountAfterActivity(countBeforeAddAnActivity);
        click(findByXpath(String.format(LAST_ACTIVITY, currentActivityCount)));

        final String getGuestField = getText(findByXpath(map.get("crm.activity.summary.guest")));

        return getGuestText.equals(getGuestField);
    }

    /**
     * Helper method to select a guest by email.
     *
     * @param guest The email address of the guest to be selected.
     */
    private void selectGuest(final String guest) {
        click(getGuestButton());
        ExtentLogger.pass("Guest button is clicked");
        sleep(2000);

        click(getGuestField());
        send(getGuestField(), guest);
        ExtentLogger.pass("Value is entered in the guest field");

        Collection<WebPageElement> guestOptions = findElementsByXpath(map.get("crm.activity.form.guest.suggestions"));
        for (WebPageElement option : guestOptions) {
            if (getText(option).equals(guest)) {
                click(option);
                ExtentLogger.pass(option + " is clicked");
                break;
            }
        }
    }

    /**
     * Helper method to handle activity count after activity is added.
     *
     * @param countBeforeAddAnActivity The count before adding an activity.
     * @return int The current activity count after the activity is added.
     */
    private int getCurrentActivityCountAfterActivity(int countBeforeAddAnActivity) {
        return (countBeforeAddAnActivity >= 20) ? scrollToNewActivityCount() : getCurrentActivityCount();
    }

    /**
     * <p>
     * Adds multiple guests to an activity by selecting their names from a list of available guests.
     * Interacts with the guest selection UI, selects multiple guests, and ensures they are
     * correctly added to the activity.
     * </p>
     *
     * @param testCase The test case containing the list of guest names to be added.
     * @return boolean Returns true if all guests are successfully added to the activity.
     */
    public boolean addMultipleGuestsToActivity(final TestCase testCase) {

        final JsonArray guests = testCase.input.getJsonArray("guest");
        int countBeforeAddAnActivity = getCurrentActivityCount();

        navigateToActivityList();

        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");
        click(getGuestButton());
        ExtentLogger.pass("Guest button is clicked");
        sleep(2000);

        click(getGuestField());
        ExtentLogger.pass("Guest field is clicked");
        click(getGuestField());

        sleep(5000);

        for (int i = 0; i < guests.size(); i++) {
            String guest = guests.getString(i);
            click(getGuestField());
            send(getGuestField(), guest);
            ExtentLogger.pass(guest + "is entered in Guest field");

            boolean isGuestExists = false;
            Collection<WebPageElement> guestOption = findElementsByXpath(map.get("crm.activity.form.guest.suggestion.multiple"));

            for (WebPageElement option : guestOption) {

                if (getText(option).equals(guest)) {
                    click(option);
                    ExtentLogger.pass(option + "is clicked");
                    isGuestExists = true;
                    break;
                }
            }

            if (!isGuestExists) {
                click(findByXpath(map.get("crm.activity.form.guest.exist")));
            }

            sleep(2000);

            click(getGuestField());
            click(getGuestField());
        }

        final String getGuestText = getText(findByXpath(map.get("crm.activity.form.guest.text")));

        click(getSave());
        ExtentLogger.pass("Save button is clicked");
        sleep(2000);

        int currentActivityCount = getCurrentActivityCountAfterActivity(countBeforeAddAnActivity);

        click(findByXpath(String.format(LAST_ACTIVITY, currentActivityCount)));
        sleep(2000);

        final String getGuestField = getText(findByXpath(map.get("crm.activity.summary.guest.field")));

        return getGuestField.contains(getGuestText);
    }

    /**
     * <p>
     * Enters an invalid email into the guest field and verifies if the error indicator is visible.
     * Simulates entering an incorrect email address in the guest field and ensures
     * that the system displays the expected validation error.
     * </p>
     * @return boolean Returns true if the error indicator is visible, indicating the invalid email was detected.
     */
    public boolean validateInvalidEmailInGuestField() {
        final String data = "kai";
        sleep(3000);
        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");
        selectGuest(data);

        return isDisplayed(getInvalidGuest());
    }

    /**
     * Verifies if an activity is created with the given field data (Location, Description, or Private Note).
     *
     * @param testCase The name of the field (e.g., "Location", "Description", "Private Note").
     * @param fieldData The value to be entered into the field (e.g., location, description, private note).
     * @return boolean Returns true if the activity's field data is correctly displayed, false otherwise.
     */
    public boolean getFieldStatus(final TestCase testCase, final String fieldData) {
        final String xPathForSpecificField = String.format(map.get("crm.activity.form.list.generic.checkbox"), fieldData);
        final int initialActivityCount = getCurrentActivityCount();

        click(getColumnSettingsButton());
        toggleCheckbox(xPathForSpecificField);

        int activityNumber = getFieldPositionInCheckedList(fieldData);
        refresh();
        sleep(4000);
        createActivityWithFieldData(testCase, fieldData);
        navigateToActivityList();

        int currentActivityCount = getCurrentActivityCountAfterActivity(initialActivityCount);
        final WebPageElement activityTitle = findByXpath(String.format(SPECIFIC_ACTIVITY, currentActivityCount, activityNumber));
        final String expectedText = testCase.input.getString(fieldData.toLowerCase());
        System.out.println(getText(activityTitle)); // This prints the actual text
        System.out.println(expectedText);

        return Objects.equals(getText(activityTitle), expectedText);
    }

    /**
     * Helper method to toggle a checkbox if not already selected.
     */
    private void toggleCheckbox(final String xPath) {
        if (!isSelected(findByXpath(xPath))) {
            sleep(2000);
            click(findByXpath(xPath));
        }
    }

    /**
     * Creates an activity with the field data provided in the test case.
     *
     * @param testCase The test case containing the input data.
     * @param fieldName The name of the field (e.g., "Location", "Description", "Private Note").
     */
    private void createActivityWithFieldData(final TestCase testCase, final String fieldName) {

        final String fieldData = testCase.input.getString(fieldName.toLowerCase());
        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");

        if (!fieldName.equalsIgnoreCase("Private Note")) {
            click(getButtonForField(fieldName));
            ExtentLogger.pass(fieldName + " button is clicked");
        }

        click(getFieldForInput(fieldName));
        ExtentLogger.pass(fieldName + " field is clicked");
        send(getFieldForInput(fieldName), fieldData);
        ExtentLogger.pass(fieldData + " value is entered in " + fieldName + " field");
        click(getSave());
        ExtentLogger.pass("Save button is clicked");
    }

    /**
     * Helper method to get the button for a specific field (Location, Description, Private Note).
     */
    private WebPageElement getButtonForField(final String fieldName) {
        return switch (fieldName) {
            case "Location" -> getLocationButton();
            case "Description" -> getDescriptionButton();
            default -> throw new IllegalArgumentException("Unknown field: " + fieldName);
        };
    }

    /**
     * Helper method to get the input field for a specific field (Location, Description, Private Note).
     */
    private WebPageElement getFieldForInput(final String fieldName) {
        return switch (fieldName) {
            case "Location" -> getLocationField();
            case "Description" -> getDescriptionField();
            case "Private note" -> getPrivateNoteField();
            case "Deal" -> getDealField();
            case "Company" -> getCompanyField();
            case "Contacts" -> getContactField();
            default -> throw new IllegalArgumentException("Unknown field: " + fieldName);
        };
    }

    /**
     * <p>
     * Retrieves the location status by calling getFieldStatus with "Location".
     * </p>
     *
     * @param testCase The test case containing the expected field value for comparison.
     * @return Returns a boolean indicating if the location status matches the expected value.
     */
    public boolean getLocationStatus(final TestCase testCase) {
        return getFieldStatus(testCase, "Location");
    }

    /**
     * <p>
     * Retrieves the description status by calling getFieldStatus with "Description".
     * </p>
     *
     * @param testCase The test case containing the expected field value for comparison.
     * @return Returns a boolean indicating if the description status matches the expected value.
     */
    public boolean getDescriptionStatus(final TestCase testCase) {
        return getFieldStatus(testCase, "Description");
    }

    /**
     * <p>
     * Retrieves the private note status by calling getFieldStatus with "Private note".
     * </p>
     *
     * @param testCase The test case containing the expected field value for comparison.
     * @return Returns a boolean indicating if the private note status matches the expected value.
     */
    public boolean getPrivateNote(final TestCase testCase) {
        return getFieldStatus(testCase, "Private note");
    }

    /**
     * <p>
     * Adds a contact to an activity and verifies if the contact is correctly associated with the activity.
     * </p>
     * @param testCase The test case containing input data for the contact field.
     * @return True if the contact is correctly added to the activity, false otherwise.
     */
    public boolean addContactToActivity(final TestCase testCase) {
        return addEntityToActivity(testCase, "Contacts", map.get("crm.activity.form.list.contact.checkbox"),
                map.get("crm.activity.form.company.options"));
    }

    /**
     * <p>
     * Adds a company to an activity and verifies if the company is correctly associated with the activity.
     * </p>
     *
     * @param testCase The test case containing input data for the company field.
     * @return True if the company is correctly added to the activity, false otherwise.
     */
    public boolean addCompanyToActivity(final TestCase testCase) {
        return addEntityToActivity(testCase, "Company", map.get("crm.activity.form.list.company.checkbox"),
                map.get("crm.activity.form.company.options"));
    }

    /**
     * <p>
     * Adds a deal to an activity and verifies if the deal is correctly associated with the activity.
     * </p>
     *
     * @param testCase The test case containing input data for the deal field.
     * @return True if the deal is correctly added to the activity, false otherwise.
     */
    public boolean addDealToActivity(final TestCase testCase) {
        return addEntityToActivity(testCase, "Deal", map.get("crm.activity.form.list.deal.checkbox"), OPTIONS);
    }

    /**
     * <p>
     * Adds an entity (e.g., Contact, Company, Deal) to an activity and verifies if the entity
     * is correctly associated with the activity in the activity list.
     * </p>
     *
     * @param testCase The test case containing input data for the entity field.
     * @param fieldName The name of the entity to be added (e.g., Contact, Company, Deal).
     * @param checkboxXPath The XPath of the checkbox corresponding to the entity in column settings.
     * @param optionsXPath The XPath of the dropdown options for selecting the entity.
     * @return True if the entity is successfully added to the activity, false otherwise.
     */
    private boolean addEntityToActivity(final TestCase testCase, final String fieldName, final String checkboxXPath, final String optionsXPath) {
        final String fieldData = testCase.input.getString(fieldName.toLowerCase());
        int initialActivityCount = getCurrentActivityCount();

        configureColumnSettings(fieldName, checkboxXPath);

        int columnIndex = getFieldPositionInCheckedList(fieldName);
        refresh();

        sleep(4000);
        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");
        click(getFieldForInput(fieldName));
        ExtentLogger.pass(fieldName + " field is clicked");
        send(getFieldForInput(fieldName), fieldData);
        ExtentLogger.pass(fieldData + " is entered in " + fieldName + " field");
        selectOptionsFromDropdown(fieldData, optionsXPath);
        click(getSave());
        ExtentLogger.pass("Save button is clicked");
        navigateToActivityList();

        int currentActivityCount = getCurrentActivityCountAfterActivity(initialActivityCount);
        final WebPageElement getActivityTitle = findByXpath(String.format(SPECIFIC_ACTIVITY, currentActivityCount, columnIndex));

        return Objects.equals(getText(getActivityTitle).trim(), fieldData);
    }

    /**
     * <p>
     * Configures the column settings to include a specific field in the activity list.
     * Ensures that the checkbox for the given field is selected.
     * </p>
     *
     * @param fieldName The name of the field (e.g., Contact, Company, Deal).
     * @param checkboxXPath The XPath of the checkbox corresponding to the field in column settings.
     */
    private void configureColumnSettings(final String fieldName, final String checkboxXPath) {
        click(getColumnSettingsButton());
        ExtentLogger.pass("Column Settings button is clicked");

        sleep(2000);
        if (!isSelected(findByXpath(checkboxXPath))) {
            sleep(1000);
            click(findByXpath(checkboxXPath));
            ExtentLogger.pass(fieldName + " checkbox is clicked");
        }
    }

    /**
     * <p>
     * Selects an option from a dropdown based on the provided value.
     * </p>
     *
     * @param fieldData The value to select from the dropdown.
     * @param optionsXPath The XPath of the dropdown options.
     */
    private void selectOptionsFromDropdown(final String fieldData, final String optionsXPath) {
        Collection<WebPageElement> options = findElementsByXpath(optionsXPath);
        for (WebPageElement option : options) {
            if (getText(option).equals(fieldData)) {
                click(option);
                ExtentLogger.pass(option + " is selected");
                break;
            }
        }
        sleep(2000);
    }

    /**
     * <p>
     * Creates a new activity by selecting a dropdown option for a given field.
     * </p>
     *
     * @param fieldName     The name of the field where the value should be entered.
     * @param fieldValue    The value to be entered in the field.
     * @param optionsXPath  The XPath of the options in the dropdown to select the matching option.
     */
    private void createActivityWithDropdownSelection(final String fieldName, final String fieldValue, final String optionsXPath) {
        sleep(4000);
        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");

        WebPageElement field = getFieldForInput(fieldName);
        click(field);
        ExtentLogger.pass(fieldName + " field is clicked");

        send(field, fieldValue);
        ExtentLogger.pass(fieldValue + " value is entered in " + fieldName + " field");

        Collection<WebPageElement> options = findElementsByXpath(optionsXPath);
        for (WebPageElement option : options) {
            if (getText(option).equals(fieldValue)) {
                click(option);
                ExtentLogger.pass(option + " is clicked");
                break;
            }
        }

        click(getSave());
        ExtentLogger.pass("Save button is clicked");
    }

    /**
     * <p>
     * Validates that the activity created in the system appears correctly in the timeline.
     * </p>
     *
     * @param currentActivityCount The current count of activities to validate the new one.
     * @param activityTitleXPath   The XPath to find the activity title in the activity list.
     * @param timelineTextXPath    The XPath for the timeline to validate the activity.
     * @param fieldIndex           The index of the field to select in the activity list.
     * @return true if the activity title matches the timeline text, otherwise false.
     */
    private boolean validateActivityInTimeline(final int currentActivityCount, final String activityTitleXPath, final String timelineTextXPath, final int fieldIndex) {

        final WebPageElement activityTitleElement = findByXpath(String.format(activityTitleXPath, currentActivityCount));
        final String activityTitle = getText(activityTitleElement).trim();

        final WebPageElement activityListElement = findByXpath(String.format(SPECIFIC_ACTIVITY, currentActivityCount, fieldIndex));
        click(activityListElement);
        ExtentLogger.pass("The selected activity is clicked");

        click(findByXpath("//button[text()='Timeline']"));
        ExtentLogger.pass("Timeline button is clicked");

        final WebPageElement timelineElement = findByXpath(String.format(timelineTextXPath, activityTitle));
        String timelineText = getText(timelineElement);

        if (timelineText != null && !timelineText.isEmpty()) {
            timelineText = timelineText.substring(0, timelineText.length() - 1);
        }

        return Objects.equals(activityTitle, Objects.requireNonNull(timelineText).trim());
    }

    /**
     * <p>
     * Checks if an entity (e.g., Deal, Company, Contact) appears correctly in the timeline.
     * </p>
     *
     * @param testCase         The test case containing the entity value.
     * @param entityName       The name of the entity (e.g., "Deal", "Company", "Contact").
     * @param fieldXPath       The XPath for the field associated with the entity.
     * @param optionsXPath     The XPath to locate the dropdown options for the entity.
     * @param timelineTextXPath The XPath for validating the timeline text for the created activity.
     * @return true if the activity with the specified entity is correctly displayed in the timeline, otherwise false.
     */
    public boolean checkEntityInTimeline(final TestCase testCase, final String entityName, final String fieldXPath, final String optionsXPath, final String timelineTextXPath) {
        final String entityValue = testCase.input.getString(entityName.toLowerCase());
        int initialActivityCount = getCurrentActivityCount();

        configureColumnSettings(entityName, fieldXPath);

        int fieldIndex = getFieldPositionInCheckedList(entityName);
        if (fieldIndex == 0) {
            ExtentLogger.pass("Field index is 0, continuing with the activity creation.");
        }

        refresh();
        createActivityWithDropdownSelection(entityName, entityValue, optionsXPath);
        navigateToActivityList();
        sleep(2000);

        int currentActivityCount = getCurrentActivityCountAfterActivity(initialActivityCount);

        return validateActivityInTimeline(currentActivityCount, LIST_TITLE, timelineTextXPath, fieldIndex);
    }

    /**
     * <p>
     * Validates that a "Deal" entity appears correctly in the timeline.
     * </p>
     *
     * @param testCase The test case containing the entity value for the "Deal".
     * @return true if the "Deal" entity appears correctly in the timeline, otherwise false.
     */
    public boolean checkDealInTimeline(final TestCase testCase) {
        return checkEntityInTimeline(testCase, "Deal", map.get("crm.activity.form.list.deal.checkbox"), OPTIONS,
                map.get("crm.activity.deal.activity.name.timeline"));
    }

    /**
     * <p>
     * Validates that a "Company" entity appears correctly in the timeline.
     * </p>
     *
     * @param testCase The test case containing the entity value for the "Deal".
     * @return true if the "Deal" entity appears correctly in the timeline, otherwise false.
     */
    public boolean checkCompanyInTimeline(final TestCase testCase) {
        return checkEntityInTimeline(testCase, "Company", map.get("crm.activity.form.list.company.checkbox"), OPTIONS,
                map.get("crm.activity.company.activity.name.timeline"));
    }

    /**
     * <p>
     * Validates that a "Contact" entity appears correctly in the timeline.
     * </p>
     *
     * @param testCase The test case containing the entity value for the "Deal".
     * @return true if the "Deal" entity appears correctly in the timeline, otherwise false.
     */
    public boolean checkContactInTimeline(final TestCase testCase) {
        return checkEntityInTimeline(testCase, "Contacts", map.get("crm.activity.form.list.contact.checkbox"), OPTIONS,
                map.get("crm.activity.deal.activity.name.timeline"));
    }


    /**
     * <p>
     * Interacts with the Deal field, the Company
     * and Contact fields to validate that data prepopulation is happening as expected.
     * </p>
     *
     * @param testCase The name of the Deal being selected for the activity.
     * @return true if the Company and Contact fields are correctly populated based on the Deal, otherwise false.
     */
    public boolean prepopulateCompanyAndContactFromDeal(final TestCase testCase) {
        final String deal = testCase.input.getString("deal");

        click(findByXpath(map.get("crm.deal.icon")));
        ExtentLogger.pass("Deal module is clicked");

        final Collection<WebPageElement> dealList = findElementsByXpath(map.get("crm.activity.deal.list"));
        for (WebPageElement element : dealList) {
            if (getText(element).equals(deal)) {
                click(element);
                ExtentLogger.pass(element + "is clicked");
                break;
            }
        }

        final WebPageElement contactElement = findByXpath(map.get("crm.activity.deal.list.contact"));
        final String contactText = getText(contactElement);

        final WebPageElement companyElement = findByXpath(map.get("crm.activity.deal.list.company"));
        final String companyText = getText(companyElement);

        sleep(2000);
        click(findByXpath(map.get("crm.activity.icon")));
        click(getActivityButton());
        ExtentLogger.pass("Activity button is clicked");
        sleep(2000);

        click(getDealField());
        ExtentLogger.pass("deal field is clicked");
        send(getDealField(), deal);
        ExtentLogger.pass(deal + " is entered in deal field");
        Collection<WebPageElement> dealOptions = findElementsByXpath(map.get("crm.activity.deal.options"));
        for (WebPageElement option : dealOptions) {
            if (getText(option).equals(deal)) {
                click(option);
                ExtentLogger.pass(option + "is clicked");
                break;
            }
        }

        sleep(2000);

        final String prePopulatedContact = getText(findByXpath(map.get("crm.activity.form.contact.pre.populated")));

        boolean isContactValid = Objects.equals(contactText, prePopulatedContact);

        boolean isCompanyValid = true;
        if (!companyText.equals("Add Company")) {
            final String prePopulatedCompany = getAttribute(getCompanyField(), "value");
            isCompanyValid = Objects.equals(companyText, prePopulatedCompany);
        }

        return isContactValid && isCompanyValid;
    }

    /**
     * <p>
     * Interacts with the Contact field, the Company
     * field to validate that data pre population is happening as expected.
     * </p>
     *
     * @param testCase The name of the Contact being selected for the activity.
     * @return true if the Company and Contact fields are correctly populated based on the Deal, otherwise false.
     */
    public boolean prepopulateCompanyFromContact(final TestCase testCase) {
        final String contact = testCase.input.getString("contacts");

        click(findByXpath(map.get("crm.contact.icon")));

        final Collection<WebPageElement> contactList = findElementsByXpath(map.get("crm.activity.contact.list"));
        for (WebPageElement element : contactList) {
            if (getText(element).equals(contact)) {
                click(element);
                break;
            }
        }

        final WebPageElement companyElement = findByXpath(map.get("crm.activity.contact.list.company"));
        final String companyText = getText(companyElement);

        click(findByXpath(map.get("crm.activities.icon")));
        sleep(2000);
        click(getActivityButton());
        sleep(2000);

        click(getContactField());
        send(getContactField(), contact);
        Collection<WebPageElement> contactOptions = findElementsByXpath(map.get("crm.activity.contact.options"));
        for (WebPageElement option : contactOptions) {
            if (getText(option).equals(contact)) {
                click(option);
                break;
            }
        }

        sleep(2000);

        boolean isCompanyValid = true;
        if (!companyText.equals("Add Company")) {
            final String prePopulatedCompany = getAttribute(getCompanyField(), "value");
            isCompanyValid = Objects.equals(companyText, prePopulatedCompany);
        }

        return isCompanyValid;
    }
}