package com.twozo.page.deal;

import com.twozo.web.driver.service.WebAutomationDriver;

public class KanbanView extends Deal{

    private static KanbanView kanbanView;

    public KanbanView(final WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static KanbanView getInstance(final WebAutomationDriver webAutomationDriver){

       // if (Objects.isNull(kanbanView)) {
            kanbanView = new KanbanView(webAutomationDriver);
        //}

        return kanbanView;
    }
}
