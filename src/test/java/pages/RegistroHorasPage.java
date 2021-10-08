package pages;

import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.Utilidades;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

public class RegistroHorasPage {

    WebDriver driver;
    Utilidades utilidades;

    @FindBy(how = How.ID, using = "client")
    private WebElement slcCliente;

    @FindBy(how = How.ID, using = "cf_1")
    private WebElement txtIDREQ;

    @FindBy(how = How.ID, using = "project")
    private WebElement slcProyecto;

    @FindBy(how = How.ID, using = "service")
    private WebElement slcServicio;

    @FindBy(how = How.XPATH, using = "/html/body/table/tbody/tr[1]/td/form/table[1]/tbody/tr/td[1]/table/tbody/tr[6]/td[2]/input")
    private WebElement btnTaskAnterior;

    @FindBy(how = How .ID, using = "task")
    private WebElement slcTarea;

    @FindBy(how = How.ID, using = "start")
    private WebElement txtInicio;

    @FindBy(how = How.ID, using = "finish")
    private WebElement txtFin;

    @FindBy(how = How.ID, using = "note")
    private WebElement txtNota;

    @FindBy(how = How.ID, using = "btn_submit")
    private WebElement btnEnviar;

    public RegistroHorasPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utilidades = new Utilidades(driver);
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

    public void clicBotonTaskAnterior(String feature) throws IOException {
        utilidades.highLight(btnTaskAnterior);
        utilidades.screenShot(feature,btnTaskAnterior);
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

    public void clicBotonEnviar(String feature)throws IOException{
        utilidades.highLight(btnEnviar);
        utilidades.screenShot(feature,btnEnviar);
        btnEnviar.click();
    }

    public void llenarFormulario(String cliente, String id, String proyecto,
                                 String servicio, String tarea, String inicio,
                                 String fin, String nota,String feature) throws IOException{
        selectCliente(cliente);
        setIDREQ(id);
        selectProyecto(proyecto);
        selectServicio(servicio);

        clicBotonTaskAnterior(feature);

        selectTarea(tarea);
        setInicio(inicio);
        setFin(fin);
        setNota(nota);
    }

}
