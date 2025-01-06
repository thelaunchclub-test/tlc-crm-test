import com.twozo.commons.exception.ErrorCode;
import com.twozo.commons.exception.error.code.CommonsErrorCode;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public final class ValueRetriever {
    private static final String FILE_PATH = "C:\\Testing\\automation-tool\\test\\src\\main\\Resource.properties";
    private static final Map<String, String> VALUES = new HashMap<>();

    private static void load() {
        final File file = new File(FILE_PATH);

        if (!file.exists()) {
            //LOGGER.error("Commons : The given file path '{}' is not found", file.getAbsolutePath());
            throw ErrorCode.get(CommonsErrorCode.FILE_NOT_FOUND);
        }
        final Properties properties = new Properties();

        try (final FileReader reader = new FileReader(file)) {
            properties.load(reader);
        } catch (IOException exception) {
            //LOGGER.error("Commons : The file '{}' can't be read", file.getAbsolutePath());
            throw ErrorCode.get(CommonsErrorCode.CANNOT_READ);
        }

        VALUES.putAll(properties.stringPropertyNames().stream()
                .collect(Collectors.toMap(key -> key, properties::getProperty)));
    }

    public static String get(final String key) {
        return VALUES.get(key);
    }
}
