package com.twozo.test.company;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.commons.json.JsonArray;
import com.twozo.commons.json.JsonObject;
import com.twozo.page.company.CompanyAddForm;
import com.twozo.page.company.CompanyForm;
import com.twozo.page.contact.ContactAddForm;
import com.twozo.page.contact.ContactForm;
import com.twozo.page.homepage.HomePage;
import com.twozo.page.url.URL;
import com.twozo.test.TestCase;
import com.twozo.test.TestDataProvider;
import com.twozo.test.add.form.AddFormTest;
import com.twozo.web.driver.service.WebAutomationDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        webNavigator.to(URL.COMPANIES);
        automationDriver.getWebWindowHandler().maximize();
        homePage = HomePage.getInstance(automationDriver);
        companyAddForm = CompanyAddForm.getInstance(automationDriver);
    }

//    @AfterMethod
//    public void after() {
//        automationDriver.close();
//    }

    @DataProvider(name = "companyAddFormFields")
    private static Object[][] getContactAddFormData() {
        return new TestDataProvider().getTestCases(getFilePath(COMPANY_ADD_FORM, "AllFieldTypeValue.json"));
    }

    private CompanyForm getCompanyForm(final Object object) {
        final TestCase testCase = (TestCase) object;
        final JsonObject input = testCase.input;
        final CompanyForm companyForm = new CompanyForm();

        if (input.containsKey("Name")) {
            companyForm.setName(input.getString("Name"));
        }

        if (input.containsKey("Website")) {
            companyForm.setWebsite(input.getString("Website"));
        }

        if (input.containsKey("Sales Owner")) {
            companyForm.setSalesOwner(input.getString("Sales Owner"));
        }

        if (input.containsKey("Address")) {
            final JsonObject addressJson = input.getJsonObject("Address");
            final CompanyForm.Address address = new CompanyForm.Address();

            if (addressJson.containsKey("Address Line 1")) {
                address.setAddressLine1(addressJson.getString("Address Line 1"));
            }
            if (addressJson.containsKey("Address Line 2")) {
                address.setAddressLine2(addressJson.getString("Address Line 2"));
            }
            if (addressJson.containsKey("City")) {
                address.setCity(addressJson.getString("City"));
            }
            if (addressJson.containsKey("State")) {
                address.setState(addressJson.getString("State"));
            }
            if (addressJson.containsKey("Country")) {
                address.setCountry(addressJson.getString("Country"));
            }
            if (addressJson.containsKey("Pincode")) {
                address.setPincode(addressJson.getString("Pincode"));
            }

            companyForm.setAddress(address);
        }

        if (input.containsKey("Facebook")) {
            companyForm.setFacebook(input.getString("Facebook"));
        }

        if (input.containsKey("Twitter")) {
            companyForm.setTwitter(input.getString("Twitter"));
        }

        if (input.containsKey("LinkedIn")) {
            companyForm.setLinkedin(input.getString("LinkedIn"));
        }

        if (input.containsKey("Annual Revenue")) {
            final JsonObject annualRevenueJson = input.getJsonObject("Annual Revenue");
            final CompanyForm.AnnualRevenue annualRevenue = new CompanyForm.AnnualRevenue();

            if (annualRevenueJson.containsKey("Value")) {
                annualRevenue.setValue(annualRevenueJson.getString("Value"));
            }
            if (annualRevenueJson.containsKey("Currency")) {
                annualRevenue.setCurrency(annualRevenueJson.getString("Currency"));
            }

            companyForm.setAnnualRevenue(annualRevenue);
        }


        if (input.containsKey("SIC Code")) {
            companyForm.setSICCode(input.getString("SIC Code"));
        }

        if (input.containsKey("Territory")) {
            companyForm.setTerritory(input.getString("Territory"));
        }

        if (input.containsKey("Tags")) {
            final JsonArray tagsArray = input.getJsonArray("Tags");
            final List<String> tags = new ArrayList<>();

            for (int i = 0; i < tagsArray.size(); i++) {
                tags.add(tagsArray.getString(i));
            }
            companyForm.setTags(tags);
        }

        if (input.containsKey("Employees")) {
            companyForm.setEmployees(input.getString("Employees"));
        }

        if (input.containsKey("Parent Company")) {
            companyForm.setParentCompany(input.getString("Parent Company"));
        }

        if (input.containsKey("Organization Status")) {
            companyForm.setOrganizationStatus(input.getString("Organization Status"));
        }

        if (input.containsKey("Industry Type")) {
            companyForm.setIndustryType(input.getString("Industry Type"));
        }

        if (input.containsKey("Business Type")) {
            companyForm.setBusinessType(input.getString("Business Type"));
        }

        if (input.containsKey("Description")) {
            companyForm.setDescription(input.getString("Description"));
        }

        return companyForm;
    }

    @Test(dataProvider = "companyAddFormFields")
    public void fill(final Object object) {
        companyAddForm.fill(getCompanyForm(object));
    }
}
