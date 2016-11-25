import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

/**
 * Created by dto21215 on 25-11-2016.
 */
public class SampleStepdefs {
    @Given("^User is on Home Page$")
    public void userIsOnHomePage() throws Throwable {
        System.out.println("Hello Java cucumber sample");
        //throw new PendingException();
    }
}
