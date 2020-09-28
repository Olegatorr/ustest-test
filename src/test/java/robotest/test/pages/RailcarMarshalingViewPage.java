package robotest.test.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import robotest.test.base.PageBase;
import robotest.test.data.RailcarMarshalingData;

public class RailcarMarshalingViewPage extends PageBase {

    private static final String URL_MATCH = "rw_train_vizit/view.xhtml";
    RailcarMarshalingData data;
    private Wait<WebDriver> wait;
    public String id;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div[2]/div[1]/div/div[1]/div[2]")
    private WebElement number;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[2]/a")
    private WebElement RWTrack;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div[2]/div[1]/div/div[3]/div[2]")
    private WebElement date;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div[2]/div[1]/div/div[4]/div[2]")
    private WebElement work;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div[2]/div[1]/div/div[5]/div[2]")
    private WebElement comment;



    public RailcarMarshalingViewPage() {
        super();
        getId();
        validateData();
    }

    public RailcarMarshalingViewPage(WebDriver driver, Wait<WebDriver> wait) {

        PageFactory.initElements(driver, this);
        this.wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(By.id("object_card_header"))));


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
            throw new IllegalStateException("This is not the page you are expected");
        }
        return true;
    }
}
