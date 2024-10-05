package com.twozo.page.settings.data.fields.contact;

import com.twozo.commons.exception.ErrorCode;

import com.twozo.page.settings.data.fields.AbstractDataField;
import com.twozo.page.settings.data.fields.contact.field.ContactField;
import com.twozo.page.settings.data.fields.field.Field;
import com.twozo.page.settings.data.fields.field.FieldElement;
import com.twozo.page.settings.data.fields.field.FieldTypePath;
import com.twozo.page.settings.data.fields.field.SystemField;
import com.twozo.page.url.settings.SettingsURL;
import com.twozo.page.xpath.XPathBuilder;

import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;
import com.twozo.web.error.code.WebDriverErrorCode;

import java.util.*;

/**
 * <p>
 * Manages contact-related fields and their behaviors in the data field settings. Provides methods to interact with
 * various contact fields. It also supports adding, editing, and validating the contact fields.
 * </p>
 *
 * @author Petchimuthu
 * @version 1.0
 */
public class ContactDataField extends AbstractDataField {

    private static ContactDataField contactDataField;

    protected ContactDataField(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);

//        if (!getURL().equals(SettingsURL.CONTACT_DATA_FIELDS)) {
//            throw ErrorCode.get(WebDriverErrorCode.EXPECTED_PAGE_NOT_FOUND, "exp page not found");
//        }
    }

    public static ContactDataField getInstance(final WebAutomationDriver webAutomationDriver) {
        contactDataField = new ContactDataField(webAutomationDriver);

        return contactDataField;
    }

    /**
     * <p>
     * Retrieves the XPath of the div element for the first name field.
     * </p>
     *
     * @return XPath of the first name field.
     */
    public String getFirstNameDiv() {
        return getFieldBlock(ContactField.FIRST_NAME);
    }

    public String getLastNameDiv() {
        return getFieldBlock(ContactField.LAST_NAME);
    }

    public String getEmailDiv() {
        return getFieldBlock(ContactField.EMAILS);
    }

    public String getPhonesDiv() {
        return getFieldBlock(ContactField.PHONES);
    }

    public String getCompanyDiv() {
        return getFieldBlock(ContactField.COMPANY);
    }

    public String getDesignationDiv() {
        return getFieldBlock(ContactField.DESIGNATION);
    }

    public String getSalesOwnerDiv() {
        return getFieldBlock(ContactField.SALES_OWNER);
    }

    public String getFacebookDiv() {
        return getFieldBlock(ContactField.FACEBOOK);
    }

    public String getTwitterDiv() {
        return getFieldBlock(ContactField.LINKED_IN);
    }

    private SystemField getFirstNameField() {
        final String firstNameDiv = getFirstNameDiv();

        return new SystemField(
                findByXpath(format(firstNameDiv, FieldElement.NON_DRAGGABLE)),
                findByXpath(format(firstNameDiv, XPathBuilder.getXPathByText(ContactField.FIRST_NAME.getName()))),
                findByXpath(format(firstNameDiv, XPathBuilder.getXPathByText(ContactField.FIRST_NAME.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(firstNameDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(firstNameDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getLastNameField() {
        final String lastNameDiv = getLastNameDiv();

        return new SystemField(
                findByXpath(format(lastNameDiv, FieldElement.NON_DRAGGABLE)),
                findByXpath(format(lastNameDiv, XPathBuilder.getXPathByText(ContactField.LAST_NAME.getName()))),
                findByXpath(format(lastNameDiv, XPathBuilder.getXPathByText(ContactField.LAST_NAME.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(lastNameDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                !isSelected(findByXpath(getPathOfSpecificCheckbox(lastNameDiv, FieldElement.REQUIRED_CHECKBOX))), null);
    }

    private SystemField getEmailsField() {
        final String emailDiv = getEmailDiv();

        return new SystemField(
                findByXpath(format(emailDiv, FieldElement.NON_DRAGGABLE)),
                findByXpath(format(emailDiv, XPathBuilder.getXPathByText(ContactField.EMAILS.getName()))),
                findByXpath(format(emailDiv, XPathBuilder.getXPathByText(ContactField.EMAILS.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(emailDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(emailDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getPhonesField() {
        final String phonesDiv = getPhonesDiv();

        return new SystemField(
                findByXpath(format(phonesDiv, FieldElement.NON_DRAGGABLE)),
                findByXpath(format(phonesDiv, XPathBuilder.getXPathByText(ContactField.PHONES.getName()))),
                findByXpath(format(phonesDiv, XPathBuilder.getXPathByText(ContactField.PHONES.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(phonesDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(phonesDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getCompanyField() {
        final String companyDiv = getCompanyDiv();

        return new SystemField(
                findByXpath(format(companyDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(companyDiv, XPathBuilder.getXPathByText(ContactField.COMPANY.getName()))),
                findByXpath(format(companyDiv, XPathBuilder.getXPathByText(ContactField.COMPANY.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(companyDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                !isSelected(findByXpath(getPathOfSpecificCheckbox(companyDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getDesignationField() {
        final String designationDiv = getDesignationDiv();

        return new SystemField(
                findByXpath(format(designationDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(designationDiv, XPathBuilder.getXPathByText(ContactField.DESIGNATION.getName()))),
                findByXpath(format(designationDiv, XPathBuilder.getXPathByText(ContactField.DESIGNATION.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(designationDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                !isSelected(findByXpath(getPathOfSpecificCheckbox(designationDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getSalesOwnerField() {
        final String salesOwnerDiv = getSalesOwnerDiv();

        return new SystemField(
                findByXpath(format(salesOwnerDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(salesOwnerDiv, XPathBuilder.getXPathByText(ContactField.SALES_OWNER.getName()))),
                findByXpath(format(salesOwnerDiv, XPathBuilder.getXPathByText(ContactField.SALES_OWNER.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(salesOwnerDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(salesOwnerDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getFacebookField() {
        final String facebookDiv = getFacebookDiv();
        return new SystemField(
                findByXpath(format(facebookDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(facebookDiv, XPathBuilder.getXPathByText(ContactField.FACEBOOK.getName()))),
                findByXpath(format(facebookDiv, XPathBuilder.getXPathByText(ContactField.FACEBOOK.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(facebookDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                !isSelected(findByXpath(getPathOfSpecificCheckbox(facebookDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getLinkedInField() {
        final String twitterDiv = getTwitterDiv();
        return new SystemField(
                findByXpath(format(twitterDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(twitterDiv, XPathBuilder.getXPathByText(ContactField.LINKED_IN.getName()))),
                findByXpath(format(twitterDiv, XPathBuilder.getXPathByText(ContactField.LINKED_IN.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(twitterDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                !isSelected(findByXpath(getPathOfSpecificCheckbox(twitterDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    public boolean verifyActiveContactTab() {
        return isDisplayed(getActiveContactTab());
    }

    public boolean emailOrPhoneShouldBeRequired() {
        return isSelected(findByXpath(getPathOfSpecificCheckbox(getEmailDiv(), FieldElement.REQUIRED_CHECKBOX))) ||
                isSelected(findByXpath(getPathOfSpecificCheckbox(getPhonesDiv(), FieldElement.REQUIRED_CHECKBOX)));
    }

    public boolean checkChoicesForSource() {
        final String[] sources = {
                "Web",
                "Organic Search",
                "Email",
                "Phone",
                "Chat",
                "Web Form",
                "Referral",
                "Direct",
                "Paid Search",
                "Blogs",
                "Social Media",
                "Display Ads",
                "Events",
                "Webinar"
        };

        final Collection<WebPageElement> choicesAsElements = findElementsByXpath("//*[@class='MuiBox-root css-173a8x7']//*[@class='css-h35jak']");
        final Collection<WebPageElement> draggableChoicesAsElements = findElementsByXpath("//*[@class='MuiBox-root css-173a8x7']//*[@class='css-1c02ymu']");
        final Collection<String> choices = new ArrayList<>();

        for (final WebPageElement choicesAsElement : choicesAsElements) {
            choices.add(getText(choicesAsElement));
        }

        for (final WebPageElement draggableChoicesAsElement : draggableChoicesAsElements) {
            choices.add(getText(draggableChoicesAsElement));
        }

        for (final String source : sources) {

            if (!choices.contains(source)) {
                return false;
            }
        }

        return true;
    }

    public boolean checkChoicesForTimeZone() {
        final String[] timeZones = {
                "(UTC+00:00) Africa/Abidjan",
                "(UTC+00:00) Africa/Accra",
                "(UTC+03:00) Africa/Addis_Ababa",
                "(UTC+01:00) Africa/Algiers",
                "(UTC+03:00) Africa/Asmara",
                "(UTC+03:00) Africa/Asmera",
                "(UTC+00:00) Africa/Bamako",
                "(UTC+01:00) Africa/Bangui",
                "(UTC+00:00) Africa/Banjul",
                "(UTC+00:00) Africa/Bissau",
                "(UTC+02:00) Africa/Blantyre",
                "(UTC+01:00) Africa/Brazzaville",
                "(UTC+02:00) Africa/Bujumbura",
                "(UTC+02:00) Africa/Cairo",
                "(UTC+00:00) Africa/Casablanca",
                "(UTC+01:00) Africa/Ceuta",
                "(UTC+00:00) Africa/Conakry",
                "(UTC+00:00) Africa/Dakar",
                "(UTC+03:00) Africa/Dar_es_Salaam",
                "(UTC+03:00) Africa/Djibouti",
                "(UTC+01:00) Africa/Douala",
                "(UTC+00:00) Africa/El_Aaiun",
                "(UTC+00:00) Africa/Freetown",
                "(UTC+02:00) Africa/Gaborone",
                "(UTC+02:00) Africa/Harare",
                "(UTC+02:00) Africa/Johannesburg",
                "(UTC+02:00) Africa/Juba",
                "(UTC+03:00) Africa/Kampala",
                "(UTC+02:00) Africa/Khartoum",
                "(UTC+02:00) Africa/Kigali",
                "(UTC+01:00) Africa/Kinshasa",
                "(UTC+01:00) Africa/Lagos",
                "(UTC+01:00) Africa/Libreville",
                "(UTC+00:00) Africa/Lome",
                "(UTC+01:00) Africa/Luanda",
                "(UTC+02:00) Africa/Lubumbashi",
                "(UTC+02:00) Africa/Lusaka",
                "(UTC+01:00) Africa/Malabo",
                "(UTC+02:00) Africa/Maputo",
                "(UTC+02:00) Africa/Maseru",
                "(UTC+02:00) Africa/Mbabane",
                "(UTC+03:00) Africa/Mogadishu",
                "(UTC+00:00) Africa/Monrovia",
                "(UTC+03:00) Africa/Nairobi",
                "(UTC+01:00) Africa/Ndjamena",
                "(UTC+01:00) Africa/Niamey",
                "(UTC+00:00) Africa/Nouakchott",
                "(UTC+00:00) Africa/Ouagadougou",
                "(UTC+01:00) Africa/Porto-Novo",
                "(UTC+00:00) Africa/Sao_Tome",
                "(UTC+00:00) Africa/Timbuktu",
                "(UTC+02:00) Africa/Tripoli",
                "(UTC+01:00) Africa/Tunis",
                "(UTC+02:00) Africa/Windhoek",
                "(UTC-09:00) America/Adak",
                "(UTC-09:00) America/Anchorage",
                "(UTC-04:00) America/Anguilla",
                "(UTC-04:00) America/Antigua",
                "(UTC-03:00) America/Araguaina",
                "(UTC-03:00) America/Argentina/Buenos_Aires",
                "(UTC-03:00) America/Argentina/Catamarca",
                "(UTC-03:00) America/Argentina/ComodRivadavia",
                "(UTC-03:00) America/Argentina/Cordoba",
                "(UTC-03:00) America/Argentina/Jujuy",
                "(UTC-03:00) America/Argentina/La_Rioja",
                "(UTC-03:00) America/Argentina/Mendoza",
                "(UTC-03:00) America/Argentina/Rio_Gallegos",
                "(UTC-03:00) America/Argentina/Salta",
                "(UTC-03:00) America/Argentina/San_Juan",
                "(UTC-03:00) America/Argentina/San_Luis",
                "(UTC-03:00) America/Argentina/Tucuman",
                "(UTC-03:00) America/Argentina/Ushuaia",
                "(UTC-04:00) America/Aruba",
                "(UTC-04:00) America/Asuncion",
                "(UTC-05:00) America/Atikokan",
                "(UTC-10:00) America/Atka",
                "(UTC-03:00) America/Bahia",
                "(UTC-06:00) America/Bahia_Banderas",
                "(UTC-04:00) America/Barbados",
                "(UTC-03:00) America/Belem",
                "(UTC-06:00) America/Belize",
                "(UTC-04:00) America/Blanc-Sablon",
                "(UTC-04:00) America/Boa_Vista",
                "(UTC-05:00) America/Bogota",
                "(UTC-07:00) America/Boise",
                "(UTC-03:00) America/Buenos_Aires",
                "(UTC-07:00) America/Cambridge_Bay",
                "(UTC-04:00) America/Campo_Grande",
                "(UTC-05:00) America/Cancun",
                "(UTC-04:00) America/Caracas",
                "(UTC-03:00) America/Catamarca",
                "(UTC-03:00) America/Cayenne",
                "(UTC-05:00) America/Cayman",
                "(UTC-06:00) America/Chicago",
                "(UTC-06:00) America/Chihuahua",
                "(UTC-07:00) America/Ciudad_Juarez",
                "(UTC-05:00) America/Coral_Harbour",
                "(UTC-03:00) America/Cordoba",
                "(UTC-06:00) America/Costa_Rica",
                "(UTC-07:00) America/Creston"
        };

        final Collection<WebPageElement> choices = findElementsByXpath("//*[@class='css-vb6e92']/div[2]/div/p");
        final Collection<String> timeZoneChoices = new LinkedList<>();

        for (final WebPageElement choice : choices) {
            timeZoneChoices.add(getText(choice));
        }

        for (final String timeZone : timeZones) {

            if (!timeZoneChoices.contains(timeZone)) {
                return false;
            }
        }

        return true;
    }

    public boolean checkChoicesForSubscriptionStatus() {
        final String[] options = {"Subscribed", "Unsubscribed", "Not Subscribed", "Reported as spam", "Bounced"};
        final Collection<String> choices = new ArrayList<>();
        final Collection<WebPageElement> choicesAsElements = findElementsByXpath("//*[@class='css-h35jak']");

        for (final WebPageElement choicesAsElement : choicesAsElements) {
            choices.add(getText(choicesAsElement));
        }

        for (final String option : options) {

            if (!choices.contains(option)) {
                return false;
            }
        }

        return true;
    }

    public boolean checkChoicesForSubscriptionTypes() {
        final String[] options = {"Newsletter", "Promotional", "Product updates", "Conference and events", "Non marketing emails from our company"};
        final Collection<String> choices = new ArrayList<>();
        final Collection<WebPageElement> choicesAsElements = findElementsByXpath("//*[@class='css-evarem']");

        for (final WebPageElement choicesAsElement : choicesAsElements) {
            choices.add(getText(choicesAsElement));
        }

        for (final String option : options) {

            if (!choices.contains(option)) {
                return false;
            }
        }

        return true;
    }

    public boolean checkChoicesForUnsubscribeReason() {
        final String[] options = {"I no longer want to receive emails from you", "I receive too many emails from you",
                "The emails are inappropriate", "The emails are spam", "Other unsubscribeReason"};

        final Collection<String> choices = new ArrayList<>();
        final Collection<WebPageElement> choicesAsElements = findElementsByXpath("//*[@class='css-vb6e92']");

        for (final WebPageElement choicesAsElement : choicesAsElements) {
            choices.add(getText(choicesAsElement));
        }

        for (final String option : options) {

            if (!choices.contains(option)) {
                return false;
            }
        }

        return true;
    }

    public boolean checkSubscriptionStatus() {
        final String subscriptionStatus = ContactField.SUBSCRIPTION_STATUS.getName();
        final String fiveChoices = XPathBuilder.getXPathByText("5");

        if (!isFieldPresent(subscriptionStatus)) {
            addField(subscriptionStatus);
        }
        try {
            Thread.sleep(5000);
        } catch (Exception exception) {

        }

        checkSpecificElement(subscriptionStatus, FieldElement.DRAGGABLE);
        checkSpecificElement(subscriptionStatus, FieldTypePath.DROPDOWN);
        click(findByXpath(format(getFieldBlock(subscriptionStatus), fiveChoices)));
        checkChoicesForSubscriptionStatus();
        refresh();
        final String subscriptionTypes = ContactField.SUBSCRIPTION_TYPES.getName();

        checkSpecificElement(subscriptionTypes, FieldTypePath.MULTI_SELECT);
        click(findByXpath(format(getFieldBlock(subscriptionTypes), fiveChoices)));
        checkChoicesForSubscriptionTypes();
        refresh();
        final String unsubscribeReason = ContactField.UNSUBSCRIBE_TYPES.getName();

        checkSpecificElement(unsubscribeReason, FieldTypePath.DROPDOWN);
        click(findByXpath(format(getFieldBlock(unsubscribeReason), fiveChoices)));
        checkChoicesForUnsubscribeReason();
        refresh();
        final String otherUnsubscribeReason = ContactField.OTHER_UNSUBSCRIBE_REASON.getName();

        checkSpecificElement(otherUnsubscribeReason, FieldTypePath.TEXT);

        return true;
    }

    public boolean checkLifecycleStage() {
        final String lifecycleStage = ContactField.LIFECYCLE_STAGE.getName();
        final String fourChoices = XPathBuilder.getXPathByText("4");

        if (!isFieldPresent(lifecycleStage)) {
            addField(lifecycleStage);
        }

        refresh();
        try {
            Thread.sleep(10000);
        } catch (Exception exception) {

        }

        checkSpecificElement(lifecycleStage, FieldElement.DRAGGABLE);
        checkSpecificElement(lifecycleStage, FieldTypePath.DROPDOWN);
        checkSpecificElement(lifecycleStage, fourChoices);

        return true;
    }

    public boolean checkSource() {
        final String source = ContactField.SOURCE.getName();
        final String fourteenChoices = XPathBuilder.getXPathByText("14");

        if (!isFieldPresent(source)) {
            addField(source);
        }

        refresh();
        try {
            Thread.sleep(10000);
        } catch (Exception exception) {

        }


        checkSpecificElement(source, FieldElement.DRAGGABLE);
        checkSpecificElement(source, FieldTypePath.DROPDOWN);
        click(findByXpath(format(getFieldBlock(source), fourteenChoices)));
        checkChoicesForSource();

        return true;
    }

    public boolean checkTimezone() {
        final String timeZone = ContactField.TIME_ZONE.getName();
        final String hundredChoices = XPathBuilder.getXPathByText("100");

        if (!isFieldPresent(timeZone)) {
            addField(timeZone);
        }

        refresh();

        try {
            Thread.sleep(10000);
        } catch (Exception exception) {

        }

        checkSpecificElement(timeZone, FieldElement.DRAGGABLE);
        checkSpecificElement(timeZone, FieldTypePath.DROPDOWN);
        click(findByXpath(format(getFieldBlock(timeZone), hundredChoices)));
        checkChoicesForTimeZone();

        return true;
    }

    @Override
    public Collection<Field> getDefaultFields() {
        return ContactField.getDefaultFields();
    }

    @Override
    public Field[] getAllFields() {
        return ContactField.values();
    }

    @Override
    public Collection<String> getMandatoryFields() {
        return Arrays.asList(
                getFirstNameDiv(),
                getLastNameDiv(),
                getEmailDiv(),
                getPhonesDiv(),
                getSalesOwnerDiv()
        );
    }

    @Override
    public boolean verifyNonDraggableFields() {
        return isNonDraggableIconDisplayed(getFirstNameDiv()) &&
                isNonDraggableIconDisplayed(getLastNameDiv()) &&
                isNonDraggableIconDisplayed(getPhonesDiv()) &&
                isNonDraggableIconDisplayed(getEmailDiv());
    }

    @Override
    public Collection<Record> getDefaultSystemFieldElements() {
        return List.of(
                getFirstNameField(),
                getLastNameField(),
                getEmailsField(),
                getPhonesField(),
                getCompanyField(),
                getDesignationField(),
                getSalesOwnerField(),
                getFacebookField(),
                getLinkedInField()
        );
    }

    @Override
    public boolean isDefaultFieldsVisibleInSummary() {
        final Collection<Field> summaryDefaultFields = List.of(ContactField.EMAILS, ContactField.PHONES, ContactField.SALES_OWNER);

        for (final Field summaryDefaultField : summaryDefaultFields) {

            if (!isDisplayed(findByXpath(format("//*[@class='css-itno5t']",
                    XPathBuilder.getXPathByText(summaryDefaultField.getName()))))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean uncheckMandatoryFields() {
        final String[] mandatoryFields = new String[]{
                getFirstNameDiv(),
                getEmailDiv(),
                getPhonesDiv(),
                getSalesOwnerDiv()
        };
        unCheck(mandatoryFields);

        return true;
    }

    @Override
    public Collection<String> getFieldsForAddViewAndRequired(final String addViewOrRequired) {
        final Collection<String> fieldsPresent = new ArrayList<>();

        int count = 0;
        final Collection<WebPageElement> elementsByXpath = findElementsByXpath("//*[@class='MuiBox-root css-19idom']");

        for (final WebPageElement webPageElement : elementsByXpath) {
            count++;
        }

        for (int i = 1; i <= count; i++) {

            final String fieldBlock = String.format(FieldElement.BLOCK, i);
            final WebPageElement fieldElement = findByXpath(getPathOfSpecificCheckbox
                    (fieldBlock, addViewOrRequired));

            if (isSelected(fieldElement)) {
                fieldsPresent.add(getText(findByXpath(String.format("(%s%s%s)", "((", fieldBlock, "/div)[1])//*[@class='MuiTypography-root MuiTypography-body1 MuiTypography-noWrap css-10vldmf']"))));
            }
        }

        return fieldsPresent;
    }

    public Collection<String> getFieldsForSummary() {
        final Collection<String> fieldsNotToDisplay = List.of("First Name", "Last Name", "LinkedIn", "Facebook",
                "Designation", "Company", "Unsubscribe Reason", "Other Unsubscribe reason");

        return getFieldsForSummary(fieldsNotToDisplay);
    }
}

