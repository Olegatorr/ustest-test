package robotest.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import robotest.test.base.PageBase;

public class MainPage extends PageBase {

    String URL_MATCH = "private/main.xhtml";
    private WebDriver driver;
    private Wait<WebDriver> wait;

    public MainPage(WebDriver driver, Wait<WebDriver> wait) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("This is not the page you are expected");
        }

        PageFactory.initElements(driver, this);
        this.wait = wait;
        this.driver = driver;
    }

}
