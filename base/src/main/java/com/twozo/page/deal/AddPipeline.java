package com.twozo.page.deal;

import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;

import java.util.List;

public class AddPipeline extends Deal {


    private static AddPipeline addPipeline;

    public AddPipeline(WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }


    public static AddPipeline getInstance(final WebAutomationDriver webAutomationDriver) {

        //   if (Objects.isNull(pipeline)) {
        addPipeline = new AddPipeline(webAutomationDriver);
        // }

        return addPipeline;
    }

    public WebPageElement getPipelineName(){
        return findBelowElement(List.of(
                new Element(LocatorType.TAG_NAME, "input", false),
                new Element(LocatorType.XPATH, "//*[text()='Pipeline Name']", true)));
    }

    public WebPageElement getMakeAsDefaultCheckbox(){
        return findByXpath("//input[@type='checkbox']");
    }

    public WebPageElement getDealsRotAfter(){
        return findBelowElement(List.of(
                new Element(LocatorType.TAG_NAME, "input", false),
                new Element(LocatorType.XPATH, "//*[text()='Deals rot after']", true)));
    }

    public WebPageElement getSaveButton(){
        return findByText("Save");
    }

    public WebPageElement getSaveAndNewButton(){
        return findByText("Save & New");
    }

    public void addDetails(final Pipeline pipeline){
//        send(getPipelineName(), pipeline.getName());
//        if(pipeline.isDefault){
//            if ()
//        }
    }


}
