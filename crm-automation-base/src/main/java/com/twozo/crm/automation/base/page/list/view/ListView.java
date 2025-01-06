package com.twozo.crm.automation.base.page.list.view;

import com.twozo.crm.automation.base.page.BasePage;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.ArrayList;
import java.util.List;

public class ListView extends BasePage {

    private static ListView listView;

    public ListView(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static ListView getInstance(final WebAutomationDriver webAutomationDriver) {
        listView = new ListView(webAutomationDriver);

        return listView;
    }

    public boolean isListEmpty() {
        waitTillVisible(CRM_LOCATOR_REGISTRY.get("tbody"));

        try {
            return isDisplayed(findByXpath(CRM_LOCATOR_REGISTRY.get("crm.list.view.empty")));
        } catch (Exception exception) {
            return false;
        }
    }

    public int getCount() {

        if (isListEmpty()) {
            return 0;
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            return findElementsByXpath(CRM_LOCATOR_REGISTRY.get("crm.list.view.count")).size();
        }
    }

    public int getFieldPositionInColumnSettings(final String fieldName) {
        final List<WebPageElement> elements = new ArrayList<>(
                findElementsByXpath(CRM_LOCATOR_REGISTRY.get("crm.column.settings.field.names"))
        );

        int position = 0;

        for (final WebPageElement element : elements) {

            if (getText(element).equals(fieldName)) {
                enableFieldInColumnSettings(fieldName);

                return position + 1;
            }
            position++;
        }

        return -1;
    }

    public String getFieldData(final int listNumber, final int fieldPosition) {
        refresh();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {

        }
        return getText(findByXpath(formatTwoStrings(String.format(CRM_LOCATOR_REGISTRY.get("crm.list.view.specific.row"), listNumber),
                String.format(CRM_LOCATOR_REGISTRY.get("crm.list.view.specific.field.data"), fieldPosition + 1))));
    }
}
