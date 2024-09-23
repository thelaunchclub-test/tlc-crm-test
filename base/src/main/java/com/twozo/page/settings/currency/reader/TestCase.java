package com.twozo.page.settings.currency.reader;

import com.twozo.commons.json.JsonObject;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Represents an individual test case with its ID, input data,
 * and expected output. It is designed to hold the data needed for running automated test cases.
 *
 * @author Navin Jones
 * @version 1.0
 */
@Getter
@Setter
public class TestCase {

    public String testCaseID;
    public JsonObject input;
    public Map<String, String> expectedOutput;
}