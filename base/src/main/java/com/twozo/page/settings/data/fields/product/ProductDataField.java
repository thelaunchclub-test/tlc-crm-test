package com.twozo.page.settings.data.fields.product;

import com.twozo.commons.exception.ErrorCode;
import com.twozo.page.settings.data.fields.AbstractDataField;
import com.twozo.page.settings.data.fields.field.Field;
import com.twozo.page.settings.data.fields.field.FieldElement;
import com.twozo.page.settings.data.fields.field.FieldTypePath;
import com.twozo.page.settings.data.fields.field.SystemField;
import com.twozo.page.settings.data.fields.product.field.ProductField;
import com.twozo.page.url.settings.SettingsURL;
import com.twozo.page.xpath.XPathBuilder;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;
import com.twozo.web.error.code.WebDriverErrorCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
                refresh();
            }
        } catch (Exception exception) {

        }

    }

    public boolean verifyActiveContactTab() {
        return isDisplayed(getActiveContactTab());
    }

    public String getNameDiv() {
        return getFieldBlock(ProductField.NAME);
    }

    public String getSalesOwnerDiv() {
        return getFieldBlock(ProductField.SALES_OWNER);
    }

    public String getProductCodeDiv() {
        return getFieldBlock(ProductField.PRODUCT_CODE);
    }

    public String getCategoryDiv() {
        return getFieldBlock(ProductField.CATEGORY);
    }

    public String getUnitPriceDiv() {
        return getFieldBlock(ProductField.UNIT_PRICE);
    }

    private SystemField getNameField() {
        final String NameDiv = getNameDiv();

        return new SystemField(
                findByXpath(format(NameDiv, FieldElement.NON_DRAGGABLE)),
                findByXpath(format(NameDiv, XPathBuilder.getXPathByText(ProductField.NAME.getName()))),
                findByXpath(format(NameDiv, XPathBuilder.getXPathByText(ProductField.NAME.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(NameDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(NameDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getSalesOwnerField() {
        final String salesOwnerDiv = getSalesOwnerDiv();

        return new SystemField(
                findByXpath(format(salesOwnerDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(salesOwnerDiv, XPathBuilder.getXPathByText(ProductField.SALES_OWNER.getName()))),
                findByXpath(format(salesOwnerDiv, XPathBuilder.getXPathByText(ProductField.SALES_OWNER.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(salesOwnerDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(salesOwnerDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getProductCodeField() {
        final String productDiv = getProductCodeDiv();

        return new SystemField(
                findByXpath(format(productDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(productDiv, XPathBuilder.getXPathByText(ProductField.PRODUCT_CODE.getName()))),
                findByXpath(format(productDiv, XPathBuilder.getXPathByText(ProductField.PRODUCT_CODE.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(productDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                !isSelected(findByXpath(getPathOfSpecificCheckbox(productDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getCategoryField() {
        final String categoryDiv = getCategoryDiv();

        return new SystemField(
                findByXpath(format(categoryDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(categoryDiv, XPathBuilder.getXPathByText(ProductField.CATEGORY.getName()))),
                findByXpath(format(categoryDiv, XPathBuilder.getXPathByText(ProductField.CATEGORY.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(categoryDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                !isSelected(findByXpath(getPathOfSpecificCheckbox(categoryDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getUnitPriceField() {
        final String unitPriceDiv = getUnitPriceDiv();

        return new SystemField(
                findByXpath(format(unitPriceDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(unitPriceDiv, XPathBuilder.getXPathByText(ProductField.UNIT_PRICE.getName()))),
                findByXpath(format(unitPriceDiv, XPathBuilder.getXPathByText(ProductField.UNIT_PRICE.getFieldType()))),
                !isSelected(findByXpath(getPathOfSpecificCheckbox(unitPriceDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                !isSelected(findByXpath(getPathOfSpecificCheckbox(unitPriceDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    public boolean verifyActiveProductTab() {
        return isDisplayed(getActiveProductTab());
    }

    public boolean choicesForActiveAndTaxable() {
        String[] options = {
                "Yes",
                "No"
        };

        return areChoicesPresent(options);
    }

    public boolean choicesForCategory() {
        String[] options = {
                "Unit",
                "Subscription"
        };

        return areChoicesPresent(options);
    }

    public boolean choicesForType() {
        String[] options = {
                "Hardware",
                "Software"
        };

        return areChoicesPresent(options);
    }

    public boolean checkCategory() {
        final String category = ProductField.CATEGORY.getName();

        if (!isFieldPresent(category)) {
            addField(category);
        }


        if (!checkSpecificElement(category, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!checkSpecificElement(category, FieldTypePath.DROPDOWN)) {
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

        if (!checkSpecificElement(active, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!checkSpecificElement(active, FieldTypePath.DROPDOWN)) {
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

        if (!checkSpecificElement(taxable, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!checkSpecificElement(taxable, FieldTypePath.DROPDOWN)) {
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

        if (!checkSpecificElement(type, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!checkSpecificElement(type, FieldTypePath.DROPDOWN)) {
            return false;
        }
        click(findByXpath(format(type, XPathBuilder.getXPathByText("2"))));

        return choicesForType();
    }

    public boolean isPresentInSummary(final Collection<String> fieldsToBePresentInSummary) {
        final Collection<WebPageElement> fieldsPresentInSummary = findElementsByXpath("//*[@class='css-1t62lt9']/div/div/table/tbody/tr/td[1]/p");
        final Collection<String> fieldNames = new ArrayList<>();

        for (final WebPageElement field : fieldsPresentInSummary) {
            String fieldText = getText(field);
            fieldNames.add(fieldText);
        }

        for (final String fieldToBePresent : fieldsToBePresentInSummary) {

            final String formattedField = String.format("%s%s%s", fieldToBePresent, " ", ":");

            if (!fieldNames.contains(formattedField)) {
                System.out.println();
                return false;
            }
        }

        return true;
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
        return Arrays.asList(getNameDiv(), getSalesOwnerDiv());
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
        final String[] mandatoryFields = new String[]{getNameDiv()};
        unCheck(mandatoryFields);

        return true;
    }

    public Collection<String> getFieldsForSummary() {
        final Collection<String> fieldsNotToDisplay = List.of("Unit Price");

        return getFieldsForSummary(fieldsNotToDisplay);

    }
}
