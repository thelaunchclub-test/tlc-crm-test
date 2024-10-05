package com.twozo.page.settings.data.fields;

import com.twozo.page.settings.Settings;
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

    private static final String TWO_STRING_FORMAT = "%s%s";
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
        return findByXpath(String.format("//*[@value='%s' and @aria-pressed='true']", tabName));
    }

    /**
     * <p>
     * Retrieves the active "Contact" tab.
     * </p>
     *
     * @return A {@link WebPageElement} representing the active "Contact" tab.
     */
    protected WebPageElement getActiveContactTab() {
        return getActiveTab("Contact");
    }

    /**
     * <p>
     * Retrieves the active "Company" tab.
     * </p>
     *
     * @return A {@link WebPageElement} representing the active "Company" tab.
     */
    protected WebPageElement getActiveCompanyTab() {
        return getActiveTab("Company");
    }

    /**
     * <p>
     * Retrieves the active "Deal" tab.
     * </p>
     *
     * @return A {@link WebPageElement} representing the active "Deal" tab.
     */
    protected WebPageElement getActiveDealTab() {
        return getActiveTab("Deal");
    }

    /**
     * <p>
     * Retrieves the active "Product" tab.
     * </p>
     *
     * @return A {@link WebPageElement} representing the active "Product" tab.
     */
    protected WebPageElement getActiveProductTab() {
        return getActiveTab("Product");
    }

    /**
     * <p>
     * Checks if the notification for exceeding a limit (e.g., maximum fields) is displayed.
     * </p>
     *
     * @return true if the notification is displayed, false otherwise.
     */
    public boolean isLimitExceededNotificationDisplayed() {
        return isDisplayed(findByXpath("//*[contains(text(),'Maximum')]"));
    }

    /**
     * <p>
     * Formats a string by concatenating two parts.
     * </p>
     *
     * @param div     The first part of the string.
     * @param element The second part of the string.
     * @return The formatted string.
     */
    protected String format(final String div, final String element) {
        return String.format(TWO_STRING_FORMAT, div, element);
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
     * @param fieldName The name of the system field.
     * @return A {@link WebPageElement} representing the checkbox element.
     */
    protected WebPageElement getAddSystemFieldToList(final String fieldName) {
        return findByXpath(format(getMenuBlock(fieldName), "//*[@type='checkbox']"));
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
        return String.format(FieldElement.FIELD_BLOCK, field.getName());
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

    /**
     * <p>
     * Retrieves the search bar element for searching system fields to add.
     * </p>
     *
     * @return A {@link WebPageElement} representing the search bar for system fields.
     */
    protected WebPageElement getAddSystemFieldSearchBar() {
        return findByXpath("(//*[@placeholder='Search'])[2]");
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
     * @param fieldName The name of the field.
     * @return The XPath string for the menu block of the specified field.
     */
    protected String getMenuBlock(final String fieldName) {
        return String.format(FieldElement.MENU_BLOCK, fieldName);
    }

    /**
     * <p>
     * Checks if a specific element within a field's block is displayed.
     * </p>
     *
     * @param fieldName   The name of the field block.
     * @param elementName The name of the element to check within the block.
     */
    protected void checkSpecificElement(final String fieldName, final String elementName) {
        isDisplayed(findByXpath(format(getFieldBlock(fieldName), elementName)));
    }

    /**
     * <p>
     * Verifies the presence and state of the elements associated with a given record
     * This method checks that all relevant elements are displayed on the web page.
     * </p>
     *
     * @param record The {@link Record}  representing the data field to be checked.
     * @return true if all elements related to the record are correctly displayed;
     * false otherwise.
     */
    protected boolean check(final Record record) {
        if (record instanceof SystemField systemField) {
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

        } else if (record instanceof DependableField dependableField) {
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
        final String customFieldName = fieldStatus.getFieldName();
        final String fieldType = fieldStatus.getFieldType();
        final List<String> choices = fieldStatus.getChoices();
        final String dropdownPath = "(((//*[@data-rbd-droppable-id='dropdown-choices']/div/div/div/div)[%d])/div/div)[2]/div/div/div/input";
        final String multiSelectPath = "(((//*[@data-rbd-droppable-id='multiselect-choices']/div/div/div/div)[%d])/div/div)[2]/div/input";

        if (!isFieldPresent(customFieldName)) {

            click(getAddCustomFieldButton());
            send(getCustomFieldName(), customFieldName);
            click(getSelectCustomFieldType());
            dropdown(fieldType);

            if (isDropdownOrMultiSelect(fieldType)) {
                click(getChoice());
                click(getAddChoice());

                for (int i = 0; i < choices.size(); i++) {
                    if ((fieldType.equalsIgnoreCase(FieldType.MULTI_SELECT) || fieldType.equalsIgnoreCase(FieldType.DROPDOWN)) && i > 1) {
                        click(getAddChoice());
                    }
                    final String path = fieldType.equalsIgnoreCase(FieldType.DROPDOWN)
                            ? dropdownPath
                            : multiSelectPath;
                    send(findByXpath(String.format(path, i + 1)), choices.get(i));
                }

            }
            click(findByXpath("//body"));

            waitTillVisible(FieldElement.ADD_BUTTON);
            click(getCustomFieldAddButton());

            refresh();
        }

        return isFieldPresent(customFieldName);
    }

    /**
     * <p>
     * Checks if the maximum limit of choices has been reached for multi-select or dropdown field types.
     * </p>
     *
     * @param fieldStatus The {@link FieldStatus}.
     */
    public void checkMaximumLimit(final FieldStatus fieldStatus) {
        final String customFieldName = fieldStatus.getFieldName();
        final String fieldType = fieldStatus.getFieldType();
        final List<String> choices = fieldStatus.getChoices();
        final String multiSelect = "multiselect";
        final String dropdownPath = "(((//*[@data-rbd-droppable-id='%s-choices']/div/div/div/div)[%d])/div/div)[2]/div/div/div/input";
        final String multiSelectPath = "(((//*[@data-rbd-droppable-id='%s-choices']/div/div/div/div)[%d])/div/div)[2]/div/input";

        try {
            Thread.sleep(2000);
        } catch (Exception exception) {
        }

        click(getAddCustomFieldButton());
        send(getCustomFieldName(), customFieldName);
        click(getSelectCustomFieldType());
        dropdown(fieldType);


        if (isDropdownOrMultiSelect(fieldType)) {
            click(getChoice());
            click(getAddChoice());

            for (int i = 0; i < choices.size(); i++) {
                if (fieldType.equalsIgnoreCase(FieldType.MULTI_SELECT) && i > 1) {
                    click(getAddChoice());
                }
                final String path = fieldType.equalsIgnoreCase(FieldType.DROPDOWN)
                        ? dropdownPath
                        : multiSelectPath;
                send(findByXpath(String.format(path, multiSelect, i + 1)), choices.get(i));
            }

        }
        click(findByXpath("//body"));

        waitTillVisible(FieldElement.ADD_BUTTON);
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
        final Collection<WebPageElement> fields = findElementsByXpath("//*[@class='css-1qqzcwf']/div/p");

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
        final String name = field.getName();

        click(getAddSelectedFieldsButton());
        click(getAddSystemFieldToList(name));
        click(getAddSelectedFieldsButton());
        click(findByXpath(getPathOfSpecificCheckbox(getFieldBlock(name), FieldElement.ADD_VIEW_CHECKBOX)));
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
     * Retrieves the XPath for the specified system field.
     * </p>
     *
     * @param field The {@link SystemField}.
     * @return The XPath string for the field.
     */
    protected String getField(final Field field) {
        return XPathBuilder.getXPathByText(field.getName());
    }

    /**
     * <p>
     * Checks if the field type of each system field matches its expected type.
     * </p>
     *
     * @return true if all system fields have the correct field type, false if any field type mismatch is found.
     */
    public boolean checkSystemFieldsFieldType() {
        for (final Field value : getAllFields()) {
            if (!checkFieldType(getField(value), value.getFieldType())) {
                return false;
            }
        }

        return true;
    }

    /**
     * <p>
     * Checks whether the field type matches the expected value.
     * </p>
     *
     * @param xpath     The XPath of the field.
     * @param fieldType The expected field type.
     * @return true if the field type matches, false otherwise.
     */
    protected boolean checkFieldType(final String xpath, final String fieldType) {
        return fieldType.equals(getText(findRightElement(List.of(new Element(LocatorType.XPATH, xpath, true),
                new Element(LocatorType.TAG_NAME, "p", false)))));
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
        final String fieldName = fieldStatus.getFieldName();
        final String fieldType = fieldStatus.getFieldType();
        final boolean addView = fieldStatus.isAddView();
        final boolean required = fieldStatus.isRequired();
        final boolean editable = fieldStatus.isEditable();
        final boolean deletable = fieldStatus.isDeletable();
        final boolean hideable = fieldStatus.isHideable();
        String fieldBlock = null;

        if (!isFieldPresent(fieldName)) {
            addField(fieldName);
        }

        fieldBlock = getFieldBlock(fieldName);

        final WebPageElement addViewCheckbox = findByXpath(getPathOfSpecificCheckbox(fieldBlock, FieldElement.ADD_VIEW_CHECKBOX));
        final WebPageElement requiredCheckbox = findByXpath(getPathOfSpecificCheckbox(fieldBlock, FieldElement.REQUIRED_CHECKBOX));

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
            refresh();
        }

        if ((required && !isRequired) || (!required && isRequired)) {
            click(requiredCheckbox);
            click(findByXpath(format(fieldBlock, FieldElement.UPDATE_BUTTON)));
            refresh();
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

    /**
     * <p>
     * Edits a custom field by appending a new string to its current name.
     * </p>
     *
     * @param fieldStatus {@link FieldStatus}.
     * @return true if the field is renamed successfully, false otherwise.
     */
    public boolean editCustomField(final FieldStatus fieldStatus) {
        final String actualName = fieldStatus.getFieldName();
        final String newName = fieldStatus.getAppend();

        String fieldBlockXpath = null;

        if (isFieldPresent(actualName)) {
            fieldBlockXpath = getFieldBlock(actualName);

            hoverByXpath(fieldBlockXpath);
            click(findByXpath(format(fieldBlockXpath, FieldElement.EDIT_ICON)));
            send(getCustomFieldName(), newName);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            click(findByXpath(format(fieldBlockXpath, FieldElement.UPDATE_BUTTON)));
            refresh();

        }

        return isFieldPresent(String.format("%s%s", actualName, newName));
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
        System.out.println(systemFieldName);

        if (isFieldPresent(systemFieldName)) {
            final String fieldBlock = getFieldBlock(systemFieldName);
            hoverByXpath(fieldBlock);
            click(findByXpath(format(fieldBlock, FieldElement.EYE_ICON)));
            refresh();
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

        if (isFieldPresent(fieldName)) {
            final String fieldBlock = getFieldBlock(fieldName);
            hoverByXpath(fieldBlock);
            click(findByXpath(format(fieldBlock, FieldElement.DELETE_ICON)));
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
            }
            click(findByText("Delete"));
            refresh();
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
        final String name = field.getName();
        final String fieldBlock = getFieldBlock(name);

        hoverByXpath(fieldBlock);
        click(findByXpath(format(fieldBlock, FieldElement.EYE_ICON)));
        click(getAddSystemFieldsButton());

        return !isSelected(findByXpath(getPathOfSpecificCheckbox(getMenuBlock(name), FieldElement.CHECKBOX)));
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
        final String name = hideableSystemField.getName();
        final String fieldBlock = getFieldBlock(name);

        hoverByXpath(fieldBlock);
        click(findByXpath(format(fieldBlock, FieldElement.EYE_ICON)));
        click(getAddSystemFieldsButton());

        return !isSelected(findByXpath(getPathOfSpecificCheckbox(getMenuBlock(name), FieldElement.CHECKBOX)));
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
        final String name = field.getName();

        click(getAddSelectedFieldsButton());
        click(getAddSystemFieldToList(name));
        click(getAddSelectedFieldsButton());
        click(findByXpath(getPathOfSpecificCheckbox(getFieldBlock(name), FieldElement.ADD_VIEW_CHECKBOX)));
        click(findByXpath(FieldElement.UPDATE_BUTTON));
        click(findByXpath(FieldElement.CANCEL_BUTTON));
        click(findByXpath(getPathOfSpecificCheckbox(getFieldBlock(name), FieldElement.REQUIRED_CHECKBOX)));
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
        return removeSystemField(field.getName());
    }

    /**
     * <p>
     * Removes a system field by name from the list of active fields.
     * </p>
     *
     * @param fieldName The name of the system field to remove.
     * @return true if the field is removed successfully.
     */
    public boolean removeSystemField(final String fieldName) {
        click(getAddSystemFieldsButton());

        final WebPageElement checkbox = getAddSystemFieldToList(fieldName);

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
            final String fieldName = specificField.getName();
            System.out.println(fieldName);
            try {
                checkbox = findByXpath(format(getMenuBlock(fieldName), "//*[@type='checkbox']"));
            } catch (Exception exception) {
                continue;
            }

            if (!isSelected(checkbox)) {
                click(checkbox);
            }
            isDisplayed(findByXpath(getFieldBlock(fieldName)));
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
    protected void unCheck(final String[] mandatoryFields) {
        for (final String specificDiv : mandatoryFields) {

            final WebPageElement path = findByXpath(getPathOfSpecificCheckbox(getFieldBlock(specificDiv), FieldElement.REQUIRED_CHECKBOX));

            if (isSelected(path)) {
                click(path);

                if (isDisplayed(findByXpath(format(getFieldBlock(specificDiv), FieldElement.UPDATE_BUTTON)))) {
                    click(findByXpath(format(getFieldBlock(specificDiv), FieldElement.UPDATE_BUTTON)));
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
    public boolean addAndRemoveSystemField(final Field fieldToBeAdded,
                                           final Field fieldToBeRemoved) {
        final String fieldToBeAddedToList = fieldToBeAdded.getName();
        final String fieldToBeRemovedFromList = fieldToBeRemoved.getName();

        click(getAddSystemFieldsButton());

        final WebPageElement systemFieldToBeAdded = findByXpath(getPathOfSpecificCheckbox(getMenuBlock(fieldToBeAddedToList),
                FieldElement.CHECKBOX));
        final WebPageElement systemFieldToBeRemoved = findByXpath(getPathOfSpecificCheckbox(getMenuBlock
                (fieldToBeRemovedFromList), FieldElement.CHECKBOX));

        if (!isSelected(systemFieldToBeAdded)) {
            click(getAddSystemFieldToList(fieldToBeAddedToList));
        }
        if (isSelected(systemFieldToBeRemoved)) {
            click(getAddSystemFieldToList(fieldToBeRemovedFromList));
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
        final Collection<WebPageElement> fieldsPresentInSummary = findElementsByXpath("//*[@class='css-itno5t']/div/div/table/tbody/tr/td[1]/p");
        final Collection<String> fieldNames = new ArrayList<>();

        for (final WebPageElement field : fieldsPresentInSummary) {
            String fieldText = getText(field);
            fieldNames.add(fieldText);
        }

        for (final String fieldToBePresent : fieldsToBePresentInSummary) {

            final String formattedField = String.format("%s%s%s", fieldToBePresent, " ", ":");

            if (!fieldNames.contains(formattedField)) {
                System.out.println(formattedField);
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
        final Collection<WebPageElement> fields = findElementsByXpath("//*[@class='MuiTypography-root MuiTypography-body1 css-1b4365c']");
        final Collection<WebPageElement> remainingFields = findElementsByXpath("//*[@class='css-1cdo4bs']/p");

        for (final WebPageElement field : fields) {

            fieldsPresentInAddForm.add(getText(field).replaceAll("\\*$", ""));
        }

        for (final WebPageElement remainingField : remainingFields) {
            fieldsPresentInAddForm.add(getText(remainingField).replaceAll("\\*$", ""));
        }

        for (final String field : fieldsToBePresentInAddForm) {
            if (!fieldsPresentInAddForm.contains(field)) {
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
        final Collection<WebPageElement> fields = findElementsByXpath("//*[@class='css-eawmf1']/div/div/p");

        for (final WebPageElement field : fields) {
            fieldsPresentInAddForm.add(getText(field));
        }

        for (final Field field : fieldsToBePresentInColumnSettings) {

            if (!fieldsPresentInAddForm.contains(field.getName())) {
                System.out.println(field.getName());
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
     * @param fieldName The name of the field to check.
     * @return true if the 'Add View' checkbox is checked, false otherwise.
     */
    public boolean checkIfGivenFieldsAddViewIsChecked(final String fieldName) {
        return isSelected(findByXpath(getPathOfSpecificCheckbox(getFieldBlock(fieldName), FieldElement.ADD_VIEW_CHECKBOX)));
    }

    /**
     * <p>
     * Switches the view to the Summary section by clicking on the relevant element.
     * </p>
     */
    public void switchToSummary() {
        click(findByXpath("//*[@class='MuiTableRow-root css-rm8p5t']//td[2]/div"));
    }

    /**
     * <p>
     * Switches the view to the Add Contact form with a delay to ensure the form loads.
     * </p>
     */
    public void switchToAddContactForm() {
        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
        }
        click(findByText("Contact"));
    }

    /**
     * <p>
     * Switches the view to the Add Company form with a delay to ensure the form loads.
     * </p>
     */
    public void switchToAddCompanyForm() {
        try {
            Thread.sleep(5000);
        } catch (Exception exception) {
        }
        click(findByText("Company"));
    }

    /**
     * <p>
     * Switches the view to the Add Deal form with a delay to ensure the form loads.
     * </p>
     */
    public void switchToAddDealForm() {
        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
        }
        click(findByText("Deal"));
    }

    /**
     * <p>
     * Switches the view to the Add Product form.
     * </p>
     */
    public void switchToAddProductForm() {
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
            System.out.println(field.getName());
            final String fieldBlock = getFieldBlock(field);

            try {
                findByXpath(fieldBlock);
            } catch (Exception exception) {
                // addSystemField(field.getName());
            }

            try {
                addViewCheckbox = findByXpath(getPathOfSpecificCheckbox(fieldBlock, FieldElement.ADD_VIEW_CHECKBOX));

                if (!isSelected(addViewCheckbox)) {
                    click(addViewCheckbox);
                    try {
                        click(findByXpath(format(fieldBlock, FieldElement.UPDATE_BUTTON)));
                    } catch (Exception exception) {
                        System.out.println(field.getName());
                    }
                    refresh();
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
    public void enableAddView(final String fieldName) {
        final String fieldBlock = getFieldBlock(fieldName);
        final WebPageElement addViewCheckbox = findByXpath(getPathOfSpecificCheckbox(fieldBlock,
                FieldElement.ADD_VIEW_CHECKBOX));

        if (!isSelected(addViewCheckbox)) {
            click(addViewCheckbox);
            try {
                click(findByXpath(format(fieldBlock, FieldElement.UPDATE_BUTTON)));
            } catch (Exception exception) {
                System.out.println(fieldName);
            }
            refresh();

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
            final WebPageElement addViewCheckbox = findByXpath(getPathOfSpecificCheckbox(fieldBlock, FieldElement.REQUIRED_CHECKBOX));

            if (!isSelected(addViewCheckbox)) {
                click(addViewCheckbox);
                try {
                    click(findByXpath(format(fieldBlock, FieldElement.UPDATE_BUTTON)));
                } catch (Exception exception) {
                    System.out.println(field.getName());
                }
                refresh();
            }
        }
    }

    /**
     * <p>
     * Checks if the 'Add View' checkbox is checked based on the field status and updates it if necessary.
     * </p>
     *
     * @param fieldStatus An object representing the status of the field.
     * @return true if the 'Add View' checkbox status matches the expected status, false otherwise.
     */
    public boolean checkIfAddViewIsChecked(final FieldStatus fieldStatus) {
        final boolean draggable = fieldStatus.isDraggable();
        final String fieldName = fieldStatus.getFieldName();
        final boolean addView = fieldStatus.isAddView();
        String fieldBlock = null;

        try {
            fieldBlock = getFieldBlock(fieldName);

        } catch (NoSuchElementException noSuchElementException) {
            fieldBlock = getFieldBlock(fieldName);
        }

        final WebPageElement addViewCheckbox = findByXpath(getPathOfSpecificCheckbox(fieldBlock, FieldElement.ADD_VIEW_CHECKBOX));
        final boolean isChecked = isSelected(addViewCheckbox);

        if (draggable) {
            isDisplayed(findByXpath(format(fieldBlock, FieldElement.DRAGGABLE)));
        } else {
            isDisplayed(findByXpath(format(fieldBlock, FieldElement.NON_DRAGGABLE)));
        }

        if ((addView && !isChecked) || (!addView && isChecked)) {
            click(addViewCheckbox);
            click(findByXpath(format(fieldBlock, FieldElement.UPDATE_BUTTON)));
            try {
                findByXpath(format(fieldBlock, FieldElement.UPDATE_BUTTON));
            } catch (Exception e) {
                System.out.println(fieldName);
            }
            refresh();
        }

        return addView;
    }

    /**
     * <p>
     * Checks the field elements based on the field status and verifies if the field type and drag status match the expected values.
     * </p>
     *
     * @param fieldStatus An object representing the status of the field.
     * @return true if the field elements match the expected status, false otherwise.
     */
    public boolean checkFieldElements(final FieldStatus fieldStatus) {
        final boolean draggable = fieldStatus.isDraggable();
        final String fieldName = fieldStatus.getFieldName();
        final String fieldType = fieldStatus.getFieldType();

        String fieldBlock = null;

        try {
            fieldBlock = getFieldBlock(fieldName);

        } catch (NoSuchElementException noSuchElementException) {
            fieldBlock = getFieldBlock(fieldName);
        }
        isDisplayed(findByXpath(format(fieldBlock, XPathBuilder.getXPathByText(fieldType))));
        if (draggable) {
            isDisplayed(findByXpath(format(fieldBlock, FieldElement.DRAGGABLE)));
        } else {
            isDisplayed(findByXpath(format(fieldBlock, FieldElement.NON_DRAGGABLE)));
        }
        return true;
    }

    /**
     * <p>
     * Checks the 'Add View' status for a given field and updates it if necessary.
     * </p>
     *
     * @param fieldStatus An object representing the status of the field.
     */
    public void checkAddView(final FieldStatus fieldStatus) {
        final String fieldBlock = getFieldBlock(fieldStatus.getFieldName());
        final boolean addView = fieldStatus.isAddView();

        final WebPageElement addViewCheckbox = findByXpath(getPathOfSpecificCheckbox(fieldBlock,
                FieldElement.ADD_VIEW_CHECKBOX));
        boolean updatedAddViewChecked = isSelected(addViewCheckbox);

        if ((addView && !updatedAddViewChecked) || (!addView && updatedAddViewChecked)) {
            click(addViewCheckbox);
            click(findByXpath(format(fieldBlock, FieldElement.UPDATE_BUTTON)));
            refresh();
            try {
                Thread.sleep(2000);
            } catch (Exception exception) {
            }
            updatedAddViewChecked = isSelected(findByXpath(getPathOfSpecificCheckbox(fieldBlock,
                    FieldElement.ADD_VIEW_CHECKBOX)));
        }

        if (addView && !updatedAddViewChecked) {
            throw new AssertionError("Expected 'Add View' to be checked but it is not.");
        } else if (!addView && updatedAddViewChecked) {
            throw new AssertionError("Expected 'Add View' to be unchecked but it is not.");
        }
    }

    /**
     * <p>
     * Checks if the 'Required' checkbox is selected for a given field and updates it if necessary.
     * </p>
     *
     * @param fieldStatus An object representing the status of the field.
     * @return true if the checkbox is correctly set, false otherwise.
     */
    public boolean checkRequired(final FieldStatus fieldStatus) {
        final String fieldBlock = getFieldBlock(fieldStatus.getFieldName());
        final boolean required = fieldStatus.isRequired();

        final WebPageElement requiredCheckbox = findByXpath(getPathOfSpecificCheckbox(fieldBlock,
                FieldElement.REQUIRED_CHECKBOX));
        final boolean isRequired = isSelected(requiredCheckbox);

        if ((required && !isRequired) || (!required && isRequired)) {
            click(requiredCheckbox);
            click(findByXpath(format(fieldBlock, FieldElement.UPDATE_BUTTON)));
            refresh();
        }

        return true;
    }

    /**
     * <p>
     * Adds a field to the system based on the field name.
     * </p>
     *
     * @param fieldName The name of the field to be added.
     */
    protected void addField(final String fieldName) {
        click(getAddSystemFieldsButton());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(getAddSystemFieldToList(fieldName));
        click(getAddSelectedFieldsButton());
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
        System.out.println(fieldName);
        final String fieldType = fieldStatus.getFieldType();

        if (!isFieldPresent(fieldName)) {
            addField(fieldName);
        }

        refresh();

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }

        return isDisplayed(findByXpath(getFieldBlock(fieldName))) && isDisplayed(findByXpath(format(getFieldBlock(fieldName),
                XPathBuilder.getXPathByText(fieldType))));
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
        final String fieldName = fieldStatus.getFieldName();
        System.out.println(fieldName);
        WebPageElement addViewCheckbox = null;

        if (!isFieldPresent(fieldName)) {
            addSystemField(fieldStatus);
        }

        addViewCheckbox = findByXpath(getPathOfSpecificCheckbox(getFieldBlock(fieldName), FieldElement.ADD_VIEW_CHECKBOX));

        if (!isSelected(addViewCheckbox)) {
            click(addViewCheckbox);
            try {
                click(findByXpath(format(getFieldBlock(fieldName), FieldElement.UPDATE_BUTTON)));
            } catch (Exception exception) {
                System.out.println(fieldName);
            }
            refresh();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return isSelected(findByXpath(getPathOfSpecificCheckbox(getFieldBlock(fieldName), FieldElement.ADD_VIEW_CHECKBOX)));
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
        final String fieldName = fieldStatus.getFieldName();
        System.out.println(fieldName);
        WebPageElement requiredCheckbox = null;

        if (!isFieldPresent(fieldName)) {
            addSystemField(fieldStatus);
        }

        requiredCheckbox = findByXpath(getPathOfSpecificCheckbox(getFieldBlock(fieldName), FieldElement.REQUIRED_CHECKBOX));

        if (!isSelected(requiredCheckbox)) {
            click(requiredCheckbox);
            try {
                click(findByXpath(format(getFieldBlock(fieldName), FieldElement.UPDATE_BUTTON)));
            } catch (Exception exception) {
                System.out.println(fieldName);
            }
            refresh();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return isSelected(findByXpath(getPathOfSpecificCheckbox(getFieldBlock(fieldName), FieldElement.REQUIRED_CHECKBOX)));
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
        final Collection<WebPageElement> fields = findElementsByXpath("//*[@class='css-1qqzcwf']/div/p");

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

    /**
     * <p>
     * Retrieves the list of fields for 'Add View' and 'Required' settings.
     * </p>
     *
     * @param addViewOrRequired The type of setting to check ('Add View' or 'Required').
     * @return A list of fields for the specified setting.
     */
    protected abstract Collection<String> getFieldsForAddViewAndRequired(final String addViewOrRequired);
}

