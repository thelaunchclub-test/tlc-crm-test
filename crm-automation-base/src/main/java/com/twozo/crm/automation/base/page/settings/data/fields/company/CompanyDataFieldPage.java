package com.twozo.crm.automation.base.page.settings.data.fields.company;

import com.twozo.crm.automation.base.page.settings.data.fields.DataFieldsPage;
import com.twozo.crm.automation.base.page.settings.data.fields.company.field.CompanyField;
import com.twozo.crm.automation.base.page.settings.data.fields.field.Field;
import com.twozo.crm.automation.base.page.settings.data.fields.field.FieldAttribute;
import com.twozo.crm.automation.base.page.settings.data.fields.field.FieldTypePath;
import com.twozo.crm.automation.base.page.settings.data.fields.field.SystemField;
import com.twozo.crm.automation.base.page.xpath.XPathBuilder;

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
public class CompanyDataFieldPage extends DataFieldsPage {

    protected CompanyDataFieldPage(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static CompanyDataFieldPage getInstance(final WebAutomationDriver webAutomationDriver) {
        return new CompanyDataFieldPage(webAutomationDriver);
    }

    /**
     * <p>
     * Retrieves the block associated with the company's name field.
     * </p>
     *
     * @return String representation of the name field block.
     */
    public String getNameDiv() {
        return getFieldBlock(CompanyField.NAME.getFieldType());
    }

    /**
     * <p>
     * Retrieves the block associated with the company's website field.
     * </p>
     *
     * @return String representation of the website field block.
     */
    public String getWebsiteDiv() {
        return getFieldBlock(CompanyField.WEBSITE.getName());
    }

    /**
     * <p>
     * Retrieves the block associated with the company's sales owner field.
     * </p>
     *
     * @return String representation of the sales owner field block.
     */
    public String getSalesOwnerDiv() {
        return getFieldBlock(CompanyField.SALES_OWNER.getName());
    }

    /**
     * <p>
     * Retrieves the block associated with the company's address field.
     * </p>
     *
     * @return String representation of the address field block.
     */
    public String getAddressDiv() {
        return getFieldBlock(CompanyField.ADDRESS.getName());
    }

    /**
     * <p>
     * Constructs and returns a {@link SystemField}  for the company's name field.
     * </p>
     *
     * @return SystemField The {@link SystemField} for the name field.
     */
    SystemField getNameField() {
        final String name = CompanyField.NAME.getName();

        return new SystemField(getNonDraggableElement(name),
                getFieldName(name),
                getFieldType(name, CompanyField.NAME.getFieldType()),
                isSelected(getAddViewCheckboxOf(name)),
                isSelected(getRequiredCheckboxOf(name)),
                null);
    }

    /**
     * <p>
     * Constructs and returns a {@link SystemField}  for the company's website field.
     * </p>
     *
     * @return SystemField The {@link SystemField} for the website field.
     */
    SystemField getWebsiteField() {
        final String website = CompanyField.WEBSITE.getName();

        return new SystemField(getDraggableElement(website),
                getFieldName(website),
                getFieldType(website, CompanyField.NAME.getFieldType()),
                isSelected(getAddViewCheckboxOf(website)),
                !isSelected(getRequiredCheckboxOf(website)),
                null);
    }

    /**
     * <p>
     * Constructs and returns a {@link SystemField}  for the company's sales owner field.
     * </p>
     *
     * @return SystemField The {@link SystemField} for the sales owner field.
     */
    SystemField getSalesOwnerField() {
        final String salesOwner = CompanyField.SALES_OWNER.getName();

        return new SystemField(
                getDraggableElement(salesOwner),
                getFieldName(salesOwner),
                getFieldType(salesOwner,CompanyField.SALES_OWNER.getFieldType()),
                isSelected(getAddViewCheckboxOf(salesOwner)),
                isSelected(getRequiredCheckboxOf(salesOwner)),
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
    SystemField getAddressField() {
        final String address = CompanyField.ADDRESS.getName();

        return new SystemField(
                getDraggableElement(CompanyField.ADDRESS),
                getFieldName(CompanyField.ADDRESS),
                getFieldType(CompanyField.ADDRESS),
                isSelected(getAddViewCheckboxOf(address)),
                !isSelected(getRequiredCheckboxOf(address)),
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

        return areAllChoicesPresent(options);
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

        return areAllChoicesPresent(industries);
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

        return areAllChoicesPresent(businessTypes);
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
            addSystemField(organizationStatus);
        }
        refresh();

        if (!isFieldElementDisplayed(organizationStatus, FieldAttribute.DRAGGABLE)) {
            return false;
        }

        if (!isFieldElementDisplayed(organizationStatus, FieldTypePath.DROPDOWN)) {
            return false;
        }
        click(findByXpath(formatTwoStrings(getFieldBlock(organizationStatus), XPathBuilder.getXPathByText("5"))));

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
            addSystemField(industryType);
        }
        refresh();

        if (!isFieldElementDisplayed(industryType, FieldAttribute.DRAGGABLE)) {
            return false;
        }

        if (!isFieldElementDisplayed(industryType, FieldTypePath.DROPDOWN)) {
            return false;
        }
        click(findByXpath(formatTwoStrings(getFieldBlock(industryType), XPathBuilder.getXPathByText("68"))));

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
            addSystemField(businessType);
        }
        refresh();

        if (!isFieldElementDisplayed(businessType, FieldAttribute.DRAGGABLE)) {
            return false;
        }

        if (!isFieldElementDisplayed(businessType, FieldTypePath.DROPDOWN)) {
            return false;
        }
        click(findByXpath(formatTwoStrings(getFieldBlock(businessType), XPathBuilder.getXPathByText("13"))));

        return checkChoicesForBusinessType();
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
