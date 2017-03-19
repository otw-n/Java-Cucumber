package webdriver.properties;

import edu.umd.cs.findbugs.annotations.Nullable;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

;

public class PropertyLoader {
    private static final Logger LOG = LoggerFactory.getLogger(PropertyLoader.class);
    private static final String LOCAL_MAVEN_POM_FILE = "pom.xml";
    private static final String OVERRIDE_PROPERTIES = "override";

    @Nullable
    public String getProperty(final PropertyType property) {
        switch (property.getOrigin()) {
            case FILE:
                return getFileProperty(property);
            case SYSTEM_OR_POM:
                return getSystemOrPomProperty(property);
            default:
                return null;
        }
    }

    protected String getFileProperty(final PropertyType property) {
        final String environment = getSystemOrPomProperty(PropertyType.ENVIRONMENT);

        final Properties props = loadPropertiesFile(environment + "-" + OVERRIDE_PROPERTIES);
        final String overrridePropertyValue = props.getProperty(property.getName());
        if (StringUtils.isNotBlank(overrridePropertyValue)) {
            return overrridePropertyValue;
        }

        return loadPropertiesFile(environment).getProperty(property.getName());
    }

    private Properties loadPropertiesFile(final String environment) {
        final Properties props = new Properties();

        final String propertyFilename = "properties/cucumber-" + environment + ".properties";
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

    protected String getSystemOrPomProperty(final PropertyType property) {
        if (System.getProperty(property.getName()) == null) {
            try {
                final MavenProject project = loadPomFile(LOCAL_MAVEN_POM_FILE);
                return project.getProperties().getProperty(property.getName());
            } catch (Exception e) {
                LOG.info(String.format("Couldn't read %s for property %s: %s", LOCAL_MAVEN_POM_FILE, property.getName(), e.getMessage()));
            }
        }

        return System.getProperty(property.getName());
    }

    private MavenProject loadPomFile(final String filename) throws IOException, XmlPullParserException {
        final File pomFile = new File(filename);
        FileReader reader = new FileReader(pomFile);
        try {
            final MavenXpp3Reader mavenReader = new MavenXpp3Reader();
            final Model model = mavenReader.read(reader);
            return new MavenProject(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}