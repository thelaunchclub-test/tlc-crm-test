package com.twozo.test.contact;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.commons.json.JsonObject;
import com.twozo.crm.automation.base.page.add.form.Form;
import com.twozo.crm.automation.base.page.contact.ContactAddForm;
import com.twozo.crm.automation.base.page.contact.ContactForm;
import com.twozo.crm.automation.base.page.list.view.ListView;
import com.twozo.crm.automation.base.page.settings.data.fields.company.CompanyDataFieldPage;
import com.twozo.crm.automation.base.page.settings.data.fields.contact.ContactDataField;
import com.twozo.crm.automation.base.page.settings.data.fields.contact.field.ContactField;
import com.twozo.crm.automation.base.page.url.URL;
import com.twozo.crm.automation.base.page.url.settings.SettingsURL;
import com.twozo.test.TestCase;
import com.twozo.test.add.form.AddFormTest;
import com.twozo.test.settings.data.fields.ContactDataFieldTest;
import com.twozo.web.driver.service.WebAutomationDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class AddContactTest extends AddFormTest {

    private static final String CONTACT_ADD_FORM = Paths.get(CONTACT_PATH, "add", "form").toString();
    private ContactAddForm contactAddForm;
    private ContactDataField contactDataField;
    private CompanyDataFieldPage companyDataField;
    private WebAutomationDriver automationDriver;

    @BeforeMethod
    public void before() {
        automationDriver = WebAutomationDriver.get();
        contactAddForm = ContactAddForm.getInstance(automationDriver);
        contactDataField = ContactDataField.getInstance(automationDriver);
        companyDataField = CompanyDataFieldPage.getInstance(automationDriver);
        listView = ListView.getInstance(automationDriver);

        contactAddForm.navigateTo(link);

        for (final BrowserCookie cookie : cookies) {
            contactAddForm.addCookie(cookie);
        }

        contactAddForm.maximize();
    }
//
//    @AfterMethod
//    public void after() {
//        automationDriver.close();
//    }


    protected ContactForm getFormWithRequiredFieldsEnabled(final Object object) {
        final TestCase testCase = (TestCase) object;
        final JsonObject input = testCase.input;
        final ContactForm contactForm = new ContactForm();
        final Collection<String> fieldsEnabledAsRequired = contactDataField.getFieldsEnabledAsRequired();

        //setStringField(contactForm::setFirstName, input, fieldsEnabledAsRequired, ContactField.FIRST_NAME.getName());
        setStringField(contactForm::setLastName, input, fieldsEnabledAsRequired, ContactField.LAST_NAME.getName());
        setListField(contactForm::setEmails, input, fieldsEnabledAsRequired, ContactField.EMAILS.getName(), "type", "email");
        setListField(contactForm::setPhones, input, fieldsEnabledAsRequired, ContactField.PHONES.getName(), "type", "phone");
        setStringField(contactForm::setCompany, input, fieldsEnabledAsRequired, ContactField.COMPANY.getName());
        setStringField(contactForm::setDesignation, input, fieldsEnabledAsRequired, ContactField.DESIGNATION.getName());
        setStringField(contactForm::setSalesOwner, input, fieldsEnabledAsRequired, ContactField.SALES_OWNER.getName());
        setStringField(contactForm::setLifecycleStage, input, fieldsEnabledAsRequired, ContactField.LIFECYCLE_STAGE.getName());
        setStringField(contactForm::setLifecycleStatus, input, fieldsEnabledAsRequired, ContactField.LIFECYCLE_STATUS.getName());
        setStringField(contactForm::setSource, input, fieldsEnabledAsRequired, ContactField.SOURCE.getName());
        setStringField(contactForm::setTerritory, input, fieldsEnabledAsRequired, ContactField.TERRITORY.getName());
        setStringListField(contactForm::setTags, input, fieldsEnabledAsRequired, ContactField.TAGS.getName());
        setStringField(contactForm::setDateOfBirth, input, fieldsEnabledAsRequired, ContactField.DATE_OF_BIRTH.getName());
        setStringField(contactForm::setDepartment, input, fieldsEnabledAsRequired, ContactField.DEPARTMENT.getName());
        setAddressField(contactForm::setAddress, input, fieldsEnabledAsRequired, ContactField.ADDRESS.getName());
        setStringField(contactForm::setFacebook, input, fieldsEnabledAsRequired, ContactField.FACEBOOK.getName());
        setStringField(contactForm::setTwitter, input, fieldsEnabledAsRequired, ContactField.TWITTER.getName());
        setStringField(contactForm::setLinkedin, input, fieldsEnabledAsRequired, ContactField.LINKED_IN.getName());
        setStringField(contactForm::setSubscriptionStatus, input, fieldsEnabledAsRequired, ContactField.SUBSCRIPTION_STATUS.getName());
        setStringField(contactForm::setSubscriptionTypes, input, fieldsEnabledAsRequired, ContactField.SUBSCRIPTION_TYPES.getName());
        setStringField(contactForm::setTimeZone, input, fieldsEnabledAsRequired, ContactField.TIME_ZONE.getName());

        return contactForm;
    }

    public ContactForm getForm(final Object object) {
        final TestCase testCase = (TestCase) object;
        final JsonObject input = testCase.input;
        final ContactForm contactForm = new ContactForm();

        setStringField(contactForm::setFirstName, input, ContactField.FIRST_NAME.getName());
        setStringField(contactForm::setLastName, input, ContactField.LAST_NAME.getName());
        setListField(contactForm::setEmails, input, ContactField.EMAILS.getName(), "type", "email");
        setListField(contactForm::setPhones, input, ContactField.PHONES.getName(), "type", "phone");
        setStringField(contactForm::setCompany, input, ContactField.COMPANY.getName());
        setStringField(contactForm::setDesignation, input, ContactField.DESIGNATION.getName());
        setStringField(contactForm::setSalesOwner, input, ContactField.SALES_OWNER.getName());
        setStringField(contactForm::setLifecycleStage, input, ContactField.LIFECYCLE_STAGE.getName());
        setStringField(contactForm::setLifecycleStatus, input, ContactField.LIFECYCLE_STATUS.getName());
        setStringField(contactForm::setSource, input, ContactField.SOURCE.getName());
        setStringField(contactForm::setTerritory, input, ContactField.TERRITORY.getName());
        setStringListField(contactForm::setTags, input, ContactField.TAGS.getName());
        setStringField(contactForm::setDateOfBirth, input, ContactField.DATE_OF_BIRTH.getName());
        setStringField(contactForm::setDepartment, input, ContactField.DEPARTMENT.getName());
        setAddressField(contactForm::setAddress, input, ContactField.ADDRESS.getName());
        setStringField(contactForm::setFacebook, input, ContactField.FACEBOOK.getName());
        setStringField(contactForm::setTwitter, input, ContactField.TWITTER.getName());
        setStringField(contactForm::setLinkedin, input, ContactField.LINKED_IN.getName());
        setStringField(contactForm::setSubscriptionStatus, input, ContactField.SUBSCRIPTION_STATUS.getName());
        setStringField(contactForm::setSubscriptionTypes, input, ContactField.SUBSCRIPTION_TYPES.getName());
        setStringField(contactForm::setTimeZone, input, ContactField.TIME_ZONE.getName());

        return contactForm;
    }

    @Override
    protected void switchToAddFormWithNonMandatoryFieldsDisabled() {

    }


    private void setAddressField(final Consumer<ContactForm.Address> setter, final JsonObject input, final String fieldName) {
        if (input.containsKey(fieldName)) {
            JsonObject addressJson = input.getJsonObject(fieldName);
            ContactForm.Address address = extractAddress(addressJson);
            setter.accept(address);
        }
    }

    private void setAddressField(final Consumer<ContactForm.Address> setter, final JsonObject input,
                                 final Collection<String> requiredFields, final String fieldName) {
        if (requiredFields.contains(fieldName) && input.containsKey(fieldName)) {
            JsonObject addressJson = input.getJsonObject(fieldName);
            ContactForm.Address address = extractAddress(addressJson);
            setter.accept(address);
        }
    }

    private ContactForm.Address extractAddress(final JsonObject addressJson) {
        final ContactForm.Address address = new ContactForm.Address();
        final String addressLine1 = "Address Line 1";
        final String addressLine2 = "Address Line 2";
        final String city = "City";
        final String state = "State";
        final String country = "Country";
        final String pincode = "Pincode";

        if (addressJson.containsKey(addressLine1)) {
            address.setAddressLine1(addressJson.getString(addressLine1));
        }

        if (addressJson.containsKey(addressLine2)) {
            address.setAddressLine2(addressJson.getString(addressLine2));
        }

        if (addressJson.containsKey(city)) {
            address.setCity(addressJson.getString(city));
        }

        if (addressJson.containsKey(state)) {
            address.setState(addressJson.getString(state));
        }

        if (addressJson.containsKey(country)) {
            address.setCountry(addressJson.getString(country));
        }

        if (addressJson.containsKey(pincode)) {
            address.setPincode(addressJson.getString(pincode));
        }

        return address;
    }

    private List<String> getFieldsForUpdate(final Form form) {
        try {
            return getFieldsForUpdate(form, ContactField.values());
        } catch (IllegalAccessException e) {

        }
        return null;
    }

    private void setUpForInvalidData(final String fieldName) {
        contactAddForm.navigateTo(SettingsURL.CONTACT_DATA_FIELDS);
        contactDataField.disableRequiredForNonMandatoryFields();
        contactDataField.setFieldAddViewEnabled(fieldName);
        contactDataField.switchToContactAddForm();
    }

    private boolean isEquals(final Object object, final String fieldName) {
        contactAddForm.navigateTo(SettingsURL.CONTACT_DATA_FIELDS);
        contactDataField.disableRequiredForNonMandatoryFields();
        contactDataField.setFieldAddViewEnabled(fieldName);
        final ContactForm formWithRequiredFieldsEnabled = getFormWithRequiredFieldsEnabled(object);

        contactDataField.navigateTo(URL.CONTACTS);
        final int listCount = listView.getCount();

        contactDataField.switchToContactAddForm();
        final ContactForm contactForm = contactAddForm.fill(formWithRequiredFieldsEnabled);

        listView.refresh();
        final int listCountAfterAdding = listView.getCount();
        int fieldPositionInColumnSettings = 0;

        if (listCount < listCountAfterAdding) {
            listView.switchToColumnSettings();
            fieldPositionInColumnSettings = listView.getFieldPositionInColumnSettings(fieldName);
        }

        final String fieldValue = listView.getFieldData(listCountAfterAdding, fieldPositionInColumnSettings).trim();
        Field declaredField = null;
        String dataType = null;

        try {
            declaredField = contactForm.getClass().getDeclaredField(toCamelCase(fieldName));
            declaredField.setAccessible(true);
            dataType = declaredField.getType().getSimpleName();
        } catch (NoSuchFieldException e) {

        }

        switch (Objects.requireNonNull(dataType)) {
            case "String" -> {
                try {
                    return Objects.equals(declaredField.get(contactForm), fieldValue);
                } catch (IllegalAccessException ignored) {

                }
            }
            case "List" -> {
                try {
                    final List<?> list = (List<?>) declaredField.get(contactForm);

                    if (list.get(0) instanceof Map) {

                        if (((Map<?, ?>) list.get(0)).containsKey("email")) {
                            return Objects.equals(((Map<?, ?>) list.get(0)).get("email"), fieldValue);
                        } else if (((Map<?, ?>) list.get(0)).containsKey("phone")) {
                            return Objects.equals(((Map<?, ?>) list.get(0)).get("phone"), fieldValue);

                        }
                    } else {
                        return Objects.equals(list.get(0), fieldValue);
                    }
                } catch (IllegalAccessException ignored) {

                }
            }
            case "Address" -> {
                try {
                    final ContactForm.Address address = (ContactForm.Address) declaredField.get(contactForm);
                    final String completeAddress = String.join(", ", address.getAddressLine1(), address.getAddressLine2(), address.getCity(),
                            address.getState(), address.getCountry(), address.getPincode());

                    return Objects.equals(completeAddress, fieldValue);


                } catch (IllegalAccessException e) {

                }
            }
        }

        return false;
    }

    //==================================================================================================================

    @Test
    public void verifyDefaultFieldsInAddForm() {
        contactDataField.switchToContactAddForm();
        Assert.assertTrue(contactAddForm.checkDefaultFieldsInAddForm(contactDataField.getDefaultFields()));
    }

    @Test
    public void verifyFieldsInAddFormAreInOrder() {
        contactAddForm.navigateTo(SettingsURL.CONTACT_DATA_FIELDS);
        Assert.assertTrue(contactAddForm.verifyFieldOrderInAddForm(contactDataField.
                getFieldsEnabledAsAddView()));
    }

    @Test
    public void verifyAutoGeneratedFieldsNotVisibleInAddForm() {
        contactAddForm.navigateTo(URL.CONTACTS);
        Assert.assertTrue(contactAddForm.verifyAutoGeneratingFieldsAreNotDisplayedInAddForm());
    }

    @Test
    public void verifyMaxEmailCountLimit() {
        contactAddForm.navigateTo(SettingsURL.CONTACT_DATA_FIELDS);
        contactDataField.setFieldAddViewEnabled(ContactField.EMAILS.getName());
        contactAddForm.navigateTo(URL.CONTACTS);
        contactDataField.switchToContactAddForm();

        Assert.assertTrue(contactAddForm.verifyMaxEmailLimit());
    }

    @Test
    public void verifyMaxPhoneCountLimit() {
        contactAddForm.navigateTo(SettingsURL.CONTACT_DATA_FIELDS);
        contactDataField.setFieldAddViewEnabled(ContactField.PHONES.getName());
        contactAddForm.navigateTo(URL.CONTACTS);
        contactDataField.switchToContactAddForm();

        Assert.assertTrue(contactAddForm.verifyMaxPhoneLimit());
    }

//    @Test
//    public void verifyAddingNewCompany() {
//        contactAddForm.navigateTo(SettingsURL.CONTACT_DATA_FIELDS);
//        contactDataField.setFieldAddViewEnabled(ContactField.COMPANY.getName());
//        contactDataField.switchToContactAddForm();
//        contactAddForm.verifyAddingNewCompany();
//    }

    @Test
    public void verifyEmailFieldDefaultType() {
        contactAddForm.navigateTo(SettingsURL.CONTACT_DATA_FIELDS);
        contactDataField.setFieldAddViewEnabled(ContactField.EMAILS.getName());
        contactDataField.switchToContactAddForm();
        Assert.assertTrue(contactAddForm.verifyDefaultTypeOfEmail());
    }

    @Test
    public void verifyPhoneFieldDefaultType() {
        contactAddForm.navigateTo(SettingsURL.CONTACT_DATA_FIELDS);
        contactDataField.setFieldAddViewEnabled(ContactField.PHONES.getName());
        contactDataField.switchToContactAddForm();
        Assert.assertTrue(contactAddForm.verifyDefaultTypeOfPhone());
    }

    @Test
    public void verifyLifecycleStageFieldDefaultType() {
        contactAddForm.navigateTo(SettingsURL.CONTACT_DATA_FIELDS);
        contactDataField.setFieldAddViewEnabled(ContactField.LIFECYCLE_STATUS.getName());
        contactDataField.switchToContactAddForm();
        Assert.assertTrue(contactAddForm.verifyDefaultLifecycleStage());
    }

    @Test
    public void verifyDefaultSourceChoiceInAddForm() {
        contactAddForm.navigateTo(SettingsURL.CONTACT_DATA_FIELDS);
        contactDataField.setFieldAddViewEnabled(ContactField.SOURCE.getName());
        contactDataField.setDefaultChoiceForSource("Lost of competitor");
        contactDataField.switchToContactAddForm();
        Assert.assertTrue(contactAddForm.verifySourceDefaultChoiceIsReflectedInAddForm("Lost of competitor"));
    }

    @Test
    public void verifyDefaultLostReasonChoiceInAddForm() {
        contactAddForm.navigateTo(SettingsURL.CONTACT_DATA_FIELDS);
        contactDataField.setFieldAddViewEnabled(ContactField.LOST_REASON.getName());
        contactDataField.setDefaultLostReason("Web");
        contactDataField.switchToContactAddForm();
        Assert.assertTrue(contactAddForm.verifySourceDefaultChoiceIsReflectedInAddForm("Web"));
    }

    @Test
    public void verifyDefaultSubscriptionStatusChoiceIsReflectedInAddForm() {
        contactAddForm.navigateTo(SettingsURL.CONTACT_DATA_FIELDS);
        contactDataField.setFieldAddViewEnabled(ContactField.SUBSCRIPTION_STATUS.getName());
        contactDataField.setDefaultChoiceForSubscriptionStatus("Unsubscribed");
        contactDataField.switchToContactAddForm();
        Assert.assertTrue(contactAddForm.verifySubscriptionStatusDefaultChoiceIsReflectedInAddForm("Unsubscribed"));
    }

    @Test
    public void verifyDefaultTimeZoneChoiceIsReflectedInAddForm() {
        contactAddForm.navigateTo(SettingsURL.CONTACT_DATA_FIELDS);
        contactDataField.setFieldAddViewEnabled(ContactField.TIME_ZONE.getName());
        contactDataField.setDefaultChoiceForTimeZone("(UTC+03:00) Africa/Asmara");
        contactDataField.switchToContactAddForm();
        Assert.assertTrue(contactAddForm.verifyTimeZoneDefaultChoiceIsReflectedInAddForm("(UTC+03:00) Africa/Asmara"));
    }

    @Test(dataProvider = "contactAddFormFields")
    public void verifySystemFieldsDisplayedInAddForm(final Object object) {
        contactAddForm.navigateTo(SettingsURL.CONTACT_DATA_FIELDS);
        contactDataField.setFieldsAddViewEnabled(getFieldsForUpdate(getForm(object)));
        contactAddForm.verifySystemFieldsDisplayedInAddForm();
    }

    @Test(dataProvider = "customField", dataProviderClass = com.twozo.test.settings.data.fields.ContactDataFieldTest.class)
    public void verifyCustomFieldsDisplayedInAddForm(final Object testCase) {
        contactAddForm.navigateTo(SettingsURL.CONTACT_DATA_FIELDS);

        TestCase testCase1 = (TestCase) testCase;

        contactDataField.checkMaximumLimit(new ContactDataFieldTest().getFieldStatus(testCase1));
        contactDataField.enableAddView(testCase1.input.getString("fieldName"));
        contactDataField.switchToContactAddForm();
        Assert.assertTrue(contactAddForm.verifyCustomFieldsDisplayedInAddForm(testCase1.input.getString("fieldName")));
    }

    //=========================================================================
    @Test(dataProvider = "contactAddFormFields")
    public void verifyFirstNameFieldWithValidData(final Object object) {
        Assert.assertTrue(isEquals(object, ContactField.FIRST_NAME.getName()));
    }

    @Test
    public void verifyFirstNameFieldWithInvalidData() {
        final String firstName = ContactField.FIRST_NAME.getName();

        setUpForInvalidData(firstName);
        contactAddForm.setDataExceedingTextLimit(firstName);
        contactAddForm.submitForm();
        Assert.assertTrue(contactAddForm.isMaxTextLimitExceededNotificationDisplayed(firstName));
    }

    @Test(dataProvider = "contactAddFormFields")
    public void verifyLastNameFieldWithValidData(final Object object) {
        Assert.assertTrue(isEquals(object, ContactField.LAST_NAME.getName()));
    }

    @Test
    public void verifyLastNameFieldWithInvalidData() {
        final String lastName = ContactField.LAST_NAME.getName();

        setUpForInvalidData(lastName);
        contactAddForm.setDataExceedingTextLimit(lastName);
        contactAddForm.submitForm();
        Assert.assertTrue(contactAddForm.isMaxTextLimitExceededNotificationDisplayed(lastName));
    }

    @Test(dataProvider = "contactAddFormFields")
    public void verifyEmailFieldWithValidData(final Object object) {
        Assert.assertTrue(isEquals(object, ContactField.EMAILS.getName()));
    }

    @Test
    public void verifyEmailFieldWithMissingAtSymbol() {
        setUpForInvalidData(ContactField.EMAILS.getName());
        contactAddForm.setEmail("abcgmai.com");
        contactAddForm.submitForm();
        Assert.assertTrue(contactAddForm.isInvalidMailNotificationDisplayed());
    }

    @Test
    public void verifyEmailFieldWithSpecialCharacter() {
        setUpForInvalidData(ContactField.EMAILS.getName());
        contactAddForm.setEmail("ana@$gmail.com");
        contactAddForm.submitForm();
        Assert.assertTrue(contactAddForm.isInvalidMailNotificationDisplayed());
    }

    @Test
    public void verifyEmailFieldWithSpace() {
        setUpForInvalidData(ContactField.EMAILS.getName());
        contactAddForm.setEmail(String.format("%s%s", " ", faker.internet().emailAddress()));
        Assert.assertTrue(contactAddForm.isInvalidMailNotificationDisplayed());
    }

    @Test(dataProvider = "contactAddFormFields")
    public void verifyPhoneFieldWithValidData(final Object object) {
        Assert.assertTrue(isEquals(object, ContactField.PHONES.getName()));
    }

    @Test
    public void verifyPhoneFieldWithInvalidData() {
        final String phone = ContactField.PHONES.getName();

        setUpForInvalidData(phone);
        contactAddForm.setPhone("a$12345a");
        contactAddForm.submitForm();
        Assert.assertTrue(contactAddForm.isMaxNumberLimitExceededNotificationDisplayed(phone));
    }

    @Test(dataProvider = "contactAddFormFields")
    public void verifyDesignationFieldWithValidData(final Object object) {
        Assert.assertTrue(isEquals(object, ContactField.DESIGNATION.getName()));
    }

    @Test
    public void verifyDesignationFieldWithInvalidData() {
        final String designation = ContactField.DESIGNATION.getName();

        setUpForInvalidData(designation);
        contactAddForm.setDataExceedingTextLimit(designation);
        contactAddForm.submitForm();
        Assert.assertTrue(contactAddForm.isMaxTextLimitExceededNotificationDisplayed(designation));
    }

    @Test(dataProvider = "contactAddFormFields")
    public void verifyDepartmentFieldWithValidData(final Object object) {
        Assert.assertTrue(isEquals(object, ContactField.DEPARTMENT.getName()));
    }

    @Test
    public void verifyDepartmentFieldWithInvalidData() {
        final String department = ContactField.DEPARTMENT.getName();

        setUpForInvalidData(department);
        contactAddForm.setDataExceedingTextLimit(department);
        contactAddForm.submitForm();
        Assert.assertTrue(contactAddForm.isMaxTextLimitExceededNotificationDisplayed(department));
    }

    @Test(dataProvider = "contactAddFormFields")
    public void verifyAddressFieldWithValidData(final Object object) {
        Assert.assertTrue(isEquals(object, ContactField.ADDRESS.getName()));
    }

    @Test
    public void verifyAddressFieldWithInvalidData() {
        final ContactForm.Address address = new ContactForm.Address();

        address.setAddressLine1(faker.address().buildingNumber());
        address.setAddressLine2(faker.address().streetAddress());
        address.setCity(faker.address().city());
        address.setState(faker.address().state());
        address.setCountry(faker.address().country());
        address.setPincode(faker.address().zipCode());
        contactAddForm.navigateTo(SettingsURL.CONTACT_DATA_FIELDS);
        contactDataField.disableRequiredForNonMandatoryFields();
        contactDataField.switchToContactAddForm();
        contactAddForm.setAddress(address);
    }

    @Test(dataProvider = "contactAddFormFields")
    public void verifyFacebookFieldWithValidData(final Object object) {
        Assert.assertTrue(isEquals(object, ContactField.FACEBOOK.getName()));
    }

    @Test
    public void verifyFacebookFieldWithInvalidData() {
        final String facebook = ContactField.FACEBOOK.getName();

        setUpForInvalidData(facebook);
        contactAddForm.submitForm();
        Assert.assertTrue(contactAddForm.isInvalidURLNotificationDisplayed(facebook));
    }

    @Test(dataProvider = "contactAddFormFields")
    public void verifyTwitterFieldWithValidData(final Object object) {
        Assert.assertTrue(isEquals(object, ContactField.TWITTER.getName()));
    }

    @Test
    public void verifyTwitterFieldWithInvalidData() {
        final String twitter = ContactField.TWITTER.getName();

        setUpForInvalidData(twitter);
        contactAddForm.submitForm();
        Assert.assertTrue(contactAddForm.isInvalidURLNotificationDisplayed(twitter));
    }

    @Test(dataProvider = "contactAddFormFields")
    public void verifyLinkedInFieldWithValidData(final Object object) {
        Assert.assertTrue(isEquals(object, ContactField.LINKED_IN.getName()));
    }

    @Test
    public void verifyLinkedInFieldWithInvalidData() {
        final String linkedIn = ContactField.LINKED_IN.getName();

        setUpForInvalidData(linkedIn);
        contactAddForm.submitForm();
        Assert.assertTrue(contactAddForm.isInvalidURLNotificationDisplayed(linkedIn));
    }

    @Test(dataProvider = "contactAddFormFields")
    public void selectExistingChoiceInTerritory(final Object object) {
        Assert.assertTrue(isEquals(object, ContactField.TERRITORY.getName()));
    }

    @Test
    public void test(){
        contactAddForm.switchToContact();
        contactAddForm.switchToColumnSettings();
    }

}
