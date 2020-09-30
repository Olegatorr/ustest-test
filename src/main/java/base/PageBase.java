package base;

import org.openqa.selenium.WebDriver;
import browser.Browser;
import waiting.WebDriverWaits;

/**
 * PageBase class - parent of all page objects
 * contains driver data and universal methods
 */
public abstract class PageBase {

    public WebDriver driver;
    public abstract boolean isOpen();

    /** Constructor */
    public PageBase() {
        driver = Browser.getInstance().driver;
    }


    /**
     * getCurrentUrl() returns current page URL
     *
     * @return String current URL
     */
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }


    /**
     * pageOpenWait is a custom wait() which will wain until given locator is present
     *
     * @param pageOpenLocator XPath of locator
     */
    public void pageOpenWait(String pageOpenLocator){
        (new WebDriverWaits()).pageOpenWait(pageOpenLocator);
    }

}
