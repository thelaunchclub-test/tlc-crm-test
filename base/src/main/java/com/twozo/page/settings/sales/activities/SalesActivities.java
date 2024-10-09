package com.twozo.page.settings.sales.activities;

import com.twozo.extent.report.reporter.logger.ExtentLogger;
import com.twozo.page.BasePage;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SalesActivities extends BasePage {

    public SalesActivities(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    private WebPageElement getActivityTypeButton() {
        return this.findByXpath("//*[text()='Activity Type']");
    }

    private WebPageElement getEnableButton() {
        return this.findByXpath("//*[text()='Enable']");
    }

    private WebPageElement getDisabledButton() {
        return this.findByXpath("//*[text()='Disabled']");
    }

    private WebPageElement getActivityNameField() {
        return this.findByXpath("//*[@class='MuiInputBase-input MuiOutlinedInput-input css-1pog434']");
    }

    private WebPageElement getSaveButton() {
        return this.findByXpath("//button[text()='Save']");
    }

    private WebPageElement disableButton() {
        return this.findByXpath("//*[@class='PrivateSwitchBase-input MuiSwitch-input css-1m9pwf3']");
    }

    public void addActivityType(final TestCase testCase) {

        final String activityIconName = testCase.input.getString("ActivityTypeIcon");
        final String activityName = testCase.input.getString("activityTypeName");


        click(getActivityTypeButton());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        send(getActivityNameField(), activityName);
        final ActivityIcon activityIcon = ActivityIcon.fromName(activityIconName);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(findByXpath(activityIcon.getIconXPath()));
        click(getSaveButton());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<String> checkAddedActivityTypeInList() {

        final Collection<WebPageElement> activityTypeElements = findElementsByXpath("//div[@class='css-95g4uk']//div[2]/p");
        final List<String> availableActivityType = new ArrayList<>();

        for (final WebPageElement activityElement : activityTypeElements) {
            final String activityTypeName = activityElement.getElementInformationProvider().getText();
            availableActivityType.add(activityTypeName);
        }

        for (String availableCurrency : availableActivityType) {
            System.out.println(availableCurrency);
        }

        return availableActivityType;
    }

    public boolean isActivityTypeAvailableInList(final String activityTypeName) {

        for (final String activityType : checkAddedActivityTypeInList()) {

            if (activityType.contains(activityTypeName)) {
                ExtentLogger.pass("The given Activity type is there");
                return true;
            }
        }

        return false;
    }

    public boolean isActivityTypeNotAvailableInList(final String activityTypeName) {

        for (final String activityType : checkAddedActivityTypeInList()) {

            if (!activityType.contains(activityTypeName)) {
                return true;
            }
        }

        return false;
    }

    protected String getActivityTypeBlock(final String activityTypeName) {
        int rowNumber = 1;
        while (getText(findByXpath(String.format("//p[contains(text(),'%s')]//parent::div", activityTypeName))).contains(activityTypeName)) {
            rowNumber++;
        }

        return String.format("(//div[@class='css-95g4uk'])[%d]", rowNumber);
    }

    public void updateActivityTypes(final TestCase testCase) {

        if (testCase.input.containsKey("isUpdate")) {
            testCase.input.optBoolean("isUpdate", false);
        }

        final String activityTypeName = testCase.input.getString("activityTypeName");
        final String changes = testCase.input.getString("changes");

        click(findByXpath(String.format("//p[contains(text(),'%s')]//parent::div", activityTypeName)));
        ExtentLogger.pass("The given Activity name is clicked");
        click(getActivityNameField());
        ExtentLogger.pass("The Activity name field is Clicked");

        mouseActions.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).sendKeys(changes).perform();
        ExtentLogger.pass("The value have in the Field is selected and the changes are Updated");
        click(getSaveButton());
        ExtentLogger.pass("The save button is clicked");

    }

    public boolean disableActivityType(final TestCase testCase) {
        final String activityTypeName = testCase.input.getString("activityType");

        if (testCase.input.optBoolean("isDisable", false)) {
            hoverByXpath(String.format("//p[contains(text(),'%s')]//parent::div", activityTypeName));
            ExtentLogger.pass("The given Activity type is Hovered");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            click(disableButton());
            ExtentLogger.pass("The given Activity type was disabled");
            refresh();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            click(getDisabledButton());
            ExtentLogger.pass("The Disabled button was clicked");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return isActivityTypeAvailableInList(activityTypeName);
        }

        return false;
    }

    public boolean enableActivityType(final TestCase testCase) {

        final String activityTypeName = testCase.input.getString("activityType");

        if (testCase.input.containsKey("isEnable")) {
            testCase.input.optBoolean("isEnable", false);


            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            click(getDisabledButton());
            ExtentLogger.pass("The Disabled button is clicked");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hoverByXpath(String.format("//p[contains(text(),'%s')]//parent::div", activityTypeName));
            ExtentLogger.pass("The given Activity type is Hovered");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            click(disableButton());
            ExtentLogger.pass("The Enable button is clicked");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            click(getEnableButton());
            ExtentLogger.pass("The Enable button is clicked");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return isActivityTypeAvailableInList(activityTypeName);
        }

        return false;
    }


    public WebPageElement getErrorMsg() {
        return this.findByXpath("//*[text()='Disabling the last active activity type is not allowed']");
    }

    public void addActivityAndCheck() {
        final String activityIconName = "Search";
        final String activityName = "sing";

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(getActivityTypeButton());
        send(getActivityNameField(), activityName);
        final ActivityIcon activityIcon = ActivityIcon.fromName(activityIconName);

        click(findByXpath(activityIcon.getIconXPath()));
        click(getSaveButton());

    }

    public void addActivityTypeAndCheck() {
        addActivityAndCheck();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        addActivityAndCheck();
    }

    public boolean isAlreadyExistErrorMsgDisplayed() {
        ExtentLogger.pass("The default Activity types are shown");
        return isDisplayed(findByXpath("//*[text()='Activity type already exists']"));
    }

    public Collection<WebPageElement> disableAll() {

        final Collection<WebPageElement> webPageElements = findElementsByXpath("//div[@class='css-95g4uk']");

        if (webPageElements.isEmpty()) {
            return webPageElements;
        }

        for (int i = 1; i <= webPageElements.size(); i++) {
            final String elementXpath = "(//div[@class='css-95g4uk'])[1]";
            hoverByXpath(elementXpath);
            ExtentLogger.pass("The element is hovered");
            click(disableButton());
            ExtentLogger.pass("The given element is Disabled");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return webPageElements;
    }


    public boolean errorMsg() {
        return isDisplayed((getErrorMsg()));
//        return isDisplayed(findByXpath("(//div[@class='css-95g4uk'])[1]"));
    }

    public boolean checkDefaultActivityTypes() {
        List<String> expectedActivityTypes = Arrays.asList("Call", "Mail", "Meeting", "Task", "Lunch", "Quote");
        List<String> actualActivityTypes = fetchActualActivityTypes();

        for (String expected : expectedActivityTypes) {
            if (!actualActivityTypes.contains(expected)) {
                System.out.println("Activity Type Missing: " + expected);
                return false;
            }
        }

        System.out.println("All expected activity types are present.");
        return true;
    }


    private List<String> fetchActualActivityTypes() {
        List<String> actualActivityTypes = new ArrayList<>();
        Collection<WebPageElement> activityElements = findElementsByXpath("//div[@class='css-95g4uk']//div[2]/p");

        for (WebPageElement element : activityElements) {
            actualActivityTypes.add(getText(element).trim());
        }

        return actualActivityTypes;
    }

    public boolean saveWithoutIconSelection(final TestCase testCase) {

        final String activityTypeName = testCase.input.getString("activityTypeName");
        try {
            click(getActivityTypeButton());
            ExtentLogger.pass("Add Activity type button is clicked");
            send(getActivityNameField(), activityTypeName);
            ExtentLogger.pass("The given value is Entered in" + getActivityNameField());
            Thread.sleep(2000);

            return isDisplayed(findElement(new Element(LocatorType.XPATH, "//button[contains(@style, 'color: rgba(255, 255, 255, 0.6);')]", true)));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean saveWithoutName(final TestCase testCase) {

        final String activityTypeName = testCase.input.getString("activityTypeIcon");
        final ActivityIcon activityIcon = ActivityIcon.fromName(activityTypeName);
        try {
            click(getActivityTypeButton());
            ExtentLogger.pass("Add Activity Type button is clicked");
            click(findByXpath(activityIcon.getIconXPath()));
            ExtentLogger.pass("The given Activity Icon is selected");

            Thread.sleep(2000);
            return isDisplayed(findElement(new Element(LocatorType.XPATH, "//button[contains(@style, 'color: rgba(255, 255, 255, 0.6);')]", true)));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean editWithoutChanges(final TestCase testCase) {

        final String activityTypeName = testCase.input.getString("activityType");

        if (testCase.input.containsKey("isDisable")) {
            testCase.input.optBoolean("isDisable", false);
        }

        try {
            Thread.sleep(2000);
            click(findByXpath(String.format("//p[contains(text(),'%s')]//parent::div",activityTypeName)));
            ExtentLogger.pass("The given Activity name is Clicked");
            Thread.sleep(2000);
            return isDisplayed(findElement(new Element(LocatorType.XPATH, "//button[contains(@style, 'color: rgba(255, 255, 255, 0.6);')]", true)));
        } catch (Exception e) {
            return false;
        }
    }
}
