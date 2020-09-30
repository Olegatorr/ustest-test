package base;

import helpers.LanguageFactory;
import io.qameta.allure.Step;
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

    protected void goToRCM(){
        // TODO: use config to get this URL
        driver.get("http://tos2.solvo.ru:37580/aet/private/rw_train_vizit.xhtml");
    }

    protected void goToLogin(){
        driver.get(ConfigParser.loginUrl);
    }

    @Step("onTestFailure")
    public void onTestFailure(ITestResult result){
        System.out.println(result.getMethod().getMethodName() + " failed!");
        saveScreenshot(driver);
    }

    @Attachment
    public byte[] saveScreenshot(WebDriver driver){
        return (((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
    }

    // TODO: move to somewhere else
    protected void clickNew(){
        driver.findElement(By.cssSelector(".new")).click();
    }

    // TODO: move to somewhere else
    protected void clickEdit(){
        driver.findElement(By.cssSelector(".new_config")).click();
        driver.findElement(By.cssSelector(".ui-menuitem-link .edit")).click();
    }

}
