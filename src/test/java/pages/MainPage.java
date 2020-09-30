package pages;

import base.PageBase;

/**
 * MainPage page object class
 */
public class MainPage extends PageBase {

    /** URL_MATCH contains part of the UPL which is unique for this page */
    // TODO: parse url_match from json
    private static String URL_MATCH = "private/main.xhtml";

    /** avatar is the page element, presence of which indicates if we are on the main page*/
    // TODO: ужасный xpath, подумать (лучше придумать способ уникально идентифицировать mainPage (avatar - неуникален) )
    String avatar = "/html/body/div[1]/div[1]/div[2]/ul/li[1]/a/div";

    /**
     * Constructor
     */
    public MainPage() {
        super(URL_MATCH);
        pageOpenWait(avatar);
    }
}
