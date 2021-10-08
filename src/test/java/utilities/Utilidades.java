package utilities;

import cucumber.api.Scenario;
import cucumber.runtime.model.CucumberFeature;
import gherkin.ast.Feature;
import gherkin.ast.GherkinDocument;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.yecht.Data;
import pages.LoginPage;
import pages.LogoutPage;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
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
        //PageFactory.initElements(driver,this);
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
    public void loguearse(WebDriver driver, String user, String pass, String feature) throws IOException {

        Integer cont = 0;
        loginPage = new LoginPage(driver);

        loginPage.clicLinkAccesoNormal(driver,feature);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        loginPage.enviarDatos(driver,user, pass,feature);
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
    public void logout(WebDriver driver,String feature)throws IOException{
        logOut = new LogoutPage(driver);
        logOut.clicBotonFinalizar(feature);
    }

    /* Creado por:       Rafael Bonett
     * Fecha:            24/08/2021
     * nombre Metodo:    fechaActual
     * Descripcion:      permite capturar la fecha actual del sistema para despues comparar
     * Version:          1.0*/
    public String fechaActual(){

        Calendar fecha = new GregorianCalendar();
        int d = fecha.get(Calendar.DAY_OF_MONTH);
        int m = fecha.get(Calendar.MONTH)+1;
        int anio = fecha.get(Calendar.YEAR);
        String mes = "";
        String dia = "";

        if(d<10){
            dia = "0"+String.valueOf(d);
        }else{
            dia = String.valueOf(d);
        }

        if(m<10){
            mes = "0"+String.valueOf(m);    
        }else{
            mes = String.valueOf(m);
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

    /* Creado por:       Rafael Bonett
     * Fecha:            21/09/2021
     * nombre Metodo:    recorrerTabla
     * Descripcion:      El metodo permite recorrer una tabla buscando un dato que se desee modificar, los parametros para
     * realizar esta solicitud son: driver: driver, tabla: el xpath de la tabla que se desea recorrer, search: el texto que
     * se desea buscar (debe ser unico), opcion: en caso que existan multiples opciones en este caso es Modificar.
     * Version:          1.0*/
    public void recorrerTabla(WebDriver driver, String tabla, String search, String opcion){

        int indexFila = 0;
        int indexColumna = 0;
        WebElement baseTable = driver.findElement(By.xpath(tabla));
        List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
        List<WebElement> tableCols = baseTable.findElements(By.tagName("td"));

        for (int i = 0; i < tableRows.size(); i++){
            if(tableRows.get(i).getText().contains(search)){
                indexFila = i+1;
                break;
            }
        }

        for(int j = 0; j < (tableCols.size()/tableRows.size()) ; j++ ){
            if(tableCols.get(j).getText().contains(opcion)){
                indexColumna = j+1;
                break;
            }
        }

        System.out.println("los elementos se encuentran en la fila: "+indexFila+" y la columna: "+indexColumna);

        WebElement lnkModificar = driver.findElement(By.xpath(tabla+"/tbody/tr["+indexFila+"]/td["+indexColumna+"]/a"));
        lnkModificar.click();

    }

    public String consecutivoHora(){

        String consecutivo = "";

        Calendar fecha = new GregorianCalendar();
        int d = fecha.get(Calendar.DAY_OF_MONTH);
        int m = fecha.get(Calendar.MONTH)+1;
        int anio = fecha.get(Calendar.YEAR);
        int horas = fecha.get(Calendar.HOUR);
        int minutos = fecha.get(Calendar.MINUTE);
        int segundos = fecha.get(Calendar.SECOND);
        int mili = fecha.get(Calendar.MILLISECOND);

        consecutivo = String.valueOf(d)+String.valueOf(m)+String.valueOf(anio)+String.valueOf(horas)+String.valueOf(minutos)+String.valueOf(segundos)+String.valueOf(mili);
        System.out.println(consecutivo);
        return consecutivo;
    }

    public void screenShot(String nombreFeature, WebElement element) throws IOException {

        String featureName = nombreFeature.split("\\|")[0];
        String methodName = nombreFeature.split("\\|")[1];

        String cont = consecutivoHora();

        File directorio = new File(System.getProperty("user.dir")+"\\"+featureName);
        if(!directorio.exists()){
            if (directorio.mkdirs()){
                System.out.println("La carpeta se creo corectamente"+ directorio);
            }else{
                System.out.println("Error");
            }
        }

        String nombreArchivo = cont + " - " + methodName + "-" + new Random().nextInt() + ".png";
        js.executeScript("arguments[0].setAttribute('style','background:yellow')",element);
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(nombreArchivo);
        FileUtils.copyFile(srcFile,new File(""+directorio+"\\"+destFile));
        js.executeScript("arguments[0].setAttribute('style','background')",element);

    }

}
