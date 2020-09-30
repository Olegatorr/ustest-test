package base;


import org.openqa.selenium.WebDriver;
import browser.Browser;
import waiting.WebDriverWaits;

public class PageBase {
    public WebDriver driver;
    public String URL_MATCH ;

    public PageBase(String url) {
        driver = Browser.getInstance().driver;
        URL_MATCH = url;
    }

    public  boolean isOpen() {
        return driver.getCurrentUrl().contains(URL_MATCH);
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public void pageOpenWait(String pageOpenLocator){
        (new WebDriverWaits()).pageOpenWait(pageOpenLocator);
    }

}
