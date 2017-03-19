package webdriver.driver;

import com.oracle.javafx.jmx.json.JSONException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import webdriver.properties.PropertyLoader;
import webdriver.properties.PropertyType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

@Component
public class SharedDriver extends EventFiringWebDriver {
    private static final Logger LOG = LoggerFactory.getLogger(SharedDriver.class);

    public static final long TIMEOUT = 15;
    private static final String FIREFOX = "Firefox";
    private static final String IE = "Internet explorer";
    private static final String CHROME = "Chrome";

    private static final String CHROME_DRIVER_SYSTEM_PROPERTY = "webdriver.chrome.driver";

    protected static WebDriver WEBDRIVER;
    private static boolean SCREENSHOT_SUPPORTED;
    private static PropertyLoader PROP_LOADER = new PropertyLoader();

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            try {
                WEBDRIVER.quit();
            } catch (final NullPointerException npe) {
                // ignore - fails on deletion of tempfiledir...
            }
        }
    };

    private long startTimestamp;

    private static void setScreenshotSupported(final boolean screenshotValue) {
        SCREENSHOT_SUPPORTED = screenshotValue;
    }

    static {
        WEBDRIVER = getRemoteOrLocalWebDriver();
        WEBDRIVER.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        WEBDRIVER.manage().timeouts().pageLoadTimeout(TIMEOUT, TimeUnit.SECONDS);
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    private static WebDriver getRemoteOrLocalWebDriver() {
        final String browser = PROP_LOADER.getProperty(PropertyType.BROWSER_TYPE);
        LOG.info("*** BROWSER TYPE: {}", browser);

        final boolean useRemoteDriver = Boolean.parseBoolean(PROP_LOADER.getProperty(PropertyType.REMOTE));
        if (useRemoteDriver) {
            WEBDRIVER = getRemoteDriver(browser);
        } else {
            WEBDRIVER = getLocalDriver(browser);
        }
        return WEBDRIVER;
    }

    private static boolean isScreenshotSupported() {
        return SCREENSHOT_SUPPORTED;
    }

    private final boolean takeScreenshotAlways;

    public SharedDriver() {
        super(WEBDRIVER);
        final String screenshotAlways = PROP_LOADER.getProperty(PropertyType.TAKE_SCREENSHOT_ALWAYS);
        takeScreenshotAlways = Boolean.parseBoolean(screenshotAlways);
        LOG.info("new shareddriver instance created");
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }

    @Before
    public void setUp() {
        LOG.info("reset webdriver");
        manage().deleteAllCookies();
        startTimestamp = DateTime.now().getMillis();
        LOG.info("reset webdriver done; start time {} (epoch ms) noted down for retrieving logs from kibana", startTimestamp);
    }

    @After
    public void addLogEntryOnFailure(final Scenario scenario) {
        if (scenario.isFailed()) {
            LOG.info("** Test gefaald, probeer browser logs op te halen - helaas levert dat meestal geen informatie");
            final LogEntries logEntries = WEBDRIVER.manage().logs().get(LogType.BROWSER);

            for (final LogEntry logEntry : logEntries.getAll()) {
                if (logEntry.getLevel().intValue() > Level.WARNING.intValue()) {
                    LOG.error("** Console log entry: [{}] {}", logEntry.getLevel().getName(), logEntry.getMessage());
                }
            }
        }
    }

    @After
    public void embedScreenshot(final Scenario scenario) {
        if (!isScreenshotSupported()) {
            LOG.info("screenshots unsupported");
            return;
        }

        if (!takeScreenshotAlways && !scenario.isFailed()) {
            LOG.info("scenario succesful, no screenshot needed");
            return;
        }

        LOG.info("creating screenshot");
        scenario.write("Current Page URL is " + getCurrentUrl());
        try {
            final byte[] screenshot = getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (final WebDriverException somePlatformsDontSupportScreenshots) {
            LOG.error(somePlatformsDontSupportScreenshots.getMessage(), somePlatformsDontSupportScreenshots);
        } finally {
            LOG.info("creating screenshot done");
        }
    }

    private static WebDriver getLocalDriver(final String browser) {
        if (browser.equalsIgnoreCase(FIREFOX)) {
            WEBDRIVER = getFireFoxDriver();
        } else if (browser.equalsIgnoreCase(CHROME)) {
            WEBDRIVER = getChromeDriver();
        }
        return WEBDRIVER;
    }

    public static WebDriver getFireFoxDriver() {
        setScreenshotSupported(true);
        final File pathToBinary = new File(PROP_LOADER.getProperty(PropertyType.FIREFOX_DRIVER));
        final FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);

        final FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("network.proxy.type", 0);
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",  "application/pdf;application/msword;application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);

        return new FirefoxDriver(ffBinary, firefoxProfile);
    }

    public static WebDriver getChromeDriver() {
        final String pathToBinary = PROP_LOADER.getProperty(PropertyType.CHROME_DRIVER);
        System.setProperty(CHROME_DRIVER_SYSTEM_PROPERTY, pathToBinary);

        setScreenshotSupported(true);

        final ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        return new ChromeDriver(options);
    }

    public static WebDriver getRemoteDriver(final String browser) {
        final String remoteUrl = PROP_LOADER.getProperty(PropertyType.REMOTE_URL);

        DesiredCapabilities capabilities = null;
        if (browser.equalsIgnoreCase(CHROME)) {
            capabilities = DesiredCapabilities.chrome();
        } else if (browser.equalsIgnoreCase(FIREFOX)) {
            capabilities = DesiredCapabilities.firefox();
        } else if (browser.equalsIgnoreCase(IE)) {
            capabilities = DesiredCapabilities.internetExplorer();
        }

        if (capabilities == null) {
            LOG.error("*** No capabilities found. Property browser.type set?");
        }

        final String browserVersion = PROP_LOADER.getProperty(PropertyType.BROWSER_VERSION);
        if (StringUtils.isNotBlank(browserVersion) && capabilities != null) {
            capabilities.setVersion(browserVersion);
            capabilities.setCapability("version", browserVersion);
        }

        try {
            final RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteUrl), capabilities);
            driver.manage().window().maximize();
            LOG.info("*** Connected to grid at url {} with browser type {}", remoteUrl, browser);
            logIpAddressOfExecutingNode(driver);
            setScreenshotSupported(true);
            return driver;
        } catch (final MalformedURLException e) {
            LOG.error("*** Something went wrong connecting to remoteUrl {}", remoteUrl);
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("resource")
    private static void logIpAddressOfExecutingNode(final RemoteWebDriver driver) {
        try {
            final URL addressOfRemoteServer = ((HttpCommandExecutor) driver.getCommandExecutor()).getAddressOfRemoteServer();
            final HttpHost host = new HttpHost(addressOfRemoteServer.getHost(), addressOfRemoteServer.getPort());
            final URL sessionURL = new URL("http://" + addressOfRemoteServer.getHost() //
                    + ":" + addressOfRemoteServer.getPort() //
                    + "/grid/api/testsession?session=" //
                    + driver.getSessionId());
            final BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest("POST", sessionURL.toExternalForm());
            final HttpClient client = HttpClientBuilder.create().build();
            final HttpResponse response = client.execute(host, r);
            final JSONObject jsonObject = convertToJSON(response);
            LOG.info("*** Tests will be running on node {}", jsonObject.get("proxyId"));
        } catch (final Exception ex) {
            LOG.error("*** Can't lookup node ip addresss: {}", ex.getMessage());
        }
    }

    private static JSONObject convertToJSON(final HttpResponse resp) throws IOException, JSONException {
        InputStream contentIs = resp.getEntity().getContent();
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(contentIs, writer, "UTF8");
            final JSONObject jsonObject = new JSONObject(writer.toString());
            //final JSONObject jsonObject = new JSONObject(writer.toString());
            return jsonObject;
        } catch(Exception e) {
            LOG.error("*** Can't convert HttpResponse to JSON: {}", e.getMessage());
        } finally {
            try {
                contentIs.close();
            } catch (Exception e) {
                LOG.error("*** Can't close inputStream: {}", e.getMessage());
            }
            try {
                writer.close();
            } catch (Exception e) {
                LOG.error("*** Can't close StringWriter: {}", e.getMessage());
            }
        }
        return new JSONObject();
    }

}