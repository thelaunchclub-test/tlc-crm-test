package com.twozo.page.deal;

import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.service.WebPageElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    public Pipeline getPipeline(final String pipelineName) {
        final Pipeline pipeline = new Pipeline();
        final String pipelineBlock = String.format(map.get("crm.deal.pipeline.block"), pipelineName);

        pipeline.setPipelineName(getText(findByXpath(String.format("%s%s", pipelineBlock, map.get("crm.deal.pipeline.name")))));
        pipeline.setDealRotAfter(getText(findByXpath(String.format("%s%s", pipelineBlock,
                map.get("crm.deal.pipeline.rot.days")))).replaceAll("\\D+", ""));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        hoverByXpath(String.format("%s%s", pipelineBlock, map.get("crm.deal.pipeline.name")));
        click(findByXpath(String.format(map.get("crm.deal.pipeline.kebab"), pipelineBlock)));
        final Collection<String> options = new ArrayList<>();
        final Collection<WebPageElement> optionsAsElement = findElementsByXpath(map.get("crm.deal.pipeline.kebab.options"));

        for (final WebPageElement webPageElement : optionsAsElement) {
            options.add(getText(webPageElement));
        }

        pipeline.setDefault(!options.contains(map.get("crm.deal.pipeline.mark.as.defaultPipeline")));
        final Map<String, String> stages = new HashMap<>();
        pipeline.setStages(stages);
        final Collection<WebPageElement> stagesAsElement = findElementsByXpath(String.format("%s%s", pipelineBlock,
                map.get("crm.deal.pipeline.stage.block")));

        for (int postiton = 1; postiton <= stagesAsElement.size(); postiton++) {
            stages.put(getText(findByXpath(String.format("(%s%s", pipelineBlock, String.format(map.get("crm.deal.pipeline.stage.name"), postiton))))
                    , getText(findByXpath(String.format("(%s%s", pipelineBlock,
                            String.format(map.get("crm.deal.pipeline.stage.probability"), postiton)))).replaceAll("\\D+", ""));
        }

        return pipeline;
    }

    public boolean checkDefaultPipelineAndStages() {
        final String pipelineBlock = String.format(map.get("crm.deal.pipeline.block"), "General");

        if (!map.get("crm.deal.pipeline.default.pipeline.name").equals(getText(findByXpath(String.format("%s%s", pipelineBlock, map.get("crm.deal.pipeline.name")))))) {
            return false;
        }

        if (!map.get("crm.deal.pipeline.default.rot.days").equals(getText(findByXpath(String.format("%s%s", pipelineBlock,
                map.get("crm.deal.pipeline.rot.days")))).replaceAll("\\D+", ""))) {
            return false;
        }

        if (!map.get("crm.deal.pipeline.default.stage.new").equals(getText(findByXpath(String.format("(%s%s", pipelineBlock,
                String.format(map.get("crm.deal.pipeline.stage.name"), 1)))))) {
            return false;
        }

        if (!map.get("crm.deal.pipeline.default.stage.new.probability").equals(getText(findByXpath(String.format("(%s%s", pipelineBlock,
                String.format(map.get("crm.deal.pipeline.stage.probability"), 1)))).replaceAll("\\D+", ""))) {
            return false;
        }

        if (!map.get("crm.deal.pipeline.default.stage.won").equals(getText(findByXpath(String.format("(%s%s", pipelineBlock,
                String.format(map.get("crm.deal.pipeline.stage.name"), 2)))))) {
            return false;
        }

        if (!map.get("crm.deal.pipeline.default.stage.won.probability").equals(getText(findByXpath(String.format("(%s%s", pipelineBlock,
                String.format(map.get("crm.deal.pipeline.stage.probability"), 2)))).replaceAll("\\D+", ""))) {
            return false;
        }

        if (!map.get("crm.deal.pipeline.default.stage.lost").equals(getText(findByXpath(String.format("(%s%s", pipelineBlock,
                String.format(map.get("crm.deal.pipeline.stage.name"), 3)))))) {
            return false;
        }

        if (!map.get("crm.deal.pipeline.default.stage.lost.probability").equals(getText(findByXpath(String.format("(%s%s", pipelineBlock,
                String.format(map.get("crm.deal.pipeline.stage.probability"), 3)))).replaceAll("\\D+", ""))) {
            return false;
        }

        return isDisplayed(findByXpath(String.format("%s%s", pipelineBlock, map.get("crm.deal.pipeline.add.stage"))));
    }

    public boolean markAsDefaultPipeline(final String pipelineName) {
        final String pipelineBlock = String.format(map.get("crm.deal.pipeline.block"), pipelineName);

        hoverByXpath(String.format("%s%s", pipelineBlock, map.get("crm.deal.pipeline.name")));
        click(findByXpath(String.format(map.get("crm.deal.pipeline.kebab"), pipelineBlock)));
        final Collection<String> options = new ArrayList<>();
        final Collection<WebPageElement> optionsAsElement = findElementsByXpath(map.get("crm.deal.pipeline.kebab.options"));

        for (final WebPageElement webPageElement : optionsAsElement) {
            options.add(getText(webPageElement));
        }

        final String markAsDefault = map.get("crm.deal.pipeline.mark.as.default.pipeline");

        if (options.contains(markAsDefault)) {
            click(findByXpath(map.get("crm.deal.pipeline.kebab.mark.as.default.pipeline")));
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        click(findByXpath(map.get("body")));
        click(findByXpath(map.get("crm.deal.pipeline.close")));
        click(findByXpath(map.get("crm.deal.pipeline.active")));

        return isDisplayed(findByXpath(String.format(map.get("crm.deal.kanban.pipeline.default.icon"), pipelineName)));
    }

    public boolean checkDefaultPipelineDisplayedInKanbanViewPipeline(final String pipelineName) {
        markAsDefaultPipeline(pipelineName);
        refresh();

        return getText(findByXpath(map.get("crm.deal.pipeline.active"))).equals(pipelineName);
    }


}
