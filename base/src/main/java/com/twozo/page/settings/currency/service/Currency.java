package com.twozo.page.settings.currency.service;

import com.twozo.extent.report.reporter.logger.ExtentLogger;
import com.twozo.page.BasePage;
import com.twozo.page.TestCase;
import com.twozo.page.settings.currency.model.DecimalOption;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

public class Currency extends BasePage {

    public static final String CURRENCY_TYPE = "currency";
    private static final String POPUP_MSG = "The pop-up will display a message indicating that the base currency cannot be changed.";
    private static final String YES = "Yes button is clicked";
    private static final String ADD_CURRENCY_BTN = "Add currency button is clicked";
    private static final String CURRENCY_FIELD = "Add currency field is clicked";
    private static final String CURRENCY_OPTION = "The given currency is clicked";
    private static final String CURRENCY_LIST = "//*[@class='MuiList-root MuiList-padding MuiMenu-list css-r8u8y9']";

    public Currency(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    /**
     * <p>
     * Locates and returns the first dropdown {@link Element} on the page
     * that is identified by the specified XPath.
     * </p>
     *
     * @return {@link WebPageElement} representing the base currency dropdown.
     */
    public WebPageElement getBaseCurrencyDropDown() {
        return this.findByXpath("(//*[@class='MuiSelect-icon MuiSelect-iconOutlined MuiBox-root css-1chfn8n'])[1]");
    }

    /**
     * <p>
     * Locates and returns the base currency dropdown {@link Element} identified
     * by the specified XPath.
     * </p>
     *
     * @return {@link WebPageElement} representing the base currency dropdown box.
     */
    public WebPageElement getBaseCurrencyBox() {
        return this.findByXpath("(//*[@aria-haspopup='listbox'])[1]");
    }

    /**
     * <p>
     * Locates and returns the Add Currency {@link Element} identified
     * by the specified XPath
     * </p>
     *
     * @return {@link WebPageElement} representing the add currency dropdown box.
     */
    public WebPageElement getAddCurrencyBox() {
        return this.findByXpath("(//*[@aria-haspopup='listbox'])[3]");
    }

    /**
     * <p>
     * Locates and returns the {@link Element} that displays "United States Dollar (USD)"
     * on the page.
     * </p>
     *
     * @return {@link WebPageElement} representing the base currency, USD.
     */
    public WebPageElement getBaseCurrency() {
        return this.findByText("United States Dollar (USD)");
    }

    /**
     * <p>
     * Locates and returns the {@link Element} that displays the number '2' on the page.
     * </p>
     *
     * @return {@link WebPageElement} representing the base decimal value.
     */
    public WebPageElement getBaseDecimal() {
        return this.findByXpath("//*[text()='2']");
    }

    /**
     * <p>
     * Locates and returns the Base Currency dropdown {@link Element} identified
     * by the specified XPath.
     * </p>
     *
     * @return {@link WebPageElement} representing the decimal precision dropdown.
     */
    public WebPageElement getDecimalDropDown() {
        return this.findByXpath("(//*[@aria-haspopup='listbox'])[2]");
    }

    /**
     * <p>
     * Locates and returns the button {@link Element} with the text "Add Currency".
     * </p>
     *
     * @return {@link WebPageElement} representing the "Add Currency" button.
     */
    public WebPageElement getAddCurrencyButton() {
        return this.findByXpath("//button[text()='Add Currency']");
    }

    /**
     * <p>
     * Locates and returns the button {@link Element} labeled "Save."
     * </p>
     *
     * @return {@link WebPageElement} representing the "Save" button.
     */
    public WebPageElement getSave() {
        return this.findByXpath("//*[text()='Save']");
    }

    /**
     * <p>
     * Locates and returns the search field identified by a unique ID.
     * </p>
     *
     * @return {@link WebPageElement} representing the search input field.
     */
    public WebPageElement getSearch() {
        return this.findByXpath("//*[@id=':ro:']");
    }

    /**
     * <p>
     * Locates and returns the button {@link Element} labeled "Change."
     * </p>
     *
     * @return {@link WebPageElement} representing the "Change" button.
     */
    public WebPageElement getChange() {
        return this.findByText("Change");
    }

    /**
     * Locates and returns the "Cancel" button, which is identified by
     * its SVG namespace and fill style.
     *
     * @return {@link WebPageElement} representing the "Cancel" button.
     */
    public WebPageElement getCancel() {
        return this.findByXpath("//*[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorPrimary MuiIconButton-sizeSmall css-1q67rw']");
    }

    /**
     * <p>
     * Locates and returns the {@link Element} representing a pop-up dialog
     * identified by its CSS class. Interact with or verify the visibility
     * of the pop-up within the AddCurrency.
     * </p>
     *
     * @return {@link WebPageElement} representing the pop-up dialog.
     */
    public WebPageElement getPopUp() {
        return this.findByXpath("//*[contains(@class, '1ty026z')]");
    }

    /**
     * Locates and returns the button element labeled "Yes."
     *
     * @return {@link WebPageElement} representing the "Yes" button.
     */
    public WebPageElement getYes() {
        return this.findByText("Yes");
    }

    /**
     * <p>
     * Locates and returns the button {@link Element} labeled "Deactivate."
     * </p>
     *
     * @return {@link WebPageElement} representing the "Deactivate" button.
     */
    public WebPageElement getDeActivateButton() {
        return this.findByXpath("//button[text()='Deactivate']");
    }

    /**
     * <p>
     * Locates and returns the toggle switch element that enables a currency.
     * </p>
     *
     * @return {@link WebPageElement} representing the enable toggle switch.
     */
    public WebPageElement getEnableButton() {
        return this.findByXpath("(//*[contains(@class, '4fvm5n')])");
    }

    /**
     * <p>
     * Locates and returns the toggle switch {@link Element} that disables a currency.
     * </p>
     *
     * @return {@link WebPageElement} representing the disable toggle switch.
     */
    public WebPageElement getDisableButton() {
        return this.findByXpath("//*[@class='MuiButtonBase-root MuiSwitch-switchBase MuiSwitch-colorPrimary Mui-checked PrivateSwitchBase-root MuiSwitch-switchBase MuiSwitch-colorPrimary Mui-checked Mui-checked css-4fvm5n']");
    }

    /**
     * <p>
     * Locates and returns the {@link Element} representing the "Add Currency"
     * pop-up dialog, identified by its CSS class.
     * </p>
     *
     * @return {@link WebPageElement} representing the "Add Currency" pop-up dialog.
     */
    public WebPageElement addCurrencyPopUp() {
        return this.findByXpath("//*[contains(@class, '1ty026z')]");
    }


    /**
     * Retrieves the "No" button element on the webpage.
     *
     * @return the {@link WebPageElement} representing the "No" button.
     */
    public WebPageElement getNo() {
        return this.findByXpath("//*[text()='No']");
    }

    /**
     * Returns the {@link WebPageElement} that represents the success message indicating the currency decimal has been updated.
     *
     * @return the {@link WebPageElement} for the "Currency decimal has been updated successfully." message.
     */
    public WebPageElement getDecimalVisible() {
        return this.findByXpath("//*[text()='Currency decimal has been updated successfully.']");
    }

    /**
     * Returns the {@link WebPageElement} that represents the success message indicating the base currency has been updated.
     *
     * @return the {@link WebPageElement} for the "Base currency has been updated successfully." message.
     */
    public WebPageElement hasBaseCurrencyPopUp() {
        return this.findByXpath("//*[text()='Base currency has been updated successfully.']");
    }

    /**
     * Returns the {@link WebPageElement} that represents the success message indicating a new currency has been added.
     *
     * @return the {@link WebPageElement} for the "Currency has been added successfully." message.
     */
    public WebPageElement hasAddCurrencyPopUp() {
        return this.findByXpath("//*[text()='Currency has been added successfully.']");
    }

    /**
     * Returns the {@link WebPageElement} that represents the success message indicating the currency has been deactivated.
     *
     * @return the {@link WebPageElement} for the "Currency has been deactivated successfully." message.
     */
    public WebPageElement hasDisablePopUp() {
        return this.findByXpath("//*[text()='Currency has been deactivated successfully.']");
    }

    /**
     * Returns the {@link WebPageElement} that represents the success message indicating the currency has been activated.
     *
     * @return the {@link WebPageElement} for the "Currency has been activated successfully." message.
     */
    public WebPageElement hasEnablePopUp() {
        return this.findByText("Currency has been activated successfully.");
    }

    /**
     * <p>
     * Verifies whether the base currency is displayed as "United States Dollar (USD)."
     * </p>
     *
     * @return boolean indicating whether the base currency is USD.
     */
    public boolean isBaseCurrencyUSD() {
        final boolean USD = isDisplayed(getBaseCurrency());

        ExtentLogger.pass("Base Currency is set to USD as default");

        return USD;
    }

    /**
     * <p>
     * Verifies whether the base decimal value is displayed,
     * with exception handling to ensure stability.
     * </p>
     *
     * @return boolean indicating whether the base decimal value is displayed.
     */
    public boolean isBaseDecimal() {
        try {
            isDisplayed(getBaseDecimal());
            ExtentLogger.info("Base decimal is set as 2 by default");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * <p>
     * Checks if the "Add Currency" pop-up is visible.
     * Verifies whether the "Add Currency" pop-up dialog is displayed
     * after clicking the "Add Currency" button.
     * </p>
     *
     * @return boolean indicating whether the pop-up is visible.
     */
    public boolean isPopUpVisible() {
        click(getAddCurrencyButton());
        ExtentLogger.pass(ADD_CURRENCY_BTN);

        boolean isVisible = isDisplayed(getPopUp());
        ExtentLogger.info(POPUP_MSG);
        click(getNo());
        ExtentLogger.pass("No button is clicked");

        return isVisible;
    }

    /**
     * <p>
     * Selects the appropriate decimal value from the dropdown based on the test case data.
     * </p>
     *
     * @param testCase the test case object containing input data, including the decimal value to be selected.
     */
    public boolean getDecimal(final TestCase testCase) {
        final int decimalValue = testCase.input.getInt("decimal");
        final DecimalOption decimalOption = DecimalOption.fromValue(decimalValue);

        click(getDecimalDropDown());
        ExtentLogger.pass("The Decimal field is clicked");

        if (decimalOption == DecimalOption.NO_DECIMAL) {
            dropdown(decimalOption.getDescription());
            ExtentLogger.pass("The No decimal value has been clicked");
        } else {
            dropdown(String.valueOf(decimalOption.getValue()));
            ExtentLogger.pass("The given decimal value has been clicked");
        }

        click(getYes());
        ExtentLogger.pass(YES);
        sleepFor();

        return Objects.equals(String.valueOf(decimalValue), getText(getDecimalDropDown()));
    }

    /**
     * Selects the base currency from the dropdown based on the test case data.
     *
     * @param testCase the test case object containing input data, including the currency to be selected.
     */
    public boolean baseCurrency(final TestCase testCase) {
        final String currency = testCase.input.getString(CURRENCY_TYPE);

        if (Objects.nonNull(currency) && !getText(getBaseCurrencyBox()).contains(currency)) {
            click(getBaseCurrencyBox());
            ExtentLogger.pass("Base currency field is clicked");
            sleepFor();
            dropdown(currency);
            ExtentLogger.pass(" The Given currency is clicked");
            sleepFor();
            click(getChange());
            ExtentLogger.pass("Get change button is clicked");
        }

        return getText(getBaseCurrencyBox()).contains(currency);
    }

    /**
     * Checks if a currency is enabled based on the conditions in the test case.
     * If the currency is set to be disabled, it tries to deactivate it;
     * otherwise, it verifies if the currency is already enabled.
     *
     * @param testCase the test case object containing the input conditions.
     * @return true if the currency is successfully handled according to the conditions, false otherwise.
     */
    public boolean isDisabledCurrencySwitch(final TestCase testCase) {

        if (testCase.input.optBoolean("makeItDisable", false)) {
            String currency = testCase.input.getString(CURRENCY_TYPE);

            try {
                isDisplayed(findByXpath(String.format("%s%s", getCurrencyFieldBlock(currency),
                        "//*[contains(@class, 'MuiSwitch-switchBase') and contains(@class, 'Mui-checked Mui-checked')]")));

                click(findByXpath(getCurrencyFieldBlock(currency)));
                ExtentLogger.pass("Disable button for the specified currency has been clicked");
                click(getDeActivateButton());
                ExtentLogger.pass("DeActivated button is clicked");

            } catch (Exception exception) {
                if (isDisplayed(getEnableButton())) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return isDisplayed(hasDisablePopUp());

    }

    public boolean isEnabledCurrency(final TestCase testCase) {

        if (testCase.input.optBoolean("makeItEnable", false)) {
            final String currency = testCase.input.getString(CURRENCY_TYPE);

            try {

                WebPageElement currencySwitch = findByXpath(String.format("%s%s",
                        getCurrencyFieldBlock(currency),
                        "//*[contains(@class, 'MuiSwitch-switchBase') and contains(@class, 'MuiSwitch-colorPrimary')]"));


                if (isDisplayed(currencySwitch)) {

                    sleepFor();
                    click(findByXpath(getCurrencyFieldBlock(currency)));
                    ExtentLogger.pass("The enable button for the specified currency has been clicked.");
                }
            } catch (Exception exception) {
                // Handle cases where the currency might already be enabled
                if (isDisplayed(getDisableButton())) {
                    ExtentLogger.info("The currency is already enabled.");
                } else {
                    ExtentLogger.fail("Failed to enable the currency. Exception: " + exception.getMessage());
                    return false;
                }
            }
        }

        try {
            return isDisplayed(hasEnablePopUp());
        } catch (NoSuchElementException e) {
            ExtentLogger.fail("Enable popup is not displayed.");
            return false;
        }
    }

    protected String getCurrencyFieldBlock(final String currency) {
        return String.format("//*[contains(text(), '%s')]/parent::div/preceding-sibling::div/span", currency);
    }

    /**
     * Retrieves a collection of available currencies from the add currency form.
     *
     * @return a collection of strings representing the available currencies.
     */
    public Collection<String> getAvailableCurrenciesInAddForm() {
        final Collection<WebPageElement> currencyElements = findElementsByXpath(CURRENCY_LIST);
        final List<String> availableCurrencies = new ArrayList<>();

        for (final WebPageElement currencyElement : currencyElements) {
            final String currencyName = currencyElement.getElementInformationProvider().getText();
            availableCurrencies.add(currencyName);
        }

        return availableCurrencies;
    }

    public Collection<String> getAvailableCurrenciesInList() {
        refresh();
        final Collection<WebPageElement> currencyElements = findElementsByXpath("//*[contains(@class, 'j7qwjs')]/div/div[2]");
        final List<String> availableCurrencies = new ArrayList<>();

        for (final WebPageElement currencyElement : currencyElements) {
            final String currencyName = currencyElement.getElementInformationProvider().getText();
            availableCurrencies.add(currencyName);
        }

        for (String availableCurrency : availableCurrencies) {
            System.out.println(availableCurrency);
        }

        return availableCurrencies;
    }

    /**
     * Checks if a specific currency is available in the add currency form dropdown.
     *
     * @param testCase the name of the currency to check for.
     * @return true if the currency is available, false otherwise.
     */
    public boolean isCurrencyAvailableInAddForm(final TestCase testCase) {
        final String currency = testCase.input.getString(CURRENCY_TYPE);

        if (Objects.nonNull(currency)) {
            click(getAddCurrencyButton());
            ExtentLogger.pass(ADD_CURRENCY_BTN);

            try {
                isDisplayed(addCurrencyPopUp());
                ExtentLogger.info(POPUP_MSG);
                click(getYes());
                ExtentLogger.pass(YES);
                addCurrencyToDropdown(currency);

            } catch (Exception exception) {
                addCurrencyToDropdown(currency);
            }
        }
        final Collection<String> availableCurrencies = getAvailableCurrenciesInAddForm();
        return !availableCurrencies.contains(currency);
    }


    /**
     * Checks if a specific currency is present in the dropdown.
     * Attempts to select the currency if found.
     *
     * @param testCase the currency to search for in the dropdown.
     * @return true if the currency is present and successfully selected, false otherwise.
     */
    public boolean isCurrencyPresentInDropdown(final TestCase testCase) {
        final String currency = testCase.input.getString(CURRENCY_TYPE);

        if (Objects.nonNull(currency)) {
            click(getAddCurrencyButton());
            ExtentLogger.pass(ADD_CURRENCY_BTN);

            try {
                isDisplayed(addCurrencyPopUp());
                ExtentLogger.info(POPUP_MSG);
                click(getYes());
                ExtentLogger.pass(YES);
                addCurrencyToDropdown(currency);

            } catch (Exception exception) {
                addCurrencyToDropdown(currency);
            }
        }

        click(getAddCurrencyButton());
        ExtentLogger.pass("Add currency button is clicked successfully");

        try {
            if (isDisplayed(addCurrencyPopUp())) {
                ExtentLogger.info(POPUP_MSG);
                click(getYes());
                ExtentLogger.pass(YES);
            }

        } catch (Exception e) {
            click(getAddCurrencyBox());
            ExtentLogger.pass("The add currency field is clicked successfully");
            final Collection<WebPageElement> currencyOptions = findElementsByXpath(CURRENCY_LIST);

            for (WebPageElement option : currencyOptions) {
                String currencyText = option.getElementInformationProvider().getText().trim();

                if (currencyText.contains(currency)) {
                    ExtentLogger.info("The already added currency is not displayed in the currency list.");
                    click(findByXpath("//body"));
                    click(getCancel());
                    ExtentLogger.pass("The cancel icon is clicked successfully");
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if the add currency button is enabled. If not, it attempts to enable it.
     *
     * @return true if the add currency button is enabled, false otherwise.
     */
    public boolean isEnabled() {
        click(getAddCurrencyButton());
        ExtentLogger.pass("Add Currency button is clicked successfully");
        click(getYes());
        ExtentLogger.pass("Yes button is clicked successfully");
        click(getAddCurrencyBox());
        ExtentLogger.pass("Add Currency field is clicked successfully");
        dropdown("EUR");
        click(getSave());
        ExtentLogger.pass("Save button is clicked successfully");

        boolean baseCurrencyButtonStatus = isDisplayed(findElement(new Element(LocatorType.CLASS_NAME, "Mui-disabled", true)));
        ExtentLogger.info("After adding a currency, the base currency button will become unClickable");
        return baseCurrencyButtonStatus;
    }

    /**
     * Searches for a base currency by its code and selects it if found.
     *
     * @return true if the currency is found and selected, false otherwise.
     */
    public boolean searchBase() {
        final String currencyCode = "KW";
        final String expectedCurrency = "KWD";

        click(getBaseCurrencyBox());
        click(getSearchInBase());

        send(getSearchInBase(), currencyCode);

        if (isElementPresent("//*[text()='" + expectedCurrency + "']")) {
            dropdown(expectedCurrency);
            click(getChange());
            return true;
        } else {
            isElementPresent("//*[text()='No results found']");
        }

        return false;
    }

    /**
     * Retrieves the search input element for searching within the base currency section.
     *
     * @return the WebPageElement representing the search input in the base currency section.
     */
    public WebPageElement getSearchInBase() {
        return this.findByXpath("//*[contains(@class,'3yog9s')]");
    }

    /**
     * Retrieves the search input element for searching within the add currency section.
     *
     * @return the WebPageElement representing the search input in the add currency section.
     */
    public WebPageElement getSearchInAddCurrency() {
        return this.findByXpath("//*[contains(@class,'3yog9s')]//*[@placeholder='Search']");
    }

    /**
     * Searches for a currency in the add currency section using a code, selects it if found,
     * and saves the selection. If not found, it displays a "No results found" message.
     *
     * @return true if the currency is found and selected, false otherwise.
     */
    public boolean searchAddCurrency() {
        final String currencyCode = "CH";
        final String expectedCurrency = "CHF";

        click(getAddCurrencyButton());
        ExtentLogger.pass(ADD_CURRENCY_BTN);

        try {
            if (isDisplayed(addCurrencyPopUp())) {
                ExtentLogger.info(POPUP_MSG);
                click(getYes());
                ExtentLogger.pass(YES);
            }
            click(getAddCurrencyBox());
            ExtentLogger.pass(CURRENCY_FIELD);
            click(getSearchInAddCurrency());
            ExtentLogger.pass("Search field is clicked");
            send(getSearchInAddCurrency(), currencyCode);
            ExtentLogger.pass("The given currency is entered in the search field");
        } catch (Exception exception) {
            sleepFor();
            click(getAddCurrencyBox());
            ExtentLogger.pass(CURRENCY_FIELD);
            click(getSearchInAddCurrency());
            ExtentLogger.pass("The search field is clicked");
            send(getSearchInAddCurrency(), currencyCode);
            ExtentLogger.pass("The search field is clicked");
        }

        if (isElementPresent("//*[text()='" + expectedCurrency + "']")) {
            dropdown(expectedCurrency);
            ExtentLogger.pass(CURRENCY_OPTION);
            click(getSave());
            ExtentLogger.pass("Save button is clicked");
            return true;
        } else {
            isElementPresent("//*[text()='No results found']");
        }

        return false;
    }

    /**
     * Checks if an element is present on the webpage using the provided Xpath locator.
     *
     * @param locator the Xpath string used to locate the element.
     * @return true if the element is found, false otherwise.
     */
    private boolean isElementPresent(final String locator) {
        try {
            findElement(new Element(LocatorType.XPATH, locator, true));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Pauses the current thread for 2 seconds (2000 milliseconds).
     * If the thread is interrupted while sleeping, it catches
     * the `InterruptedException` and rethrows it as a `RuntimeException`.
     */
    public void sleepFor() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the process of adding a currency from the test case data.
     *
     * @param testCase The {@link TestCase} containing input data for the currency type.
     */
    public boolean addCurrencyTest(final TestCase testCase) {
        final String currency = testCase.input.getString(CURRENCY_TYPE);

        if (Objects.nonNull(currency)) {
            click(getAddCurrencyButton());
            ExtentLogger.pass(ADD_CURRENCY_BTN);

            try {
                isDisplayed(addCurrencyPopUp());
                ExtentLogger.info(POPUP_MSG);
                click(getYes());
                ExtentLogger.pass(YES);
                addCurrencyToDropdown(currency);

            } catch (Exception exception) {
                addCurrencyToDropdown(currency);
            }
        }

        for (final String currencies : getAvailableCurrenciesInList()) {

            if (currencies.contains(currency)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adding the specified currency to the dropdown
     * and saving the changes. It selects the currency based on the provided XPath.
     *
     * @param currency The {@link Currency} string to be selected and added.
     */
    private void addCurrencyToDropdown(final String currency) {
        click(getAddCurrencyBox());
        ExtentLogger.pass(CURRENCY_FIELD);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        click(findByXpath(String.format("//p[contains(text(),'%s')]//parent::div", currency)));
        ExtentLogger.pass(CURRENCY_OPTION);
        click(getSave());
        ExtentLogger.pass("The save button is clicked");
    }


    public boolean verifyAllCurrenciesPresent() {
        click(getBaseCurrencyBox());

        List<String> currencyList = List.of(
                "Afghanistan Afghani", "Albanian Lek", "Algerian Dinar", "Angolan Kwanza",
                "Argentine Peso", "Armenian Dram", "Aruban Guilder", "Australian Dollar",
                "Azerbaijan Manat", "Azerbaijan Old Manat", "Bahamian Dollar", "Bahraini Dinar",
                "Bangladesh Taka", "Barbados Dollar", "Belarusian Ruble", "Belize Dollar",
                "Bermudian Dollar", "Bhutan Ngultrum", "Bitcoin", "Bolivian Boliviano",
                "Bolívar Soberano", "Bosnia and Herzegovina Convertible Marks", "Botswana Pula",
                "Brazilian Real", "Brunei Dollar", "Bulgarian Lev", "Burundi Franc",
                "CFA Franc BCEAO", "CFA Franc BEAC", "CFP Franc", "Cambodia Riel",
                "Canadian Dollar", "Cape Verde Escudo", "Cayman Islands Dollar", "Chilean Peso",
                "Chilean Unidad de Fomento", "Chinese Yuan Renminbi", "Colombian Peso",
                "Comorian Franc", "Costa Rican Colon", "Croatian Kuna", "Cuban Peso",
                "Cyprus Pound", "Czech Koruna", "Danish Krone", "Djibouti Franc", "Dobra",
                "Dominican Peso", "East Caribbean Dollar", "Egyptian Pound", "El Salvador Colon",
                "Eritrea Nafka", "Estonian Kroon", "Ethiopian Birr", "Euro", "Falkland Islands Pound",
                "Fiji Dollar", "Franc Congolais", "Gambian Dalasi", "Georgian Lari",
                "Ghana Cedi", "Ghanaian Cedi", "Gibraltar Pound", "Gold", "Guatemala Quetzal",
                "Guinean Franc", "Guyana Dollar", "Haiti Gourde", "Honduras Lempira",
                "Hong Kong Dollar", "Hungarian Forint", "Iceland Krona", "Indian Rupee",
                "Indonesian Rupiah", "Iranian Rial", "Iraqi Dinar", "Jamaican Dollar",
                "Japanese Yen", "Jordanian Dinar", "Kazakhstan Tenge", "Kenyan Shilling",
                "Korean Won", "Kuwaiti Dinar", "Kyrgyzstan Som", "Lao Kip", "Latvian Lats",
                "Lebanese Pound", "Lesotho Loti", "Liberian Dollar", "Libyan Dinar",
                "Lithuanian Litas", "Macau Pataca", "Macedonian Denar", "Malagasy Ariary",
                "Malawi Kwacha", "Malaysian Ringgit", "Maldives Rufiyaa", "Maltese Lira",
                "Mauritania Ouguiya", "Mauritius Rupee", "Mexican Peso", "Moldovan Leu",
                "Mongolian Tugrik", "Moroccan Dirham", "Mozambican Metical", "Mozambique Metical",
                "Myanmar Kyat", "Namibia Dollar", "Nepalese Rupee", "Netherlands Antillian Guilder",
                "New Belarusian Ruble", "New Israeli Shekel", "New Taiwan Dollar", "New Zealand Dollar",
                "Nicaragua Cordoba Oro", "Nigerian Naira", "North Korean Won", "Norwegian Krone",
                "Ouguiya", "Pakistan Rupee", "Palladium", "Panama Balboa", "Papua New Guinea Kina",
                "Paraguayan Guarani", "Peruvian Nuevo Sol", "Peso Convertible", "Peso Uruguayo",
                "Philippine Peso", "Platinum", "Polish Zloty", "Pound Sterling", "Qatari Rial",
                "Rial Omani", "Romanian Leu", "Russian Ruble", "Rwanda Franc", "SDR (Special Drawing Rights)",
                "Samoa Tala", "Saudi Riyal", "Serbian Dinar", "Seychelles Rupee", "Sierra Leone Leone",
                "Silver", "Singapore Dollar", "Slovak Koruna", "Slovenian Tolar", "Solomon Islands Dollar",
                "Somali Shilling", "South African Rand", "South Sudanese Pound", "Sri Lanka Rupee",
                "St Helena Pound", "Sudanese Dinar", "Sudanese Pound", "Surinam Dollar",
                "Swaziland Lilangeni", "Swedish Krona", "Swiss Franc", "Syrian Pound",
                "São Tome and Principe Dobra", "Tajik Somoni", "Tanzanian Shilling", "Thai Baht",
                "Tonga Pa'anga", "Trinidad and Tobago Dollar", "Tunisian Dinar", "Turkish Lira",
                "Turkmenistan Manat", "Turkmenistani Manat", "UAE Dirham", "US Dollar",
                "Uganda Shilling", "Ukraine Hryvnia", "Uzbekistan Sum", "Vanuatu Vatu",
                "Venezuelan Bolivar", "Venezuelan Bolívar", "Vietnamese Dong", "Yemeni Rial",
                "Zambian Kwacha", "Zimbabwe Dollar"
        );

        Collection<WebPageElement> currencyElements = findElementsByXpath("//ul[contains(@class, 'MuiMenu-list')]//li//p[1]");

        List<String> availableCurrencies = currencyElements.stream()
                .map(e -> getText(e).trim().toLowerCase())
                .collect(Collectors.toList());


        List<String> missingCurrencies = new ArrayList<>();
        for (String currency : currencyList) {
            if (!availableCurrencies.contains(currency.toLowerCase().trim())) {
                missingCurrencies.add(currency);
            }
        }

        if (!missingCurrencies.isEmpty()) {
            System.out.println("Missing Currencies: " + missingCurrencies);
            return false;
        }

        System.out.println("All specified currencies are available.");
        return true;
    }
}
