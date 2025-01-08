package com.twozo.crm.automation.page.settings.data.fields.company.field;

import com.twozo.crm.automation.page.settings.data.fields.FieldType;
import com.twozo.crm.automation.page.settings.data.fields.field.Field;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Represents different fields and their type.
 * </p>
 *
 * @author Petchimuthu
 * @version 1.0
 */
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
    INDUSTRY_TYPE("Industry Type", FieldType.DROPDOWN),
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
    LAST_ASSIGNED_AT("Last Assigned At", FieldType.TIME);

    private final String name;
    private final String fieldType;

    CompanyField(final String name, final String fieldType) {
        this.name = name;
        this.fieldType = fieldType;
    }

    /**
     * Returns the name of the field.
     *
     * @return the field name as a String
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the field type associated with the field.
     *
     * @return the field type as a String
     */
    @Override
    public String getFieldType() {
        return fieldType;
    }

    /**
     * Retrieves a list of default company fields.
     *
     * @return a list of default fields for a company entity
     */
    public static Collection<String> getDefaultFields() {
        return Arrays.asList(NAME, WEBSITE, SALES_OWNER, ADDRESS)
                .stream()
                .map(CompanyField::getName)
                .collect(Collectors.toList());
    }


    /**
     * Retrieves a list of auto-generating fields for the company entity.
     *
     * @return a collection of auto-generating field names
     */
    public static List<String> getAutoGeneratingFields() {
        return List.of(
                ID.getName(), CREATED_BY.getName(), CREATED_AT.getName(), UPDATED_BY.getName(),
                UPDATED_AT.getName(), LAST_ASSIGNED_AT.getName()
        );
    }

    /**
     * Retrieves all field names as a String array.
     *
     * @return a String array of all field names
     */
    public static List<String> getAllFields() {
        return Arrays.stream(CompanyField.values())
                .map(CompanyField::getName)
                .collect(Collectors.toList());
    }
}
