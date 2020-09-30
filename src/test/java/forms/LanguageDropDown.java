package forms;

import base.BaseElement;
import base.ListBaseElement;

import java.util.Random;

/**
 * LanguageDropDown class containing methods fo work with
 * language dropDown on Login page only (since it is unique)
 */
public class LanguageDropDown {

    protected BaseElement element ;
    protected ListBaseElement options;

    String optionTemplate = "//*[@id=\"LoginForm:language_%s\"] ";


    /**
     * Constructor
     *
     * @param locator XPath of the dropDown
     * @param optionsLocator XPath of the dropDown list elements
     */
    public LanguageDropDown(String locator, String optionsLocator) {
        super();
        element = new BaseElement(locator);
        options  = new ListBaseElement(optionsLocator);
    }


    /**
     * select and click random dropdown option
     */
    public void select() {
        element.click();
        String optionLocator = String.format(optionTemplate, selectRandomOption());
        (new BaseElement(optionLocator)).click();
    }

    /**
     * select random dropdown option
     *
     * @param option select specified option
     */
    public void select(String option) {
        element.click();
        String optionLocator = String.format(optionTemplate, option);
        (new BaseElement(optionLocator)).click();
    }

    /**
     * random integer generator in range [0-1]
     *
     * @return random integer [0-1]
     */
    private int selectRandomOption() {
        Random random = new Random();
        int i = random.nextInt(options.list.size() - 1);
        if (i == 0) {
            return i + 1;
        }
        return i;
    }
}
