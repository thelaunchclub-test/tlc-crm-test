package com.twozo.crm.automation.page.deal;

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
            isDisplayed(findByXpath(CRM_LOCATOR_REGISTRY.get("crm.deal.kanban.pipeline.default.stage.new")));
            isDisplayed(findByXpath(CRM_LOCATOR_REGISTRY.get("crm.deal.kanban.pipeline.default.stage.won")));
            isDisplayed(findByXpath(CRM_LOCATOR_REGISTRY.get("crm.deal.kanban.pipeline.default.stage.lost")));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
