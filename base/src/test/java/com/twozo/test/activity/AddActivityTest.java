package com.twozo.test.activity;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.page.activity.AddActivityForm;
import com.twozo.page.activity.JsonFields;
import com.twozo.page.activity.JsonFileReader;
import com.twozo.page.activity.TestCase;
import com.twozo.page.homepage.HomePage;
import com.twozo.page.url.settings.SettingsURL;
import com.twozo.test.BaseTest;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.driver.service.WebNavigator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddActivityTest extends BaseTest {

    AddActivityForm activityForm;
    WebNavigator webNavigator;
    WebAutomationDriver automationDriver;

    @BeforeMethod
    public void beforeMethod() {
        automationDriver = WebAutomationDriver.get();
        webNavigator = automationDriver.getWebNavigator();
        webNavigator.to(link);

        for (final BrowserCookie cookie : cookies) {
            automationDriver.getSessionCookie().addCookie(cookie);
        }

        automationDriver.getWebWindowHandler().maximize();
        automationDriver.getImplicitWaitHandler().implicitWait(Duration.ofSeconds(10));
        webNavigator.to(SettingsURL.ACTIVITIES);
        HomePage.getInstance(automationDriver);
        activityForm = new AddActivityForm(automationDriver);
    }

    @AfterMethod
    public void close() {
        automationDriver.close();
    }

    @DataProvider(name = "activityTitle")
    public static TestCase[][] addTitle() {
        return new JsonFileReader().getTestCases(JsonFields.TITLE);
    }

    @DataProvider(name = "guests")
    public static TestCase[][] checkIcon() {
        return new JsonFileReader().getTestCases(JsonFields.GUESTS);
    }

    /**
     * Verifies the visibility of the activity form.
     */
    @Test(priority = 1)
    public void checkActivityFormVisibility() {
        Assert.assertTrue(activityForm.verifyAddFormVisible());
    }

    /**
     * Verifies the behavior when attempting to save an activity without any values entered.
     * Checks whether the list of activities is updated after attempting to save an incomplete form.
     */
    @Test(priority = 2)
    public void saveActivityWithoutValues() {
        Assert.assertTrue(activityForm.getListOfCollection());
    }

    /**
     * Verifies the functionality of closing or cancelling the activity form.
     */
    @Test(priority = 3)
    public void cancelActivityForm() {
        Assert.assertTrue(activityForm.isActivityFormClosable());
    }

    /**
     * Verifies the default state of the fields in the activity form.
     */
    @Test(priority = 4)
    public void checkDefault() {
        Assert.assertTrue(activityForm.checkDefaultField());
    }

    /**
     * Verifies the "Save and New" functionality in the activity form.
     * Ensures that when an activity is saved using the "Save and New" option, the form is saved and a new form is displayed.
     */
    @Test(priority = 5)
    public void getSaveAndNewTest() {
        Assert.assertTrue(activityForm.saveWithSaveAndNew() && activityForm.isActivityAddFormVisible());
    }

    /**
     * Verifies if the activity is marked as completed.
     * Checks whether the activity is correctly marked as "Completed" after the required actions are performed.
     * The test will pass if the activity's status is successfully updated to "Completed".
     */
    @Test(priority = 6)
    public void getMarkedAsCompleted() {
        Assert.assertTrue(activityForm.getMarkedAsCompleted());
    }

    /**
     * Verifies the functionality of adding a title to an activity.
     */
    @Test(dataProvider = "activityTitle", priority = 7)
    public void addTitleTest(final TestCase testCase) {
        Assert.assertTrue(activityForm.addTitleToActivity(testCase));
    }

    /**
     * Verifies the default activity type icon in the activity form.
     */
    @Test(priority = 8)
    public void checkDefaultActivityIcon() {
        Assert.assertTrue(activityForm.checkDefaultActivityType());
    }

    /**
     * Verifies the activity type icon based on provided test data.
     */
    @Test(dataProvider = "activityTitle", priority = 9)
    public void checkActivityIcon(final TestCase testCase) {
        Assert.assertTrue(activityForm.checkActivityType(testCase));
    }

    /**
     * Verifies the functionality of changing the activity type icon based on provided test data.
     */
    @Test(dataProvider = "activityTitle", priority = 10)
    public void changeActivityIcon(final TestCase testCase) {
        Assert.assertTrue(activityForm.changeActivityType(testCase));
    }

    /**
     * Verifies that the activity date is correctly displayed with the start time.
     */
    @Test(priority = 11)
    public void getDateWithStartTime() {
        Assert.assertTrue(activityForm.validateActivityStartDate());
    }

    /**
     * Verifies that the activity date is correctly displayed with the end time.
     */
    @Test(priority = 12)
    public void getDateWithEndTime() {
        Assert.assertTrue(activityForm.validateActivityEndDate());
    }

    /**
     * Verifies that the activity date is correctly displayed.
     */
    @Test(priority = 13)
    public void testStatusValidationForRandomDate() {
        Assert.assertTrue(activityForm.validateActivityStatusByDate());
    }

    /**
     * Verifies the default availability status of the activity form.
     */
    @Test(priority = 14)
    public void checkDefaultAvailability() {
        Assert.assertTrue(activityForm.verifyDefaultAvailabilityStatus());
    }

    /**
     * Verifies the functionality of changing the availability status based on the provided test data.
     */
    @Test(dataProvider = "activityTitle", priority = 15)
    public void changeAvailabilityStatus(final TestCase testCase) {
        Assert.assertTrue(activityForm.validateAvailabilityStatus(testCase));
    }


    /**
     * Verifies the default reminder status in the activity form.
     */
    @Test(priority = 16)
    public void checkDefaultReminder() {
        Assert.assertTrue(activityForm.verifyDefaultReminderStatus());
    }

    /**
     * Verifies the functionality of changing the reminder status based on provided test data.
     */
    @Test(dataProvider = "activityTitle", priority = 17)
    public void changeReminderStatus(final TestCase testCase) {
        Assert.assertTrue(activityForm.validateReminderStatus(testCase));
    }

    /**
     * Verifies the default priority status in the activity form.
     */
    @Test(priority = 18)
    public void checkDefaultPriority() {
        Assert.assertTrue(activityForm.verifyDefaultPriorityStatus());
    }

    /**
     * Verifies the functionality of changing the priority status based on provided test data.
     */
    @Test(dataProvider = "activityTitle", priority = 19)
    public void changePriorityStatus(final TestCase testCase) {
        Assert.assertTrue(activityForm.validatePriorityStatus(testCase));
    }

    /**
     * Verifies the default sales owner in the activity form.
     */
    @Test(priority = 20)
    public void checkDefaultSalesOwner() {
        Assert.assertTrue(activityForm.verifyDefaultAssignToUserStatus());
    }

    /**
     * Verifies the functionality of adding a sales owner as a collaborator in the activity form.
     */
    @Test(dataProvider = "activityTitle", priority = 21)
    public void addSalesOwnerToCollaborators(final TestCase testCase) {
        Assert.assertTrue(activityForm.validateCollaboratorField(testCase));
    }

    /**
     * Verifies the functionality of adding a guest to the activity form.
     */
    @Test(dataProvider = "activityTitle", priority = 21)
    public void addGuest(final TestCase testCase) {
        Assert.assertTrue(activityForm.verifyGuestSelection(testCase));
    }

    /**
     * Verifies the functionality of adding a guest with an email to the activity form.
     */
    @Test(dataProvider = "activityTitle", priority = 22)
    public void addGuestWithEmail(final TestCase testCase) {
        Assert.assertTrue(activityForm.getGuestWithEmail(testCase));
    }

    /**
     * Verifies the functionality of adding multiple guests to the activity form.
     */
    @Test(dataProvider = "guests", priority = 23)
    public void addMultipleGuests(final TestCase testcase) {
        Assert.assertTrue(activityForm.addMultipleGuestsToActivity(testcase));
    }

    /**
     * Verifies the functionality of adding a guest with invalid data to the activity form.
     */
    @Test(priority = 24)
    public void addGuestWithInvalidData() {
        Assert.assertTrue(activityForm.validateInvalidEmailInGuestField());
    }

    /**
     * Verifies the functionality of adding a location to the activity form based on provided test data.
     */
    @Test(dataProvider = "activityTitle", priority = 25)
    public void addLocation(final TestCase testCase) {
        Assert.assertTrue(activityForm.getLocationStatus(testCase));
    }

    /**
     * Verifies the functionality of adding a description to the activity form based on provided test data.
     */
    @Test(dataProvider = "activityTitle", priority = 26)
    public void addDescription(final TestCase testCase) {
        Assert.assertTrue(activityForm.getDescriptionStatus(testCase));
    }

    /**
     * Verifies the functionality of adding a private note to the activity form based on provided test data.
     */
    @Test(dataProvider = "activityTitle", priority = 27)
    public void addPrivateNote(final TestCase testCase) {
        Assert.assertTrue(activityForm.getPrivateNote(testCase));
    }

    /**
     * Verifies the functionality of adding a deal to the activity form based on provided test data.
     */
    @Test(dataProvider = "activityTitle", priority = 28)
    public void addDeal(final TestCase testCase) {
        Assert.assertTrue(activityForm.addDealToActivity(testCase));
    }

    /**
     * Verifies that the activity name appears correctly in the deal timeline.
     */
    @Test(dataProvider = "activityTitle", priority = 29)
    public void testActivityNameInDeal(final TestCase testCase) {
        Assert.assertTrue(activityForm.checkDealInTimeline(testCase));
    }

    /**
     * Verifies the functionality of adding a company to the activity form based on provided test data.
     */
    @Test(dataProvider = "activityTitle", priority = 30)
    public void addCompany(final TestCase testCase) {
        Assert.assertTrue(activityForm.addCompanyToActivity(testCase));
    }

    /**
     * Verifies that the activity name appears correctly in the company timeline.
     */
    @Test(dataProvider = "activityTitle", priority = 31)
    public void testActivityNameInCompany(final TestCase testCase) {
        Assert.assertTrue(activityForm.checkCompanyInTimeline(testCase));
    }

    /**
     * Verifies the functionality of adding a contact to the activity form based on provided test data.
     */
    @Test(dataProvider = "activityTitle", priority = 32)
    public void addContact(final TestCase testCase) {
        Assert.assertTrue(activityForm.addContactToActivity(testCase));
    }

    /**
     * Verifies that the activity name appears correctly in the contact timeline.
     */
    @Test(dataProvider = "activityTitle", priority = 33)
    public void testActivityNameInContact(final TestCase testCase) {
        Assert.assertTrue(activityForm.checkContactInTimeline(testCase));
    }

    /**
     * Verifies the functionality of checking deal details in the activity form based on provided test data.
     */
    @Test(dataProvider = "activityTitle", priority = 34)
    public void verifyCompanyAndContactAutoPopulationFromDeal(final TestCase testCase) {
        Assert.assertTrue(activityForm.prepopulateCompanyAndContactFromDeal(testCase));
    }

    /**
     * Verifies the functionality of checking contact details in the activity form based on provided test data.
     */
    @Test(dataProvider = "activityTitle", priority = 35)
    public void verifyCompanyAutoPopulationFromContact(final TestCase testCase) {
        Assert.assertTrue(activityForm.prepopulateCompanyFromContact(testCase));
    }
}