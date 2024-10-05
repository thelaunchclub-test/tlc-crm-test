package com.twozo.test.settings.data.fields;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.page.homepage.HomePage;
import com.twozo.page.settings.data.fields.FieldStatus;
import com.twozo.page.settings.data.fields.contact.ContactDataField;
import com.twozo.page.settings.data.fields.field.Field;
import com.twozo.page.settings.data.fields.field.FieldElement;
import com.twozo.page.url.URL;
import com.twozo.page.url.settings.SettingsURL;
import com.twozo.test.TestDataProvider;
import com.twozo.web.driver.service.WebAutomationDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collection;
import java.util.List;

public class ContactDataFieldTest extends DataFieldTest {

    private ContactDataField contactDataField;
    private HomePage homePage;
    private WebAutomationDriver automationDriver;

    @DataProvider(name = "contactSystemFields")
    private static Object[][] getContactSystemFieldData() {
        return new TestDataProvider().getTestCases("settings.data.fields/contact/SystemFields.json");
    }

    @DataProvider(name = "addViewAndRequired")
    private static Object[][] getAddView() {
        return new TestDataProvider().getTestCases("settings.data.fields/contact/AddViewAndRequired.json");
    }

    @DataProvider(name = "autoGeneratingField")
    private static Object[][] getAutoGeneratingFieldData() {
        return new TestDataProvider().getTestCases("settings.data.fields/contact/AutoGeneratingFields.json");
    }

//    @BeforeClass
//    public void set() {
//        automationDriver = WebAutomationDriver.get();
//        webNavigator = automationDriver.getWebNavigator();
//        webNavigator.to(link);
//
//        for (final BrowserCookie cookie : cookies) {
//            automationDriver.getSessionCookie().addCookie(cookie);
//        }
//
//        automationDriver.getWebWindowHandler().maximize();
//        automationDriver.getImplicitWaitHandler().implicitWait(Duration.ofSeconds(10));
//        webNavigator.to(URL.CONTACTS);
//        automationDriver.getWebWindowHandler().maximize();
//        Contact.getInstance(automationDriver).addContact().createContact("a", "a@gmail.com", "9876543211");
//    }

    @BeforeMethod
    public void before() {
        automationDriver = WebAutomationDriver.get();
        webNavigator = automationDriver.getWebNavigator();
        webNavigator.to(link);

        for (final BrowserCookie cookie : cookies) {
            automationDriver.getSessionCookie().addCookie(cookie);
        }

        automationDriver.getWebWindowHandler().maximize();
        automationDriver.getImplicitWaitHandler().implicitWait(Duration.ofSeconds(10));
        webNavigator.to(SettingsURL.CONTACT_DATA_FIELDS);
        automationDriver.getWebWindowHandler().maximize();
        homePage = HomePage.getInstance(automationDriver);
        contactDataField = ContactDataField.getInstance(automationDriver);
    }

    @AfterMethod
    public void after() {
        automationDriver.close();
    }

    @Test
    public void verifyDefaultSystemFields() {
        Assert.assertTrue(contactDataField.verifyDefaultSystemFields());
    }

    @Test(dataProvider = "contactSystemFields")
    public void addSystemField(final Object object) {
        Assert.assertTrue(contactDataField.addSystemField(getFieldStatus(object)));
    }

    @Test(dataProvider = "addViewAndRequired")
    public void enableAddView(final Object object) {
        Assert.assertTrue(contactDataField.enableAddView(getFieldStatus(object)));
    }

    @Test(dataProvider = "addViewAndRequired")
    public void enableRequired(final Object object) {
        Assert.assertTrue(contactDataField.enableRequired(getFieldStatus(object)));
    }

    @Test(dataProvider = "autoGeneratingField")
    public void enableAddViewForAutoGeneratingField(final Object object) {
        Assert.assertFalse(contactDataField.enableAddView(getFieldStatus(object)));
    }

    @Test(dataProvider = "autoGeneratingField")
    public void enableRequiredForAutoGeneratingField(final Object object) {
        Assert.assertFalse(contactDataField.enableRequired(getFieldStatus(object)));
    }

    @Test(dataProvider = "contactSystemFields")
    public void hideField(final Object object) {
        Assert.assertTrue(contactDataField.hideField(getFieldStatus(object).getFieldName()));
    }

    @Test(dataProvider = "customField")
    public void addCustomFieldsWithAllFieldType(final Object object) {
        Assert.assertTrue(contactDataField.addCustomField(getFieldStatus(object)));
    }

    @Test(dataProvider = "editData")
    public void editFieldName(final Object object) {
        Assert.assertTrue(contactDataField.editCustomField(getFieldStatus(object)));
    }

    @Test(dataProvider = "deleteField")
    public void deleteField(final Object object) {
        Assert.assertTrue(contactDataField.deleteField(getFieldStatus(object).getFieldName()));
    }

    @Test
    public void checkMaxLimit() {
        final String fieldName = "CustomField";
        final List<String> choices = List.of("a", "b");

        for (int i = 1; i <= 11; i++) {
            FieldStatus fieldStatus = new FieldStatus();
            fieldStatus.setFieldName(String.format("%s%d", fieldName, i));
            fieldStatus.setFieldType("Multi Select");
            fieldStatus.setChoices(choices);
            contactDataField.checkMaximumLimit(fieldStatus);

            if (i != 11) {
                contactDataField.refresh();
            }
        }
        Assert.assertTrue(contactDataField.isLimitExceededNotificationDisplayed());
    }

    @Test
    public void checkAddForm() {
        Assert.assertTrue(isPresentInAddForm(contactDataField.getFieldsForAddViewAndRequired(FieldElement.ADD_VIEW_CHECKBOX)));
    }

    @Test
    public void checkAddFormAsRequired() {
        Assert.assertTrue(isPresentInAddForm(contactDataField.getFieldsForAddViewAndRequired(FieldElement.REQUIRED_CHECKBOX)));
    }

    @Test
    public void checkSummary() {
        Assert.assertTrue(isPresentInSummary(contactDataField.getFieldsForSummary()));
    }

    @Test
    public void checkColumnSettings() {
        Assert.assertTrue(isPresentInColumnSettings(contactDataField.getAllFields()));
    }

    @Test
    public void checkSubscriptionStatus() {
        Assert.assertTrue(contactDataField.checkSubscriptionStatus());
    }

    @Test
    public void checkLifecycleStage() {
        Assert.assertTrue(contactDataField.checkLifecycleStage());
    }

    @Test
    public void checkTimeZone() {
        Assert.assertTrue(contactDataField.checkTimezone());
    }

    @Test
    public void checkSource() {
        Assert.assertTrue(contactDataField.checkSource());
    }

    @Override
    public boolean isPresentInSummary(final Collection<String> fields) {
        webNavigator.to(URL.CONTACTS);
        contactDataField.switchToSummary();

        return contactDataField.isPresentInSummary(fields);
    }

    @Override
    public boolean isPresentInAddForm(final Collection<String> fields) {
        webNavigator.to(URL.CONTACTS);
        contactDataField.switchToAddContactForm();

        return contactDataField.isPresentInAddForm(fields);
    }

    @Override
    public boolean isPresentInColumnSettings(final Field[] fields) {
        webNavigator.to(URL.CONTACTS);
        contactDataField.switchToColumnSettings();

        return contactDataField.isPresentInColumnSettings(fields);
    }
}
