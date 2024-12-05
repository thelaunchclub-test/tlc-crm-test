package com.twozo.page.contact;

import com.twozo.page.add.form.AddForm;
import com.twozo.page.settings.data.fields.contact.field.ContactField;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ContactAddForm extends AddForm {

    private static ContactAddForm addContact;

    protected ContactAddForm(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static ContactAddForm getInstance(final WebAutomationDriver webAutomationDriver) {
        addContact = new ContactAddForm(webAutomationDriver);

        return addContact;
    }

    private WebPageElement getFirstName() {
        return getTextFieldWebPageElement(ContactField.FIRST_NAME.getName());
    }

    private WebPageElement getLastName() {
        return getTextFieldWebPageElement(ContactField.LAST_NAME.getName());
    }

    private WebPageElement getEmailId() {
        return getTextFieldWebPageElement(ContactField.EMAILS.getName());
    }

    private WebPageElement getPhone() {
        return getNumberFieldWebPageElement(ContactField.PHONES.getName());
    }

    private WebPageElement getDesignation() {
        return getTextFieldWebPageElement(ContactField.DESIGNATION.getName());
    }

    private WebPageElement getLifecycleStage() {
        return getDropdownFieldWebPageElement(ContactField.LIFECYCLE_STAGE.getName());
    }

    private WebPageElement getDepartment() {
        return getTextFieldWebPageElement(ContactField.DEPARTMENT.getName());
    }

    private WebPageElement getSubscriptionStatus() {
        return getTextFieldWebPageElement(ContactField.SUBSCRIPTION_STATUS.getName());
    }

//    private WebPageElement getSubscriptionTypes() {
//        return getTextFieldWebPageElement(ContactField.SUBSCRIPTION_TYPES.getName());
//    }

    private WebPageElement getTimeZone() {
        return getAddressFieldWebPageElement(ContactField.TIME_ZONE.getName());
    }

    private WebPageElement getAddEmailButton() {
        return findByXpath(MAP.get("crm.add.form.contact.add.email"));
    }

    private WebPageElement getAddPhoneButton() {
        return findByXpath(MAP.get("crm.add.form.contact.add.phone"));
    }


    private void setFirstName(final ContactForm contactForm) {
        send(getFirstName(), contactForm.getFirstName());
    }

    private void setLastName(final ContactForm contactForm) {
        send(getLastName(), contactForm.getLastName());
    }

    private void setEmail(final ContactForm contactForm) {
        final List<Map<String, String>> emails = contactForm.getEmails();
        final int emailCount = emails.size();

        if (emailCount == 1) {
            final Map<String, String> emailData = emails.get(0);

            send(findByXpath(String.format(MAP.get("crm.add.form.contact.specific.email.field"), 1)), emailData.get("email"));
            final WebPageElement emailType = findByXpath(String.format(MAP.get("crm.add.form.contact.specific.email.type"), 1));
            final String type = emailData.get("type");

            if (!Objects.equals(getText(emailType), type)) {
                click(emailType);
                dropdown(type);
            }
        } else {
            for (int i = 1; i < emailCount; i++) {
                click(getAddEmailButton());
            }

            for (int i = 1; i <= emailCount; i++) {
                final Map<String, String> emailData = emails.get(i - 1);

                send(findByXpath(String.format(MAP.get("crm.add.form.contact.specific.email.field"), i)), emailData.get("email"));
                final WebPageElement emailType = findByXpath(String.format(MAP.get("crm.add.form.contact.specific.email.type"), i));
                final String type = emailData.get("type");

                if (!Objects.equals(getText(emailType), type)) {
                    click(emailType);
                    dropdown(type);
                }
            }
        }
    }

    private void setPhone(final ContactForm contactForm) {
        final List<Map<String, String>> phones = contactForm.getPhones();
        final int phoneCount = phones.size();

        if (phoneCount == 1) {
            final Map<String, String> phoneData = phones.get(0);

            send(findByXpath(String.format(MAP.get("crm.add.form.contact.specific.phone.field"), 1)), phoneData.get("phone"));
            final WebPageElement phoneType = findByXpath(String.format(MAP.get("crm.add.form.contact.specific.phone.type"), 1));
            final String type = phoneData.get("type");

            if (!Objects.equals(getText(phoneType), type)) {
                click(phoneType);
                dropdown(type);
            }
        } else {
            for (int i = 1; i < phoneCount; i++) {
                click(getAddPhoneButton());
            }

            for (int i = 1; i <= phoneCount; i++) {
                final Map<String, String> phoneData = phones.get(i - 1);

                send(findByXpath(String.format(MAP.get("crm.add.form.contact.specific.phone.field"), i)), phoneData.get("phone"));
                final WebPageElement phoneType = findByXpath(String.format(MAP.get("crm.add.form.contact.specific.phone.type"), i));
                final String type = phoneData.get("type");

                if (!Objects.equals(getText(phoneType), type)) {
                    click(phoneType);
                    dropdown(type);
                }
            }
        }
    }


    public void fill(final ContactForm contactForm) {
        //waitTillVisible("//*[@class='jss3 MuiBox-root css-0']");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
//        final String salesOwner = contactForm.getSalesOwner();
//
//        sendFirstName(contactForm);
//        sendLastName(contactForm);
//        sendEmail(contactForm);
//        sendPhone(contactForm);

//        send(getCompany(), contactForm.getCompany());
//
//        final Collection<WebPageElement> company = findElementsByXpath("//*[@class='MuiAutocomplete-option MuiBox-root css-0']//child::p");
//
//        for (final WebPageElement option : company) {
//            String optionText = getText(option);
//
//            if (optionText.equals(contactForm.getCompany())) {
//                click(option);
//                break;
//            }
//        }

//
//        send(getDesignation(), contactForm.getDesignation());
//
//        if (!Objects.equals(getText(getSalesOwner()), salesOwner)) {
//            click(getSalesOwner());
//            dropdown(salesOwner);
//        }
//
//        click(getLifecycleStage());
//        dropdown(contactForm.getLifecycleStage());
//        click(getSource());
//        dropdown(contactForm.getSource());
//        click(getTerritory());
//        dropdown(contactForm.getTerritory());
//
//        for (int i = 0; i < contactForm.getTags().size(); i++) {
//            send(getTags(), contactForm.getTags().get(i));
//            mouseActions.keyDown(Keys.ENTER).keyUp(Keys.CONTROL).perform();
//        }
//
//        final Collection<WebPageElement> tag = findElementsByXpath("//*[@class='MuiAutocomplete-option MuiBox-root css-0']//child::p");
//
//        for (final WebPageElement option : tag) {j
//            String optionText = getText(option);
//
//            if (optionText.equals(contactForm.getTags())) {
//                click(option);
//                break;
//            }
//        }
//
//        chooseDate(ContactField.DATE_OF_BIRTH.getName(), contactForm.getDateOfBirth());
//        send(getDepartment(), contactForm.getDepartment());
//
//        final ContactForm.Address address = contactForm.getAddress();
//
//        send(getAddressLine1(), address.getAddressLine1());
//        send(getAddressLine2(), address.getAddressLine2());
//        send(getCity(), address.getCity());
//        send(getState(), address.getState());
//        click(getCountry());
//        dropdown(address.getCity());
//        send(getPincode(), address.getPincode());
//        send(getFacebook(), contactForm.getFacebook());
//        send(getTwitter(), contactForm.getTwitter());
//        send(getLinkedIn(), contactForm.getLinkedin());
//        send(getSubscriptionStatus(), contactForm.getSubscriptionStatus());
//        click(getTimeZone());
//        dropdown(contactForm.getTimeZone());
//        click(getSaveButton());

        setFirstName(contactForm);
        setLastName(contactForm);
    }


}
