package com.twozo.test.settings.data.fields;

import com.twozo.page.settings.sales.activities.WebAutomationListener;
import com.twozo.test.BaseTest;

import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.ElementInteraction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyLogExample extends BaseTest implements WebAutomationListener{

    private WebDriverListener webDriverListener;
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyLogExample.class);

    @Override
    public void beforeClickOn(ElementInteraction element, WebAutomationDriver driver) {
        webDriverListener.beforeClick((WebElement) element);
        LOGGER.info("Before clicking on: " + element.toString());
    }

    @Override
    public void afterClickOn(WebElement element, WebAutomationDriver driver) {
        webDriverListener.afterClick(element);
    }

//    @Override
//    public void afterClickOn(ElementInteraction element, WebAutomationDriver driver) {
//        LOGGER.info("Clicked on: " + element.toString());
//    }
//
//    @Override
//    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
//        logger.info("Trying to find element: " + by.toString());
//    }
//
//    @Override
//    public void afterFindBy(By by, WebElement element, WebDriver driver) {
//        logger.info("Found element: " + element.toString());
//    }
//
//    // Implement other methods as needed...
//    @Override
//    public void beforeNavigateTo(String url, WebDriver driver) {
//        logger.info("Navigating to: " + url);
//    }
//
//    @Override
//    public void afterNavigateTo(String url, WebDriver driver) {
//        logger.info("Navigated to: " + url);
//    }
//
//    // You can also log exceptions
//    @Override
//    public void onException(Throwable throwable, WebDriver driver) {
//        logger.error("Exception occurred: " + throwable.getMessage());
//    }

}
