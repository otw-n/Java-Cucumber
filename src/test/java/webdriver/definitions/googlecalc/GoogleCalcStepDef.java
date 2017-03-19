package webdriver.definitions.googlecalc;

import cucumber.api.java.nl.Als;
import cucumber.api.java.nl.Dan;
import cucumber.api.java.nl.Gegeven;
import org.openqa.selenium.support.PageFactory;
import webdriver.definitions.BaseStepDef;

import javax.annotation.PostConstruct;

public class GoogleCalcStepDef extends BaseStepDef {

    private GoogleCalcPage googleCalcPage;

    @PostConstruct
    public void setUp() {
        googleCalcPage = PageFactory.initElements(webDriver, GoogleCalcPage.class);
    }

	@Gegeven("^open google$")
	public void open_google() {
        googleCalcPage.navigateToGoogle();
	}

	@Als("^enter \"([^\"]*)\" in searchbox$")
	public void enter_in_searchbox(final String search) {
        googleCalcPage.enterInSearchBox(search);
	}

	@Dan("^I should get result as \"([^\"]*)\"$")
	public void I_should_get_correct_result(final String verwachteResultaat) {
        googleCalcPage.controleerVerwachteWaarde(verwachteResultaat);
	}

}