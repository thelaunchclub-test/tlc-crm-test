package com.twozo.crm.automation.base.page.settings;

import com.twozo.crm.automation.base.page.company.Company;
import com.twozo.crm.automation.base.page.contact.Contact;
import com.twozo.crm.automation.base.page.deal.DealPage;
import com.twozo.crm.automation.base.page.page.AbstractPage;
import com.twozo.crm.automation.base.page.product.Product;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.Collection;

/**
 * <p>
 * Represents the settings page.It provides methods to interact with different modules.
 * </p>
 *
 * @author Petchimuthu
 * @version 1.0
 */
public class SettingsPage extends AbstractPage {

    private static SettingsPage settings;
    private WebPageElement contact;
    private WebPageElement company;
    private WebPageElement deal;
    private WebPageElement product;
    private Collection<WebPageElement> fields;

    protected SettingsPage(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
//        if (!getURL().equals(URL.SETTINGS)) {
//            throw ErrorCode.get(WebDriverErrorCode.EXPECTED_PAGE_NOT_FOUND, "exp page not found");
//        }
    }

    public static SettingsPage getInstance(final WebAutomationDriver webAutomationDriver) {
        if (settings == null) {
            settings = new SettingsPage(webAutomationDriver);
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
     * Retrieves the {@link WebPageElement} for the {@link DealPage} data field.
     * </p>
     *
     * @return The {@link WebPageElement} representing the {@link DealPage} data field.
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

}
