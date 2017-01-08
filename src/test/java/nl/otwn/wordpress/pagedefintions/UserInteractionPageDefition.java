package nl.otwn.wordpress.pagedefintions;

import cucumber.api.DataTable;
import gherkin.formatter.model.DataTableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Iterator;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by ewi21211 on 4-12-2016.
 */
public class UserInteractionPageDefition {
    InitiateWebsitePageDefinition getWebDriver = new InitiateWebsitePageDefinition();

    private int textToNumber(String number) {
        if (number.equals("first")) {
            return 1;

        } else if (number.equals("second")) {
            return 2;

        } else if (number.equals("third")) {
            return 3;

        } else {
            throw new IllegalArgumentException("Invalid number: " + number);
        }
    }


    public void clickOnNewsPost(String numberOfNewsPost) {
        int rowNumber = textToNumber(numberOfNewsPost);
        getWebDriver.driver.findElement(By.cssSelector(".recent-posts-2 li:nth-child(" + rowNumber + ")")).click();
//        getWebDriver.driver.findElement(By.className("trendingPost")).click();
    }

    public void clickOnMenuItem(String menuName) {
        getWebDriver.driver.findElement(By.linkText(menuName)).click();
    }

    public void verifyBrowserTitle(String browserTitle) {
        assertEquals(browserTitle, checkBrowserTitle());
    }

    private String checkBrowserTitle() {
        return getWebDriver.driver.getTitle();
    }


    public void doesPageContainText(DataTable tableContent) {
        Iterator<DataTableRow> rowsIterator = tableContent.getGherkinRows().iterator();
        String allTextOnPage = getTextOnPage();
        String text;

        while (rowsIterator.hasNext()) {
            DataTableRow row = rowsIterator.next();
            text = row.getCells().get(0);
            assertTrue("Text found: " + text + "\n", allTextOnPage.contains(text));
        }
    }

    private String getTextOnPage() {;
        return getWebDriver.driver.findElement(By.id("content_box")).getText();
    }
}
