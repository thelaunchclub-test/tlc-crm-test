package com.twozo.test;

import com.twozo.commons.json.Json;
import com.twozo.commons.json.JsonArray;
import com.twozo.commons.json.JsonObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class TestDataProvider {

    public Object[][] getTestCases(final String fileName) {
        final JsonArray jsonArray = Json.array(new File("C:\\Testing\\automation-tool\\test\\src\\test\\resources", fileName));
        final Collection<TestCase> testCases1 = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            final JsonObject jsonObject = jsonArray.getJsonObject(i);
            final TestCase testCase = new TestCase();

            testCase.setTestCaseId(jsonObject.getString("testCaseID"));
            testCase.setInput(jsonObject.getJsonObject("input"));
            testCases1.add(testCase);
        }

        final Object[][] testCasesArray = new Object[testCases1.size()][1];
        int index = 0;

        for (final TestCase testCase : testCases1) {
            testCasesArray[index++][0] = testCase;
        }

        return testCasesArray;
    }

}
