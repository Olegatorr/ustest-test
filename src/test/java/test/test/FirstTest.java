package test.test;
import org.junit.Test;
        import org.junit.Before;
        import org.junit.After;
        import static org.junit.Assert.*;
        import static org.hamcrest.CoreMatchers.is;
        import static org.hamcrest.core.IsNot.not;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.firefox.FirefoxDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.remote.RemoteWebDriver;
        import org.openqa.selenium.remote.DesiredCapabilities;
        import org.openqa.selenium.Dimension;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.interactions.Actions;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;
        import org.openqa.selenium.JavascriptExecutor;
        import org.openqa.selenium.Alert;
        import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.TestRunner;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.Console;
import java.util.*;
        import java.net.MalformedURLException;
        import java.net.URL;
public class FirstTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;



    @BeforeTest
    public void setUp() {
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();

    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @org.testng.annotations.Test (priority = 1)
    public void login() {
        driver.get("http://tos2.solvo.ru:37580/aet/login.xhtml");
        driver.manage().window().setSize(new Dimension(1936, 1066));
        driver.findElement(By.id("LoginForm:userid")).click();
        driver.findElement(By.id("LoginForm:userid")).sendKeys("olegatorw");
        driver.findElement(By.id("LoginForm:password")).sendKeys("123");
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
        }catch (Exception e){
            System.out.println("There was no active login");
        }
    }

    @org.testng.annotations.Test (priority = 2)
    public void makeBoLWithGC() {
        driver.get("http://tos2.solvo.ru:37580/aet/private/bill_of_lading.xhtml?id=1014241");
        //driver.findElement(By.linkText("Processing")).click(); // TODO: Examine why tf it isn't working
        //driver.findElement(By.linkText("Cargo")).click();
        //driver.findElement(By.cssSelector(".ui-md-12:nth-child(1) .present-card-header")).click();
        driver.findElement(By.linkText("Bill of Ladings")).click();
        {
            WebElement element = driver.findElement(By.linkText("Bill of Ladings"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".new"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector(".new")).click();
        driver.findElement(By.id("BillOfLadingEditForm:docId")).sendKeys("autotest_BoL_00000001");
        driver.findElement(By.id("BillOfLadingEditForm:lineAgentId:ac_input")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("#BillOfLadingEditForm\\3AlineAgentId\\3AsearchBtn > .ui-button-icon-left"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector("#BillOfLadingEditForm\\3AlineAgentId\\3AsearchBtn > .ui-button-icon-left")).click();
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.id("BillOfLadingEditForm:lineAgentId:searchBtn_dlg_modal")).click();
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector(".ui-state-hover > td:nth-child(2)")).click();
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("#BillOfLadingEditForm\\3A contractId\\3AsearchBtn > .ui-button-icon-left")).click();
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector(".ui-datatable-even > td:nth-child(2)")).click();
        driver.switchTo().defaultContent();
        {
            WebElement element = driver.findElement(By.cssSelector("#BillOfLadingEditForm\\3A destPortId\\3AsearchBtn > .ui-button-icon-left"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector("#BillOfLadingEditForm\\3A destPortId\\3AsearchBtn > .ui-button-icon-left")).click();
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector(".ui-state-hover > td:nth-child(3)")).click();
        driver.switchTo().defaultContent();
        {
            WebElement element = driver.findElement(By.cssSelector("#BillOfLadingEditForm\\3AloadPortId\\3AsearchBtn > .ui-button-icon-left"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector("#BillOfLadingEditForm\\3AloadPortId\\3AsearchBtn > .ui-button-icon-left")).click();
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.switchTo().frame(0);
        driver.findElement(By.id("PortHelper_datalist:listForm:datalist:6:nameOutput")).click();
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector(".ui-chkbox-icon")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("#BillOfLadingEditForm\\3A editSaveBtn > .ui-button-text"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector("#BillOfLadingEditForm\\3A editSaveBtn > .ui-button-text")).click();
        driver.findElement(By.linkText("Cargo")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".new"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector(".new")).click();
        driver.findElement(By.cssSelector("#BillOfLadingCargoEditForm\\3A cargoGroupId\\3AsearchBtn > .ui-button-icon-left")).click();
        driver.switchTo().frame(0);
        driver.findElement(By.id("CargoGroupHelper_datalist:toolbarForm:quickFilter")).click();
        driver.findElement(By.id("CargoGroupHelper_datalist:toolbarForm:quickFilter")).sendKeys("ar");
        driver.findElement(By.id("CargoGroupHelper_datalist:toolbarForm:quickFilter")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector(".ui-state-hover > td:nth-child(2)")).click();
        driver.switchTo().defaultContent();
        driver.findElement(By.id("BillOfLadingCargoEditForm:packId:ac_input")).click();
        driver.findElement(By.id("BillOfLadingCargoEditForm:packId:ac_input")).sendKeys("b");
        driver.findElement(By.cssSelector(".ui-state-highlight > td:nth-child(1)")).click();
        driver.findElement(By.id("BillOfLadingCargoEditForm:qty_input")).click();
        driver.findElement(By.id("BillOfLadingCargoEditForm:qty_input")).sendKeys("500");
        driver.findElement(By.id("BillOfLadingCargoEditForm:iweightBrutto:scaledValue_input")).click();
        driver.findElement(By.id("BillOfLadingCargoEditForm:iweightBrutto:scaledValue_input")).sendKeys("500.000");
        driver.findElement(By.cssSelector(".ui-g-4:nth-child(8) > .ui-g-5")).click();
        driver.findElement(By.id("BillOfLadingCargoEditForm:weightNetto:scaledValue_input")).click();
        driver.findElement(By.id("BillOfLadingCargoEditForm:weightNetto:scaledValue_input")).sendKeys("500.000");
        driver.findElement(By.id("BillOfLadingCargoEditForm:weightNetto")).click();
        driver.findElement(By.id("BillOfLadingCargoEditForm:length:scaledValue_input")).click();
        driver.findElement(By.id("BillOfLadingCargoEditForm:length:scaledValue_input")).sendKeys("1.0");
        driver.findElement(By.id("BillOfLadingCargoEditForm:width:scaledValue_input")).click();
        driver.findElement(By.id("BillOfLadingCargoEditForm:width:scaledValue_input")).sendKeys("2.0");
        driver.findElement(By.id("BillOfLadingCargoEditForm:height:scaledValue_input")).click();
        driver.findElement(By.id("BillOfLadingCargoEditForm:height:scaledValue_input")).sendKeys("3.0");
        driver.findElement(By.cssSelector(".ui-g-4:nth-child(17) > .ui-g-5")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("#BillOfLadingCargoEditForm\\3A editSaveBtn > .ui-button-text"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector("#BillOfLadingCargoEditForm\\3A editSaveBtn > .ui-button-text")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("#object_action_menu\\3AmenuBtn > .ui-button-text"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector("#object_action_menu\\3AmenuBtn > .ui-button-text")).click();
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.cssSelector(".ui-state-hover > .ui-menuitem-text")).click();
        {
            WebElement element = driver.findElement(By.id("object_action_menu:menuBtn"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.id("object_action_menu:menuBtn")).click();
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.cssSelector(".ui-state-hover > .ui-menuitem-text")).click();
    }
}
