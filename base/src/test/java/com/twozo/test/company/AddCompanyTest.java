package com.twozo.test.company;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.commons.json.JsonArray;
import com.twozo.commons.json.JsonObject;
import com.twozo.page.company.CompanyAddForm;
import com.twozo.page.company.CompanyForm;
import com.twozo.page.homepage.HomePage;
import com.twozo.page.settings.data.fields.company.CompanyDataField;
import com.twozo.page.settings.data.fields.company.field.CompanyField;
import com.twozo.page.settings.data.fields.contact.ContactDataField;
import com.twozo.page.url.URL;
import com.twozo.page.url.settings.SettingsURL;
import com.twozo.test.TestCase;
import com.twozo.test.TestDataProvider;
import com.twozo.test.add.form.AddFormTest;
import com.twozo.web.driver.service.WebAutomationDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddCompanyTest extends AddFormTest {
    private static final String COMPANY_ADD_FORM = Paths.get(COMPANY_PATH, "add", "form").toString();
    private CompanyAddForm companyAddForm;
    private HomePage homePage;
    private WebAutomationDriver automationDriver;

    @BeforeMethod
    public void before() {
        automationDriver = WebAutomationDriver.get();
        webNavigator = automationDriver.getWebNavigator();
        webNavigator.to(link);

        for (final BrowserCookie cookie : cookies) {
            automationDriver.getSessionCookie().addCookie(cookie);
        }

        automationDriver.getWebWindowHandler().maximize();
        companyAddForm = CompanyAddForm.getInstance(automationDriver);
        webNavigator.to(SettingsURL.COMPANY_DATA_FIELDS);
    }

//    @AfterMethod
//    public void after() {
//        automationDriver.close();
//    }

    @DataProvider(name = "companyAddFormFields")
    private static Object[][] getContactAddFormData() {
        return new TestDataProvider().getTestCases(getFilePath(COMPANY_ADD_FORM, "AllFieldTypeValue.json"));
    }

    protected CompanyForm getForm(final Object object) {
        final TestCase testCase = (TestCase) object;
        final JsonObject input = testCase.input;
        final CompanyForm companyForm = new CompanyForm();

        final String name = CompanyField.NAME.getName();
        final String website = CompanyField.WEBSITE.getName();
        final String salesOwner = CompanyField.SALES_OWNER.getName();
        final String address = CompanyField.ADDRESS.getName();
        final String facebook = CompanyField.FACEBOOK.getName();
        final String twitter = CompanyField.TWITTER.getName();
        final String linkedIn = CompanyField.LINKED_IN.getName();
        final String annualRevenue = CompanyField.ANNUAL_REVENUE.getName();
        final String sicCode = CompanyField.SIC_CODE.getName();
        final String territory = CompanyField.TERRITORY.getName();
        final String tags = CompanyField.TAGS.getName();
        final String employees = CompanyField.EMPLOYEES.getName();
        final String parentCompany = CompanyField.PARENT_COMPANY.getName();
        final String organizationStatus = CompanyField.ORGANIZATION_STATUS.getName();
        final String industryType = CompanyField.INDUSTRY_TYPE.getName();
        final String businessType = CompanyField.BUSINESS_TYPE.getName();
        final String description = CompanyField.DESCRIPTION.getName();
        final String recentNote = CompanyField.RECENT_NOTE.getName();
        final String openDeals = CompanyField.OPEN_DEALS.getName();
        final String closedDeals = CompanyField.CLOSED_DEALS.getName();
        final String wonDeals = CompanyField.WON_DEALS.getName();
        final String lostDeals = CompanyField.LOST_DEALS.getName();
        final String lastActivityDate = CompanyField.LAST_ACTIVITY_DATE.getName();
        final String nextActivityDate = CompanyField.NEXT_ACTIVITY_DATE.getName();
        final String lastActivityType = CompanyField.LAST_ACTIVITY_TYPE.getName();
        final String doneActivities = CompanyField.DONE_ACTIVITIES.getName();
        final String upcomingActivities = CompanyField.UPCOMING_ACTIVITIES.getName();
        final String totalActivities = CompanyField.TOTAL_ACTIVITIES.getName();
        final String emailMessagesCount = CompanyField.EMAIL_MESSAGES_COUNT.getName();
        final String lastEmailReceived = CompanyField.LAST_EMAIL_RECEIVED.getName();
        final String lastEmailSent = CompanyField.LAST_EMAIL_SENT.getName();
        final String id = CompanyField.ID.getName();
        final String updatedBy = CompanyField.UPDATED_BY.getName();
        final String updatedAt = CompanyField.UPDATED_AT.getName();
        final String createdBy = CompanyField.CREATED_BY.getName();
        final String createdAt = CompanyField.CREATED_AT.getName();
        final String lastAssignedAt = CompanyField.LAST_ASSIGNED_AT.getName();

        if (input.containsKey(name)) {
            companyForm.setName(input.getString(name));
        }

        if (input.containsKey(website)) {
            companyForm.setWebsite(input.getString(website));
        }

        if (input.containsKey(salesOwner)) {
            companyForm.setSalesOwner(input.getString(salesOwner));
        }

        if (input.containsKey(address)) {
            final String addressLine1 = "Address Line 1";
            final String addressLine2 = "Address Line 2";
            final String city = "City";
            final String state = "State";
            final String country = "Country";
            final String pincode = "Pincode";
            final JsonObject addressJson = input.getJsonObject(address);
            final CompanyForm.Address companyAddress = new CompanyForm.Address();

            if (addressJson.containsKey(addressLine1)) {
                companyAddress.setAddressLine1(addressJson.getString(addressLine1));
            }
            if (addressJson.containsKey(addressLine2)) {
                companyAddress.setAddressLine2(addressJson.getString(addressLine2));
            }
            if (addressJson.containsKey(city)) {
                companyAddress.setCity(addressJson.getString(city));
            }
            if (addressJson.containsKey(state)) {
                companyAddress.setState(addressJson.getString(state));
            }
            if (addressJson.containsKey(country)) {
                companyAddress.setCountry(addressJson.getString(country));
            }
            if (addressJson.containsKey(pincode)) {
                companyAddress.setPincode(addressJson.getString(pincode));
            }

            companyForm.setAddress(companyAddress);
        }

        if (input.containsKey(facebook)) {
            companyForm.setFacebook(input.getString(facebook));
        }

        if (input.containsKey(twitter)) {
            companyForm.setTwitter(input.getString(twitter));
        }

        if (input.containsKey(linkedIn)) {
            companyForm.setLinkedin(input.getString(linkedIn));
        }

        if (input.containsKey(annualRevenue)) {
            final String value = "Value";
            final String currency = "Currency";
            final JsonObject annualRevenueJson = input.getJsonObject(annualRevenue);
            final CompanyForm.AnnualRevenue companyAnnualRevenue = new CompanyForm.AnnualRevenue();

            if (annualRevenueJson.containsKey(value)) {
                companyAnnualRevenue.setValue(annualRevenueJson.getString(value));
            }
            if (annualRevenueJson.containsKey(currency)) {
                companyAnnualRevenue.setCurrency(annualRevenueJson.getString(currency));
            }

            companyForm.setAnnualRevenue(companyAnnualRevenue);
        }


        if (input.containsKey(sicCode)) {
            companyForm.setSICCode(input.getString(sicCode));
        }

        if (input.containsKey(territory)) {
            companyForm.setTerritory(input.getString(territory));
        }

        if (input.containsKey(tags)) {
            final JsonArray tagsArray = input.getJsonArray(tags);
            final List<String> companyTags = new ArrayList<>();

            for (int i = 0; i < tagsArray.size(); i++) {
                companyTags.add(tagsArray.getString(i));
            }
            companyForm.setTags(companyTags);
        }

        if (input.containsKey(employees)) {
            companyForm.setEmployees(input.getString(employees));
        }

        if (input.containsKey(parentCompany)) {
            companyForm.setParentCompany(input.getString(parentCompany));
        }

        if (input.containsKey(organizationStatus)) {
            companyForm.setOrganizationStatus(input.getString(organizationStatus));
        }

        if (input.containsKey(industryType)) {
            companyForm.setIndustryType(input.getString(industryType));
        }

        if (input.containsKey(businessType)) {
            companyForm.setBusinessType(input.getString(businessType));
        }

        if (input.containsKey(description)) {
            companyForm.setDescription(input.getString(description));
        }

        return companyForm;
    }

    @Test(dataProvider = "companyAddFormFields")
    public void fill(final Object object) throws IllegalAccessException {
        CompanyDataField.getInstance(automationDriver).setFieldsForAddForm(getFieldsForUpdate(getForm(object)));
        webNavigator.to(URL.CONTACTS);
        companyAddForm.switchToAddForm("Company");
        companyAddForm.fill(getForm(object));
    }
}
