package pages;

import base.Button;
import base.Field;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import base.PageBase;
import helpers.RailcarMarshalingData;

import java.util.*;

public class RailcarMarshalingFillPage extends PageBase {

    private static final String URL_MATCH = "rw_train_vizit/edit.xhtml";
    public List<String> errorMessages = new ArrayList<String>();
    @FindBy(css = ".ui-messages .ui-messages-error ul li span")
    private List<WebElement> errors;

    private final Field number = new Field("//*[@id=\"RwTrainVizitEditForm:name\"]");
    private final Field track = new Field("//*[@id=\"RwTrainVizitEditForm:way:ac_input\"]");
    private final Field date = new Field("//*[@id=\"RwTrainVizitEditForm:prepareDate_input\"]");
    private final Field comment = new Field("//*[@id=\"RwTrainVizitEditForm:comments\"]");
    private final Button bSave = new Button("//*[@id=\"RwTrainVizitEditForm:editSaveBtn\"]");

    public RailcarMarshalingFillPage() {
        super();
    }

    @Override
    public boolean isOpen() {
        return driver.getCurrentUrl().contains(URL_MATCH);
    }

    @Description("makeRailcarMarshalingSuccess")
    public RailcarMarshalingViewPage makeRailcarMarshalingSuccess(RailcarMarshalingData data){
        makeRailcarMarshaling(data);
        bSave.click();
        return new RailcarMarshalingViewPage();
    }

    @Description("makeRailcarMarshalingFailure")
    public RailcarMarshalingFillPage makeRailcarMarshalingFail(RailcarMarshalingData data){
        makeRailcarMarshaling(data);
        bSave.click();
        return this;
    }

    public void makeRailcarMarshaling(RailcarMarshalingData data){
        number.sendKeysWithClear(data.number);
        track.sendKeysWithClear(data.RWTrack);
        driver.findElement(By.cssSelector("td:nth-child(1)")).click();
        date.sendKeysWithClear(data.date);
        comment.sendKeysWithClear(data.comment);
    }

    public RailcarMarshalingFillPage checkErrorMessages(){

        errorMessages.clear();

        if(errors == null){
            Assert.fail("Error(s) should be present");
            return this;
        }

        for(WebElement error: errors){
            attachError(error.getText());
            errorMessages.add(error.getText());
        }

        return this;
    }

    public boolean isErrorPresent(){
        return !errors.isEmpty();
    }

    public String getErrorMessage(){

        if(errors == null){
            Assert.fail("Error(s) should be present");
            return "";
        }

        attachError(errors.get(0).getText());
        return errors.get(0).getText();
    }

    @Attachment
    private String attachError(String error){
        return error;
    }


}
