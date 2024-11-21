package com.twozo.test.contact;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.commons.json.JsonArray;
import com.twozo.commons.json.JsonObject;
import com.twozo.page.contact.ContactAddForm;
import com.twozo.page.contact.ContactForm;
import com.twozo.page.homepage.HomePage;
import com.twozo.page.url.URL;
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
    private HomePage homePage;
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
        webNavigator.to(URL.CONTACTS);
        automationDriver.getWebWindowHandler().maximize();
        homePage = HomePage.getInstance(automationDriver);
        contactAddForm = ContactAddForm.getInstance(automationDriver);
    }

//    @AfterMethod
//    public void after() {
//        automationDriver.close();
//    }

    @DataProvider(name = "contactSystemFields")
    private static Object[][] getContactAddFormData() {
        return new TestDataProvider().getTestCases(getFilePath(CONTACT_ADD_FORM, "AllFieldTypeValue.json"));
    }

    private ContactForm getContactForm(final Object object) {
        final TestCase testCase = (TestCase) object;
        final JsonObject input = testCase.input;
        final ContactForm contactForm = new ContactForm();

        if (input.containsKey("First Name")) {
            contactForm.setFirstName(input.getString("First Name"));
        }

        if (input.containsKey("Last Name")) {
            contactForm.setLastName(input.getString("Last Name"));
        }

        if (input.containsKey("Emails")) {
            final JsonArray emails = input.getJsonArray("Emails");
            final List<Map<String, String>> multipleEmails = new ArrayList<>();

            for (int i = 0; i < emails.size(); i++) {
                JsonObject emailObject = emails.getJsonObject(i);
                Map<String, String> emailEntry = new HashMap<>();
                emailEntry.put("type", emailObject.getString("type"));
                emailEntry.put("email", emailObject.getString("email"));
                multipleEmails.add(emailEntry);
            }
            contactForm.setEmails(multipleEmails);
        }


        if (input.containsKey("Phones")) {
            final JsonArray phones = input.getJsonArray("Phones");
            final List<Map<String, String>> multiplePhones = new ArrayList<>();

            for (int i = 0; i < phones.size(); i++) {
                JsonObject phoneObject = phones.getJsonObject(i);
                Map<String, String> phoneEntry = new HashMap<>();
                phoneEntry.put("type", phoneObject.getString("type"));
                phoneEntry.put("phone", phoneObject.getString("phone"));
                multiplePhones.add(phoneEntry);
            }
            contactForm.setPhones(multiplePhones); // Assuming setPhones is updated to accept List<Map<String, String>>
        }

        if (input.containsKey("Company")) {
            contactForm.setCompany(input.getString("Company"));
        }

        if (input.containsKey("Designation")) {
            contactForm.setDesignation(input.getString("Designation"));
        }

        if (input.containsKey("Sales Owner")) {
            contactForm.setSalesOwner(input.getString("Sales Owner"));
        }

        if (input.containsKey("Lifecycle Stage")) {
            contactForm.setLifecycleStage(input.getString("Lifecycle Stage"));
        }

        if (input.containsKey("Source")) {
            contactForm.setSource(input.getString("Source"));
        }

        if (input.containsKey("Territory")) {
            contactForm.setTerritory(input.getString("Territory"));
        }

        if (input.containsKey("Tags")) {
            final JsonArray tagsArray = input.getJsonArray("Tags");
            final List<String> tags = new ArrayList<>();

            for (int i = 0; i < tagsArray.size(); i++) {
                tags.add(tagsArray.getString(i));
            }
            contactForm.setTags(tags);
        }

        if (input.containsKey("Date Of Birth")) {
            contactForm.setDateOfBirth(input.getString("Date Of Birth"));
        }

        if (input.containsKey("Department")) {
            contactForm.setDepartment(input.getString("Department"));
        }

        if (input.containsKey("Address")) {
            final JsonObject addressJson = input.getJsonObject("Address");
            final ContactForm.Address address = new ContactForm.Address();

            if (addressJson.containsKey("Address Line 1")) {
                address.setAddressLine1(addressJson.getString("Address Line 1"));
            }
            if (addressJson.containsKey("Address Line 2")) {
                address.setAddressLine2(addressJson.getString("Address Line 2"));
            }
            if (addressJson.containsKey("City")) {
                address.setCity(addressJson.getString("City"));
            }
            if (addressJson.containsKey("State")) {
                address.setState(addressJson.getString("State"));
            }
            if (addressJson.containsKey("Country")) {
                address.setCountry(addressJson.getString("Country"));
            }
            if (addressJson.containsKey("Pincode")) {
                address.setPincode(addressJson.getString("Pincode"));
            }

            contactForm.setAddress(address);
        }

        if (input.containsKey("Facebook")) {
            contactForm.setFacebook(input.getString("Facebook"));
        }

        if (input.containsKey("Twitter")) {
            contactForm.setTwitter(input.getString("Twitter"));
        }

        if (input.containsKey("LinkedIn")) {
            contactForm.setLinkedin(input.getString("LinkedIn"));
        }

        if (input.containsKey("Subscription Status")) {
            contactForm.setSubscriptionStatus(input.getString("Subscription Status"));
        }

        if (input.containsKey("SubscriptionTypes")) {
            contactForm.setSubscriptionTypes(input.getString("Subscription Types"));
        }

        if (input.containsKey("Time Zone")) {
            contactForm.setTimeZone(input.getString("Time Zone"));
        }

        return contactForm;
    }

    @Test(dataProvider = "contactSystemFields")
    public void fill(final Object object) {
        contactAddForm.fill(getContactForm(object));
    }
}
