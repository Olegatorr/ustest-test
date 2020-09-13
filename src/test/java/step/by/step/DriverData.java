package step.by.step;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class DriverData {
    private static DriverData instance;

    public WebDriver driver;
    public Map<String, Object> vars;
    JavascriptExecutor js;
    public Wait<WebDriver> wait;

    private DriverData(){
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        wait = new WebDriverWait(driver, 5, 1000);
    }

    //return driver object
    public WebDriver getDriver(){
        return this.driver;
    }

    //return wait object
    public Wait<WebDriver> getWait(){
        return this.wait;
    }

    //singleton DriverData initialization
    public static DriverData getInstance() {
        if (instance == null) {
            instance = new DriverData();
        }
        return instance;
    }

}
