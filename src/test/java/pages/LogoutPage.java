package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.Utilidades;

public class LogoutPage {

    WebDriver driver;
    Utilidades utilidades;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Finalizar ')]")
    private WebElement lnkLogOut;

    public LogoutPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);
        utilidades = new Utilidades(driver);

    }

    public void clicBotonFinalizar(){

        utilidades.highLight(lnkLogOut);
        lnkLogOut.click();

    }

}
