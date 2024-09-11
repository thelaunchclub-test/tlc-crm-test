package com.twozo.page.deal;

import com.twozo.page.BasePage;
import com.twozo.page.Filter;
import com.twozo.page.xpath.XPathBuilder;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;

import java.util.List;
import java.util.Objects;

public class Deal extends BasePage {

    private static Deal deal;

    private AddDeal addDeal;
    private KanbanView kanbanView;
    private ForecastView forecastView;
    private AddPipeline addPipeline;
    private Filter filter;
    private WebPageElement signInButton;
    private WebPageElement newDeal;
    private WebPageElement wonDeal;
    private WebPageElement lostDeal;
    private WebPageElement general;
    private WebPageElement allDeal;
    private WebPageElement importDeals;
    private WebPageElement addDealButton;
    private WebPageElement kanbanViewButton;
    private WebPageElement listViewButton;
    private WebPageElement forecastViewButton;
    private WebPageElement contactIcon;
    private WebPageElement filterIcon;

    public Deal(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static Deal getInstance(final WebAutomationDriver webAutomationDriver) {

        //  if (Objects.isNull(deal)) {
        deal = new Deal(webAutomationDriver);
        //}

        return deal;
    }

    public WebPageElement getListView(){
        return findByXpath("//*[@value='list']");
    }

    public WebPageElement getKanbanViewButton() {
        return findByText("//*[@value='kanban']");
    }

    public WebPageElement getForecastViewButton() {
        return findByText("//*[@value='forecast']");
    }

    public WebPageElement getAllDealButton() {
        return findByText("All Deal");
    }

    public WebPageElement getImportDealsButton() {
        return findByText("Import Deals");
    }

    public WebPageElement getAddDealButton() {
        return findByXpath("//*[text()='Deal'][@type='button']");
    }



    public WebPageElement getContactIcon() {
        return findByXpath("(//*[@class='MuiListItem-root MuiListItem-gutters css-12dzmul'])[2]");
    }

    public WebPageElement getFilterIcon() {
        return findByXpath("//div[@class='css-j7qwjs'][@style='cursor: pointer;']");
    }

    public WebPageElement getAddPipelineButton() {
        return findAboveElement(List.of(new Element(LocatorType.XPATH, XPathBuilder.getXPathByText("Add Pipeline"),
                true), new Element(LocatorType.TAG_NAME, "button", false)));
    }

    public WebPageElement getNewDealButton() {
        return findByText("New");
    }

    public WebPageElement getSignInButton() {
        return findByText("Sign In");
    }

    public WebPageElement getWonDealButton() {
        return findByText("Won");
    }

    public WebPageElement getLostDealButton() {
        return findByText("Lost");
    }

    public WebPageElement getGeneralButton() {
        return findByText("General");
    }

    public WebPageElement getManageButton() {
        return findByText("Manage");
    }

    public AddDeal getAddDeal() {

        if (Objects.isNull(addDeal)) {
            addDeal = AddDeal.getInstance(webAutomationDriver);

        }

        return addDeal;
    }

    public KanbanView getKanbanView() {

        if (Objects.isNull(kanbanView)) {
            kanbanView = KanbanView.getInstance(webAutomationDriver);

        }

        return kanbanView;
    }

    public ForecastView getForecastView() {

        if (Objects.isNull(addDeal)) {
            forecastView = ForecastView.getInstance(webAutomationDriver);
        }

        return forecastView;
    }

    public AddPipeline getAddPipeline() {
        if (Objects.isNull(addPipeline)) {
            addPipeline = AddPipeline.getInstance(webAutomationDriver);
        }

        return addPipeline;
    }

    public Filter getFilter() {

        if (Objects.isNull(filter)) {
            filter = Filter.getInstance(webAutomationDriver);
        }

        return filter;
    }

    public AddDeal switchToAddDeal() {
        click(getAddDealButton());

        return getAddDeal();
    }

    public ForecastView switchToForecastView() {
        click(getForecastViewButton());

        return getForecastView();
    }

    public KanbanView switchToKanbanView() {
        click(getKanbanViewButton());

        return getKanbanView();
    }

    public Filter switchToFilter() {
        click(getFilterIcon());

        return getFilter();
    }

    public AddPipeline switchToAddNewPipeline() {
        click(getGeneralButton());
        click(getManageButton());
        click(getAddPipelineButton());

        return getAddPipeline();
    }

    public void switchToColumnSettings() {
        click(getListView());
        click(getColumnSettingsButton());
    }

    public AddDeal addDeal(){
        click(getAddDealButton());
        return getAddDeal();
    }
}
