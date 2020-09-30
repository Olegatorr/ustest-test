package pages;

import Enums.RCMViewOptions;
import base.BaseElement;
import base.ViewField;
import helpers.XpathCreator;
import base.PageBase;


public class RailcarMarshalingViewPage extends PageBase {

    private static String URL_MATCH = "rw_train_vizit/view.xhtml";
    public BaseElement id = new BaseElement("//*[@id=\"object_card_header\"]");
    private String template = "//*[@id=\"view_grid_content\"]/descendant::div[text()='%s']/following-sibling::div";
    private ViewField number = new ViewField(XpathCreator.createXpath(template, RCMViewOptions.NUMBER));
    private ViewField rwTrack = new ViewField(XpathCreator.createXpath(template, RCMViewOptions.RWTRACK));
    private ViewField date = new ViewField(XpathCreator.createXpath(template,RCMViewOptions.DATE));
    private ViewField work = new ViewField(XpathCreator.createXpath(template, RCMViewOptions.WORK));
    private ViewField comments = new ViewField(XpathCreator.createXpath(template, RCMViewOptions.COMMENTS));


    public RailcarMarshalingViewPage() {
        super(URL_MATCH);
        pageOpenWait(id.getLocator());
    }


    public String getId(){
        return id.getText().substring(id.getText().indexOf("#") + 1,id.getText().indexOf(")"));
    }

    @Override
    public boolean isOpen() {
        return driver.getCurrentUrl().contains(URL_MATCH);
    }
}
