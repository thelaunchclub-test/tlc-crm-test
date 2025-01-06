package com.twozo.crm.automation.base.page.contact;

import com.twozo.crm.automation.base.page.add.form.AddForm;
import com.twozo.crm.automation.base.page.settings.data.fields.contact.field.ContactField;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;
import org.openqa.selenium.Keys;

import java.util.*;

public class ContactAddForm extends AddForm {

    private static ContactAddForm contactAddForm;

    protected ContactAddForm(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static ContactAddForm getInstance(final WebAutomationDriver webAutomationDriver) {
        contactAddForm = new ContactAddForm(webAutomationDriver);

        return contactAddForm;
    }

    @Override
    protected String[] getAutoGeneratingFields() {

        return new String[]{
                ContactField.CREATED_BY.getName(),
                ContactField.CREATED_AT.getName(),
                ContactField.UPDATED_BY.getName(),
                ContactField.UPDATED_AT.getName(),
                ContactField.LAST_ASSIGNED_AT.getName(),
                ContactField.ID.getName(),
                ContactField.WEB_FORM.getName(),
                ContactField.RECENT_NOTE.getName(),
                ContactField.OPEN_DEALS.getName(),
                ContactField.CLOSED_DEALS.getName(),
                ContactField.WON_DEALS.getName(),
                ContactField.LOST_DEALS.getName(),
                ContactField.LAST_ACTIVITY_DATE.getName(),
                ContactField.NEXT_ACTIVITY_DATE.getName(),
                ContactField.LAST_ACTIVITY_TYPE.getName(),
                ContactField.DONE_ACTIVITIES.getName(),
                ContactField.UPCOMING_ACTIVITIES.getName(),
                ContactField.TOTAL_ACTIVITIES.getName(),
                ContactField.EMAIL_MESSAGES_COUNT.getName(),
                ContactField.LAST_EMAIL_RECEIVED.getName(),
                ContactField.LAST_EMAIL_SENT.getName()
        };
    }

    @Override
    protected String[] getAddFormSystemFields() {
        return new String[]{
                ContactField.FIRST_NAME.getName(),
                ContactField.LAST_NAME.getName(),
                ContactField.EMAILS.getName(),
                ContactField.PHONES.getName(),
                ContactField.COMPANY.getName(),
                ContactField.DESIGNATION.getName(),
                ContactField.SALES_OWNER.getName(),
                ContactField.LIFECYCLE_STAGE.getName(),
                ContactField.SOURCE.getName(),
                ContactField.TERRITORY.getName(),
                ContactField.TAGS.getName(),
                ContactField.DATE_OF_BIRTH.getName(),
                ContactField.DEPARTMENT.getName(),
                ContactField.ADDRESS.getName(),
                ContactField.FACEBOOK.getName(),
                ContactField.TWITTER.getName(),
                ContactField.LINKED_IN.getName(),
                ContactField.SUBSCRIPTION_STATUS.getName(),
                ContactField.TIME_ZONE.getName(),
        };
    }

    private WebPageElement getFirstName() {
        return getTextFieldWebPageElement(ContactField.FIRST_NAME.getName());
    }

    private WebPageElement getLastName() {
        return getTextFieldWebPageElement(ContactField.LAST_NAME.getName());
    }

    private WebPageElement getEmailId(final int position) {
        return findByXpath(String.format(CRM_LOCATOR_REGISTRY.get("crm.add.form.contact.specific.email.field"), position));
    }

    private WebPageElement getEmailType(final int position) {
        return findByXpath(String.format(CRM_LOCATOR_REGISTRY.get("crm.add.form.contact.specific.email.type"), position));

    }

    private WebPageElement getPhone(final int position) {
        return findByXpath(String.format(CRM_LOCATOR_REGISTRY.get("crm.add.form.contact.specific.phone.field"), position));
    }

    private WebPageElement getPhoneType(final int position) {
        return findByXpath(String.format(CRM_LOCATOR_REGISTRY.get("crm.add.form.contact.specific.phone.type"), position));

    }

    private WebPageElement getDesignation() {
        return getTextFieldWebPageElement(ContactField.DESIGNATION.getName());
    }

    private WebPageElement getLifecycleStage() {
        return getDropdownFieldWebPageElement(ContactField.LIFECYCLE_STAGE.getName());
    }

    private WebPageElement getLifecycleStatus() {
        return getDropdownFieldWebPageElement(ContactField.LIFECYCLE_STATUS.getName());
    }

    private WebPageElement getDepartment() {
        return getTextFieldWebPageElement(ContactField.DEPARTMENT.getName());
    }

    private WebPageElement getSubscriptionStatus() {
        return getTextFieldWebPageElement(ContactField.SUBSCRIPTION_STATUS.getName());
    }

//    private WebPageElement getSubscriptionTypes() {
//        return getTextFieldWebPageElement(ContactField.SUBSCRIPTION_TYPES.getName());
//    }

    private WebPageElement getTimeZone() {
        return getAddressFieldWebPageElement(ContactField.TIME_ZONE.getName());
    }

    private WebPageElement getAddEmailButton() {
        return findByXpath(CRM_LOCATOR_REGISTRY.get("crm.add.form.contact.add.email"));
    }

    private WebPageElement getAddPhoneButton() {
        return findByXpath(CRM_LOCATOR_REGISTRY.get("crm.add.form.contact.add.phone"));
    }

    public void setFirstName(final String firstName) {
        send(getFirstName(), firstName);
    }

    public void setFirstName(final ContactForm contactForm) {
        setFirstName(contactForm.getFirstName());
    }

    public void setLastName(final String lastName) {
        send(getLastName(), lastName);
    }

    public void setLastName(final ContactForm contactForm) {
        setLastName(contactForm.getLastName());
    }

    public void setEmail(final String emailId) {
        send(getEmailId(1), emailId);
    }

    public void setEmail(final ContactForm contactForm) {
        final List<Map<String, String>> emails = contactForm.getEmails();
        final int emailCount = emails.size();

        if (emailCount == 1) {
            final Map<String, String> emailData = emails.get(0);

            send(getEmailId(1), emailData.get("email"));
            final WebPageElement emailType = getEmailType(1);
            final String type = emailData.get("type");

            if (!Objects.equals(getText(emailType), type)) {
                click(emailType);
                dropdown(type);
            }
        } else {
            for (int i = 1; i < emailCount; i++) {
                click(getAddEmailButton());
            }

            for (int i = 1; i <= emailCount; i++) {
                final Map<String, String> emailData = emails.get(i - 1);

                send(getEmailId(i), emailData.get("email"));
                final WebPageElement emailType = getEmailType(i);
                final String type = emailData.get("type");

                if (!Objects.equals(getText(emailType), type)) {
                    click(emailType);
                    dropdown(type);
                }
            }
        }
    }

    public void setPhone(final String phoneNumber) {
        send(getPhone(1), phoneNumber);
    }

    public void setPhone(final List<Map<String, String>> phones) {
        final int phoneCount = phones.size();

        if (phoneCount == 1) {
            final Map<String, String> phoneData = phones.get(0);

            send(getPhone(1), phoneData.get("phone"));
            final WebPageElement phoneType = getPhoneType(1);
            final String type = phoneData.get("type");

            if (!Objects.equals(getText(phoneType), type)) {
                click(phoneType);
                dropdown(type);
            }
        } else {
            for (int i = 1; i < phoneCount; i++) {
                click(getAddPhoneButton());
            }

            for (int i = 1; i <= phoneCount; i++) {
                final Map<String, String> phoneData = phones.get(i - 1);

                send(getPhone(i), phoneData.get("phone"));
                final WebPageElement phoneType = getPhoneType(i);
                final String type = phoneData.get("type");

                if (!Objects.equals(getText(phoneType), type)) {
                    click(phoneType);
                    dropdown(type);
                }
            }
        }
    }

    public void setPhone(final ContactForm contactForm) {
        setPhone(contactForm.getPhones());
    }

    public void setCompany(final String company) {
        send(getCompany(), company);
        final Collection<WebPageElement> companySuggestions = findElementsByXpath("//*[@class='MuiAutocomplete-option MuiBox-root css-0']//child::p");

        for (final WebPageElement option : companySuggestions) {
            String optionText = getText(option);

            if (optionText.equals(company)) {
                click(option);
                break;
            }
        }

        try {

            if (isDisplayed(findByXpath(CRM_LOCATOR_REGISTRY.get("crm.add.form.contact.add.company.button")))) {
                click(findByXpath(CRM_LOCATOR_REGISTRY.get("crm.add.form.contact.add.company.button")));
            }
        } catch (Exception exception) {
        }
    }

    public void setCompany(final ContactForm contactForm) {
        setCompany(contactForm.getCompany());
    }

    public void setSalesOwner(final String salesOwner) {

        if (!Objects.equals(getText(getSalesOwner()), salesOwner)) {
            click(getSalesOwner());
            dropdown(salesOwner);
        }
    }

    public void setSalesOwner(final ContactForm contactForm) {
        setSalesOwner(contactForm.getSalesOwner());
    }

    public void setLifecycleStage(final String lifecycleStage) {
        click(getSalesOwner());
        dropdown(lifecycleStage);
    }

    public void setLifecycleStage(final ContactForm contactForm) {
        setLifecycleStage(contactForm.getLifecycleStage());
    }

    public void setLifecycleStatus(final String lifecycleStatus) {
        click(getLifecycleStatus());
        dropdown(lifecycleStatus);
    }

    public void setLifecycleStatus(final ContactForm contactForm) {
        setLifecycleStatus(contactForm.getLifecycleStatus());
    }

    public void setSource(final String source) {
        click(getSource());
        dropdown(source);
    }

    public void setSource(final ContactForm contactForm) {
        setSource(contactForm.getSource());
    }

    public void setTerritory(final String territory) {
        click(getTerritory());
        dropdown(territory);
    }

    public void setTerritory(final ContactForm contactForm) {
        setTerritory(contactForm.getTerritory());
    }

    public void setTags(final String tag) {
        send(getTags(), tag);
        mouseActions.keyDown(Keys.ENTER).keyUp(Keys.CONTROL).perform();
    }

    public void setTags(final List<String> tags) {
        for (final String tag : tags) {

            send(getTags(), tag);
            mouseActions.keyDown(Keys.ENTER).keyUp(Keys.CONTROL).perform();
        }
    }

    public void setTags(final ContactForm contactForm) {
        setTags(contactForm.getTags());
    }

    public void setDepartment() {
        send(getDesignation(), DATA_FAKER.getDepartment());
    }

    public void setDepartment(final String department) {
        send(getDepartment(), department);
    }

    public void setDepartment(final ContactForm contactForm) {
        setDepartment(contactForm.getDepartment());
    }

    public void setAddress() {
        send(getAddressLine1(), DATA_FAKER.getAddressLine1());
        send(getAddressLine2(), DATA_FAKER.getAddressLine2());
        send(getCity(), DATA_FAKER.getCity());
        send(getState(), DATA_FAKER.getState());
        click(getCountry());
        dropdown(DATA_FAKER.getCountry());
        send(getPincode(), DATA_FAKER.getZipcode());
    }

    public void setAddress(final ContactForm.Address address) {
        send(getAddressLine1(), address.getAddressLine1());
        send(getAddressLine2(), address.getAddressLine2());
        send(getCity(), address.getCity());
        send(getState(), address.getState());
        click(getCountry());
        dropdown(address.getCity());
        send(getPincode(), address.getPincode());
    }

    public void setAddress(final ContactForm contactForm) {
        setAddress(contactForm.getAddress());
    }

    public void setFacebook(final String facebook) {
        send(getFacebook(), facebook);
    }

    public void setFacebook(final ContactForm contactForm) {
        setFacebook(contactForm.getFacebook());
    }

    public void setDesignation(final String designation) {
        send(getDesignation(), designation);
    }

    public void setDesignation(final ContactForm contactForm) {
        setDesignation(contactForm.getDesignation());
    }

    public void setTwitter(final String twitter) {
        send(getTwitter(), twitter);
    }

    public void setTwitter(final ContactForm contactForm) {
        setTwitter(contactForm.getTwitter());
    }

    public void setLinkedIn(final String linkedIn) {
        send(getLinkedIn(), linkedIn);
    }

    public void setLinkedIn(final ContactForm contactForm) {
        setLinkedIn(contactForm.getLinkedin());
    }

    public void setSubscriptionStatus(final String subscriptionStatus) {
        click(getSubscriptionStatus());
        dropdown(subscriptionStatus);
    }

    public void setSubscriptionStatus(final ContactForm contactForm) {
        setSubscriptionStatus(contactForm.getSubscriptionStatus());
    }

    public void setTimeZone(final String timeZone) {
        click(getTimeZone());
        dropdown(timeZone);
    }

    public void setTimeZone(final ContactForm contactForm) {
        setTimeZone(contactForm.getTimeZone());
    }


    private boolean hasNonDefaultValue(final Object value) {

        return switch (value) {
            case String s -> !s.isEmpty();
            case Collection<?> objects -> !objects.isEmpty();
            case Map<?, ?> map -> !map.isEmpty();
            case ContactForm.Address address -> true;
            case null, default -> Objects.nonNull(value);
        };
    }

    public void setValueForMandatoryFieldsInAddForm() {
        setFirstName(DATA_FAKER.getFirstName());
        setEmail(DATA_FAKER.getEmailId());
        setPhone(DATA_FAKER.getPhoneNumber());
    }

    public ContactForm fill(final ContactForm contactForm) {
        final String firstName = DATA_FAKER.getFirstName();

        contactForm.setFirstName(firstName);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        setFirstName(firstName);

        if (hasNonDefaultValue(contactForm.getLastName())) {
            setLastName(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getEmails())) {
            setEmail(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getPhones())) {
            setPhone(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getCompany())) {
            setCompany(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getSalesOwner())) {
            setSalesOwner(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getLifecycleStatus())) {
            setLifecycleStatus(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getLifecycleStage())) {
            setLifecycleStatus(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getSource())) {
            setSource(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getTerritory())) {
            setTerritory(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getTags())) {
            setTags(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getDepartment())) {
            setDepartment(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getAddress())) {
            setAddress(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getFacebook())) {
            setFacebook(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getDesignation())) {
            setDesignation(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getTwitter())) {
            setTwitter(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getLinkedin())) {
            setLinkedIn(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getSubscriptionStatus())) {
            setSubscriptionStatus(contactForm);
        }

        if (hasNonDefaultValue(contactForm.getTimeZone())) {
            setTimeZone(contactForm);
        }

        click(getSaveButton());

        return contactForm;
    }

    public boolean verifyMaxEmailLimit() {

        for (int noOfEmails = 1; noOfEmails < 10; noOfEmails++) {
            click(getAddEmailButton());
        }

        try {
            if (isDisplayed(getAddEmailButton())) {
                return false;
            }
        } catch (Exception exception) {
            return true;
        }

        return false;
    }

    public boolean verifyMaxPhoneLimit() {

        for (int noOfPhones = 1; noOfPhones < 10; noOfPhones++) {
            click(getAddPhoneButton());
        }

        try {
            if (isDisplayed(getAddPhoneButton())) {
                return false;
            }
        } catch (Exception exception) {
            return true;
        }

        return false;
    }

    public boolean verifyAddingNewCompany() {
        return false;
    }

    public boolean verifyDefaultTypeOfEmail() {
        return Objects.equals(getText(findByXpath(String.format(CRM_LOCATOR_REGISTRY.get("crm.add.form.contact.specific.email.type"), 1))), "Work");
    }

    public boolean verifyDefaultTypeOfPhone() {
        return Objects.equals(getText(findByXpath(String.format(CRM_LOCATOR_REGISTRY.get("crm.add.form.contact.specific.phone.type"), 1))), "Work");
    }

    public boolean verifyDefaultLifecycleStage() {
        return Objects.equals(getText(findByXpath(String.format(CRM_LOCATOR_REGISTRY.get("crm.add.form.dropdown.field"),
                ContactField.LIFECYCLE_STAGE.getName()))), "Lead");
    }

    public boolean verifySourceDefaultChoiceIsReflectedInAddForm(final String choice) {
        return Objects.equals(getText(findByXpath(String.format(CRM_LOCATOR_REGISTRY.get("crm.add.form.dropdown.field"),
                ContactField.SOURCE.getName()))), choice);
    }

    public boolean verifySubscriptionStatusDefaultChoiceIsReflectedInAddForm(final String choice) {
        return Objects.equals(getText(findByXpath(String.format(CRM_LOCATOR_REGISTRY.get("crm.add.form.dropdown.field"),
                ContactField.SUBSCRIPTION_STATUS.getName()))), choice);
    }

    public boolean verifyTimeZoneDefaultChoiceIsReflectedInAddForm(final String choice) {
        return Objects.equals(getText(findByXpath(String.format(CRM_LOCATOR_REGISTRY.get("crm.add.form.dropdown.field"),
                ContactField.TIME_ZONE.getName()))), choice);
    }

    public boolean verifySystemFieldsDisplayedInAddForm() {
        final List<String> addFormFields = new ArrayList<>();

        for (final WebPageElement webPageElement : findElementsByXpath(CRM_LOCATOR_REGISTRY.get("crm.add.form.fields"))) {
            addFormFields.add(getText(webPageElement));
        }

        for (final String addFormField : getAddFormSystemFields()) {

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
}
