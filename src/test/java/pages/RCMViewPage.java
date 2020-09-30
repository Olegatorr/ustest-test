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


public class RCMViewPage extends PageBase {

    private static final String URL_MATCH = "rw_train_vizit/view.xhtml";
    public String id;

	private String template = "//*[@id=\"view_grid_content\"]/descendant::div[text()='%s']/following-sibling::div";
    private final Field number = new Field(XpathCreator.createXpath(template, RCMViewOptions.NUMBER));
    private final Field RWTrack = new Field(XpathCreator.createXpath(template, RCMViewOptions.RWTRACK));
    private final Field date = new Field(XpathCreator.createXpath(template,RCMViewOptions.DATE));
    private final Field work = new Field(XpathCreator.createXpath(template, RCMViewOptions.WORK));
    private final Field comment = new Field(XpathCreator.createXpath(template, RCMViewOptions.COMMENTS));

    public RCMViewPage() {
        super();
        getId();
    }

    public RCMViewPage(WebDriver driver, Wait<WebDriver> wait) {

        PageFactory.initElements(driver, this);
        getId();

    }

    @Step("validate Data")
    public void validateData(RCMData data){
        Assert.assertEquals(number.getText(), data.number,"Comparing data");
        Assert.assertEquals(RWTrack.getText(), data.RWTrack,"Comparing data");
        Assert.assertEquals(date.getText(), data.date,"Comparing data");

        // TODO: *LATER* parse expected RCM status from sysParams
        // Assert.assertEquals(work.getText(), [GET EXPECTED WORK FROM SYS PARAMS],"Comparing data");

        Assert.assertEquals(comment.getText(), data.comment,"Comparing data");
    }

    // get RCM ID from URL
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

    @Override
    public boolean isOpen() {
        return this.driver.getCurrentUrl().contains(URL_MATCH);
    }
}
