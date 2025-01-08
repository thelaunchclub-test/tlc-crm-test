package com.twozo.test.deal;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.crm.automation.page.deal.KanbanView;
import com.twozo.crm.automation.page.url.URL;
import com.twozo.web.driver.service.WebAutomationDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class KanbanViewTest extends DealTest {

    private KanbanView kanbanView;
    private WebAutomationDriver automationDriver;

    @BeforeMethod
    public void before() {
        automationDriver = WebAutomationDriver.get();
        kanbanView = KanbanView.getInstance(automationDriver);

        kanbanView.navigateTo(link);

        for (final BrowserCookie cookie : cookies) {
            kanbanView.addCookie(cookie);
        }

        kanbanView.maximize();
        kanbanView.navigateTo(URL.DEALS);
    }

    @AfterMethod
    public void after() {
        automationDriver.close();
    }

    @Test
    public void checkDefaultStagesInKanbanView() {
        Assert.assertTrue(kanbanView.checkDefaultStagesInKanbanView());
    }
}
