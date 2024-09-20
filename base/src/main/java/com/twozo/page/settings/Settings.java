package com.twozo.page.settings;

import com.twozo.page.BasePage;
import com.twozo.page.company.Company;
import com.twozo.page.contact.Contact;
import com.twozo.page.deal.Deal;
import com.twozo.page.product.Product;
import com.twozo.page.settings.data.fields.company.CompanyDataField;
import com.twozo.page.settings.data.fields.contact.ContactDataField;
import com.twozo.page.settings.data.fields.deal.DealDataField;
import com.twozo.page.settings.data.fields.product.ProductDataField;
import com.twozo.page.settings.tags.Tags;
import com.twozo.page.xpath.XPathBuilder;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * <p>
 * Represents the settings page.It provides methods to interact with different modules.
 * </p>
 *
 * @author Petchimuthu
 * @version 1.0
 */
public class Settings extends BasePage {

    private static Settings settings;

    private WebPageElement contact;
    private WebPageElement company;
    private WebPageElement deal;
    private WebPageElement product;
    private Collection<WebPageElement> fields;
    private Tags tags = new Tags(webAutomationDriver);

    protected Settings(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
//        if (!getURL().equals(URL.SETTINGS)) {
//            throw ErrorCode.get(WebDriverErrorCode.EXPECTED_PAGE_NOT_FOUND, "exp page not found");
//        }
    }

    public static Settings getInstance(final WebAutomationDriver webAutomationDriver) {
        if (settings == null) {
            settings = new Settings(webAutomationDriver);
        }
        return settings;
    }

    /**
     * <p>
     * Retrieves the {@link WebPageElement} for the {@link Contact} data field.
     * </p>
     *
     * @return The {@link WebPageElement} representing the {@link Contact} data field.
     */
    public WebPageElement getContact() {
        return findByText("Contact");
    }

    /**
     * <p>
     * Retrieves the {@link WebPageElement} for the {@link Company} data field.
     * </p>
     *
     * @return The {@link WebPageElement} representing the {@link Company} data field.
     */
    public WebPageElement getCompany() {
        return findByText("Company");
    }

    /**
     * <p>
     * Retrieves the {@link WebPageElement} for the {@link Deal} data field.
     * </p>
     *
     * @return The {@link WebPageElement} representing the {@link Deal} data field.
     */
    public WebPageElement getDeal() {
        return findByText("Deal");
    }

    /**
     * <p>
     * Retrieves the {@link WebPageElement} for the {@link Product} data field.
     * </p>
     *
     * @return The {@link WebPageElement} representing the {@link Product} data field.
     */
    public WebPageElement getProduct() {
        return findByText("Product");
    }

    /**
     * <p>
     * Switches to the Contact data fields page and returns the {@link ContactDataField}.
     * </p>
     *
     * @return The {@link ContactDataField}.
     */
    public ContactDataField switchToContactDataFields() {
        click(getContact());

        return ContactDataField.getInstance(webAutomationDriver);
    }

    /**
     * Switches to the Company data fields page and returns the {@link CompanyDataField}.
     *
     * @return The {@link CompanyDataField}.
     */
    public CompanyDataField switchToCompanyDataFields() {
        click(getCompany());

        return CompanyDataField.getInstance(webAutomationDriver);
    }

    /**
     * Switches to the Deal data fields page and returns the {@link DealDataField}.
     *
     * @return The {@link DealDataField}.
     */
    public DealDataField switchToDealDataFields() {
        click(getDeal());

        return DealDataField.getInstance(webAutomationDriver);
    }

    /**
     * <p>
     * Switches to the Product data fields page and returns the {@link ProductDataField}.
     * </p>
     *
     * @return The {@link ProductDataField}.
     */
    public ProductDataField switchToProductDataFields() {
        click(getProduct());

        try {
            if (isDisplayed(findByXpath(XPathBuilder.getXpathByContains("Enables")))) {
                click(findLeftElement(List.of(new Element(LocatorType.XPATH,
                        "(//*[text()='Product'])[2]", true), new Element(LocatorType.TAG_NAME, "input",
                        false))));
            }
        } catch (NoSuchElementException noSuchElementException) {
        }

        refresh();

        return ProductDataField.getInstance(webAutomationDriver);
    }

    public Tags clickTags() {
        click(getTags());

        return tags;
    }

    public WebPageElement getTags() {
        return this.findByXpath("//*[text()='Tags']");
    }
}
