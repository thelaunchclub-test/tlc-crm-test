package com.twozo.page.settings.data.fields.error.code;


import com.twozo.commons.exception.provider.PrefixProvider;
import com.twozo.commons.exception.service.ErrorCodeProvider;

public enum DataFieldsErrorCode implements ErrorCodeProvider {

    INVALID_BASE_CODE(1),
    BASE_CODE_ALREADY_REGISTERED(2),
    FILE_NOT_FOUND(3),
    CANNOT_READ(4);

    private static final PrefixProvider PREFIX_PROVIDER = new PrefixProvider();
    private static final int BASE_CODE = 0x1000;

    private final int code;

    DataFieldsErrorCode(final int code) {
        this.code = code;
    }

    /**
     * {@inheritDoc}
     *
     * @return The error code.
     */
    @Override
    public int getErrorCode() {
        return PREFIX_PROVIDER.get(BASE_CODE, code);
    }
}
