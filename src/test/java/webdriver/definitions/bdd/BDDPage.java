package webdriver.definitions.bdd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import webdriver.definitions.BasePage;
/**
 * Created by abe20538 on 27/02/2017.
 */

public class BDDPage extends BasePage {

    public BDDPage(final WebDriver webdriver) {
        super(webdriver);
    }

    public void openUrl(String url) {
        navigateToPage(url);
    }

    public void selecteerRadioButton(String selectie) {
        radioButtonVisibleAndSelect(By.id(selectie));
    }

    public void teLenenBedragInvullen(String bedrag) {
        textInputSetText(By.id("amount"), bedrag);

    }

    public void correcteRadiobuttionGeselecteerd(String selectie) {
        assertRadioButtonVisibleAndSelected(By.id(selectie));
    }

    public void correcteTextInInputveld(String text) {
        assertTextInputContainsValue(By.id("amount"), text);
    }

    public void volgendePagina(String text) {
        final By locator = By.xpath("//button[text()='"+ text +"']");
        assertButtonVisibleAndEnabled(locator);
        buttonClick(locator);
    }


}