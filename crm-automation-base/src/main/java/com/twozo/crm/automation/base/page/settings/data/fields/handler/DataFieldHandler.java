package com.twozo.crm.automation.base.page.settings.data.fields.handler;

import com.twozo.crm.automation.base.page.settings.data.fields.FieldStatus;
import com.twozo.crm.automation.base.page.settings.data.fields.contact.field.ContactField;
import com.twozo.crm.automation.base.page.settings.data.fields.field.Field;
import com.twozo.crm.automation.base.page.settings.data.fields.field.FieldAttribute;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public interface DataFieldHandler {
    boolean isLimitExceededNotificationDisplayed();

    boolean isNonDraggableIconDisplayed(final String blockName);

    boolean isFieldElementDisplayed(final String fieldName, final String elementName);

    boolean isDependableFieldElementDisplayed(final String dependableFieldName, final String element);

    boolean areAllChoicesPresent(final String[] options);

    boolean checkFieldAttributes(final Record field);

    Collection<String> getFields();

    boolean isFieldNotPresent(final String fieldName);

    void addSystemField(final String fieldName);

    boolean addSystemField(final FieldStatus fieldStatus);

    void addSystemFields(final List<String> fields);

    boolean enableAddView(final String fieldName);

    boolean enableAddView(final FieldStatus fieldStatus);

    boolean enableRequired(final String fieldName);

    boolean enableRequired(final FieldStatus fieldStatus);

    boolean enableAddViewForAutoGeneratingField(final FieldStatus fieldStatus);

    boolean enableRequiredForAutoGeneratingField(final FieldStatus fieldStatus);

    boolean setDefaultChoice(final boolean isDependableField, final String fieldName, final String choice);

    Collection<String> getFieldsEnabledAsAddView();

    Collection<String> getFieldsEnabledAsRequired();

    void setFieldsAddViewEnabled(final List<String> fields);

    void setFieldsRequiredEnabled(final List<String> fields);

    void setFieldAddViewEnabled(final String field);

    void setFieldRequiredEnabled(final String field);
    public boolean verifyDefaultSystemFields();
    public boolean hideField(final String systemFieldName);

    Collection<Field> getDefaultFields();

    Field[] getAllFields();

    Collection<String> getMandatoryFields();

    boolean verifyNonDraggableFields();

    Collection<Record> getDefaultSystemFieldElements();

    boolean isDefaultFieldsVisibleInSummary();

    boolean uncheckMandatoryFields();
}
