package base;

import org.openqa.selenium.WebDriver;
import browser.Browser;
import waiting.WebDriverWaits;

/**
 * PageBase class - parent of all page objects
 * contains driver data and universal methods
 */
public class PageBase {
    public WebDriver driver;
    public String URL_MATCH ;

	/** Constructor */
    public PageBase(String url) {
        driver = Browser.getInstance().driver;
        URL_MATCH = url;
    }

    public  boolean isOpen() {
        return driver.getCurrentUrl().contains(URL_MATCH);
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
