package nl.otwn.wordpress.pagedefintions;

import cucumber.api.java.After;
import nl.otwn.wordpress.GridClass;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Created by ewi21211 on 4-12-2016.
 */
public class InitiateWebsitePageDefinition {
    GridClass grid = new GridClass();
    static WebDriver driver;


    @BeforeTest
    public void openBrowser(String browser) {
        try {
            if (browser.equalsIgnoreCase("Firefox")) {
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("IE")) {
                driver = new InternetExplorerDriver();
            }
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
        }
    }

    public void goToWebsiteWithBrowser(String websiteLink, String browser) {

        openBrowser(browser);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        grid.driver.navigate().to(websiteLink);
        driver.navigate().to(websiteLink);
        driver.manage().window().maximize();
//        Assert.assertEquals("otwn – Just another WordPress site", grid.driver.getTitle());
//        grid.driver.findElement(By.xpath("//a[@href='http://otwn.nl/index.php/2016/11/19/hello-world/']")).click();
//        Thread.sleep(3000);

    }

    @After
    public void quitDriver(){
        driver.close();
    }

}
