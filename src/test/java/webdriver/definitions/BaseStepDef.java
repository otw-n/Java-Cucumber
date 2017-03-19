package webdriver.definitions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import webdriver.driver.SharedDriver;

@ContextConfiguration(locations = {"classpath:spring/cucumber-context.xml"})
public abstract class BaseStepDef {

    @Autowired
    protected SharedDriver webDriver;

    // NOTE!!
    // "You're not allowed to extend classes that define Step Definitions or hooks."
    // So don't add any steps here.
}
