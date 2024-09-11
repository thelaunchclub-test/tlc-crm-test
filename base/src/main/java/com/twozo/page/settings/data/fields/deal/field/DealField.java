package com.twozo.page.settings.data.fields.deal.field;

import com.twozo.page.settings.data.fields.FieldType;
import com.twozo.page.settings.data.fields.field.Field;

import java.util.List;

public enum DealField implements Field {

    TITLE("Title", FieldType.TEXT),
    PIPELINE("Pipeline", FieldType.DROPDOWN),
    STAGE("Stage", FieldType.DROPDOWN),
    WON_REASON("Won Reason", FieldType.DROPDOWN),
    LOST_REASON("Lost Reason", FieldType.DROPDOWN),
    DEAL_CLOSED_ON("Deal Closed On", FieldType.DATE),
    PRIMARY_CONTACT("Primary Contact", FieldType.CONTACT),
    COMPANY("Company", FieldType.COMPANY),
    RELATED_CONTACTS("Related Contacts", FieldType.CONTACT),
    DEAL_VALUE("Deal Value", FieldType.MONETARY),
    SALES_OWNER("Sales Owner", FieldType.OWNER),
    PRODUCT_QUANTITY("Product Quantity", FieldType.NUMBER),
    DEAL_VALUE_IN_BASE_CURRENCY("Deal Value In Base Currency", FieldType.MONETARY),
    EXPECTED_CLOSE_DATE("Expected Close Date", FieldType.DATE),
    SOURCE("Source", FieldType.DROPDOWN),
    TAGS("Tags", FieldType.MULTI_SELECT),
    TERRITORY("Territory", FieldType.DROPDOWN),
    ID("ID", FieldType.NUMBER),
    ROTTING_DAYS("Rotting Days", FieldType.NUMBER),
    AGE("Age", FieldType.NUMBER),
    WON_TIME("Won Time", FieldType.DATE),
    LOST_TIME("Lost Time", FieldType.DATE),
    STAGE_PROBABILITY("Stage Probability", FieldType.NUMBER),
    WEIGHTED_VALUE("Weighted value", FieldType.MONETARY),
    EXPECTED_DEAL_VALUE("Expected Deal Value", FieldType.MONETARY),
    DESCRIPTION("Description", FieldType.TEXT),
    TYPE("Type", FieldType.DROPDOWN),
    PAYMENT_STATUS("Payment Status", FieldType.DROPDOWN),
    STATUS("Status", FieldType.TEXT),
    DEAL_STAGE_UPDATED_AT("Deal Stage Updated At", FieldType.DATE),
    WEB_FORM("Web Form", FieldType.TEXT),
    CREATED_BY("Created By", FieldType.OWNER),
    CREATED_AT("Created At", FieldType.DATE),
    UPDATED_BY("Updated By", FieldType.OWNER),
    UPDATED_AT("Updated At", FieldType.DATE),
    RECENT_NOTE("Recent Note", FieldType.LARGE_TEXT),
    LAST_ASSIGNED_AT("Last Assigned At", FieldType.DATE),
    LAST_ACTIVITY_DATE("Last Activity Date", FieldType.DATE),
    NEXT_ACTIVITY_DATE("Next Activity Date", FieldType.DATE),
    LAST_ACTIVITY_TYPE("Last Activity Type", FieldType.TEXT),
    DONE_ACTIVITIES("Done Activities", FieldType.NUMBER),
    UPCOMING_ACTIVITIES("Upcoming Activities", FieldType.NUMBER),
    TOTAL_ACTIVITIES("Total Activities", FieldType.NUMBER),
    LAST_EMAIL_RECEIVED("Last Email Received", FieldType.DATE),
    EMAIL_MESSAGE_COUNT("Email Message Count", FieldType.NUMBER),
    LAST_EMAIL_SENT("Last Email Sent", FieldType.DATE),
    ;

    private final String name;
    private final String fieldType;

    DealField(final String name, final String fieldType) {
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

    public static List<Field> getDefaultFields() {
        return List.of(TITLE, PIPELINE, STAGE, WON_REASON, LOST_REASON, DEAL_CLOSED_ON, PRIMARY_CONTACT, RELATED_CONTACTS,
                COMPANY, DEAL_VALUE, SALES_OWNER, PRODUCT_QUANTITY
        );
    }
}
