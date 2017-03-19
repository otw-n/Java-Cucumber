package webdriver.definitions.bdd;

import cucumber.api.java.nl.Als;
import cucumber.api.java.nl.Dan;
import cucumber.api.java.nl.En;
import cucumber.api.java.nl.Gegeven;
import org.openqa.selenium.support.PageFactory;
import webdriver.definitions.BaseStepDef;

import javax.annotation.PostConstruct;

public class BDDStepDef extends BaseStepDef {

    private BDDPage BDDPage;

    @PostConstruct
    public void setUp() {
        BDDPage = PageFactory.initElements(webDriver, BDDPage.class);
    }

    @Gegeven("^de bdd workshop page is geopend$")
    public void deBddWorkshopPageIsGeopend() {
        BDDPage.openUrl("http://bdd-versie1.azurewebsites.net/bdd4_bestanden.html");
    }

    @Als("^het formulier ingevuld is$")
    public void hetFormulierIngevuldIs() throws Throwable {
        BDDPage.selecteerRadioButton("flits");
        BDDPage.teLenenBedragInvullen("100000");
    }

    @Dan("^zijn de velden correct gevuld$")
    public void zijnDeVeldenCorrectGevuld() {
        BDDPage.correcteRadiobuttionGeselecteerd("flits");
        BDDPage.correcteTextInInputveld("100000");
    }

    @Als("^alle formulieren worden doorlopen$")
    public void alleFormulierenWordenDoorlopen() throws Throwable {
        BDDPage.selecteerRadioButton("flits");
        BDDPage.teLenenBedragInvullen("100000");
        BDDPage.volgendePagina("Ga verder");
        BDDPage.selecteerRadioButton("ja");
        BDDPage.volgendePagina("Ga terug");
    }

    @Gegeven("^ga naar de Ordina azure website \"([^\"]*)\"$")
    public void gaNaarDeOrdinaAzureWebsite(String url) throws Throwable {
        BDDPage.openUrl2("http://ordina-ta-demo.azurewebsites.net/");
    }


    @En("^De gebruikt klikt op \"([^\"]*)\"$")
    public void deGebruiktKliktOp(String myaccount) throws Throwable {
       // BDDPage.buttonClick(By.id());

    }
}