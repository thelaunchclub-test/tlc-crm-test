package com.twozo.test.settings.data.fields;

import com.twozo.commons.json.JsonArray;
import com.twozo.commons.json.JsonObject;
import com.twozo.crm.automation.page.settings.data.fields.FieldStatus;
import com.twozo.crm.automation.page.settings.data.fields.field.Field;
import com.twozo.test.TestCase;
import com.twozo.test.TestDataProvider;
import com.twozo.test.settings.SettingsTest;
import org.testng.annotations.DataProvider;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class DataFieldTest extends SettingsTest {

    protected static final String DATA_FIELDS = Paths.get(SETTINGS_PATH, "data", "fields").toString();

    @DataProvider(name = "searchData")
    public static Object[][] getSearchData() {
        return TestDataProvider.getTestData(getFilePath(DATA_FIELDS, "SearchData.json"));
    }

    @DataProvider(name = "customField")
    public static Object[][] getCustomField() {
        return TestDataProvider.getTestData(getFilePath(SETTINGS_PATH, "CustomField.json"));
    }

    @DataProvider(name = "editData")
    public static Object[][] getEditData() {
        return TestDataProvider.getTestData(getFilePath(SETTINGS_PATH, "EditFieldName.json"));
    }

    @DataProvider(name = "deleteField")
    public static Object[][] getDeleteFieldData() {
        return TestDataProvider.getTestData(getFilePath(SETTINGS_PATH, "DeleteField.json"));
    }

    public FieldStatus getFieldStatus(final Object object) {
        final TestCase testCase = (TestCase) object;
        final JsonObject input = testCase.input;
        final FieldStatus fieldStatus = new FieldStatus();

        if (input.containsKey("fieldName")) {
            fieldStatus.setFieldName(input.getString("fieldName"));
        }
        if (input.containsKey("fieldType")) {
            fieldStatus.setFieldType(input.getString("fieldType"));
        }

        if (input.containsKey("append")) {
            fieldStatus.setAppend(input.getString("append"));
        }

        if (input.containsKey("choices")) {
            final JsonArray choices = input.getJsonArray("choices");
            final List<String> choice = new ArrayList<>();

            for (int i = 0; i < choices.size(); i++) {
                choice.add(choices.getString(i));
            }
            fieldStatus.setChoices(choice);
        }

        return fieldStatus;
    }

    public abstract boolean isPresentInSummary(final Collection<String> fields);

    public abstract boolean isPresentInAddForm(final Collection<String> fields);

    public abstract boolean isPresentInColumnSettings(final Field[] fields);
}
