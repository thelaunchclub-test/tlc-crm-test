package com.twozo.page.settings.currency.reader;

import com.twozo.commons.json.Json;
import com.twozo.commons.json.JsonArray;
import com.twozo.commons.json.JsonObject;
import com.twozo.commons.util.EnvUtility;
import com.twozo.page.JsonFields;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Responsible for reading JSON data from a specified file
 * and converting it into a two-dimensional array of {@link TestCase} objects. This array can
 * be used for running test cases, typically in a test automation framework.
 *
 * @author Navin Jones
 * @version 1.0
 */
public class JsonFileReader {

    /**
     * Reads the specified JSON file, processes the data, and returns an array of {@link TestCase} objects.
     *
     * @param fileName The name of the JSON file that contains the test case data.
     * @return A two-dimensional array of {@link TestCase} objects where each test case is stored in a separate array.
     */
    public TestCase[][] getTestCases(final String fileName) {

        final JsonArray jsonArray = Json.array(new File(EnvUtility.getConfig(), fileName));
        final Collection<TestCase> testCases1 = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            final JsonObject jsonObject = jsonArray.getJsonObject(i);
            final TestCase testCase = new TestCase();

            testCase.setTestCaseID(jsonObject.getString(JsonFields.TEST_CASE_ID));
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

