package pages;

import org.openqa.selenium.support.PageFactory;
import utilities.Utilidades;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegistroHorasPage {

    WebDriver driver;
    Utilidades utilidades;

    @FindBy(id = "client")
    WebElement slcCliente;

    @FindBy(id = "cf_1")
    WebElement txtIDREQ;

    @FindBy(id = "project")
    WebElement slcProyecto;

    @FindBy(id = "service")
    WebElement slcServicio;

    @FindBy(xpath = "/html/body/table/tbody/tr[1]/td/form/table[1]/tbody/tr/td[1]/table/tbody/tr[6]/td[2]/input")
    WebElement btnTaskAnterior;

    @FindBy(id = "task")
    WebElement slcTarea;

    @FindBy(id = "start")
    WebElement txtInicio;

    @FindBy(id = "finish")
    WebElement txtFin;

    @FindBy(id = "note")
    WebElement txtNota;

    @FindBy(id = "btn_submit")
    WebElement btnEnviar;

    public RegistroHorasPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void selectCliente(String cliente){
        new Select(slcCliente).selectByVisibleText(cliente);
    }
    public void setIDREQ(String id){
        txtIDREQ.sendKeys(id);
    }

    public void selectProyecto(String proyecto){
        new Select(slcProyecto).selectByVisibleText(proyecto);
    }

    public void selectServicio(String servicio){
        new Select(slcServicio).selectByVisibleText(servicio);
    }

    public void clicBotonTaskAnterior(){
        btnTaskAnterior.click();
    }

    public void selectTarea(String tarea){
        new Select(slcTarea).selectByVisibleText(tarea);
    }

    public void setInicio(String inicio){
        txtInicio.sendKeys(inicio);
    }

    public void setFin(String fin){
        txtFin.sendKeys(fin);
    }

    public void setNota(String nota){
        txtNota.sendKeys(nota);
    }

    public void clicBotonEnviar(){
        utilidades = new Utilidades(driver);
        utilidades.highLight(btnEnviar);
        btnEnviar.click();
    }

    public void llenarFormulario(String cliente, String id, String proyecto,
                                 String servicio, String tarea, String inicio,
                                 String fin, String nota){
        selectCliente(cliente);
        setIDREQ(id);
        selectProyecto(proyecto);
        selectServicio(servicio);

        clicBotonTaskAnterior();

        selectTarea(tarea);
        setInicio(inicio);
        setFin(fin);
        setNota(nota);
    }

}
