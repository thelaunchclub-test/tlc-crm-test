package com.twozo.crm.automation.base.page.settings.data.fields.company;

import com.twozo.crm.automation.base.page.settings.data.fields.company.field.CompanyField;
import com.twozo.crm.automation.base.page.settings.data.fields.field.Field;
import com.twozo.crm.automation.base.page.settings.data.fields.handler.AbstractDataFieldHandler;
import com.twozo.crm.automation.base.page.xpath.XPathBuilder;
import com.twozo.web.driver.service.WebAutomationDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CompanyDataFieldHandler extends AbstractDataFieldHandler {

    CompanyDataFieldPage companyDataFieldPage = null;

    private CompanyDataFieldHandler(WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static CompanyDataFieldHandler getInstance(final WebAutomationDriver webAutomationDriver) {
        return new CompanyDataFieldHandler(webAutomationDriver);
    }

    /**
     * <p>
     * Retrieves the default fields for the company.
     * </p>
     *
     * @return The {@link Collection} of default fields.
     */
    @Override
    protected Collection<Field> getDefaultFields() {
        return CompanyField.getDefaultFields();
    }

    /**
     * <p>
     * Retrieves all available fields for the company.
     * </p>
     *
     * @return Array of all fields.
     */
    @Override
    public Field[] getAllFields() {
        return CompanyField.values();
    }

    /**
     * <p>
     * Retrieves the mandatory fields for the company.
     * </p>
     *
     * @return The {@link Collection} of mandatory fields.
     */
    @Override
    public Collection<String> getMandatoryFields() {
        return Arrays.asList(
                CompanyField.NAME.getName(),
                CompanyField.SALES_OWNER.getName()
        );
    }

    /**
     * <p>
     * Verifies that the non-draggable fields are correctly displayed.
     * </p>
     *
     * @return true if non-draggable fields are displayed correctly.
     */
    @Override
    public boolean verifyNonDraggableFields() {
        return isNonDraggableIconDisplayed(companyDataFieldPage.getNameDiv());
    }

    /**
     * <p>
     * Retrieves the default system field elements for the company.
     * </p>
     *
     * @return The {@link Collection} of default system field elements.
     */
    @Override
    public Collection<Record> getDefaultSystemFieldElements() {
        return List.of(companyDataFieldPage.getNameField(), companyDataFieldPage.getWebsiteField(),
                companyDataFieldPage.getSalesOwnerField(), companyDataFieldPage.getAddressField());
    }

    /**
     * <p>
     * Verifies if the default fields are visible in the summary view.
     * </p>
     *
     * @return true if default fields are visible in the summary.
     */
    @Override
    public boolean isDefaultFieldsVisibleInSummary() {
        return isDisplayed(findByXpath(formatTwoStrings("//*[@class='css-itno5t']",
                XPathBuilder.getXPathByText(CompanyField.SALES_OWNER.getName()))));
    }

    /**
     * <p>
     * Unchecks the mandatory fields, making them non-mandatory.
     * </p>
     *
     * @return true after unchecking mandatory fields.
     */
    @Override
    public boolean uncheckMandatoryFields() {
        final String[] mandatoryFields = new String[]{
                getNameDiv(),
                getSalesOwnerDiv(),
        };
        uncheck(mandatoryFields);

        return true;
    }

    /**
     * <p>
     * Retrieves the fields visible in the summary view, excluding certain profile fields for company.
     * </p>
     *
     * @return The {@link Collection} of fields visible in the summary.
     */
    public Collection<String> getFieldsForSummary() {
        final List<String> fieldsNotToDisplay = List.of("Name", "Website");

        return getFieldsForSummary(fieldsNotToDisplay);
    }
}
