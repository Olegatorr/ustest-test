package test.test;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Map;

public class Login extends TestMain{

    @BeforeTest
    public void setUp() {
        super.setUp();
    }

    @AfterTest
    public void tearDown() {
        super.tearDown();
    }

    @Test
    public void login() {
        //driver = getDriverInstance();

        driver.get("http://tos2.solvo.ru:37580/aet/login.xhtml");
        driver.manage().window().setSize(new Dimension(1800, 1000));
        driver.findElement(By.id("LoginForm:userid")).click();
        driver.findElement(By.id("LoginForm:userid")).sendKeys("ROBOTESTER");
        driver.findElement(By.id("LoginForm:password")).sendKeys("ELECTROSTALIN");
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

        driver.get("http://tos2.solvo.ru:37580/aet/private/main.xhtml");
        if(driver.getCurrentUrl().endsWith("main.xhtml")){
            System.out.println("SUCCESS");
        }else{
            System.out.println("FAILURE");
            Assert.fail();
        }
//        final Wait<WebDriver> wait = new WebDriverWait(driver, 8, 1000);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".worker-avatar-clip")));
    }
}