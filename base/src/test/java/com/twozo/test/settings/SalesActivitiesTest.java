package com.twozo.test.settings;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.page.homepage.HomePage;
import com.twozo.page.settings.sales.activities.JsonFields;
import com.twozo.page.settings.sales.activities.JsonFileReader;
import com.twozo.page.settings.sales.activities.SalesActivities;
import com.twozo.page.settings.sales.activities.TestCase;
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

    @Test
    public void checkDefault() {
        Assert.assertTrue(salesActivities.checkDefaultActivityTypes());
    }

    @Test(dataProvider = "salesActivities", priority = 1)
    public void addActivityType(final TestCase testCase) {
        final String activityTypeName = testCase.input.getString("activityTypeName");

        salesActivities.addActivityType(testCase);

        Assert.assertTrue(salesActivities.isActivityTypeAvailableInList(activityTypeName));
    }

    @Test(priority = 2)
    public void checkDuplicate() {
        salesActivities.addActivityTypeAndCheck();
        Assert.assertTrue(salesActivities.isAlreadyExistErrorMsgDisplayed());
    }

    @Test(dataProvider = "disabledActivityType", priority = 3)
    public void disableActivityType(final TestCase testCase) {
        final String activityTypeName = testCase.input.getString("activityType");
        Assert.assertTrue(salesActivities.disableActivityType(testCase));
//        Assert.assertTrue(salesActivities.isActivityTypeNotAvailableInList(activityTypeName));
    }

    @Test(dataProvider = "enableActivityType", priority = 4)
    public void enableActivityType(final TestCase testCase) {
        final String activityTypeName = testCase.input.getString("activityType");
        Assert.assertTrue(salesActivities.enableActivityType(testCase));
//        Assert.assertTrue(salesActivities.isActivityTypeNotAvailableInList(activityTypeName));
    }

    @Test(dataProvider = "updateActivityType", priority = 5)
    public void updateActivityType(final TestCase testCase) {
        final String changes = testCase.input.getString("changes");
        salesActivities.updateActivityTypes(testCase);
        Assert.assertTrue(salesActivities.isActivityTypeAvailableInList(changes));
    }

    @Test(dataProvider = "disabledActivityType", priority = 6)
    public void testEditWithoutChanges(final TestCase testCase) {
        Assert.assertTrue(salesActivities.editWithoutChanges(testCase));
    }

    @Test(priority = 7)
    public void disAbleAll() {
        salesActivities.disableAll();
        Assert.assertTrue(salesActivities.errorMsg() && salesActivities.isDisplayed(salesActivities.findByXpath("(//div[@class='css-95g4uk'])[1]")));
    }

    @Test(dataProvider = "saveWithoutIcon", priority = 8)
    public void withOutIcon(final TestCase testCase) {
        Assert.assertTrue(salesActivities.saveWithoutIconSelection(testCase));
    }

    @Test(dataProvider = "saveWithoutName", priority = 9)
    public void withOutName(final TestCase testCase) {
        Assert.assertTrue(salesActivities.saveWithoutName(testCase));
    }
}
