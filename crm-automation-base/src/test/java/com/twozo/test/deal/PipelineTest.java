package com.twozo.test.deal;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.crm.automation.base.page.deal.PipelinePage;
import com.twozo.test.TestCase;
import com.twozo.test.TestDataProvider;
import com.twozo.web.driver.service.WebAutomationDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class PipelineTest extends DealTest {

    protected static final String PIPELINE_PATH = Paths.get(DEAL_PATH, "pipeline").toString();

    private PipelinePage pipelinePage;
    private WebAutomationDriver automationDriver;

    @DataProvider(name = "defaultPipeline")
    public static Object[][] getPipelineForDefault() {
        return new TestDataProvider().getTestData(getFilePath(PIPELINE_PATH, "DefaultPipeline.json"));
    }

    @BeforeMethod
    public void before() {
        automationDriver = WebAutomationDriver.get();
        pipelinePage = PipelinePage.getInstance(automationDriver);

        pipelinePage.navigateTo(link);

        for (final BrowserCookie cookie : cookies) {
            pipelinePage.addCookie(cookie);
        }

        pipelinePage.maximize();
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
