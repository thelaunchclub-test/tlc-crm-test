package com.twozo.test.deal;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.page.deal.PipelineForm;
import com.twozo.page.url.URL;
import com.twozo.test.TestCase;
import com.twozo.web.driver.service.WebAutomationDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class PipelineFormTest extends DealTest {
    private PipelineForm pipelineForm;
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

        pipelineForm = PipelineForm.getInstance(automationDriver);
        pipelineForm.switchToPipeline();
    }

    @AfterMethod
    public void after() {
        automationDriver.close();
    }

    @Test(dataProvider = "editPipelineName")
    public void editPipelineName(final Object object) {
        Assert.assertTrue(pipelineForm.editPipelineName(((TestCase) object).input));
    }

    @Test(dataProvider = "editPipelineStage")
    public void editPipelineStage(final Object object) {
        Assert.assertTrue(pipelineForm.editPipelineStage(((TestCase) object).input));
    }

    @Test(dataProvider = "editPipelineRottingDays")
    public void editPipelineRottingDays(final Object object) {
        Assert.assertTrue(pipelineForm.editPipelineRottingDays(((TestCase) object).input));
    }

    @Test(dataProvider = "addStage")
    public void addStage(final Object object) {
        Assert.assertTrue(pipelineForm.addStage(((TestCase) object).input));
    }

    @Test(dataProvider = "deleteStage")
    public void deleteStage(final Object object) {
        Assert.assertTrue(pipelineForm.deleteStage(((TestCase) object).input));
    }

    @Test(dataProvider = "editProbability")
    public void editProbability(final Object object) {
        Assert.assertTrue(pipelineForm.editProbability(((TestCase) object).input));
    }

    @Test(dataProvider = "deletePipeline")
    public void deletePipeline(final Object object) {
        Assert.assertTrue(pipelineForm.deletePipeline(((TestCase) object).input.getString("pipelineName")));
    }

    @Test(dataProvider = "createPipeline")
    public void createPipeline(final Object object) {
        Assert.assertTrue(pipelineForm.createPipeline(getPipeline(object)));
    }

    @Test
    public void checkPipelineLimit() {
        Assert.assertTrue(pipelineForm.checkPipelineLimit());
    }

    @Test
    public void checkStageLimit() {
        Assert.assertTrue(pipelineForm.checkStageLimit("stageCheck"));
    }

    @Test
    public void checkDefaultStagesArePresent() {
        Assert.assertTrue(pipelineForm.checkDefaultStagesArePresent());
    }

    @Test
    public void checkDuplicateStage() {
        Assert.assertTrue(pipelineForm.checkDuplicateStage());
    }

    @Test
    public void checkDuplicatePipeline() {
        Assert.assertTrue(pipelineForm.checkDuplicatePipeline());
    }

    @Test
    public void verifyStageAddedBetweenDefaultAndClosedStage() {
        Assert.assertTrue(pipelineForm.verifyStageAddedBetweenDefaultAndClosedStage());
    }


    @Test
    public void verifyDefaultStagesCannotBeDeleted() {
        Assert.assertTrue(pipelineForm.verifyDefaultStagesCannotBeDeleted());
    }

    @Test
    public void verifyStageDefaultProbability() {
        Assert.assertTrue(pipelineForm.verifyStageDefaultProbability());
    }

    @Test
    public void verifyDefaultRottingDays() {
        Assert.assertTrue(pipelineForm.verifyDefaultRottingDays());
    }

    @Test
    public void verifyPipelineNameMandatory() {
        Assert.assertTrue(pipelineForm.verifyPipelineNameMandatory());
    }

    @Test
    public void verifyDefaultPipelineMarkAsDefaultCannotBeUnchecked() {
        Assert.assertTrue(pipelineForm.verifyDefaultPipelineMarkAsDefaultCannotBeUnchecked());
    }

    @Test
    public void verifyMarkAsDefaultCanBeCheckedForNonDefaultPipelines() {
        Assert.assertTrue(pipelineForm.verifyMarkAsDefaultCanBeCheckedForNonDefaultPipelines());
    }
}
