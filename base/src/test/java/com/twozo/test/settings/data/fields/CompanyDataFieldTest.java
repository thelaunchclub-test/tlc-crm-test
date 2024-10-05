package com.twozo.test.settings.data.fields;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.page.homepage.HomePage;
import com.twozo.page.settings.data.fields.FieldStatus;
import com.twozo.page.settings.data.fields.company.CompanyDataField;
import com.twozo.page.settings.data.fields.field.Field;
import com.twozo.page.settings.data.fields.field.FieldElement;
import com.twozo.page.url.URL;
import com.twozo.page.url.settings.SettingsURL;
import com.twozo.test.TestDataProvider;
import com.twozo.web.driver.service.WebAutomationDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Collection;
import java.util.List;

public class CompanyDataFieldTest extends DataFieldTest {

    private CompanyDataField companyDataField;
    private HomePage homePage;
    private WebAutomationDriver automationDriver;

    @DataProvider(name = "companySystemFields")
    private static Object[][] getCompanySystemFieldData() {
        return new TestDataProvider().getTestCases("settings.data.fields/company/SystemFields.json");
    }

    @DataProvider(name = "addViewAndRequired")
    private static Object[][] getAddView() {
        return new TestDataProvider().getTestCases("settings.data.fields/company/AddViewAndRequired.json");
    }

    @DataProvider(name = "autoGeneratingField")
    private static Object[][] getAutoGeneratingFieldData() {
        return new TestDataProvider().getTestCases("settings.data.fields/company/AutoGeneratingFields.json");
    }

//    @BeforeClass
//    public void set(){
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
//        webNavigator.to(URL.COMPANIES);
//        automationDriver.getWebWindowHandler().maximize();
//        Company.getInstance(automationDriver).addCompany().createCompany("a");
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
        automationDriver.getWebNavigator().to(SettingsURL.COMPANY_DATA_FIELDS);
        automationDriver.getWebWindowHandler().maximize();

        homePage = HomePage.getInstance(automationDriver);
        companyDataField = CompanyDataField.getInstance(automationDriver);
    }

    @AfterMethod
    public void after() {
        automationDriver.close();
    }

    @Test
    public void verifyDefaultSystemFields() {
        Assert.assertTrue(companyDataField.verifyDefaultSystemFields());
    }

    @Test(dataProvider = "companySystemFields")
    public void addSystemFields(final Object object) {
        Assert.assertTrue(companyDataField.addSystemField(getFieldStatus(object)));
    }

    @Test(dataProvider = "addViewAndRequired")
    public void enableAddView(final Object object) {
        Assert.assertTrue(companyDataField.enableAddView(getFieldStatus(object)));
    }

    @Test(dataProvider = "addViewAndRequired")
    public void enableRequired(final Object object) {
        Assert.assertTrue(companyDataField.enableRequired(getFieldStatus(object)));
    }

    @Test(dataProvider = "autoGeneratingField")
    public void enableAddViewForAutoGeneratingField(final Object object) {
        Assert.assertFalse(companyDataField.enableAddView(getFieldStatus(object)));
    }

    @Test(dataProvider = "autoGeneratingField")
    public void enableRequiredForAutoGeneratingField(final Object object) {
        Assert.assertFalse(companyDataField.enableRequired(getFieldStatus(object)));
    }

    @Test(dataProvider = "companySystemFields")
    public void hideField(final Object object) {
        Assert.assertTrue(companyDataField.hideField(getFieldStatus(object).getFieldName()));
    }

    @Test(dataProvider = "customField")
    public void addCustomFieldsWithAllFieldType(final Object object) {
        Assert.assertTrue(companyDataField.addCustomField(getFieldStatus(object)));
    }

    @Test(dataProvider = "editData")
    public void editFieldName(final Object object) {
        Assert.assertTrue(companyDataField.editCustomField(getFieldStatus(object)));
    }

    @Test(dataProvider = "deleteField")
    public void deleteField(final Object object) {
        Assert.assertTrue(companyDataField.deleteField(getFieldStatus(object).getFieldName()));
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
            companyDataField.checkMaximumLimit(fieldStatus);

            if (i != 11) {
                companyDataField.refresh();
            }
        }
        Assert.assertTrue(companyDataField.isLimitExceededNotificationDisplayed());
    }

    @Test
    public void checkAddForm() {
        Assert.assertTrue(isPresentInAddForm(companyDataField.getFieldsForAddViewAndRequired(FieldElement.ADD_VIEW_CHECKBOX)));
    }

    @Test
    public void checkAddFormAsRequired() {
        Assert.assertTrue(isPresentInAddForm(companyDataField.getFieldsForAddViewAndRequired(FieldElement.REQUIRED_CHECKBOX)));
    }

    @Test
    public void checkSummary() {
        Assert.assertTrue(isPresentInSummary(companyDataField.getFieldsForSummary()));
    }

    @Test
    public void checkColumnSettings() {
        Assert.assertTrue(isPresentInColumnSettings(companyDataField.getAllFields()));
    }

    @Test
    public void checkOrganizationStatus() {
        Assert.assertTrue(companyDataField.checkOrganizationStatus());
    }

    @Test
    public void checkIndustryType() {
        Assert.assertTrue(companyDataField.checkIndustryType());
    }

    @Test
    public void checkBusinessType() {
        Assert.assertTrue(companyDataField.checkBusinessType());
    }

    @Override
    public boolean isPresentInSummary(final Collection<String> fields) {
        webNavigator.to(URL.COMPANIES);
        companyDataField.switchToSummary();

        return companyDataField.isPresentInSummary(fields);
    }

    @Override
    public boolean isPresentInAddForm(final Collection<String> fields) {
        webNavigator.to(URL.COMPANIES);
        companyDataField.switchToAddCompanyForm();

        return companyDataField.isPresentInAddForm(fields);
    }

    @Override
    public boolean isPresentInColumnSettings(final Field[] fields) {
        webNavigator.to(URL.COMPANIES);
        companyDataField.switchToColumnSettings();

        return companyDataField.isPresentInColumnSettings(fields);
    }
}
