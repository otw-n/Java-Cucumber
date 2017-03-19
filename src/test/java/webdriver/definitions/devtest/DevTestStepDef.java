package webdriver.definitions.devtest;

import cucumber.api.java.nl.Als;
import cucumber.api.java.nl.Dan;
import cucumber.api.java.nl.Gegeven;
import org.openqa.selenium.support.PageFactory;
import webdriver.definitions.BaseStepDef;

import javax.annotation.PostConstruct;

public class DevTestStepDef extends BaseStepDef {

    private DevTestPage devTestPage;

    @PostConstruct
    public void setUp() {
        devTestPage = PageFactory.initElements(webDriver, DevTestPage.class);
    }

	@Gegeven("^open dev html$")
	public void open_dev_html() {
        devTestPage.openDevHtml();
	}

	@Als("^do something$")
	public void do_something() {
        devTestPage.doSomething();
	}

	@Dan("^should result in$")
	public void should_result_in() {
        devTestPage.resultsIn();
	}

}