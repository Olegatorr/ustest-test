package pages;

import base.PageBase;

/**
 * MainPage page object class
 */
public class MainPage extends PageBase {

    /** URL_MATCH contains part of the UPL which is unique for this page */
    // TODO: parse url_match from json
    String URL_MATCH = "private/main.xhtml";

    /** avatar is the page element, presence of which indicates if we are on the main page*/
    // TODO: ужасный xpath, подумать (лучше придумать способ уникально идентифицировать mainPage (avatar - неуникален) )
    String avatar = "/html/body/div[1]/div[1]/div[2]/ul/li[1]/a/div";

    /**
     * Constructor
     */
    public MainPage() {
        super();
        pageOpenWait(avatar);
    }


    /**
     * isOpen method indicates if this page object is loaded on the correct (corresponding) page
     *
     * @return boolean
     * TRUE if we are on the intended page
     * FALSE if we are on different page
     */
    @Override
    public boolean isOpen() {
        return driver.getCurrentUrl().contains(URL_MATCH);
    }

}
