package com.twozo.page.settings.data.fields.company.field;

import com.twozo.page.settings.data.fields.FieldType;
import com.twozo.page.settings.data.fields.field.Field;

import java.util.List;

public enum CompanyField implements Field {

    NAME("Name", FieldType.TEXT),
    WEBSITE("Website", FieldType.TEXT),
    SALES_OWNER("Sales Owner", FieldType.OWNER),
    ADDRESS("Address", FieldType.ADDRESS),
    FACEBOOK("Facebook", FieldType.TEXT),
    TWITTER("Twitter", FieldType.TEXT),
    LINKED_IN("LinkedIn", FieldType.TEXT),
    ANNUAL_REVENUE("Annual Revenue", FieldType.MONETARY),
    SIC_CODE("SIC Code", FieldType.NUMBER),
    TERRITORY("Territory", FieldType.DROPDOWN),
    TAGS("Tags", FieldType.MULTI_SELECT),
    EMPLOYEES("Employees", FieldType.NUMBER),
    PARENT_COMPANY("Parent Company", FieldType.COMPANY),
    ORGANIZATION_STATUS("Organization Status", FieldType.DROPDOWN),
    INDUSTRIAL_TYPE("Industry Type", FieldType.DROPDOWN),
    BUSINESS_TYPE("Business Type", FieldType.DROPDOWN),
    DESCRIPTION("Description", FieldType.TEXT),
    RECENT_NOTE("Recent Note", FieldType.LARGE_TEXT),
    OPEN_DEALS("Open Deals", FieldType.NUMBER),
    CLOSED_DEALS("Closed Deals", FieldType.NUMBER),
    WON_DEALS("Won Deals", FieldType.NUMBER),
    LOST_DEALS("Lost Deals", FieldType.NUMBER),
    LAST_ACTIVITY_DATE("Last Activity Date", FieldType.DATE),
    NEXT_ACTIVITY_DATE("Next Activity Date", FieldType.DATE),
    LAST_ACTIVITY_TYPE("Last Activity Type", FieldType.DROPDOWN),
    DONE_ACTIVITIES("Done Activities", FieldType.NUMBER),
    UPCOMING_ACTIVITIES("Upcoming Activities", FieldType.NUMBER),
    TOTAL_ACTIVITIES("Total Activities", FieldType.NUMBER),
    EMAIL_MESSAGES_COUNT("Email Message Count", FieldType.NUMBER),
    LAST_EMAIL_RECEIVED("Last Email Received", FieldType.DATE),
    LAST_EMAIL_SENT("Last Email Sent", FieldType.DATE),
    ID("ID", FieldType.NUMBER),
    UPDATED_BY("Updated By", FieldType.OWNER),
    UPDATED_AT("Updated At", FieldType.TIME),
    CREATED_BY("Created By", FieldType.OWNER),
    CREATED_AT("Created At", FieldType.TIME),
    LAST_ASSIGNED_AT("Last Assigned At", FieldType.TIME),
    ;

    private final String name;
    private final String fieldType;

    CompanyField(final String name, final String fieldType) {
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
        return List.of(NAME, WEBSITE, SALES_OWNER, ADDRESS);
    }
}

