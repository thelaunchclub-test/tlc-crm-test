package com.twozo.test.deal;

import com.twozo.commons.cookie.BrowserCookie;
import com.twozo.commons.json.JsonObject;
import com.twozo.page.deal.DealPage;
import com.twozo.page.deal.Pipeline;
import com.twozo.page.url.URL;
import com.twozo.test.BaseTest;
import com.twozo.test.TestCase;
import com.twozo.web.driver.service.WebAutomationDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class DealTest extends BaseTest {

    protected static final String DEAL_PATH = "deal";

    private DealPage dealPage;
    private WebAutomationDriver automationDriver;

    @BeforeMethod
    public void before() {
        automationDriver = WebAutomationDriver.get();
        dealPage = DealPage.getInstance(automationDriver);

        dealPage.navigateTo(link);

        for (final BrowserCookie cookie : cookies) {
            dealPage.addCookie(cookie);
        }

        dealPage.maximize();
        dealPage.navigateTo(URL.DEALS);
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
    }
}
