package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {

    WebDriver driver;

    @FindBy(xpath = "//*[contains(text(),'Finalizar ')]")
    WebElement lnkLogOut;

    public LogoutPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void clicBotonFinalizar(){
        lnkLogOut.click();
    }

}
