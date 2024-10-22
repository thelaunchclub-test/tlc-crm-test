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
    QUANTITY_ORDERED("Quantity Ordered", FieldType.NUMBER),
    QUANTITY_IN_STOCK("Quantity In Stock", FieldType.NUMBER),
    DESCRIPTION("Description", FieldType.TEXT),
    TYPE("Type", FieldType.DROPDOWN),
    SKU_NUMBER("SKU Number", FieldType.TEXT),
    VALID_TILL("Valid Till", FieldType.DATE),
    CREATED_BY("Created By", FieldType.OWNER),
    CREATED_AT("Created At", FieldType.TIME),
    UPDATED_BY("Updated By", FieldType.OWNER),
    UPDATED_AT("Updated At", FieldType.TIME),
    LAST_ASSIGNED_AT("Last Assigned At", FieldType.TIME),
    ID("ID", FieldType.NUMBER),
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
