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


/**
 * TestBase class, parent of all the test classes
 * contains JUnit test preparation annotations
 * and universal methods that are expected to be accessed from several test classes
 */
public class TestBase {

    public static WebDriver driver;


    /**
     * JUnit setup method
     * runs ONCE before ALL the tests
     */
    @BeforeSuite
    public void setUp() {
        String jsonPath = "./src/main/resources/JSON.json";
        ConfigParser.getData(jsonPath);

        LanguageFactory.getData();
        driver = getInstance().driver;
        driver.manage().timeouts().implicitlyWait(ConfigParser.timeOutInSeconds, TimeUnit.SECONDS);
    }

    /**
     * JUnit afterMethod method
     * runs after EVERY test method (test methods are annotated with @Test)
     *
     * @param result is automatically parsed by the test framework
     *               contains last test data, which can be used
     */
    @AfterMethod
    public void afterMethod(ITestResult result){
        // check if last test was a failure
        if(result.getStatus() == ITestResult.FAILURE){
            onTestFailure(result);
        }
    }

    /**
     * JUnit tearDown method
     * runs ONCE after ALL the tests
     */
    @AfterSuite
    public void tearDown(){
        driver.quit();
    }

    // TODO: move to RCM fill page
    protected void goToRCM(){
        // TODO: use config to get this URL
        driver.get("http://tos2.solvo.ru:37580/aet/private/rw_train_vizit.xhtml");
    }

    // TODO: move to login page
    protected void goToLogin(){
        driver.get(ConfigParser.loginUrl);
    }


    /**
     * Method which is needed to be called after every unexpected test failure
     *
     * @param result is automatically parsed by the test framework
     *               contains last test data, which can be used
     */
    @Step("onTestFailure")
    public void onTestFailure(ITestResult result){
        System.out.println(result.getMethod().getMethodName() + " failed!");
        saveScreenshot(driver);
    }


    /**
     * @Attachment annotated methods are recognized by the test framework;
     *               their return is automatically attached to Allure test report
     * @param driver webDriver instance
     * @return data to be attached to Allure test report
     */
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
