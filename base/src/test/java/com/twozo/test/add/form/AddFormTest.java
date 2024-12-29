package com.twozo.test.add.form;

import com.twozo.commons.json.JsonArray;
import com.twozo.commons.json.JsonObject;
import com.twozo.page.add.form.AddForm;
import com.twozo.page.add.form.Form;
import com.twozo.page.contact.ContactForm;
import com.twozo.page.list.view.ListView;
import com.twozo.test.BaseTest;
import com.twozo.test.TestDataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;

public abstract class AddFormTest extends BaseTest {

    protected static final String CONTACT_PATH = "contact";
    protected static final String COMPANY_PATH = "company";
    protected static final String DEAL_PATH = "deal";
    protected static final String PRODUCT_PATH = "product";

    private static final String CONTACT_ADD_FORM = Paths.get(CONTACT_PATH, "add", "form").toString();
    private static final String COMPANY_ADD_FORM = Paths.get(COMPANY_PATH, "add", "form").toString();
    private static final String DEAL_ADD_FORM = Paths.get(DEAL_PATH, "add", "form").toString();
    private static final String PRODUCT_ADD_FORM = Paths.get(PRODUCT_PATH, "add", "form").toString();

    protected ListView listView;

    @DataProvider(name = "contactAddFormFields")
    public static Object[][] getContactAddFormData() {
        return TestDataProvider.getTestData(getFilePath(CONTACT_ADD_FORM, "AllFieldTypeValue.json"));
    }

    @DataProvider(name = "companyAddFormFields")
    protected static Object[][] getCompanyAddFormData() {
        return TestDataProvider.getTestData(getFilePath(COMPANY_ADD_FORM, "AllFieldTypeValue.json"));
    }

    @DataProvider(name = "dealAddFormFields")
    public static Object[][] getDealAddFormData() {
        return TestDataProvider.getTestData(getFilePath(DEAL_ADD_FORM, "AllFieldTypeValue.json"));
    }

    @DataProvider(name = "productAddFormFields")
    protected static Object[][] getProductAddFormData() {
        return TestDataProvider.getTestData(getFilePath(PRODUCT_ADD_FORM, "AllFieldTypeValue.json"));
    }

    /**
     * <p>
     * Extracts fields for update based on the provided form and specific fields.
     * </p>
     *
     * @param form             The {@link Form}.
     * @param particularFields The specific fields to check against.
     * @param <T>              The type of field.
     * @return A {@link List} of field names to add.
     */
    protected <T extends com.twozo.page.settings.data.fields.field.Field> List<String> getFieldsForUpdate(
            final Form form, final T[] particularFields) throws IllegalAccessException {
        final List<String> fieldsToAdd = new ArrayList<>();
        final Field[] declaredFields = form.getClass().getDeclaredFields();
        String fieldName;

        for (final Field field : declaredFields) {
            field.setAccessible(true);
            final Object object = field.get(form);

            if (hasValue(object)) {
                for (final T specificField : particularFields) {
                    fieldName = specificField.getName();

                    if (fieldName.toLowerCase().replace(" ", "").contains(field.getName().toLowerCase())) {
                        fieldsToAdd.add(fieldName);
                    }
                }
            }
        }

        return fieldsToAdd;
    }

    protected void setStringField(final Consumer<String> setter, final JsonObject input, final Collection<String> requiredFields, final String fieldName) {
        if (requiredFields.contains(fieldName)) {
            setter.accept(input.getString(fieldName));
        }
    }

    protected void setStringField(final Consumer<String> setter, final JsonObject input, final String fieldName) {
        if (input.containsKey(fieldName)) {
            setter.accept(input.getString(fieldName));
        }
    }

    protected void setListField(final Consumer<List<Map<String, String>>> setter, final JsonObject input,
                                final Collection<String> requiredFields, final String fieldName, final String typeKey,
                                final String valueKey) {
        if (requiredFields.contains(fieldName) && input.containsKey(fieldName)) {
            final JsonArray array = input.getJsonArray(fieldName);
            final List<Map<String, String>> list = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                JsonObject jsonObject = array.getJsonObject(i);
                Map<String, String> map = new HashMap<>();
                map.put(typeKey, jsonObject.getString(typeKey));
                map.put(valueKey, jsonObject.getString(valueKey));
                list.add(map);
            }
            setter.accept(list);
        }
    }

    protected void setListField(Consumer<List<Map<String, String>>> setter, JsonObject input, String fieldName, String typeKey, String valueKey) {
        if (input.containsKey(fieldName)) {
            final JsonArray array = input.getJsonArray(fieldName);
            final List<Map<String, String>> list = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                JsonObject jsonObject = array.getJsonObject(i);
                Map<String, String> map = new HashMap<>();
                map.put(typeKey, jsonObject.getString(typeKey));
                map.put(valueKey, jsonObject.getString(valueKey));
                list.add(map);
            }
            setter.accept(list);
        }
    }

    protected void setStringListField(Consumer<List<String>> setter, JsonObject input, Collection<String> requiredFields, String fieldName) {
        if (requiredFields.contains(fieldName) && input.containsKey(fieldName)) {
            final JsonArray array = input.getJsonArray(fieldName);
            final List<String> list = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                list.add(array.getString(i));
            }
            setter.accept(list);
        }
    }

    protected void setStringListField(final Consumer<List<String>> setter, final JsonObject input, final String fieldName) {
        if (input.containsKey(fieldName)) {
            final JsonArray array = input.getJsonArray(fieldName);
            final List<String> list = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                list.add(array.getString(i));
            }
            setter.accept(list);
        }
    }

    /**
     * <p>
     * Validates if a field value is considered valid (non-default or non-null).
     * </p>
     *
     * @param field The field value to validate.
     * @return True if the value is valid, otherwise false.
     */
    private boolean hasValue(final Object field) {
        return switch (field) {
            case String string -> true;
            case Integer integer -> integer != 0;
            case Collection<?> collection -> !collection.isEmpty();
            case Map<?, ?> map -> !map.isEmpty();
            case Boolean value -> true;
            default -> true;
        };
    }

    public static String toCamelCase(String input) {
        String[] words = input.split(" ");
        StringBuilder camelCaseString = new StringBuilder();

        camelCaseString.append(words[0].toLowerCase());

        for (int i = 1; i < words.length; i++) {
            camelCaseString.append(words[i].substring(0, 1).toUpperCase());
            camelCaseString.append(words[i].substring(1).toLowerCase());
        }

        return camelCaseString.toString();
    }

    protected abstract Form getForm(final Object object);

    protected abstract Form getFormWithRequiredFieldsEnabled(final Object object);

    protected abstract void switchToAddFormWithNonMandatoryFieldsDisabled();

}
