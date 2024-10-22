package com.twozo.page.deal;

import com.twozo.page.BasePage;
import com.twozo.page.Filter;
import com.twozo.web.driver.service.WebAutomationDriver;
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
        return findByXpath(MAP.get("crm.deal.view.list"));
    }

    public WebPageElement getKanbanViewButton() {
        return findByText(MAP.get("crm.deal.view.kanban"));
    }

    public WebPageElement getForecastViewButton() {
        return findByText("crm.deal.view.forecast");
    }

    public WebPageElement getAllDealButton() {
        return findByText(MAP.get("crm.deal.filter.all.deal"));
    }

    public WebPageElement getImportDealsButton() {
        return findByText(MAP.get("crm.deal.button.import.deal"));
    }

    public WebPageElement getAddDealButton() {
        return findByXpath(MAP.get("crm.deal.button.add.deal"));
    }

    public WebPageElement getContactIcon() {
        return findByXpath(MAP.get("crm.contact.icon"));
    }

    public WebPageElement getFilterIcon() {
        return findByXpath(MAP.get("crm.source.filter.icon"));
    }

//    public WebPageElement getNewDeal() {
//        return findByText(MAP.get("crm.deal.kanban.pipeline.default.stage.new"));
//    }
//
//    public WebPageElement getWonDeal() {
//        return findByText(MAP.get("crm.deal.kanban.pipeline.default.stage.won"));
//    }
//
//    public WebPageElement getLostDeal() {
//        return findByText(MAP.get("crm.deal.kanban.pipeline.default.stage.lost"));
//    }

    public WebPageElement getManageButton() {
        return findByXpath(MAP.get("crm.deal.button.manage"));
    }

    public WebPageElement getActivePipeline() {
        return findByXpath(MAP.get("crm.deal.pipeline.active"));
    }

    public Collection<WebPageElement> getStagesOfPipeline() {
        return findElementsByClass(MAP.get("crm.deal.pipeline.stages"));
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
       // waitTillVisible(MAP.get("crm.deal.button.manage"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        click(getManageButton());
    }

    public boolean verifyPipelineChangeInKanbanReflectedInAddDealForm() {
        final String activePipelineXPath = MAP.get("crm.deal.pipeline.active");
        String newPipeline = null;
        waitTillVisible(activePipelineXPath);

        final String currentPipeline = getText(findByXpath(activePipelineXPath));

        System.out.println(currentPipeline);
        click(getActivePipeline());
        final Collection<WebPageElement> pipelinesAsElement = findElementsByXpath(MAP.get("crm.deal.pipelines"));
        boolean isPipelineChanged = false;

        for (final WebPageElement pipelineAsElement : pipelinesAsElement) {

            final String pipelineText = getText(pipelineAsElement);

            if (!pipelineText.equals(currentPipeline) && !pipelineText.equals("Manage") && !pipelineText.equals("General")) {
                System.out.println(getText(pipelineAsElement));
                click(pipelineAsElement);
                isPipelineChanged = true;
                break;
            }
        }
        //click(findByXpath(MAP.get("body")));

        if (!isPipelineChanged) {
            newPipeline = String.format("%s%s", currentPipeline, "New");
            final PipelineForm pipelineForm = PipelineForm.getInstance(webAutomationDriver);

            click(getManageButton());
            pipelineForm.createPipeline(newPipeline);
            refresh();
            click(getActivePipeline());
            click(findByText(newPipeline));
        }

        //final String pipelineNameAfterChange = getText(findByXpath("//*[text()='Pipeline']/parent::div/div/div"));

        click(getAddDealButton());

//        System.out.println(newPipeline);
//        System.out.println(getText(findByXpath("//*[text()='Pipeline']/parent::div/div/div")));

        //  waitTillVisible(XPathBuilder.getXPathByText(MAP.get("crm.deal.pipeline.form.save.and.new")));

        System.out.println(Objects.equals(newPipeline,
                getText(findByXpath("//*[text()='Pipeline']/parent::div/div/div"))));
        boolean equals = Objects.equals(newPipeline,
                getText(findByXpath("//*[text()='Pipeline']/parent::div/div/div")));
        return equals;
    }

}
