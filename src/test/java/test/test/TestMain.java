package test.test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.TestNG;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.Map;

public class TestMain {

    //public WebDriver driver = new FirefoxDriver();
    //public Map<String, Object> vars = new HashMap<String, Object>();
    //JavascriptExecutor js = (JavascriptExecutor) driver;

    public WebDriver driver;
    public Map<String, Object> vars;
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

}
