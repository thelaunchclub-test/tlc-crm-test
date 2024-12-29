package com.twozo.page.settings.data.fields.company;

import com.twozo.page.settings.data.fields.AbstractDataField;
import com.twozo.page.settings.data.fields.company.field.CompanyField;
import com.twozo.page.settings.data.fields.contact.field.ContactField;
import com.twozo.page.settings.data.fields.field.Field;
import com.twozo.page.settings.data.fields.field.FieldElement;
import com.twozo.page.settings.data.fields.field.FieldTypePath;
import com.twozo.page.settings.data.fields.field.SystemField;
import com.twozo.page.xpath.XPathBuilder;

import com.twozo.web.driver.service.WebAutomationDriver;

import java.util.*;

/**
 * <p>
 * Manages company-related fields and their behaviors in the data field settings. Provides methods to interact with
 * various company fields. It also supports adding, editing, and validating the company fields.
 * </p>
 *
 * @author Petchimuthu
 * @version 1.0
 */
public class CompanyDataField extends AbstractDataField {

    private static CompanyDataField company;

    protected CompanyDataField(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static CompanyDataField getInstance(final WebAutomationDriver webAutomationDriver) {
        company = new CompanyDataField(webAutomationDriver);

        return company;
    }

    /**
     * <p>
     * Retrieves the block associated with the company's name field.
     * </p>
     *
     * @return String representation of the name field block.
     */
    public String getNameDiv() {
        return getFieldBlock(CompanyField.NAME);
    }

    /**
     * <p>
     * Retrieves the block associated with the company's website field.
     * </p>
     *
     * @return String representation of the website field block.
     */
    public String getWebsiteDiv() {
        return getFieldBlock(CompanyField.WEBSITE);
    }

    /**
     * <p>
     * Retrieves the block associated with the company's sales owner field.
     * </p>
     *
     * @return String representation of the sales owner field block.
     */
    public String getSalesOwnerDiv() {
        return getFieldBlock(CompanyField.SALES_OWNER);
    }

    /**
     * <p>
     * Retrieves the block associated with the company's address field.
     * </p>
     *
     * @return String representation of the address field block.
     */
    public String getAddressDiv() {
        return getFieldBlock(CompanyField.ADDRESS);
    }

    /**
     * <p>
     * Constructs and returns a {@link SystemField}  for the company's name field.
     * </p>
     *
     * @return SystemField The {@link SystemField} for the name field.
     */
    private SystemField getNameField() {
        return new SystemField(getNonDraggableElement(CompanyField.NAME),
                getFieldName(CompanyField.NAME),
                getFieldType(CompanyField.NAME),
                isSelected(getAddViewCheckboxOf(CompanyField.NAME)),
                isSelected(getRequiredCheckboxOf(CompanyField.NAME)),
                null);
    }

    /**
     * <p>
     * Constructs and returns a {@link SystemField}  for the company's website field.
     * </p>
     *
     * @return SystemField The {@link SystemField} for the website field.
     */
        private SystemField getWebsiteField() {
            return new SystemField(getDraggableElement(CompanyField.WEBSITE),
                    getFieldName(CompanyField.WEBSITE),
                    getFieldType(CompanyField.WEBSITE),
                    isSelected(getAddViewCheckboxOf(CompanyField.WEBSITE)),
                    !isSelected(getRequiredCheckboxOf(CompanyField.WEBSITE)),
                    null);
        }

    /**
     * <p>
     * Constructs and returns a {@link SystemField}  for the company's sales owner field.
     * </p>
     *
     * @return SystemField The {@link SystemField} for the sales owner field.
     */
    private SystemField getSalesOwnerField() {
        return new SystemField(
                getDraggableElement(CompanyField.SALES_OWNER),
                getFieldName(CompanyField.SALES_OWNER),
                getFieldType(CompanyField.SALES_OWNER),
                isSelected(getAddViewCheckboxOf(CompanyField.SALES_OWNER)),
                isSelected(getRequiredCheckboxOf(CompanyField.SALES_OWNER)),
                null
        );
    }

    /**
     * <p>
     * Constructs and returns a {@link SystemField}  for the company's address field.
     * </p>
     *
     * @return SystemField The {@link SystemField} for the address field.
     */
    private SystemField getAddressField() {
        return new SystemField(
                getDraggableElement(CompanyField.ADDRESS),
                getFieldName(CompanyField.ADDRESS),
                getFieldType(CompanyField.ADDRESS),
                isSelected(getAddViewCheckboxOf(CompanyField.ADDRESS)),
                !isSelected(getRequiredCheckboxOf(CompanyField.ADDRESS)),
                null
        );

    }

    /**
     * <p>
     * Checks if the available choices for organization status match the expected options.
     * </p>
     *
     * @return true if all expected options are present, false otherwise.
     */
    public boolean checkChoicesForOrganizationStatus() {
        final String[] options = {"Acquired", "Active", "Market Failed", "Project Cancelled", "Shutdown"};

        return areChoicesPresent(options);
    }

    /**
     * <p>
     * Checks if the available choices for industry type match the expected industries.
     * </p>
     *
     * @return true if all expected industries are present, false otherwise.
     */
    public boolean checkChoicesForIndustryType() {
        final String[] industries = {
                "Accounting",
                "Advertising",
                "Aerospace",
                "Agriculture",
                "Aircraft",
                "Airline",
                "Apparel & Accessories",
                "Automotive",
                "Banking",
                "Biotechnology",
                "Broadcasting",
                "Brokerage",
                "Call Centers",
                "Cargo Handling",
                "Chemicals",
                "Computer",
                "Construction",
                "Consulting",
                "Consumer Products",
                "Cosmetics",
                "Defense",
                "Department Stores",
                "Ecommerce",
                "Education",
                "Electronics",
                "Energy",
                "Engineering",
                "Entertainment & Leisure",
                "Executive Search",
                "Financial Services",
                "Food, Beverage & Tobacco",
                "Gaming",
                "Government",
                "Grocery",
                "Health Care",
                "Hospitality",
                "Insurance",
                "Internet Publishing",
                "Investment Banking",
                "Legal",
                "Machinery",
                "Manufacturing",
                "Media",
                "Motion Picture & Video",
                "Music",
                "Newspaper Publishers",
                "Not for Profit",
                "Online Auctions",
                "Other",
                "Pension Funds",
                "Pharmaceuticals",
                "Private Equity",
                "Publishing",
                "Real Estate",
                "Retail & Wholesale",
                "Securities & Commodity Exchanges",
                "Service",
                "Shipping",
                "Soap & Detergent",
                "Software",
                "Sports",
                "Technology",
                "Telecommunications",
                "Television",
                "Transportation",
                "Trucking",
                "Utilities",
                "Venture Capital"
        };

        return areChoicesPresent(industries);
    }

    /**
     * <p>
     * Checks if the available choices for business type match the expected business types.
     * </p>
     *
     * @return true if all expected business types are present, false otherwise.
     */
    public boolean checkChoicesForBusinessType() {
        final String[] businessTypes = {
                "Analyst",
                "Competitor",
                "Customer",
                "Distributor",
                "Integrator",
                "Investor",
                "Other",
                "Partner",
                "Press",
                "Prospect",
                "Reseller",
                "Supplier",
                "Vendor"
        };

        return areChoicesPresent(businessTypes);
    }

    /**
     * <p>
     * Verifies if the active company tab is displayed on the page.
     * </p>
     *
     * @return true if the active company tab is visible, false otherwise.
     */
    public boolean verifyActiveCompanyTab() {
        return isDisplayed(getActiveCompanyTab());
    }

    /**
     * <p>
     * Checks if the "Organization Status" field is correctly added and displays the expected choices.
     * </p>
     *
     * @return true if the field and its choices are verified successfully.
     */
    public boolean checkOrganizationStatus() {
        final String organizationStatus = CompanyField.ORGANIZATION_STATUS.getName();

        if (!isFieldPresent(organizationStatus)) {
            addField(organizationStatus);
        }
        refresh();

        if (!isFieldSpecificElementDisplayed(organizationStatus, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!isFieldSpecificElementDisplayed(organizationStatus, FieldTypePath.DROPDOWN)) {
            return false;
        }
        click(findByXpath(format(getFieldBlock(organizationStatus), XPathBuilder.getXPathByText("5"))));

        return checkChoicesForOrganizationStatus();
    }

    /**
     * <p>
     * Checks if the "Industry Type" field is correctly added and displays the expected choices.
     * </p>
     *
     * @return true if the field and its choices are verified successfully.
     */
    public boolean checkIndustryType() {
        final String industryType = CompanyField.INDUSTRY_TYPE.getName();

        if (!isFieldPresent(industryType)) {
            addField(industryType);
        }
        refresh();

        if (!isFieldSpecificElementDisplayed(industryType, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!isFieldSpecificElementDisplayed(industryType, FieldTypePath.DROPDOWN)) {
            return false;
        }
        click(findByXpath(format(getFieldBlock(industryType), XPathBuilder.getXPathByText("68"))));

        return checkChoicesForIndustryType();
    }

    /**
     * <p>
     * Checks if the "Business Type" field is correctly added and displays the expected choices.
     * </p>
     *
     * @return true if the field and its choices are verified successfully.
     */
    public boolean checkBusinessType() {
        final String businessType = CompanyField.BUSINESS_TYPE.getName();

        if (!isFieldPresent(businessType)) {
            addField(businessType);
        }
        refresh();

        if (!isFieldSpecificElementDisplayed(businessType, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!isFieldSpecificElementDisplayed(businessType, FieldTypePath.DROPDOWN)) {
            return false;
        }
        click(findByXpath(format(getFieldBlock(businessType), XPathBuilder.getXPathByText("13"))));

        return checkChoicesForBusinessType();
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
    protected Collection<String> getMandatoryFields() {
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
        return isNonDraggableIconDisplayed(getNameDiv());
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
        return List.of(getNameField(), getWebsiteField(), getSalesOwnerField(), getAddressField());
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
        return isDisplayed(findByXpath(format("//*[@class='css-itno5t']",
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
        final Field[] mandatoryFields = new CompanyField[]{
                CompanyField.NAME,
                CompanyField.SALES_OWNER
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
