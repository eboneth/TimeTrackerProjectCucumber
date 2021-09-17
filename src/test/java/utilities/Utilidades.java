package utilities;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.LoginPage;
import pages.LogoutPage;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class Utilidades {

    WebDriver driver;
    String chromePath = System.getProperty("user.dir")+"\\drivers\\chromedriver.exe";
    String actualResult = "";
    String expectedResult = "";

    private JavascriptExecutor js;

    LoginPage loginPage;
    LogoutPage logOut;

    public Utilidades(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /*Creado por:       Rafael Bonett
    * Fecha:            24/08/2021
    * nombre Metodo:    setUp
    * Descripcion:      permite cargar el navegador e ingresar a la url solicitada
    * Version:          1.0*/
    public WebDriver setUp(String baseUrl, WebDriver driver){

        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        actualResult = driver.getTitle();
        expectedResult = "Time Tracker - Sesión iniciada";

        System.out.println(actualResult);

        Assert.assertEquals(actualResult, expectedResult);

        return driver;
    }

     /*Creado por:       Rafael Bonett
     * Fecha:            24/08/2021
     * nombre Metodo:    loguearse
     * Descripcion:      permite realizar el login en la pagina con los datos suministrado en el feature
     * Version:          1.0*/
    public void loguearse(WebDriver driver, String user, String pass){

        loginPage = new LoginPage(driver);

        loginPage.clicLinkAccesoNormal();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        loginPage.enviarDatos(user, pass);
        actualResult = driver.getTitle();
        System.out.println(actualResult);
        expectedResult = "Time Tracker - Tiempo";

        Assert.assertEquals(actualResult, expectedResult);

    }

    /*Creado por:       Rafael Bonett
     * Fecha:            24/08/2021
     * nombre Metodo:    logout
     * Descripcion:      cierra la sesion
     * Version:          1.0*/
    public void logout(WebDriver driver){
        logOut = new LogoutPage(driver);
        logOut.clicBotonFinalizar();
    }

    /* Creado por:       Rafael Bonett
     * Fecha:            24/08/2021
     * nombre Metodo:    fechaActual
     * Descripcion:      permite capturar la fecha actual del sistema para despues comparar
     * Version:          1.0*/
    public String fechaActual(){

        Calendar fecha = new GregorianCalendar();
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int m = fecha.get(Calendar.MONTH)+1;
        int anio = fecha.get(Calendar.YEAR);
        String mes = "";
        if(m<10){
            mes = "0"+String.valueOf(m);    
        }
        String fechaActual = String.valueOf(anio)+"-"+mes+"-"+String.valueOf(dia);
        return fechaActual;

    }

    /* Creado por:       Rafael Bonett
     * Fecha:            24/08/2021
     * nombre Metodo:    highLight
     * Descripcion:      hace highlight al objeto qu se le pasa por parametro
     * Version:          1.0*/
    public boolean highLight(WebElement element){
        js = (JavascriptExecutor)driver;
        for (int iCnt=0 ; iCnt<3 ; iCnt++){
            try{
                js.executeScript("arguments[0].setAttribute('style','background:yellow')",element);
                Thread.sleep(500);
                js.executeScript("arguments[0].setAttribute('style','background')",element);
            }catch (Exception e){
                System.err.println("Javascript | Method: highlight | Exception desc: " + e.getMessage());
                return false;
            }
        }

        return true;
    }

}
