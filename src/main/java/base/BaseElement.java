package base;

import org.openqa.selenium.StaleElementReferenceException;
import waiting.WebDriverWaits;
import org.openqa.selenium.WebElement;

//TODO: добавить сеттеры и геттеры

/**
 * BaseElement class - parent of all the others elements
 *
 * Custom wrappers with wait()'s
 */
public class BaseElement {

    /** XPath of the web element */
    String locator;


    /**
     * Constructor
     *
     * @param locator XPath of the elem
     */
    public BaseElement(String locator) {
        this.locator = locator;
    }


    /**
     * XPath locator getter
     *
     * @return Xpath
     */
    public String getLocator() {
        return locator;
    }


    /**
     * Element text getter
     *
     * @return String text of the web element
     */
    public String getText() {
        return (new WebDriverWaits()).explicitWaitOfElement(locator).getText();
    }


    /** wrapped click with wait() */
    public void click() {
        try {
            (new WebDriverWaits()).explicitWaitOfElement(locator).click();
        } catch(StaleElementReferenceException e) {
            (new WebDriverWaits()).explicitWaitOfElement(locator).click();
        }
    }


    /**
     * WebElement object getter
     *
     * @return WebElement
     */
    public WebElement getWebElement() {
        return (new WebDriverWaits().explicitWaitOfElement(locator));
    }

    /**
     * Specified attribute getter
     *
     * @param attribute attribute to get
     * @return attribute itself
     */
    public String getAttribute(String attribute) {
        return (new WebDriverWaits()).explicitWaitOfElement(locator).getAttribute(attribute);
    }

    /**
     * Checker if wrapped element is present (exists, but not necessarily shown)
     *
     * @return boolean
     * TRUE if element is present
     */
    public boolean isPresent() {
        return (new WebDriverWaits().explicitWaitOfElement(locator) != null);
    }

    /**
     * Checker if wrapped element is visible
     *
     * @return boolean
     * TRUE if element is visible
     */
    public boolean isVisible() {
        return (new WebDriverWaits().explicitWaitOfElement(locator).isDisplayed());
    }

}
