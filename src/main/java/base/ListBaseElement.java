package base;

import waiting.WebDriverWaits;

import java.util.ArrayList;

/**
 * ListBaseElement class - custom ListBaseElement wrapper
 * handles list element separation
 */
public class ListBaseElement extends BaseElement {

    /** list of elements in a list */
    public java.util.List<BaseElement> list;

    /**
     * Constructor
     *
     * @param locator XPath of the webElement
     */
    public ListBaseElement(String locator) {
        super(locator);
        getList();
    }


    /**
     * writes list elements to public list
     */
    private void getList() {
        list = new ArrayList<>();
        String template = locator + "[%s]";
        int size = (new WebDriverWaits().explicitWaitOfElements(locator)).size();
        for (int i = 1; i < size + 1; i++) {
            String locatorOfElement = String.format(template,i);
            list.add(new BaseElement(locatorOfElement));
        }
    }

}
