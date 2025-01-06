package com.twozo.crm.automation.base.page.deal;

import com.twozo.crm.automation.base.page.add.form.AddForm;
import com.twozo.crm.automation.base.page.settings.data.fields.deal.field.DealField;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

public class DealAddForm extends AddForm {

    private static DealAddForm addDeal;

    public static DealAddForm getInstance(final WebAutomationDriver webAutomationDriver) {
        addDeal = new DealAddForm(webAutomationDriver);

        return addDeal;
    }


    protected DealAddForm(WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    @Override
    protected String[] getAutoGeneratingFields() {
        return new String[0];
    }

    @Override
    protected String[] getAddFormSystemFields() {
        return new String[0];
    }

    private WebPageElement getTitle() {
        return getTextFieldWebPageElement(DealField.TITLE.getName());
    }

    private WebPageElement getPipeline() {
        return getDropdownFieldWebPageElement(DealField.PIPELINE.getName());
    }

    private WebPageElement getStage() {
        return getDropdownFieldWebPageElement(DealField.STAGE.getName());
    }

    private WebPageElement getPrimaryContact() {
        return getTextFieldWebPageElement(DealField.PRIMARY_CONTACT.getName());
    }

    private WebPageElement getRelatedContact() {
        return getTextFieldWebPageElement(DealField.RELATED_CONTACTS.getName());
    }

    private WebPageElement getDealValue() {
        return getTextFieldWebPageElement(DealField.DEAL_VALUE.getName());
    }

    private WebPageElement getExpectedCloseDate() {
        return getTextFieldWebPageElement(DealField.EXPECTED_CLOSE_DATE.getName());
    }

    private WebPageElement getPaymentStatus() {
        return getTextFieldWebPageElement(DealField.PAYMENT_STATUS.getName());
    }
}
