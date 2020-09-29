package base;


import org.openqa.selenium.WebDriver;
import browser.Browser;
import waiting.WebDriverWaits;

public abstract class PageBase {
    public WebDriver driver;
    public PageBase() {
        driver = Browser.getInstance().driver;
    }

    public abstract boolean isOpen();

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public void pageOpenWait(String pageOpenLocator){
        (new WebDriverWaits()).pageOpenWait(pageOpenLocator);
    }

}
