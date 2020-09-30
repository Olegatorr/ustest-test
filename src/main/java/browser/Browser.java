package browser;
import org.openqa.selenium.WebDriver;

/** Browser class handles driver */
public class Browser {

    private static Browser instance;
    public WebDriver driver;

    /** Constructor */
    private Browser() {
        driver = BrowserFactory.getDriver();
    }


    /**
     * getInstance returns singleton Browser obj
     *
     * @return Browser
     */
    public static Browser getInstance() {
        if (instance == null) {
            instance = new Browser();
        }
        return instance;
    }
}
