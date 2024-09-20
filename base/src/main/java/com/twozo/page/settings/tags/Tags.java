package com.twozo.page.settings.tags;

import com.twozo.extent.report.reporter.logger.ExtentLogger;
import com.twozo.page.BasePage;
import com.twozo.page.settings.TestCase;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;

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
    private static final String DELETE = "isDelete";
    private static final String UPDATE = "isUpdate";
    private static final String VALUE_ENTERED = " is entered in the TagNameField";
    private static final String ADD_BTN_CLICKED = "Add button is clicked";
    private static final String TAGS_LIST_BLOCK = "//*[@class='css-l5c1s3']";


    public Tags(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    /**
     * Retrieves the {@link WebPageElement} for the "Record Tags" button on the tags page.
     *
     * @return {@link WebPageElement} for the "Record Tags" button
     */
    public WebPageElement getRecordTagsButton() {
        return this.findByXpath("//*[text()='Record Tags']");
    }

    /**
     * Retrieves the {@link WebPageElement} for the "Email Template Tags" button on the tags page.
     *
     * @return {@link WebPageElement} for the "Email Template Tags" button
     */
    public WebPageElement getEmailTemplateButton() {
        return this.findByXpath("//*[text()='Email Template Tags']");
    }

    /**
     * Retrieves the {@link WebPageElement} for the "Add New Tag" button on the tags page.
     *
     * @return {@link WebPageElement} for the "Add New Tag" button
     */
    public WebPageElement getAddNewTagButton() {
        return this.findByXpath("//*[text()='Add New Tag']");
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
     * Retrieves the {@link WebPageElement} for the "Cancel" button, which cancels the tag creation or update.
     *
     * @return {@link WebPageElement} for the "Cancel" button
     */
    public WebPageElement getCancelButton() {
        return this.findByXpath("//*[text()='Cancel']");
    }

    /**
     * Retrieves the {@link WebPageElement} for the "Update" icon used to modify an existing tag.
     *
     * @return {@link WebPageElement} for the "Update" icon
     */
    public WebPageElement getUpdate() {
        return this.findByXpath("(//*[@class='MuiBox-root css-k008qs'])[15]");
    }

    /**
     * Retrieves the {@link WebPageElement} for the error message displayed when an error occurs.
     *
     * @return {@link WebPageElement} for the error message
     */
    public WebPageElement errorMsg() {
        return this.findByText("Error!");
    }

    /**
     * Retrieves the {@link WebPageElement} for the delete icon used to remove a tag.
     *
     * @return {@link WebPageElement} for the delete icon
     */
    public WebPageElement getDelete() {
        return this.findByXpath("(//*[@class='MuiBox-root css-k008qs'])[16]");
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
     * Checks if a tag with the specified name exists in the list of tags.
     *
     * @param tagName The name of the tag to search for
     * @return true if the tag is found; false otherwise
     */
    public boolean getTagBlock(final String tagName) {
        final Collection<WebPageElement> currencyOptions = findElementsByXpath(TAGS_LIST_BLOCK);
        for (WebPageElement option : currencyOptions) {
            String currencyText = option.getElementInformationProvider().getText().trim();

            if (currencyText.contains(tagName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds a new tag based on the provided test case. If the tag does not already exist, it will be created.
     *
     * @param testCase The test case containing the tag name to be added
     */
    public void addTag(final TestCase testCase) {
        final String tagName = testCase.input.getString(TAG_NAME);
        click(getAddNewTagButton());
        ExtentLogger.pass("Add new button is clicked");
        final Collection<WebPageElement> currencyOptions = findElementsByXpath(TAGS_LIST_BLOCK);
        for (WebPageElement option : currencyOptions) {
            String currencyText = option.getElementInformationProvider().getText().trim();

            if (!currencyText.contains(tagName)) {
                send(getEnterTagNameField(), tagName);
                ExtentLogger.pass(tagName + VALUE_ENTERED);
                click(getAddButton());
                ExtentLogger.pass(ADD_BTN_CLICKED);
            }
        }
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
     * Verifies that a tag name cannot be duplicated by attempting to add it twice and checking for an error message.
     *
     * @param testCase The test case containing the tag name to verify
     * @return true if an error message is displayed indicating duplication; false otherwise
     */
    public boolean verifyTagNameCannotBeDuplicated(final TestCase testCase) {
        isTagNameExists(testCase);
        refresh();
        isTagNameExists(testCase);

        return isDisplayed(errorMsg());
    }

    /**
     * Adds a new email tag based on the provided test case. If the tag does not already exist, it will be created.
     *
     * @param testCase The test case containing the tag name to be added
     */
    public void addEmailTag(final TestCase testCase) {
        final String tagName = testCase.input.getString(TAG_NAME);

        click(getEmailTemplateButton());
        click(getAddNewTagButton());
        ExtentLogger.pass("Add Email Template button is clicked");
        final Collection<WebPageElement> currencyOptions = findElementsByXpath(TAGS_LIST_BLOCK);
        for (WebPageElement option : currencyOptions) {
            String currencyText = option.getElementInformationProvider().getText().trim();

            if (!currencyText.contains(tagName)) {
                send(getEnterTagNameField(), tagName);
                ExtentLogger.pass(tagName + VALUE_ENTERED);
                click(getAddButton());
                ExtentLogger.pass(ADD_BTN_CLICKED);
            }
        }
    }

    /**
     * Updates an existing tag based on the specified test case. The tag will be hovered over, and its name will be updated with the provided changes.
     *
     * @param testCase The test case containing the tag name and the new value to update
     */
    public void updateTags(final TestCase testCase) {
        if (testCase.input.containsKey(UPDATE)) {
            testCase.input.optBoolean(UPDATE, false);
        }

        final String tagName = testCase.input.getString(TAG_NAME);
        final String changes = testCase.input.getString("changes");

        hover(new Element(LocatorType.XPATH, getTagNameBlock(tagName), true));

        ExtentLogger.pass(tagName + " is Hovered");
        click(getUpdate());
        ExtentLogger.pass("update icon is clicked");
        clear(getEnterTagNameField());
        send(getEnterTagNameField(), changes);
        ExtentLogger.pass("The given " + changes + VALUE_ENTERED);
        click(getUpdateButton());
        ExtentLogger.pass("Update button is clicked");
    }

    /**
     * Updates an existing email tag based on the specified test case. The tag will be selected, and its name will be updated with the provided changes.
     *
     * @param testCase The test case containing the tag name and the new value to update
     */
    public void updateEmailTags(final TestCase testCase) {
        if (testCase.input.containsKey(UPDATE)) {
            testCase.input.optBoolean(UPDATE, false);
        }

        final String tagName = testCase.input.getString(TAG_NAME);
        final String changes = testCase.input.getString("changes");

        click(getEmailTemplateButton());
        hover(new Element(LocatorType.XPATH, getTagNameBlock(tagName), true));
        ExtentLogger.pass(tagName + " is Hovered");
        click(getUpdate());
        ExtentLogger.pass("update icon is clicked");
        send(getEnterTagNameField(), changes);
        ExtentLogger.pass("The given " + changes + VALUE_ENTERED);
        click(getUpdateButton());
        ExtentLogger.pass("Update button is clicked");
    }

    /**
     * Deletes a tag based on the specified test case. The tag will be hovered over, and the delete action will be performed.
     *
     * @param testCase The test case containing the tag name to be deleted
     */
    public void deleteTag(final TestCase testCase) {
        if (testCase.input.containsKey(DELETE)) {
            testCase.input.optBoolean(DELETE, false);
        }

        final String tagName = testCase.input.getString(TAG_NAME);

        hover(new Element(LocatorType.XPATH, getTagNameBlock(tagName), true));
        ExtentLogger.pass(tagName + " is hovered");
        click(getDelete());
        ExtentLogger.pass("delete icon is clicked");
        click(getDeleteButton());
        ExtentLogger.pass("Delete button is clicked");
    }

    /**
     * Deletes an email tag based on the specified test case. The tag will be selected, and the delete action will be performed.
     *
     * @param testCase The test case containing the tag name to be deleted
     */
    public void deleteEmailTag(final TestCase testCase) {
        if (testCase.input.containsKey(DELETE)) {
            testCase.input.optBoolean(DELETE, false);
        }

        final String tagName = testCase.input.getString(TAG_NAME);

        click(getEmailTemplateButton());
        hover(new Element(LocatorType.XPATH, getTagNameBlock(tagName), true));
        ExtentLogger.pass(tagName + " is hovered");
        click(getDelete());
        ExtentLogger.pass("delete icon is clicked");
        click(getDeleteButton());
        ExtentLogger.pass("Delete button is clicked");
    }

    /**
     * Constructs the XPath for locating a tag block element based on the tag name.
     *
     * @param tagName The name of the tag to locate
     * @return The XPath of the tag block element
     */
    protected String getTagNameBlock(final String tagName) {
        int rowNumber = 1;
        while (!getText(findByXpath(String.format("((//*[@class='css-u4p24i'])[%d]/div/div/p)[1]", rowNumber))).contains(tagName)) {
            rowNumber++;
        }

        return String.format("(//*[@class='css-u4p24i'])[%d]", rowNumber);
    }

    /**
     * Retrieves a collection of available tags displayed in the list view.
     *
     * @return A collection of tag names
     */
    public Collection<String> getAvailableTagsInListView() {
        final Collection<WebPageElement> tagElements = findElementsByXpath("//*[@class='MuiTypography-root MuiTypography-body1 MuiTypography-noWrap css-yzwrlm']");
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
     * Checks if the error validation message is displayed.
     *
     * @return true if the error message is displayed; false otherwise
     */
    public boolean isErrorValidationMessageDisplayed() {
        return isDisplayed(errorMsg());
    }
}