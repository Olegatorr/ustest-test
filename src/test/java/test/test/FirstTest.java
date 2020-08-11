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
        import java.util.*;
        import java.net.MalformedURLException;
        import java.net.URL;
public class FirstTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void first() {
        driver.get("http://tos2.solvo.ru:37580/aet/login.xhtml");
        driver.manage().window().setSize(new Dimension(965, 879));
        driver.findElement(By.id("LoginForm:userid")).click();
        driver.findElement(By.id("LoginForm:userid")).sendKeys("olegatorw");
        driver.findElement(By.id("LoginForm:password")).sendKeys("123");
        driver.findElement(By.id("LoginForm:language")).click();
        driver.findElement(By.id("LoginForm:language_label")).click();
        driver.findElement(By.id("LoginForm:language_0")).click();
        driver.findElement(By.cssSelector(".ui-button-text:nth-child(2)")).click();
        driver.close();
    }
}
