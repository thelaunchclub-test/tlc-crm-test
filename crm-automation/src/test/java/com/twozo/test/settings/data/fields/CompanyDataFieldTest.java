package com.twozo.test.settings.data.fields;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.crm.automation.page.settings.data.fields.FieldStatus;
import com.twozo.crm.automation.page.settings.data.fields.company.CompanyDataField;
import com.twozo.crm.automation.page.settings.data.fields.field.Field;
import com.twozo.crm.automation.page.url.URL;
import com.twozo.crm.automation.page.url.settings.SettingsURL;
import com.twozo.test.TestDataProvider;
import com.twozo.web.driver.service.WebAutomationDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

public class CompanyDataFieldTest extends DataFieldTest {

    private static final String COMPANY_DATA_FIELDS = Paths.get(DATA_FIELDS, "company").toString();

    private CompanyDataField companyDataField;
    private WebAutomationDriver automationDriver;

    @DataProvider(name = "companySystemFields")
    private static Object[][] getCompanySystemFieldData() {
        return new TestDataProvider().getTestData(getFilePath(COMPANY_DATA_FIELDS, "SystemFields.json"));
    }

    @DataProvider(name = "addViewAndRequired")
    private static Object[][] getAddView() {
        return new TestDataProvider().getTestData(getFilePath(COMPANY_DATA_FIELDS, "AddViewAndRequired.json"));
    }

    @DataProvider(name = "autoGeneratingField")
    private static Object[][] getAutoGeneratingFieldData() {
        return new TestDataProvider().getTestData(getFilePath(COMPANY_DATA_FIELDS, "AutoGeneratingFields.json"));
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
        companyDataField = CompanyDataField.getInstance(automationDriver);

        companyDataField.navigateTo(link);

        for (final BrowserCookie cookie : cookies) {
            companyDataField.addCookie(cookie);
        }

        companyDataField.maximize();
        companyDataField.navigateTo(SettingsURL.COMPANY_DATA_FIELDS);
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
        Assert.assertFalse(companyDataField.enableAddViewForAutoGeneratingField(getFieldStatus(object)));
    }

    @Test(dataProvider = "autoGeneratingField")
    public void enableRequiredForAutoGeneratingField(final Object object) {
        Assert.assertFalse(companyDataField.enableRequiredForAutoGeneratingField(getFieldStatus(object)));
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
        }

        Assert.assertTrue(companyDataField.isLimitExceededNotificationDisplayed());
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

    @Test
    public void checkAddForm() {
        Assert.assertTrue(isPresentInAddForm(companyDataField.getFieldsEnabledAsAddView()));
    }

    @Test
    public void checkAddFormAsRequired() {
        Assert.assertTrue(isPresentInAddForm(companyDataField.getFieldsEnabledAsRequired()));
    }

    @Test
    public void checkSummary() {
        Assert.assertTrue(isPresentInSummary(companyDataField.getFieldsForSummary()));
    }

    @Test
    public void checkColumnSettings() {
        Assert.assertTrue(isPresentInColumnSettings(companyDataField.getAllFields()));
    }

    @Override
    public boolean isPresentInSummary(final Collection<String> fields) {
        companyDataField.navigateTo(URL.COMPANIES);
        companyDataField.switchToSummary();

        return companyDataField.isPresentInSummary(fields);
    }

    @Override
    public boolean isPresentInAddForm(final Collection<String> fields) {
        companyDataField.navigateTo(URL.COMPANIES);
        companyDataField.switchToAddCompanyForm();

        return companyDataField.isPresentInAddForm(fields);
    }

    @Override
    public boolean isPresentInColumnSettings(final Field[] fields) {
        companyDataField.navigateTo(URL.COMPANIES);
        companyDataField.switchToColumnSettings();

        return companyDataField.isPresentInColumnSettings(fields);
    }
}
