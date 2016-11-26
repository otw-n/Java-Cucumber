import cucumber.api.java.en.Given;
import org.junit.Assert;
import org.testng.annotations.Test;

/**
 * Created by dto21215 on 25-11-2016.
 */
public class Sample2Stepdefs {
    @Given("^User is on Home Page2$")
    @Test
    public void userIsOnHomePage() throws Throwable {
        GridClass grid = new GridClass();

        grid.launchbrowser("phantomjs");
        Thread.sleep(8000);
        grid.driver.navigate().to("http://otwn.nl/");
        grid.driver.manage().window().maximize();

        Assert.assertEquals("otwn | Just another WordPress site", grid.driver.getTitle());
        Thread.sleep(8000);

        // quit driver
        grid.driver.quit();
    }
}
