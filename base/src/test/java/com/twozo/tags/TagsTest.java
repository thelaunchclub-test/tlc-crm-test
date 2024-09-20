package com.twozo.tags;

import com.twozo.BaseTest;

import com.twozo.commons.cookie.BrowserCookie;

import com.twozo.page.homepage.HomePage;
import com.twozo.page.settings.JsonFields;
import com.twozo.page.settings.JsonFileReader;
import com.twozo.page.settings.TestCase;
import com.twozo.page.settings.tags.Tags;
import com.twozo.page.url.settings.SettingsURL;

import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.driver.service.WebNavigator;

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Contains test cases for testing the "Tags" functionality in the web application.
 * <p>
 * Tests are data-driven and use data providers to fetch test data from JSON files.
 * Each test verifies the expected outcome using assertions.
 *
 * @author Navin Jones
 * @version 1.0
 * @see BaseTest
 */
public class TagsTest extends BaseTest {

    private WebAutomationDriver webAutomationDriver;
    WebNavigator webNavigator;
    Tags tags;

    /**
     * Sets up the test environment before each test method is executed.
     * It initializes the web driver, navigates to the Tags settings page,
     * adds session cookies, maximizes the browser window, and sets implicit wait time.
     */
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
        webNavigator.to(SettingsURL.TAGS);
        automationDriver.getWebWindowHandler().maximize();
        homePage = HomePage.getInstance(automationDriver);
        tags = new Tags(automationDriver);
    }

    /**
     * Data provider for adding tags.
     * Fetches test data from the JSON file specified by {@link JsonFields#TAGS}.
     *
     * @return 2D array of {@link TestCase} objects containing test data for adding tags.
     */
    @DataProvider(name = "addTag")
    public static TestCase[][] addTag() {
        return new JsonFileReader().getTestCases(JsonFields.TAGS);
    }

    /**
     * Data provider for updating tags.
     * Fetches test data from the JSON file specified by {@link JsonFields#UPDATE_TAGS}.
     *
     * @return 2D array of {@link TestCase} objects containing test data for updating tags.
     */
    @DataProvider(name = "updateTag")
    public static TestCase[][] tagsUpdate() {
        return new JsonFileReader().getTestCases(JsonFields.UPDATE_TAGS);
    }

    /**
     * Data provider for checking duplicate tags.
     * Fetches test data from the JSON file specified by {@link JsonFields#DUPLICATE}.
     *
     * @return 2D array of {@link TestCase} objects containing test data for duplicate tag checking.
     */
    @DataProvider(name = "duplicate")
    public static TestCase[][] checkDuplicateTags() {
        return new JsonFileReader().getTestCases(JsonFields.DUPLICATE);
    }

    /**
     * Data provider for deleting tags.
     * Fetches test data from the JSON file specified by {@link JsonFields#DELETE_TAGS}.
     *
     * @return 2D array of {@link TestCase} objects containing test data for deleting tags.
     */
    @DataProvider(name = "deleteTag")
    public static TestCase[][] deleteTag() {
        return new JsonFileReader().getTestCases(JsonFields.DELETE_TAGS);
    }

    /**
     * Data provider for adding maximum number of tags.
     * Fetches test data from the JSON file specified by {@link JsonFields#MAXIMUM}.
     *
     * @return 2D array of {@link TestCase} objects containing test data for maximum tag limit checking.
     */
    @DataProvider(name = "maximum")
    public static TestCase[][] maximumTag() {
        return new JsonFileReader().getTestCases(JsonFields.MAXIMUM);
    }

    /**
     * Tests the addition of a new tag using the provided test data.
     * Asserts that the tag is available in the list view after addition.
     *
     * @param testCase The {@link TestCase} containing the test data for adding a tag.
     */
    @Test(dataProvider = "addTag")
    public void addTag(final TestCase testCase) {
        tags.addTag(testCase);
        Assert.assertTrue(tags.isTagsAvailableInListView(testCase.input.getString("tagName")));
    }

    /**
     * Tests the update of an existing tag using the provided test data.
     * Asserts that the updated tag is available in the list view.
     *
     * @param testCase The {@link TestCase} containing the test data for updating a tag.
     */
    @Test(dataProvider = "updateTag", priority = 1)
    public void updateTag(final TestCase testCase) {
        tags.updateTags(testCase);
        Assert.assertTrue(tags.isTagsAvailableInListView(testCase.input.getString("changes")));
    }

    /**
     * Tests the deletion of a tag using the provided test data.
     * Asserts that the tag is not available in the list view after deletion.
     *
     * @param testCase The {@link TestCase} containing the test data for deleting a tag.
     */
    @Test(dataProvider = "deleteTag", priority = 2)
    public void deleteTag(final TestCase testCase) {
        tags.deleteTag(testCase);
        Assert.assertFalse(tags.isTagsAvailableInListView(testCase.input.getString("tagName")));
    }

    /**
     * Tests that a duplicate tag name cannot be added using the provided test data.
     * Asserts that the tag name duplication is not allowed.
     *
     * @param testCase The {@link TestCase} containing the test data for checking duplicate tags.
     */
    @Test(dataProvider = "duplicate", priority = 3)
    public void checkDuplicateTagName(final TestCase testCase) {
        Assert.assertTrue(tags.verifyTagNameCannotBeDuplicated(testCase));
    }

    /**
     * Tests adding the maximum number of tags using the provided test data.
     * Asserts that an error validation message is displayed when the limit is exceeded.
     *
     * @param testCase The {@link TestCase} containing the test data for adding maximum tags.
     */
    @Test(dataProvider = "maximum", priority = 4)
    public void addMaximumTagName(final TestCase testCase) {
        tags.addTag(testCase);
        Assert.assertTrue(tags.isErrorValidationMessageDisplayed());
    }

    /**
     * Tests the addition of a new email tag using the provided test data.
     * Asserts that the email tag is present in the tag block.
     *
     * @param testCase The {@link TestCase} containing the test data for adding an email tag.
     */
    @Test(dataProvider = "addTag", priority = 5)
    public void addEmailTag(final TestCase testCase) {
        tags.addEmailTag(testCase);
        Assert.assertTrue(tags.getTagBlock(testCase.input.getString("tagName")));
    }

    /**
     * Tests the update of an existing email tag using the provided test data.
     * This test case does not contain an assertion since it's assumed the update is verified elsewhere.
     *
     * @param testCase The {@link TestCase} containing the test data for updating an email tag.
     */
    @Test(dataProvider = "updateTag", priority = 6)
    public void updateEmailTag(final TestCase testCase) {
        tags.updateEmailTags(testCase);
    }

    /**
     * Tests the deletion of an email tag using the provided test data.
     * Asserts that the email tag is not available in the list view after deletion.
     *
     * @param testCase The {@link TestCase} containing the test data for deleting an email tag.
     */
    @Test(dataProvider = "deleteTag", priority = 7)
    public void deleteEmailTag(final TestCase testCase) {
        tags.deleteEmailTag(testCase);
        Assert.assertFalse(tags.isTagsAvailableInListView(testCase.input.getString("tagName")));
    }

    /**
     * Tests that a duplicate email tag name cannot be added using the provided test data.
     * Asserts that the email tag name duplication is not allowed.
     *
     * @param testCase The {@link TestCase} containing the test data for checking duplicate email tags.
     */
    @Test(dataProvider = "duplicate", priority = 8)
    public void checkDuplicateEmailTagName(final TestCase testCase) {
        tags.addEmailTag(testCase);
        Assert.assertTrue(tags.verifyTagNameCannotBeDuplicated(testCase));
    }

    /**
     * Tests adding the maximum number of email tags using the provided test data.
     * Asserts that an error validation message is displayed when the limit is exceeded.
     *
     * @param testCase The {@link TestCase} containing the test data for adding maximum email tags.
     */
    @Test(dataProvider = "maximum", priority = 9)
    public void addMaximumEmailTagName(final TestCase testCase) {
        tags.addEmailTag(testCase);
        Assert.assertTrue(tags.isErrorValidationMessageDisplayed());
    }
}