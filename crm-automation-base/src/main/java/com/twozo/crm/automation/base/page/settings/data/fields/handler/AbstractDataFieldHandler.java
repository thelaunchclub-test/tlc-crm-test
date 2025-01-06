package com.twozo.crm.automation.base.page.settings.data.fields.handler;

import com.twozo.crm.automation.base.page.handler.AbstractPageHandler;
import com.twozo.crm.automation.base.page.settings.data.fields.DataFieldsPage;
import com.twozo.crm.automation.base.page.settings.data.fields.FieldStatus;
import com.twozo.crm.automation.base.page.settings.data.fields.contact.field.ContactField;
import com.twozo.crm.automation.base.page.settings.data.fields.field.DependableField;
import com.twozo.crm.automation.base.page.settings.data.fields.field.Field;
import com.twozo.crm.automation.base.page.settings.data.fields.field.FieldAttribute;
import com.twozo.crm.automation.base.page.settings.data.fields.field.SystemField;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.*;

public abstract class AbstractDataFieldHandler extends AbstractPageHandler implements DataFieldHandler{

    DataFieldsPage dataFieldsPage = null;

    protected AbstractDataFieldHandler(WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    @Override
    public boolean verifyDefaultSystemFields() {
        for (final Record field : getDefaultSystemFieldElements()) {

            if (!checkFieldAttributes(field)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean hideField(final String systemFieldName) {
        LOGGER.info(systemFieldName);
        String fieldBlock;
        String eyeIconButton;

        if (dataFieldsPage.isFieldPresent(systemFieldName)) {

            if (Objects.equals(ContactField.SUBSCRIPTION_STATUS.getName(), systemFieldName)) {
                fieldBlock = getDependableBlock(systemFieldName);
            } else {
                fieldBlock = getFieldBlock(systemFieldName);
            }
            eyeIconButton = formatTwoStrings(fieldBlock, FieldAttribute.EYE_ICON);
            hoverByXpath(fieldBlock);

            waitTillClickable(eyeIconButton);
            click(findByXpath(eyeIconButton));
        }

        return !isFieldPresent(systemFieldName);
    }

    /**
     * <p>
     * Checks if the notification for exceeding a limit (e.g., maximum fields) is displayed.
     * </p>
     *
     * @return true if the notification is displayed, false otherwise.
     */
    @Override
    public boolean isLimitExceededNotificationDisplayed() {
        return isDisplayed(dataFieldsPage.getLimitExceededForCustomField());
    }

    @Override
    public boolean isNonDraggableIconDisplayed(final String blockName) {
        return isDisplayed(dataFieldsPage.getNonDraggableIcon(blockName));
    }

    /**
     * <p>
     * Checks if a specific element within a field's block is displayed.
     * </p>
     *
     * @param fieldName   The name of the field block.
     * @param elementName The name of the element to check within the block.
     */
    @Override
    public boolean isFieldElementDisplayed(final String fieldName, final String elementName) {
        return isDisplayed(dataFieldsPage.getFieldElement(fieldName, elementName));
    }

    @Override
    public boolean isDependableFieldElementDisplayed(final String dependableFieldName, final String element) {
        return isDisplayed(dataFieldsPage.getDependableFieldElement(dependableFieldName, element));
    }

    @Override
    public boolean areAllChoicesPresent(final String[] options) {
        final Collection<String> choices = new ArrayList<>();
        final Collection<WebPageElement> choicesAsElements = dataFieldsPage.getDropdownChoices();

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
    @Override
    public boolean checkFieldAttributes(final Record field) {
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
     * Retrieves the {@link Collection} of fields.
     * </p>
     *
     * @return A {@link Collection} of fields.
     */
    @Override
    public Collection<String> getFields() {
        final Collection<String> fieldsPresent = new ArrayList<>();

        for (final WebPageElement field : dataFieldsPage.getFields()) {
            fieldsPresent.add(getText(field));
        }

        return fieldsPresent;
    }

    /**
     * <p>
     * Checks if a field with the specified name is present in the current set of fields.
     * </p>
     *
     * @param fieldName The {@link FieldStatus}.
     * @return true if the field is present, false otherwise.
     */
    @Override
    public boolean isFieldNotPresent(final String fieldName) {
        return !getFields().contains(fieldName);
    }

    /**
     * <p>
     * Adds a field to the system based on the field name.
     * </p>
     *
     * @param fieldName The name of the field to be added.
     */
    @Override
    public void addSystemField(final String fieldName) {
        if (isFieldNotPresent(fieldName)) {
            click(dataFieldsPage.getAddSystemFieldsButton());
            click(dataFieldsPage.getCheckboxOf(fieldName));
            click(dataFieldsPage.getAddSelectedFieldsButton());
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
    @Override
    public boolean addSystemField(final FieldStatus fieldStatus) {
        final String fieldName = fieldStatus.getFieldName();
        final String fieldType = fieldStatus.getFieldType();

        addSystemField(fieldName);

        return isDisplayed(dataFieldsPage.getFieldName(fieldName)) && isDisplayed(dataFieldsPage.getFieldType(fieldName, fieldType));
    }

    @Override
    public void addSystemFields(final List<String> fields) {
        final List<String> fieldsToBeAdded = new ArrayList<>(fields);
        String path;

        fieldsToBeAdded.removeAll(getFields());

        if (!fieldsToBeAdded.isEmpty()) {
            click(dataFieldsPage.getAddSystemFieldsButton());

            for (final String field : fieldsToBeAdded) {

                final WebPageElement checkboxOfField = dataFieldsPage.getCheckboxOf(field);

                if (!isSelected(checkboxOfField)) {
                    click(checkboxOfField);
                }
            }

            click(dataFieldsPage.getAddSelectedFieldsButton());
        }
    }

    /**
     * <p>
     * Enables the 'Add View' checkbox for a specific field and updates the changes.
     * </p>
     *
     * @param fieldName The name of the field to update.
     */
    @Override
    public boolean enableAddView(final String fieldName) {
        final WebPageElement addViewCheckboxOfFieldName = dataFieldsPage.getAddViewCheckboxOf(fieldName);

        if (!isSelected(addViewCheckboxOfFieldName)) {
            click(addViewCheckboxOfFieldName);
            click(dataFieldsPage.getUpdateButtonOf(fieldName));
        }

        return isSelected(dataFieldsPage.getAddViewCheckboxOf(fieldName));
    }

    /**
     * <p>
     * Enables the 'Add View' checkbox for a given field and updates the changes.
     * </p>
     *
     * @param fieldStatus An object representing the status of the field.
     * @return true if the 'Add View' checkbox is selected, false otherwise.
     */
    @Override
    public boolean enableAddView(final FieldStatus fieldStatus) {
        return enableAddView(fieldStatus.getFieldName());
    }

    boolean enableRequired(final String fieldName) {
        final WebPageElement requiredCheckboxOfFieldName = dataFieldsPage.getRequiredCheckboxOf(fieldName);


        if (!isSelected(requiredCheckboxOfFieldName)) {
            click(requiredCheckboxOfFieldName);
            click(dataFieldsPage.getUpdateButtonOf(fieldName));
        }

        return isSelected(dataFieldsPage.getRequiredCheckboxOf(fieldName));
    }

    @Override
    public boolean enableRequired(final FieldStatus fieldStatus) {
        return enableRequired(fieldStatus.getFieldName());
    }

    private boolean enableAutoGeneratingField(final FieldStatus fieldStatus, final String addViewOrRequired) {
        final String fieldName = fieldStatus.getFieldName();

        if (isFieldNotPresent(fieldName)) {
            addSystemField(fieldStatus);
        }

        WebPageElement checkbox;
        switch (addViewOrRequired) {
            case FieldAttribute.ADD_VIEW_CHECKBOX -> checkbox = dataFieldsPage.getAddViewCheckboxOf(fieldName);
            case FieldAttribute.REQUIRED_CHECKBOX -> checkbox = dataFieldsPage.getRequiredCheckboxOf(fieldName);
            default -> {
                return false;
            }
        }

        if (!isSelected(checkbox)) {
            click(checkbox);
        }
        return isSelected(checkbox);
    }

    /**
     * <p>
     * Enables the 'Add View' checkbox for a given field and updates the changes.
     * </p>
     *
     * @param fieldStatus An object representing the status of the field.
     * @return true if the 'Add View' checkbox is selected, false otherwise.
     */
    @Override
    public boolean enableAddViewForAutoGeneratingField(final FieldStatus fieldStatus) {
        return enableAutoGeneratingField(fieldStatus, FieldAttribute.ADD_VIEW_CHECKBOX);
    }

    @Override
    public boolean enableRequiredForAutoGeneratingField(final FieldStatus fieldStatus) {
        return enableAutoGeneratingField(fieldStatus, FieldAttribute.REQUIRED_CHECKBOX);
    }

    @Override
    public boolean setDefaultChoice(final boolean isDependableField, final String fieldName, final String choice) {
        WebPageElement choicesButton = isDependableField
                ? dataFieldsPage.getChoicesButtonOfDependableField(fieldName)
                : dataFieldsPage.getChoicesButtonOfField(fieldName);

        click(choicesButton);
        final WebPageElement radioButtonOfChoice = dataFieldsPage.getRadioButtonOfChoice(choice);
        String currentLabel = getAttribute(radioButtonOfChoice, "aria-label");

        if (Objects.equals("Mark as Default Choice", currentLabel)) {
            click(radioButtonOfChoice);
            click(dataFieldsPage.getBody());
            click(dataFieldsPage.getUpdateButtonOf(fieldName));

            choicesButton = isDependableField
                    ? dataFieldsPage.getChoicesButtonOfDependableField(fieldName)
                    : dataFieldsPage.getChoicesButtonOfField(fieldName);

            click(choicesButton);

            currentLabel = getAttribute(dataFieldsPage.getRadioButtonOfChoice(choice), "aria-label");
        }

        return Objects.equals("Mark as Default Choice", currentLabel);
    }

    @Override
    public Collection<String> getFieldsEnabledAsAddView() {
        final Collection<String> fields = new ArrayList<>();
        final Collection<WebPageElement> fieldsEnabledAsAddView = dataFieldsPage.getFieldsEnabledAsAddView();

        for (final WebPageElement webPageElement : fieldsEnabledAsAddView) {
            fields.add(getText(webPageElement));
        }

        return fields;
    }

    @Override
    public Collection<String> getFieldsEnabledAsRequired() {
        final Collection<String> fields = new ArrayList<>();
        final Collection<WebPageElement> fieldsEnabledAsAddView = dataFieldsPage.getFieldsEnabledAsRequired();

        for (final WebPageElement webPageElement : fieldsEnabledAsAddView) {
            fields.add(getText(webPageElement));
        }

        return fields;
    }

    private void setFieldsForAddFormAs(final List<String> fields, final String addViewOrRequired) {
        final List<String> fieldsToBeAdded = new ArrayList<>(fields);

        fieldsToBeAdded.removeAll(getFields());

        if (!fieldsToBeAdded.isEmpty()) {
            click(dataFieldsPage.getAddSystemFieldsButton());

            for (final String field : fieldsToBeAdded) {

                final WebPageElement checkboxOfField = dataFieldsPage.getCheckboxOf(field);

                if (!isSelected(checkboxOfField)) {
                    click(checkboxOfField);
                }
            }

            click(dataFieldsPage.getAddSystemFieldsButton());
        }

        for (final String field : fields) {

            if (addViewOrRequired.equals(FieldAttribute.ADD_VIEW_CHECKBOX)) {
                enableAddView(field);
            } else {
                enableRequired(field);
            }
        }
    }

    private void setFieldForAddFormAs(final String field, final String addViewOrRequired) {
        addSystemField(field);

        if (addViewOrRequired.equals(FieldAttribute.ADD_VIEW_CHECKBOX)) {
            enableAddView(field);
        } else {
            enableRequired(field);
        }
    }

    @Override
    public void setFieldsAddViewEnabled(final List<String> fields) {
        setFieldsForAddFormAs(fields, FieldAttribute.ADD_VIEW_CHECKBOX);
    }

    @Override
    public void setFieldsRequiredEnabled(final List<String> fields) {
        setFieldsForAddFormAs(fields, FieldAttribute.REQUIRED_CHECKBOX);
    }

    @Override
    public void setFieldAddViewEnabled(final String field) {
        setFieldForAddFormAs(field, FieldAttribute.ADD_VIEW_CHECKBOX);
    }

    @Override
    public void setFieldRequiredEnabled(final String field) {
        setFieldForAddFormAs(field, FieldAttribute.REQUIRED_CHECKBOX);
    }

    public abstract Collection<Field> getDefaultFields();

    public abstract Field[] getAllFields();

    public abstract Collection<String> getMandatoryFields();

    public abstract boolean verifyNonDraggableFields();

    public abstract Collection<Record> getDefaultSystemFieldElements();

    public abstract boolean isDefaultFieldsVisibleInSummary();

    public abstract boolean uncheckMandatoryFields();
}
