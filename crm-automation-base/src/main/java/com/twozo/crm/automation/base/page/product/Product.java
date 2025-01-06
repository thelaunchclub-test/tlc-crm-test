package com.twozo.crm.automation.base.page.product;

import com.twozo.crm.automation.base.page.BasePage;
import com.twozo.web.driver.service.WebAutomationDriver;

public class Product extends BasePage {

    private static Product product;

    protected Product(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static Product getInstance(final WebAutomationDriver webAutomationDriver){

      //  if (Objects.isNull(product)) {
            product = new Product(webAutomationDriver);
        //}

        return product;
    }
}
