package pages;

import base.Button;
import base.Field;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import base.PageBase;
import helpers.RCMData;

import java.util.*;

/**
 * RCMFillPage page object class
 * contains wrapped elements
 * and methods for any intended page interactions
 */
public class RCMFillPage extends PageBase {

    /** URL_MATCH contains part of the UPL which is unique for this page */
    // TODO: parse url_match from json
    private static final String URL_MATCH = "rw_train_vizit/edit.xhtml";

    //public List<String> errorMessages = new ArrayList<String>();
    //@FindBy(css = ".ui-messages .ui-messages-error ul li span")
    //private List<WebElement> errors;

    /** wrapped elements of the page */
    private final Field number = new Field("//*[@id=\"RwTrainVizitEditForm:name\"]");
    private final Field track = new Field("//*[@id=\"RwTrainVizitEditForm:way:ac_input\"]");
    private final Field date = new Field("//*[@id=\"RwTrainVizitEditForm:prepareDate_input\"]");
    private final Field comment = new Field("//*[@id=\"RwTrainVizitEditForm:comments\"]");
    private final Button bSave = new Button("//*[@id=\"RwTrainVizitEditForm:editSaveBtn\"]");

    /**
     * Constructor
     */
    public RCMFillPage() {
        super(URL_MATCH);
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


    /**
     * makeRCMSuccess parses RCMData data to page fields, then submits the RCM
     *
     * RCM creation is expected to succeed
     *
     * @param data RCMData containing Login and Password
     * @return RCMViewPage object since we are expected to appear on the RCM view page after successful RCM creation
     */
    @Story("make Railcar Marshaling Success")
    public RCMViewPage makeRCMSuccess(RCMData data){
        fill(data);
        bSave.click();
        return new RCMViewPage();
    }


    @Story("make Railcar Marshaling Failure")
    public RCMFillPage makeRCMFail(RCMData data){
        fill(data);
        bSave.click();
        return this;
    }

    /**
     * fill method fills Login and Password fields with given Strings
     *
     * @param data data to be entered to the fields
     */
    @Step("fill all data")
    private void fill(RCMData data){
        number.sendKeysWithClear(data.number);
        track.sendKeysWithClear(data.RWTrack);
        // select 1st available option from dropdown
        // TODO: обернуть
        driver.findElement(By.cssSelector("td:nth-child(1)")).click();
        date.sendKeysWithClear(data.date);
        comment.sendKeysWithClear(data.comment);
    }

    /*
    public RCMFillPage checkErrorMessages(){

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
    */

}
