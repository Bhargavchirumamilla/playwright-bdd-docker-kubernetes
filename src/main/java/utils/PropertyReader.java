package utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static final Properties envProps = new Properties();
    private static final Properties testDataProps = new Properties();

    static {
        try {
            // -------- ENV PROPERTIES --------
            InputStream envInput =
                    PropertyReader.class
                            .getClassLoader()
                            .getResourceAsStream("config/env.properties");

            if (envInput == null) {
                throw new RuntimeException("❌ env.properties not found");
            }
            envProps.load(envInput);

            // -------- TEST DATA PROPERTIES --------
            InputStream testDataInput =
                    PropertyReader.class
                            .getClassLoader()
                            .getResourceAsStream("testdata/testdata.properties");

            if (testDataInput == null) {
                throw new RuntimeException("❌ testdata.properties not found");
            }
            testDataProps.load(testDataInput);

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to load property files", e);
        }
    }

    // ===== ENV GETTERS =====
    public static String getUrl() {
        return envProps.getProperty("url");
    }

    public static String getBrowser() {
        return envProps.getProperty("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(envProps.getProperty("headless"));
    }

    // ===== TEST DATA GETTERS =====
    public static String getTestData(String key) {
        return testDataProps.getProperty(key);
    }
}
