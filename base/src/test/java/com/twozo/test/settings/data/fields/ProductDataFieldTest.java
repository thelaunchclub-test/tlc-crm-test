package com.twozo.test.settings.data.fields;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.page.homepage.HomePage;
import com.twozo.page.settings.data.fields.FieldStatus;
import com.twozo.page.settings.data.fields.field.Field;
import com.twozo.page.settings.data.fields.field.FieldElement;
import com.twozo.page.settings.data.fields.product.ProductDataField;
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

public class ProductDataFieldTest extends DataFieldTest {

    private ProductDataField productDataField;
    private HomePage homePage;
    private WebAutomationDriver automationDriver;

    @DataProvider(name = "productSystemFields")
    private static Object[][] getContactSystemFieldData() {
        return new TestDataProvider().getTestCases("settings.data.fields/product/ProductSystemFields.json");
    }

    @DataProvider(name = "addViewAndRequired")
    private static Object[][] getAddView() {
        return new TestDataProvider().getTestCases("settings.data.fields/product/AddViewAndRequired.json");
    }

    @DataProvider(name = "autoGeneratingField")
    private static Object[][] getAutoGeneratingFieldData() {
        return new TestDataProvider().getTestCases("settings.data.fields/product/AutoGeneratingFields.json");
    }

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
        automationDriver.getWebNavigator().to(SettingsURL.PRODUCT_DATA_FIELDS);
        automationDriver.getWebWindowHandler().maximize();

        homePage = HomePage.getInstance(automationDriver);
        productDataField = ProductDataField.getInstance(automationDriver);
    }

    @AfterMethod
    public void after() {
        automationDriver.close();
    }

    @Test
    public void verifyDefaultSystemFields() {
        Assert.assertTrue(productDataField.verifyDefaultSystemFields());
    }

    @Test(dataProvider = "productSystemFields")
    public void addSystemFields(final Object object) {
        Assert.assertTrue(productDataField.addSystemField(getFieldStatus(object)));
    }


    @Test(dataProvider = "addViewAndRequired")
    public void enableAddView(final Object object) {
        Assert.assertTrue(productDataField.enableAddView(getFieldStatus(object)));
    }

    @Test(dataProvider = "addViewAndRequired")
    public void enableRequired(final Object object) {
        Assert.assertTrue(productDataField.enableRequired(getFieldStatus(object)));
    }

    @Test(dataProvider = "autoGeneratingField")
    public void enableAddViewForAutoGeneratingField(final Object object) {
        Assert.assertTrue(!productDataField.enableAddView(getFieldStatus(object)));
    }

    @Test(dataProvider = "autoGeneratingField")
    public void enableRequiredForAutoGeneratingField(final Object object) {
        Assert.assertTrue(!productDataField.enableRequired(getFieldStatus(object)));
    }

    @Test(dataProvider = "productSystemFields")
    public void hideField(final Object object) {
        Assert.assertTrue(productDataField.hideField(getFieldStatus(object).getFieldName()));
    }

    @Test(dataProvider = "customField")
    public void addCustomFieldsWithAllFieldType(final Object object) {
        Assert.assertTrue(productDataField.addCustomField(getFieldStatus(object)));
    }

    @Test(dataProvider = "editData")
    public void editFieldName(final Object object) {
        Assert.assertTrue(productDataField.editCustomField(getFieldStatus(object)));
    }

    @Test(dataProvider = "deleteField")
    public void deleteField(final Object object) {
        Assert.assertTrue(productDataField.deleteField(getFieldStatus(object).getFieldName()));
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
            productDataField.checkMaximumLimit(fieldStatus);

            if (i!=11) {
                productDataField.refresh();
            }
        }
        Assert.assertTrue(productDataField.isLimitExceededNotificationDisplayed());
    }

    @Test
    public void checkAddForm() {
        Assert.assertTrue(isPresentInAddForm(productDataField.getFieldsForAddViewAndRequired(FieldElement.ADD_VIEW_CHECKBOX)));
    }

    @Test
    public void checkAddFormAsRequired() {
        Assert.assertTrue(isPresentInAddForm(productDataField.getFieldsForAddViewAndRequired(FieldElement.REQUIRED_CHECKBOX)));
    }

    @Test
    public void checkSummary() {
        Assert.assertTrue(isPresentInSummary(productDataField.getFieldsForSummary()));
    }

    @Test
    public void checkColumnSettings() {
        Assert.assertTrue(isPresentInColumnSettings(productDataField.getAllFields()));
    }

    @Override
    public boolean isPresentInSummary(final Collection<String> fields) {
        webNavigator.to(URL.PRODUCTS);
        productDataField.switchToSummary();

        return productDataField.isPresentInSummary(fields);
    }

    @Override
    public boolean isPresentInAddForm(final Collection<String> fields) {
        webNavigator.to(URL.PRODUCTS);
        productDataField.switchToAddProductForm();

        return productDataField.isPresentInAddForm(fields);
    }

    @Override
    public boolean isPresentInColumnSettings(final Field[] fields) {
        webNavigator.to(URL.PRODUCTS);
        productDataField.switchToColumnSettings();

        return productDataField.isPresentInColumnSettings(fields);
    }
}
