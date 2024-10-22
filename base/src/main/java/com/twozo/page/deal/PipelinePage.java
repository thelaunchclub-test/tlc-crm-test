package com.twozo.page.deal;

import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.*;

public class PipelinePage extends DealPage {

    private static PipelinePage pipelinePage;

    public PipelinePage(WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static PipelinePage getInstance(final WebAutomationDriver webAutomationDriver) {

        //  if (Objects.isNull(deal)) {
        pipelinePage = new PipelinePage(webAutomationDriver);
        //}

        return pipelinePage;
    }

    private Pipeline getPipeline(final String pipelineName) {
        final Pipeline pipeline = new Pipeline();
        final String pipelineBlock = String.format(MAP.get("crm.deal.pipeline.block"), pipelineName);

        pipeline.setPipelineName(getText(findByXpath(String.format("%s%s", pipelineBlock, MAP.get("crm.deal.pipeline.name")))));
        pipeline.setDealRotAfter(getText(findByXpath(String.format("%s%s", pipelineBlock,
                MAP.get("crm.deal.pipeline.rot.days")))).replaceAll("\\D+", ""));

        waitTillVisible(String.format("%s%s", pipelineBlock, MAP.get("crm.deal.pipeline.name")));
        hoverByXpath(String.format("%s%s", pipelineBlock, MAP.get("crm.deal.pipeline.name")));
        click(findByXpath(String.format(MAP.get("crm.deal.pipeline.kebab"), pipelineBlock)));
        final Collection<String> options = new ArrayList<>();
        final Collection<WebPageElement> optionsAsElement = findElementsByXpath(MAP.get("crm.deal.pipeline.kebab.options"));

        for (final WebPageElement webPageElement : optionsAsElement) {
            options.add(getText(webPageElement));
        }

        pipeline.setDefault(!options.contains(MAP.get("crm.deal.pipeline.mark.as.defaultPipeline")));
        final Map<String, String> stages = new HashMap<>();
        pipeline.setStages(stages);
        final Collection<WebPageElement> stagesAsElement = findElementsByXpath(String.format("%s%s", pipelineBlock,
                MAP.get("crm.deal.pipeline.stage.block")));

        for (int postiton = 1; postiton <= stagesAsElement.size(); postiton++) {
            stages.put(getText(findByXpath(String.format("(%s%s", pipelineBlock, String.format(MAP.get("crm.deal.pipeline.stage.name"), postiton))))
                    , getText(findByXpath(String.format("(%s%s", pipelineBlock,
                            String.format(MAP.get("crm.deal.pipeline.stage.probability"), postiton)))).replaceAll("\\D+", ""));
        }

        return pipeline;
    }

    private void check(final String pipelineName) {
        final String pipelineBlock = String.format(MAP.get("crm.deal.pipeline.block"), pipelineName);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        hoverByXpath(format(pipelineBlock, MAP.get("crm.deal.pipeline.name")));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        click(findByXpath(String.format(MAP.get("crm.deal.pipeline.kebab"), pipelineBlock)));
        final Collection<String> options = new ArrayList<>();
        final Collection<WebPageElement> optionsAsElement = findElementsByXpath(MAP.get("crm.deal.pipeline.kebab.options"));

        for (final WebPageElement webPageElement : optionsAsElement) {
            options.add(getText(webPageElement));
        }

        final String markAsDefault = MAP.get("crm.deal.pipeline.mark.as.default.pipeline");

        if (options.contains(markAsDefault)) {
            click(findByXpath(MAP.get("crm.deal.pipeline.kebab.mark.as.default.pipeline")));

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {

            }
        }

        click(findByXpath(MAP.get("body")));
        click(findByXpath(MAP.get("crm.deal.pipeline.close")));
    }

    public boolean checkDefaultPipelineAndStages() {
        final String general = "General";
        final String pipelineBlock = String.format(MAP.get("crm.deal.pipeline.block"), general);

        if (!general.equals(getText(findByXpath(String.format("%s%s", pipelineBlock, MAP.get("crm.deal.pipeline.name")))))) {
            return false;
        }

        if (!MAP.get("crm.deal.pipeline.default.rot.days").equals(getText(findByXpath(String.format("%s%s", pipelineBlock,
                MAP.get("crm.deal.pipeline.rot.days")))).replaceAll("\\D+", ""))) {
            return false;
        }

        if (!MAP.get("crm.deal.pipeline.default.stage.new").equals(getText(findByXpath(String.format("(%s%s", pipelineBlock,
                String.format(MAP.get("crm.deal.pipeline.stage.name"), 1)))))) {
            return false;
        }

        if (!MAP.get("crm.deal.pipeline.default.stage.new.probability").equals(getText(findByXpath(String.format("(%s%s", pipelineBlock,
                String.format(MAP.get("crm.deal.pipeline.stage.probability"), 1)))).replaceAll("\\D+", ""))) {
            return false;
        }

        if (!MAP.get("crm.deal.pipeline.default.stage.won").equals(getText(findByXpath(String.format("(%s%s", pipelineBlock,
                String.format(MAP.get("crm.deal.pipeline.stage.name"), 2)))))) {
            return false;
        }

        if (!MAP.get("crm.deal.pipeline.default.stage.won.probability").equals(getText(findByXpath(String.format("(%s%s", pipelineBlock,
                String.format(MAP.get("crm.deal.pipeline.stage.probability"), 2)))).replaceAll("\\D+", ""))) {
            return false;
        }

        if (!MAP.get("crm.deal.pipeline.default.stage.lost").equals(getText(findByXpath(String.format("(%s%s", pipelineBlock,
                String.format(MAP.get("crm.deal.pipeline.stage.name"), 3)))))) {
            return false;
        }

        if (!MAP.get("crm.deal.pipeline.default.stage.lost.probability").equals(getText(findByXpath(String.format("(%s%s", pipelineBlock,
                String.format(MAP.get("crm.deal.pipeline.stage.probability"), 3)))).replaceAll("\\D+", ""))) {
            return false;
        }

        return isDisplayed(findByXpath(String.format("%s%s", pipelineBlock, MAP.get("crm.deal.pipeline.add.stage"))));
    }

    public boolean markAsDefaultPipeline(final String pipelineName) {

//        try {
//            isDisplayed(findByXpath(String.format(MAP.get("crm.deal.pipeline.block"), pipelineName)));
//        } catch (Exception exception) {
//            new PipelineForm(webAutomationDriver).createPipeline(pipelineName);
//        }
        final Collection<String> pipelineNames = new ArrayList<>();
        final Collection<WebPageElement> pipelineNamesAsElement = findElementsByXpath(MAP.get("crm.deal.pipeline.names"));

        for (final WebPageElement webPageElement : pipelineNamesAsElement) {
            pipelineNames.add(getText(webPageElement));
        }

        if (!pipelineNames.contains(pipelineName)) {
            new PipelineForm(webAutomationDriver).createPipeline(pipelineName);
        }

        check(pipelineName);
        click(getActivePipeline());

        return isDisplayed(findByXpath(String.format(MAP.get("crm.deal.kanban.pipeline.default.icon"), pipelineName)));
    }

    public boolean checkDefaultPipelineDisplayedInKanbanViewPipeline(final String pipelineName) {
        check(pipelineName);
        System.out.println(getText(findByXpath(MAP.get("crm.deal.pipeline.active"))));
        return getText(findByXpath(MAP.get("crm.deal.pipeline.active"))).equals(pipelineName);
    }

    public boolean verifyPipelineOrderInEditDrawerMatchesDropdown() {
        final Collection<String> pipelineNamesInPipelinePage = new ArrayList<>();
        final Collection<String> pipelineNamesInPipelineDropdown = new ArrayList<>();
        final Collection<WebPageElement> pipelineNamesInPipelinePageAsElements = findElementsByXpath(MAP.get("crm.deal.pipeline.names"));

        for (final WebPageElement pipelineNamesAsElement : pipelineNamesInPipelinePageAsElements) {
            pipelineNamesInPipelinePage.add(getText(pipelineNamesAsElement));
        }

        click(findByXpath(MAP.get("crm.deal.pipeline.close")));
        click(getActivePipeline());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        final Collection<WebPageElement> pipelineNamesInPipelineDropdownAsElements = findElementsByXpath(MAP.get("crm.deal.pipelines"));

        for (final WebPageElement pipelineNamesInPipelineDropdownAsElement : pipelineNamesInPipelineDropdownAsElements) {
            pipelineNamesInPipelineDropdown.add(getText(pipelineNamesInPipelineDropdownAsElement));
        }
        pipelineNamesInPipelineDropdown.remove("Manage");

        return pipelineNamesInPipelinePage.equals(pipelineNamesInPipelineDropdown);
    }
}
