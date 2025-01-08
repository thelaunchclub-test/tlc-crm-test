package com.twozo.crm.automation.page.settings.data.fields.deal;

import com.twozo.crm.automation.page.settings.data.fields.deal.field.DealField;
import com.twozo.crm.automation.page.settings.data.fields.field.*;
import com.twozo.crm.automation.page.xpath.XPathBuilder;
import com.twozo.crm.automation.page.settings.data.fields.AbstractDataField;
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
        return getFieldBlock(DealField.TITLE.getName());
    }

    public String getPipelineDiv() {
        return getFieldBlock(DealField.PIPELINE.getName());
    }

    public String getStageDiv() {
        return getFieldBlock(DealField.STAGE.getName());
    }

    public String getWonReasonDiv() {
        return getFieldBlock(DealField.WON_REASON.getName());
    }

    public String getLostReasonDiv() {
        return getFieldBlock(DealField.LOST_REASON.getName());
    }

    public String getDealClosedOnDiv() {
        return getFieldBlock(DealField.DEAL_CLOSED_ON.getName());
    }

    public String getPrimaryContactDiv() {
        return getFieldBlock(DealField.PRIMARY_CONTACT.getName());
    }

    public String getRelatedContactsDiv() {
        return getFieldBlock(DealField.RELATED_CONTACTS.getName());
    }

    public String getCompanyDiv() {
        return getFieldBlock(DealField.COMPANY.getName());
    }

    public String getDealValueDiv() {
        return getFieldBlock(DealField.DEAL_VALUE.getName());
    }

    public String getSalesOwnerDiv() {
        return getFieldBlock(DealField.SALES_OWNER.getName());
    }

    public String getProductQuantityDiv() {
        return getFieldBlock(DealField.PRODUCT_QUANTITY.getName());
    }

    private SystemField getTitleField() {
        final String title = DealField.TITLE.getName();

        return new SystemField(
                getDraggableElement(title),
                getFieldName(title),
                getFieldType(title, DealField.TITLE.getFieldType()),
                isSelected(getAddViewCheckboxOf(title)),
                isSelected(getRequiredCheckboxOf(title)),
                null
        );
    }

    private SystemField getPipelineField() {
        final String pipeline = DealField.PIPELINE.getName();

        return new SystemField(
                getDraggableElement(pipeline),
                getFieldName(pipeline),
                getFieldType(pipeline, DealField.PIPELINE.getFieldType()),
                isSelected(getAddViewCheckboxOf(pipeline)),
                isSelected(getRequiredCheckboxOf(pipeline)),
                null
        );
    }

    private DependableField getStageField() {
        final String stage = DealField.STAGE.getName();

        return new DependableField(
                getFieldName(stage),
                getFieldType(stage, DealField.STAGE.getFieldType())
        );
    }

    private DependableField getWonReasonField() {
        final String wonReason = DealField.WON_REASON.getName();

        return new DependableField(
                getFieldName(wonReason),
                getFieldType(wonReason, DealField.WON_REASON.getFieldType())
        );
    }

    private DependableField getLostReasonField() {
        final String lostReason = DealField.LOST_REASON.getName();

        return new DependableField(
                getFieldName(lostReason),
                getFieldType(lostReason, DealField.LOST_REASON.getFieldType())
        );
    }

    private DependableField getDealClosedOnField() {
        final String dealClosedOn = DealField.DEAL_CLOSED_ON.getName();

        return new DependableField(
                getFieldName(dealClosedOn),
                getFieldType(dealClosedOn, DealField.DEAL_CLOSED_ON.getFieldType())
        );
    }

    private SystemField getPrimaryContactField() {
        final String primaryContact = DealField.PRIMARY_CONTACT.getName();

        return new SystemField(
                getDraggableElement(primaryContact),
                getFieldName(primaryContact),
                getFieldType(primaryContact, DealField.PRIMARY_CONTACT.getFieldType()),
                isSelected(getAddViewCheckboxOf(primaryContact)),
                isSelected(getRequiredCheckboxOf(primaryContact)),
                null
        );
    }

    private SystemField getCompanyField() {
        final String company = DealField.COMPANY.getName();

        return new SystemField(
                getDraggableElement(company),
                getFieldName(company),
                findByXpath(format(getCompanyDiv(), FieldTypePath.COMPANY)),
                isSelected(getAddViewCheckboxOf(company)),
                !isSelected(getRequiredCheckboxOf(company)),
                null
        );
    }

    private SystemField getRelatedContactsField() {
        final String relatedContacts = DealField.RELATED_CONTACTS.getName();

        return new SystemField(
                getDraggableElement(relatedContacts),
                getFieldName(relatedContacts),
                findByXpath(format(getRelatedContactsDiv(), FieldTypePath.CONTACT)),
                isSelected(getAddViewCheckboxOf(relatedContacts)),
                !isSelected(getRequiredCheckboxOf(relatedContacts)),
                null
        );
    }

    private SystemField getDealValueField() {
        final String dealValue = DealField.DEAL_VALUE.getName();

        return new SystemField(
                getDraggableElement(dealValue),
                getFieldName(dealValue),
                getFieldType(dealValue, DealField.DEAL_VALUE.getFieldType()),
                isSelected(getAddViewCheckboxOf(dealValue)),
                !isSelected(getRequiredCheckboxOf(dealValue)),
                null
        );
    }

    private SystemField getSalesOwnerField() {
        final String salesOwner = DealField.SALES_OWNER.getName();

        return new SystemField(
                getDraggableElement(salesOwner),
                getFieldName(salesOwner),
                getFieldType(salesOwner, DealField.SALES_OWNER.getFieldType()),
                isSelected(getAddViewCheckboxOf(salesOwner)),
                isSelected(getRequiredCheckboxOf(salesOwner)),
                null
        );
    }

    private SystemField getProductQuantityField() {
        final String productQuantity = DealField.PRODUCT_QUANTITY.getName();

        return new SystemField(
                getDraggableElement(productQuantity),
                getFieldName(productQuantity),
                getFieldType(productQuantity, DealField.PRODUCT_QUANTITY.getFieldType()),
                isSelected(getAddViewCheckboxOf(productQuantity)),
                isSelected(getRequiredCheckboxOf(productQuantity)),
                null
        );
    }

    public boolean verifyActiveDealTab() {
        return isDisplayed(getActiveDealTab());
    }

    public boolean checkWonReasonChoices() {
        return areChoicesPresent(List.of(
                "Product feature",
                "Price",
                "Timing",
                "Others"));
    }

    public boolean checkLostReasonChoices() {
        return areChoicesPresent(List.of("Opted our rival",
                "Price is too high",
                "Junk Lead",
                "Not interested",
                "No Requirement",
                "Need only in future",
                "Product not satisfying",
                "No proper follow-up",
                "Appointment Missed"));
    }

    public boolean checkTypeChoices() {
        return areChoicesPresent(List.of("New Business",
                "Existing Business - Renewal",
                "Existing Business - Upgrade"));
    }

    public boolean checkPaymentStatusChoices() {
        return areChoicesPresent(List.of("Online",
                "Offline"));
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

        click(findByXpath(CRM_LOCATOR_REGISTRY.get("body")));

        final String lostReason = DealField.LOST_REASON.getName();
        if (!isFieldSpecificElementDisplayed(lostReason, FieldTypePath.DROPDOWN)) {
            return false;
        }

        click(findByXpath(format(getFieldBlock(lostReason), XPathBuilder.getXPathByText("9"))));
        if (!checkLostReasonChoices()) {
            return false;
        }

        click(findByXpath(CRM_LOCATOR_REGISTRY.get("body")));

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
    protected Collection<String> getDefaultFields() {
        return DealField.getDefaultFields();
    }

    @Override
    public List<String> getAllFields() {
        return DealField.getAllFields();
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
        uncheck(List.of(DealField.TITLE.getName(),
                DealField.PIPELINE.getName(),
                DealField.PRIMARY_CONTACT.getName(),
                DealField.SALES_OWNER.getName()));

        return true;
    }

    public Collection<String> getFieldsForSummary() {
        final Collection<String> fieldsNotToDisplay = List.of("Title", "Won Reason",
                "Lost Reason", "Deal Closed On", "Primary Contact", "Related Contacts", "Company", "Deal Value",
                "Rotting Days", "Status");

        return getFieldsForSummary(fieldsNotToDisplay);
    }
}
