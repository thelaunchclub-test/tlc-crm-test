package com.twozo.test;

import com.twozo.commons.json.JsonObject;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class TestCase {

    public String testCaseId;
    public JsonObject input;
    public Map<String, String> expectedOutput;

}
