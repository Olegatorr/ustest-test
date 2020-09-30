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

/** WebDriverWaits is a custom implementation of implicit and explicit waits */
public class WebDriverWaits {

    private WebDriver driver;
    private WebDriverWait wait;

    /** Constructor */
    public WebDriverWaits() {
        driver = Browser.getInstance().driver;
        wait = new WebDriverWait(driver, ConfigParser.timeOutInSeconds);
    }

    /**
     * explicitWaitOfElement() is a custom WebElement explicit wait which returns awaited WebElement
     *
     * @param locator XPath to the element
     * @return awaited WebElement
     */
    public WebElement explicitWaitOfElement(String locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        return driver.findElement(By.xpath(locator));
    }

    /**
     * explicitWaitOfElements() is a custom WebElement explicit wait which returns awaited WebElements
     *
     * @param locator XPath to the element
     * @return awaited WebElements as List<WebElement>
     */
    public List<WebElement> explicitWaitOfElements(String locator) {
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
        return driver.findElements(By.xpath(locator));
    }


    /**
     * Custom page Open method with implemented wait() of signature locator element
     *
     * @param locator XPath to the locator to bew waited for
     */
    public void pageOpenWait(String locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

}
