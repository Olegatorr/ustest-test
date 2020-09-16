package step.by.step;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public WebDriver driver;
    public Map<String, Object> vars;
    JavascriptExecutor js;
    public Wait<WebDriver> wait;
    private SessionHelper sessionHelper;
    private final Properties properties;
    // get singleton driver class instance
    DriverData driverData = DriverData.getInstance();

    public TestBase() {
        properties = new Properties();
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }
    // runs once before ALL the tests
    @BeforeSuite
    public void setUp() {
        driver = driverData.driver;
        wait = driverData.wait;
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //login(new LoginData("ROBOTESTER", "ELECTROSTALIN"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    // runs before every @Test-annotated method
    @BeforeMethod
    public void beforeMethod(){
        driver = driverData.driver;
        wait = driverData.wait;
    }

    // runs after ALL the tests
    @AfterSuite
    public void tearDown(){
        driver.quit();
    }

    // login to 7.1 EE

    // go to 7.1 EE main page
    protected void goToMainPage() {
        driver.get("http://tos2.solvo.ru:37580/aet/private/main.xhtml");
    }

    // go to 7.1 Railcar Marshaling
    protected void goToRailcarMarshaling(){
        driver.findElement(By.cssSelector("#menuform\\3Aj_idt36_2 span:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#menuform\\3Aj_idt36_2_0 span")).click();
    }

    protected void goToRailcarMarshalingShort(){
        driver.get("http://tos2.solvo.ru:37580/aet/private/rw_train_vizit.xhtml");
    }

    // clink on "create new" button. Seems universal. TODO: check if universal
    protected void clickNew(){
        driver.findElement(By.cssSelector(".new")).click();
    }

    // fill RWM Number
    protected void RWTrainVizitFillName(String name){
        driver.findElement(By.id("RwTrainVizitEditForm:name")).click();
        driver.findElement(By.id("RwTrainVizitEditForm:name")).clear();
        driver.findElement(By.id("RwTrainVizitEditForm:name")).sendKeys(name);
    }

    // fill RWM Track
    protected void RWTrainVizitFillRWTrack(String track){
        driver.findElement(By.id("RwTrainVizitEditForm:way:ac_input")).click();
        driver.findElement(By.id("RwTrainVizitEditForm:way:ac_input")).clear();
        driver.findElement(By.id("RwTrainVizitEditForm:way:ac_input")).sendKeys(track);
        driver.findElement(By.cssSelector("td:nth-child(1)")).click();
    }

    // fill RWM Date
    protected void RWTrainVizitFillDate(String date){
        driver.findElement(By.id("RwTrainVizitEditForm:prepareDate_input")).click();
        driver.findElement(By.id("RwTrainVizitEditForm:prepareDate_input")).clear();
        driver.findElement(By.id("RwTrainVizitEditForm:prepareDate_input")).sendKeys(date);
    }

    // fill RWM Comment
    protected void RWTrainVizitFillComment(@Nullable String comment){
        try{
            driver.findElement(By.id("RwTrainVizitEditForm:comments")).click();
            driver.findElement(By.id("RwTrainVizitEditForm:comments")).clear();
            driver.findElement(By.id("RwTrainVizitEditForm:comments")).sendKeys(comment);
        }catch (NullPointerException e){
            System.out.println("Comment was empty");
        }
    }

    // save RWM
    protected void RWTrainVizitSave(){
        driver.findElement(By.id("RwTrainVizitEditForm:editSaveBtn")).click();
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".new")));
        }catch (org.openqa.selenium.TimeoutException e){
            Assert.fail(getErrors());
        }

    }

    // get object ID from the URL
    protected String getURLID(String url){

        String subUrl;
        try{
            subUrl = url.substring(url.indexOf("id=")+3, url.indexOf("&jfwid"));
        }catch (NullPointerException e){
            System.out.println("There were no jfwid");
            subUrl = url.substring(url.indexOf("id="));
        }catch (Exception e){
            System.out.println(e);
            subUrl = null;
        }
        return subUrl;
    }

    // get object field data by field name
    protected String checkFieldData(List<WebElement> fields, String label){
        for (WebElement field:fields) {
            // get all sub-elements
            List <WebElement> subElements = field.findElements(By.cssSelector("*"));
            // for each sub-element
            for (WebElement subElement:subElements)
            {

                // get data DIV text
                if(subElement.getText().equals(label)){
                    if(subElement.getText().equals("")){
                        try{
                            return subElement.findElement(By.cssSelector("*")).getText();
                        }catch (Exception e){
                            return null;
                        }
                    }else{
                        //in some rare cases data text may be a link (inside <a>)
                        return subElements.get(1).getText();
                    }

                }
            }
        }
        return null;
    }

    // check if any field-related errors are present
    protected boolean checkForErrors(){
        try{
            return driver.findElement(By.className("ui-messages-error")).isDisplayed();
        }catch (ElementNotVisibleException e){
            return false;
        }
    }

    // get all field-related errors as String
    protected String getErrors(){ // TODO: find a way to extract errors
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot create RWM. Errors: ");
        try{
            // this list contains every <li> with error
            List <WebElement> errors2 = driver.findElement(By.cssSelector(".ui-messages .ui-messages-error.ui-corner-all")).findElements(By.cssSelector("*"));
            //List <WebElement> errors = driver.findElement(By.cssSelector(".ui-messages .ui-messages-error.ui-corner-all")).findElements(By.cssSelector("*")).get(4).findElements(By.cssSelector("*"));
            List <WebElement> errors = driver.findElement(By.cssSelector(".ui-messages .ui-messages-error.ui-corner-all")).findElement(By.cssSelector("ul")).findElements(By.cssSelector("li"));
            // for each <li> with error get text of its <span>
            for(WebElement error:errors){
                sb.append(System.getProperty("line.separator"));
                sb.append(error.findElement(By.cssSelector("*")).getText());
            }
        }catch (Exception e){
            sb.append("unexpected.");
            sb.append(System.getProperty("line.separator"));
            sb.append(e);
        }
        return sb.toString();
    }

    protected void clickEdit(){
        driver.findElement(By.cssSelector(".new_config")).click();
        driver.findElement(By.cssSelector(".ui-menuitem-link .edit")).click();
    }

}
