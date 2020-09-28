
package robotest.test.cases;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.*;
import robotest.test.base.TestBase;
import robotest.test.data.RailcarMarshalingData;
import robotest.test.pages.RailcarMarshalingFillPage;
import robotest.test.pages.RailcarMarshalingViewPage;

public class RailcarMarshalingCreateTestsPositive extends TestBase {

    RailcarMarshalingFillPage railcarMarshalingFillPage;
    RailcarMarshalingViewPage railcarMarshalingViewPage;
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

