package nl.otwn.wordpress.stepdefintions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import nl.otwn.wordpress.GridClass;
import nl.otwn.wordpress.pagedefintions.InitiateWebsitePageDefinition;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by dto21215 on 25-11-2016.
 */
public class GoToPageStepdefs {

    private final InitiateWebsitePageDefinition initiateWebsitePageDefinition = new InitiateWebsitePageDefinition();


    @Given("^User is on Home Page2$")
//    @Test
    public void userIsOnHomePage() throws Throwable {
        GridClass grid = new GridClass();

        grid.launchbrowser("phantomjs");
        Thread.sleep(3000);
        grid.driver.navigate().to("http://otwn.nl/");
        grid.driver.manage().window().maximize();
        Assert.assertEquals("otwn – Just another WordPress site", grid.driver.getTitle());
        grid.driver.findElement(By.xpath("//a[@href='http://otwn.nl/index.php/2016/11/19/hello-world/']")).click();
        Thread.sleep(3000);

        // quit driver
        grid.driver.quit();
    }

    @Given("^a user is surfed to the website \"([^\"]*)\" in (Firefox|Chrome|IE|phantomjs) browser$")
    public void aUserIsSurfedToTheWebsite(String websiteLink, String browser) throws Throwable {
       initiateWebsitePageDefinition.goToWebsiteWithBrowser(websiteLink,browser);
    }
}
