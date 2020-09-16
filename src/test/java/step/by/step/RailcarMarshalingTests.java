package step.by.step;
//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class RailcarMarshalingTests extends TestBase{

    RailcarMarshalingFillPage railcarMarshalingFillPage;
    RailcarMarshalingViewPage railcarMarshalingViewPage;
    RailcarMarshalingData data;

    @Test
    public void testCreateRwTrainVizit() {

        goToRailcarMarshaling();
        clickNew();

        // create new page object
        railcarMarshalingFillPage = new RailcarMarshalingFillPage(driver, wait);

        // generate valid field data for all fields
        data = RailcarMarshalingData.createValidData();
        // break some data
        data.date = "";
        // create RWM, expecting a failure
        railcarMarshalingFillPage.makeRailcarMarshalingFail(data);
        railcarMarshalingFillPage.checkErrorMessage("Marshaling date: Validation Error: Value is required.");

        // generate valid field data for all fields
        data = RailcarMarshalingData.createValidData();
        // create RWM, expecting a success
        railcarMarshalingViewPage = railcarMarshalingFillPage.makeRailcarMarshalingSuccess(data);



    }

    /*
    @Test
    public void testEditRwTrainVizit(){

        //assuming we are on the same RWM page
        clickEdit();

        RWTrainVizitName = RWTrainVizitName + " edited";
        RWTrainVizitFillName(RWTrainVizitName);
        RWTrainVizitRWTrack =  "RAIL12";
        RWTrainVizitFillRWTrack(RWTrainVizitRWTrack); // no use as of today: only RAILWAY is correctly configured
        RWTrainVizitDate = formatterForDate.format(new Date());
        RWTrainVizitFillDate(RWTrainVizitDate);
        RWTrainVizitComment = RWTrainVizitComment + " edited";
        RWTrainVizitFillComment(RWTrainVizitComment);

        RWTrainVizitSave();

        List<WebElement> fields = driver.findElements(By.className("ui-g-4"));

        Assert.assertEquals(checkFieldData(fields, "Number"), RWTrainVizitName, "Comparing number");
        Assert.assertEquals(checkFieldData(fields, "RW track"), RWTrainVizitRWTrack, "RW track");
        Assert.assertEquals(checkFieldData(fields, "Marshaling date"), RWTrainVizitDate, "Comparing date");
        Assert.assertEquals(checkFieldData(fields, "Comments"), RWTrainVizitComment, "Comparing comment");
        Assert.assertTrue(driver.findElement(By.id("object_card_header")).getText().contains(id), "Comparing #");

    }
    */

    @Test
    public void test1(){
        goToMainPage();
        Assert.assertTrue(true, "Example of Successful assert");
    }

    @Test
    public void test2(){
        goToMainPage();
        Assert.fail("Example of Failed assert");
    }

    @Test
    public void test3(){
        goToMainPage();
        Assert.fail("Example of another failed assert");
    }


}



