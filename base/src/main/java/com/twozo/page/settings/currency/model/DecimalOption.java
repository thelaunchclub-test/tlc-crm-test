package com.twozo.page.settings.currency.model;

import lombok.Getter;

/**
 * <p>
 * Represents different decimal value options that can be selected or used
 * in a given context. Each enum constant corresponds to a specific decimal value and its description,
 * such as "No Decimal", "1 Decimal", "2 Decimals", etc.
 * </p>
 *
 * @author Navin Jones
 * @version 1.0
 */
@Getter
public enum DecimalOption {

    NO_DECIMAL(0, "No Decimals"),
    ONE_DECIMAL(1, "One decimal"),
    TWO_DECIMALS(2, "Two decimals"),
    THREE_DECIMALS(3, "Three decimals"),
    FOUR_DECIMALS(4, "Four decimals");

    private final int value;
    private final String description;

    DecimalOption(int value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * Iterates through all the possible {@link DecimalOption} values, compares each option's
     * value with the given input, and returns the matching option.
     *
     * @param value the {@link Integer} value to be matched with a {@link DecimalOption}
     * @return the matching {@link DecimalOption} for the given value
     * @throws IllegalArgumentException if no {@link DecimalOption} matches the input value
     */
    public static DecimalOption fromValue(int value) {
        for (DecimalOption option : DecimalOption.values()) {
            if (option.getValue() == value) {
                return option;
            }
        }
        throw new IllegalArgumentException("Invalid decimal value: " + value);
    }

    /**
     * Iterates through all the {@link DecimalOption} values and compares their description
     * with the given input, ignoring case sensitivity.
     *
     * @param description the string description to be matched with a {@link DecimalOption}
     * @return the matching {@link DecimalOption} for the given description
     * @throws IllegalArgumentException if no {@link DecimalOption} matches the input description
     */
    public static DecimalOption fromDescription(String description) {
        for (DecimalOption option : DecimalOption.values()) {
            if (option.getDescription().equalsIgnoreCase(description)) {
                return option;
            }
        }
        throw new IllegalArgumentException("Invalid decimal description: " + description);
    }
}