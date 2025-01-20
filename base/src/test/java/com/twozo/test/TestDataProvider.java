package com.twozo.test;

import com.twozo.commons.json.Json;
import com.twozo.commons.json.JsonArray;
import com.twozo.commons.json.JsonObject;
import com.twozo.commons.util.EnvUtility;
import com.twozo.page.TestCase;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class TestDataProvider {

    public Object[][] getTestCases(final String fileName) {
        System.out.println(EnvUtility.getConfDirectory());
        final JsonArray jsonArray = Json.array(new File(EnvUtility.getConfDirectory(), fileName));
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
//public class TestDataProvider {
//
//    public Object[][] getTestCases(final String fileName) {
//        final JsonArray jsonArray = Json.array(new File("C:\\Data fields log\\tlc-crm-validateActivityStatusByDate\\base\\src\\validateActivityStatusByDate\\resources", fileName));
//        final Collection<TestCase> testCases1 = new ArrayList<>();
//
//        for (int i = 0; i < jsonArray.size(); i++) {
//            final JsonObject jsonObject = jsonArray.getJsonObject(i);
//            final TestCase testCase = new TestCase();
//
//            testCase.setTestCaseId(jsonObject.getString("testCaseID"));
//            testCase.setInput(jsonObject.getJsonObject("input"));
//            testCases1.add(testCase);
//        }
//
//        final Object[][] testCasesArray = new Object[testCases1.size()][1];
//        int index = 0;
//
//        for (final TestCase testCase : testCases1) {
//            testCasesArray[index++][0] = testCase;
//        }
//
//        return testCasesArray;
//    }
//
//}
