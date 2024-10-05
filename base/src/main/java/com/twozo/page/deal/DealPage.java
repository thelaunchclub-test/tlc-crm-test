package com.twozo.page.deal;

import com.twozo.page.BasePage;
import com.twozo.page.Filter;
import com.twozo.page.xpath.XPathBuilder;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;

import java.util.*;

public class DealPage extends BasePage {

    private static DealPage dealPage;

    private AddDeal addDeal;
    private KanbanView kanbanView;
    private ForecastView forecastView;
    private ListView listView;
    private PipelineForm addPipeline;
    private Filter filter;
    private WebPageElement signInButton;
    private WebPageElement newDeal;
    private WebPageElement wonDeal;
    private WebPageElement lostDeal;
    private WebPageElement allDeal;
    private WebPageElement importDeals;
    private WebPageElement addDealButton;
    private WebPageElement kanbanViewButton;
    private WebPageElement listViewButton;
    private WebPageElement forecastViewButton;
    private WebPageElement contactIcon;
    private WebPageElement filterIcon;
    private WebPageElement activePipeline;
    private Collection<WebPageElement> stagesOfPipeline;

    public DealPage(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static DealPage getInstance(final WebAutomationDriver webAutomationDriver) {

        //  if (Objects.isNull(deal)) {
        dealPage = new DealPage(webAutomationDriver);
        //}

        return dealPage;
    }

    public WebPageElement getListViewButton() {
        return findByXpath(map.get("crm.deal.view.list"));
    }

    public WebPageElement getKanbanViewButton() {
        return findByText(map.get("crm.deal.view.kanban"));
    }

    public WebPageElement getForecastViewButton() {
        return findByText("crm.deal.view.forecast");
    }

    public WebPageElement getAllDealButton() {
        return findByText(map.get("crm.deal.filter.all.deal"));
    }

    public WebPageElement getImportDealsButton() {
        return findByText(map.get("crm.deal.button.import.deal"));
    }

    public WebPageElement getAddDealButton() {
        return findByXpath(map.get("crm.deal.button.add.deal"));
    }

    public WebPageElement getContactIcon() {
        return findByXpath(map.get("crm.contact.icon"));
    }

    public WebPageElement getFilterIcon() {
        return findByXpath(map.get("crm.source.filter.icon"));
    }

//    public WebPageElement getNewDeal() {
//        return findByText(map.get("crm.deal.kanban.pipeline.default.stage.new"));
//    }
//
//    public WebPageElement getWonDeal() {
//        return findByText(map.get("crm.deal.kanban.pipeline.default.stage.won"));
//    }
//
//    public WebPageElement getLostDeal() {
//        return findByText(map.get("crm.deal.kanban.pipeline.default.stage.lost"));
//    }

    public WebPageElement getManageButton() {
        return findByXpath(map.get("crm.deal.button.manage"));
    }

    public WebPageElement getActivePipeline() {
        return findByXpath(map.get("crm.deal.pipeline.active"));
    }

    public Collection<WebPageElement> getStagesOfPipeline() {
        return findElementsByClass(map.get("crm.deal.pipeline.stages"));
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

    public PipelineForm getAddPipeline() {
        if (Objects.isNull(addPipeline)) {
            addPipeline = PipelineForm.getInstance(webAutomationDriver);
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


    public void switchToColumnSettings() {
        click(getListViewButton());
        click(getColumnSettingsButton());
    }

    public AddDeal addDeal() {
        click(getAddDealButton());
        return getAddDeal();
    }

    public void switchToPipeline() {
        click(getActivePipeline());
        //waitTillVisible(map.get("crm.deal.button.manage"));
        click(getManageButton());

//        isDisplayed(findByXpath(map.get("crm.deal.pipeline.default.pipeline")));
//        isDisplayed(findByXpath(map.get("crm.deal.pipeline.default.rot.days")));
    }

    public boolean verifyPipelineChangeInKanbanReflectedInAddDealForm() {
        final String currentPipeline = getText(findByXpath(map.get("crm.deal.pipeline.active")));
        click(getActivePipeline());
        final Collection<WebPageElement> pipelinesAsElement = findElementsByXpath(map.get("crm.deal.pipelines"));
        boolean isPipelineChanged = false;

        for (final WebPageElement pipelineAsElement : pipelinesAsElement) {

            if (!Objects.equals(getText(pipelineAsElement), currentPipeline)) {
                System.out.println(getText(pipelineAsElement));
                click(pipelineAsElement);
                isPipelineChanged = true;
                break;
            }
        }
        click(findByXpath(map.get("body")));

        if (!isPipelineChanged) {
            final String newPipeline = String.format("%s%s", currentPipeline, "New");
            final PipelineForm pipelineForm = PipelineForm.getInstance(webAutomationDriver);

            pipelineForm.switchToPipeline();
            pipelineForm.createPipeline(newPipeline);
            refresh();
        }

        final String newActivePipeline = getText(findByXpath(map.get("crm.deal.pipeline.active")));

        click(getAddDealButton());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return newActivePipeline.equals(
                getText(findBelowElement(List.of(new Element(LocatorType.TAG_NAME, "p", false),
                        new Element(LocatorType.XPATH, "//*[text()='Pipeline']", true)))));
    }

}
