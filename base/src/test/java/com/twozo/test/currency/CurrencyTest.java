package com.twozo.test.currency;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.page.JsonFields;
import com.twozo.page.homepage.HomePage;
import com.twozo.page.settings.currency.service.Currency;
import com.twozo.page.settings.currency.model.DecimalOption;
import com.twozo.page.settings.currency.reader.JsonFileReader;
import com.twozo.page.settings.currency.reader.TestCase;
import com.twozo.page.url.settings.SettingsURL;
import com.twozo.test.BaseTest;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.driver.service.WebNavigator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class CurrencyTest extends BaseTest {

    private WebAutomationDriver webAutomationDriver;
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

    /**
     * Verifies that the base currency is set to USD by default.
     */
    @Test
    public void checkBaseCurrency() {
        Assert.assertTrue(currency.isBaseCurrencyUSD());
    }

    /**
     * Verifies that the base decimal value is set to 2 by default.
     */
    @Test(priority = 1)
    public void checkBaseDecimal() {
        Assert.assertTrue(currency.isBaseDecimal());
    }

    /**
     * Verifies that the popup is visible after clicking the "Add Currency" button.
     */
    @Test(priority = 3)
    public void hasPopUpVisibleToAddCurrency() {
        Assert.assertTrue(currency.isPopUpVisible());
    }

    @Test(dataProvider = "decimal", priority = 5)
    public void addDecimal(final TestCase testCase) {

        if (testCase.input.containsKey("decimal")) {
            final DecimalOption decimalOption = DecimalOption.fromValue(testCase.input.getInt("decimal"));

            currency.getDecimal(testCase);
            Assert.assertTrue(currency.checkTheDecimalValue(testCase),
                    "The decimal value was not changed as expected. Expected: " + decimalOption);
        }
    }

    @Test(dataProvider = "currency", priority = 4)
    public void addBaseCurrency(final TestCase testCase) {

        if (testCase.input.containsKey("currency")) {
            testCase.input.getString("currency");
        }
        currency.baseCurrency(testCase);
        Assert.assertTrue(currency.checkTheBaseCurrencyValue(testCase));
    }

    @Test(dataProvider = "addCurrency", priority = 6)
    public void addCurrency(final TestCase testCase) {

        if (testCase.input.containsKey("currency")) {
            testCase.input.getString("currency");
        }
        currency.addCurrency(testCase);
        Assert.assertTrue(currency.isAddCurrency());
    }

    @Test(dataProvider = "disableCurrency", priority = 7)
    public void disAbleCurrency(final TestCase testCase) {

        if (testCase.input.containsKey("makeItDisable")) {
            testCase.input.optBoolean("makeItDisable", false);
        }

        currency.isDisabledCurrencySwitch(testCase);

        Assert.assertTrue(currency.isDisableCurrency());
    }

    @Test(dataProvider = "disableCurrency", priority = 9)
    public void enableCurrency(final TestCase testCase) {

        if (testCase.input.containsKey("currency")) {
            testCase.input.getString("currency");
        }

        if (testCase.input.containsKey("isEnable")) {
            testCase.input.optBoolean("isEnable", false);
        }

        currency.isEnabledCurrency(testCase);
        Assert.assertTrue(currency.isEnableCurrency());
    }

    @Test(dataProvider = "addCurrencyAndVerify", priority = 10)
    public void addCurrencyAndVerify(final TestCase testCase) {
        final String currencyToAdd = testCase.input.getString("currency");
        currency.addCurrency(testCase);

        Assert.assertTrue(currency.isCurrencyAvailableInAddForm(currencyToAdd),
                "Currency should not be available in the Add Currency form after adding it.");
    }

    @Test(dataProvider = "addCurrencyAndCheck", priority = 11)
    public void testCurrencySelection(final TestCase testCase) {
        final String currencyToCheck = testCase.input.getString("currency");

        currency.addCurrency(testCase);
        final boolean isPresent = currency.isCurrencyPresentInDropdown(currencyToCheck);
        Assert.assertFalse(isPresent, "The currency " + currencyToCheck + " should be present in the dropdown.");
    }

    @Test(priority = 12)
    public void baseCurrencyButtonStatusAfterAddingCurrency() {
        Assert.assertTrue(currency.isEnabled());
    }

    @Test(priority = 13)
    public void AddBaseCurrencyInSearch() {
        Assert.assertTrue(currency.searchBase());
    }

    @Test(priority = 14)
    public void searchAddCurrency() {
        Assert.assertTrue(currency.searchAddCurrency());
    }
}
