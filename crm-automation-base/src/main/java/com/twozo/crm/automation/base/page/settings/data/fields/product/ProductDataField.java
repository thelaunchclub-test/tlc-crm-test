package com.twozo.crm.automation.base.page.settings.data.fields.product;

import com.twozo.crm.automation.base.page.settings.data.fields.DataFieldsPage;
import com.twozo.crm.automation.base.page.settings.data.fields.product.field.ProductField;
import com.twozo.crm.automation.base.page.settings.data.fields.AbstractDataField;
import com.twozo.crm.automation.base.page.settings.data.fields.field.Field;
import com.twozo.crm.automation.base.page.settings.data.fields.field.FieldAttribute;
import com.twozo.crm.automation.base.page.settings.data.fields.field.FieldTypePath;
import com.twozo.crm.automation.base.page.settings.data.fields.field.SystemField;
import com.twozo.crm.automation.base.page.xpath.XPathBuilder;
import com.twozo.web.driver.service.WebAutomationDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ProductDataField extends DataFieldsPage {

    private static ProductDataField product;

    protected ProductDataField(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);

//        if (!getURL().equals(SettingsURL.PRODUCT_DATA_FIELDS)) {
//            throw ErrorCode.get(WebDriverErrorCode.EXPECTED_PAGE_NOT_FOUND, "exp page not found");
//        }
        enableProduct();
    }

    public static ProductDataField getInstance(final WebAutomationDriver webAutomationDriver) {
        product = new ProductDataField(webAutomationDriver);

        return product;
    }

    public void enableProduct() {
        try {
            if (isDisplayed(findByXpath("//*[contains(text(),'Enables')]"))) {
                click(findByXpath("//*[@class='MuiSwitch-root MuiSwitch-sizeMedium css-1v2eis']"));
            }
        } catch (Exception exception) {

        }
    }

    public boolean verifyActiveContactTab() {
        return isDisplayed(getActiveContactTab());
    }

    public String getNameDiv() {
        return getFieldBlock(ProductField.NAME.getName());
    }

    public String getSalesOwnerDiv() {
        return getFieldBlock(ProductField.SALES_OWNER.getName());
    }

    public String getProductCodeDiv() {
        return getFieldBlock(ProductField.PRODUCT_CODE.getName());
    }

    public String getCategoryDiv() {
        return getFieldBlock(ProductField.CATEGORY.getName());
    }

    public String getUnitPriceDiv() {
        return getFieldBlock(ProductField.UNIT_PRICE.getName());
    }

    private SystemField getNameField() {
        final String name = ProductField.NAME.getName();

        return new SystemField(
                getDraggableElement(ProductField.NAME),
                getFieldName(ProductField.NAME),
                getFieldType(ProductField.NAME),
                isSelected(getAddViewCheckboxOf(name)),
                isSelected(getRequiredCheckboxOf(name)),
                null
        );
    }

    private SystemField getSalesOwnerField() {
        final String salesOwner = ProductField.SALES_OWNER.getName();

        return new SystemField(
                getDraggableElement(ProductField.SALES_OWNER),
                getFieldName(ProductField.SALES_OWNER),
                getFieldType(ProductField.SALES_OWNER),
                isSelected(getAddViewCheckboxOf(salesOwner)),
                isSelected(getRequiredCheckboxOf(salesOwner)),
                null
        );
    }

    private SystemField getProductCodeField() {
        final String productCode = ProductField.PRODUCT_CODE.getName();

        return new SystemField(
                getDraggableElement(ProductField.PRODUCT_CODE),
                getFieldName(ProductField.PRODUCT_CODE),
                getFieldType(ProductField.PRODUCT_CODE),
                isSelected(getAddViewCheckboxOf(productCode)),
                !isSelected(getRequiredCheckboxOf(productCode)),
                null
        );
    }

    private SystemField getCategoryField() {
        final String category = ProductField.CATEGORY.getName();

        return new SystemField(
                getDraggableElement(ProductField.CATEGORY),
                getFieldName(ProductField.CATEGORY),
                getFieldType(ProductField.CATEGORY),
                isSelected(getAddViewCheckboxOf(category)),
                !isSelected(getRequiredCheckboxOf(category)),
                null
        );
    }

    private SystemField getUnitPriceField() {
        final String unitPrice = ProductField.UNIT_PRICE.getName();

        return new SystemField(
                getDraggableElement(ProductField.UNIT_PRICE),
                getFieldName(ProductField.UNIT_PRICE),
                getFieldType(ProductField.UNIT_PRICE),
                !isSelected(getAddViewCheckboxOf(unitPrice)),
                !isSelected(getRequiredCheckboxOf(unitPrice)),
                null
        );
    }


    public boolean verifyActiveProductTab() {
        return isDisplayed(getActiveProductTab());
    }

    public boolean choicesForActiveAndTaxable() {
        final String[] options = {
                "Yes",
                "No"
        };

        return areAllChoicesPresent(options);
    }

    public boolean choicesForCategory() {
        final String[] options = {
                "Unit",
                "Subscription"
        };

        return areAllChoicesPresent(options);
    }

    public boolean choicesForType() {
        final String[] options = {
                "Hardware",
                "Software"
        };

        return areAllChoicesPresent(options);
    }

    public boolean checkCategory() {
        final String category = ProductField.CATEGORY.getName();

        if (!isFieldPresent(category)) {
            addSystemField(category);
        }


        if (!isFieldElementDisplayed(category, FieldAttribute.DRAGGABLE)) {
            return false;
        }

        if (!isFieldElementDisplayed(category, FieldTypePath.DROPDOWN)) {
            return false;
        }

        click(findByXpath(formatTwoStrings(category, XPathBuilder.getXPathByText("2"))));

        return choicesForCategory();
    }

    public boolean checkActive() {
        final String active = ProductField.ACTIVE.getName();

        if (!isFieldPresent(active)) {
            addSystemField(active);
        }

        if (!isFieldElementDisplayed(active, FieldAttribute.DRAGGABLE)) {
            return false;
        }

        if (!isFieldElementDisplayed(active, FieldTypePath.DROPDOWN)) {
            return false;
        }
        click(findByXpath(formatTwoStrings(active, XPathBuilder.getXPathByText("2"))));

        return choicesForActiveAndTaxable();
    }

    public boolean checkTaxable() {
        final String taxable = ProductField.TAXABLE.getName();

        if (!isFieldPresent(taxable)) {
            addSystemField(taxable);
        }

        if (!isFieldElementDisplayed(taxable, FieldAttribute.DRAGGABLE)) {
            return false;
        }

        if (!isFieldElementDisplayed(taxable, FieldTypePath.DROPDOWN)) {
            return false;
        }
        click(findByXpath(formatTwoStrings(taxable, XPathBuilder.getXPathByText("2"))));

        return choicesForActiveAndTaxable();
    }

    public boolean checkType() {
        final String type = ProductField.TYPE.getName();

        if (!isFieldPresent(type)) {
            addSystemField(type);
        }

        if (!isFieldElementDisplayed(type, FieldAttribute.DRAGGABLE)) {
            return false;
        }

        if (!isFieldElementDisplayed(type, FieldTypePath.DROPDOWN)) {
            return false;
        }
        click(findByXpath(formatTwoStrings(type, XPathBuilder.getXPathByText("2"))));

        return choicesForType();
    }

    @Override
    protected Collection<Field> getDefaultFields() {
        return ProductField.getDefaultFields();
    }

    @Override
    public Field[] getAllFields() {
        return ProductField.values();
    }

    @Override
    protected List<String> getMandatoryFields() {
        return Arrays.asList(ProductField.NAME.getName(), ProductField.SALES_OWNER.getName());
    }

    @Override
    public boolean verifyNonDraggableFields() {
        return isNonDraggableIconDisplayed(getNameDiv());
    }

    @Override
    protected Collection<Record> getDefaultSystemFieldElements() {
        return List.of(getNameField(), getSalesOwnerField(), getProductCodeField(), getCategoryField(), getUnitPriceField());
    }

    @Override
    public boolean isDefaultFieldsVisibleInSummary() {
        final Collection<Field> summaryDefaultFields = List.of(ProductField.SALES_OWNER, ProductField.UNIT_PRICE,
                ProductField.PRODUCT_CODE, ProductField.CATEGORY);

        for (final Field summaryDefaultField : summaryDefaultFields) {

            if (!isDisplayed(findByXpath(formatTwoStrings("//*[@class='css-itno5t']",
                    XPathBuilder.getXPathByText(summaryDefaultField.getName()))))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean uncheckMandatoryFields() {
        final String[] mandatoryFields = new String[]{getNameDiv()};

        uncheck(mandatoryFields);

        return true;
    }

    public Collection<String> getFieldsForSummary() {
        final Collection<String> fieldsNotToDisplay = List.of("Unit Price");

        return getFieldsForSummary(fieldsNotToDisplay);
    }
}
