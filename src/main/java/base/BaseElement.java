package base;

import waiting.WebDriverWaits;
import org.openqa.selenium.WebElement;

//добавить сеттеры и геттеры

public class BaseElement {
    String locator;

    public BaseElement(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }

    public String getText() {
        return (new WebDriverWaits()).explicitWaitOfElement(locator).getText();
    }

    public void click() {
        (new WebDriverWaits()).explicitWaitOfElement(locator).click();
    }

    public WebElement getWebElement() {
        return (new WebDriverWaits().explicitWaitOfElement(locator));
    }

    public String getAttribute(String attribute) {
        return (new WebDriverWaits()).explicitWaitOfElement(locator).getAttribute(attribute);
    }

    public boolean isPresence() {
        return (new WebDriverWaits().explicitWaitOfElement(locator) != null);

    }

    public boolean isVisible() {
        return (new WebDriverWaits().explicitWaitOfElement(locator).isDisplayed());

    }

}
