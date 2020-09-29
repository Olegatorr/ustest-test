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

public class BrowserFactory {

    private static WebDriver chromeProperties() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
        chromePrefs.put("intl.accept_languages", ConfigParser.language);
        chromePrefs.put("safebrowsing.enabled", "true");
        chromePrefs.put("safebrowsing.enabled", "true");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);

    }

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