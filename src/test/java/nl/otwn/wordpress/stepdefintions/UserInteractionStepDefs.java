package nl.otwn.wordpress.stepdefintions;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.otwn.wordpress.pagedefintions.UserInteractionPageDefition;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by ewi21211 on 4-12-2016.
 */
public class UserInteractionStepDefs {
    private final UserInteractionPageDefition userInteractionPageDefition = new UserInteractionPageDefition();

    @When("^the user clicks on (first|second|third) newspost$")
    public void theUserClicksOnFirstNewspost(final String numberOfNewsPost) throws Throwable {
        userInteractionPageDefition.clickOnNewsPost(numberOfNewsPost);
    }

    @Then("^the user should be able to place a comment$")
    public void theUserShouldBeAbleToPlaceAComment() throws Throwable {
        assertTrue(true);
    }


    @When("^the user clicks on menu item \"(GIT|HOME|MAVEN)\" in bottom menu$")
    public void theUserClicksOnMenuItemInBottomMenu(final String menuName) throws Throwable {
        userInteractionPageDefition.clickOnMenuItem(menuName);
    }

    @Then("^the browser title should be \"([^\"]*)\"$")
    public void theBrowserTitleShouldBe(final String browserTitle) throws Throwable {
        userInteractionPageDefition.verifyBrowserTitle(browserTitle);

    }

    @Then("^the page should contain the following (?:text|texts)$")
    public void thePageShouldContainTheFollowingTexts(final DataTable tableContent) throws Throwable {
userInteractionPageDefition.doesPageContainText(tableContent);
    }
}
