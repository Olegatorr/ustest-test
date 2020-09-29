package base;

import waiting.WebDriverWaits;

public class Button extends BaseElement {
    public Button(String locator) {
        super(locator);
    }

    public String getHref() {
        return (new WebDriverWaits().explicitWaitOfElement(locator)).getAttribute("href");
    }
}