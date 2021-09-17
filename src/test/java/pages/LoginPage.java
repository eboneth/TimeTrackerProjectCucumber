package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @FindBy(how = How.LINK_TEXT, using = "Acceso normal")
    private WebElement lnkAcceder;

    @FindBy(id = "login")
    WebElement txtUsuario;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(id = "btn_login")
    WebElement btnAcceder;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clicLinkAccesoNormal(){
        lnkAcceder.click();
    }

    public void setUsuario(String usuario){
        txtUsuario.sendKeys(usuario);
    }

    public void setPassword(String password){
        txtPassword.sendKeys(password);
    }

    public void clicBotonIniciarSesion(){
        btnAcceder.click();
    }

    public void enviarDatos(String user, String pass){
        setUsuario(user);
        setPassword(pass);
        clicBotonIniciarSesion();
    }

}
