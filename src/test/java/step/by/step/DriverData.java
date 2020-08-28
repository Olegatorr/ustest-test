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

    public DriverData(){
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        wait = new WebDriverWait(driver, 5, 1000); // TODO: check wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS)

        System.out.println("DriverData initialized");
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public Wait<WebDriver> getWait(){
        return this.wait;
    }

    public static DriverData getInstance() {
        if (instance == null) {
            instance = new DriverData();
        }
        return instance;
    }

}
