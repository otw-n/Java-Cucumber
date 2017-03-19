package webdriver.properties;

public enum PropertyType {
    ENVIRONMENT("environment", Origin.SYSTEM_OR_POM),
    BROWSER_TYPE("browser.type", Origin.FILE),
    BROWSER_VERSION("browser.version", Origin.SYSTEM_OR_POM),
    REMOTE("remote", Origin.FILE),
    REMOTE_URL("remote.url", Origin.FILE),
    FIREFOX_DRIVER("firefox.binary.location", Origin.FILE),
    CHROME_DRIVER("chrome.binary.location", Origin.FILE),
    PHANTOM_DRIVER("phantom.binary.location", Origin.FILE),
    TAKE_SCREENSHOT_ALWAYS("screenshot.always", Origin.FILE);

    private final String name;
    private final Origin origin;

    private PropertyType(final String name, final Origin origin) {
        this.name = name;
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public Origin getOrigin() {
        return origin;
    }

    public enum Origin {
        FILE, SYSTEM_OR_POM;
    }
}
