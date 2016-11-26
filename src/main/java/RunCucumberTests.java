/**
 * Created by dto21215 on 25-11-2016.
 */

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/",
        plugin = {"pretty", "html:target/cucumber-html-report"},
        tags = {}
)
public class RunCucumberTests {
}
