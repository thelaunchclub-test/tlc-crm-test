package com.twozo.test.settings.sales.activities;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.page.JsonFileReader;
import com.twozo.page.TestCase;
import com.twozo.page.homepage.HomePage;
import com.twozo.page.settings.sales.activities.JsonFields;

import com.twozo.page.settings.sales.activities.SalesActivities;
import com.twozo.page.url.settings.SettingsURL;
import com.twozo.test.BaseTest;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.driver.service.WebNavigator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;


public class SalesActivitiesTest extends BaseTest {
    private WebAutomationDriver webAutomationDriver;
    WebNavigator webNavigator;
    SalesActivities salesActivities;

    @BeforeMethod
    public void beforeMethod() {
        automationDriver = WebAutomationDriver.get();
        webNavigator = automationDriver.getWebNavigator();
        webNavigator.to(link);

        for (final BrowserCookie cookie : cookies) {
            automationDriver.getSessionCookie().addCookie(cookie);
        }

        automationDriver.getWebWindowHandler().maximize();
        automationDriver.getImplicitWaitHandler().implicitWait(Duration.ofSeconds(10));
        webNavigator.to(SettingsURL.SALES_ACTIVITIES);
        automationDriver.getWebWindowHandler().maximize();
        HomePage.getInstance(automationDriver);
        salesActivities = new SalesActivities(automationDriver);

    }

    @AfterMethod
    public void close() {
        automationDriver.close();
    }

    @DataProvider(name = "salesActivities")
    public static TestCase[][] checkBase() {
        return new JsonFileReader().getTestCases(JsonFields.ADD_ACTIVITY_TYPE);
    }

    @DataProvider(name = "updateActivityType")
    public static TestCase[][] updateActivity() {
        return new JsonFileReader().getTestCases(JsonFields.UPDATE_ACTIVITY_TYPE);
    }

    @DataProvider(name = "disabledActivityType")
    public static TestCase[][] disableActivityType() {
        return new JsonFileReader().getTestCases(JsonFields.DISABLE_ACTIVITY_TYPE);
    }

    @DataProvider(name = "enableActivityType")
    public static TestCase[][] enableActivity() {
        return new JsonFileReader().getTestCases(JsonFields.ENABLE_ACTIVITY_TYPE);
    }

    @DataProvider(name = "saveWithoutIcon")
    public static TestCase[][] saveWithoutIcon() {
        return new JsonFileReader().getTestCases(JsonFields.SAVE_WITHOUT_ICON);
    }

    @DataProvider(name = "saveWithoutName")
    public static TestCase[][] saveWithoutName() {
        return new JsonFileReader().getTestCases(JsonFields.SAVE_WITHOUT_NAME);
    }

    @Test (priority = 1)
    public void checkDefault() {
        Assert.assertTrue(salesActivities.checkDefaultActivityTypes());
    }

    @Test (priority = 2 )
    public void isAvailableInAddForm() {
        Assert.assertTrue(salesActivities.verifyActivityTypesInAddForm());
    }

    @Test(dataProvider = "salesActivities", priority = 3)
    public void addActivityType(final TestCase testCase) {
        Assert.assertTrue(salesActivities.addNewActivityType(testCase));
    }

    @Test(priority = 4)
    public void checkDuplicate() {
        Assert.assertTrue(salesActivities.addActivityTypeAndCheck());
    }

    @Test(dataProvider = "disabledActivityType", priority = 5)
    public void disableActivityType(final TestCase testCase) {
        Assert.assertTrue(salesActivities.disableActivityType(testCase));
    }

    @Test(dataProvider = "enableActivityType", priority = 6)
    public void enableActivityType(final TestCase testCase) {
        Assert.assertTrue(salesActivities.enableActivityType(testCase));
    }

    @Test(dataProvider = "updateActivityType", priority = 7)
    public void updateActivityType(final TestCase testCase) {
        Assert.assertTrue(salesActivities.updateActivityType(testCase));
    }

    @Test(dataProvider = "disabledActivityType", priority = 8)
    public void testEditWithoutChanges(final TestCase testCase) {
        Assert.assertTrue(salesActivities.editWithoutChanges(testCase));
    }

    @Test(priority = 9)
    public void disAbleAll() {
        Assert.assertTrue(salesActivities.disableAll() && salesActivities.isDisplayed(salesActivities.findByXpath("(//div[contains(@class,'95g4uk')])[1]")));
    }

    @Test(dataProvider = "saveWithoutIcon", priority = 10)
    public void withOutIcon(final TestCase testCase) {
        Assert.assertTrue(salesActivities.saveWithoutIconSelection(testCase));
    }

    @Test(dataProvider = "saveWithoutName", priority = 11)
    public void withOutName(final TestCase testCase) {
        Assert.assertTrue(salesActivities.saveWithoutName(testCase));
    }
}

