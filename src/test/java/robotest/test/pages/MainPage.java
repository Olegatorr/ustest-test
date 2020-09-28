package robotest.test.pages;

import robotest.test.base.PageBase;

public class MainPage extends PageBase {

    String URL_MATCH = "private/main.xhtml";
    //ужасный xpath, подумать
    String avatar = "/html/body/div[1]/div[1]/div[2]/ul/li[1]/a/div";

    public MainPage() {
        super();
        pageOpenWait(avatar);
    }

    @Override
    public boolean isOpen() {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            return false;
        }
        return true;
    }

}
