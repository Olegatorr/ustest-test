package robotest.test.cases;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.*;
import robotest.test.base.TestBase;
import robotest.test.data.RailcarMarshalingData;
import robotest.test.pages.RailcarMarshalingFillPage;
import robotest.test.pages.RailcarMarshalingViewPage;

public class RailcarMarshalingTests extends TestBase {

    RailcarMarshalingFillPage railcarMarshalingFillPage;
    RailcarMarshalingViewPage railcarMarshalingViewPage;
    RailcarMarshalingData data;

    @Story("negative create Railcar Marshaling 1")
    @Test (priority = 1)
    public void CreateRailcarMarshalingNegativeExample1() {

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
        // check if specific expected error is present
        Assert.assertEquals(
                railcarMarshalingFillPage.getErrorMessage(),
                "Marshaling date: Validation Error: Value is required.",
                "Comparing error message");

    }

    @Story("negative create Railcar Marshaling 2")
    @Test (priority = 1)
    public void CreateRailcarMarshalingNegativeExample2() {

        goToRailcarMarshaling();
        clickNew();

        data = RailcarMarshalingData.createValidData();
        data.number = "";
        railcarMarshalingFillPage.makeRailcarMarshalingFail(data);
        Assert.assertEquals(
                railcarMarshalingFillPage.getErrorMessage(),
                "Number: Validation Error: Value is required.",
                "Comparing error message");

    }

    @Story("positive create Railcar Marshaling")
    @Test (priority = 2)
    public void CreateRailcarMarshalingPositive() {

        goToRailcarMarshaling();
        clickNew();

        data = RailcarMarshalingData.createValidData();

        // create RWM, expecting a success
        railcarMarshalingViewPage = railcarMarshalingFillPage.makeRailcarMarshalingSuccess(data);

    }


    @Story("positive edit Railcar Marshaling")
    @Test (priority = 3)
    public void EditRailcarMarshalingPositive(){

        //assuming we are on the same RWM page
        clickEdit();

        data = RailcarMarshalingData.createValidData();
        railcarMarshalingFillPage.makeRailcarMarshalingSuccess(data);
    }


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



