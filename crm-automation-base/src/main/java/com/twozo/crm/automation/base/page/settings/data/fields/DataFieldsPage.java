package com.twozo.crm.automation.base.page.settings.data.fields;

import com.twozo.crm.automation.base.page.settings.SettingsPage;
import com.twozo.crm.automation.base.page.settings.data.fields.field.Field;
import com.twozo.crm.automation.base.page.settings.data.fields.field.FieldAttribute;
import com.twozo.crm.automation.base.page.xpath.XPathBuilder;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.ArrayList;
import java.util.Collection;

public abstract class DataFieldsPage extends SettingsPage {

    public DataFieldsPage(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    /**
     * <p>
     * Retrieves the block XPath for a field based on its field name.
     * </p>
     *
     * @param field The name of the field.
     * @return The XPath string for the field block.
     */
    public String getFieldBlock(final String field) {
        return String.format(FieldAttribute.FIELD_BLOCK, field);
    }

    /**
     * <p>
     * Retrieves the block XPath for a field based on its field name.
     * </p>
     *
     * @param field The name of the field.
     * @return The XPath string for the field block.
     */
    public String getDependableBlock(final String field) {
        return String.format(FieldAttribute.DEPENDABLE_BLOCK, field);
    }

    protected WebPageElement getNonDraggableElement(final String fieldName) {
        return findByXpath(formatTwoStrings(getFieldBlock(fieldName), FieldAttribute.NON_DRAGGABLE));
    }

    protected WebPageElement getDraggableElement(final String fieldName) {
        return findByXpath(formatTwoStrings(getFieldBlock(fieldName), FieldAttribute.DRAGGABLE));
    }

    /**
     * <p>
     * Retrieves the {@link WebPageElement} of an active tab by its name.
     * </p>
     *
     * @param tabName The name of the tab.
     * @return A {@link WebPageElement} representing the active tab.
     */
    public WebPageElement getActiveTab(final String tabName) {
        final String activeTab = String.format("//*[@value='%s' and @aria-pressed='true']", tabName);

        waitTillVisible(activeTab);

        return findByXpath(activeTab);
    }

    public WebPageElement getNonDraggableIcon(final String divBlockName) {
        final String nonDraggableIcon = formatTwoStrings(divBlockName, FieldAttribute.NON_DRAGGABLE);

        waitTillVisible(nonDraggableIcon);

        return findByXpath(nonDraggableIcon);
    }

    public WebPageElement getAddSystemFieldsButton() {
        waitTillVisible(DataFieldsButton.ADD_SELECTED_FIELDS);

        return findByXpath(DataFieldsButton.ADD_SELECTED_FIELDS);
    }

    public WebPageElement getButton() {
        waitTillVisible(DataFieldsButton.ADD_SELECTED_FIELDS);

        return findByXpath(DataFieldsButton.ADD_SELECTED_FIELDS);
    }

    public WebPageElement getFieldElement(final String fieldName, final String elementName) {
        final String fieldElement = formatTwoStrings(getFieldBlock(fieldName), elementName);

        waitTillVisible(formatTwoStrings(getFieldBlock(fieldName), elementName));

        return findByXpath(fieldElement);
    }

    public WebPageElement getDependableFieldElement(final String fieldName, final String elementName) {
        final String dependableFieldElement = formatTwoStrings(getDependableBlock(fieldName), elementName);

        waitTillVisible(dependableFieldElement);

        return findByXpath(dependableFieldElement);
    }

    public Collection<WebPageElement> getDropdownChoices() {
        waitTillVisible("//*[@role='menu']");

        return findElementsByXpath("//*[@role='menu']//child::p");
    }

    public WebPageElement getAddViewCheckboxOf(final String fieldName) {
        final String addViewCheckboxOfFieldName = String.format(THREE_STRING_FORMAT, OPEN_PARENTHESIS, getFieldBlock(fieldName), FieldAttribute.ADD_VIEW_CHECKBOX);

        waitTillVisible(addViewCheckboxOfFieldName);

        return findByXpath(addViewCheckboxOfFieldName);
    }

    public WebPageElement getRequiredCheckboxOf(final String fieldName) {
        final String requiredCheckboxOfFieldName = String.format(THREE_STRING_FORMAT, OPEN_PARENTHESIS,
                getFieldBlock(fieldName), FieldAttribute.REQUIRED_CHECKBOX);

        waitTillVisible(requiredCheckboxOfFieldName);

        return findByXpath(requiredCheckboxOfFieldName);
    }

    /**
     * <p>
     * Retrieves the {@link Collection} of fields.
     * </p>
     *
     * @return A {@link Collection} of fields.
     */
    public Collection<WebPageElement> getFields() {
        waitTillVisible("//*[@data-rbd-droppable-id='field-list']");

        return findElementsByXpath("//*[@data-rbd-droppable-id='field-list']//*[@aria-label]");
    }

    public WebPageElement getFieldName(final String fieldName) {
        waitTillVisible("//div[@data-rbd-droppable-id='field-list']");

        return findByXpath(getFieldBlock(fieldName));
    }

    public WebPageElement getFieldType(final String fieldName, final String fieldType) {
        final String fieldTypeOfSpecificField = formatTwoStrings(getFieldBlock(fieldName), XPathBuilder.getXPathByText(fieldType));

        waitTillVisible(fieldTypeOfSpecificField);

        return findByXpath(fieldTypeOfSpecificField);
    }

    public WebPageElement getUpdateButtonOf(final String fieldName) {
        final String updateButtonOfField = formatTwoStrings(getFieldBlock(fieldName), FieldAttribute.UPDATE_BUTTON);

        waitTillVisible(updateButtonOfField);

        return findByXpath(updateButtonOfField);
    }

    public WebPageElement getLimitExceededForCustomField() {
        final String limitExceededForCustomField = XPathBuilder.getXPathByText
                ("Maximum limit of 10 custom fields per type has been reached");

        waitTillVisible(limitExceededForCustomField);

        return findByXpath(limitExceededForCustomField);
    }

    /**
     * <p>
     * Retrieves the "Add Selected Fields" button element.
     * </p>
     *
     * @return A WebPageElement representing the "Add Selected Fields" button.
     */
    public WebPageElement getAddSelectedFieldsButton() {
        waitTillVisible(DataFieldsButton.ADD_SELECTED_FIELDS);

        return findByXpath(DataFieldsButton.ADD_SELECTED_FIELDS);
    }

    /**
     * <p>
     * Retrieves the "Add Custom Field" button element.
     * </p>
     *
     * @return A WebPageElement representing the "Add Custom Field" button.
     */
    public WebPageElement getAddCustomFieldButton() {
        return findByText(DataFieldsButton.CUSTOM_FIELD);
    }

    public WebPageElement getRadioButtonOfChoice(final String choice) {
        final String xpathOfRadioButton = String.format(
                "//*[@role='menu']//*[text()='%s']//ancestor::div[3]/child::div[1]//*[@aria-label]", choice);

        waitTillVisible(xpathOfRadioButton);

        return findByXpath(xpathOfRadioButton);
    }

    public WebPageElement getChoicesButtonOfField(final String fieldName) {
        final String choicesButton = formatTwoStrings(getFieldBlock(fieldName), "//div[3]//p");

        waitTillVisible(choicesButton);

        return findByXpath(choicesButton);
    }

    public WebPageElement getChoicesButtonOfDependableField(final String fieldName) {
        final String choicesButton = formatTwoStrings(getDependableBlock(fieldName), "//div[3]//p");

        waitTillVisible(choicesButton);

        return findByXpath(choicesButton);
    }

    /**
     * <p>
     * Retrieves the menu block XPath for a specific field name.
     * </p>
     *
     * @param fieldName The name of the field.
     * @return The XPath string for the menu block of the specified field.
     */
    public String getMenuBlock(final String fieldName) {
        return String.format(FieldAttribute.MENU_BLOCK, fieldName);
    }

    /**
     * <p>
     * Retrieves the checkbox element for adding a system field to the list.
     * </p>
     *
     * @param fieldName The name of the system field.
     * @return A {@link WebPageElement} representing the checkbox element.
     */
    public WebPageElement getCheckboxOf(final String fieldName) {
        waitTillVisible(getMenuBlock(fieldName));

        return findByXpath(formatTwoStrings(getMenuBlock(fieldName), "//*[@type='checkbox']"));
    }

    private Collection<WebPageElement> getFieldsEnabledAs(final String addViewOrRequired) {
        final Collection<WebPageElement> fieldsPresent = new ArrayList<>();

        for (int i = 1; i <= getFields().size(); i++) {
            final String fieldBlock = String.format(FieldAttribute.BLOCK, i);
            final WebPageElement fieldElement = findByXpath(formatThreeStrings("(", fieldBlock, addViewOrRequired));

            if (isSelected(fieldElement)) {
                fieldsPresent.add(findByXpath(formatTwoStrings(fieldBlock,"//*[@aria-label]")));
            }
        }

        return fieldsPresent;
    }

    public Collection<WebPageElement> getFieldsEnabledAsAddView(){
        return getFieldsEnabledAs(FieldAttribute.ADD_VIEW_CHECKBOX);
    }

    public Collection<WebPageElement> getFieldsEnabledAsRequired(){
        return getFieldsEnabledAs(FieldAttribute.REQUIRED_CHECKBOX);
    }

}
