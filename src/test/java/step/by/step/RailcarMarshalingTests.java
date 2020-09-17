package step.by.step;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;

public class RailcarMarshalingTests extends TestBase{

    RailcarMarshalingFillPage railcarMarshalingFillPage;
    RailcarMarshalingViewPage railcarMarshalingViewPage;
    RailcarMarshalingData data;

    @Description("negative create Railcar Marshaling 1")
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
        railcarMarshalingFillPage.checkErrorMessage("Marshaling date: Validation Error: Value is required.");
        // TODO: parse expected error message from tos2

    }

    @Description("negative create Railcar Marshaling 2")
    @Test (priority = 1)
    public void CreateRailcarMarshalingNegativeExample2() {

        goToRailcarMarshaling();
        clickNew();

        data = RailcarMarshalingData.createValidData();
        data.number = "";
        railcarMarshalingFillPage.makeRailcarMarshalingFail(data);
        railcarMarshalingFillPage.checkErrorMessage("Number: Validation Error: Value is required.");

    }

    @Description("positive create Railcar Marshaling")
    @Test (priority = 2)
    public void CreateRailcarMarshalingPositive() {

        goToRailcarMarshaling();
        clickNew();

        data = RailcarMarshalingData.createValidData();

        // create RWM, expecting a success
        railcarMarshalingViewPage = railcarMarshalingFillPage.makeRailcarMarshalingSuccess(data);

    }


    @Description("positive edit Railcar Marshaling")
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



