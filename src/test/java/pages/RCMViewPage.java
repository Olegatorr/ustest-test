package pages;

import base.Field;
        import helpers.RCMData;
        import io.qameta.allure.Step;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.PageFactory;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.Wait;
        import org.testng.Assert;
		import Enums.RCMViewOptions;
        import base.Field;
        import helpers.LanguageFactory;
        import helpers.XpathCreator;

        import base.PageBase;

/**
 * RCMViewPage page object class
 * contains wrapped elements
 * and methods for any intended page interactions
 */
public class RCMViewPage extends PageBase {

    /** URL_MATCH contains part of the UPL which is unique for this page */
    // TODO: parse url_match from json
    private static final String URL_MATCH = "rw_train_vizit/view.xhtml";

    /** String for ID of the RCM */
    public String id;

    /** wrapped elements of the page */
	private final String template = "//*[@id=\"view_grid_content\"]/descendant::div[text()='%s']/following-sibling::div";
    private final Field number = new Field(XpathCreator.createXpath(template, RCMViewOptions.NUMBER));
    private final Field RWTrack = new Field(XpathCreator.createXpath(template, RCMViewOptions.RWTRACK));
    private final Field date = new Field(XpathCreator.createXpath(template,RCMViewOptions.DATE));
    private final Field work = new Field(XpathCreator.createXpath(template, RCMViewOptions.WORK));
    private final Field comment = new Field(XpathCreator.createXpath(template, RCMViewOptions.COMMENTS));

    /**
     * Constructor
     */
    public RCMViewPage() {
        super(URL_MATCH);
        getId();
    }

    /**
     * validateData validates data showed on the page with given data
     *
     * @param data to compare Page fields data with
     */
    @Step("validate Data")
    public void validateData(RCMData data){
        Assert.assertEquals(number.getText(), data.number);
        Assert.assertEquals(RWTrack.getText(), data.RWTrack);
        Assert.assertEquals(date.getText(), data.date);
        Assert.assertEquals(comment.getText(), data.comment);

        // TODO: *LATER* parse expected RCM status from sysParams
        // Assert.assertEquals(work.getText(), [GET EXPECTED WORK FROM SYS PARAMS]);
    }

    /**
     * getId method returns ID the the RCM, on which page we're currently on
     *
     * @return String ID
     */
    @Step("get Id")
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

    /**
     * isOpen method indicates if this page object is loaded on the correct (corresponding) page
     *
     * @return boolean
     * TRUE if we are on the intended page
     * FALSE if we are on different page
     */
    @Override
    public boolean isOpen() {
        // TODO: find better (unique) way of checking if RCMView is open - action menu isn't unique
        String actions = "//*[@id=\"object_action_menu:menuBtn\"]";
        pageOpenWait(actions);
        return this.driver.getCurrentUrl().contains(URL_MATCH);
    }
}
