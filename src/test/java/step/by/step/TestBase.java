package step.by.step;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public WebDriver driver;
    public Map<String, Object> vars;
    JavascriptExecutor js;
    public Wait<WebDriver> wait;

    @BeforeTest
    public void setUp() {

    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        wait = new WebDriverWait(driver, 5, 1000); // TODO: check wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS)
        login(new LoginData("ROBOTESTER", "ELECTROSTALIN"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @AfterTest
    public void tearDown(){

    }

    protected void login(LoginData loginData) {
        driver.get("http://tos2.solvo.ru:37580/aet/login.xhtml");
        System.out.println("login form goto");
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
