package com.twozo.test.add.form;

import com.twozo.page.add.form.Form;
import com.twozo.page.settings.data.fields.contact.field.ContactField;
import com.twozo.test.BaseTest;
import com.twozo.web.driver.service.WebNavigator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AddFormTest extends BaseTest {
    protected static final String CONTACT_PATH = "contact";
    protected static final String COMPANY_PATH = "company";

    protected WebNavigator webNavigator;

    protected abstract Form getForm(final Object object);
    protected List getFieldsForUpdate(final Form form) throws IllegalAccessException {
        final List<String> fields = new ArrayList<>();
        final Field[] declaredFields = form.getClass().getDeclaredFields();

        String fieldName = null;

        for (Field field : declaredFields) {
            field.setAccessible(true);
            final Object value = field.get(form);

            if (Objects.nonNull(value)) {
                for (final ContactField contactField : ContactField.values()) {
                    fieldName = contactField.getName();

                    if (fieldName.toLowerCase().contains(field.getName())) {
                        fields.add(fieldName);
                    }
                }

            }
        }
        return fields;
    }
}
