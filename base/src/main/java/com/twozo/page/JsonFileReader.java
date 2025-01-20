package com.twozo.page;

import com.twozo.commons.json.Json;
import com.twozo.commons.json.JsonArray;
import com.twozo.commons.json.JsonObject;
import com.twozo.commons.util.EnvUtility;
import com.twozo.page.activity.JsonFields;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;


public class JsonFileReader {

    /**
     * Reads the specified ADD_ACTIVITY_TYPE file, processes the data, and returns an array of {@link TestCase} objects.
     *
     * @param fileName The name of the ADD_ACTIVITY_TYPE file that contains the test case data.
     * @return A two-dimensional array of {@link TestCase} objects where each test case is stored in a separate array.
     */
    public TestCase[][] getTestCases(final String fileName) {

        final JsonArray jsonArray = Json.array(new File(EnvUtility.getConfDirectory(), fileName));
        final Collection<TestCase> testCases1 = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            final JsonObject jsonObject = jsonArray.getJsonObject(i);
            final TestCase testCase = new TestCase();

            testCase.setTestCaseId(jsonObject.getString(JsonFields.TEST_CASE_ID));
            testCase.setInput(jsonObject.getJsonObject(JsonFields.INPUT));
            testCases1.add(testCase);
        }

        final TestCase[][] testCasesArray = new TestCase[testCases1.size()][1];

        int index = 0;
        for (final TestCase testCase : testCases1) {
            testCasesArray[index++][0] = testCase;
        }

        return testCasesArray;
    }
}

