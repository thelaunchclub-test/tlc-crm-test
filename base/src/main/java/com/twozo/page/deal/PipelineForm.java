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
        return findAboveElement(List.of(new Element(LocatorType.XPATH, XPathBuilder.getXPathByText(MAP.get("crm.deal.pipeline.add")),
                true), new Element(LocatorType.TAG_NAME, MAP.get("tag.button"), false)));
    }

    public WebPageElement getPipelineName() {
        return findBelowElement(List.of(
                new Element(LocatorType.TAG_NAME, MAP.get("tag.input"), false),
                new Element(LocatorType.XPATH, MAP.get("crm.deal.pipeline.form.pipeline.name"), true)));
    }

    public WebPageElement getMarkAsDefaultCheckbox() {
        return findLeftElement(List.of(
                new Element(LocatorType.TAG_NAME, MAP.get("tag.input"), false),
                new Element(LocatorType.XPATH, MAP.get("crm.deal.pipeline.form.mark.as.default.pipeline"), true)));
    }

    public WebPageElement getDealsRotAfter() {
        return findBelowElement(List.of(
                new Element(LocatorType.TAG_NAME, MAP.get("tag.input"), false),
                new Element(LocatorType.XPATH, MAP.get("crm.deal.pipeline.form.deals.rot.after"), true)));
    }

    public WebPageElement getAddStageButton() {
        return findByXpath(MAP.get("crm.deal.pipeline.form.add.stage"));
    }

    public WebPageElement getUpdateButton() {
        return findByText(MAP.get("crm.deal.pipeline.form.update"));
    }

    public WebPageElement getSaveButton() {
        return findByText(MAP.get("crm.deal.pipeline.form.save"));
    }

    public WebPageElement getSaveAndNewButton() {
        return findByText(MAP.get("crm.deal.pipeline.form.save.and.new"));
    }

    public WebPageElement getEditButton() {
        return findByXpath(MAP.get("crm.deal.pipeline.kebab.edit"));
    }

    public WebPageElement getDeleteButton() {
        return findByXpath(MAP.get("crm.deal.pipeline.kebab.delete"));
    }

    public WebPageElement getDeleteConfirmationButton() {
        return findByXpath(MAP.get("crm.deal.pipeline.delete.confirmation"));
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
        final String pipelineBlock = String.format(MAP.get("crm.deal.pipeline.block"), jsonObject.getString("currentPipelineName"));

        //waitTillVisible(format(pipelineBlock, MAP.get("crm.deal.pipeline.name")));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        hoverByXpath(format(pipelineBlock, MAP.get("crm.deal.pipeline.name")));
        click(findByXpath(String.format(MAP.get("crm.deal.pipeline.kebab"), pipelineBlock)));
        click(getEditButton());
        click(getPipelineName());
        mouseActions.keyDown(Keys.CONTROL)
                .sendKeys(A)
                .keyUp(Keys.CONTROL).sendKeys(newPipelineName)
                .perform();
        click(getUpdateButton());

        return newPipelineName.equals(getText(findByXpath(format(String.format(MAP.get("crm.deal.pipeline.block"), newPipelineName), MAP.get("crm.deal.pipeline.name")))));
    }

    public boolean editPipelineStage(final JsonObject jsonObject) {
        System.out.println("current stage name" + jsonObject.getString("currentStage"));
        System.out.println("new stage name" + jsonObject.getString("newStage"));


        final String newStage = jsonObject.getString("newStage");
        final String pipelineBlock = String.format(MAP.get("crm.deal.pipeline.block"), jsonObject.getString("pipelineName"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        // waitTillVisible(format(pipelineBlock, MAP.get("crm.deal.pipeline.name")));
        hoverByXpath(format(pipelineBlock, MAP.get("crm.deal.pipeline.name")));
        click(findByXpath(String.format(MAP.get("crm.deal.pipeline.kebab"), pipelineBlock)));
        click(getEditButton());
        final String currentStage = getStageBlock(jsonObject.getString("currentStage"));

        //waitTillVisible(currentStage);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        click(findByXpath(currentStage));
        mouseActions.keyDown(Keys.CONTROL)
                .sendKeys(A)
                .keyUp(Keys.CONTROL).sendKeys(newStage)
                .perform();
        click(getUpdateButton());

        return newStage.equals(getText(findByXpath(format(pipelineBlock,
                String.format(MAP.get("crm.deal.pipeline.form.edited.stage"), newStage)))));
    }

    public boolean editPipelineRottingDays(final JsonObject jsonObject) {
        final String rottingDays = jsonObject.getString("dealRotAfter");
        final String pipelineBlock = String.format(MAP.get("crm.deal.pipeline.block"), jsonObject.getString("pipelineName"));

        //waitTillVisible(format(pipelineBlock, MAP.get("crm.deal.pipeline.name")));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        hoverByXpath(format(pipelineBlock, MAP.get("crm.deal.pipeline.name")));
        click(findByXpath(String.format(MAP.get("crm.deal.pipeline.kebab"), pipelineBlock)));
        click(getEditButton());
        click(getDealsRotAfter());
        mouseActions.keyDown(Keys.CONTROL)
                .sendKeys(A)
                .keyUp(Keys.CONTROL).sendKeys(rottingDays)
                .perform();
        click(getUpdateButton());

        return getText(findByXpath(format(pipelineBlock,
                String.format(MAP.get("crm.deal.pipeline.form.edited.deals.rot.after"), rottingDays)))).contains(rottingDays);
    }

    public boolean addStage(final JsonObject jsonObject) {
        final String stageName = jsonObject.getString("stageName");
        final String stageProbability = jsonObject.getString("stageProbability");
        final String pipelineBlock = String.format(MAP.get("crm.deal.pipeline.block"), jsonObject.getString("pipelineName"));

        //  waitTillVisible(format(pipelineBlock, MAP.get("crm.deal.pipeline.name")));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        hoverByXpath(format(pipelineBlock, MAP.get("crm.deal.pipeline.name")));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        click(findByXpath(String.format(MAP.get("crm.deal.pipeline.kebab"), pipelineBlock)));
        click(getEditButton());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        //  waitTillVisible(MAP.get("crm.deal.pipeline.form.add.stage"));
        click(getAddStageButton());
        send(findByXpath(String.format(MAP.get("crm.deal.pipeline.form.edit.stage"), "")), stageName);

        final WebPageElement probability = findByXpath(format(String.format(MAP.get("crm.deal.pipeline.form.edit.stage"), stageName)
                , MAP.get("crm.deal.pipeline.form.edit.probability")));

        click(probability);
        mouseActions.keyDown(Keys.CONTROL)
                .sendKeys(A)
                .keyUp(Keys.CONTROL).sendKeys(stageProbability)
                .perform();
        click(getUpdateButton());

        return getText(findByXpath(String.valueOf(String.valueOf(String.format("%s%s%s", pipelineBlock, String.format(MAP.get("crm.deal.pipeline.form.check.added.stage"),
                stageName), "/p[1]"))))).equals(stageName) && getText(findByXpath(String.format("%s%s%s", pipelineBlock,
                String.format(MAP.get("crm.deal.pipeline.form.check.added.stage"),
                        stageName), "/p[2]"))).contains(stageProbability);
    }

    public boolean deleteStage(final JsonObject jsonObject) {
        final String stageName = jsonObject.getString("stageName");
        final String pipelineBlock = String.format(MAP.get("crm.deal.pipeline.block"), jsonObject.getString("pipelineName"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        // waitTillVisible(format(pipelineBlock, MAP.get("crm.deal.pipeline.name")));
        hoverByXpath(format(pipelineBlock, MAP.get("crm.deal.pipeline.name")));
        click(findByXpath(String.format(MAP.get("crm.deal.pipeline.kebab"), pipelineBlock)));
        click(getEditButton());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        //waitTillVisible(format(String.format(MAP.get("crm.deal.pipeline.form.edit.stage"), stageName)
//                , MAP.get("crm.deal.pipeline.form.close.icon")));
        click(findByXpath(format(String.format(MAP.get("crm.deal.pipeline.form.edit.stage"), stageName)
                , MAP.get("crm.deal.pipeline.form.close.icon"))));
        click(getUpdateButton());

        refresh();

        final Collection<WebPageElement> stagesAsElements = findElementsByXpath(format(pipelineBlock, MAP.get("crm.deal.pipeline.stages.name")));
        final Collection<String> stages = new ArrayList<>();

        for (final WebPageElement stageAsElement : stagesAsElements) {
            stages.add(getText(stageAsElement));
        }

        return !stages.contains(stageName);
    }

    public boolean editProbability(final JsonObject jsonObject) {
        final String stageName = jsonObject.getString("stageName");
        final String newStageProbability = jsonObject.getString("probability");
        final String newStageProbabilityWithPercentage = format(newStageProbability, "%");
        final String pipelineBlock = String.format(MAP.get("crm.deal.pipeline.block"), jsonObject.getString("pipelineName"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        hoverByXpath(format(pipelineBlock, MAP.get("crm.deal.pipeline.name")));
        click(findByXpath(String.format(MAP.get("crm.deal.pipeline.kebab"), pipelineBlock)));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        //waitTillVisible(MAP.get("crm.deal.pipeline.kebab.edit"));
        click(getEditButton());
        final WebPageElement probability = findByXpath(format(String.format(MAP.get("crm.deal.pipeline.form.edit.stage"), stageName)
                , MAP.get("crm.deal.pipeline.form.edit.probability")));

        click(probability);
        mouseActions.keyDown(Keys.CONTROL)
                .sendKeys(A)
                .keyUp(Keys.CONTROL).sendKeys(newStageProbability)
                .perform();
        click(getUpdateButton());

        return isDisplayed(findByXpath(String.format("%s%s%s", pipelineBlock,
                String.format(MAP.get("crm.deal.pipeline.form.check.added.stage"),
                        stageName), String.format(MAP.get("crm.deal.pipeline.stage.probability.percentage"), newStageProbabilityWithPercentage))));
    }

    public boolean deletePipeline(final String pipelineName) {
        final String pipelineBlock = String.format(MAP.get("crm.deal.pipeline.block"), pipelineName);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        hoverByXpath(format(pipelineBlock, MAP.get("crm.deal.pipeline.name")));
        click(findByXpath(String.format(MAP.get("crm.deal.pipeline.kebab"), pipelineBlock)));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        //waitTillVisible(MAP.get("crm.deal.pipeline.kebab.delete"));
        click(getDeleteButton());
        click(getDeleteConfirmationButton());
        refresh();

        final Collection<WebPageElement> pipelineNamesAsElement = findElementsByXpath(MAP.get("crm.deal.pipeline.names"));
        final Collection<String> pipelineNames = new ArrayList<>();

        for (final WebPageElement webPageElement : pipelineNamesAsElement) {
            pipelineNames.add(getText(webPageElement));
        }

        for (String name : pipelineNames) {
            System.out.println(name);
        }


        return !pipelineNames.contains(pipelineName);

    }

    public boolean createPipeline(final Pipeline pipeline) {
        final String pipelineName = pipeline.getPipelineName();

        // waitTillVisible(XPathBuilder.getXPathByText(MAP.get("crm.deal.pipeline.add")));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        click(getAddPipelineButton());
        send(getPipelineName(), pipelineName);

        if (pipeline.isDefault() != isSelected(getMarkAsDefaultCheckbox())) {
            click(getMarkAsDefaultCheckbox());
        }

        send(getDealsRotAfter(), pipeline.getDealRotAfter());


        for (final Map.Entry<String, String> entry : pipeline.getStages().entrySet()) {
            click(getAddStageButton());
            send(findByXpath(String.format(MAP.get("crm.deal.pipeline.form.edit.stage"), "")), entry.getKey());
            send(findByXpath(String.format(String.format(MAP.get("crm.deal.pipeline.form.edit.stage"), entry.getKey())
                    , MAP.get("crm.deal.pipeline.form.edit.probability"))), entry.getValue());
        }

        click(getSaveButton());

        return isDisplayed(findByXpath(String.format(MAP.get("crm.deal.pipeline.specific.pipeline.name"), pipelineName)));
    }

    public void createPipeline(final String pipelineName) {
        try {
            Thread.sleep(7000);
        } catch (Exception e) {
        }
        //waitTillVisible(XPathBuilder.getXPathByText(MAP.get("crm.deal.pipeline.add")));
        click(getAddPipelineButton());
        send(getPipelineName(), pipelineName);
        click(getSaveButton());
    }

    public boolean checkPipelineLimit() {
        final Collection<WebPageElement> pipelinesAsElement = findElementsByXpath(MAP.get("crm.deal.pipeline.count"));
        final String name = "pipeline check";
        String pipelineName;

        for (int i = 1; i <= 15 - pipelinesAsElement.size(); i++) {
            pipelineName = String.format("%s%d", name, i);

            createPipeline(pipelineName);
        }

        try {
            click(getAddPipelineButton());
            return false;
        } catch (Exception exception) {
            return true;
        }
    }

    public boolean checkStageLimit(final String pipeline) {
        final String name = "stage";

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        //waitTillVisible(XPathBuilder.getXPathByText(MAP.get("crm.deal.pipeline.add")));
        click(getAddPipelineButton());
        send(getPipelineName(), pipeline);
        final Collection<WebPageElement> stagesAsElement = findElementsByXpath(MAP.get("crm.deal.pipeline.form.stage.count"));

        for (int i = 1; i <= 15 - stagesAsElement.size(); i++) {

            click(getAddStageButton());
            send(findByXpath(String.format(MAP.get("crm.deal.pipeline.form.edit.stage"), "")), String.format("%s%d", name, i));
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        try {
            isDisplayed(getAddStageButton());
            return false;
        } catch (Exception exception) {
            return true;
        }
    }

    public boolean checkDefaultStagesArePresent() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        // waitTillVisible(XPathBuilder.getXPathByText(MAP.get("crm.deal.pipeline.add")));
        click(getAddPipelineButton());

        try {
            final String newStage = String.format(MAP.get("crm.deal.pipeline.form.edit.stage"), MAP.get("crm.deal.pipeline.default.stage.new"));
            final String wonStage = String.format(MAP.get("crm.deal.pipeline.form.edit.stage"), MAP.get("crm.deal.pipeline.default.stage.won"));
            final String lostStage = String.format(MAP.get("crm.deal.pipeline.form.edit.stage"), MAP.get("crm.deal.pipeline.default.stage.lost"));

            isDisplayed(findByXpath(newStage));
            isDisplayed(findByXpath(format(newStage, MAP.get("crm.deal.pipeline.form.probability.hundred"))));
            isDisplayed(findByXpath(wonStage));
            isDisplayed(findByXpath(format(wonStage, MAP.get("crm.deal.pipeline.form.probability.hundred"))));
            isDisplayed(findByXpath(lostStage));
            isDisplayed(findByXpath(format(lostStage, MAP.get("crm.deal.pipeline.form.probability.zero"))));

            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean checkDuplicateStage() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        //waitTillVisible(MAP.get("crm.deal.pipeline.names"));
        hoverByXpath(MAP.get("crm.deal.pipeline.names"));
        click(findByXpath(MAP.get("crm.deal.pipeline.default.pipeline.kebab")));
        click(findByXpath(MAP.get("crm.deal.pipeline.kebab.edit")));

        // waitTillVisible(MAP.get("crm.deal.pipeline.form.add.stage"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        click(getAddStageButton());
        send(findByXpath(String.format(MAP.get("crm.deal.pipeline.form.edit.stage"), "")), MAP.get("crm.deal.pipeline.default.stage.new"));
        click(getUpdateButton());

        try {
            return isDisplayed(findByXpath(MAP.get("crm.deal.pipeline.form.duplicate.stage")));
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean checkDuplicatePipeline() {
        createPipeline(getText(findByXpath(MAP.get("crm.deal.pipeline.names"))));

        try {
            return isDisplayed(findByXpath(MAP.get("crm.deal.pipeline.form.duplicate.pipeline")));
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean verifyStageAddedBetweenDefaultAndClosedStage() {
        final String pipelineName = "New Pipeline to check stage position";
        final String newStage = "new stage";

        // waitTillVisible(XPathBuilder.getXPathByText(MAP.get("crm.deal.pipeline.add")));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        click(getAddPipelineButton());
        send(getPipelineName(), pipelineName);


        click(getAddStageButton());
        send(findByXpath(String.format(MAP.get("crm.deal.pipeline.form.edit.stage"), "")), newStage);
        click(getSaveButton());

        final Collection<WebPageElement> stagesAsElements = findElementsByXpath(String.format(MAP.get("crm.deal.pipeline.pipeline.stages"), pipelineName));

        final List<String> stages = new ArrayList<>();

        for (final WebPageElement stageAsElement : stagesAsElements) {
            // System.out.println(getText(stageAsElement));
            stages.add(getText(stageAsElement));
        }

        for (String stage : stages) {
            System.out.println(stage);
        }

        System.out.println("New Stage Index: " + stages.indexOf(MAP.get("crm.deal.pipeline.default.stage.new")));
        System.out.println("Added Stage Index: " + stages.indexOf(newStage));
        System.out.println("Lost Stage Index: " + stages.indexOf(MAP.get("crm.deal.pipeline.default.stage.lost")));

        return stages.indexOf(MAP.get("crm.deal.pipeline.default.stage.new")) < stages.indexOf(newStage) &&
                stages.indexOf(newStage) < stages.indexOf(MAP.get("crm.deal.pipeline.default.stage.lost"));
    }

    public boolean verifyDefaultStagesCannotBeDeleted() {
        final Collection<WebPageElement> stagesAsElements = findElementsByXpath(MAP.get("crm.deal.pipeline.default.pipeline.stages.block"));

        final int numberOfStages = stagesAsElements.size();

        hoverByXpath(String.format(MAP.get("crm.deal.pipeline.default.pipeline.specific.stage"), 1));

        try {
            isDisplayed(findByXpath(String.format(MAP.get("crm.deal.pipeline.default.pipeline.default.stage.deletable"))));
            System.out.println("1");
            return false;
        } catch (Exception ignored) {
        }


        try {
            isDisplayed(findByXpath(String.format("crm.deal.pipeline.default.pipeline.specific.stage.editable", numberOfStages - 1)));
            System.out.println("2");
            return false;
        } catch (Exception ignored) {


        }
        try {
            isDisplayed(findByXpath(String.format("crm.deal.pipeline.default.pipeline.specific.stage.editable", numberOfStages)));
            System.out.println("3");
            return false;
        } catch (Exception ignored) {

        }
        return true;
    }

    public boolean verifyStageDefaultProbability() {
//        waitTillVisible("//*[@data-rbd-droppable-id='pipelines']");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        click(findByXpath(MAP.get("crm.deal.pipeline.new.stage.check.probability")));

        return Objects.equals("100", getAttribute(findByXpath(format(String.format(MAP.get("crm.deal.pipeline.form.edit.stage"), "")
                , MAP.get("crm.deal.pipeline.form.edit.probability"))), "value"));
    }

    public boolean verifyDefaultRottingDays() {
        final String pipelineName = "pipelineName";
        final String pipelineBlock = String.format(MAP.get("crm.deal.pipeline.block"), pipelineName);

        createPipeline(pipelineName);
        System.out.println(getText(findByXpath(String.format("%s%s", pipelineBlock,
                MAP.get("crm.deal.pipeline.rot.days")))).replaceAll("\\D+", ""));

        return MAP.get("crm.deal.pipeline.default.rot.days").equals(getText(findByXpath(String.format("%s%s", pipelineBlock,
                MAP.get("crm.deal.pipeline.rot.days")))).replaceAll("\\D+", ""));
    }

    public boolean verifyPipelineNameMandatory() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        // waitTillVisible(XPathBuilder.getXPathByText(MAP.get("crm.deal.pipeline.add")));
        click(getAddPipelineButton());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(getSaveButton());

        //waitTillVisible(MAP.get("crm.deal.pipeline.form.pipeline.empty"));

        return isDisplayed(findByXpath(MAP.get("crm.deal.pipeline.form.pipeline.empty")));
    }

    public boolean verifyDefaultPipelineMarkAsDefaultCannotBeUnchecked() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        hoverByXpath(MAP.get("crm.deal.pipeline.default.pipeline.name.block"));
        click(findByXpath(MAP.get("crm.deal.pipeline.default.pipeline.kebab")));
        click(findByXpath(MAP.get("crm.deal.pipeline.kebab.edit")));
        final WebPageElement markAsDefaultCheckbox = getMarkAsDefaultCheckbox();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        if (isSelected(markAsDefaultCheckbox)) {
            try {
                click(getMarkAsDefaultCheckbox());
            } catch (Exception exception) {
                return true;
            }
        }

        return isSelected(markAsDefaultCheckbox);
    }

    public boolean verifyMarkAsDefaultCanBeCheckedForNonDefaultPipelines() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        final String newPipeline = format(getText(findByXpath(MAP.get("crm.deal.pipeline.default.pipeline.name"))), "createdForCheck");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        try {
            hoverByXpath(MAP.get("crm.deal.pipeline.other.pipeline.name.block"));
        } catch (Exception exception) {
            createPipeline(newPipeline);
        }
        click(findByXpath(MAP.get("crm.deal.pipeline.other.pipeline.kebab")));
        click(findByXpath(MAP.get("crm.deal.pipeline.kebab.edit")));

        final WebPageElement markAsDefaultCheckbox = getMarkAsDefaultCheckbox();

        if (!isSelected(markAsDefaultCheckbox)) {
            try {
                click(getMarkAsDefaultCheckbox());
            } catch (Exception exception) {
                return true;
            }
        }
        return isSelected(markAsDefaultCheckbox);
    }
}

