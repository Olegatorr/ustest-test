package robotest.base.browser;

import org.openqa.selenium.WebDriver;

public class Browser {
    private static Browser instance;
    public WebDriver driver;

    private Browser() {
        driver = BrowserFactory.getDriver();
    }

    public static Browser getInstance() {
        if (instance == null) {
            instance = new Browser();
        }
        return instance;
    }
}
