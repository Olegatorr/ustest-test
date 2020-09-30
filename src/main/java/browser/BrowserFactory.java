package browser;

import parsers.ConfigParser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;


import java.util.HashMap;

/** BrowserFactory class handles different setting for different browsers */
public class BrowserFactory {


    /**
     * Method for setting up a Chrome browser
     *
     * @return WebDriver for Chrome
     */
    private static WebDriver chromeProperties() {

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
        chromePrefs.put("intl.accept_languages", ConfigParser.language);
        chromePrefs.put("safebrowsing.enabled", "true");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);

    }

    /**
     * Method for setting up a Firefox browser
     *
     * @return WebDriver for Firefox
     */
    private static WebDriver firefoxProperties() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.dir",System.getProperty("user.dir"));
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/octet-stream ");
        profile.setPreference("intl.accept_languages", ConfigParser.language);
        options.setProfile(profile);
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(options);
    }


    /**
     * Getter for WebDriver
     * reads preferred browser from config file, then applies corresponding settings and WebDriver via private methods
     *
     * @return WebDriver of preferred browser, if available
     */
    public static WebDriver getDriver() {
        switch (ConfigParser.browserType) {
            case "Chrome":
                return chromeProperties();

            case "Firefox":
                return firefoxProperties();

            default:
                return chromeProperties();
        }
    }
}