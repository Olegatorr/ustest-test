
package cases;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.*;
import base.TestBase;
import helpers.RailcarMarshalingData;
import pages.MainPage;
import pages.RailcarMarshalingFillPage;
import pages.RailcarMarshalingViewPage;

public class RailcarMarshalingCreateTestsPositive extends TestBase {

    RailcarMarshalingFillPage railcarMarshalingFillPage;
    RailcarMarshalingViewPage railcarMarshalingViewPage;
    MainPage mainPage;
    RailcarMarshalingData data;

    @Story("positive create Railcar Marshaling")
    @Test (priority = 1)
    public void CreateRailcarMarshalingPositive() {
        goToRailcarMarshaling();
        clickNew();
        railcarMarshalingFillPage = new RailcarMarshalingFillPage();
        data = RailcarMarshalingData.createValidData();
        // create RWM, expecting a success
        railcarMarshalingViewPage = railcarMarshalingFillPage.makeRailcarMarshalingSuccess(data);
        Assert.assertTrue(railcarMarshalingViewPage.isOpen());
    }


    @Story("positive edit Railcar Marshaling")
    @Test (priority = 2)
    public void EditRailcarMarshalingPositive(){
        //assuming we are on the same RWM page
        clickEdit();
        data = RailcarMarshalingData.createValidData();
        railcarMarshalingFillPage.makeRailcarMarshalingSuccess(data);
    }
}

