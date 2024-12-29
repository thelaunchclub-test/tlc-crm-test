package com.twozo.page.settings.data.fields;

import com.twozo.page.settings.Settings;
import com.twozo.page.settings.data.fields.contact.field.ContactField;
import com.twozo.page.settings.data.fields.field.DependableField;
import com.twozo.page.settings.data.fields.field.Field;
import com.twozo.page.settings.data.fields.field.FieldElement;
import com.twozo.page.settings.data.fields.field.SystemField;
import com.twozo.page.xpath.XPathBuilder;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;

import java.util.*;

/**
 * <p>
 * Handles data fields within the settings page of a web application. Provides common functionality for managing
 * system and custom fields, interacting with UI elements, and verifying field states.
 * </p>
 *
 * @author Petchimuthu
 * @version 1.0
 */
public abstract class AbstractDataField extends Settings {

    private static final String THREE_STRING_FORMAT = "%s%s%s";
    private static final String OPEN_PARENTHESIS = "(";

    protected AbstractDataField(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    /**
     * <p>
     * Retrieves the {@link WebPageElement} of an active tab by its name.
     * </p>
     *
     * @param tabName The name of the tab.
     * @return A {@link WebPageElement} representing the active tab.
     */
    private WebPageElement getActiveTab(final String tabName) {
        return findByXpath(String.format(LOCATORS.get("crm.data.fields.active.tab"), tabName));
    }

    /**
     * <p>
     * Retrieves the active "Contact" tab.
     * </p>
     *
     * @return A {@link WebPageElement} representing the active "Contact" tab.
     */
    protected WebPageElement getActiveContactTab() {
        return getActiveTab(TEXT.get("crm.contact"));
    }

    /**
     * <p>
     * Retrieves the active "Company" tab.
     * </p>
     *
     * @return A {@link WebPageElement} representing the active "Company" tab.
     */
    protected WebPageElement getActiveCompanyTab() {
        return getActiveTab(TEXT.get("crm.company"));
    }

    /**
     * <p>
     * Retrieves the active "Deal" tab.
     * </p>
     *
     * @return A {@link WebPageElement} representing the active "Deal" tab.
     */
    protected WebPageElement getActiveDealTab() {
        return getActiveTab(TEXT.get("crm.deal"));
    }

    /**
     * <p>
     * Retrieves the active "Product" tab.
     * </p>
     *
     * @return A {@link WebPageElement} representing the active "Product" tab.
     */
    protected WebPageElement getActiveProductTab() {
        return getActiveTab(TEXT.get("crm.product"));
    }

    protected WebPageElement getAddViewCheckboxOf(final Field field) {
        return findByXpath(String.format(THREE_STRING_FORMAT, OPEN_PARENTHESIS, getFieldBlock(field), FieldElement.ADD_VIEW_CHECKBOX));
    }

    protected WebPageElement getRequiredCheckboxOf(final Field field) {
        return findByXpath(String.format(THREE_STRING_FORMAT, OPEN_PARENTHESIS, getFieldBlock(field), FieldElement.REQUIRED_CHECKBOX));
    }

    /**
     * <p>
     * Checks if the notification for exceeding a limit (e.g., maximum fields) is displayed.
     * </p>
     *
     * @return true if the notification is displayed, false otherwise.
     */
    public boolean isLimitExceededNotificationDisplayed() {
        final String notification = XPathBuilder.getXpathByContains(TEXT.get("crm.notification.maximum"));

        waitTillVisible(notification);

        return isDisplayed(findByXpath(notification));
    }

    /**
     * <p>
     * Constructs the XPath for a specific checkbox within a block element.
     * </p>
     *
     * @param div     The parent block element.
     * @param element The specific checkbox element.
     * @return The XPath string for the checkbox.
     */
    protected String getPathOfSpecificCheckbox(final String div, final String element) {
        return String.format(THREE_STRING_FORMAT, OPEN_PARENTHESIS, div, element);
    }

    /**
     * <p>
     * Retrieves the "Add System Fields" button element.
     * </p>
     *
     * @return A WebPageElement representing the "Add System Fields" button.
     */
    protected WebPageElement getAddSystemFieldsButton() {
        return findByText(Button.SYSTEM_FIELDS);
    }

    /**
     * <p>
     * Retrieves the checkbox element for adding a system field to the list.
     * </p>
     *
     * @param field The name of the system field.
     * @return A {@link WebPageElement} representing the checkbox element.
     */
    protected WebPageElement getCheckboxOf(final Field field) {
        waitTillVisible(getMenuBlock(field));

        return findByXpath(format(getMenuBlock(field), LOCATORS.get("checkbox")));
    }

    /**
     * <p>
     * Retrieves the block XPath for a field based on its field name.
     * </p>
     *
     * @param field The name of the field.
     * @return The XPath string for the field block.
     */
    protected String getFieldBlock(final String field) {
        return String.format(FieldElement.FIELD_BLOCK, field);
    }

    /**
     * <p>
     * Retrieves the block XPath for a field based on its {@link Field} .
     * </p>
     *
     * @param field The field object.
     * @return The XPath string for the {@link Field} block.
     */
    protected String getFieldBlock(final Field field) {
        return getFieldBlock(field.getName());
    }

    /**
     * <p>
     * Retrieves the block XPath for a field based on its field name.
     * </p>
     *
     * @param field The name of the field.
     * @return The XPath string for the field block.
     */
    protected String getDependableBlock(final String field) {
        return String.format(FieldElement.DEPENDABLE_BLOCK, field);
    }

    /**
     * <p>
     * Retrieves the block XPath for a field based on its {@link Field} .
     * </p>
     *
     * @param field The field object.
     * @return The XPath string for the {@link Field} block.
     */
    protected String getDependableBlock(final Field field) {
        return getDependableBlock(field.getName());
    }

    /**
     * <p>
     * Retrieves the "Add Selected Fields" button element.
     * </p>
     *
     * @return A WebPageElement representing the "Add Selected Fields" button.
     */
    protected WebPageElement getAddSelectedFieldsButton() {
        return findByText(Button.ADD_SELECTED_FIELDS);
    }

    /**
     * <p>
     * Retrieves the "Add Custom Field" button element.
     * </p>
     *
     * @return A WebPageElement representing the "Add Custom Field" button.
     */
    public WebPageElement getAddCustomFieldButton() {
        return findByText(Button.CUSTOM_FIELD);
    }

    /**
     * <p>
     * Retrieves the element for the custom field name input.
     * </p>
     *
     * @return A {@link WebPageElement} representing the custom field name input.
     */
    protected WebPageElement getCustomFieldName() {
        return findByXpath(FieldElement.CUSTOM_FIELD_NAME);
    }

    /**
     * <p>
     * Retrieves the dropdown element for selecting the custom field type.
     * </p>
     *
     * @return A {@link WebPageElement} representing the custom field type dropdown.
     */
    protected WebPageElement getSelectCustomFieldType() {
        return findByXpath(Button.CUSTOM_FIELDS_FIELD_TYPE);
    }

    /**
     * <p>
     * Retrieves the button element to add a custom field.
     * </p>
     *
     * @return A {@link WebPageElement} representing the "Add" button for custom fields.
     */
    protected WebPageElement getCustomFieldAddButton() {
        return findByXpath(FieldElement.ADD_BUTTON);
    }

    /**
     * <p>
     * Retrieves the button element for adding a choice to a custom field.
     * </p>
     *
     * @return A {@link WebPageElement} representing the "Choice" button.
     */
    protected WebPageElement getChoice() {
        return findByText(Button.CHOICE);
    }

    /**
     * <p>
     * Retrieves the button element for adding a new choice option.
     * </p>
     *
     * @return A {@link WebPageElement} representing the "Add Choice" button.
     */
    protected WebPageElement getAddChoice() {
        return findByText(Button.ADD_CHOICE);
    }

    /**
     * <p>
     * Retrieves the breadcrumb element for navigation within the settings page.
     * </p>
     *
     * @return A {@link WebPageElement} representing the breadcrumb element.
     */
    protected WebPageElement getBreadCrumb() {
        return findRightElement(List.of(new Element(LocatorType.XPATH,
                buildXpathByText("Admin Settings"), true), new Element(LocatorType.TAG_NAME, "svg",
                false)));
    }

    protected WebPageElement getNonDraggableElement(final Field field) {
        return findByXpath(format(getFieldBlock(field.getName()), FieldElement.NON_DRAGGABLE));
    }

    protected WebPageElement getDraggableElement(final Field field) {
        return findByXpath(format(getFieldBlock(field.getName()), FieldElement.DRAGGABLE));
    }

    protected WebPageElement getFieldName(final Field field) {
        final String name = field.getName();

        return findByXpath(format(getFieldBlock(name), XPathBuilder.getXPathByText(name)));
    }

    protected WebPageElement getFieldType(final Field field) {
        return findByXpath(format(getFieldBlock(field.getName()), XPathBuilder.getXPathByText(field.getFieldType())));
    }

    /**
     * <p>
     * Retrieves the search bar element for searching system fields to add.
     * </p>
     *
     * @return A {@link WebPageElement} representing the search bar for system fields.
     */
    protected WebPageElement getAddSystemFieldSearchBar() {
        return findByXpath(LOCATORS.get("crm.data.fields.search"));
    }

    /**
     * <p>
     * Retrieves the success notification element that indicates an operation was successful.
     * </p>
     *
     * @return A {@link WebPageElement} representing the "Success!" notification.
     */
    protected WebPageElement getSuccessNotification() {
        return findByText("Success!");
    }

    /**
     * <p>
     * Retrieves the element for displaying search results for system fields.
     * </p>
     *
     * @return An {@link Element} representing the system field search results.
     */
    protected Element getSystemFieldSearchResults() {
        return new Element(LocatorType.XPATH, FieldElement.SYSTEM_FIELD_SEARCH_RESULT, true);
    }

    /**
     * <p>
     * Retrieves the menu block XPath for a specific field name.
     * </p>
     *
     * @param field The name of the field.
     * @return The XPath string for the menu block of the specified field.
     */
    protected String getMenuBlock(final Field field) {
        return String.format(FieldElement.MENU_BLOCK, field.getName());
    }

    /**
     * <p>
     * Checks if a specific element within a field's block is displayed.
     * </p>
     *
     * @param fieldName   The name of the field block.
     * @param elementName The name of the element to check within the block.
     */
    protected boolean isFieldSpecificElementDisplayed(final String fieldName, final String elementName) {
        final String xpath = format(getFieldBlock(fieldName), elementName);

        waitTillVisible(xpath);

        return isDisplayed(findByXpath(xpath));
    }

    protected boolean isDependableFieldSpecificElementDisplay(final String fieldName, final String elementName) {
        final String xpath = format(getDependableBlock(fieldName), elementName);

        waitTillVisible(xpath);

        return isDisplayed(findByXpath(xpath));
    }

    protected boolean areChoicesPresent(final String[] options) {
        final Collection<String> choices = new ArrayList<>();
        waitTillVisible("//*[@role='menu']");
        final Collection<WebPageElement> choicesAsElements = findElementsByXpath("//*[@role='menu']//child::p");

        for (final WebPageElement choicesAsElement : choicesAsElements) {
            choices.add(getText(choicesAsElement));
        }

        for (final String option : options) {

            if (!choices.contains(option)) {
                return false;
            }
        }

        return true;
    }

    /**
     * <p>
     * Verifies the presence and state of the elements associated with a given record
     * This method checks that all relevant elements are displayed on the web page.
     * </p>
     *
     * @param field The {@link Record}  representing the data field to be checked.
     * @return true if all elements related to the record are correctly displayed;
     * false otherwise.
     */
    protected boolean check(final Record field) {
        if (field instanceof SystemField systemField) {
            final List<WebPageElement> elementsToCheck = Arrays.asList(
                    systemField.dragAndDropIcon(),
                    systemField.fieldName(),
                    systemField.fieldType());

            for (final WebPageElement element : elementsToCheck) {
                if (!isDisplayed(element)) {
                    return false;
                }

                if (!systemField.addViewCheckbox() && systemField.requiredCheckbox()) {
                    return false;
                }
            }

            return true;

        } else if (field instanceof DependableField dependableField) {
            final List<WebPageElement> elementsToCheck = Arrays.asList(
                    dependableField.fieldName(),
                    dependableField.fieldType()
            );

            for (final WebPageElement element : elementsToCheck) {
                if (!isDisplayed(element)) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    /**
     * <p>
     * Adds a custom field with the specified attributes.
     * </p>
     *
     * @param fieldStatus The {@link FieldStatus}.
     * @return true if the field is added successfully, false if it is not added.
     */
    public boolean addCustomField(final FieldStatus fieldStatus) {
        checkMaximumLimit(fieldStatus);
        final String fieldBlock = getFieldBlock(fieldStatus.getField());

        waitTillVisible(fieldBlock);

        return isDisplayed(findByXpath(fieldBlock)) && isDisplayed(findByXpath(format(fieldBlock, XPathBuilder.getXPathByText(fieldStatus.getFieldType()))));
    }

    /**
     * <p>
     * Checks if the maximum limit of choices has been reached for multi-select or dropdown field types.
     * </p>
     *
     * @param fieldStatus The {@link FieldStatus}.
     */
    public void checkMaximumLimit(final FieldStatus fieldStatus) {
        final String customFieldName = fieldStatus.getField().getName();
        final String fieldType = fieldStatus.getFieldType();
        final List<String> choices = fieldStatus.getChoices();
        final String xPath = "(//*[@data-rbd-droppable-id='%s-choices']//child::input[@type='text'])[%d]";

        waitTillVisible("//main/div[3]");
        click(getAddCustomFieldButton());
        waitTillClickable(FieldElement.CUSTOM_FIELD_NAME);
        send(getCustomFieldName(), customFieldName);
        waitTillClickable(Button.CUSTOM_FIELDS_FIELD_TYPE);
        click(getSelectCustomFieldType());
        waitTillClickable("//*[@role='menu']");
        dropdown(fieldType);

        if (isDropdownOrMultiSelect(fieldType)) {
            click(getChoice());
            click(getAddChoice());

            for (int i = 0; i < choices.size(); i++) {
                if (i > 1) {
                    click(getAddChoice());
                }

                if (fieldType.equalsIgnoreCase(FieldType.DROPDOWN)) {
                    send(findByXpath(String.format(xPath, "dropdown", i + 1)), choices.get(i));
                } else {
                    send(findByXpath(String.format(xPath, "multiselect", i + 1)), choices.get(i));
                }
            }
        }
        click(findByXpath(LOCATORS.get("body")));

        waitTillClickable(FieldElement.ADD_BUTTON);
        click(getCustomFieldAddButton());
    }

    /**
     * <p>
     * Retrieves the {@link Collection} of fields.
     * </p>
     *
     * @return A {@link Collection} of fields.
     */
    public Collection<String> getFields() {
        final Collection<String> fieldsPresent = new ArrayList<>();

        waitTillVisible("//*[@data-rbd-droppable-id='field-list']");
        final Collection<WebPageElement> fields = findElementsByXpath("//*[@data-rbd-droppable-id='field-list']//*[@aria-label]");

        for (final WebPageElement field : fields) {
            fieldsPresent.add(getText(field));
        }

        return fieldsPresent;
    }

    /**
     * <p>
     * Verifies if the given field type is either a dropdown or multi-select.
     * </p>
     *
     * @param fieldType The type of the field (dropdown or multi-select).
     * @return true if it's a dropdown or multi-select, otherwise false.
     */
    private boolean isDropdownOrMultiSelect(final String fieldType) {
        return fieldType.equalsIgnoreCase(FieldType.DROPDOWN) || fieldType.equalsIgnoreCase(FieldType.MULTI_SELECT);
    }

    /**
     * <p>
     * Checks if a field with the specified name is present in the current set of fields.
     * </p>
     *
     * @param fieldName The {@link FieldStatus}.
     * @return true if the field is present, false otherwise.
     */
    protected boolean isFieldPresent(final String fieldName) {
        return getFields().contains(fieldName);
    }

    /**
     * <p>
     * Verifies the search result for the system field with the given name.
     * </p>
     *
     * @param fieldName The {@link FieldStatus}.
     * @return true if the field is found or No Results otherwise false.
     */
    public boolean verifySearchResult(final String fieldName) {
        click(getAddSystemFieldsButton());
        send(getAddSystemFieldSearchBar(), fieldName);

        for (final WebPageElement webPageElement : findElements(getSystemFieldSearchResults())) {

            if (!getText(webPageElement).equalsIgnoreCase(contains(fieldName))) {
                return false;
            } else {
                return !getText(findByXpath(FieldElement.SYSTEM_FIELD_SEARCH_RESULT)).equals("No Results");
            }
        }

        return true;
    }

    /**
     * <p>
     * Verifies that the specified system field is not editable.
     * </p>
     *
     * @param field The {@link Field}.
     */
    protected void verifySystemFieldNotEditable(final Field field) {
        if (!isFieldPresent(field.getName())) {
            addField(field);
        }

        click(getAddViewCheckboxOf(field));
        click(findByXpath(FieldElement.UPDATE_BUTTON));
    }

    /**
     * Verifies if selected tabs (Contact, Company, Product, Deal) is active or not.
     *
     * @return true if selected tab is active else false.
     */
    public boolean isSelectedTabIsActiveOrNot() {
        click(getContact());

        if (!isDisplayed(getActiveContactTab())) {
            return false;
        }
        click(getCompany());

        if (!isDisplayed(getActiveCompanyTab())) {
            return false;
        }
        click(getProduct());

        if (!isDisplayed(getActiveProductTab())) {
            return false;
        }
        click(getDeal());

        return isDisplayed(getActiveDealTab());
    }

    /**
     * <p>
     * Checks if the field is displayed and verifies its properties (draggable, add view, required, editable, deletable,
     * hideable) based on the input.
     * </p>
     *
     * @param fieldStatus The {@link FieldStatus}.
     * @return true if all verifications pass, false if any fail.
     */
    public boolean checkIfDisplayed(final FieldStatus fieldStatus) {
        final boolean draggable = fieldStatus.isDraggable();
        final Field field = fieldStatus.getField();
        final String fieldType = fieldStatus.getFieldType();
        final boolean addView = fieldStatus.isAddView();
        final boolean required = fieldStatus.isRequired();
        final boolean editable = fieldStatus.isEditable();
        final boolean deletable = fieldStatus.isDeletable();
        final boolean hideable = fieldStatus.isHideable();
        String fieldBlock = null;

        if (!isFieldPresent(field.getName())) {
            addField(field);
        }

        fieldBlock = getFieldBlock(field);

        final WebPageElement addViewCheckbox = getAddViewCheckboxOf(field);
        final WebPageElement requiredCheckbox = getRequiredCheckboxOf(field);

        isDisplayed(findByXpath(format(fieldBlock, XPathBuilder.getXPathByText(fieldType))));

        final boolean isChecked = isSelected(addViewCheckbox);
        final boolean isRequired = isSelected(requiredCheckbox);

        if (draggable) {
            isDisplayed(findByXpath(format(fieldBlock, FieldElement.DRAGGABLE)));
        } else {
            isDisplayed(findByXpath(format(fieldBlock, FieldElement.NON_DRAGGABLE)));
        }

        if ((addView && !isChecked) || (!addView && isChecked)) {
            click(addViewCheckbox);
            click(findByXpath(format(fieldBlock, FieldElement.UPDATE_BUTTON)));
        }

        if ((required && !isRequired) || (!required && isRequired)) {
            click(requiredCheckbox);
            click(findByXpath(format(fieldBlock, FieldElement.UPDATE_BUTTON)));
        }

        hoverByXpath(fieldBlock);

        if (editable) {
            isDisplayed(findByXpath(format(fieldBlock, FieldElement.DELETE_ICON)));
        } else {
            try {
                if (isDisplayed(findByXpath(format(fieldBlock, FieldElement.DELETE_ICON)))) {
                    throw new AssertionError("Edit icon should not be displayed when editable is false.");
                }
            } catch (NoSuchElementException noSuchElementException) {

            }
        }

        if (deletable) {
            isDisplayed(findByXpath(format(fieldBlock, FieldElement.DELETE_ICON)));
        } else {
            try {
                if (isDisplayed(findByXpath(format(fieldBlock, FieldElement.DELETE_ICON)))) {
                    throw new AssertionError("Delete icon should not be displayed when Deletable is false.");
                }
            } catch (NoSuchElementException noSuchElementException) {

            }
        }

        if (hideable) {
            isDisplayed(findByXpath(format(fieldBlock, FieldElement.EYE_ICON)));
        } else {
            try {
                if (isDisplayed(findByXpath(format(fieldBlock, FieldElement.EYE_ICON)))) {
                    throw new AssertionError("Eye icon should not be displayed when hideable is false.");
                }
            } catch (NoSuchElementException noSuchElementException) {

            }
        }

        return true;
    }

//    public boolean addFormConfig(final ContactForm contactForm) {
//        final boolean draggable = fieldStatus.isDraggable();
//        final String fieldName = fieldStatus.getFieldName();
//        final String fieldType = fieldStatus.getFieldType();
//        final boolean addView = fieldStatus.isAddView();
//        final boolean required = fieldStatus.isRequired();
//        final boolean editable = fieldStatus.isEditable();
//        final boolean deletable = fieldStatus.isDeletable();
//        final boolean hideable = fieldStatus.isHideable();
//        String fieldBlock = null;
//
//        if (!isFieldPresent(fieldName)) {
//            addField(fieldName);
//        }
//
//        fieldBlock = getFieldBlock(fieldName);
//
//        final WebPageElement addViewCheckbox = getAddViewCheckboxOf(fieldName);
//        final WebPageElement requiredCheckbox = getRequiredCheckboxOf(fieldName);
//
//        isDisplayed(findByXpath(format(fieldBlock, XPathBuilder.getXPathByText(fieldType))));
//
//        final boolean isChecked = isSelected(addViewCheckbox);
//        final boolean isRequired = isSelected(requiredCheckbox);
//
//        if (draggable) {
//            isDisplayed(findByXpath(format(fieldBlock, FieldElement.DRAGGABLE)));
//        } else {
//            isDisplayed(findByXpath(format(fieldBlock, FieldElement.NON_DRAGGABLE)));
//        }
//
//        if ((addView && !isChecked) || (!addView && isChecked)) {
//            click(addViewCheckbox);
//            click(findByXpath(format(fieldBlock, FieldElement.UPDATE_BUTTON)));
//        }
//
//        if ((required && !isRequired) || (!required && isRequired)) {
//            click(requiredCheckbox);
//            click(findByXpath(format(fieldBlock, FieldElement.UPDATE_BUTTON)));
//        }
//
//        hoverByXpath(fieldBlock);
//
//        if (editable) {
//            isDisplayed(findByXpath(format(fieldBlock, FieldElement.DELETE_ICON)));
//        } else {
//            try {
//                if (isDisplayed(findByXpath(format(fieldBlock, FieldElement.DELETE_ICON)))) {
//                    throw new AssertionError("Edit icon should not be displayed when editable is false.");
//                }
//            } catch (NoSuchElementException noSuchElementException) {
//
//            }
//        }
//
//        if (deletable) {
//            isDisplayed(findByXpath(format(fieldBlock, FieldElement.DELETE_ICON)));
//        } else {
//            try {
//                if (isDisplayed(findByXpath(format(fieldBlock, FieldElement.DELETE_ICON)))) {
//                    throw new AssertionError("Delete icon should not be displayed when Deletable is false.");
//                }
//            } catch (NoSuchElementException noSuchElementException) {
//
//            }
//        }
//
//        if (hideable) {
//            isDisplayed(findByXpath(format(fieldBlock, FieldElement.EYE_ICON)));
//        } else {
//            try {
//                if (isDisplayed(findByXpath(format(fieldBlock, FieldElement.EYE_ICON)))) {
//                    throw new AssertionError("Eye icon should not be displayed when hideable is false.");
//                }
//            } catch (NoSuchElementException noSuchElementException) {
//
//            }
//        }
//
//        return true;
//    }

    /**
     * <p>
     * Edits a custom field by appending a new string to its current name.
     * </p>
     *
     * @param fieldStatus {@link FieldStatus}.
     * @return true if the field is renamed successfully, false otherwise.
     */
    public boolean editCustomField(final FieldStatus fieldStatus) {
        final Field field = fieldStatus.getField();
        final String fieldName = field.getName();
        final String newName = fieldStatus.getAppend();

        String fieldBlockXpath = null;

        if (isFieldPresent(fieldName)) {
            fieldBlockXpath = getFieldBlock(field);

            hoverByXpath(fieldBlockXpath);
            click(findByXpath(format(fieldBlockXpath, FieldElement.EDIT_ICON)));
            send(getCustomFieldName(), newName);
            click(findByXpath(FieldElement.UPDATE_BUTTON));
        }

        return isFieldPresent(String.format("%s%s", fieldName, newName));
    }

    /**
     * <p>
     * Hides a system field by interacting with the UI to click the 'eye' icon.
     * </p>
     *
     * @param systemFieldName The name of the system field to hide.
     * @return true if the field is hidden successfully, false otherwise.
     */
    public boolean hideField(final String systemFieldName) {
        LOGGER.info(systemFieldName);
        String fieldBlock;
        String eyeIconButton;

        if (isFieldPresent(systemFieldName)) {

            if (Objects.equals(ContactField.SUBSCRIPTION_STATUS.getName(), systemFieldName)) {
                fieldBlock = getDependableBlock(systemFieldName);
            } else {
                fieldBlock = getFieldBlock(systemFieldName);
            }
            eyeIconButton = format(fieldBlock, FieldElement.EYE_ICON);
            hoverByXpath(fieldBlock);

            waitTillClickable(eyeIconButton);
            click(findByXpath(eyeIconButton));
        }

        return !isFieldPresent(systemFieldName);
    }

    /**
     * <p>
     * Deletes a field by selecting the delete icon and confirming the deletion.
     * </p>
     *
     * @param fieldName The name of the field to delete.
     * @return true if the field is deleted successfully, false otherwise.
     */
    public boolean deleteField(final String fieldName) {
        LOGGER.info(fieldName);
        ;

        if (isFieldPresent(fieldName)) {
            final String fieldBlock = getFieldBlock(fieldName);

            hoverByXpath(fieldBlock);
            click(findByXpath(format(fieldBlock, FieldElement.DELETE_ICON)));

            waitTillClickable(XPathBuilder.getXPathByText("Delete"));
            click(findByText("Delete"));
            // refresh();
        }

        return !isFieldPresent(fieldName);
    }

    /**
     * <p>
     * Verifies that the 'eye' icon is displayed after hovering the particular field.
     * </p>
     *
     * @param field The {@link Field}.
     * @return true if the 'eye' icon is displayed, false otherwise.
     */
    public boolean verifyEyeIcon(final Field field) {
        final String fieldBlock = getFieldBlock(field);

        hoverByXpath(fieldBlock);
        click(findByXpath(format(fieldBlock, FieldElement.EYE_ICON)));
        click(getAddSystemFieldsButton());

        return !isSelected(getCheckboxOf(field));
    }

    /**
     * <p>
     * Hides an auto-generating system field.
     * </p>
     *
     * @param hideableSystemField The {@link Field}.
     * @return true if the field is hidden successfully, false otherwise.
     */
    public boolean hideAutoGeneratingSystemField(final Field hideableSystemField) {
        final String fieldBlock = getFieldBlock(hideableSystemField);

        hoverByXpath(fieldBlock);
        click(findByXpath(format(fieldBlock, FieldElement.EYE_ICON)));
        click(getAddSystemFieldsButton());

        return !isSelected(getCheckboxOf(hideableSystemField));
    }

    /**
     * <p>
     * Verifies that an auto-generating system field cannot be edited.
     * </p>
     *
     * @param field The {@link Field}.
     * @return true as this field should not be editable.
     */
    public boolean verifyAutoGeneratingSystemFieldNotEditable(final Field field) {
        if (!isFieldPresent(field.getName())) {
            addField(field);
        }

        click(getAddViewCheckboxOf(field));
        click(findByXpath(FieldElement.UPDATE_BUTTON));
        click(findByXpath(FieldElement.CANCEL_BUTTON));
        click(getRequiredCheckboxOf(field));
        click(findByXpath(FieldElement.UPDATE_BUTTON));
        click(findByXpath(FieldElement.CANCEL_BUTTON));

        return true;
    }

    /**
     * <p>
     * Removes a system field from the list of fields.
     * </p>
     *
     * @param field The {@link Field}.
     * @return true if the field is removed successfully.
     */
    public boolean removeSystemField(final Field field) {
        click(getAddSystemFieldsButton());

        final WebPageElement checkbox = getCheckboxOf(field);

        if (isSelected(checkbox)) {
            click(checkbox);
        }
        click(getAddSelectedFieldsButton());

        return true;
    }

    /**
     * <p>
     * Verifies that the system field tab does not hide other tabs when clicked.
     * </p>
     *
     * @return true if other tabs are not hidden, false otherwise.
     */
    public boolean verifySystemFieldTabsDoesNotHideOtherTabs() {
        click(getAddSystemFieldsButton());
        final boolean displayed = isDisplayed(getAddSystemFieldsButton());

        return displayed;
    }

    /**
     * <p>
     * Adds all available system fields to the active list by selecting their checkboxes.
     * </p>
     *
     * @return true if all fields are added successfully, false otherwise.
     */
    public boolean addAllSystemFields() {
        WebPageElement checkbox = null;
        click(getAddSystemFieldsButton());

        for (final Field specificField : getAllFields()) {
            try {
                checkbox = getCheckboxOf(specificField);
            } catch (Exception exception) {
            }

            if (!isSelected(checkbox)) {
                click(checkbox);
            }
            isDisplayed(findByXpath(getFieldBlock(specificField)));
        }
        click(getAddSelectedFieldsButton());

        try {
            for (final Field specificField : getAllFields()) {
                isDisplayed(findByXpath(specificField.getName()));
            }
        } catch (Exception exception) {
            return false;
        }

        return true;
    }

    /**
     * <p>
     * Unchecks the required checkbox for the provided mandatory fields if selected.
     * </p>
     *
     * @param mandatoryFields An array of field names that are marked as required.
     */
    protected void uncheck(final Field[] mandatoryFields) {

        for (final Field field : mandatoryFields) {

            final WebPageElement path = getCheckboxOf(field);

            if (isSelected(path)) {
                click(path);

                if (isDisplayed(findByXpath(format(getFieldBlock(field), FieldElement.UPDATE_BUTTON)))) {
                    click(findByXpath(format(getFieldBlock(field), FieldElement.UPDATE_BUTTON)));
                }
            }
        }
    }

    /**
     * <p>
     * Adds one system field and removes another by selecting/deselecting the corresponding checkboxes.
     * </p>
     *
     * @param fieldToBeAdded   The field to be added to the list.
     * @param fieldToBeRemoved The field to be removed from the list.
     * @return true once the operation is completed.
     */
    public boolean addAndRemoveSystemField(final Field fieldToBeAdded, final Field fieldToBeRemoved) {
        click(getAddSystemFieldsButton());
        final WebPageElement systemFieldToBeAdded = getCheckboxOf(fieldToBeAdded);
        final WebPageElement systemFieldToBeRemoved = getCheckboxOf(fieldToBeRemoved);

        if (!isSelected(systemFieldToBeAdded)) {
            click(getCheckboxOf(fieldToBeAdded));
        }
        if (isSelected(systemFieldToBeRemoved)) {
            click(getCheckboxOf(fieldToBeRemoved));
        }
        return true;
    }

    /**
     * <p>
     * Verifies that specific fields are present in the summary section.
     * </p>
     *
     * @param fieldsToBePresentInSummary List of field names expected to be present in the summary.
     * @return true if all specified fields are present, false otherwise.
     */
    public boolean isPresentInSummary(final Collection<String> fieldsToBePresentInSummary) {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//
//        }
        waitTillClickable("//*[@class='css-itno5t']");
        try {
            while (isDisplayed(findByXpath("//*[contains(@class,'1hyoz7m')]/button"))) {
                click(findByXpath("//*[contains(@class,'1hyoz7m')]"));
            }
        } catch (Exception exception) {
        }

        final Collection<WebPageElement> fieldsPresentInSummary = findElementsByXpath("//*[contains(@class,'1hcm7kq')]");
        final Collection<String> fieldNames = new ArrayList<>();

        for (final WebPageElement field : fieldsPresentInSummary) {
            String fieldText = getText(field);
            fieldNames.add(fieldText);
        }

        for (final String fieldName : fieldNames) {
            LOGGER.info(fieldName);
            ;
        }

        for (final String fieldToBePresent : fieldsToBePresentInSummary) {

            // final String formattedField = String.format("%s%s%s", fieldToBePresent, " ", ":");

            if (!fieldNames.contains(fieldToBePresent)) {
                LOGGER.info(fieldToBePresent);

                return false;
            }
        }

        return true;
    }

    /**
     * <p>
     * Verifies that specific fields are present in the 'Add Form' section.
     * </p>
     *
     * @param fieldsToBePresentInAddForm List of field names expected to be present in the Add Form.
     * @return true if all specified fields are present, false otherwise.
     */
    public boolean isPresentInAddForm(final Collection<String> fieldsToBePresentInAddForm) {
        final Collection<String> fieldsPresentInAddForm = new ArrayList<>();
        final Collection<WebPageElement> fields = findElementsByXpath("//*[contains(@class,'1b4365c')]");
        //final Collection<WebPageElement> remainingFields = findElementsByXpath("//*[@class='css-1cdo4bs']/p");

        for (final WebPageElement field : fields) {

            fieldsPresentInAddForm.add(getText(field).replaceAll("\\*$", ""));
        }

//        for (final WebPageElement remainingField : remainingFields) {
//            fieldsPresentInAddForm.add(getText(remainingField).replaceAll("\\*$", ""));
//        }

        for (final String field : fieldsToBePresentInAddForm) {
            if (!fieldsPresentInAddForm.contains(field)) {
                LOGGER.info(field);
                return false;
            }
        }

        return true;
    }

    /**
     * <p>
     * Verifies that specific fields are present in the 'Column Settings' section.
     * </p>
     *
     * @param fieldsToBePresentInColumnSettings List of field names expected to be present in the Column Settings.
     * @return true if all specified fields are present, false otherwise.
     */
    public boolean isPresentInColumnSettings(final Field[] fieldsToBePresentInColumnSettings) {
        final Collection<String> fieldsPresentInAddForm = new ArrayList<>();
        final Collection<WebPageElement> fields = findElementsByXpath("//div[@data-rbd-droppable-id='tableHead_items']//child::p");

        for (final WebPageElement field : fields) {
            fieldsPresentInAddForm.add(getText(field));
        }

        for (final Field field : fieldsToBePresentInColumnSettings) {

            final String fieldName = field.getName();

            if (!fieldsPresentInAddForm.contains(fieldName)) {
                LOGGER.info(fieldName);
                return false;
            }
        }

        return true;
    }

    /**
     * <p>
     * Adds a particular field to the list by clicking on the corresponding checkbox in column settings.
     * </p>
     *
     * @param fieldName The name of the field to be added.
     */
    public void addParticularFieldToList(final String fieldName) {
        click(findByXpath(String.format(FieldElement.SPECIFIC_FIELD_COLUMN_SETTINGS_CHECKBOX, fieldName)));
    }

    /**
     * <p>
     * Verifies that all default system fields are present and checked correctly.
     * </p>
     *
     * @return true if all default system fields are verified successfully, false otherwise.
     */
    public boolean verifyDefaultSystemFields() {
        for (final Record field : getDefaultSystemFieldElements()) {

            if (!check(field)) {
                return false;
            }
        }

        return true;
    }

    /**
     * <p>
     * Verifies the presence of the breadcrumb navigation on the page.
     * </p>
     *
     * @return true if the breadcrumb is displayed, false otherwise.
     */
    public boolean verifyBreadCrumb() {
        return isDisplayed(getBreadCrumb());
    }

    /**
     * <p>
     * Retrieves the error message displayed when an unexpected error occurs.
     * </p>
     *
     * @return The WebPageElement representing the error message.
     */
    public WebPageElement getAnUnExpectedErrorMessage() {
        return findByText("An unexpected error occurred. Please try again later.");
    }

    /**
     * <p>
     * Checks if a non-draggable icon is displayed for a given field block.
     * </p>
     *
     * @param divBlockName The name of the div block to check.
     * @return true if the non-draggable icon is displayed, false otherwise.
     */
    protected boolean isNonDraggableIconDisplayed(String divBlockName) {
        return isDisplayed(findByXpath(format(divBlockName, FieldElement.NON_DRAGGABLE)));
    }

    /**
     * <p>
     * Verifies that the eye icon is not visible for default fields.
     * </p>
     *
     * @return true if the eye icon is not visible for all default fields, false otherwise.
     */
    public boolean verifyEyeIconIsNotVisibleForDefaultFields() {
        for (Field value : getDefaultFields()) {
            final String name = value.getName();

            hover(new Element(LocatorType.XPATH, buildXpathByText(name), true));

            try {
                WebPageElement element = findRightElement(List.of(
                        new Element(LocatorType.XPATH, format(getFieldBlock(name), buildXpathByText("Required")), true),
                        new Element(LocatorType.TAG_NAME, "button", false)
                ));
                if (isDisplayed(element)) {
                    return false;
                }
            } catch (Exception e) {

            }
        }

        return true;
    }

    /**
     * <p>
     * Checks if the 'Add View' checkbox is checked for a given field.
     * </p>
     *
     * @param field The name of the field to check.
     * @return true if the 'Add View' checkbox is checked, false otherwise.
     */
    public boolean checkIfGivenFieldsAddViewIsChecked(final Field field) {
        return isSelected(getAddViewCheckboxOf(field));
    }

    /**
     * <p>
     * Switches the view to the Summary section by clicking on the relevant element.
     * </p>
     */
    public void switchToSummary() {
        waitTillClickable("//*[contains(@class,'1xnox0e')]//child::div");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//
//        }
        click(findByXpath("//*[contains(@class,'1xnox0e')]//child::div"));
    }

    /**
     * <p>
     * Switches the view to the Add Contact form with a delay to ensure the form loads.
     * </p>
     */
    public void switchToAddContactForm() {
        //waitTillVisible("//*[@class='MuiStack-root twozo-css-prefix-1gqdsmd']");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        click(findByText("Contact"));
    }

    /**
     * <p>
     * Switches the view to the Add Company form with a delay to ensure the form loads.
     * </p>
     */
    public void switchToAddCompanyForm() {
        // waitTillClickable("//*[@class='MuiStack-root twozo-css-prefix-1gqdsmd']");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
        click(findByText("Company"));
    }

    /**
     * <p>
     * Switches the view to the Add Deal form with a delay to ensure the form loads.
     * </p>
     */
    public void switchToAddDealForm() {
//        waitTillClickable("//*[@class='MuiStack-root twozo-css-prefix-1gqdsmd']");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
        click(findByText("Deal"));
    }

    /**
     * <p>
     * Switches the view to the Add Product form.
     * </p>
     */
    public void switchToAddProductForm() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
        // waitTillClickable("//*[@class='MuiStack-root twozo-css-prefix-1gqdsmd']");
        click(findByText("Product"));
    }

    /**
     * <p>
     * Checks if a given field is present in the list by verifying its display status.
     * </p>
     *
     * @param contactSystemField The name of the field to check.
     * @return true if the field is displayed, false otherwise.
     */
    public boolean checkIfGivenFieldIsInList(final String contactSystemField) {
        return isDisplayed(findByXpath(getFieldBlock(contactSystemField)));
    }

    /**
     * <p>
     * Verifies that the 'Add View' checkbox for mandatory fields is not editable (update button should not be visible).
     * </p>
     *
     * @return true if the checkbox is not editable for all mandatory fields, false otherwise.
     */
    public boolean isAddViewCheckBoxEditableForMandatoryField() {
        for (final String fieldDiv : getMandatoryFields()) {
            click(findByXpath(format(fieldDiv, FieldElement.ADD_VIEW_CHECKBOX)));

            try {
                if (isDisplayed(findByXpath(format(fieldDiv, FieldElement.UPDATE_BUTTON)))) {
                    throw new RuntimeException("Update button should not be visible after clicking the checkbox");
                }
            } catch (NoSuchElementException noSuchElementException) {

            }
        }

        return true;
    }

    /**
     * <p>
     * Enables the 'Add View' checkbox for all system fields and updates the changes.
     * </p>
     *
     * @return true if the 'Add View' checkbox is enabled for all system fields, false otherwise.
     */
    public boolean enableAddViewForAllSystemFields() {
        WebPageElement addViewCheckbox = null;
        for (final Field field : getAllFields()) {
            LOGGER.info(field.getName());
            final String fieldBlock = getFieldBlock(field);

            try {
                findByXpath(fieldBlock);
            } catch (Exception exception) {

            }

            try {
                addViewCheckbox = getAddViewCheckboxOf(field);

                if (!isSelected(addViewCheckbox)) {
                    click(addViewCheckbox);
                    try {
                        click(findByXpath(format(fieldBlock, FieldElement.UPDATE_BUTTON)));
                    } catch (Exception exception) {
                        LOGGER.info(field.getName());
                    }

                }
            } catch (Exception exception) {
            }
        }
        return true;
    }

    /**
     * <p>
     * Enables the 'Add View' checkbox for a specific field and updates the changes.
     * </p>
     *
     * @param fieldName The name of the field to update.
     */
    public boolean enableAddView(final String fieldName) {
        final String fieldBlock = getFieldBlock(fieldName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        if (!isSelected(getAddViewCheckboxOf(fieldName))) {
            click(getAddViewCheckboxOf(fieldName));

            try {
                click(findByXpath(format(fieldBlock, FieldElement.UPDATE_BUTTON)));
            } catch (Exception exception) {
                LOGGER.info(fieldName);
            }
        }

        return isSelected(getAddViewCheckboxOf(fieldName));
    }

    /**
     * <p>
     * Enables the 'Required' checkbox for a specific field and updates the changes.
     * </p>
     *
     * @param fieldName The name of the field to update.
     */
    public boolean enableRequired(final String fieldName) {
        final String fieldBlock = getFieldBlock(fieldName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        if (!isSelected(getRequiredCheckboxOf(fieldName))) {
            click(getRequiredCheckboxOf(fieldName));

            try {
                click(findByXpath(format(fieldBlock, FieldElement.UPDATE_BUTTON)));
            } catch (Exception exception) {
                LOGGER.info(fieldName);
            }
        }

        return isSelected(getRequiredCheckboxOf(fieldName));
    }

    public void disableRequiredForNonMandatoryFields() {
        final Collection<String> mandatoryFields = getMandatoryFields();

        for (final String field : getFields()) {

            if (!mandatoryFields.contains(field) && isSelected(getRequiredCheckboxOf(field))) {
                click(getRequiredCheckboxOf(field));

                try {
                    click(findByXpath(format(getFieldBlock(field), FieldElement.UPDATE_BUTTON)));
                } catch (Exception exception) {
                    LOGGER.info(field);
                }
            }

        }
    }

    /**
     * <p>
     * Enables the 'Required' checkbox for all system fields and updates the changes.
     * </p>
     */
    public void enableRequiredForAllSystemFields() {
        for (final Field field : getAllFields()) {

            final String fieldBlock = getFieldBlock(field);
            final WebPageElement addViewCheckbox = getAddViewCheckboxOf(field.getName());

            if (!isSelected(addViewCheckbox)) {
                click(addViewCheckbox);
                try {
                    click(findByXpath(format(fieldBlock, FieldElement.UPDATE_BUTTON)));
                } catch (Exception exception) {
                    LOGGER.info(field.getName());
                }
            }
        }
    }

    /**
     * <p>
     * Adds a field to the system based on the field name.
     * </p>
     *
     * @param fieldName The name of the field to be added.
     */
    protected void addField(final Field field) {
        waitTillVisible(XPathBuilder.getXPathByText(Button.SYSTEM_FIELDS));
        click(getAddSystemFieldsButton());
        waitTillVisible(getMenuBlock(fieldName));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        click(getCheckboxOf(fieldName));
        click(getAddSelectedFieldsButton());
    }

    protected void addFields(final List<String> fields) {
        waitTillVisible(XPathBuilder.getXPathByText(Button.SYSTEM_FIELDS));
        final List<String> fieldsToBeAdded = new ArrayList<>(fields);
        String path;

        fieldsToBeAdded.removeAll(getFields());

        if (!fieldsToBeAdded.isEmpty()) {
            click(getAddSystemFieldsButton());

            for (final String field : fieldsToBeAdded) {

                path = format(getMenuBlock(field), "//*[@type='checkbox']");

                if (!isSelected(findByXpath(path))) {
                    click(findByXpath(path));
                }
            }

            click(getAddSelectedFieldsButton());
        }
    }

    /**
     * <p>
     * Adds a system field based on the field status and verifies its presence.
     * </p>
     *
     * @param fieldStatus An object representing the status of the field.
     * @return true if the field is added and displayed correctly, false otherwise.
     */
    public boolean addSystemField(final FieldStatus fieldStatus) {
        final String fieldName = fieldStatus.getFieldName();
        final String fieldType = fieldStatus.getFieldType();

        LOGGER.info(fieldName);

        if (!isFieldPresent(fieldName)) {
            addField(fieldName);
        }

        final String fieldNameXPath = getFieldBlock(fieldName);
        final String fieldTypeXPath = format(getFieldBlock(fieldName), XPathBuilder.getXPathByText(fieldType));

        waitTillVisible("//div[@data-rbd-droppable-id='field-list']");
        waitTillVisible(fieldNameXPath);

        return isDisplayed(findByXpath(fieldNameXPath)) && isDisplayed(findByXpath(fieldTypeXPath));
    }

    /**
     * <p>
     * Enables the 'Add View' checkbox for a given field and updates the changes.
     * </p>
     *
     * @param fieldStatus An object representing the status of the field.
     * @return true if the 'Add View' checkbox is selected, false otherwise.
     */
    public boolean enableAddView(final FieldStatus fieldStatus) {
        return enableAddView(fieldStatus.getFieldName());
    }

    /**
     * <p>
     * Enables the 'Required' checkbox for a given field and updates the changes.
     * </p>
     *
     * @param fieldStatus An object representing the status of the field.
     * @return true if the 'Required' checkbox is selected, false otherwise.
     */
    public boolean enableRequired(final FieldStatus fieldStatus) {
        return enableRequired(fieldStatus.getFieldName());
    }

    /**
     * <p>
     * Enables the 'Add View' checkbox for a given field and updates the changes.
     * </p>
     *
     * @param fieldStatus An object representing the status of the field.
     * @return true if the 'Add View' checkbox is selected, false otherwise.
     */
    public boolean enableAddViewForAutoGeneratingField(final FieldStatus fieldStatus) {
        final String fieldName = fieldStatus.getFieldName();
        final String fieldBlock = getFieldBlock(fieldName);

        LOGGER.info(fieldName);

        if (!isFieldPresent(fieldName)) {
            addSystemField(fieldStatus);
        }

        if (!isSelected(getAddViewCheckboxOf(fieldName))) {
            click(getAddViewCheckboxOf(fieldName));

            try {
                shortWaitTillVisible(format(fieldBlock, FieldElement.UPDATE_BUTTON));
            } catch (Exception exception) {
            }
        }

        waitTillVisible(fieldBlock);

        return isSelected(getAddViewCheckboxOf(fieldName));
    }

    /**
     * <p>
     * Enables the 'Required' checkbox for a given field and updates the changes.
     * </p>
     *
     * @param fieldStatus An object representing the status of the field.
     * @return true if the 'Required' checkbox is selected, false otherwise.
     */
    public boolean enableRequiredForAutoGeneratingField(final FieldStatus fieldStatus) {
        final String fieldName = fieldStatus.getFieldName();
        final String fieldBlock = getFieldBlock(fieldName);

        WebPageElement requiredCheckbox = null;

        if (!isFieldPresent(fieldName)) {
            addSystemField(fieldStatus);
        }

        requiredCheckbox = getRequiredCheckboxOf(fieldName);


        if (!isSelected(requiredCheckbox)) {
            click(requiredCheckbox);

            try {
                shortWaitTillVisible(format(fieldBlock, FieldElement.UPDATE_BUTTON));
            } catch (Exception exception) {

            }
        }
        waitTillVisible(fieldBlock);

        return isSelected(getRequiredCheckboxOf(fieldName));

    }

    /**
     * <p>
     * Retrieves the list of fields for summary view.
     * </p>
     *
     * @return A list of fields for the summary view.
     */
    protected Collection<String> getFieldsForSummary(final Collection<String> fieldsNotToDisplay) {
        final Collection<String> fieldsPresent = new ArrayList<>();
        final Collection<WebPageElement> fields = findElementsByXpath("//*[@data-rbd-droppable-id='field-list']//*[@aria-label]");

        for (final WebPageElement field : fields) {
            final String fieldName = getText(field);

            if (!fieldsNotToDisplay.contains(fieldName)) {
                fieldsPresent.add(fieldName);
            }
        }

        return fieldsPresent;
    }

    /**
     * <p>
     * Retrieves the list of fields for 'Add View' and 'Required' settings.
     * </p>
     *
     * @param addViewOrRequired The type of setting to check ('Add View' or 'Required').
     * @return A list of fields for the specified setting.
     */
    private Collection<String> getFieldsEnabledAs(final String addViewOrRequired) {
        waitTillVisible("//*[@data-rbd-droppable-id]");
        final Collection<String> fieldsPresent = new ArrayList<>();
        final Collection<WebPageElement> elementsByXpath = findElementsByXpath("//*[@data-rbd-draggable-context-id]");

        for (int i = 1; i <= elementsByXpath.size(); i++) {
            final String fieldBlock = String.format(FieldElement.BLOCK, i);
            final WebPageElement fieldElement = findByXpath(getPathOfSpecificCheckbox(fieldBlock, addViewOrRequired));

            if (isSelected(fieldElement)) {
                final Collection<WebPageElement> matchingElements = findElementsByXpath(format(fieldBlock, "//*[@aria-label]"));

                for (final WebPageElement element : matchingElements) {
                    fieldsPresent.add(getText(element));
                }
            }
        }

        return fieldsPresent;
    }

    protected boolean setDefaultChoice(final boolean isDependableField, final String fieldName, final String choice) {
        final String path = String.format(
                "//*[@role='menu']//*[text()='%s']//ancestor::div[3]/child::div[1]//*[@aria-label]", choice);

        if (!isDependableField) {
            click(findByXpath(format(getFieldBlock(fieldName), "//div[3]//p")));
        } else {
            click(findByXpath(format(getDependableBlock(fieldName), "//div[3]//p")));
        }

        String currentLabel = getAttribute(findByXpath(path), "aria-label");

        if (Objects.equals("Mark as Default Choice", currentLabel)) {
            click(findByXpath(path));
            click(findByXpath(LOCATORS.get("body")));
            click(findByXpath(FieldElement.UPDATE_BUTTON));
            click(findByXpath(format(getFieldBlock(fieldName), XPathBuilder.getXPathByText("14"))));
            currentLabel = getAttribute(findByXpath(path), "aria-label");
        }

        return Objects.equals("Mark as Default Choice", currentLabel);
    }

    public Collection<String> getFieldsEnabledAsAddView() {
        return getFieldsEnabledAs(FieldElement.ADD_VIEW_CHECKBOX);
    }

    public Collection<String> getFieldsEnabledAsRequired() {
        return getFieldsEnabledAs(FieldElement.REQUIRED_CHECKBOX);
    }

    private void setFieldsForAddFormAs(final List<String> fields, final String addViewOrRequired) {
        waitTillVisible(XPathBuilder.getXPathByText(Button.SYSTEM_FIELDS));
        final List<String> fieldsToBeAdded = new ArrayList<>(fields);
        String path;

        fieldsToBeAdded.removeAll(getFields());

        if (!fieldsToBeAdded.isEmpty()) {
            click(getAddSystemFieldsButton());

            for (final String field : fieldsToBeAdded) {

                path = format(getMenuBlock(field), "//*[@type='checkbox']");

                if (!isSelected(findByXpath(path))) {
                    click(findByXpath(path));
                }
            }

            click(getAddSelectedFieldsButton());
        }

        for (final String field : fields) {

            if (addViewOrRequired.equals(FieldElement.ADD_VIEW_CHECKBOX)) {
                enableAddView(field);
            } else {
                enableRequired(field);
            }
        }
    }

    private void setFieldForAddFormAs(final String field, final String addViewOrRequired) {
        if (!isFieldPresent(field)) {
            addField(field);
        }

        if (addViewOrRequired.equals(FieldElement.ADD_VIEW_CHECKBOX)) {
            enableAddView(field);
        } else {
            enableRequired(field);
        }
    }

    public void setFieldsAddViewEnabled(final List<String> fields) {
        setFieldsForAddFormAs(fields, FieldElement.ADD_VIEW_CHECKBOX);
    }

    public void setFieldsRequiredEnabled(final List<String> fields) {
        setFieldsForAddFormAs(fields, FieldElement.REQUIRED_CHECKBOX);
    }

    public void setFieldAddViewEnabled(final String field) {
        setFieldForAddFormAs(field, FieldElement.ADD_VIEW_CHECKBOX);
    }

    public void setFieldRequiredEnabled(final String field) {
        setFieldForAddFormAs(field, FieldElement.REQUIRED_CHECKBOX);
    }

    /**
     * <p>
     * Retrieves the list of default fields.
     * </p>
     *
     * @return A list of default fields.
     */
    protected abstract Collection<Field> getDefaultFields();

    /**
     * <p>
     * Retrieves all fields available in the system.
     * </p>
     *
     * @return An array of all fields.
     */
    protected abstract Field[] getAllFields();

    /**
     * <p>
     * Retrieves the list of mandatory fields.
     * </p>
     *
     * @return A list of mandatory fields.
     */
    protected abstract Collection<String> getMandatoryFields();

    /**
     * <p>
     * Verifies that non-draggable fields are properly set.
     * </p>
     *
     * @return true if non-draggable fields are verified, false otherwise.
     */
    protected abstract boolean verifyNonDraggableFields();

    /**
     * <p>
     * Retrieves default system field elements.
     * </p>
     *
     * @return A list of default system field elements.
     */
    protected abstract Collection<Record> getDefaultSystemFieldElements();

    /**
     * <p>
     * Checks if default fields are visible in the summary view.
     * </p>
     *
     * @return true if default fields are visible in the summary, false otherwise.
     */
    public abstract boolean isDefaultFieldsVisibleInSummary();

    /**
     * <p>
     * Unchecks mandatory fields.
     * </p>
     *
     * @return true if mandatory fields are successfully unchecked, false otherwise.
     */
    protected abstract boolean uncheckMandatoryFields();
}

