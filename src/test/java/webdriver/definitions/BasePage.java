package webdriver.definitions;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class BasePage {
    private WebDriver webDriver;
    private static final long SECONDS_TO_WAIT_ACCORDING_SLA = 15;
    private static final long SECONDS_PAGELOAD_TIMEOUT = 12;

    public BasePage(final WebDriver webdriver) {
        this.webDriver = webdriver;
    }

    /* WebDriver */
    protected void turnOnImplicitWaits(final int time, final TimeUnit timeUnit) {
        webDriver.manage().timeouts().implicitlyWait(time, timeUnit);
    }

    protected void turnOffImplicitWaits() {
        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    private WebDriverWait getWebDriverWait(final long numberOfSeconds) {
        return new WebDriverWait(webDriver, numberOfSeconds);
    }

    private WebDriverWait getWebDriverWait() {
        return getWebDriverWait(SECONDS_TO_WAIT_ACCORDING_SLA);
    }

    public WebElement findElement(final By locator) {
        return webDriver.findElement(locator);
    }

    public List<WebElement> findElements(final By locator) {
        return webDriver.findElements(locator);
    }

    public boolean containsInPageSource(final String value) {
        return webDriver.getPageSource().contains(value);
    }

    public void waitForPageToLoad(final By waitForElement) {
        waitForPageToLoad(SECONDS_PAGELOAD_TIMEOUT, waitForElement);
    }

    public void waitForPageToLoad(final long numberOfSeconds, final By locator) {
        final WebDriverWait wait = new WebDriverWait(webDriver, numberOfSeconds);
        wait.withMessage("Element [" + locator.toString() + "] is not currently visible and so may not be interacted with");
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void refreshPage() {
        webDriver.navigate().refresh();
    }

    public void navigateBack() {
        webDriver.navigate().back();
    }

    public void quitWebDriver() {
        webDriver.quit();
    }

    public void closeWebDriver() {
        webDriver.close();
    }

    public void navigateForward() {
        webDriver.navigate().forward();
    }

    public void navigateToPage(final String url) {
        webDriver.navigate().to(url);
    }

    public String elementGetAttributeHref(final By locator) {
        return getAttributeOfElement(locator, "href");
    }

    public Set<Cookie> getCookies() {
        return webDriver.manage().getCookies();
    }

    public void deleteAllCookies() {
        webDriver.manage().deleteAllCookies();
    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    public Object executeJavaScript(final String script) {
        return ((JavascriptExecutor) webDriver).executeScript(script);
    }

    public String getAttributeOfElement(final By locator, final String attr) {
        return findElement(locator).getAttribute(attr);
    }

    /* elementen  */
    public void waitForElementPresent(final By locator, final long numberOfSeconds) {
        final WebDriverWait wait = getWebDriverWait(numberOfSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementPresent(final By locator) {
        waitForElementPresent(locator, SECONDS_TO_WAIT_ACCORDING_SLA);
    }

    public void waitForElementVisible(final By locator, final long numberOfSeconds) {
        final WebDriverWait wait = getWebDriverWait(numberOfSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementVisible(final By locator) {
        waitForElementVisible(locator, SECONDS_TO_WAIT_ACCORDING_SLA);
    }

    public void sendKey(final By locator, final Keys key) {
        findElement(locator).sendKeys(key);
    }

    public void sendTab(final By locator) {
        sendKey(locator, Keys.TAB);
    }

    /* inputElementen  */
    public boolean inputElementIsEnabled(final By locator) {
        return findElement(locator).isEnabled();
    }

    public void assertInputElementIsEnabled(final By locator) {
        final WebDriverWait wait = getWebDriverWait();
        wait.until(isEnabledOfElementLocated(locator, true));
    }

    public void assertInputElementIsDisabled(final By locator) {
        final WebDriverWait wait = getWebDriverWait();
        wait.until(isEnabledOfElementLocated(locator, false));
    }

    /* buttons */
    /* alleen voor form fields */
    public void buttonClick(final By locator) {
        waitForElementVisible(locator);
        findElement(locator).click();
    }

    public void assertButtonVisibleAndEnabled(final By locator) {
        final WebDriverWait wait = getWebDriverWait();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(isEnabledOfElementLocated(locator, true));
    }

    public void assertButtonVisibleAndDisabled(final By locator) {
        final WebDriverWait wait = getWebDriverWait();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(isEnabledOfElementLocated(locator, false));
    }

    public void assertButtonVisible(final By locator) {
        waitForElementVisible(locator);
    }

    public void returnButtonIsClickable(final By locator) {
        final WebDriverWait wait = getWebDriverWait();
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /* checkbox */
    public void checkBoxVisibleAndCheck(final By locator) {
        waitForElementVisible(locator);
        final WebElement element = findElement(locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void checkBoxVisibleAndUncheck(final By locator) {
        waitForElementVisible(locator);
        final WebElement element = findElement(locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void assertElementVisibleAndCheck(final By locator) {
        waitForElementVisible(locator);
        Assert.assertEquals(findElement(locator).isSelected(), true);
    }

    public void assertElementVisibleAndUncheck(final By locator) {
        waitForElementVisible(locator);
        Assert.assertEquals(findElement(locator).isSelected(), true);
    }

    public boolean checkboxIsSelected(final By locator) {
        return findElement(locator).isSelected();
    }

    /* radiobuttons*/
    public void radioButtonVisibleAndSelect(final By locator) {
        waitForElementVisible(locator);
        findElement(locator).click();
    }

    public void radioButtonVisibleAndSelectByIndex(final By locator, final int option) {
        waitForElementVisible(locator);
        final List<WebElement> radios = findElements(locator);
        if (option > 0 && option <= radios.size()) {
            radios.get(option - 1).click();
        } else {
            throw new NotFoundException("option " + option + " not found (counting as 1,2,3,...)");
        }
    }

    public void assertRadioButtonVisibleAndSelected(final By locator) {
        waitForElementVisible(locator);
        Assert.assertEquals(findElement(locator).isSelected(), true);
    }

    public void assertRadioButtonVisibleAndNotSelected(final By locator) {
        waitForElementVisible(locator);
        Assert.assertEquals(findElement(locator).isSelected(), false);
    }

    public boolean radioButtonIsSelected(final By locator) {
        waitForElementVisible(locator);
        return findElement(locator).isSelected();
    }

    /* dropdown (select) */
    public String dropdownGetTextFirstSelected(final By locator) {
        waitForElementVisible(locator);
        WebElement selectedOption = new Select(findElement(locator)).getFirstSelectedOption();
        return selectedOption.getText();
    }

    public List<String> dropdownGetTextAllSelected(final By locator) {
        waitForElementVisible(locator);
        final List<WebElement> selectedOptions = new Select(findElement(locator)).getAllSelectedOptions();
        final List<String> selectedOptionsText = new ArrayList<String>();
        for (WebElement element : selectedOptions) {
            selectedOptionsText.add(element.getText());
        }
        return selectedOptionsText;
    }

    public void dropdownSelectByValue(final By locator, final String... listOfValues) {
        waitForElementVisible(locator);
        if (listOfValues != null && listOfValues.length > 0) {
            final Select select = new Select(findElement(locator));
            if (select.isMultiple()) {
                select.deselectAll();
                for (String value : listOfValues) {
                    select.selectByValue(value);
                }
            } else {
                select.selectByValue(listOfValues[0]);
            }
        } else {
            throw new IllegalArgumentException("at least one value is required to select");
        }
    }

    public void dropdownSelectByText(final By locator, final String... listOfTextValues) {
        waitForElementVisible(locator);
        if (listOfTextValues != null && listOfTextValues.length > 0) {
            final Select select = new Select(findElement(locator));
            if (select.isMultiple()) {
                select.deselectAll();
                for (String text : listOfTextValues) {
                    select.selectByVisibleText(text);
                }
            } else {
                select.selectByVisibleText(listOfTextValues[0]);
            }
        } else {
            throw new IllegalArgumentException("at least one text value is required to select");
        }
    }

    public void dropdownSelectByIndex(final By locator, final int... listOfIndexValues) {
        waitForElementVisible(locator);
        if (listOfIndexValues != null && listOfIndexValues.length > 0) {
            final Select select = new Select(findElement(locator));
            if (select.isMultiple()) {
                select.deselectAll();
                for (int index : listOfIndexValues) {
                    select.selectByIndex(index);
                }
            } else {
                select.selectByIndex(listOfIndexValues[0]);
            }
        } else {
            throw new IllegalArgumentException("at least one index value is required to select");
        }
    }

    public void assertDropdownSelectedContainsText(final By locator, final String value) {
        waitForElementVisible(locator);
        final Select select = new Select(findElement(locator));
        final List<WebElement> selectedOptions = select.getAllSelectedOptions();
        boolean hasFoundValue = false;
        for (WebElement option : selectedOptions) {
            if (option.getText().equals(value)) {
                hasFoundValue = true;
            }
        }
        Assert.assertTrue("Could not find " + value + " in the dropdown menu", hasFoundValue);
    }

    /* DOM */
    public void assertDOMContainsInnerText(final By locator, final String value) {
        final WebDriverWait wait = getWebDriverWait();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, value));
    }

    public void assertDOMContainsAnyInnerText(final By locator) {
        final WebDriverWait wait = getWebDriverWait();
        wait.until(anyTextToBePresentInElementLocated(locator));
    }

    /* TextInput */
    public void textInputSetText(final By locator, final String value) {
        waitForElementVisible(locator);
        final WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    public void textInputAppendText(final By locator, final String value) {
        waitForElementVisible(locator);
        findElement(locator).sendKeys(value);
    }

    public String textInputGetText(final By locator) {
        waitForElementVisible(locator);
        return findElement(locator).getText();
    }

    public String textInputGetValue(final By locator) {
        waitForElementVisible(locator);
        return getAttributeOfElement(locator, "value");
    }

    public boolean textInputIsEnabled(final By locator) {
        waitForElementVisible(locator);
        return findElement(locator).isEnabled();
    }

    public void assertTextInputIsEnabled(final By locator) {
        final WebDriverWait wait = getWebDriverWait();
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(isEnabledOfElementLocated(locator, true));
    }

    public void assertTextInputIsDisabled(final By locator) {
        final WebDriverWait wait = getWebDriverWait();
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(isEnabledOfElementLocated(locator, false));
    }

    public void assertTextInputContainsText(final By locator, final String text) {
        final WebDriverWait wait = getWebDriverWait();
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public void assertTextInputContainsAnyText(final By locator) {
        final WebDriverWait wait = getWebDriverWait();
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(anyTextToBePresentInElementLocated(locator));
    }

    public void assertTextInputContainsValue(final By locator, final String text) {
        final WebDriverWait wait = getWebDriverWait();
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
    }

    public void assertTextInputContainsAnyValue(final By locator) {
        final WebDriverWait wait = getWebDriverWait();
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(anyTextToBePresentInElementValue(locator));
    }

    // used for debugging purposes only
    @Deprecated
    public void pause(final long timeInMillies) {
        try {
            Thread.sleep(timeInMillies);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* custom expected conditions */
    private static ExpectedCondition<Boolean> isEnabledOfElementLocated(final By locator, final boolean isEnabled) {
        return new ExpectedCondition<Boolean>() {
            private boolean elementIsEnabled;

            public Boolean apply(final WebDriver driver) {
                elementIsEnabled = driver.findElement(locator).isEnabled();
                return isEnabled == elementIsEnabled;
            }

            public String toString() {
                return String.format("Expected element to be enabled \"%s\". Element enabled is: \"%s\"", isEnabled, elementIsEnabled);
            }
        };
    }

    private static ExpectedCondition<Boolean> anyTextToBePresentInElementLocated(final By locator) {
        return new ExpectedCondition<Boolean>() {
            private String text;
            public Boolean apply(final WebDriver driver) {
                text = driver.findElement(locator).getText();
                return text != null;
                //return !text.isEmpty();
            }

            public String toString() {
                return String.format("Element contains text \'%s\'", text);
            }
        };
    }

    public static ExpectedCondition<Boolean> anyTextToBePresentInElementValue(final By locator) {
        return new ExpectedCondition<Boolean>() {
            private String text;
            public Boolean apply(final WebDriver driver) {
                try {
                    text = driver.findElement(locator).getAttribute("value");
                    return text != null?Boolean.valueOf(text !=null):Boolean.valueOf(false);
                } catch (StaleElementReferenceException var3) {
                    return null;
                }
            }

            public String toString() {
                return String.format("text (\'%s\') to be the value of element located by %s", text, locator);
            }
        };
    }


    public void openUrl2(String url) {
        webDriver.navigate().to(url);
    }

}
