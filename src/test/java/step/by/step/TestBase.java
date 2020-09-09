package step.by.step;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public WebDriver driver;
    public Map<String, Object> vars;
    JavascriptExecutor js;
    public Wait<WebDriver> wait;

    // get singleton driver class instance
    DriverData driverData = DriverData.getInstance();

    // runs once before ALL the tests
    @BeforeSuite
    public void setUp() {
        driver = driverData.driver;
        wait = driverData.wait;
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        login(new LoginData("ROBOTESTER", "ELECTROSTALIN"));
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
    protected void login(LoginData loginData) {

        driver.get("http://tos2.solvo.ru:37580/aet/login.xhtml");
        driver.manage().window().setSize(new Dimension(1800, 1000));
        driver.findElement(By.id("LoginForm:userid")).click();
        driver.findElement(By.id("LoginForm:userid")).sendKeys(loginData.getRobotester());
        driver.findElement(By.id("LoginForm:password")).sendKeys(loginData.getElectrostalin());
        driver.findElement(By.id("LoginForm:language")).click();
        driver.findElement(By.id("LoginForm:language_label")).click();
        driver.findElement(By.id("LoginForm:language_0")).click();
        driver.findElement(By.cssSelector(".ui-button-text:nth-child(2)")).click();

        //in case of an existing active login we need to confirm login
        try{
            {
                WebElement element = driver.findElement(By.cssSelector("#duplicateLoginForm\\3A duplicateLoginYesBtn > .ui-button-text"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element).perform();
            }
            driver.findElement(By.cssSelector("#duplicateLoginForm\\3A duplicateLoginYesBtn > .ui-button-text")).click();
            System.out.println("There was active login");
        }catch (Exception e){
            System.out.println("There was no active login");
        }

        //then wait for redirection to the main page to happen
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".worker-avatar-clip")));
    }

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

    protected void RWTrainVizitFillName(String name){
        driver.findElement(By.id("RwTrainVizitEditForm:name")).click();
        // TODO: generate unique marshaling name
        driver.findElement(By.id("RwTrainVizitEditForm:name")).sendKeys(name);
    }

    protected void RWTrainVizitFillRWTrack(String track){
        driver.findElement(By.id("RwTrainVizitEditForm:way:ac_input")).click();
        driver.findElement(By.id("RwTrainVizitEditForm:way:ac_input")).sendKeys(track);
        driver.findElement(By.cssSelector("td:nth-child(1)")).click();
    }

    protected void RWTrainVizitFillDate(String date){
        driver.findElement(By.id("RwTrainVizitEditForm:prepareDate_input")).click();
        driver.findElement(By.id("RwTrainVizitEditForm:prepareDate_input")).sendKeys(date);
    }

    protected void RWTrainVizitFillComment(@Nullable String comment){
        try{
            driver.findElement(By.id("RwTrainVizitEditForm:comments")).click();
            driver.findElement(By.id("RwTrainVizitEditForm:comments")).sendKeys(comment);
        }catch (NullPointerException e){
            System.out.println("Comment was empty");
        }
    }

    protected void RWTrainVizitSave(){
        driver.findElement(By.id("RwTrainVizitEditForm:editSaveBtn")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".new")));
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
            for (WebElement subElement:subElements){

                // get data DIV text
                if(subElement.getText().equals(label)){
                    if(subElement.getText().equals("")){
                        try{
                            return subElement.findElement(By.cssSelector("*")).getText();
                        }catch (Exception e){
                            return null;
                        }
                    }else{
                        return subElements.get(1).getText();
                    }
                //in some rare cases data text may be a link (inside <a>)
                }
            }
        }
        return null;
    }
}
