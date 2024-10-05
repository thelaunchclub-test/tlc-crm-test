package com.twozo.test.deal;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.page.deal.KanbanView;
import com.twozo.page.url.URL;
import com.twozo.web.driver.service.WebAutomationDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class KanbanViewTest extends DealTest {

    private KanbanView kanbanView;
    private WebAutomationDriver automationDriver;

    @BeforeMethod
    public void before() {
        automationDriver = WebAutomationDriver.get();
        webNavigator = automationDriver.getWebNavigator();
        webNavigator.to(link);

        for (final BrowserCookie cookie : cookies) {
            automationDriver.getSessionCookie().addCookie(cookie);
        }

        automationDriver.getWebWindowHandler().maximize();
        automationDriver.getImplicitWaitHandler().implicitWait(Duration.ofSeconds(10));
        automationDriver.getWebWindowHandler().maximize();
        webNavigator.to(URL.DEALS);

        kanbanView = KanbanView.getInstance(automationDriver);
        kanbanView.switchToPipeline();
    }

//    @AfterMethod
//    public void after() {
//        automationDriver.close();
//    }

    @Test
    public void checkDefaultStagesInKanbanView() {
        Assert.assertTrue(kanbanView.checkDefaultStagesInKanbanView());
    }
}
