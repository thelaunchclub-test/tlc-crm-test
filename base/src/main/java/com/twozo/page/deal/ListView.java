package com.twozo.page.deal;

import com.twozo.web.driver.service.WebAutomationDriver;

public class ListView extends DealPage {

    private static ListView listView;

    public ListView(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static ListView getInstance(final WebAutomationDriver webAutomationDriver){

        //   if (Objects.isNull(forecastView)) {
        listView= new ListView(webAutomationDriver);
        // }

        return listView;
    }
}
