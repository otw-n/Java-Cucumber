import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by dto21215 on 25-11-2016.
 */
public class SampleStepdefs {
    @Given("^User is on Home Page$")
    public void userIsOnHomePage() throws Throwable {
        ChromeDriver driver = new ChromeDriver();
        driver.navigate().to("http://otwn.nl/");
        Thread.sleep(5000);
        Assert.assertEquals("otwn | Just another WordPress site", driver.getTitle());
        Thread.sleep(2000);
        driver.quit();

        System.out.println("Java Cucumber run successful");
        //throw new PendingException();
    }
}
