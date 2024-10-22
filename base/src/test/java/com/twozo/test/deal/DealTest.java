package com.twozo.test.deal;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.commons.json.JsonObject;
import com.twozo.page.deal.DealPage;
import com.twozo.page.deal.Pipeline;
import com.twozo.page.deal.PipelineForm;
import com.twozo.page.url.URL;
import com.twozo.test.BaseTest;
import com.twozo.test.TestCase;
import com.twozo.test.TestDataProvider;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.driver.service.WebNavigator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

public class DealTest extends BaseTest {

    private DealPage dealPage;
    WebNavigator webNavigator;
    private WebAutomationDriver automationDriver;


    @DataProvider(name = "defaultPipeline")
    public static Object[][] getPipelineForDefault() {
        return new TestDataProvider().getTestCases("deal/data/DefaultPipeline.json");
    }

    @DataProvider(name = "editPipelineName")
    public static Object[][] getNewPipelineName() {
        return new TestDataProvider().getTestCases("deal/data/EditPipelineName.json");
    }

    @DataProvider(name = "editPipelineStage")
    public static Object[][] getNewPipelineStage() {
        return new TestDataProvider().getTestCases("deal/data/EditPipelineStage.json");
    }

    @DataProvider(name = "editPipelineRottingDays")
    public static Object[][] getPipelineRottingDays() {
        return new TestDataProvider().getTestCases("deal/data/EditPipelineRottingDays.json");
    }

    @DataProvider(name = "addStage")
    public static Object[][] getAddStageName() {
        return new TestDataProvider().getTestCases("deal/data/AddStage.json");
    }

    @DataProvider(name = "deleteStage")
    public static Object[][] getDeleteStageName() {
        return new TestDataProvider().getTestCases("deal/data/DeleteStage.json");
    }

    @DataProvider(name = "editProbability")
    public static Object[][] getNewProbability() {
        return new TestDataProvider().getTestCases("deal/data/EditProbability.json");
    }

    @DataProvider(name = "deletePipeline")
    public static Object[][] getDeletePipelineName() {
        return new TestDataProvider().getTestCases("deal/data/DeletePipeline.json");
    }

    @DataProvider(name = "createPipeline")
    public static Object[][] getPipelineName() {
        return new TestDataProvider().getTestCases("deal/data/CreatePipeline.json");
    }

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

        dealPage = DealPage.getInstance(automationDriver);
    }

    @AfterMethod
    public void after() {
        automationDriver.close();
    }

    protected Pipeline getPipeline(final Object object) {
        final TestCase testCase = (TestCase) object;
        final JsonObject input = testCase.input;
        final Pipeline pipeline = new Pipeline();

        if (input.containsKey("pipelineName")) {
            pipeline.setPipelineName(input.getString("pipelineName"));
        }
        if (input.containsKey("isDefault")) {
            pipeline.setDefault(input.getBoolean("isDefault"));
        }

        if (input.containsKey("dealRotAfter")) {
            pipeline.setDealRotAfter(input.getString("dealRotAfter"));
        }

        if (input.containsKey("stage")) {
            final JsonObject stages = input.getJsonObject("stage");
            final Map<String, String> stageMap = new LinkedHashMap<>();

            for (final String key : stages.keySet()) {
                stageMap.put(key, stages.getString(key));
            }

            pipeline.setStages(stageMap);
        }

        return pipeline;
    }

    @Test
    public void verifyPipelineChangeInKanbanReflectedInAddDealForm() {
        Assert.assertTrue(dealPage.verifyPipelineChangeInKanbanReflectedInAddDealForm());
        //dealPage.verifyPipelineChangeInKanbanReflectedInAddDealForm();
    }
}
