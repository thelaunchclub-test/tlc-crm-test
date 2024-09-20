package com.twozo.page.settings;

import com.twozo.commons.json.JsonObject;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class TestCase {

    public String testCaseID;
    public JsonObject input;
    public Map<String, String> expectedOutput;

    public void setTestCaseID(final String testCaseID) {
        this.testCaseID = testCaseID;
    }

    public void setInput(final JsonObject input) {
        this.input = input;
    }

    public void setExpectedOutput(final Map<String, String> expectedOutput) {
        this.expectedOutput = expectedOutput;
    }
}
