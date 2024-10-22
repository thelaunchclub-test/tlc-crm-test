package com.twozo.test.sign;

import com.twozo.commons.json.JsonArray;
import com.twozo.commons.json.JsonObject;
import com.twozo.page.settings.data.fields.FieldStatus;
import com.twozo.page.sign.Account;
import com.twozo.page.sign.SignUp;
import com.twozo.test.BaseTest;
import com.twozo.test.TestCase;
import com.twozo.test.TestDataProvider;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class SignUpTest extends BaseTest {

    WebAutomationDriver automationDriver;

    private static final Logger LOG = LoggerFactory.getLogger(SignUpTest.class);

    @DataProvider(name = "signUpData")
    public static Object[][] getSearchData() {
        return new TestDataProvider().getTestCases("SignUpData.json");
    }

    protected FieldStatus getFieldStatus(final Object object) {
        final TestCase testCase = (TestCase) object;
        final JsonObject input = testCase.input;
        final FieldStatus fieldStatus = new FieldStatus();

        if (input.containsKey("fieldName")) {
            fieldStatus.setFieldName(input.getString("fieldName"));
        }
        if (input.containsKey("fieldType")) {
            fieldStatus.setFieldType(input.getString("fieldType"));
        }

        if (input.containsKey("append")) {
            fieldStatus.setAppend(input.getString("append"));
        }

        if (input.containsKey("choices")) {
            final JsonArray choices = input.getJsonArray("choices");
            final List<String> choice = new ArrayList<>();

            for (int i = 0; i < choices.size(); i++) {
                choice.add(choices.getString(i));
            }
            fieldStatus.setChoices(choice);
        }

        return fieldStatus;
    }

    @Test(dataProvider = "signUpData")
    public void signUpWithValidDetails(final Object object) {
        final TestCase testCase = (TestCase) object;
        final JsonObject input = testCase.input;
        final Account account = new Account();

        account.setEmail(input.getString("email"));
        account.setName(input.getString("name"));
        account.setPassword(input.getString("password"));
        account.setConfirmPassword(input.getString("confirmPassword"));
        account.setCompany(input.getString("company"));
        account.setJobRole(input.getString("jobRole"));
        LOG.info("Signed up successfully");
        automationDriver = WebAutomationDriver.get();
        automationDriver.getWebNavigator().to(link);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        SignUp.getInstance(automationDriver).signUp(account);
    }

//    @Test
//    public void print() {
//        automationDriver = WebAutomationDriver.get();
//
//        automationDriver.getWebNavigator().to(link);
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//        }
//
//        automationDriver.getElementFinder().getWebPageElement(new Element(LocatorType.XPATH, "//*[@name='email']", true)).interact().sendKeys("xd@gmail.com");
//        automationDriver.getElementFinder().getWebPageElement(new Element(LocatorType.XPATH, "//*[@name='password']", true)).interact().sendKeys("A$12345a");
//        automationDriver.getElementFinder().getWebPageElement(new Element(LocatorType.XPATH, "//*[text()='Sign In']", true)).interact().click();
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//        }
//
//        automationDriver.getElementFinder().getWebPageElement(new Element(LocatorType.XPATH, "(//li[@class='MuiListItem-root MuiListItem-gutters css-12dzmul'])[4]", true)).interact().click();
//
//        try {
//            Thread.sleep(6000);
//        } catch (InterruptedException e) {
//        }
//
//        //  automationDriver.getWebNavigator().refresh();
//
////        automationDriver.getElementFinder().getWebPageElement(new Element(LocatorType.XPATH,"//*[@class='css-vb6e92']",true)).interact().click();
////        automationDriver.getElementFinder().getWebPageElement(new Element(LocatorType.XPATH,"//*[text()='Overdue Activities']",true)).interact().click();
////
////        try {
////            Thread.sleep(4000);
////        } catch (InterruptedException e) {
////        }
//        Collection<WebPageElement> webPageElements = automationDriver.getElementFinder().getWebPageElements(new Element(LocatorType.XPATH, "//*[@class='MuiTableRow-root css-rm8p5t']", true));
//
//        System.out.println(webPageElements.size());
//
//    }


    @AfterMethod
    public void tearDown() {
        automationDriver.close();
    }
}
