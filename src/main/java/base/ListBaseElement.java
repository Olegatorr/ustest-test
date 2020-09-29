package base;

import waiting.WebDriverWaits;

import java.util.ArrayList;


public class ListBaseElement extends BaseElement {
    public java.util.List<BaseElement> list;

    public ListBaseElement(String locator) {
        super(locator);
        getList();
    }

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
