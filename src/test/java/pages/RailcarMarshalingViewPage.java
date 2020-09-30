package pages;

        import base.Field;
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


public class RailcarMarshalingViewPage extends PageBase {

    private static final String URL_MATCH = "rw_train_vizit/view.xhtml";
    RailcarMarshalingData data;
    public String id;

	private String template = "//*[@id=\"view_grid_content\"]/descendant::div[text()='%s']/following-sibling::div";
    private final Field number = new Field(XpathCreator.createXpath(template, RCMViewOptions.NUMBER));
    private final Field RWTrack = new Field(XpathCreator.createXpath(template, RCMViewOptions.RWTRACK));
    private final Field date = new Field(XpathCreator.createXpath(template,RCMViewOptions.DATE));
    private final Field work = new Field(XpathCreator.createXpath(template, RCMViewOptions.WORK));
    private final Field comment = new Field(XpathCreator.createXpath(template, RCMViewOptions.COMMENTS));

    public RailcarMarshalingViewPage() {
        super();
        getId();
    }

    public RailcarMarshalingViewPage(WebDriver driver, Wait<WebDriver> wait) {

        PageFactory.initElements(driver, this);
        //this.wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(By.id("object_card_header"))));


        getId();
    }

    @Step("validate Data")
    private void validateData(){
        Assert.assertEquals(number.getText(), data.number,"Comparing data");
        Assert.assertEquals(RWTrack.getText(), data.RWTrack,"Comparing data");
        Assert.assertEquals(date.getText(), data.date,"Comparing data");
        //Assert.assertEquals(work.getText(), [GET EXPECTED WORK FROM SYS PARAMS],"Comparing data");
        Assert.assertEquals(comment.getText(), data.comment,"Comparing data");
        //Assert.assertTrue(browser.findElement(By.id("object_card_header")).getText().contains(getId()), "Comparing id");
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
