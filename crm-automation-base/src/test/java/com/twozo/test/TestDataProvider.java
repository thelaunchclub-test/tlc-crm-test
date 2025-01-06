package com.twozo.test;

import com.github.javafaker.Faker;
import com.twozo.commons.json.Json;
import com.twozo.commons.json.JsonArray;
import com.twozo.commons.json.JsonObject;
import com.twozo.commons.util.EnvUtility;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class TestDataProvider {

    private static final String EMPTY_DATA = "";
    private static final Faker FAKER = new Faker();

    public static Object[][] getTestData(final String fileName) {
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

    public static String generateInvalidData(final String data, final int choice) {

        return switch (choice) {
            case 0 -> data + "123";
            case 1 -> data.replace("@", "@#");
            case 2 -> "";
            case 3 -> null;
            default -> data;
        };
    }

    public static String generateValidTextData() {
        return FAKER.name().firstName();
    }

    public static String generateTextExceedingMaxLength(){
        return FAKER.lorem().characters(256);
    }

    public static String generateValidPhoneNumber(){
        return FAKER.phoneNumber().phoneNumber();
    }

    public static void generatePhoneNumberExceedingMaxLength(){
        System.out.println(FAKER.number().randomNumber(19,true));
    }

    public static String generateValidEmailData() {
        return FAKER.internet().emailAddress();
    }

    public static String getEmptyData(){
        return EMPTY_DATA;
    }
}
