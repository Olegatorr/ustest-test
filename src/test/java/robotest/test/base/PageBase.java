package robotest.test.base;


import org.openqa.selenium.WebDriver;
import robotest.base.browser.Browser;
import robotest.base.waiting.WebDriverWaits;

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
