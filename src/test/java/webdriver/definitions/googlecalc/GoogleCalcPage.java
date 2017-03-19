package webdriver.definitions.googlecalc;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webdriver.definitions.BasePage;

public class GoogleCalcPage extends BasePage {

    public GoogleCalcPage(final WebDriver webdriver) {
        super(webdriver);
    }

    public void navigateToGoogle() {
        navigateToPage("http://www.google.com/");
    }

    public void enterInSearchBox(final String search) {
        final WebElement searchBox = findElement(By.name("q"));
        searchBox.sendKeys(search);
        searchBox.submit();
    }

    public void controleerVerwachteWaarde(final String verwachteWaarde) {
        final String result = textInputGetText(By.id("cwos"));
        Assert.assertEquals(verwachteWaarde, result);
    }
}
