package com.twozo.page.settings.data.fields.deal;

import com.twozo.page.settings.data.fields.AbstractDataField;
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
        final String titleDiv = getTitleDiv();

        return new SystemField(
                findByXpath(format(titleDiv, FieldElement.NON_DRAGGABLE)),
                findByXpath(format(titleDiv, XPathBuilder.getXPathByText(DealField.TITLE.getName()))),
                findByXpath(format(titleDiv, XPathBuilder.getXPathByText(DealField.TITLE.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(titleDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(titleDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getPipelineField() {
        final String pipelineDiv = getPipelineDiv();

        return new SystemField(
                findByXpath(format(pipelineDiv, FieldElement.NON_DRAGGABLE)),
                findByXpath(format(pipelineDiv, XPathBuilder.getXPathByText(DealField.PIPELINE.getName()))),
                findByXpath(format(pipelineDiv, XPathBuilder.getXPathByText(DealField.PIPELINE.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(pipelineDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(pipelineDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private DependableField getStageField() {
        final String stageDiv = getStageDiv();

        return new DependableField(
                findByXpath(format(stageDiv, XPathBuilder.getXPathByText(DealField.STAGE.getName()))),
                findByXpath(format(stageDiv, XPathBuilder.getXPathByText(DealField.STAGE.getFieldType()))));
    }

    private DependableField getWonReasonField() {
        final String wonReasonDiv = getWonReasonDiv();

        return new DependableField(
                findByXpath(format(wonReasonDiv, XPathBuilder.getXPathByText(DealField.WON_REASON.getName()))),
                findByXpath(format(wonReasonDiv, XPathBuilder.getXPathByText(DealField.WON_REASON.getFieldType()))));
    }

    private DependableField getLostReasonField() {
        final String lostReasonDiv = getLostReasonDiv();

        return new DependableField(
                findByXpath(format(lostReasonDiv, XPathBuilder.getXPathByText(DealField.LOST_REASON.getName()))),
                findByXpath(format(lostReasonDiv, XPathBuilder.getXPathByText(DealField.LOST_REASON.getFieldType()))));
    }

    private DependableField getDealClosedOnField() {
        final String dealClosedOnDiv = getDealClosedOnDiv();

        return new DependableField(
                findByXpath(format(dealClosedOnDiv, XPathBuilder.getXPathByText(DealField.DEAL_CLOSED_ON.getName()))),
                findByXpath(format(dealClosedOnDiv, XPathBuilder.getXPathByText(DealField.DEAL_CLOSED_ON.getFieldType()))));
    }

    private SystemField getPrimaryContactField() {
        final String primaryContactDiv = getPrimaryContactDiv();

        return new SystemField(
                findByXpath(format(primaryContactDiv, FieldElement.NON_DRAGGABLE)),
                findByXpath(format(primaryContactDiv, XPathBuilder.getXPathByText(DealField.PRIMARY_CONTACT.getName()))),
                findByXpath(format(primaryContactDiv, XPathBuilder.getXPathByText(DealField.PRIMARY_CONTACT.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(primaryContactDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(primaryContactDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getCompanyField() {
        final String companyDiv = getCompanyDiv();

        return new SystemField(
                findByXpath(format(companyDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(companyDiv, XPathBuilder.getXPathByText(DealField.COMPANY.getName()))),
                findByXpath(format(companyDiv, FieldTypePath.COMPANY)),
                isSelected(findByXpath(getPathOfSpecificCheckbox(companyDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                !isSelected(findByXpath(getPathOfSpecificCheckbox(companyDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getRelatedContactsField() {
        final String relatedContactsDiv = getRelatedContactsDiv();

        return new SystemField(
                findByXpath(format(relatedContactsDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(relatedContactsDiv, XPathBuilder.getXPathByText(DealField.RELATED_CONTACTS.getName()))),
                findByXpath(format(relatedContactsDiv, FieldTypePath.CONTACT)),
                isSelected(findByXpath(getPathOfSpecificCheckbox(relatedContactsDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                !isSelected(findByXpath(getPathOfSpecificCheckbox(relatedContactsDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getDealValueField() {
        final String dealValueDiv = getDealValueDiv();

        return new SystemField(
                findByXpath(format(dealValueDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(dealValueDiv, XPathBuilder.getXPathByText(DealField.DEAL_VALUE.getName()))),
                findByXpath(format(dealValueDiv, XPathBuilder.getXPathByText(DealField.DEAL_VALUE.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(dealValueDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                !isSelected(findByXpath(getPathOfSpecificCheckbox(dealValueDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getSalesOwnerField() {
        final String salesOwnerDiv = getSalesOwnerDiv();

        return new SystemField(
                findByXpath(format(salesOwnerDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(salesOwnerDiv, XPathBuilder.getXPathByText(DealField.SALES_OWNER.getName()))),
                findByXpath(format(salesOwnerDiv, XPathBuilder.getXPathByText(DealField.SALES_OWNER.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(salesOwnerDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(salesOwnerDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
    }

    private SystemField getProductQuantityField() {
        final String productQuantityDiv = getProductQuantityDiv();
        return new SystemField(
                findByXpath(format(productQuantityDiv, FieldElement.DRAGGABLE)),
                findByXpath(format(productQuantityDiv, XPathBuilder.getXPathByText(DealField.PRODUCT_QUANTITY.getName()))),
                findByXpath(format(productQuantityDiv, XPathBuilder.getXPathByText(DealField.PRODUCT_QUANTITY.getFieldType()))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(productQuantityDiv, FieldElement.ADD_VIEW_CHECKBOX))),
                isSelected(findByXpath(getPathOfSpecificCheckbox(productQuantityDiv, FieldElement.REQUIRED_CHECKBOX))),
                null);
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

        if (!checkSpecificElement(pipeline, FieldElement.NON_DRAGGABLE)) {
            return false;
        }

        if (!checkSpecificElement(pipeline, FieldTypePath.DROPDOWN)) {
            return false;
        }

        if (!checkSpecificElement(pipeline, XPathBuilder.getXPathByText("1"))) {
            return false;
        }
        final String stage = DealField.STAGE.getName();

        if (!checkSpecificElement(stage, FieldTypePath.DROPDOWN)) {
            return false;
        }
        if (!checkSpecificElement(stage, XPathBuilder.getXPathByText("0"))) {
            return false;
        }

        final String wonReason = DealField.WON_REASON.getName();

        if (!checkSpecificElement(wonReason, FieldTypePath.DROPDOWN)) {
            return false;
        }

        click(findByXpath(format(getFieldBlock(wonReason), XPathBuilder.getXPathByText("4"))));
        if (!checkWonReasonChoices()) {
            return false;
        }

        click(findByXpath(MAP.get("body")));

        final String lostReason = DealField.LOST_REASON.getName();
        if (!checkSpecificElement(lostReason, FieldTypePath.DROPDOWN)) {
            return false;
        }

        click(findByXpath(format(getFieldBlock(lostReason), XPathBuilder.getXPathByText("9"))));
        if (!checkLostReasonChoices()) {
            return false;
        }

        click(findByXpath(MAP.get("body")));

        return checkSpecificElement(DealField.DEAL_CLOSED_ON.getName(), FieldTypePath.DATE);
    }


    public boolean checkType() {
        final String type = DealField.TYPE.getName();

        if (isFieldPresent(type)) {
            addField(type);
        }

        if (!checkSpecificElement(type, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!checkSpecificElement(type, FieldTypePath.DROPDOWN)) {
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

        if (!checkSpecificElement(paymentStatus, FieldElement.DRAGGABLE)) {
            return false;
        }

        if (!checkSpecificElement(paymentStatus, FieldTypePath.DROPDOWN)) {
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
                getTitleDiv(),
                getPipelineDiv(),
                getPrimaryContactDiv(),
                getSalesOwnerDiv()
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
        final String[] mandatoryFields = new String[]{
                getTitleDiv(),
                getPipelineDiv(),
                getPrimaryContactDiv(),
                getSalesOwnerDiv()
        };
        unCheck(mandatoryFields);

        return true;
    }

    public Collection<String> getFieldsForSummary() {
        final Collection<String> fieldsNotToDisplay = List.of("Title", "Won Reason",
                "Lost Reason", "Deal Closed On", "Primary Contact", "Related Contacts", "Company", "Deal Value",
                "Rotting Days", "Status");

        return getFieldsForSummary(fieldsNotToDisplay);
    }
}
