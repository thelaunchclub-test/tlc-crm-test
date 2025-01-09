package com.twozo.page.settings.tags;

import com.twozo.extent.report.reporter.logger.ExtentLogger;
import com.twozo.page.BasePage;
import com.twozo.page.settings.TestCase;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Interacts with the tag management features of a web application.
 * Uses {@link WebAutomationDriver} to perform actions like adding, updating, deleting, and verifying tags
 * in both "Record Tags" and "Email Template Tags" sections.
 *
 * <p>Main functionalities include:</p>
 * <ul>
 *   <li>Adding tags (`addTag`, `addEmailTag`)</li>
 *   <li>Updating tags (`updateTags`, `updateEmailTags`)</li>
 *   <li>Deleting tags (`deleteTag`, `deleteEmailTag`)</li>
 *   <li>Verifying tag existence and uniqueness (`verifyTagNameCannotBeDuplicated`, `isTagsAvailableInListView`)</li>
 * </ul>
 *
 * @author Navin Jones
 * @version 1.0
 * @see BasePage
 */
public class Tags extends BasePage {

    private static final String TAG_NAME = "tagName";
    private static final String VALUE_ENTERED = " is entered in the TagNameField";
    private static final String ADD_BTN_CLICKED = "Add button is clicked";
    private static final String TAGS_LIST_BLOCK = map.get("crm.settings.tags.list.block");
    private static final String TAGS_NAME_LIST = map.get("crm.settings,tags.list.tagName");


    public Tags(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    /**
     * Retrieves the {@link WebPageElement} for the "Email Template Tags" button on the tags page.
     *
     * @return {@link WebPageElement} for the "Email Template Tags" button
     */
    public WebPageElement getEmailTemplateButton() {
        return this.findByXpath(map.get("crm.settings.tags.email.template.button"));
    }

    /**
     * Retrieves the {@link WebPageElement} for the "Add New Tag" button on the tags page.
     *
     * @return {@link WebPageElement} for the "Add New Tag" button
     */
    public WebPageElement getAddNewTagButton() {
        return this.findByXpath(map.get("crm.settings.tags.addNewTag.button"));
    }

    /**
     * Retrieves the {@link WebPageElement} for the input field where the tag name can be entered.
     *
     * @return {@link WebPageElement} for the "Enter Tag Name" field
     */
    public WebPageElement getEnterTagNameField() {
        return this.findByXpath("//*[@placeholder='Enter Tag Name']");
    }

    /**
     * Retrieves the {@link WebPageElement} for the "Add" button used to submit a new tag.
     *
     * @return {@link WebPageElement} for the "Add" button
     */
    public WebPageElement getAddButton() {
        return this.findByXpath("//*[text()='Add']");
    }

    /**
     * Retrieves the {@link WebPageElement} for the "Update" icon used to modify an existing tag.
     *
     * @return {@link WebPageElement} for the "Update" icon
     */
    public WebPageElement getUpdate() {
        return this.findByXpath("(//button[contains(@class,'1q67rw')])[2]");
    }

    /**
     * Retrieves the {@link WebPageElement} for the error message displayed when an error occurs.
     *
     * @return {@link WebPageElement} for the error message
     */
    public WebPageElement errorMsgForMaximum() {
        return this.findByText("Max. of 255 characters are allowed");
    }

    public WebPageElement errorMsg() {
        return this.findByText("Error!");
    }

    /**
     * Retrieves the {@link WebPageElement} for the delete icon used to remove a tag.
     *
     * @return {@link WebPageElement} for the delete icon
     */
    public WebPageElement getDelete() {
        return this.findByXpath("(//button[contains(@class,'1q67rw')])[3]");
    }

    /**
     * Retrieves the {@link WebPageElement} for the "Update" button used to apply changes to an existing tag.
     *
     * @return {@link WebPageElement} for the "Update" button
     */
    public WebPageElement getUpdateButton() {
        return this.findByXpath("//*[text()='Update']");
    }

    /**
     * Retrieves the {@link WebPageElement} for the "Delete" button used to confirm the removal of a tag.
     *
     * @return {@link WebPageElement} for the "Delete" button
     */
    public WebPageElement getDeleteButton() {
        return this.findByXpath("//*[text()='Delete']");
    }

    /**
     * Retrieves the web element representing the message displayed after adding a tag.
     *
     * @return A WebPageElement representing the "You added a tag." message.
     */
    public  WebPageElement getAddTagMsg() {
        return findByXpath("//*[text()='You added a tag.']");
    }

    /**
     * Adds a tag in a regular context.
     *
     * @param testCase The test case containing the input data, including the tag name.
     * @return True if the tag is added successfully, false otherwise.
     */
    public boolean addTag(final TestCase testCase) {
        return addTagCommon(testCase, false);
    }

    /**
     * Adds a tag in a email context.
     *
     * @param testCase The test case containing the input data, including the tag name.
     * @return True if the tag is added successfully, false otherwise.
     */
    public boolean addEmailTag(final TestCase testCase) {
        return addTagCommon(testCase, true);
    }

    /**
     * Common logic for adding a tag or an email tag.
     *
     * @param testCase The test case containing the input tag name.
     * @param isEmailTag Flag to determine if it's an email tag addition.
     * @return true if the tag was successfully added, false otherwise.
     */
    private boolean addTagCommon(final TestCase testCase, boolean isEmailTag) {
        final String tagName = testCase.input.getString(TAG_NAME);

        if (isEmailTag) {
            click(getEmailTemplateButton());
        }
        click(getAddNewTagButton());
        ExtentLogger.pass("Add New Tag button is clicked");

        final Collection<String> availableTags = getAvailableTags();

        if (!availableTags.contains(tagName)) {
            addNewTag(tagName);
        }

        return isTagPresent(tagName);
    }

    /**
     * Fetches available tags from the specified list block XPath.
     *
     * @return A collection of available tag names.
     */
    private Collection<String> getAvailableTags() {
        final Collection<WebPageElement> tagOptions = findElementsByXpath(Tags.TAGS_LIST_BLOCK);
        final Collection<String> availableTags = new ArrayList<>();
        for (WebPageElement option : tagOptions) {
            availableTags.add(getText(option).trim());
        }
        return availableTags;
    }

    /**
     * Adds a new tag using the provided tag name.
     *
     * @param tagName The name of the tag to be added.
     */
    private void addNewTag(final String tagName) {
        send(getEnterTagNameField(), tagName);
        ExtentLogger.pass(tagName + VALUE_ENTERED);
        sleep(2000);
        click(getAddButton());
        ExtentLogger.pass(ADD_BTN_CLICKED);
        sleep(2000);
    }

    /**
     * Checks if a tag is present in the specified tag name list XPath.
     *
     * @param tagName The name of the tag to search for.
     * @return true if the tag is present, false otherwise.
     */
    private boolean isTagPresent(final String tagName) {
        final Collection<WebPageElement> tagElements = findElementsByXpath(Tags.TAGS_NAME_LIST);
        for (WebPageElement option : tagElements) {
            if (getText(option).trim().contains(tagName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Utility method to pause execution.
     * Replace with proper wait mechanisms (e.g., WebDriverWait) where possible.
     *
     * @param millis Duration to sleep in milliseconds.
     */
    private void sleep(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates a tag in the list for a regular context.
     *
     * @param testCase The test case containing the input data, including the tag name and updates.
     * @return True if the tag update succeeds, false otherwise.
     */
    public boolean updateTags(final TestCase testCase) {
        return updateTagCommon(testCase, false);
    }

    /**
     * Updates a tag in the list for a email context.
     *
     * @param testCase The test case containing the input data, including the tag name and updates.
     * @return True if the tag update succeeds, false otherwise.
     */
    public boolean updateEmailTags(final TestCase testCase) {
        return updateTagCommon(testCase, true);
    }

    /**
     * Deletes a tag in the list for a regular context.
     *
     * @param testCase The test case containing the input data, including the tag name and updates.
     * @return True if the tag update succeeds, false otherwise.
     */
    public boolean deleteTag(final TestCase testCase) {
        return deleteTagCommon(testCase, false);
    }

    /**
     * Deletes a tag in the list for a email context.
     *
     * @param testCase The test case containing the input data, including the tag name and updates.
     * @return True if the tag update succeeds, false otherwise.
     */
    public boolean deleteEmailTag(final TestCase testCase) {
        return deleteTagCommon(testCase, true);
    }

    /**
     * Common logic for updating a tag or email tag.
     *
     * @param testCase The test case containing the input data.
     * @param isEmailTag Flag to determine if it's an email tag update.
     * @return true if the tag was successfully updated, false otherwise.
     */
    private boolean updateTagCommon(final TestCase testCase, boolean isEmailTag) {
        final String tagName = testCase.input.getString(TAG_NAME);
        final String changes = testCase.input.getString("changes");

        if (isEmailTag) {
            click(getEmailTemplateButton());
        }

        ensureTagExists(tagName, isEmailTag);

        hover(new Element(LocatorType.XPATH, getTagNameBlock(tagName), true));
        ExtentLogger.pass(tagName + " is Hovered");
        click(getUpdate());
        ExtentLogger.pass("Update icon is clicked");

        updateTagName(changes);
        ExtentLogger.pass("The given " + changes + VALUE_ENTERED);

        return getAvailableTagsInListView().contains(changes);
    }

    /**
     * Common logic for deleting a tag or email tag.
     *
     * @param testCase The test case containing the input data.
     * @param isEmailTag Flag to determine if it's an email tag deletion.
     * @return true if the tag was successfully deleted, false otherwise.
     */
    private boolean deleteTagCommon(final TestCase testCase, boolean isEmailTag) {
        final String tagName = testCase.input.getString(TAG_NAME);

        if (isEmailTag) {
            click(getEmailTemplateButton());
        }

        ensureTagExists(tagName, isEmailTag);

        hover(new Element(LocatorType.XPATH, getTagNameBlock(tagName), true));
        ExtentLogger.pass(tagName + " is Hovered");
        click(getDelete());
        ExtentLogger.pass("Delete icon is clicked");

        confirmDeletion();

        return !getAvailableTagsInListView().contains(tagName);
    }

    /**
     * Ensures the tag exists; creates it if not present.
     *

     * @param tagName The name of the tag to ensure existence.
     * @param isEmailTag Flag to determine if it's an email tag.
     */
    private void ensureTagExists(final String tagName, boolean isEmailTag) {
        if (!isTagsAvailableInListView(tagName)) {
            createTag(tagName);
            ExtentLogger.pass(tagName + " is created successfully");
            if (isEmailTag) {
                click(getEmailTemplateButton());
            }
        }
    }

    /**
     * Updates the tag name with the specified changes.
     *
     * @param changes The new tag name to set.
     */
    private void updateTagName(final String changes) {
        click(getEnterTagNameField());
        sleep(2000);
        mouseActions.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).sendKeys(changes).perform();
        click(getUpdateButton());
        ExtentLogger.pass("Update button is clicked");
        sleep(2000);
    }

    /**
     * Confirms deletion by clicking the delete button.
     */
    private void confirmDeletion() {
        sleep(2000);
        click(getDeleteButton());
        ExtentLogger.pass("Delete button is clicked");
    }

    /**
     * Checks the existence of a tag in the tag list for a regular context.
     *
     * @param testCase The test case containing the input data, including the tag name.
     * @return True if the tag check succeeds, false otherwise.
     */
    public boolean checkTag(final TestCase testCase) {
        return checkTagCommon(testCase, false);
    }

    /**
     * Checks the existence of a tag in the tag list for a email context.
     *
     * @param testCase The test case containing the input data, including the tag name.
     * @return True if the tag check succeeds, false otherwise.
     */
    public boolean checkEmailTag(final TestCase testCase) {
        return checkTagCommon(testCase, true);
    }

    /**
     * Common method to check if a tag or email tag exists and validate maximum tag limit error.
     *
     * @param testCase The test case containing input data.
     * @param isEmailTag Flag to determine if it's an email tag.
     * @return true if the maximum tag limit error is displayed, false otherwise.
     */
    private boolean checkTagCommon(final TestCase testCase, boolean isEmailTag) {
        final String tagName = testCase.input.getString(TAG_NAME);

        if (isEmailTag) {
            click(getEmailTemplateButton());
        }

        click(getAddNewTagButton());
        ExtentLogger.pass("Add new button is clicked");

        final Collection<WebPageElement> tagOptions = findElementsByXpath(TAGS_NAME_LIST);
        for (WebPageElement option : tagOptions) {
            String tagText = option.getElementInformationProvider().getText().trim();

            if (!tagText.contains(tagName)) {
                send(getEnterTagNameField(), tagName);
                ExtentLogger.pass(tagName + VALUE_ENTERED);
            }
        }

        return isDisplayed(errorMsgForMaximum());
    }

    /**
     * Attempts to add a tag with the specified name from the test case. Assumes that the tag name does not already exist.
     *
     * @param testCase The test case containing the tag name to be added
     */
    public void isTagNameExists(final TestCase testCase) {
        final String tagName = testCase.input.getString(TAG_NAME);
        click(getAddNewTagButton());
        ExtentLogger.pass("Add new button is clicked");
        send(getEnterTagNameField(), tagName);
        ExtentLogger.pass(tagName + VALUE_ENTERED);
        click(getAddButton());
        ExtentLogger.pass(ADD_BTN_CLICKED);
    }

    /**
     * Attempts to add a tag with the specified name from the test case. Assumes that the tag name does not already exist.
     *
     * @param testCase The test case containing the tag name to be added
     */
    public void isEmailTagNameExists(final TestCase testCase) {
        final String tagName = testCase.input.getString(TAG_NAME);
        click(getAddNewTagButton());
        ExtentLogger.pass("Add new button is clicked");
        send(getEnterTagNameField(), tagName);
        ExtentLogger.pass(tagName + VALUE_ENTERED);
        click(getAddButton());
        ExtentLogger.pass(ADD_BTN_CLICKED);
    }

    /**
     * Verifies that a tag name cannot be duplicated by attempting to add it twice and checking for an error message.
     *
     * @param testCase The test case containing the tag name to verify
     * @return true if an error message is displayed indicating duplication; false otherwise
     */
    public boolean verifyTagNameCannotBeDuplicated(final TestCase testCase) {
        isTagNameExists(testCase);
        refresh();
        isTagNameExists(testCase);
        waitTillVisible("//*[text()='Error!']");
        return isDisplayed(errorMsg());
    }

    /**
     * Verifies that a tag name cannot be duplicated by attempting to add it twice and checking for an error message.
     *
     * @param testCase The test case containing the tag name to verify
     * @return true if an error message is displayed indicating duplication; false otherwise
     */
    public boolean verifyEmailTagNameCannotBeDuplicated(final TestCase testCase) {
        isEmailTagNameExists(testCase);
        refresh();
        isEmailTagNameExists(testCase);

        return isDisplayed(errorMsg());
    }

    /**
     * Constructs the XPath for locating a tag block element based on the tag name.
     *
     * @param tagName The name of the tag to locate
     * @return The XPath of the tag block element
     */
    protected String getTagNameBlock(final String tagName) {
        return String.format("(//*[contains(@class,'u4p24i')])//*[text()='%s']", tagName);
    }

    /**
     * Retrieves a collection of available tags displayed in the list view.
     *
     * @return A collection of tag names
     */
    public Collection<String> getAvailableTagsInListView() {
        final Collection<WebPageElement> tagElements = findElementsByXpath("//*[contains(@class,'u4p24i')]//p[contains(@class,'17l2x8q')]");
        final List<String> availableTags = new ArrayList<>();

        for (final WebPageElement tagElement : tagElements) {
            availableTags.add(getText(tagElement));
        }

        return availableTags;
    }

    /**
     * Checks if a specific tag is available in the list view.
     *
     * @param tagName The name of the tag to check for
     * @return true if the tag is present in the list view; false otherwise
     */
    public boolean isTagsAvailableInListView(final String tagName) {

        final Collection<String> availableTags = getAvailableTagsInListView();
        return availableTags.contains(tagName);
    }

    /**
     * Creates a new tag with the specified name.
     *
     * @param tagName The name of the tag to be created.
     */
    private void createTag(String tagName) {
        click(getAddNewTagButton());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(getEnterTagNameField());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        mouseActions.sendKeys(tagName).perform();
        click(getAddButton());
        refresh();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if the success message indicating an update operation is displayed on the page.
     *
     * @return true if the success message ("Success!") is displayed, false otherwise.
     */
    public boolean isUpdateMsg() {
        return isDisplayed(findByXpath("//*[text()='Success!']"));
    }

    /**
     * Checks if the message indicating a tag was successfully added is displayed on the page.
     *
     * @return true if the "Add Tag" success message is displayed, false otherwise.
     */
    public boolean isAddTagMsg() {
        return isDisplayed(getAddTagMsg());
    }
}