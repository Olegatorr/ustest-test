package step.by.step;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import javax.annotation.Nullable;
import java.sql.Driver;
import java.util.*;

public class RailcarMarshalingFillPage {

    private static final String URL_MATCH = "rw_train_vizit/edit.xhtml";
    private WebDriver driver;
    private Wait<WebDriver> wait;

    public String id;
    public List<String> errorMessages = new ArrayList<String>();

    // list of all the elements we are interested in
    @FindBy(id = "RwTrainVizitEditForm:name")
    private WebElement number;

    @FindBy(id = "RwTrainVizitEditForm:way:ac_input")
    private WebElement RWTrack;

    @FindBy(id = "RwTrainVizitEditForm:prepareDate_input")
    private WebElement date;

    @FindBy(id = "RwTrainVizitEditForm:comments")
    private WebElement comment;

    @FindBy(id = "RwTrainVizitEditForm:editSaveBtn")
    private WebElement bSave;

    @FindBy(css = ".ui-messages .ui-messages-error ul li span")
    private List<WebElement> errors;

    public RailcarMarshalingFillPage(WebDriver driver, Wait<WebDriver> wait) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("This is not the page you are expected");
        }

        PageFactory.initElements(driver, this);
        this.wait = wait;
        this.driver = driver;
    }

    public RailcarMarshalingViewPage makeRailcarMarshalingSuccess(RailcarMarshalingData data){
        fillNumber(data.number);
        fillTrack(data.RWTrack);
        fillDate(data.date);
        fillComment(data.comment);
        save();

        return new RailcarMarshalingViewPage(driver, wait, data);
    }

    public RailcarMarshalingFillPage makeRailcarMarshalingFail(RailcarMarshalingData data){
        fillNumber(data.number);
        fillTrack(data.RWTrack);
        fillDate(data.date);
        fillComment(data.comment);
        save();

        Assert.assertNotNull(errors, "Error(s) should be present");

        checkErrorMessages();

        return this;
    }

    private void fillNumber(String _number){
        number.click();
        number.clear();
        number.sendKeys(_number);
    }

    private void fillTrack(String _track){
        RWTrack.click();
        RWTrack.clear();
        RWTrack.sendKeys(_track);
        // click on the first dropdown item (otherwise field value will be lost)
        driver.findElement(By.cssSelector("td:nth-child(1)")).click();
    }

    private void fillDate(String _date){
        date.click();
        date.clear();
        date.sendKeys(_date);
    }

    private void fillComment(@Nullable String _comment){
        try{
            comment.click();
            comment.clear();
            comment.sendKeys(_comment);
        }catch (NullPointerException e){
            System.out.println("Comment was empty");
        }
    }

    private void save(){
        bSave.click();
    }

    public RailcarMarshalingFillPage checkErrorMessages(){

        if(errors == null){
            Assert.fail("Error(s) should be present");
            return this;
        }

        for(WebElement error: errors){
            errorMessages.add(error.getText());
        }

        return this;
    }

    public RailcarMarshalingFillPage checkErrorMessages(List<String> expectedErrors){

        if(errors == null){
            Assert.fail("Error(s) should be present");
            return this;
        }

        for(WebElement error:errors){
            Assert.assertTrue(errors.contains(error.getText()));
        }

        return this;
    }

    public RailcarMarshalingFillPage checkErrorMessage(String expectedError){

        if(errors == null){
            Assert.fail("Error(s) should be present");
            return this;
        }

        Assert.assertEquals(errors.get(0).getText(), expectedError, "Comparing Errors");

        return this;
    }

}
