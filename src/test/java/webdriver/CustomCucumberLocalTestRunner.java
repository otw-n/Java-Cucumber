package webdriver;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        tags = { "~@Ignore", "@RegressieTest"},
        format = { "pretty", "html:target/cucumber-report/", "json:target/cucumber-report/cucumber.json" },
        strict = true)
public class CustomCucumberLocalTestRunner {
    // intentionally empty
}

