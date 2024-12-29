package com.twozo.page.settings.data.fields.deal;

import com.twozo.page.settings.data.fields.AbstractDataField;
import com.twozo.page.settings.data.fields.company.field.CompanyField;
import com.twozo.page.settings.data.fields.deal.field.DealField;
import com.twozo.page.settings.data.fields.field.*;
import com.twozo.page.xpath.XPathBuilder;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DealDataField extends AbstractDataField {

    private static DealDataField deal;

    protected DealDataField(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static DealDataField getInstance(final WebAutomationDriver webAutomationDriver) {
        deal = new DealDataField(webAutomationDriver);

        return deal;
    }

    public String getTitleDiv() {
        return getFieldBlock(DealField.TITLE);
    }

    public String getPipelineDiv() {
        return getFieldBlock(DealField.PIPELINE);
    }

    public String getStageDiv() {
        return getFieldBlock(DealField.STAGE);
    }

    public String getWonReasonDiv() {
        return getFieldBlock(DealField.WON_REASON);
    }

    public String getLostReasonDiv() {
        return getFieldBlock(DealField.LOST_REASON);
    }

    public String getDealClosedOnDiv() {
        return getFieldBlock(DealField.DEAL_CLOSED_ON);
    }

    public String getPrimaryContactDiv() {
        return getFieldBlock(DealField.PRIMARY_CONTACT);
    }

    public String getRelatedContactsDiv() {
        return getFieldBlock(DealField.RELATED_CONTACTS);
    }

    public String getCompanyDiv() {
        return getFieldBlock(DealField.COMPANY);
    }

    public String getDealValueDiv() {
        return getFieldBlock(DealField.DEAL_VALUE);
    }

    public String getSalesOwnerDiv() {
        return getFieldBlock(DealField.SALES_OWNER);
    }

    public String getProductQuantityDiv() {
        return getFieldBlock(DealField.PRODUCT_QUANTITY);
    }

    private SystemField getTitleField() {
        final String title = DealField.TITLE.getName();

        return new SystemField(
                getDraggableElement(DealField.TITLE),
                getFieldName(DealField.TITLE),
                getFieldType(DealField.TITLE),
                isSelected(getAddViewCheckboxOf(title)
                ), isSelected(getRequiredCheckboxOf(title)), null);
    }

    private SystemField getPipelineField() {
        final String pipeline = DealField.PIPELINE.getName();

        return new SystemField(
                getDraggableElement(DealField.PIPELINE),
                getFieldName(DealField.PIPELINE),
                getFieldType(DealField.PIPELINE),
                isSelected(getAddViewCheckboxOf(pipeline)),
                isSelected(getRequiredCheckboxOf(pipeline)),
                null
        );
    }

    private DependableField getStageField() {
        return new DependableField(
                getFieldName(DealField.STAGE),
                getFieldType(DealField.STAGE)
        );
    }

    private DependableField getWonReasonField() {
        return new DependableField(
                getFieldName(DealField.WON_REASON),
                getFieldType(DealField.WON_REASON)
        );
    }

    private DependableField getLostReasonField() {
        return new DependableField(
                getFieldName(DealField.LOST_REASON),
                getFieldType(DealField.LOST_REASON)
        );
    }

    private DependableField getDealClosedOnField() {
        return new DependableField(
                getFieldName(DealField.DEAL_CLOSED_ON),
                getFieldType(DealField.DEAL_CLOSED_ON)
        );
    }

    private SystemField getPrimaryContactField() {
        final String primaryContactName = DealField.PRIMARY_CONTACT.getName();

        return new SystemField(
                getDraggableElement(DealField.PRIMARY_CONTACT),
                getFieldName(DealField.PRIMARY_CONTACT),
                getFieldType(DealField.PRIMARY_CONTACT),
                isSelected(getAddViewCheckboxOf(primaryContactName)),
                isSelected(getRequiredCheckboxOf(primaryContactName)),
                null
        );
    }

    private SystemField getCompanyField() {
        final String company = DealField.COMPANY.getName();

        return new SystemField(
                getDraggableElement(DealField.COMPANY),
                getFieldName(DealField.COMPANY),
                findByXpath(format(getCompanyDiv(), FieldTypePath.COMPANY)),
                isSelected(getAddViewCheckboxOf(company)),
                !isSelected(getRequiredCheckboxOf(company)),
                null
        );
    }

    private SystemField getRelatedContactsField() {
        final String relatedContacts = DealField.RELATED_CONTACTS.getName();

        return new SystemField(
                getDraggableElement(DealField.RELATED_CONTACTS),
                getFieldName(DealField.RELATED_CONTACTS),
                findByXpath(format(getRelatedContactsDiv(), FieldTypePath.CONTACT)),
                isSelected(getAddViewCheckboxOf(relatedContacts)),
                !isSelected(getRequiredCheckboxOf(relatedContacts)),
                null
        );
    }

    private SystemField getDealValueField() {
        final String dealValue = DealField.DEAL_VALUE.getName();

        return new SystemField(
                getDraggableElement(DealField.DEAL_VALUE),
                getFieldName(DealField.DEAL_VALUE),
                getFieldType(DealField.DEAL_VALUE),
                isSelected(getAddViewCheckboxOf(dealValue)),
                !isSelected(getRequiredCheckboxOf(dealValue)),
                null
        );
    }

    private SystemField getSalesOwnerField() {
        final String salesOwner = DealField.SALES_OWNER.getName();

        return new SystemField(
                getDraggableElement(DealField.SALES_OWNER),
                getFieldName(DealField.SALES_OWNER),
                getFieldType(DealField.SALES_OWNER),
                isSelected(getAddViewCheckboxOf(salesOwner)),
                isSelected(getRequiredCheckboxOf(salesOwner)),
                null
        );
    }

    private SystemField getProductQuantityField() {
        final String productQuantity = DealField.PRODUCT_QUANTITY.getName();

        return new SystemField(
                getDraggableElement(DealField.PRODUCT_QUANTITY),
                getFieldName(DealField.PRODUCT_QUANTITY),
                getFieldType(DealField.PRODUCT_QUANTITY),
                isSelected(getAddViewCheckboxOf(productQuantity)),
                isSelected(getRequiredCheckboxOf(productQuantity)),
                null
        );
    }

    public boolean verifyActiveDealTab() {
        return isDisplayed(getActiveDealTab());
    }

    public boolean checkWonReasonChoices() {
        final String[] wonReasonChoices = {
                "Product feature",
                "Price",
                "Timing",
                "Others",
        };

        return areChoicesPresent(wonReasonChoices);
    }

    public boolean checkLostReasonChoices() {
        String[] lostReasonChoices = {
                "Opted our rival",
                "Price is too high",
                "Junk Lead",
                "Not interested",
                "No Requirement",
                "Need only in future",
                "Product not satisfying",
                "No proper follow-up",
                "Appointment Missed"
        };

        return areChoicesPresent(lostReasonChoices);
    }

    public boolean checkTypeChoices() {
        final String[] typeChoices = {
                "New Business",
                "Existing Business - Renewal",
                "Existing Business - Upgrade"
        };

        return areChoicesPresent(typeChoices);
    }

    public boolean checkPaymentStatusChoices() {
        final String[] paymentStatus = {
                "Online",
                "Offline",
        };

        return areChoicesPresent(paymentStatus);
    }


    public boolean checkPipeline() {
        final String pipeline = DealField.PIPELINE.getName();

        if (!isFieldPresent(pipeline)) {
            addField(pipeline);
        }

        if (!isFieldSpecificElementDisplayed(pipeline, FieldElement.NON_DRAGGABLE)) {
            return false;
        }

        if (!isFieldSpecificElementDisplayed(pipeline, FieldTypePath.DROPDOWN)) {
            return false;
        }

        if (!isFieldSpecificElementDisplayed(pipeline, XPathBuilder.getXPathByText("1"))) {
            return false;
        }
        final String stage = DealField.STAGE.getName();

        if (!isFieldSpecificElementDisplayed(stage, FieldTypePath.DROPDOWN)) {
            return false;
        }
        if (!isFieldSpecificElementDisplayed(stage, XPathBuilder.getXPathByText("0"))) {
            return false;
        }

        final String wonReason = DealField.WON_REASON.getName();

        if (!isFieldSpecificElementDisplayed(wonReason, FieldTypePath.DROPDOWN)) {
            return false;
        }

        click(findByXpath(format(getFieldBlock(wonReason), XPathBuilder.getXPathByText("4"))));
        if (!checkWonReasonChoices()) {
            return false;
        }

        click(findByXpath(LOCATORS.get("body")));

        final String lostReason = DealField.LOST_REASON.getName();
        if (!isFieldSpecificElementDisplayed(lostReason, FieldTypePath.DROPDOWN)) {
            return false;
        }

        click(findByXpath(format(getFieldBlock(lostReason), XPathBuilder.getXPathByText("9"))));
        if (!checkLostReasonChoices()) {
            return false;
        }

        click(findByXpath(LOCATORS.get("body")));

        return isFieldSpecificElementDisplayed(DealField.DEAL_CLOSED_ON.getName(), FieldTypePath.DATE);
    }


    public boolean checkType() {
        final String type = DealField.TYPE.getName();

        if (isFieldPresent(type)) {
            addField(type);
        }

        if (!isFieldSpecificElementDisplayed(type, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!isFieldSpecificElementDisplayed(type, FieldTypePath.DROPDOWN)) {
            return false;
        }
        click(findByXpath(format(type, XPathBuilder.getXPathByText("3"))));

        return checkTypeChoices();
    }

    public boolean checkPaymentStatus() {
        final String paymentStatus = DealField.PAYMENT_STATUS.getName();

        if (!isFieldPresent(paymentStatus)) {
            addField(paymentStatus);
        }

        if (!isFieldSpecificElementDisplayed(paymentStatus, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!isFieldSpecificElementDisplayed(paymentStatus, FieldTypePath.DROPDOWN)) {
            return false;
        }
        click(findByXpath(format(paymentStatus, XPathBuilder.getXPathByText("2"))));

        return checkPaymentStatusChoices();
    }

    public void switchToSummary() {
        final WebPageElement moveToSummary = findByXpath("(//*[@class='MuiBox-root css-19idom'])[1]//child::div[@class='css-1ivgi17']");

        click(moveToSummary);
    }

    @Override
    protected Collection<Field> getDefaultFields() {
        return DealField.getDefaultFields();
    }

    @Override
    public Field[] getAllFields() {
        return DealField.values();
    }

    @Override
    protected List<String> getMandatoryFields() {
        return Arrays.asList(
                DealField.TITLE.getName(),
                DealField.PIPELINE.getName(),
                DealField.PRIMARY_CONTACT.getName(),
                DealField.SALES_OWNER.getName()
        );
    }

    @Override
    public boolean verifyNonDraggableFields() {
        return isNonDraggableIconDisplayed(getTitleDiv()) &&
                isNonDraggableIconDisplayed(getPipelineDiv()) &&
                isNonDraggableIconDisplayed(getPrimaryContactDiv());
    }

    @Override
    protected Collection<Record> getDefaultSystemFieldElements() {
        return List.of(getTitleField(), getPipelineField(), getStageField(), getWonReasonField(), getLostReasonField(),
                getDealClosedOnField(), getPrimaryContactField(), getRelatedContactsField(), getCompanyField(),
                getDealValueField(), getSalesOwnerField(), getProductQuantityField());
    }

    @Override
    public boolean isDefaultFieldsVisibleInSummary() {
        final List<Field> summaryDefaultFields = List.of(DealField.SALES_OWNER, DealField.WON_REASON, DealField.DEAL_CLOSED_ON,
                DealField.PRODUCT_QUANTITY);

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
        final Field[] mandatoryFields = new DealField[]{
                DealField.TITLE,
                DealField.PIPELINE,
                DealField.PRIMARY_CONTACT,
                DealField.SALES_OWNER
        };
        uncheck(mandatoryFields);

        return true;
    }

    public Collection<String> getFieldsForSummary() {
        final Collection<String> fieldsNotToDisplay = List.of("Title", "Won Reason",
                "Lost Reason", "Deal Closed On", "Primary Contact", "Related Contacts", "Company", "Deal Value",
                "Rotting Days", "Status");

        return getFieldsForSummary(fieldsNotToDisplay);
    }
}
