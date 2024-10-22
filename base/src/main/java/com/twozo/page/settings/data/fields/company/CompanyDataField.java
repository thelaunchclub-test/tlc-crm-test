package com.twozo.page.settings.data.fields.company;

import com.twozo.commons.exception.ErrorCode;

import com.twozo.page.settings.data.fields.AbstractDataField;
import com.twozo.page.settings.data.fields.company.field.CompanyField;
import com.twozo.page.settings.data.fields.field.Field;
import com.twozo.page.settings.data.fields.field.FieldElement;
import com.twozo.page.settings.data.fields.field.FieldTypePath;
import com.twozo.page.settings.data.fields.field.SystemField;
import com.twozo.page.url.settings.SettingsURL;
import com.twozo.page.xpath.XPathBuilder;

import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;
import com.twozo.web.error.code.WebDriverErrorCode;

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
        final String nameDiv = getNameDiv();

        return new SystemField(findByXpath(format(nameDiv, FieldElement.NON_DRAGGABLE)),
                findByXpath(format(nameDiv, XPathBuilder.getXPathByText(CompanyField.NAME.getName()))),
                findByXpath(format(nameDiv, XPathBuilder.getXPathByText(CompanyField.NAME.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(nameDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(nameDiv, FieldElement.REQUIRED_CHECKBOX))),
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
        final String websiteDiv = getWebsiteDiv();

        return new SystemField(findByXpath(format(websiteDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(websiteDiv, XPathBuilder.getXPathByText(CompanyField.WEBSITE.getName()))),
                findByXpath(format(websiteDiv, XPathBuilder.getXPathByText(CompanyField.WEBSITE.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(websiteDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                !isSelected(findByXpath(getPathOfSpecificCheckbox(websiteDiv, FieldElement.REQUIRED_CHECKBOX))),
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
        final String salesOwnerDiv = getSalesOwnerDiv();

        return new SystemField(findByXpath(format(salesOwnerDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(salesOwnerDiv, XPathBuilder.getXPathByText(CompanyField.SALES_OWNER.getName()))),
                findByXpath(format(salesOwnerDiv, XPathBuilder.getXPathByText(CompanyField.SALES_OWNER.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(salesOwnerDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(salesOwnerDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    /**
     * <p>
     * Constructs and returns a {@link SystemField}  for the company's address field.
     * </p>
     *
     * @return SystemField The {@link SystemField} for the address field.
     */
    private SystemField getAddressField() {
        final String addressDiv = getAddressDiv();

        return new SystemField(findByXpath(format(addressDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(addressDiv, XPathBuilder.getXPathByText(CompanyField.ADDRESS.getName()))),
                findByXpath(format(addressDiv, XPathBuilder.getXPathByText(CompanyField.ADDRESS.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(addressDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                !isSelected(findByXpath(getPathOfSpecificCheckbox(addressDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
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

        if (!checkSpecificElement(organizationStatus, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!checkSpecificElement(organizationStatus, FieldTypePath.DROPDOWN)) {
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
        final String industryType = CompanyField.INDUSTRIAL_TYPE.getName();

        if (!isFieldPresent(industryType)) {
            addField(industryType);
        }
        refresh();

        if (!checkSpecificElement(industryType, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!checkSpecificElement(industryType, FieldTypePath.DROPDOWN)) {
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

        if (!checkSpecificElement(businessType, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!checkSpecificElement(businessType, FieldTypePath.DROPDOWN)) {
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
                getNameDiv(),
                getSalesOwnerDiv()
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
        final String[] mandatoryFields = new String[]{
                getNameDiv(),
                getSalesOwnerDiv(),
        };
        unCheck(mandatoryFields);

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
