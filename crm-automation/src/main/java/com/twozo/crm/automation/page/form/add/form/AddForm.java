package com.twozo.crm.automation.page.form.add.form;

import com.twozo.crm.automation.page.BasePage;
import com.twozo.crm.automation.page.settings.data.fields.company.field.CompanyField;
import com.twozo.crm.automation.page.settings.data.fields.contact.field.ContactField;
import com.twozo.crm.automation.page.settings.data.fields.deal.field.DealField;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AddForm extends BasePage {

    protected static final String SAVE_BUTTON = CRM_LOCATOR_REGISTRY.get("crm.deal.pipeline.form.save");
    protected static final String SAVE_AND_NEW_BUTTON = CRM_LOCATOR_REGISTRY.get("crm.deal.pipeline.form.save.and.new");
    protected static final String CLOSE_BUTTON = CRM_LOCATOR_REGISTRY.get("crm.deal.pipeline.close");
    protected static final String TEXT_FIELD = CRM_LOCATOR_REGISTRY.get("crm.add.form.text.field");
    protected static final String NUMBER_FIELD = CRM_LOCATOR_REGISTRY.get("crm.add.form.number.field");
    protected static final String DROPDOWN_FIELD = CRM_LOCATOR_REGISTRY.get("crm.add.form.dropdown.field");
    protected static final String ADDRESS_FIELD = CRM_LOCATOR_REGISTRY.get("crm.add.form.address.field");
    protected static final String ADDRESS_LINE_1 = CRM_LOCATOR_REGISTRY.get("crm.add.form.address.placeholder.address.line.1");
    protected static final String ADDRESS_LINE_2 = CRM_LOCATOR_REGISTRY.get("crm.add.form.address.placeholder.address.line.2");
    protected static final String CITY = CRM_LOCATOR_REGISTRY.get("crm.add.form.address.placeholder.address.city");
    protected static final String STATE = CRM_LOCATOR_REGISTRY.get("crm.add.form.address.placeholder.address.state");
    protected static final String COUNTRY = CRM_LOCATOR_REGISTRY.get("crm.add.form.address.placeholder.address.country");
    protected static final String PINCODE = CRM_LOCATOR_REGISTRY.get("crm.add.form.address.placeholder.address.pincode");
    protected static final String MAX_TEXT_LIMIT_EXCEEDED = CRM_LOCATOR_REGISTRY.get("crm.notification.max.text.limit.exceeded");
    protected static final String MAX_NUMBER_LIMIT_EXCEEDED = CRM_LOCATOR_REGISTRY.get("crm.notification.max.number.limit.exceeded");
    protected static final String INVALID_MAIL_ID = CRM_LOCATOR_REGISTRY.get("crm.notification.invalid.mail.id");

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
        return findElementsByXpath(CRM_LOCATOR_REGISTRY.get("crm.add.form.fields"));
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
        return isDisplayed(findByXpath(String.format(INVALID_MAIL_ID, fieldName)));
    }

    public boolean verifyFieldOrderInAddForm(final List<String> fields) {
        final List<String> fieldsInAddForm = getAddFormFields().stream()
                .map(this::getText)
                .map(field -> field == null ? null : field.replaceAll("\\*$", ""))
                .toList();

        if (fields.size() != fieldsInAddForm.size()) {
            return false;
        }

        for (int i = 0; i < fields.size(); i++) {
            String expectedField = fields.get(i);
            String actualField = fieldsInAddForm.get(i);

            if (!Objects.equals(expectedField, actualField)) {
                return false;
            }
        }

        return true;
    }


    public boolean checkDefaultFieldsInAddForm(final List<String> fields) {
        final Set<String> fieldsInAddForm = getAddFormFields().stream()
                .map(webPageElement -> getText(webPageElement).replaceAll("\\*$", ""))
                .collect(Collectors.toSet());

        final Set<String> missingFields = new HashSet<>(fields);
        missingFields.removeAll(fieldsInAddForm);

        final Set<String> extraFields = new HashSet<>(fieldsInAddForm);
        fields.forEach(extraFields::remove);

        if (!missingFields.isEmpty()) {
            System.out.println("Missing fields: " + missingFields);
        }
        if (!extraFields.isEmpty()) {
            System.out.println("Extra fields: " + extraFields);
        }

        return missingFields.isEmpty() && extraFields.isEmpty();
    }


    public boolean verifySystemFieldsDisplayedInAddForm(final List<String> fields) {
        final List<String> addFormFields = new ArrayList<>();

        for (final WebPageElement webPageElement : findElementsByXpath(CRM_LOCATOR_REGISTRY.get("crm.add.form.fields"))) {
            addFormFields.add(getText(webPageElement));
        }

        for (final String addFormField : fields) {

            if (!addFormFields.contains(addFormField)) {
                return false;
            }
        }

        return false;
    }

    public boolean verifyCustomFieldsDisplayedInAddForm(final String fieldName) {
        final List<String> addFormFields = new ArrayList<>();

        for (final WebPageElement webPageElement : findElementsByXpath(CRM_LOCATOR_REGISTRY.get("crm.add.form.fields"))) {
            addFormFields.add(getText(webPageElement));
        }

        return addFormFields.contains(fieldName);
    }

    public boolean verifyAutoGeneratingFieldsAreNotDisplayedInAddForm(final List<String> fields) {
        final List<String> fieldsInAddForm = new ArrayList<>();

        for (final WebPageElement webPageElement : getAddFormFields()) {
            fieldsInAddForm.add(getText(webPageElement).replaceAll("\\*$", ""));
        }

        for (final String autoGeneratingField : fields) {

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
        return isDisplayed(findByXpath(String.format(CRM_LOCATOR_REGISTRY.get("crm.add.form.field.name"),
                fieldName)));
    }

    public void setDataExceedingTextLimit(final String fieldName) {
        send(getTextFieldWebPageElement(fieldName), faker.lorem().characters(256));
    }

    public void submitForm() {
        click(getSaveButton());
    }

}
