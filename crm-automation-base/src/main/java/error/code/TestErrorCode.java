package error.code;

import com.twozo.commons.exception.provider.PrefixProvider;
import com.twozo.commons.exception.service.ErrorCodeProvider;

public enum TestErrorCode implements ErrorCodeProvider {

    NO_SUCH_ELEMENT(1);

    private static final PrefixProvider PREFIX_PROVIDER = new PrefixProvider();
    private static final int BASE_CODE = 0x4000;

    private final int code;

    TestErrorCode(final int code) {
        this.code = code;
    }

    /**
     * <p>
     * Retrieves the error code value.
     * </p>
     *
     * @return The error code.
     */
    @Override
    public int getErrorCode() {
        return PREFIX_PROVIDER.get(BASE_CODE, code);
    }
}
