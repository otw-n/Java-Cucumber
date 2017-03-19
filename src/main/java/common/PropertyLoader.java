package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    private static final Logger LOG = LoggerFactory.getLogger(PropertyLoader.class);

    public static Properties loadPropertiesFileByName(final String name) {
        final Properties props = new Properties();
        final String propertyFilename = "properties/" + name + ".properties";
        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyFilename);
        try {
            if (inputStream != null) {
                props.load(inputStream);
            }
        } catch (final IOException e) {
            LOG.info("Couldn't read property file: {}", e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    LOG.error("*** Can't close InputStream {}", e);
                }
            }
        }
        return props;
    }

}