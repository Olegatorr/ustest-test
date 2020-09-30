package pages;

import base.PageBase;

public class MainPage extends PageBase {

    private static String URL_MATCH = "private/main.xhtml";
    //ужасный xpath, подумать
    String avatar = "/html/body/div[1]/div[1]/div[2]/ul/li[1]/a/div";

    public MainPage() {
        super(URL_MATCH);
        pageOpenWait(avatar);
    }

}
