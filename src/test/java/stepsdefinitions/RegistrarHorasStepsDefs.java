package stepsdefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistroHorasPage;
import utilities.Utilidades;

import java.io.IOException;

public class RegistrarHorasStepsDefs {

    WebDriver driver;
    Utilidades utilidades;
    RegistroHorasPage registroHora;

    String expectedResult;
    String actualResult;
    String featureName = "";
    String methodName = "";
    Integer cont = 0;

    @Before
    public void getFeatureName(Scenario scenario) throws IOException {

        featureName = scenario.getUri().split("/")[5];
        featureName = featureName.split("\\.")[0];
    }

    @Given("^abrir el navegador ingresar a la url (.*)$")
    public void abrirNavegador (String baseurl) throws IOException{

        String methodName = new String(Thread.currentThread().getStackTrace()[1].getMethodName());
        String ruta = featureName+"|"+methodName;

        utilidades = new Utilidades(driver);
        driver = utilidades.setUp(baseurl,driver,"Time Tracker - Sesión iniciada");
        utilidades.screenShot(driver,ruta);

    }

    @When("^cuando le de clic a el link acceder debera ingresar el usuario (.*) y el password (.*)$")
    public void iniciarSesion (String user, String pass) throws IOException {

        String methodName = new String(Thread.currentThread().getStackTrace()[1].getMethodName());
        String ruta = featureName+"|"+methodName;

        utilidades.loguearse(driver,user,pass,ruta);
    }

    @Then("^debera registrar su reporte de horas diarias$")
    public void registrarHoras() throws IOException, InterruptedException {

        int cant = 1;

        while(cant <= utilidades.cantDiasMes(driver)){

            //recorre dia a dia
            int indiceFila = utilidades.recorrerCalendario(driver,cant);
            WebElement seleccionarDia = driver.findElement(By.xpath("*//form[@name='timeRecordForm']/table[1]/tbody/tr/td[2]/table/tbody/tr/td/center/table/tbody/tr["+indiceFila+"]/td/a[contains(text(),'"+cant+"')]"));
            seleccionarDia.click();
            Thread.sleep(1000);

            //registra las horas
            String methodName = new String(Thread.currentThread().getStackTrace()[1].getMethodName());
            String ruta = featureName+"|"+methodName;

            String fecha = utilidades.fechaActual();

            registroHora = new RegistroHorasPage(driver);
            String tiempo = driver.findElement(By.xpath("//*[contains(text(),'Tiempo:')]")).getText();

            WebDriverWait wait = new WebDriverWait(driver,15);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("*//table/tbody/tr/td[@class='CalendarDaySelected']/a")));

            String atributo = driver.findElement(By.xpath("*//table/tbody/tr/td[@class='CalendarDaySelected']/a")).getAttribute("class");

            String cliente = "Grupo Aval ATH";
            String id = "PNF";
            String proyecto = "ATH-2021-NDE-1 Automatización";
            String servicio = "Pruebas No Funcionales";
            String tarea = "03-Ejecución - Diseño de Automatización";
            String inicio = "";
            String fin = "";
            String nota = "Creación y Ejecución Script PNF";

            String s = "";

            if(atributo.isEmpty()){
               s="am";
            }else{
               s="NA";
            }

            while(s != "NA"){
                if(s.equals("am")){
                    inicio = "0800";
                    fin = "1200";
                    registroHora.llenarFormulario(cliente,id,proyecto,servicio,tarea,inicio,fin,nota,ruta);
                    registroHora.clicBotonEnviar(ruta);
                    s = "pm";
                }else if(s.equals("pm")){
                    inicio = "1300";
                    fin = "1730";
                    registroHora.setIDREQ(id);
                    registroHora.setInicio(inicio);
                    registroHora.setFin(fin);
                    registroHora.setNota(nota);
                    registroHora.clicBotonEnviar(ruta);
                    s = "NA";
                }
            }

            cant++;
        }

    }

    @Then("^Finalizar la sesion$")
    public void cerrarSesion() throws IOException {

        String methodName = new String(Thread.currentThread().getStackTrace()[1].getMethodName());
        String ruta = featureName+"|"+methodName;

        utilidades.logout(driver,ruta);

        actualResult = driver.getTitle();
        expectedResult = "Time Tracker - Sesión iniciada";

        driver.quit();
        Assert.assertEquals(actualResult, expectedResult);

    }

}
