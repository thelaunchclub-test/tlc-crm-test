package com.twozo.test.settings.data.fields;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.page.deal.Deal;
import com.twozo.page.homepage.HomePage;
import com.twozo.page.settings.data.fields.FieldStatus;
import com.twozo.page.settings.data.fields.deal.DealDataField;
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
import java.util.List;

public class DealDataFieldTest extends DataFieldTest {

    private DealDataField dealDataField;
    private HomePage homePage;
    private WebAutomationDriver automationDriver;

    @DataProvider(name = "dealSystemFields")
    private static Object[][] getDealSystemFieldData() {
        return new TestDataProvider().getTestCases("deal/SystemFields.json");
    }

    @DataProvider(name = "addViewAndRequired")
    private static Object[][] getAddView() {
        return new TestDataProvider().getTestCases("deal/AddViewAndRequired.json");
    }

    @DataProvider(name = "autoGeneratingField")
    private static Object[][] getAutoGeneratingFieldData() {
        return new TestDataProvider().getTestCases("deal/AutoGeneratingFields.json");
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
//        webNavigator.to(URL.CONTACTS);
//        automationDriver.getWebWindowHandler().maximize();
//        Deal.getInstance(automationDriver).addDeal().createDeal();
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
        automationDriver.getWebNavigator().to(SettingsURL.DEAL_DATA_FIELDS);
        automationDriver.getWebWindowHandler().maximize();

        homePage = HomePage.getInstance(automationDriver);
        dealDataField = DealDataField.getInstance(automationDriver);
    }

    @AfterMethod
    public void after() {
        automationDriver.close();
    }

    @Test
    public void verifyDefaultSystemFields() {
        Assert.assertTrue(dealDataField.verifyDefaultSystemFields());
    }

    @Test(dataProvider = "dealSystemFields")
    public void addSystemField(final Object object) {
        Assert.assertTrue(dealDataField.addSystemField(getFieldStatus(object)));
    }


    @Test(dataProvider = "addViewAndRequired")
    public void enableAddView(final Object object) {
        Assert.assertTrue(dealDataField.enableAddView(getFieldStatus(object)));
    }

    @Test(dataProvider = "addViewAndRequired")
    public void enableRequired(final Object object) {
        Assert.assertTrue(dealDataField.enableRequired(getFieldStatus(object)));
    }

    @Test(dataProvider = "autoGeneratingField")
    public void enableAddViewForAutoGeneratingField(final Object object) {
        Assert.assertFalse(dealDataField.enableAddView(getFieldStatus(object)));
    }

    @Test(dataProvider = "autoGeneratingField")
    public void enableRequiredForAutoGeneratingField(final Object object) {
        Assert.assertFalse(dealDataField.enableRequired(getFieldStatus(object)));
    }

    @Test(dataProvider = "dealSystemFields")
    public void hideField(final Object object) {
        Assert.assertTrue(dealDataField.hideField(getFieldStatus(object).getFieldName()));
    }

    @Test(dataProvider = "customField")
    public void addCustomFieldsWithAllFieldType(final Object object) {
        Assert.assertTrue(dealDataField.addCustomField(getFieldStatus(object)));
    }

    @Test(dataProvider = "editData")
    public void editFieldName(final Object object) {
        Assert.assertTrue(dealDataField.editCustomField(getFieldStatus(object)));
    }

    @Test(dataProvider = "deleteField")
    public void deleteField(final Object object) {
             Assert.assertTrue(dealDataField.deleteField(getFieldStatus(object).getFieldName()));
    }

    @Test
    public void checkMaxLimit() {
        final String fieldName = "CustomField";
        final List<String> choices = List.of("a","b");


        for (int i = 1; i <= 11; i++) {
            FieldStatus fieldStatus = new FieldStatus();
            fieldStatus.setFieldName(String.format("%s%d", fieldName, i));
            fieldStatus.setFieldType("Multi Select");
            fieldStatus.setChoices(choices);
            dealDataField.checkMaximumLimit(fieldStatus);

            if (i!=11) {
                dealDataField.refresh();
            }
        }
        Assert.assertTrue(dealDataField.isLimitExceededNotificationDisplayed());
    }

    @Test
    public void checkAddForm() {
        Assert.assertTrue(isPresentInAddForm(dealDataField.getFieldsForAddViewAndRequired(FieldElement.ADD_VIEW_CHECKBOX)));
    }

    @Test
    public void checkAddFormAsRequired() {
        Assert.assertTrue(isPresentInAddForm(dealDataField.getFieldsForAddViewAndRequired(FieldElement.REQUIRED_CHECKBOX)));
    }

    @Test
    public void checkSummary() {
        Assert.assertTrue(isPresentInSummary(dealDataField.getFieldsForSummary()));
    }

    @Test
    public void checkColumnSettings() {
        Assert.assertTrue(isPresentInColumnSettings(dealDataField.getFields()));
    }

    @Test
    public void checkPipeline() {
        Assert.assertTrue(dealDataField.checkPipeline());
    }

    @Override
    public boolean isPresentInSummary(final List<String> fields) {
        webNavigator.to(URL.DEALS);
        dealDataField.switchToSummary();

        return dealDataField.isPresentInSummary(fields);
    }

    @Override
    public boolean isPresentInAddForm(final List<String> fields) {
        webNavigator.to(URL.DEALS);
        dealDataField.switchToAddDealForm();

        return dealDataField.isPresentInAddForm(fields);
    }

    @Override
    public boolean isPresentInColumnSettings(final List<String> fields) {
        webNavigator.to(URL.DEALS);
        Deal.getInstance(automationDriver).switchToColumnSettings();

        return dealDataField.isPresentInColumnSettings(fields);
    }
}
