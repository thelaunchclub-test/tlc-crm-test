package com.twozo.page.settings.data.fields.company;

import com.twozo.commons.exception.ErrorCode;
import com.twozo.page.settings.data.fields.AbstractDataField;
import com.twozo.page.settings.data.fields.company.field.CompanyField;
import com.twozo.page.settings.data.fields.contact.field.ContactField;
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

public class CompanyDataField extends AbstractDataField {

    private static CompanyDataField company;

    protected CompanyDataField(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);

        if (getURL().equals(SettingsURL.CONTACT_DATA_FIELDS)) {
            throw ErrorCode.get(WebDriverErrorCode.EXPECTED_PAGE_NOT_FOUND, "exp page not found");
        }
    }

    public static CompanyDataField getInstance(final WebAutomationDriver webAutomationDriver) {
        company = new CompanyDataField(webAutomationDriver);

        return company;
    }

    public String getNameDiv() {
        return getFieldBlock(CompanyField.NAME);
    }

    public String getWebsiteDiv() {
        return getFieldBlock(CompanyField.WEBSITE);
    }

    public String getSalesOwnerDiv() {
        return getFieldBlock(CompanyField.SALES_OWNER);
    }

    public String getAddressDiv() {
        return getFieldBlock(CompanyField.ADDRESS);
    }

    private SystemField getNameField() {
        final String nameDiv = getNameDiv();

        return new SystemField(findByXpath(format(nameDiv, FieldElement.NON_DRAGGABLE)),
                findByXpath(format(nameDiv, XPathBuilder.getXPathByText(CompanyField.NAME.getName()))),
                findByXpath(format(nameDiv, XPathBuilder.getXPathByText(CompanyField.NAME.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(nameDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(nameDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getWebsiteField() {
        final String websiteDiv = getWebsiteDiv();

        return new SystemField(findByXpath(format(websiteDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(websiteDiv, XPathBuilder.getXPathByText(CompanyField.WEBSITE.getName()))),
                findByXpath(format(websiteDiv, XPathBuilder.getXPathByText(CompanyField.WEBSITE.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(websiteDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                !isSelected(findByXpath(getPathOfSpecificCheckbox(websiteDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getSalesOwnerField() {
        final String salesOwnerDiv = getSalesOwnerDiv();

        return new SystemField(findByXpath(format(salesOwnerDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(salesOwnerDiv, XPathBuilder.getXPathByText(CompanyField.SALES_OWNER.getName()))),
                findByXpath(format(salesOwnerDiv, XPathBuilder.getXPathByText(CompanyField.SALES_OWNER.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(salesOwnerDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(salesOwnerDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getAddressField() {
        final String addressDiv = getAddressDiv();

        return new SystemField(findByXpath(format(addressDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(addressDiv, XPathBuilder.getXPathByText(CompanyField.ADDRESS.getName()))),
                findByXpath(format(addressDiv, XPathBuilder.getXPathByText(CompanyField.ADDRESS.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(addressDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                !isSelected(findByXpath(getPathOfSpecificCheckbox(addressDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    public boolean checkChoicesForOrganizationStatus() {
        final String[] options = {"Acquire", "Active", "Market Failed", "Project Cancelled", "Shutdown"};

        final List<String> choices = new ArrayList<>();
        final Collection<WebPageElement> choicesAsElements = findElementsByXpath("//*[@class='css-vb6e92']");

        for (final WebPageElement choicesAsElement : choicesAsElements) {
            choices.add(getText(choicesAsElement));
        }

        for (final String option : options) {

            if (!choices.contains(option)) {
                return false;
            }
        }

        return true;
    }

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


        final Collection<WebPageElement> choices = findElementsByXpath("//*[@class='css-vb6e92']");
        final Set<String> industryChoices = new HashSet<>();

        for (final WebPageElement choice : choices) {
            industryChoices.add(getText(choice));
        }

        for (final String industry : industries) {

            if (!industryChoices.contains(industry)) {
                return false;
            }
        }

        return true;
    }

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

        final List<String> choices = new ArrayList<>();
        final Collection<WebPageElement> choicesAsElements = findElementsByXpath("//*[@class='css-vb6e92']");

        for (final WebPageElement choicesAsElement : choicesAsElements) {
            choices.add(getText(choicesAsElement));
        }

        for (final String businessType : businessTypes) {

            if (!choices.contains(businessType)) {
                return false;
            }
        }

        return true;
    }

    public boolean verifyActiveCompanyTab() {
        return isDisplayed(getActiveCompanyTab());
    }

    public boolean checkOrganizationStatus() {
        final String organizationStatus = "Organization Status";
        final String fiveChoices = XPathBuilder.getXPathByText("5");
        String organizationStatusBlock = null;


        if (!isFieldPresent(organizationStatus)) {
            addField(organizationStatus);
        }
        refresh();
        try {
            Thread.sleep(2000);
        } catch (Exception exception) {

        }

        checkSpecificElement(organizationStatus, FieldElement.DRAGGABLE);
        checkSpecificElement(organizationStatus, FieldTypePath.DROPDOWN);
        click(findByXpath(format(getFieldBlock(organizationStatus), fiveChoices)));
        checkChoicesForOrganizationStatus();

        return true;
    }

    public boolean checkIndustryType() {
        final String industryType = "Industry Type";
        final String sixtyEightChoices = XPathBuilder.getXPathByText("68");
        String industryTypeBlock = null;

        if (!isFieldPresent(industryType)) {
            addField(industryType);
        }
        refresh();
        try {
            Thread.sleep(2000);
        } catch (Exception exception) {

        }

        checkSpecificElement(industryType, FieldElement.DRAGGABLE);
        checkSpecificElement(industryType, FieldTypePath.DROPDOWN);
        click(findByXpath(format(getFieldBlock(industryType), sixtyEightChoices)));
        checkChoicesForIndustryType();
        return true;
    }

    public boolean checkBusinessType() {
        final String businessType = "Business Type";
        final String thirteenChoices = XPathBuilder.getXPathByText("13");
        String businessTypeBlock = null;

        if (!isFieldPresent(businessType)) {
            addField(businessType);
        }

        refresh();
        try {
            Thread.sleep(2000);
        } catch (Exception exception) {

        }

        checkSpecificElement(businessType, FieldElement.DRAGGABLE);
        checkSpecificElement(businessType, FieldTypePath.DROPDOWN);
        click(findByXpath(format(getFieldBlock(businessType), thirteenChoices)));
        checkChoicesForBusinessType();
        return true;
    }

    @Override
    protected List<Field> getDefaultFields() {
        return CompanyField.getDefaultFields();
    }

    @Override
    protected Field[] getAllFields() {
        return CompanyField.values();
    }

    @Override
    protected List<String> getMandatoryFields() {
        return Arrays.asList(
                getNameDiv(),
                getSalesOwnerDiv()
        );
    }

    @Override
    public boolean verifyNonDraggableFields() {
        return isNonDraggableIconDisplayed(getNameDiv());
    }

    @Override
    protected List<Record> getDefaultSystemFieldElements() {
        return List.of(getNameField(), getWebsiteField(), getSalesOwnerField(), getAddressField());
    }

    @Override
    public boolean isDefaultFieldsVisibleInSummary() {
        return isDisplayed(findByXpath(format("//*[@class='css-itno5t']",
                XPathBuilder.getXPathByText(ContactField.SALES_OWNER.getName()))));
    }

    @Override
    public boolean uncheckMandatoryFields() {
        final String[] mandatoryFields = new String[]{
                getNameDiv(),
                getSalesOwnerDiv(),
        };
        unCheck(mandatoryFields);

        return true;
    }

    @Override
    public List<String> getFieldsForAddViewAndRequired(final String addViewOrRequired) {
        final List<String> fieldsPresent = new ArrayList<>();

        int count = 0;
        final Collection<WebPageElement> elementsByXpath = findElementsByXpath("//*[@class='MuiBox-root css-19idom']");

        for (final WebPageElement webPageElement : elementsByXpath) {
            count++;
        }

        for (int i = 1; i <= count; i++) {

            final String fieldBlock = String.format(FieldElement.BLOCK, i);
            final WebPageElement fieldElement = findByXpath(getPathOfSpecificCheckbox
                    (fieldBlock, addViewOrRequired));

            if (isSelected(fieldElement)) {
                fieldsPresent.add(getText(findByXpath(String.format("(%s%s%s)", "((", fieldBlock, "/div)[1])//*[@class='MuiTypography-root MuiTypography-body1 MuiTypography-noWrap css-10vldmf']"))));

            }
        }

        return fieldsPresent;
    }

    @Override
    public List<String> getFields() {
        final List<String> fieldsPresent = new ArrayList<>();
        final Collection<WebPageElement> fields = findElementsByXpath("//*[@class='css-1qqzcwf']/div/p");

        for (final WebPageElement field : fields) {
            fieldsPresent.add(getText(field));
        }

        return fieldsPresent;
    }

    @Override
    public List<String> getFieldsForSummary() {
        final List<String> fieldsPresent = new ArrayList<>();
        final Collection<WebPageElement> fields = findElementsByXpath("//*[@class='css-1qqzcwf']/div/p");
        final List<String> profileFieldsForContact = List.of("Name", "Website");

        for (final WebPageElement field : fields) {
            final String fieldName = getText(field);

            if (!profileFieldsForContact.contains(fieldName)) {
                fieldsPresent.add(getText(field));
            }
        }

        return fieldsPresent;
    }
}
