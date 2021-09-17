package stepsdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistroHorasPage;
import utilities.Utilidades;

public class RegistrarHorasStepsDefs {

    WebDriver driver;
    Utilidades utilidades;
    RegistroHorasPage registroHora;

    String expectedResult;
    String actualResult;

    @Given("^abrir el navegador ingresar a la url (.*)$")
    public void abrirNavegador (String baseurl) {
        utilidades = new Utilidades(driver);
        driver = utilidades.setUp(baseurl,driver);
    }

    @When("^cuando le de clic a el link acceder debera ingresar el usuario (.*) y el password (.*)$")
    public void iniciarSesion (String user, String pass) {
        utilidades.loguearse(driver,user,pass);
    }

    @Then("^debera registrar su reporte de horas diarias$")
    public void registrarHoras() {
        String fecha = utilidades.fechaActual();

        registroHora = new RegistroHorasPage(driver);
        String tiempo = driver.findElement(By.xpath("//*[contains(text(),'Tiempo:')]")).getText();

        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("*//table/tbody/tr/td[@class='CalendarDaySelected']/a")));

        String atributo = driver.findElement(By.xpath("*//table/tbody/tr/td[@class='CalendarDaySelected']/a")).getAttribute("class");
        System.out.println("el atributo es: "+atributo);

        String cliente = "Grupo Aval ATH";
        String id = "PNF";
        String proyecto = "ATH-2021-NDE-1 Automatización";
        String servicio = "Pruebas No Funcionales";
        String tarea = "03 - Ejecución - ATM - Construcción de Automatización";
        String inicio = "";
        String fin = "";
        String nota = "Creación y Ejecución Script PNF";

        String s = ""; //1 = am, 2 = pm, NA = cierra el ciclo

        if(tiempo.contains(fecha)){

            if(atributo.equals("CalendarLinkRecordsExist")||atributo.isEmpty()){
                s="am";
            }else{
                s="NA";
            }

            while(s != "NA"){

                if(s.equals("am")){
                    inicio = "0800";
                    fin = "1200";
                    registroHora.llenarFormulario(cliente,id,proyecto,servicio,tarea,inicio,fin,nota);
                    registroHora.clicBotonEnviar();
                    s = "pm";
                }else if(s.equals("pm")){
                    inicio = "1300";
                    fin = "1730";
                    registroHora.setIDREQ(id);
                    registroHora.setInicio(inicio);
                    registroHora.setFin(fin);
                    registroHora.setNota(nota);
                    registroHora.clicBotonEnviar();
                    s = "NA";
                }

            }

        }else{
            System.out.println("Revisa por que no esta tomando bien el dia!!");
        }
    }

    @Then("^Finalizar la sesion$")
    public void cerrarSesion() {

        utilidades.logout(driver);

        actualResult = driver.getTitle();
        expectedResult = "Time Tracker - Sesión iniciada";

        System.out.println("Se cerró la sesión correctamente!");

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }


}
