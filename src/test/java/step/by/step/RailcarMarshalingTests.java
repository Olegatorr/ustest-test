package step.by.step;
import org.testng.Assert;
import org.testng.annotations.*;

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
        Assert.assertTrue(true, "Example of Successful assert");
    }

    @Test
    public void test2(){
        Assert.fail("Example of Failed assert");
    }

    @Test
    public void test3(){
        Assert.fail("Example of another failed assert");
    }


}



