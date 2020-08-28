package step.by.step;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public WebDriver driver;
    public Map<String, Object> vars;
    JavascriptExecutor js;
    public Wait<WebDriver> wait;
    DriverData driverData = DriverData.getInstance();

    @BeforeSuite
    public void setUp() {
        System.out.println("setup");

        //DriverData driverData = DriverData.getInstance();

        driver = driverData.getDriver();
        wait = driverData.getWait();

        //driver = new FirefoxDriver();
        //js = (JavascriptExecutor) driver;
        //vars = new HashMap<String, Object>();
        //wait = new WebDriverWait(driver, 5, 1000); // TODO: check wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS)

        //login(new LoginData("ROBOTESTER", "ELECTROSTALIN"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMethod");
        driver = driverData.getDriver();
        wait = driverData.getWait();
    }

    @AfterSuite
    public void tearDown(){
        System.out.println("tearDown");
        driver.quit();
    }

    protected void login(LoginData loginData) {

        System.out.println("login");
        driver.get("http://tos2.solvo.ru:37580/aet/login.xhtml");
        driver.manage().window().setSize(new Dimension(1800, 1000));
        driver.findElement(By.id("LoginForm:userid")).click();
        driver.findElement(By.id("LoginForm:userid")).sendKeys(loginData.getRobotester());
        driver.findElement(By.id("LoginForm:password")).sendKeys(loginData.getElectrostalin());
        driver.findElement(By.id("LoginForm:language")).click();
        driver.findElement(By.id("LoginForm:language_label")).click();
        driver.findElement(By.id("LoginForm:language_0")).click();
        driver.findElement(By.cssSelector(".ui-button-text:nth-child(2)")).click();

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
    }

    protected void goToMainPage() {
        driver.get("http://tos2.solvo.ru:37580/aet/private/main.xhtml");
    }

}
