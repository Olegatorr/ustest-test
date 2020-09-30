package pages;

        import Enums.RCMViewOptions;
        import base.Field;
        import helpers.LanguageFactory;
        import helpers.XpathCreator;
        import base.PageBase;


public class RailcarMarshalingViewPage extends PageBase {

    private static final String URL_MATCH = "rw_train_vizit/view.xhtml";
    public String id;
    private String template = "//*[@id=\"view_grid_content\"]/descendant::div[text()='%s']/following-sibling::div";
    private Field Number = new Field(XpathCreator.createXpath(template, RCMViewOptions.NUMBER));
    private Field RWTrack = new Field(XpathCreator.createXpath(template, RCMViewOptions.RWTRACK));
    private Field Date = new Field(XpathCreator.createXpath(template,RCMViewOptions.DATE));
    private Field Work = new Field(XpathCreator.createXpath(template, RCMViewOptions.WORK));
    private Field Comments = new Field(XpathCreator.createXpath(template, RCMViewOptions.COMMENTS));


    public RailcarMarshalingViewPage() {
        super();
        getId();
    }


    public String getId(){
        String url = driver.getCurrentUrl();
        String subUrl;
        if(id == null){
            try{
                subUrl = url.substring(url.indexOf("id=")+3, url.indexOf("&jfwid"));
            }catch (NullPointerException e){
                subUrl = url.substring(url.indexOf("id="));
            }catch (Exception e){
                System.out.println(e);
                subUrl = null;
            }
            id = subUrl;
        }
        return id;

    }

    @Override
    public boolean isOpen() {
        if (!this.driver.getCurrentUrl().contains(URL_MATCH)) {
            return false;
        }
        return true;
    }
}
