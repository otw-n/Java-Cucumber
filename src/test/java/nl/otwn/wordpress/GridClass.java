package nl.otwn.wordpress; /**
 * Created by dto21215 on 25-11-2016.
 */

import cucumber.api.java.After;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GridClass {
    public WebDriver driver;
    public String node;

    @Parameters("browser")
    @BeforeTest
    public void launchbrowser(String browser) throws MalformedURLException {

        if (browser.equalsIgnoreCase("phantomjs")) {
            node = "http://37.97.206.118:5555/wd/hub";
            DesiredCapabilities cap = DesiredCapabilities.phantomjs();
            cap.setBrowserName("phantomjs");
            cap.setVersion("2.0.0");
            cap.setPlatform(Platform.UNIX);
            driver = new RemoteWebDriver(new URL(node), cap);

            // puts an Implicit wait, Will wait for 10 seconds before throwing an exception
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    @After("@browser")
    public void tearDown() {
        // quit driver
        driver.quit();
    }
}