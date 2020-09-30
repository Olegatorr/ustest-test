package pages;

import base.Button;
import base.Field;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.PageBase;
import helpers.RailcarMarshalingData;

import java.net.URL;
import java.util.*;

public class RailcarMarshalingFillPage extends PageBase {

    private static String URL_MATCH = "rw_train_vizit/edit.xhtml";
    @FindBy(css = ".ui-messages .ui-messages-error ul li span")
    private List<WebElement> errors;

    private Field number = new Field("//*[@id=\"RwTrainVizitEditForm:name\"]");
    private Field track = new Field("//*[@id=\"RwTrainVizitEditForm:way:ac_input\"]");
    private Field date = new Field("//*[@id=\"RwTrainVizitEditForm:prepareDate_input\"]");
    private Field comment = new Field("//*[@id=\"RwTrainVizitEditForm:comments\"]");
    private Button bSave = new Button("//*[@id=\"RwTrainVizitEditForm:editSaveBtn\"]");

    public RailcarMarshalingFillPage() {
        super(URL_MATCH);
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


    public boolean isErrorPresent(){
        return !errors.isEmpty();
    }
}
