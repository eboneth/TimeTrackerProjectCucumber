package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.Utilidades;

public class LoginPage {

    WebDriver driver;
    Utilidades utilidades;

    @FindBy(how = How.LINK_TEXT, using = "Acceso normal")
    private WebElement lnkAcceder;

    @FindBy(how = How.ID, using = "login")
    private WebElement txtUsuario;

    @FindBy(how = How.ID, using = "password")
    private WebElement txtPassword;

    @FindBy(how = How.ID, using = "btn_login")
    private WebElement btnAcceder;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        utilidades = new Utilidades(driver);
    }

    public void clicLinkAccesoNormal(){
        utilidades.highLight(lnkAcceder);
        lnkAcceder.click();
    }

    public void setUsuario(String usuario){
        txtUsuario.sendKeys(usuario);
    }

    public void setPassword(String password){
        txtPassword.sendKeys(password);
    }

    public void clicBotonIniciarSesion(){
        utilidades.highLight(btnAcceder);
        btnAcceder.click();
    }

    public void enviarDatos(String user, String pass){
        setUsuario(user);
        setPassword(pass);
        clicBotonIniciarSesion();
    }

}
