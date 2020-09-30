package base;

import java.util.Random;

public class TableDropDown {
    //protected BaseElement element;
    protected BaseElement optionElement;
    protected ListBaseElement options;
    String optionTemplate;

    public TableDropDown(ListBaseElement options, String optionTemplate) {
        this.options = options;
        this.optionTemplate = optionTemplate;
        //element = new BaseElement(locator);
    }

    public void select() {
        //element.click();
        optionElement = options.list.get(selectRandomOption());
        optionElement.click();
    }

    public void select(String option) {
        //element.click();
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
