package com.twozo.test.deal;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.page.deal.PipelinePage;
import com.twozo.page.url.URL;
import com.twozo.test.TestCase;
import com.twozo.web.driver.service.WebAutomationDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class PipelineTest extends DealTest {
    private PipelinePage pipelinePage;
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

        pipelinePage = PipelinePage.getInstance(automationDriver);
        pipelinePage.switchToPipeline();
    }

    @AfterMethod
    public void after() {
        automationDriver.close();
    }

    @Test
    public void checkDefaultPipelineAndStages() {
        Assert.assertTrue(pipelinePage.checkDefaultPipelineAndStages());
    }

    @Test(dataProvider = "defaultPipeline")
    public void markAsDefaultPipeline(final Object object) {
        Assert.assertTrue(pipelinePage.markAsDefaultPipeline(((TestCase) object).input.getString("pipelineName")));
    }

    @Test(dataProvider = "defaultPipeline")
    public void checkDefaultPipelineDisplayedInKanbanViewPipeline(final Object object) {
        Assert.assertTrue(pipelinePage.checkDefaultPipelineDisplayedInKanbanViewPipeline(((TestCase) object).input.getString("pipelineName")));
    }

    @Test
    public void verifyPipelineOrderInEditDrawerMatchesDropdown() {
        Assert.assertTrue(pipelinePage.verifyPipelineOrderInEditDrawerMatchesDropdown());
    }

}
