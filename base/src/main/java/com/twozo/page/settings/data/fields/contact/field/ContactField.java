package com.twozo.page.settings.data.fields.contact.field;

import com.twozo.page.settings.data.fields.FieldType;
import com.twozo.page.settings.data.fields.field.Field;

import java.util.List;

public enum ContactField implements Field {

    FIRST_NAME("First Name", FieldType.TEXT),
    LAST_NAME("Last Name", FieldType.TEXT),
    EMAILS("Emails", FieldType.EMAILS),
    PHONES("Phones", FieldType.PHONES),
    DESIGNATION("Designation", FieldType.TEXT),
    COMPANY("Company", FieldType.COMPANY),
    SALES_OWNER("Sales Owner", FieldType.OWNER),
    TWITTER("Twitter", FieldType.TEXT),
    FACEBOOK("Facebook", FieldType.TEXT),
    SOURCE("Source", FieldType.DROPDOWN),
    LINKED_IN("LinkedIn", FieldType.TEXT),
    LIFECYCLE_STAGE("Lifecycle Stage", FieldType.DROPDOWN),
    DEPARTMENT("Department", FieldType.TEXT),
    DATE_OF_BIRTH("Date Of Birth", FieldType.DATE),
    ADDRESS("Address", FieldType.ADDRESS),
    SUBSCRIPTION_STATUS("Subscription Status", FieldType.DROPDOWN),
    SUBSCRIPTION_TYPES("Subscription Types", FieldType.DROPDOWN),
    UNSUBSCRIBE_TYPES("Unsubscribe reason", FieldType.DROPDOWN),
    OTHER_UNSUBSCRIBE_REASON("Other unsubscribe reason", FieldType.DROPDOWN),
    WEB_FORM("Web Form", FieldType.TEXT),
    TIME_ZONE("Time Zone", FieldType.DROPDOWN),
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
    EMAIL_MESSAGES_COUNT("Email Messages Count", FieldType.NUMBER),
    LAST_EMAIL_RECEIVED("Last Email Received", FieldType.DATE),
    LAST_EMAIL_SENT("Last Email Sent", FieldType.DATE),
    TAGS("Tags", FieldType.MULTI_SELECT),
    TERRITORY("Territory", FieldType.DROPDOWN),
    ID("ID", FieldType.NUMBER),
    UPDATED_BY("Updated By", FieldType.OWNER),
    UPDATED_AT("Updated At", FieldType.TIME),
    CREATED_BY("Created By", FieldType.OWNER),
    CREATED_AT("Created At", FieldType.TIME),
    LAST_ASSIGNED_AT("Last Assigned At", FieldType.TIME),
    ;

    private final String name;
    private final String fieldType;

    ContactField(final String name, final String fieldType) {
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
        return List.of(FIRST_NAME, LAST_NAME, EMAILS, PHONES, COMPANY, DESIGNATION, SALES_OWNER, FACEBOOK, LINKED_IN);
    }
}
