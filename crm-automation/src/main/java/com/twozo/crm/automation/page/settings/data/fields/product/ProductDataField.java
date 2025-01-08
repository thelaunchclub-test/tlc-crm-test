package com.twozo.crm.automation.page.settings.data.fields.product;

import com.twozo.crm.automation.page.settings.data.fields.company.field.CompanyField;
import com.twozo.crm.automation.page.settings.data.fields.product.field.ProductField;
import com.twozo.crm.automation.page.xpath.XPathBuilder;
import com.twozo.crm.automation.page.settings.data.fields.AbstractDataField;
import com.twozo.crm.automation.page.settings.data.fields.field.Field;
import com.twozo.crm.automation.page.settings.data.fields.field.FieldElement;
import com.twozo.crm.automation.page.settings.data.fields.field.FieldTypePath;
import com.twozo.crm.automation.page.settings.data.fields.field.SystemField;
import com.twozo.web.driver.service.WebAutomationDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDataField extends AbstractDataField {

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
                getDraggableElement(name),
                getFieldName(name),
                getFieldType(name, ProductField.NAME.getFieldType()),
                isSelected(getAddViewCheckboxOf(name)),
                isSelected(getRequiredCheckboxOf(name)),
                null
        );
    }

    private SystemField getSalesOwnerField() {
        final String salesOwner = ProductField.SALES_OWNER.getName();

        return new SystemField(
                getDraggableElement(salesOwner),
                getFieldName(salesOwner),
                getFieldType(salesOwner, ProductField.SALES_OWNER.getFieldType()),
                isSelected(getAddViewCheckboxOf(salesOwner)),
                isSelected(getRequiredCheckboxOf(salesOwner)),
                null
        );
    }

    private SystemField getProductCodeField() {
        final String productCode = ProductField.PRODUCT_CODE.getName();

        return new SystemField(
                getDraggableElement(productCode),
                getFieldName(productCode),
                getFieldType(productCode, ProductField.PRODUCT_CODE.getFieldType()),
                isSelected(getAddViewCheckboxOf(productCode)),
                !isSelected(getRequiredCheckboxOf(productCode)),
                null
        );
    }

    private SystemField getCategoryField() {
        final String category = ProductField.CATEGORY.getName();

        return new SystemField(
                getDraggableElement(category),
                getFieldName(category),
                getFieldType(category, ProductField.CATEGORY.getFieldType()),
                isSelected(getAddViewCheckboxOf(category)),
                !isSelected(getRequiredCheckboxOf(category)),
                null
        );
    }

    private SystemField getUnitPriceField() {
        final String unitPrice = ProductField.UNIT_PRICE.getName();

        return new SystemField(
                getDraggableElement(unitPrice),
                getFieldName(unitPrice),
                getFieldType(unitPrice, ProductField.UNIT_PRICE.getFieldType()),
                !isSelected(getAddViewCheckboxOf(unitPrice)),
                !isSelected(getRequiredCheckboxOf(unitPrice)),
                null
        );
    }

    public boolean verifyActiveProductTab() {
        return isDisplayed(getActiveProductTab());
    }

    public boolean choicesForActiveAndTaxable() {
        return areChoicesPresent(List.of("Yes",
                "No"));
    }

    public boolean choicesForCategory() {
        return areChoicesPresent(List.of("Unit",
                "Subscription"));
    }

    public boolean choicesForType() {
        return areChoicesPresent(List.of("Hardware",
                "Software"));
    }

    public boolean checkCategory() {
        final String category = ProductField.CATEGORY.getName();

        if (!isFieldPresent(category)) {
            addField(category);
        }


        if (!isFieldSpecificElementDisplayed(category, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!isFieldSpecificElementDisplayed(category, FieldTypePath.DROPDOWN)) {
            return false;
        }

        click(findByXpath(format(category, XPathBuilder.getXPathByText("2"))));

        return choicesForCategory();
    }

    public boolean checkActive() {
        final String active = ProductField.ACTIVE.getName();

        if (!isFieldPresent(active)) {
            addField(active);
        }

        if (!isFieldSpecificElementDisplayed(active, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!isFieldSpecificElementDisplayed(active, FieldTypePath.DROPDOWN)) {
            return false;
        }
        click(findByXpath(format(active, XPathBuilder.getXPathByText("2"))));

        return choicesForActiveAndTaxable();
    }

    public boolean checkTaxable() {
        final String taxable = ProductField.TAXABLE.getName();

        if (!isFieldPresent(taxable)) {
            addField(taxable);
        }

        if (!isFieldSpecificElementDisplayed(taxable, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!isFieldSpecificElementDisplayed(taxable, FieldTypePath.DROPDOWN)) {
            return false;
        }
        click(findByXpath(format(taxable, XPathBuilder.getXPathByText("2"))));

        return choicesForActiveAndTaxable();
    }

    public boolean checkType() {
        final String type = ProductField.TYPE.getName();

        if (!isFieldPresent(type)) {
            addField(type);
        }

        if (!isFieldSpecificElementDisplayed(type, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!isFieldSpecificElementDisplayed(type, FieldTypePath.DROPDOWN)) {
            return false;
        }
        click(findByXpath(format(type, XPathBuilder.getXPathByText("2"))));

        return choicesForType();
    }

    @Override
    protected Collection<String> getDefaultFields() {
        return ProductField.getDefaultFields();
    }

    @Override
    public List<String> getAllFields() {
        return Arrays.stream(ProductField.values())
                .map(ProductField::getName)
                .collect(Collectors.toList());
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

            if (!isDisplayed(findByXpath(format("//*[@class='css-itno5t']",
                    XPathBuilder.getXPathByText(summaryDefaultField.getName()))))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean uncheckMandatoryFields() {
        uncheck(List.of(ProductField.NAME.getName()));

        return true;
    }

    public Collection<String> getFieldsForSummary() {
        final Collection<String> fieldsNotToDisplay = List.of("Unit Price");

        return getFieldsForSummary(fieldsNotToDisplay);
    }
}
