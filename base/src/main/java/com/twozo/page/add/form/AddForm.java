package com.twozo.page.add.form;

import com.twozo.page.BasePage;
import com.twozo.page.settings.data.fields.company.field.CompanyField;
import com.twozo.page.settings.data.fields.contact.field.ContactField;
import com.twozo.page.settings.data.fields.deal.field.DealField;
import com.twozo.page.settings.data.fields.field.Field;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AddForm extends BasePage {

    protected static final String SAVE_BUTTON = LOCATORS.get("crm.deal.pipeline.form.save");
    protected static final String SAVE_AND_NEW_BUTTON = LOCATORS.get("crm.deal.pipeline.form.save.and.new");
    protected static final String CLOSE_BUTTON = LOCATORS.get("crm.deal.pipeline.close");
    protected static final String TEXT_FIELD = LOCATORS.get("crm.add.form.text.field");
    protected static final String NUMBER_FIELD = LOCATORS.get("crm.add.form.number.field");
    protected static final String DROPDOWN_FIELD = LOCATORS.get("crm.add.form.dropdown.field");
    protected static final String ADDRESS_FIELD = LOCATORS.get("crm.add.form.address.field");
    protected static final String ADDRESS_LINE_1 = LOCATORS.get("crm.add.form.address.placeholder.address.line.1");
    protected static final String ADDRESS_LINE_2 = LOCATORS.get("crm.add.form.address.placeholder.address.line.2");
    protected static final String CITY = LOCATORS.get("crm.add.form.address.placeholder.address.city");
    protected static final String STATE = LOCATORS.get("crm.add.form.address.placeholder.address.state");
    protected static final String COUNTRY = LOCATORS.get("crm.add.form.address.placeholder.address.country");
    protected static final String PINCODE = LOCATORS.get("crm.add.form.address.placeholder.address.pincode");
    protected static final String MAX_TEXT_LIMIT_EXCEEDED = LOCATORS.get("crm.notification.max.text.limit.exceeded");
    protected static final String MAX_NUMBER_LIMIT_EXCEEDED = LOCATORS.get("crm.notification.max.number.limit.exceeded");
    protected static final String INVALID_MAIL_ID_ = LOCATORS.get("crm.notification.invalid.mail.id");

    protected AddForm(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    protected WebPageElement getTextFieldWebPageElement(final String fieldName) {
        return findByXpath(String.format(TEXT_FIELD, fieldName));
    }

    protected WebPageElement getNumberFieldWebPageElement(final String fieldName) {
        return findByXpath(String.format(NUMBER_FIELD, fieldName));
    }

    protected WebPageElement getDropdownFieldWebPageElement(final String fieldName) {
        return findByXpath(String.format(DROPDOWN_FIELD, fieldName));
    }

    protected WebPageElement getAddressFieldWebPageElement(final String textFieldName) {
        return findByXpath(String.format(ADDRESS_FIELD, textFieldName));
    }

    protected Collection<WebPageElement> getAddFormFields() {
        return findElementsByXpath(LOCATORS.get("crm.add.form.fields"));
    }

    protected WebPageElement getSaveButton() {
        return findByText(SAVE_BUTTON);
    }

    protected WebPageElement getCompany() {
        return getTextFieldWebPageElement(ContactField.COMPANY.getName());
    }

    protected WebPageElement getSource() {
        return getDropdownFieldWebPageElement(ContactField.SOURCE.getName());
    }

    protected WebPageElement getTerritory() {
        return getDropdownFieldWebPageElement(ContactField.TERRITORY.getName());
    }

    protected WebPageElement getSalesOwner() {
        return getDropdownFieldWebPageElement(ContactField.SALES_OWNER.getName());
    }

    protected WebPageElement getTags() {
        return getTextFieldWebPageElement(ContactField.TAGS.getName());
    }

    protected WebPageElement getAddressLine1() {
        return getAddressFieldWebPageElement(ADDRESS_LINE_1);
    }

    protected WebPageElement getAddressLine2() {
        return getAddressFieldWebPageElement(ADDRESS_LINE_2);
    }

    protected WebPageElement getCity() {
        return getAddressFieldWebPageElement(CITY);
    }

    protected WebPageElement getState() {
        return getAddressFieldWebPageElement(STATE);
    }

    protected WebPageElement getCountry() {
        return getDropdownFieldWebPageElement(COUNTRY);
    }

    protected WebPageElement getPincode() {
        return getAddressFieldWebPageElement(PINCODE);
    }

    protected WebPageElement getFacebook() {
        return getTextFieldWebPageElement(ContactField.FACEBOOK.getName());
    }

    protected WebPageElement getTwitter() {
        return getTextFieldWebPageElement(ContactField.TWITTER.getName());
    }

    protected WebPageElement getLinkedIn() {
        return getTextFieldWebPageElement(ContactField.LINKED_IN.getName());
    }

    protected WebPageElement getDescription() {
        return getTextFieldWebPageElement(CompanyField.DESCRIPTION.getName());
    }

    protected WebPageElement getName() {
        return getTextFieldWebPageElement(CompanyField.NAME.getName());
    }

    protected WebPageElement getType() {
        return getTextFieldWebPageElement(DealField.TYPE.getName());
    }

    public boolean isMaxTextLimitExceededNotificationDisplayed(final String fieldName) {
        return isDisplayed(findByXpath(String.format(MAX_TEXT_LIMIT_EXCEEDED, fieldName)));
    }

    public boolean isMaxNumberLimitExceededNotificationDisplayed(final String fieldName) {
        return isDisplayed(findByXpath(String.format(MAX_NUMBER_LIMIT_EXCEEDED, fieldName)));
    }

    public boolean isInvalidMailNotificationDisplayed(final String fieldName) {
        return isDisplayed(findByXpath(String.format(INVALID_MAIL_ID_, fieldName)));
    }

    public boolean verifyFieldOrderInAddForm(final Collection<String> fields) {
        final List<String> fieldsForAddView = new ArrayList<>(fields);
        final List<String> fieldsInAddForm = new ArrayList<>();

        for (final WebPageElement webPageElement : getAddFormFields()) {
            fieldsInAddForm.add(getText(webPageElement));
        }

        if (fieldsForAddView.size() != fieldsInAddForm.size()) {
            return false;
        }

        for (int i = 0; i < fieldsForAddView.size(); i++) {

            if (!fieldsForAddView.get(i).equals(fieldsInAddForm.get(i).replaceAll("\\*$", ""))) {
                return false;
            }
        }

        return true;
    }

    public boolean checkDefaultFieldsInAddForm(final Collection<Field> fields) {
        final Collection<String> defaultFields = new ArrayList<>();
        final Collection<String> fieldsInAddForm = new ArrayList<>();

        for (final Field field : fields) {
            defaultFields.add(field.getName());
        }

        for (final WebPageElement webPageElement : getAddFormFields()) {
            fieldsInAddForm.add(getText(webPageElement).replaceAll("\\*$", ""));
        }

        for (String field : defaultFields) {
            if (!fieldsInAddForm.contains(field)) {
                final Collection<String> missingFields = new ArrayList<>(defaultFields);
                missingFields.removeAll(fieldsInAddForm);

                final Collection<String> extraFields = new ArrayList<>(fieldsInAddForm);
                extraFields.removeAll(defaultFields);


                if (!missingFields.isEmpty()) {
                    System.out.println("Missing fields: " + missingFields);
                }
                if (!extraFields.isEmpty()) {
                    System.out.println("Extra fields: " + extraFields);
                }

                return false;
            }
        }

        return true;
    }


    public boolean verifyAutoGeneratingFieldsAreNotDisplayedInAddForm() {
        final List<String> fieldsInAddForm = new ArrayList<>();

        for (final WebPageElement webPageElement : getAddFormFields()) {
            fieldsInAddForm.add(getText(webPageElement).replaceAll("\\*$", ""));
        }

        for (final String autoGeneratingField : getAutoGeneratingFields()) {

            if (fieldsInAddForm.contains(autoGeneratingField)) {
                return false;
            }
        }

        return true;
    }

    protected String[] getCustomFields() {
        return new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q"
        };
    }

    protected boolean isFieldNameDisplayedInAddForm(final String fieldName) {
        return isDisplayed(findByXpath(String.format(LOCATORS.get("crm.add.form.field.name"),
                fieldName)));
    }

    public void setDataExceedingTextLimit(final Field field) {
        send(getTextFieldWebPageElement(field.getName()), faker.lorem().characters(256));
    }

    public void submitForm(){
        click(getSaveButton());
    }

    protected abstract String[] getAutoGeneratingFields();

    protected abstract String[] getAddFormSystemFields();

}
