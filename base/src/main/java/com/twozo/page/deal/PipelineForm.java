package com.twozo.page.deal;

import com.twozo.commons.json.JsonObject;
import com.twozo.page.xpath.XPathBuilder;
import com.twozo.web.driver.service.WebAutomationDriver;
import com.twozo.web.element.model.Element;
import com.twozo.web.element.model.LocatorType;
import com.twozo.web.element.service.WebPageElement;
import org.openqa.selenium.Keys;

import java.util.*;

public class PipelineForm extends DealPage {

    private static PipelineForm pipelineForm;
    private static final CharSequence A = "a";

    public PipelineForm(WebAutomationDriver webAutomationDriver) {
        super(webAutomationDriver);
    }

    public static PipelineForm getInstance(final WebAutomationDriver webAutomationDriver) {

        //   if (Objects.isNull(pipeline)) {
        pipelineForm = new PipelineForm(webAutomationDriver);
        // }

        return pipelineForm;
    }

    public WebPageElement getAddPipelineButton() {
        return findAboveElement(List.of(new Element(LocatorType.XPATH, XPathBuilder.getXPathByText(map.get("crm.deal.pipeline.add")),
                true), new Element(LocatorType.TAG_NAME, map.get("tag.button"), false)));
    }

    public WebPageElement getPipelineName() {
        return findBelowElement(List.of(
                new Element(LocatorType.TAG_NAME, map.get("tag.input"), false),
                new Element(LocatorType.XPATH, map.get("crm.deal.pipeline.form.pipeline.name"), true)));
    }

    public WebPageElement getMarkAsDefaultCheckbox() {
        return findLeftElement(List.of(
                new Element(LocatorType.TAG_NAME, map.get("tag.input"), false),
                new Element(LocatorType.XPATH, map.get("crm.deal.pipeline.form.mark.as.default.pipeline"), true)));
    }

    public WebPageElement getDealsRotAfter() {
        return findBelowElement(List.of(
                new Element(LocatorType.TAG_NAME, map.get("tag.input"), false),
                new Element(LocatorType.XPATH, map.get("crm.deal.pipeline.form.deals.rot.after"), true)));
    }

    public WebPageElement getAddStageButton() {
        return findByXpath(map.get("crm.deal.pipeline.form.add.stage"));
    }

    public WebPageElement getUpdateButton() {
        return findByText(map.get("crm.deal.pipeline.form.update"));
    }

    public WebPageElement getSaveButton() {
        return findByText(map.get("crm.deal.pipeline.form.save"));
    }

    public WebPageElement getSaveAndNewButton() {
        return findByText(map.get("crm.deal.pipeline.form.save.and.new"));
    }

    public WebPageElement getEditButton() {
        return findByXpath(map.get("crm.deal.pipeline.kebab.edit"));
    }

    public WebPageElement getDeleteButton() {
        return findByXpath(map.get("crm.deal.pipeline.kebab.delete"));
    }

    public WebPageElement getDeleteConfirmationButton() {
        return findByXpath(map.get("crm.deal.pipeline.delete.confirmation"));
    }

    protected String getStageBlock(final String stageName) {
        int rowNumber = 1;
        String xpath;

        while (true) {
            xpath = String.format("(//*[@placeholder='Enter Stage Name' and @value])[%d]", rowNumber);

            if (stageName.equals(getAttribute(findByXpath(xpath), "value"))) {
                break;
            }
            rowNumber++;
        }

        System.out.println(xpath);
        return xpath;
    }

    public boolean editPipelineName(final JsonObject jsonObject) {
        final String newPipelineName = jsonObject.getString("newPipelineName");
        final String pipelineBlock = String.format(map.get("crm.deal.pipeline.block"), jsonObject.getString("currentPipelineName"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        hoverByXpath(String.format("%s%s", pipelineBlock, map.get("crm.deal.pipeline.name")));
        click(findByXpath(String.format(map.get("crm.deal.pipeline.kebab"), pipelineBlock)));
        click(getEditButton());
        click(getPipelineName());
        mouseActions.keyDown(Keys.CONTROL)
                .sendKeys(A)
                .keyUp(Keys.CONTROL).sendKeys(newPipelineName)
                .perform();
        click(getUpdateButton());

        return newPipelineName.equals(getText(findByXpath(String.format("%s%s", String.format(map.get("crm.deal.pipeline.block"), newPipelineName), map.get("crm.deal.pipeline.name")))));
    }

    public boolean editPipelineStage(final JsonObject jsonObject) {
        System.out.println("current stage name" + jsonObject.getString("currentStage"));
        System.out.println("new stage name" + jsonObject.getString("newStage"));


        final String newStage = jsonObject.getString("newStage");
        final String pipelineBlock = String.format(map.get("crm.deal.pipeline.block"), jsonObject.getString("pipelineName"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        hoverByXpath(String.format("%s%s", pipelineBlock, map.get("crm.deal.pipeline.name")));
        click(findByXpath(String.format(map.get("crm.deal.pipeline.kebab"), pipelineBlock)));
        click(getEditButton());
        final String currentStage = getStageBlock(jsonObject.getString("currentStage"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        click(findByXpath(currentStage));
        mouseActions.keyDown(Keys.CONTROL)
                .sendKeys(A)
                .keyUp(Keys.CONTROL).sendKeys(newStage)
                .perform();
        click(getUpdateButton());

        return newStage.equals(getText(findByXpath(String.format("%s%s", pipelineBlock,
                String.format(map.get("crm.deal.pipeline.form.edited.stage"), newStage)))));
    }

    public boolean editPipelineRottingDays(final JsonObject jsonObject) {
        final String rottingDays = jsonObject.getString("dealRotAfter");
        final String pipelineBlock = String.format(map.get("crm.deal.pipeline.block"), jsonObject.getString("pipelineName"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        hoverByXpath(String.format("%s%s", pipelineBlock, map.get("crm.deal.pipeline.name")));
        click(findByXpath(String.format(map.get("crm.deal.pipeline.kebab"), pipelineBlock)));
        click(getEditButton());
        click(getDealsRotAfter());
        mouseActions.keyDown(Keys.CONTROL)
                .sendKeys(A)
                .keyUp(Keys.CONTROL).sendKeys(rottingDays)
                .perform();
        click(getUpdateButton());

        return getText(findByXpath(String.format("%s%s", pipelineBlock,
                String.format(map.get("crm.deal.pipeline.form.edited.deals.rot.after"), rottingDays)))).contains(rottingDays);
    }

    public boolean addStage(final JsonObject jsonObject) {
        final String stageName = jsonObject.getString("stageName");
        final String stageProbability = jsonObject.getString("stageProbability");
        final String pipelineBlock = String.format(map.get("crm.deal.pipeline.block"), jsonObject.getString("pipelineName"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        hoverByXpath(String.format("%s%s", pipelineBlock, map.get("crm.deal.pipeline.name")));
        click(findByXpath(String.format(map.get("crm.deal.pipeline.kebab"), pipelineBlock)));
        click(getEditButton());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        click(getAddStageButton());
        send(findByXpath(String.format(map.get("crm.deal.pipeline.form.edit.stage"), "")), stageName);

        final WebPageElement probability = findByXpath(String.format("%s%s", String.format(map.get("crm.deal.pipeline.form.edit.stage"), stageName)
                , map.get("crm.deal.pipeline.form.edit.probability")));

        click(probability);
        mouseActions.keyDown(Keys.CONTROL)
                .sendKeys(A)
                .keyUp(Keys.CONTROL).sendKeys(stageProbability)
                .perform();
        click(getUpdateButton());

        return getText(findByXpath(String.valueOf(String.valueOf(String.format("%s%s%s", pipelineBlock, String.format(map.get("crm.deal.pipeline.form.check.added.stage"),
                stageName), "/p[1]"))))).equals(stageName) && getText(findByXpath(String.format("%s%s%s", pipelineBlock,
                String.format(map.get("crm.deal.pipeline.form.check.added.stage"),
                        stageName), "/p[2]"))).contains(stageProbability);
    }

    public boolean deleteStage(final JsonObject jsonObject) {
        final String stageName = jsonObject.getString("stageName");
        final String pipelineBlock = String.format(map.get("crm.deal.pipeline.block"), jsonObject.getString("pipelineName"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        hoverByXpath(String.format("%s%s", pipelineBlock, map.get("crm.deal.pipeline.name")));
        click(findByXpath(String.format(map.get("crm.deal.pipeline.kebab"), pipelineBlock)));
        click(getEditButton());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(findByXpath(String.format("%s%s", String.format(map.get("crm.deal.pipeline.form.edit.stage"), stageName)
                , map.get("crm.deal.pipeline.form.close.icon"))));
        click(getUpdateButton());

        refresh();

        final Collection<WebPageElement> stagesAsElements = findElementsByXpath(String.format("%s%s", pipelineBlock, map.get("crm.deal.pipeline.stages.name")));
        final Collection<String> stages = new ArrayList<>();

        for (final WebPageElement stageAsElement : stagesAsElements) {
            stages.add(getText(stageAsElement));
        }

        return !stages.contains(stageName);
    }

    public boolean editProbability(final JsonObject jsonObject) {
        final String stageName = jsonObject.getString("stageName");
        final String newStageProbability = jsonObject.getString("probability");
        final String newStageProbabilityWithPercentage = String.format("%s%s", newStageProbability, "%");
        final String pipelineBlock = String.format(map.get("crm.deal.pipeline.block"), jsonObject.getString("pipelineName"));
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        hoverByXpath(String.format("%s%s", pipelineBlock, map.get("crm.deal.pipeline.name")));
        click(findByXpath(String.format(map.get("crm.deal.pipeline.kebab"), pipelineBlock)));


//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        waitTillVisible(map.get("crm.deal.pipeline.kebab.edit"));
        click(getEditButton());

        final WebPageElement probability = findByXpath(String.format("%s%s", String.format(map.get("crm.deal.pipeline.form.edit.stage"), stageName)
                , map.get("crm.deal.pipeline.form.edit.probability")));

        click(probability);
        mouseActions.keyDown(Keys.CONTROL)
                .sendKeys(A)
                .keyUp(Keys.CONTROL).sendKeys(newStageProbability)
                .perform();
        click(getUpdateButton());

        return isDisplayed(findByXpath(String.format("%s%s%s", pipelineBlock,
                String.format(map.get("crm.deal.pipeline.form.check.added.stage"),
                        stageName), String.format(map.get("crm.deal.pipeline.stage.probability.percentage"), newStageProbabilityWithPercentage))));
    }

    public boolean deletePipeline(final String pipelineName) {
        final String pipelineBlock = String.format(map.get("crm.deal.pipeline.block"), pipelineName);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        hoverByXpath(String.format("%s%s", pipelineBlock, map.get("crm.deal.pipeline.name")));
        click(findByXpath(String.format(map.get("crm.deal.pipeline.kebab"), pipelineBlock)));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(getDeleteButton());
        click(getDeleteConfirmationButton());

        try {
            return !isDisplayed(findByXpath(String.format(map.get("crm.deal.pipeline.specific.pipeline.name"), pipelineName)));
        } catch (Exception exception) {
            return true;
        }
    }

    public boolean createPipeline(final Pipeline pipeline) {
        final String pipelineName = pipeline.getPipelineName();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(getAddPipelineButton());
        send(getPipelineName(), pipelineName);

        if (pipeline.isDefault() != isSelected(getMarkAsDefaultCheckbox())) {
            click(getMarkAsDefaultCheckbox());
        }

        send(getDealsRotAfter(), pipeline.getDealRotAfter());


        for (final Map.Entry<String, String> entry : pipeline.getStages().entrySet()) {
            click(getAddStageButton());
            send(findByXpath(String.format(map.get("crm.deal.pipeline.form.edit.stage"), "")), entry.getKey());
            send(findByXpath(String.format("%s%s", String.format(map.get("crm.deal.pipeline.form.edit.stage"), entry.getKey())
                    , map.get("crm.deal.pipeline.form.edit.probability"))), entry.getValue());
        }

        click(getSaveButton());

        return isDisplayed(findByXpath(String.format(map.get("crm.deal.pipeline.specific.pipeline.name"), pipelineName)));
    }

    public void createPipeline(final String pipelineName) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(getAddPipelineButton());
        send(getPipelineName(), pipelineName);
        click(getSaveButton());
    }

    public boolean checkPipelineLimit() {
        final Collection<WebPageElement> pipelinesAsElement = findElementsByXpath(map.get("crm.deal.pipeline.count"));
        final String name = "pipeline check";
        String pipelineName;

        for (int i = 1; i <= 15 - pipelinesAsElement.size(); i++) {
            pipelineName = String.format("%s%d", name, i);

            createPipeline(pipelineName);
        }

        try {
            return !isDisplayed(getAddPipelineButton());
        } catch (Exception exception) {
            return true;
        }
    }

    public boolean checkStageLimit(final String pipeline) {
        final String pipelineName = pipeline;
        final String name = "stage";
        String stageName;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(getAddPipelineButton());
        send(getPipelineName(), pipelineName);
        final Collection<WebPageElement> stagesAsElement = findElementsByXpath(map.get("crm.deal.pipeline.form.stage.count"));

        for (int i = 1; i < 15 - stagesAsElement.size(); i++) {

            click(getAddStageButton());
            send(findByXpath(String.format(map.get("crm.deal.pipeline.form.edit.stage"), "")), String.format("%s%d", name, i));
        }

        try {
            return !isDisplayed(getAddStageButton());
        } catch (Exception exception) {
            return true;
        }
    }

    public boolean checkDefaultStagesArePresent() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(getAddPipelineButton());

        try {
            final String newStage = String.format(map.get("crm.deal.pipeline.form.edit.stage"), map.get("crm.deal.pipeline.default.stage.new"));
            final String wonStage = String.format(map.get("crm.deal.pipeline.form.edit.stage"), map.get("crm.deal.pipeline.default.stage.won"));
            final String lostStage = String.format(map.get("crm.deal.pipeline.form.edit.stage"), map.get("crm.deal.pipeline.default.stage.lost"));

            isDisplayed(findByXpath(newStage));
            isDisplayed(findByXpath(String.format("%s%s", newStage, map.get("crm.deal.pipeline.form.probability.hundred"))));
            isDisplayed(findByXpath(wonStage));
            isDisplayed(findByXpath(String.format("%s%s", wonStage, map.get("crm.deal.pipeline.form.probability.hundred"))));
            isDisplayed(findByXpath(lostStage));
            isDisplayed(findByXpath(String.format("%s%s", lostStage, map.get("crm.deal.pipeline.form.probability.zero"))));

            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean checkDuplicateStage() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        hoverByXpath(map.get("crm.deal.pipeline.names"));
        click(findByXpath(map.get("crm.deal.pipeline.default.pipeline.kebab")));
        click(findByXpath(map.get("crm.deal.pipeline.kebab.edit")));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        click(getAddStageButton());
        send(findByXpath(String.format(map.get("crm.deal.pipeline.form.edit.stage"), "")), map.get("crm.deal.pipeline.default.stage.new"));
        click(getUpdateButton());

        try {
            return isDisplayed(findByXpath(map.get("crm.deal.pipeline.form.duplicate.stage")));
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean checkDuplicatePipeline() {
        createPipeline(getText(findByXpath(map.get("crm.deal.pipeline.names"))));

        try {
            return isDisplayed(findByXpath(map.get("crm.deal.pipeline.form.duplicate.pipeline")));
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean verifyStageAddedBetweenDefaultAndClosedStage() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        hoverByXpath(map.get("crm.deal.pipeline.names"));
        click(findByXpath(map.get("crm.deal.pipeline.default.pipeline.kebab")));
        click(findByXpath(map.get("crm.deal.pipeline.kebab.edit")));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        click(getAddStageButton());
        final String stageName = "checkNewlyAddedStagePosition";

        send(findByXpath(String.format(map.get("crm.deal.pipeline.form.edit.stage"), "")), stageName);

        final WebPageElement probability = findByXpath(String.format(
                "%s%s",
                String.format(map.get("crm.deal.pipeline.form.edit.stage"), stageName),
                map.get("crm.deal.pipeline.form.edit.probability")
        ));

        click(probability);
        mouseActions.keyDown(Keys.CONTROL)
                .sendKeys("A") // Select all
                .keyUp(Keys.CONTROL)
                .sendKeys("50")
                .perform();

        click(findByXpath(map.get("body")));

        final Collection<WebPageElement> stagesAsElements = findElementsByXpath(map.get("crm.deal.pipeline.form.stage.count"));

        final List<String> stages = new ArrayList<>();
        for (final WebPageElement stageAsElement : stagesAsElements) {
            System.out.println(getText(stageAsElement));
            stages.add(getText(stageAsElement));
        }

        System.out.println("New Stage Index: " + stages.indexOf(map.get("crm.deal.pipeline.default.stage.new")));
        System.out.println("Added Stage Index: " + stages.indexOf(stageName));
        System.out.println("Lost Stage Index: " + stages.indexOf(map.get("crm.deal.pipeline.default.stage.lost")));

        return stages.indexOf(map.get("crm.deal.pipeline.default.stage.new")) < stages.indexOf(stageName) &&
                stages.indexOf(stageName) > stages.indexOf(map.get("crm.deal.pipeline.default.stage.lost"));

    }

}

