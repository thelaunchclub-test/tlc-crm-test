package com.twozo.test.contact;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.commons.json.JsonArray;
import com.twozo.commons.json.JsonObject;
import com.twozo.page.contact.ContactAddForm;
import com.twozo.page.contact.ContactForm;
import com.twozo.page.settings.data.fields.contact.ContactDataField;
import com.twozo.page.settings.data.fields.contact.field.ContactField;
import com.twozo.page.url.URL;
import com.twozo.page.url.settings.SettingsURL;
import com.twozo.test.TestCase;
import com.twozo.test.TestDataProvider;
import com.twozo.test.add.form.AddFormTest;
import com.twozo.web.driver.service.WebAutomationDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddContactTest extends AddFormTest {

    private static final String CONTACT_ADD_FORM = Paths.get(CONTACT_PATH, "add", "form").toString();
    private ContactAddForm contactAddForm;
    private WebAutomationDriver automationDriver;

    @BeforeMethod
    public void before() {
        automationDriver = WebAutomationDriver.get();
        webNavigator = automationDriver.getWebNavigator();
        webNavigator.to(link);

        for (final BrowserCookie cookie : cookies) {
            automationDriver.getSessionCookie().addCookie(cookie);
        }

        automationDriver.getWebWindowHandler().maximize();
        contactAddForm = ContactAddForm.getInstance(automationDriver);
        webNavigator.to(SettingsURL.CONTACT_DATA_FIELDS);
    }

//    @AfterMethod
//    public void after() {
//        automationDriver.close();
//    }

    @DataProvider(name = "contactSystemFields")
    private static Object[][] getContactAddFormData() {
        return new TestDataProvider().getTestCases(getFilePath(CONTACT_ADD_FORM, "AllFieldTypeValue.json"));
    }

    public ContactForm getForm(final Object object) {
        final TestCase testCase = (TestCase) object;
        final JsonObject input = testCase.input;
        final ContactForm contactForm = new ContactForm();
        final String type = "type";
        final String email = "email";
        final String phone = "phone";
        final String firstName = ContactField.FIRST_NAME.getName();
        final String lastName = ContactField.LAST_NAME.getName();
        final String emails = ContactField.EMAILS.getName();
        final String phones = ContactField.PHONES.getName();
        final String company = ContactField.COMPANY.getName();
        final String designation = ContactField.DESIGNATION.getName();
        final String salesOwner = ContactField.SALES_OWNER.getName();
        final String twitter = ContactField.TWITTER.getName();
        final String facebook = ContactField.FACEBOOK.getName();
        final String source = ContactField.SOURCE.getName();
        final String linkedIn = ContactField.LINKED_IN.getName();
        final String lifecycleStage = ContactField.LIFECYCLE_STAGE.getName();
        final String department = ContactField.DEPARTMENT.getName();
        final String dateOfBirth = ContactField.DATE_OF_BIRTH.getName();
        final String address = ContactField.ADDRESS.getName();
        final String subscriptionStatus = ContactField.SUBSCRIPTION_STATUS.getName();
        final String subscriptionTypes = ContactField.SUBSCRIPTION_TYPES.getName();
        final String timeZone = ContactField.TIME_ZONE.getName();
        final String tags = ContactField.TAGS.getName();
        final String territory = ContactField.TERRITORY.getName();

        if (input.containsKey(firstName)) {
            contactForm.setFirstName(input.getString(firstName));
        }

        if (input.containsKey(lastName)) {
            contactForm.setLastName(input.getString(lastName));
        }

        if (input.containsKey(emails)) {
            final JsonArray contactEmails = input.getJsonArray(emails);
            final List<Map<String, String>> multipleEmails = new ArrayList<>();

            for (int i = 0; i < contactEmails.size(); i++) {
                JsonObject emailObject = contactEmails.getJsonObject(i);
                Map<String, String> emailEntry = new HashMap<>();
                emailEntry.put(type, emailObject.getString(type));
                emailEntry.put(email, emailObject.getString(email));
                multipleEmails.add(emailEntry);
            }
            contactForm.setEmails(multipleEmails);
        }

        if (input.containsKey(phones)) {
            final JsonArray contactPhones = input.getJsonArray(phones);
            final List<Map<String, String>> multiplePhones = new ArrayList<>();

            for (int i = 0; i < contactPhones.size(); i++) {
                JsonObject phoneObject = contactPhones.getJsonObject(i);
                Map<String, String> phoneEntry = new HashMap<>();
                phoneEntry.put(type, phoneObject.getString(type));
                phoneEntry.put(phone, phoneObject.getString(phone));
                multiplePhones.add(phoneEntry);
            }
            contactForm.setPhones(multiplePhones);
        }

        if (input.containsKey(company)) {
            contactForm.setCompany(input.getString(company));
        }

        if (input.containsKey(designation)) {
            contactForm.setDesignation(input.getString(designation));
        }

        if (input.containsKey(salesOwner)) {
            contactForm.setSalesOwner(input.getString(salesOwner));
        }

        if (input.containsKey(lifecycleStage)) {
            contactForm.setLifecycleStage(input.getString(lifecycleStage));
        }

        if (input.containsKey(source)) {
            contactForm.setSource(input.getString(source));
        }

        if (input.containsKey(territory)) {
            contactForm.setTerritory(input.getString(territory));
        }

        if (input.containsKey(tags)) {
            final JsonArray tagsArray = input.getJsonArray(tags);
            final List<String> contactTags = new ArrayList<>();

            for (int i = 0; i < tagsArray.size(); i++) {
                contactTags.add(tagsArray.getString(i));
            }
            contactForm.setTags(contactTags);
        }

        if (input.containsKey(dateOfBirth)) {
            contactForm.setDateOfBirth(input.getString(dateOfBirth));
        }

        if (input.containsKey(department)) {
            contactForm.setDepartment(input.getString(department));
        }

        if (input.containsKey(address)) {

            final String addressLine1 = "Address Line 1";
            final String addressLine2 = "Address Line 2";
            final String city = "City";
            final String state = "State";
            final String country = "Country";
            final String pincode = "Pincode";
            final JsonObject addressJson = input.getJsonObject(address);
            final ContactForm.Address contactAddress = new ContactForm.Address();

            if (addressJson.containsKey(addressLine1)) {
                contactAddress.setAddressLine1(addressJson.getString(addressLine1));
            }
            if (addressJson.containsKey(addressLine2)) {
                contactAddress.setAddressLine2(addressJson.getString(addressLine2));
            }
            if (addressJson.containsKey(city)) {
                contactAddress.setCity(addressJson.getString(city));
            }
            if (addressJson.containsKey(state)) {
                contactAddress.setState(addressJson.getString(state));
            }
            if (addressJson.containsKey(country)) {
                contactAddress.setCountry(addressJson.getString(country));
            }
            if (addressJson.containsKey(pincode)) {
                contactAddress.setPincode(addressJson.getString(pincode));
            }

            contactForm.setAddress(contactAddress);
        }

        if (input.containsKey(facebook)) {
            contactForm.setFacebook(input.getString(facebook));
        }

        if (input.containsKey(twitter)) {
            contactForm.setTwitter(input.getString(twitter));
        }

        if (input.containsKey(linkedIn)) {
            contactForm.setLinkedin(input.getString(linkedIn));
        }

        if (input.containsKey(subscriptionStatus)) {
            contactForm.setSubscriptionStatus(input.getString(subscriptionStatus));
        }

        if (input.containsKey(subscriptionTypes)) {
            contactForm.setSubscriptionTypes(input.getString(subscriptionTypes));
        }

        if (input.containsKey(timeZone)) {
            contactForm.setTimeZone(input.getString(timeZone));
        }

        return contactForm;
    }

    @Test(dataProvider = "contactSystemFields")
    public void fill(final Object object) throws IllegalAccessException {
        ContactDataField.getInstance(automationDriver).setFieldsForAddForm(getFieldsForUpdate(getForm(object)));
        webNavigator.to(URL.CONTACTS);
        contactAddForm.switchToAddForm("Contact");
        contactAddForm.fill(getForm(object));
    }
}
