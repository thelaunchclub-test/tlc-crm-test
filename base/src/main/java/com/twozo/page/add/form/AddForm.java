package com.twozo.page.add.form;

import com.twozo.page.BasePage;
import com.twozo.page.settings.data.fields.company.field.CompanyField;
import com.twozo.page.settings.data.fields.contact.field.ContactField;
import com.twozo.page.settings.data.fields.deal.field.DealField;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

public class AddForm extends BasePage {

    protected static final String SAVE_BUTTON = map.get("crm.deal.pipeline.form.save");
    protected static final String SAVE_AND_NEW_BUTTON = map.get("crm.deal.pipeline.form.save.and.new");
    protected static final String CLOSE_BUTTON = map.get("crm.deal.pipeline.close");
    protected static final String TEXT_FIELD = map.get("crm.add.form.text.field");
    protected static final String NUMBER_FIELD = map.get("crm.add.form.number.field");
    protected static final String DROPDOWN_FIELD = map.get("crm.add.form.dropdown.field");
    protected static final String ADDRESS_FIELD = map.get("crm.add.form.address.field");
    protected static final String ADDRESS_LINE_1 = map.get("crm.add.form.address.placeholder.address.line.1");
    protected static final String ADDRESS_LINE_2 = map.get("crm.add.form.address.placeholder.address.line.2");
    protected static final String CITY = map.get("crm.add.form.address.placeholder.address.city");
    protected static final String STATE = map.get("crm.add.form.address.placeholder.address.state");
    protected static final String COUNTRY = map.get("crm.add.form.address.placeholder.address.country");
    protected static final String PINCODE = map.get("crm.add.form.address.placeholder.address.pincode");

    protected AddForm(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    protected WebPageElement getTextFieldWebPageElement(final String fieldName) {
        return findByXpath(String.format(TEXT_FIELD, fieldName));
    }

    protected WebPageElement getNumberFieldWebPageElement(final String fieldName) {
        return findByXpath(String.format(NUMBER_FIELD, fieldName));
    }

    protected WebPageElement getDropdownFieldWebPageElement(final String fieldName) {
        return findByXpath(String.format(DROPDOWN_FIELD, fieldName));
    }

    protected WebPageElement getAddressFieldWebPageElement(final String textFieldName) {
        return findByXpath(String.format(ADDRESS_FIELD, textFieldName));
    }

    public WebPageElement getSaveButton() {
        return findByText(SAVE_BUTTON);
    }

    protected WebPageElement getCompany() {
        return getTextFieldWebPageElement(ContactField.COMPANY.getName());
    }

    protected WebPageElement getSource() {
        return getDropdownFieldWebPageElement(ContactField.SOURCE.getName());
    }

    protected WebPageElement getTerritory() {
        return getDropdownFieldWebPageElement(ContactField.TERRITORY.getName());
    }

    protected WebPageElement getSalesOwner() {
        return getDropdownFieldWebPageElement(ContactField.SALES_OWNER.getName());
    }

    protected WebPageElement getTags() {
        return getTextFieldWebPageElement(ContactField.TAGS.getName());
    }

    protected WebPageElement getAddressLine1() {
        return getAddressFieldWebPageElement(ADDRESS_LINE_1);
    }

    protected WebPageElement getAddressLine2() {
        return getAddressFieldWebPageElement(ADDRESS_LINE_2);
    }

    protected WebPageElement getCity() {
        return getAddressFieldWebPageElement(CITY);
    }

    protected WebPageElement getState() {
        return getAddressFieldWebPageElement(STATE);
    }

    protected WebPageElement getCountry() {
        return getDropdownFieldWebPageElement(COUNTRY);
    }

    protected WebPageElement getPincode() {
        return getAddressFieldWebPageElement(PINCODE);
    }

    protected WebPageElement getFacebook() {
        return getTextFieldWebPageElement(ContactField.FACEBOOK.getName());
    }

    protected WebPageElement getTwitter() {
        return getTextFieldWebPageElement(ContactField.TWITTER.getName());
    }

    protected WebPageElement getLinkedIn() {
        return getTextFieldWebPageElement(ContactField.LINKED_IN.getName());
    }

    protected WebPageElement getDescription() {
        return getTextFieldWebPageElement(CompanyField.DESCRIPTION.getName());
    }

    protected WebPageElement getName() {
        return getTextFieldWebPageElement(CompanyField.NAME.getName());
    }

    protected WebPageElement getType() {
        return getTextFieldWebPageElement(DealField.TYPE.getName());
    }


    public void switchToAddForm(final String module) {
        // waitTillClickable(XPathBuilder.getXPathByText(module));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
        click(findByText(module));
    }

//   public void run(){
//       Collection<WebPageElement> dealOptions = findElementsByXpath("//*[@class='MuiAutocomplete-option MuiBox-root css-0']//child::p");
//
//       for (WebPageElement option : dealOptions) {
//           if (getText(option).equals(deal)) {
//               click(option);
//               break;
//           }
//       }
//   }
}
