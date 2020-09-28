package robotest.base.baseForms;

import robotest.base.waiting.WebDriverWaits;

public class Field extends BaseElement {
    public Field(String locator) {
        super(locator);
    }

    public void sendKeys(String keys) {
        (new WebDriverWaits()).explicitWaitOfElement(locator).sendKeys(keys);
    }

    public void clear() {
        (new WebDriverWaits()).explicitWaitOfElement(locator).clear();
    }

    public void clickWithClear() {
        click();
        clear();
    }

    public void sendKeysWithClear(String keys) {
        clickWithClear();
        sendKeys(keys);
    }
}
