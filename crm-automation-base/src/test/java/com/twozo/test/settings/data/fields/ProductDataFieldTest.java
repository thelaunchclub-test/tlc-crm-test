package com.twozo.test.settings.data.fields;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.crm.automation.base.page.settings.data.fields.FieldStatus;
import com.twozo.crm.automation.base.page.settings.data.fields.field.Field;
import com.twozo.crm.automation.base.page.settings.data.fields.product.ProductDataField;
import com.twozo.crm.automation.base.page.url.URL;
import com.twozo.crm.automation.base.page.url.settings.SettingsURL;
import com.twozo.test.TestDataProvider;
import com.twozo.web.driver.service.WebAutomationDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

public class ProductDataFieldTest extends DataFieldTest {
    private static final String PRODUCT_DATA_FIELDS = Paths.get(DATA_FIELDS,"product").toString();

    private ProductDataField productDataField;
    private WebAutomationDriver automationDriver;

    @DataProvider(name = "productSystemFields")
    private static Object[][] getContactSystemFieldData() {
        return new TestDataProvider().getTestData(getFilePath(PRODUCT_DATA_FIELDS, "ProductSystemFields.json"));
    }

    @DataProvider(name = "addViewAndRequired")
    private static Object[][] getAddView() {
        return new TestDataProvider().getTestData(getFilePath(PRODUCT_DATA_FIELDS, "AddViewAndRequired.json"));
    }

    @DataProvider(name = "autoGeneratingField")
    private static Object[][] getAutoGeneratingFieldData() {
        return new TestDataProvider().getTestData(getFilePath(PRODUCT_DATA_FIELDS, "AutoGeneratingFields.json"));
    }

    @BeforeMethod
    public void before() {
        automationDriver = WebAutomationDriver.get();
        productDataField = ProductDataField.getInstance(automationDriver);
        productDataField.navigateTo(link);

        for (final BrowserCookie cookie : cookies) {
            productDataField.addCookie(cookie);
        }

        productDataField.maximize();
        productDataField.navigateTo(SettingsURL.PRODUCT_DATA_FIELDS);
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
        Assert.assertTrue(!productDataField.enableAddViewForAutoGeneratingField(getFieldStatus(object)));
    }

    @Test(dataProvider = "autoGeneratingField")
    public void enableRequiredForAutoGeneratingField(final Object object) {
        Assert.assertTrue(!productDataField.enableRequiredForAutoGeneratingField(getFieldStatus(object)));
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
        final List<String> choices = List.of("a", "b");

        for (int i = 1; i <= 11; i++) {
            FieldStatus fieldStatus = new FieldStatus();
            fieldStatus.setFieldName(String.format("%s%d", fieldName, i));
            fieldStatus.setFieldType("Multi Select");
            fieldStatus.setChoices(choices);
            productDataField.checkMaximumLimit(fieldStatus);

            if (i != 11) {
                productDataField.refresh();
            }
        }
        Assert.assertTrue(productDataField.isLimitExceededNotificationDisplayed());
    }

    @Test
    public void checkCategory() {
        Assert.assertTrue(productDataField.checkCategory());
    }

    @Test
    public void checkActive() {
        Assert.assertTrue(productDataField.checkActive());
    }

    @Test
    public void checkTaxable() {
        Assert.assertTrue(productDataField.checkTaxable());
    }

    @Test
    public void checkType() {
        Assert.assertTrue(productDataField.checkType());
    }

    @Test
    public void checkAddForm() {
        Assert.assertTrue(isPresentInAddForm(productDataField.getFieldsEnabledAsAddView()));
    }

    @Test
    public void checkAddFormAsRequired() {
        Assert.assertTrue(isPresentInAddForm(productDataField.getFieldsEnabledAsRequired()));
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
        productDataField.navigateTo(URL.PRODUCTS);
        productDataField.switchToSummary();

        return productDataField.isPresentInSummary(fields);
    }

    @Override
    public boolean isPresentInAddForm(final Collection<String> fields) {
        productDataField.navigateTo(URL.PRODUCTS);
        productDataField.switchToAddProductForm();

        return productDataField.isPresentInAddForm(fields);
    }

    @Override
    public boolean isPresentInColumnSettings(final Field[] fields) {
        productDataField.navigateTo(URL.PRODUCTS);
        productDataField.switchToColumnSettings();

        return productDataField.isPresentInColumnSettings(fields);
    }
}
