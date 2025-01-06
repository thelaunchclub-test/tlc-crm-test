package com.twozo.crm.automation.base.page.product;

import com.twozo.crm.automation.base.page.add.form.AddForm;
import com.twozo.crm.automation.base.page.settings.data.fields.product.field.ProductField;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

public class ProductAddForm extends AddForm {

    private static ProductAddForm addProduct;

    protected ProductAddForm(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    @Override
    protected String[] getAutoGeneratingFields() {
        return new String[0];
    }

    @Override
    protected String[] getAddFormSystemFields() {
        return new String[0];
    }


    public static ProductAddForm getInstance(final WebAutomationDriver webAutomationDriver) {
        addProduct = new ProductAddForm(webAutomationDriver);

        return addProduct;
    }

    private WebPageElement getProductCode() {
        return getTextFieldWebPageElement(ProductField.PRODUCT_CODE.getName());
    }

    private WebPageElement getCategory() {
        return getTextFieldWebPageElement(ProductField.CATEGORY.getName());
    }

    private WebPageElement getUnitPrice() {
        return getTextFieldWebPageElement(ProductField.UNIT_PRICE.getName());
    }

    private WebPageElement getActive() {
        return getTextFieldWebPageElement(ProductField.ACTIVE.getName());
    }

    private WebPageElement getTaxable() {
        return getTextFieldWebPageElement(ProductField.TAXABLE.getName());
    }

    private WebPageElement getQuantityOrdered() {
        return getTextFieldWebPageElement(ProductField.QUANTITY_ORDERED.getName());
    }

    private WebPageElement getQuantityInStock() {
        return getTextFieldWebPageElement(ProductField.QUANTITY_IN_STOCK.getName());
    }

    private WebPageElement getSkuNumber() {
        return getTextFieldWebPageElement(ProductField.SKU_NUMBER.getName());
    }

    private WebPageElement getValidTill() {
        return getTextFieldWebPageElement(ProductField.VALID_TILL.getName());
    }
}
