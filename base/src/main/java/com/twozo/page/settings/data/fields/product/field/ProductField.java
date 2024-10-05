package com.twozo.page.settings.data.fields.product.field;

import com.twozo.page.settings.data.fields.FieldType;
import com.twozo.page.settings.data.fields.field.Field;

import java.util.Collection;
import java.util.List;

public enum ProductField implements Field {

    NAME("Name", FieldType.TEXT),
    SALES_OWNER("Sales Owner", FieldType.OWNER),
    PRODUCT_CODE("Product Code", FieldType.TEXT),
    CATEGORY("Category", FieldType.DROPDOWN),
    UNIT_PRICE("Unit Price", FieldType.MONETARY),
    ACTIVE("Active", FieldType.DROPDOWN),
    TAXABLE("Taxable", FieldType.DROPDOWN),
    BASE_CURRENCY_AMOUNT("Base Currency Amount", FieldType.MONETARY),
    ;

    private final String name;
    private final String fieldType;

    ProductField(final String name, final String fieldType) {
        this.name = name;
        this.fieldType = fieldType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFieldType() {
        return fieldType;
    }

    public static Collection<Field> getDefaultFields() {
        return List.of(NAME, PRODUCT_CODE, CATEGORY, UNIT_PRICE);
    }
}
