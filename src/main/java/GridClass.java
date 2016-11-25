/**
 * Created by dto21215 on 25-11-2016.
 */

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class GridClass {
    public WebDriver driver;
    public String Node;

    @Parameters("browser")
    @BeforeTest
    public void launchbrowser(String browser) throws MalformedURLException {

        if (browser.equalsIgnoreCase("phantomjs")) {
            System.out.println(" Executing on phantomjs");
            Node = "http://37.97.206.118:5555/wd/hub";
            DesiredCapabilities cap = DesiredCapabilities.phantomjs();
            cap.setBrowserName("phantomjs");
            cap.setVersion("2.0.0");
            cap.setPlatform(Platform.UNIX);
            driver = new RemoteWebDriver(new URL(Node), cap);

            // Puts an Implicit wait, Will wait for 10 seconds before throwing an exception
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    @After("@browser")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "src/test/test.png");
        }
    }
}