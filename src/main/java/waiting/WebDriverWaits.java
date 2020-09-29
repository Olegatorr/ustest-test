package waiting;

import org.openqa.selenium.StaleElementReferenceException;
import parsers.ConfigParser;
import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebDriverWaits {
    private WebDriver driver;
    private WebDriverWait wait;

    public WebDriverWaits() {
        driver = Browser.getInstance().driver;
        wait = new WebDriverWait(driver, ConfigParser.timeOutInSeconds);
    }

    public WebElement explicitWaitOfElement(String locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        return driver.findElement(By.xpath(locator));
    }

    public List<WebElement> explicitWaitOfElements(String locator) {
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
        return driver.findElements(By.xpath(locator));
    }

    public void pageOpenWait(String locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

}
