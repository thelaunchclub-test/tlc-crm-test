package com.twozo.test.settings.currency;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.page.JsonFields;
import com.twozo.page.JsonFileReader;
import com.twozo.page.TestCase;
import com.twozo.page.homepage.HomePage;
import com.twozo.page.settings.currency.service.Currency;
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

public class CurrencyTest extends BaseTest {

    WebAutomationDriver automationDriver;
    WebNavigator webNavigator;
    Currency currency;

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
        webNavigator.to(SettingsURL.CURRENCY);
        automationDriver.getWebWindowHandler().maximize();
        HomePage.getInstance(automationDriver);
        currency = new Currency(automationDriver);
    }

    @AfterMethod
    public void close() {
        automationDriver.close();
    }

    @DataProvider(name = "currency")
    public static TestCase[][] checkBase() {
        return new JsonFileReader().getTestCases(JsonFields.BASE_CURRENCY);
    }

    @DataProvider(name = "decimal")
    public static TestCase[][] checkDecimal() {
        return new JsonFileReader().getTestCases(JsonFields.DECIMAL);
    }

    @DataProvider(name = "addCurrency")
    public static TestCase[][] CheckCurrency() {
        return new JsonFileReader().getTestCases(JsonFields.ADD_CURRENCY);
    }

    @DataProvider(name = "disableCurrency")
    public static TestCase[][] disableCurrency() {
        return new JsonFileReader().getTestCases(JsonFields.DISABLE_CURRENCY);
    }

    @DataProvider(name = "addCurrencyAndCheck")
    public static TestCase[][] addCurrencyAndCheck() {
        return new JsonFileReader().getTestCases(JsonFields.ADD_CURRENCY_CHECK);
    }

    @DataProvider(name = "addCurrencyAndVerify")
    public static TestCase[][] addCurrencyAndVerify() {
        return new JsonFileReader().getTestCases(JsonFields.ADD_CURRENCY_VERIFY);
    }

    @Test(priority = 1)
    public void verifyAllCurrencyOptions() {
        Assert.assertTrue(currency.verifyAllCurrenciesPresent());
    }

    /**
     * Verifies that the base currency is set to USD by default.
     */
    @Test(priority = 2)
    public void checkBaseCurrency() {
        Assert.assertTrue(currency.isBaseCurrencyUSD());
    }

    /**
     * Verifies that the base decimal value is set to 2 by default.
     */
    @Test(priority = 3)
    public void checkBaseDecimal() {
        Assert.assertTrue(currency.isBaseDecimal());
    }

    /**
     * Verifies that the popup is visible after clicking the "Add Currency" button.
     */
    @Test(priority = 4)
    public void hasPopUpVisibleToAddCurrency() {
        Assert.assertTrue(currency.isPopUpVisible());
    }

    @Test(dataProvider = "currency", priority = 5)
    public void addBaseCurrency(final TestCase testCase) {
        Assert.assertTrue(currency.baseCurrency(testCase));
    }

    @Test(priority = 6)
    public void AddBaseCurrencyInSearch() {
        Assert.assertTrue(currency.searchBase());
    }

    @Test(dataProvider = "decimal", priority = 7)
    public void addDecimal(final TestCase testCase) {
        Assert.assertTrue(currency.getDecimal(testCase));
    }

    @Test(priority = 8)
    public void baseCurrencyButtonStatusAfterAddingCurrency() {
        Assert.assertTrue(currency.isEnabled());
    }

    @Test(dataProvider = "addCurrency", priority = 9)
    public void addCurrency(final TestCase testCase) {
        Assert.assertTrue(currency.addCurrencyTest(testCase));
    }

    @Test(dataProvider = "disableCurrency", priority = 10)
    public void disAbleCurrency(final TestCase testCase) {
        Assert.assertTrue(currency.isDisabledCurrencySwitch(testCase));
    }

    @Test(dataProvider = "disableCurrency", priority = 11)
    public void enableCurrency(final TestCase testCase) {
        Assert.assertTrue(currency.isEnabledCurrency(testCase));
    }

    @Test(dataProvider = "addCurrencyAndVerify", priority = 12)
    public void addCurrencyAndVerify(final TestCase testCase) {
        Assert.assertTrue(currency.isCurrencyAvailableInAddForm(testCase),
                "Currency should not be available in the Add Currency form after adding it.");
    }

    @Test(dataProvider = "addCurrencyAndCheck", priority = 13)
    public void testCurrencySelection(final TestCase testCase) {
        Assert.assertFalse(currency.isCurrencyPresentInDropdown(testCase), "The currency should be present in the dropdown.");
    }

    @Test(priority = 14)
    public void searchAddCurrency() {
        Assert.assertTrue(currency.searchAddCurrency());
    }
}