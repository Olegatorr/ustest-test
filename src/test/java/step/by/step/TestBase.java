package step.by.step;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.annotation.Nullable;
import java.util.Arrays;
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
        //login(new LoginData("ROBOTESTER", "ELECTROSTALIN", "English"));
    }

    // runs before every @Test-annotated method
    @BeforeMethod
    public void beforeMethod(){
        driver = driverData.driver;
        wait = driverData.wait;
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            onTestFailure(result);
        }
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
        driver.findElement(By.id("LoginForm:userid")).sendKeys(loginData.login);
        driver.findElement(By.id("LoginForm:password")).sendKeys(loginData.password);
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

    protected void goToMainPage() {
        driver.get("http://tos2.solvo.ru:37580/aet/private/main.xhtml");
    }

    protected void goToRailcarMarshaling(){
        driver.get("http://tos2.solvo.ru:37580/aet/private/rw_train_vizit.xhtml");
    }

    protected void goToLogin(){
        driver.get("http://tos2.solvo.ru:37580/aet/login.xhtml");
    }

    public void onTestFailure(ITestResult result){
        System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        System.out.println(result.getMethod().getMethodName() + " failed!");

        saveScreenshot(driver);
    }

    @Attachment
    public byte[] saveScreenshot(WebDriver driver){
        return (((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
    }

    protected void clickNew(){
        driver.findElement(By.cssSelector(".new")).click();
    }

    protected void clickEdit(){
        driver.findElement(By.cssSelector(".new_config")).click();
        driver.findElement(By.cssSelector(".ui-menuitem-link .edit")).click();
    }

}
