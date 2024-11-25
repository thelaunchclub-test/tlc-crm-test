package com.twozo.test.deal;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.commons.json.JsonObject;
import com.twozo.page.deal.DealPage;
import com.twozo.page.deal.Pipeline;
import com.twozo.page.url.URL;
import com.twozo.test.BaseTest;
import com.twozo.test.TestCase;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.driver.service.WebNavigator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

public class DealTest extends BaseTest {

    protected static final String DEAL_PATH = "deal";

    private DealPage dealPage;
    WebNavigator webNavigator;
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

            for (final String key : stages.getKeys()) {
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
