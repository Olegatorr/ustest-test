package forms;


import base.BaseElement;
import base.ListBaseElement;

import java.util.Random;

public class LanguageDropDown {
    protected BaseElement element ;
    protected ListBaseElement options;
    String optionTemplate = "//*[@id=\"LoginForm:language_%s\"] ";

    public LanguageDropDown(String locator, String optionsLocator) {
        super();
        element = new BaseElement(locator);
        options  = new ListBaseElement(optionsLocator);
    }

    public void select() {
        element.click();
        String optionLocator = String.format(optionTemplate, selectRandomOption());
        (new BaseElement(optionLocator)).click();
    }

    public void select(String option) {
        element.click();
        String optionLocator = String.format(optionTemplate, option);
        (new BaseElement(optionLocator)).click();
    }

    private int selectRandomOption() {
        Random random = new Random();
        int i = random.nextInt(options.list.size() - 1);
        if (i == 0) {
            return i + 1;
        }
        return i;
    }
}
