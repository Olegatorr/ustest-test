package base;

import helpers.LanguageFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import java.util.concurrent.TimeUnit;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import parsers.ConfigParser;
import parsers.XmlParser;

import static browser.Browser.*;

public class TestBase {

    public static WebDriver driver;
    private String jsonPath = "./src/main/resources/JSON.json";

    @BeforeSuite
    public void setUp() {
        ConfigParser.getData(jsonPath);
        LanguageFactory.getData();
        driver = getInstance().driver;
        driver.manage().timeouts().implicitlyWait(ConfigParser.timeOutInSeconds, TimeUnit.SECONDS);
    }
/*
    @AfterMethod
    public void afterMethod(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            onTestFailure(result);
        }
    }
    */

    // runs after ALL the tests
    @AfterSuite
    public void tearDown(){
        driver.quit();
    }
/*
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
*/
    protected void goToRailcarMarshaling(){
        driver.get("http://tos2.solvo.ru:37580/aet/private/rw_train_vizit.xhtml");
    }

    protected void goToLogin(){
        driver.get(ConfigParser.loginUrl);
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
