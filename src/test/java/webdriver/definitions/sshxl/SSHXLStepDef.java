package webdriver.definitions.sshxl;

import cucumber.api.java.nl.Als;
import cucumber.api.java.nl.Dan;
import cucumber.api.java.nl.Gegeven;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import webdriver.definitions.BaseStepDef;

import javax.annotation.PostConstruct;
import java.util.List;

public class SSHXLStepDef extends BaseStepDef {

    private SSHXLPage sshxlPage;

    @PostConstruct
    public void setUp() {
        sshxlPage = PageFactory.initElements(webDriver, SSHXLPage.class);
    }

    @Gegeven("^open woningaanbod van sshxl met \"([^\"]*)\"$")
	public void open_woningaanbod_van_sshxl_met_plaats(String plaatsnaam) {
        sshxlPage.navigateToAanbodWithPlaatsnaam(plaatsnaam);
	}

	@Als("^er type Woning is geselecteerd$")
	public void selecteer_type_woning() {
        sshxlPage.selecteerTypeWoning();
	}

	@Dan("^wordt fotoweergave geselecteerd$")
	public void selecteer_fotoweergave() {
        sshxlPage.selecteerFotoWeergave();
	}

	@Dan("^worden alle resultaten met naam \"([^\"]*)\" gefilterd en verstuurd$")
	public void filter_en_verstuur_alle_resultaten_bij_naam(String naamWoning) {
        final List<WebElement> woningen = sshxlPage.filterEnVerstuurAlleWoningenBijNaam(naamWoning);
        sshxlPage.vertuurWoningenViaEmail(woningen, naamWoning);
	}

}