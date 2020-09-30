package base;

import waiting.WebDriverWaits;

/** Field class - custom field wrapper with wait() */
public class Field extends BaseElement {

    /**
     * Constructor
     *
     * @param locator XPath of the webElement
     */
    public Field(String locator) {
        super(locator);
    }

    /**
     * custom wrapped sendKeys
     *
     * @param keys String to be sent to the field
     */
    public void sendKeys(String keys) {
        (new WebDriverWaits()).explicitWaitOfElement(locator).sendKeys(keys);
    }


    /** custom wrapped field clear() */
    public void clear() {
        (new WebDriverWaits()).explicitWaitOfElement(locator).clear();
    }

    /** clickWithClear unites click() and clear() */
    public void clickWithClear() {
        click();
        clear();
    }

    /** clickWithClear unites click() , clear() and  sendKeys() */
    public void sendKeysWithClear(String keys) {
        clickWithClear();
        sendKeys(keys);
    }
}
