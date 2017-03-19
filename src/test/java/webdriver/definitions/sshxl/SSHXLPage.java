package webdriver.definitions.sshxl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tools.mailer.MailerUtility;
import webdriver.definitions.BasePage;

import java.util.List;

public class SSHXLPage extends BasePage {

    public SSHXLPage(final WebDriver webdriver) {
        super(webdriver);
    }

    public void navigateToAanbodWithPlaatsnaam(final String plaatsnaam) {
        navigateToPage("https://aanbod.sshxl.nl/ons-huuraanbod?aggregatie=" + plaatsnaam);
    }

    public void selecteerTypeWoning() {
        checkBoxVisibleAndCheck(By.xpath("//*[@type=\"checkbox\"][@value=\"woning\"]"));
    }

    public void selecteerFotoWeergave() {
        buttonClick(By.xpath("//*[@id=\"IkWilHuren\"]/div[2]/div/div/div[2]/div[1]/ul[2]/li[3]/a"));
    }

    public List<WebElement> filterEnVerstuurAlleWoningenBijNaam(final String naamWoning) {
        return findElements(By.xpath("//*[@id=\"Advertenties\"]/a[starts-with(@title, \"" + naamWoning + "\")]"));
    }

    public void vertuurWoningenViaEmail(final List<WebElement> woningen, final String naamWoning) {
        // compose mail content
        if (woningen.size() > 0) {
            final StringBuilder content = new StringBuilder();
            content.append("<p>woningen:</p>");

            for (WebElement woning : woningen) {
                content.append(" - ");
                content.append(woning.getAttribute("title"));
                content.append("<br/>");
            }

            // send mail
            MailerUtility.sendMailThroughOutlook("SSH Woningen : " + naamWoning, "ronaldvanbroeckhuijsen@outlook.com", content.toString());
        }
    }
}
