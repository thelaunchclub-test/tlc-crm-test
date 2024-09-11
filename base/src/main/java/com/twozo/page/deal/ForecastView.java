package com.twozo.page.deal;

import com.twozo.web.driver.service.WebAutomationDriver;

public class ForecastView extends Deal{

    private static ForecastView forecastView;

    public ForecastView(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static ForecastView getInstance(final WebAutomationDriver webAutomationDriver){

     //   if (Objects.isNull(forecastView)) {
            forecastView = new ForecastView(webAutomationDriver);
       // }

        return forecastView;
    }
}
