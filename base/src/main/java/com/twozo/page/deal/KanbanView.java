package com.twozo.page.deal;

import com.twozo.web.driver.service.WebAutomationDriver;

public class KanbanView extends DealPage {

    private static KanbanView kanbanView;

    public KanbanView(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static KanbanView getInstance(final WebAutomationDriver webAutomationDriver) {

        // if (Objects.isNull(kanbanView)) {
        kanbanView = new KanbanView(webAutomationDriver);
        //}

        return kanbanView;
    }

    public boolean checkDefaultStagesInKanbanView() {
        try {
            isDisplayed(findByXpath(map.get("crm.deal.kanban.pipeline.default.stage.new")));
            isDisplayed(findByXpath(map.get("crm.deal.kanban.pipeline.default.stage.won")));
            isDisplayed(findByXpath(map.get("crm.deal.kanban.pipeline.default.stage.lost")));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
