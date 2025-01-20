package com.twozo.page.settings.sales.activities;

import com.twozo.page.url.URL;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SalesActivities extends BasePage {

    public SalesActivities(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    private WebPageElement getActivityTypeButton() {
        return this.findByXpath("//*[text()='Activity Type']");
    }

    private WebPageElement getEnableButton() {
        return this.findByXpath("//*[text()='Enable']");
    }

    private WebPageElement getDisabledButton() {
        return this.findByXpath("//*[text()='Disabled']");
    }

    private WebPageElement getActivityNameField() {
        return this.findByXpath("//*[contains(@class, '1pog434')]");
    }

    public WebPageElement getErrorMsg() {
        return this.findByXpath("//*[text()='Disabling the last active activity type is not allowed']");
    }

    private WebPageElement getSaveButton() {
        return this.findByXpath("//button[text()='Save']");
    }

    private WebPageElement disableButton() {
        return this.findByXpath("//*[contains(@class, '1m9pwf3')]");
    }

    public void switchToAddForm() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(findByText("Activity"));
    }

    /**
     * Waits for UI updates by introducing a short delay.
     * Replace this with an explicit wait mechanism to avoid hardcoded delays.
     */
    private void waitForUIUpdate(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("UI update wait interrupted", e);
        }
    }

    /**
     * Helper method to hover over an activity type and click the disable button.
     *
     * @param activityTypeName the name of the activity type to disable.
     */
    private void hoverAndClickDisable(final String activityTypeName) {
        hoverByXpath(String.format("//p[contains(text(),'%s')]//parent::div", activityTypeName));
        ExtentLogger.pass("The given Activity type is hovered");

        click(disableButton());
        ExtentLogger.pass("The Disable button is clicked");
    }

    /**
     * Helper method to clear the text field and enter new keys.
     *
     * @param keys the text to be entered.
     */
    private void clearAndSendKeys(String keys) {
        mouseActions.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).sendKeys(keys).perform();
    }

    /**
     * Checks if a specific activity type is present in the activity list.
     *
     * @param activityName the name of the activity type to check for.
     * @return true if the activity type is found in the list, false otherwise.
     */
    private boolean isActivityTypeInList(final String activityName) {
        waitForUIUpdate(2000);
        return fetchAllActivityTypes()
                .stream()
                .anyMatch(activityType -> activityType.contains(activityName));
    }

    /**
     * Retrieves a list of all activity type names currently displayed.
     *
     * @return a collection of activity type names as strings.
     */
    public Collection<String> fetchAllActivityTypes() {
        final Collection<WebPageElement> activityTypeElements = findElementsByXpath("//div[contains(@class,'95g4uk')]//div[2]/p");
        final List<String> availableActivityTypes = new ArrayList<>();

        for (final WebPageElement activityElement : activityTypeElements) {
            availableActivityTypes.add(activityElement.getElementInformationProvider().getText().trim());
        }

        availableActivityTypes.forEach(System.out::println);
        return availableActivityTypes;
    }

    /**
     * Adds a new activity type using the provided test case data.
     *
     * @param testCase the test case containing input data, including the activity name and icon.
     * @return true if the activity type is successfully added and visible in the list, false otherwise.
     */
    public boolean addNewActivityType(final TestCase testCase) {
        final String activityIconName = testCase.input.getString("ActivityTypeIcon");
        final String activityName = testCase.input.getString("activityTypeName");

        click(getActivityTypeButton());
        waitForUIUpdate(2000);

        send(getActivityNameField(), activityName);

        final ActivityIcon activityIcon = ActivityIcon.fromName(activityIconName);
        waitForUIUpdate(2000);

        click(findByXpath(activityIcon.getIconXPath()));
        click(getSaveButton());
        waitForUIUpdate(2000);

        if (isActivityTypeInList(activityName)) {
            ExtentLogger.pass("The given Activity type is present.");
            return true;
        }

        return false;
    }

    /**
     * Updates the name of an existing activity type.
     *
     * @param testCase the test case containing input data, including the activity type name and changes to apply.
     * @return true if the updated activity type is visible in the list, false otherwise.
     */
    public boolean updateActivityType(final TestCase testCase) {
        final String activityTypeName = testCase.input.getString("activityTypeName");
        final String changes = testCase.input.getString("changes");

        click(findByXpath(String.format("//p[contains(text(),'%s')]//parent::div", activityTypeName)));
        ExtentLogger.pass("The given Activity name is clicked");

        click(getActivityNameField());
        ExtentLogger.pass("The Activity name field is clicked");

        clearAndSendKeys(changes);
        ExtentLogger.pass("The Activity name is updated with changes");

        click(getSaveButton());
        ExtentLogger.pass("The Save button is clicked");

        return isActivityTypeInList(changes);
    }

    /**
     * Disables an activity type by hovering and clicking the disable button.
     *
     * @param testCase the test case containing input data, including the activity type name.
     * @return true if the activity type remains in the list (disabled), false otherwise.
     */
    public boolean disableActivityType(final TestCase testCase) {
        final String activityTypeName = testCase.input.getString("activityType");

        if (testCase.input.optBoolean("isDisable", false)) {
            hoverAndClickDisable(activityTypeName);
            refresh();

            click(getDisabledButton());
            ExtentLogger.pass("The Disabled button was clicked");
        }

        return isActivityTypeInList(activityTypeName);
    }

    /**
     * Enables a disabled activity type.
     *
     * @param testCase the test case containing input data, including the activity type name.
     * @return true if the activity type is re-enabled and visible in the list, false otherwise.
     */
    public boolean enableActivityType(final TestCase testCase) {
        final String activityTypeName = testCase.input.getString("activityType");

        if (testCase.input.optBoolean("isEnable", false)) {
            click(getDisabledButton());
            ExtentLogger.pass("The Disabled button is clicked");

            hoverAndClickDisable(activityTypeName);

            click(getEnableButton());
            ExtentLogger.pass("The Enable button is clicked");
        }

        return isActivityTypeInList(activityTypeName);
    }

    /**
     * Adds an activity type and checks if the "Activity type already exists" message appears.
     *
     * @return true if the error message is displayed, false otherwise.
     */
    public boolean addActivityTypeAndCheck() {
        addActivity("Search", "sung");
        waitForUIUpdate(3000);
        addActivity("Search", "sung");

        return isDisplayed(findByXpath("//*[text()='Activity type already exists']"));
    }

    /**
     * Adds an activity type using the provided icon name and activity name.
     *
     * @param activityIconName the name of the activity icon.
     * @param activityName     the name of the activity type.
     */
    private void addActivity(String activityIconName, String activityName) {
        waitForUIUpdate(2000);
        click(getActivityTypeButton());
        send(getActivityNameField(), activityName);

        ActivityIcon activityIcon = ActivityIcon.fromName(activityIconName);
        click(findByXpath(activityIcon.getIconXPath()));
        click(getSaveButton());
    }

    /**
     * Disables all activity types present in the list.
     *
     * @return true if the error message is displayed after disabling all, false otherwise.
     */
    public boolean disableAll() {
        final Collection<WebPageElement> webPageElements = findElementsByXpath("//div[contains(@class,'95g4uk')]");

        if (webPageElements.isEmpty()) {
            return false;
        }

        for (int i = 1; i <= webPageElements.size(); i++) {
            final String elementXpath = "(//div[contains(@class,'95g4uk')])[1]";
            hoverByXpath(elementXpath);
            ExtentLogger.pass("The element is hovered");
            click(disableButton());
            ExtentLogger.pass("The given element is Disabled");
            waitForUIUpdate(3000);
        }

        return isDisplayed((getErrorMsg()));
    }

    /**
     * Checks if all default activity types are present in the activity list.
     *
     * @return true if all expected activity types are present, false otherwise.
     */
    public boolean checkDefaultActivityTypes() {
        List<String> expectedActivityTypes = Arrays.asList("Call", "Mail", "Meeting", "Task", "Lunch", "Quote");
        Collection<String> actualActivityTypes = fetchAllActivityTypes();

        if (!actualActivityTypes.containsAll(expectedActivityTypes)) {
            expectedActivityTypes.stream()
                    .filter(expected -> !actualActivityTypes.contains(expected))
                    .forEach(missing -> System.out.println("Activity Type Missing: " + missing));
            return false;
        }

        System.out.println("All expected activity types are present.");
        return true;
    }

    /**
     * Fetches all activity types present in the add form.
     *
     * @return a list of activity type names.
     */
    private List<String> fetchAddFormActivityTypes() {
        return findElementsByXpath("//button[@class='MuiBox-root twozo-css-prefix-0']").stream()
                .map(element -> getAttribute(element, "aria-label"))
                .collect(Collectors.toList());
    }

    /**
     * Verifies that all activity types in the main list match the activity types in the add form.
     *
     * @return true if the activity types match, false otherwise.
     */
    public boolean verifyActivityTypesInAddForm() {
        waitForUIUpdate(3000);

        Collection<String> actualActivityTypes = fetchAllActivityTypes();
        webNavigator.to(URL.ACTIVITIES);
        switchToAddForm();

        List<String> addFormActivityTypes = fetchAddFormActivityTypes();

        System.out.println(actualActivityTypes);
        System.out.println(addFormActivityTypes);

        return actualActivityTypes.equals(addFormActivityTypes);
    }

    /**
     * Tries to save an activity type without selecting an icon.
     *
     * @param testCase the test case containing input data, including the activity type name.
     * @return true if the error message is displayed, false otherwise.
     */
    public boolean saveWithoutIconSelection(final TestCase testCase) {
        String activityTypeName = testCase.input.getString("activityTypeName");

        try {
            click(getActivityTypeButton());
            ExtentLogger.pass("Add Activity type button is clicked");
            send(getActivityNameField(), activityTypeName);
            ExtentLogger.pass("The given value is entered in the activity name field");

            return isDisabledButton();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Tries to save an activity type without providing a name.
     *
     * @param testCase the test case containing input data, including the activity type icon.
     * @return true if the error message is displayed, false otherwise.
     */
    public boolean saveWithoutName(final TestCase testCase) {
        String activityTypeIcon = testCase.input.getString("activityTypeIcon");
        ActivityIcon activityIcon = ActivityIcon.fromName(activityTypeIcon);

        try {
            click(getActivityTypeButton());
            ExtentLogger.pass("Add Activity Type button is clicked");
            click(findByXpath(activityIcon.getIconXPath()));
            ExtentLogger.pass("The given Activity Icon is selected");

            return isDisabledButton();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Tries to edit an activity type without making changes.
     *
     * @param testCase the test case containing input data, including the activity type name.
     * @return true if the save button is disabled, false otherwise.
     */
    public boolean editWithoutChanges(final TestCase testCase) {
        String activityTypeName = testCase.input.getString("activityType");

        try {
            waitForUIUpdate(2000);
            click(findByXpath(String.format("//p[contains(text(),'%s')]//parent::div", activityTypeName)));
            ExtentLogger.pass("The given Activity name is clicked");

            return isDisabledButton();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if the save button is disabled (e.g., due to missing required inputs).
     *
     * @return true if the save button is disabled, false otherwise.
     */
    private boolean isDisabledButton() {
        return isDisplayed(findElement(new Element(LocatorType.XPATH, "//button[contains(@style, 'color: rgba(255, 255, 255, 0.6);')]", true)));
    }
}
