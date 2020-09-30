package base;

import waiting.WebDriverWaits;

/** Button class - custom button wrapper with wait() */
public class Button extends BaseElement {

    /**
     * Constructor
     *
     * @param locator XPath of the webElement
     */
    public Button(String locator) {
        super(locator);
    }


    /**
     * getHref returns link to whichever page the button leads to, if specified
     *
     * @return String href link
     */
    public String getHref() {
        return (new WebDriverWaits().explicitWaitOfElement(locator)).getAttribute("href");
    }
}